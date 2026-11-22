package app.morphe.patches.youtube.interaction

import app.morphe.patcher.extensions.InstructionExtensions.instructions
import app.morphe.patcher.extensions.InstructionExtensions.replaceInstruction
import app.morphe.patcher.patch.PatchException
import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.patches.youtube.misc.SecureSurfaceViewFingerprint
import app.morphe.util.getReference
import app.morphe.util.indexOfFirstInstructionOrThrow
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.builder.instruction.BuilderInstruction21s
import com.android.tools.smali.dexlib2.iface.instruction.OneRegisterInstruction
import com.android.tools.smali.dexlib2.iface.instruction.RegisterRangeInstruction
import com.android.tools.smali.dexlib2.iface.instruction.FiveRegisterInstruction
import com.android.tools.smali.dexlib2.iface.reference.MethodReference

@Suppress("unused")
val allowScreenshotsPatch = patch(
    name = "Allow screenshots and screen recording",
    description = "Disables the secure flag on the player's `SurfaceView` so screenshots and screen recording " +
        "work. DRM-protected streams may still be blanked by Android codecs.",
    default = true,
) {
    category("Interaction")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        SecureSurfaceViewFingerprint.method.apply {
            val all = instructions.toList()

            val setSecureIndex = indexOfFirstInstructionOrThrow {
                getReference<MethodReference>()?.let {
                    it.name == "setSecure" && it.definingClass == "Landroid/view/SurfaceView;"
                } == true
            }

            // `setSecure(Z)` is invoked as `invoke-virtual {vSurface, vFlag}`.
            // Resolve the flag register from the invoke itself rather than assuming
            // a fixed instruction offset or a specific `const` form.
            val flagRegister = when (val invoke = all[setSecureIndex]) {
                is RegisterRangeInstruction -> invoke.startRegister + 1
                is FiveRegisterInstruction -> {
                    val registers = listOf(
                        invoke.registerC,
                        invoke.registerD,
                        invoke.registerE,
                        invoke.registerF,
                        invoke.registerG,
                    ).take(invoke.registerCount)
                    registers.getOrNull(1)
                        ?: throw PatchException("setSecure invoke has too few registers in $definingClass->$name")
                }
                else -> throw PatchException("Unexpected setSecure invoke format in $definingClass->$name")
            }

            val loadIndex = (setSecureIndex - 1 downTo 0).firstOrNull { index ->
                val instruction = all[index]
                instruction.opcode in setOf(
                    Opcode.CONST_4,
                    Opcode.CONST_16,
                    Opcode.CONST,
                    Opcode.CONST_HIGH16,
                ) && (instruction as? OneRegisterInstruction)?.registerA == flagRegister
            } ?: throw PatchException("setSecure flag load not found in $definingClass->$name")

            replaceInstruction(
                loadIndex,
                BuilderInstruction21s(Opcode.CONST_16, flagRegister, 0),
            )
        }
    }
}