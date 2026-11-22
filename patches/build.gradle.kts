group = "app.morphe"

patches {
    about {
        name = "YouTube Patches"
        description = "Privacy, ad-blocking, and feature patches for YouTube compatible with Morphe."
        source = "https://github.com/YOUR_USERNAME/Morphe-YouTube-Patches"
        author = "YOUR_USERNAME"
        contact = "https://github.com/YOUR_USERNAME/Morphe-YouTube-Patches/issues"
        website = "https://github.com/YOUR_USERNAME/Morphe-YouTube-Patches"
        license = "GNU General Public License v3.0, with additional GPL section 7 requirements"
    }
}

dependencies {
    compileOnly(libs.morphe.patcher)
    implementation(libs.gson)
    implementation(libs.guava)
}

tasks.register<JavaExec>("generatePatchesList") {
    description = "Generates patches-list.json from the built .mpp bundle."
    dependsOn(tasks.named("jar"))
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("app.morphe.util.PatchListGeneratorKt")
    args(project.version.toString())
}