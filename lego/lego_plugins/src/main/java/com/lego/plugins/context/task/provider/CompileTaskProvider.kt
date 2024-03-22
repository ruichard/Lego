package com.lego.plugins.context.task.provider

import com.ktnail.x.emptyRecursively
import com.ktnail.x.toCamel
import com.lego.plugins.basic.exception.LegoNoKotlinCompileFoundException
import com.lego.plugins.basic.exception.LegoNoSourceToCompileException
import com.lego.plugins.context.model.Lib
import com.lego.plugins.basic.utility.getKotlinCompileTaskByVariant
import com.lego.plugins.basic.utility.legoTask
import org.gradle.api.Project
import org.gradle.api.Task
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class CompileTaskProvider(val project: Project) {
    fun getByLib(lib: Lib): KotlinCompile {
        return (project.getKotlinCompileTaskByVariant(lib.variant.name) ?: throw LegoNoKotlinCompileFoundException(
            lib.variant.name
        )).apply {
            doFirst{
                source = project.fileTree(lib.sourceDir)
                if (lib.sourceDir.emptyRecursively()) {
                    throw LegoNoSourceToCompileException(lib.sourceDir.absolutePath)
                }
            }
        }
    }
}

fun KotlinCompile.getRedirectDestinationTask(lib: Lib): Task {
    return project.legoTask(toCamel("redirect", "destination", "dir", "lego", name)) {
        it.doFirst {
            lib.classesDir.deleteRecursively()
            lib.classesDir.mkdirs()
            destinationDir = lib.classesDir
        }
    }
}