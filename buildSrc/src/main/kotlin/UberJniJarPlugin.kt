import dev.nokee.platform.jni.JarBinary
import dev.nokee.platform.jni.JniJarBinary
import dev.nokee.platform.jni.JniLibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.FileTree
import org.gradle.api.provider.Provider
import org.gradle.jvm.tasks.Jar
import org.gradle.nativeplatform.tasks.LinkSharedLibrary

class UberJniJarPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.tasks.named("jar", Jar::class.java) {
            configure(this)
        }
    }

    private fun configure(task: Jar) {
        val project = task.project
        val logger = task.logger
        val library = project.extensions.getByType(JniLibraryExtension::class.java)

        // Prevent variants from being published.
        val targetMachines = library.targetMachines.forUseAtConfigurationTime().get()
        val variantNames = targetMachines.map { "${project.name}-${it.architectureString}" }
        project.configurations.forEach { config ->
            config.artifacts.removeIf { it.name in variantNames }
        }

        logger.info("${project.name}: Merging binaries into the JVM Jar.")
        library.variants.configureEach {
            binaries.withType(JniJarBinary::class.java).configureEach {
                jarTask.configure { enabled = false }
            }
            task.dependsOn(sharedLibrary.linkTask)
        }
        targetMachines.forEach { target ->
            val targetVariant = library.variants.filter { it.targetMachine == target }.map {
                check(it.size == 1)
                it.first()
            }
            if (!target.targetsHost) {
                // nativeRuntimeFiles will be populated in this case due to using pre-build binaries.
                task.from(targetVariant.map { it.nativeRuntimeFiles }) {
                    into(targetVariant.map { it.resourcePath })
                }
            } else {
                task.from(targetVariant.map { it.sharedLibrary.linkTask.map { linkTask -> linkTask.linkedFile } }) {
                    into(targetVariant.map { it.resourcePath })
                }
            }
        }
    }

    private fun JniJarBinary.asZipTree(project: Project): Provider<FileTree> =
        jarTask.map { project.zipTree(it.archiveFile) }

}
