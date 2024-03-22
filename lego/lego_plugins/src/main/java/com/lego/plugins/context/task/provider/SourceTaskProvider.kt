package com.lego.plugins.context.task.provider

import com.ktnail.x.emptyRecursively
import com.ktnail.x.toCamel
import com.lego.plugins.basic.exception.LegoNoSourceToCompileException
import com.lego.plugins.context.model.Lib
import com.lego.plugins.basic.utility.Ext
import com.lego.plugins.basic.utility.propertyOr
import com.lego.plugins.basic.utility.legoTask
import org.gradle.api.Project
import org.gradle.api.Task
import java.io.File

class SourceTaskProvider(val project: Project) {
    fun getByLib(lib: Lib): Task {
        return project.legoTask(toCamel("source", "lego", lib.variant.buildType.name)) {
            it.doFirst {
                val kaptSourceFolder = project.propertyOr(Ext.LEGO_KAPT_SOURCE_FOLDER) { "generated/source/kaptKotlin" }
                val kaptSourceDir = File(project.buildDir, kaptSourceFolder + File.separator + lib.variant.name + File.separator + "lego")
                if(kaptSourceDir.emptyRecursively())
                    throw LegoNoSourceToCompileException(kaptSourceDir.absolutePath)
                else
                    lib.sourceDir.let { sourceDir->
                        sourceDir.deleteRecursively()
                        kaptSourceDir.copyRecursively(File(sourceDir,"lego").apply { mkdirs() })
                    }
            }
        }
    }
}