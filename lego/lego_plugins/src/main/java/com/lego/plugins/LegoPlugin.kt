package com.lego.plugins

import com.android.build.gradle.api.BaseVariant
import com.ktnail.x.Logger
import com.ktnail.x.toCamel
import com.lego.plugins.basic.LogTags
import com.lego.plugins.basic.exception.LegoPluginNotApplyException
import com.lego.plugins.basic.utility.*
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

/**
 *  The super class of all gradle plugin in lego.
 *
 *  @since 1.3
 */
abstract class LegoPlugin : Plugin<Project> {
    private var _project: Project? = null
    val project: Project
        get() = _project ?: throw LegoPluginNotApplyException()

    override fun apply(project: Project) {
        _project = project
        Logger.p(LogTags.PLUGIN, project) { " APPLIED PLUGIN (${this::class.java})" }
        project.legoExtension
        project.addLegoRepository()
    }

    fun makeGeneratedDirs(onMakeDir: (File, BaseVariant) -> Unit) {
        project.forEachVariant { variant ->
            makeBuildGeneratedDir(variant).let { dir ->
                dir.mkdirs()
                onMakeDir(dir, variant)
            }
        }
    }

    private fun makeBuildGeneratedDir(variant: BaseVariant): File =
        File(project.file(File(project.buildDir, "generated/source/legoKotlin")), variant.name)

    fun addImplementationJarDirDependency(dir: File) {
        project.addDirDependency(DependencyType.IMPLEMENTATION, dir)
    }

    fun addImplementationDependency(groupId: String, artifactId: String, version: String) {
        project.addDependency(DependencyType.IMPLEMENTATION, groupId, artifactId, version)
    }

    fun addImplementationDependency(flavor:String, groupId: String, artifactId: String, version: String) {
        project.addDependency(DependencyType.IMPLEMENTATION, flavor, groupId, artifactId, version)
    }

    fun addImplementationDependency(depProject: Project) {
        project.addProjectDependency(DependencyType.IMPLEMENTATION, depProject)
    }

    fun addImplementationDependency(flavor:String, depProject: Project) {
        project.addProjectDependency(flavor.flavorDependencyType(DependencyType.IMPLEMENTATION), depProject)
    }

    fun addAndroidTestImplementationDependency(groupId: String, artifactId: String, version: String) {
        project.addDependency(DependencyType.ANDROID_TEST_IMPLEMENTATION, groupId, artifactId, version)
    }

    fun addAndroidTestImplementationDependency(flavor:String, groupId: String, artifactId: String, version: String) {
        project.addDependency(DependencyType.ANDROID_TEST_IMPLEMENTATION, flavor, groupId, artifactId, version)
    }

    fun addCompileOnlyJarDirDependency(dir: File) {
        project.addDirDependency(DependencyType.COMPILE_ONLY, dir)
    }

    fun addCompileOnlyDependency(groupId: String, artifactId: String, version: String) {
        project.addDependency(DependencyType.COMPILE_ONLY, groupId, artifactId, version)
    }

    fun addAndroidTestCompileOnlyDependency(groupId: String, artifactId: String, version: String) {
        project.addDependency(DependencyType.ANDROID_TEST_COMPILE_ONLY, groupId, artifactId, version)
    }

    fun addAndroidTestCompileOnlyDependency(flavor:String, groupId: String, artifactId: String, version: String) {
        project.addDependency(toCamel(flavor, DependencyType.ANDROID_TEST_COMPILE_ONLY), groupId, artifactId, version)
    }



}