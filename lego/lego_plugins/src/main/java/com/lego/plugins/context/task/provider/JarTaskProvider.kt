package com.lego.plugins.context.task.provider

import com.ktnail.x.emptyRecursively
import com.lego.plugins.basic.exception.LegoNoClassesToJarException
import com.lego.plugins.context.model.Lib
import com.lego.plugins.basic.utility.legoTask
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.jvm.tasks.Jar

class JarTaskProvider(val project: Project) {
    fun getByLib(lib: Lib): Task {
        return project.legoTask(
            lib.jarTaskName,
            Jar::class.java
        ) { task ->
            task.from(lib.jarFromDir)
            task.archiveBaseName.set(lib.artifactId)
            task.destinationDirectory.set(lib.jarToDir)
        }.doFirst {
            if (lib.jarFromDir.emptyRecursively()) {
                throw LegoNoClassesToJarException(lib.jarFromDir.absolutePath)
            }
            lib.jarToDir.deleteRecursively()
            lib.jarToDir.mkdirs()
        }
    }
}