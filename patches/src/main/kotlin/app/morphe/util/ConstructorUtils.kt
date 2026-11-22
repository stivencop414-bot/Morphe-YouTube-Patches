package app.morphe.util

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.extensions.InstructionExtensions.instructions
import app.morphe.patcher.patch.PatchException
import app.morphe.patcher.util.proxy.mutableTypes.MutableMethod
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.iface.instruction.OneRegisterInstruction

/**
 * Hides the view an inflated constructor builds, without breaking construction.
 *
 * A `return-void` placed before the superclass `&lt;init&gt;` call produces a `VerifyError`
 * on device, and returning right after it leaves every field `null`, which makes later
 * callers throw. Appending `View.setVisibility(GONE)` after the original body keeps the
 * instance valid while preventing the component from ever being drawn.
 *
 * A free local register is derived from the method's own register count, so the helper
 * does not depend on `v0` happening to exist in the target constructor.
 */
internal fun MutableMethod.hideViewAfterConstruction() {
    val body = instructions.toList()
    if (body.isEmpty()) {
        throw PatchException("$definingClass->$name has no instructions; cannot hide its view")
    }

    // Insert immediately before the trailing `return-void` so field setup has completed.
    val returnIndex = body.indexOfLast { it.opcode == Opcode.RETURN_VOID }
    if (returnIndex < 0) {
        throw PatchException("$definingClass->$name does not end in return-void; expected a void constructor")
    }

    val scratch = implementation!!.registerCount - 1
    if (scratch < 0) {
        throw PatchException("$definingClass->$name has no registers; cannot allocate a scratch register")
    }

    addInstructions(
        returnIndex,
        """
            const/16 v$scratch, 0x8
            invoke-virtual {p0, v$scratch}, Landroid/view/View;->setVisibility(I)V
        """.trimIndent(),
    )
}