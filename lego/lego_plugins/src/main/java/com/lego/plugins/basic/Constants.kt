package com.lego.plugins.basic

import com.ktnail.x.toPascal
import com.ktnail.x.uriToSnake
import org.gradle.api.Project
import java.io.File

object Constants {
    object Router {
        const val PACKAGE_NAME = "com.lego"
        const val LEGO_CLASS_NAME = "$PACKAGE_NAME.Lego"
        const val INIT_FUNCTION_NAME = "init"
        const val GENERATE_BASIC_PACKAGE_NAME = "lego.generate"
        const val GENERATE_PACKAGE_NAME = "$GENERATE_BASIC_PACKAGE_NAME.root"
        const val GENERATE_FILE_NAME = "LegoExt"
        const val KEEP_ANNOTATION_CLASS_NAME = "androidx.annotation.Keep"
        const val METHOD_REGISTER_NAME = "registerAggregatable"

    }

    object Aggregate {
        object Declare{
            private const val GENERATE_PACKAGE_NAME = "lego.generate"
            fun makeAggregatePackageName(uri: String): String = "$GENERATE_PACKAGE_NAME.aggregate.${uri.uriToSnake()}"
            fun makeAggregateClassName(name: String) = toPascal(name, "Aggregate")
            const val REGISTER_FUNCTION_NAME = "register"
        }
    }

    object Lib {
        fun classesTmpDirPath(project: Project, libType: String,  variantName: String, uri: String) =
            "${project.buildDir}${File.separator}lego${File.separator}${variantName}${File.separator}${uri.uriToSnake()}${File.separator}${libType}_classes"

        fun generatedSourceDir(project: Project, variantName: String, uri: String) =
            "${project.buildDir}${File.separator}lego${File.separator}${variantName}${File.separator}${uri.uriToSnake()}${File.separator}generated${File.separator}source"

    }

    object KDoc {
        fun init() =
            """
            |init Lego Router.
            """.trimMargin()
    }
}
