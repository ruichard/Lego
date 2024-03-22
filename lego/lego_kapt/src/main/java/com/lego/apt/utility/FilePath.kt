package com.lego.apt.utility

import com.lego.apt.plugin.Arguments
import com.lego.apt.plugin.PluginArguments
import com.lego.apt.plugin.arguments
import java.io.File
import javax.annotation.processing.ProcessingEnvironment

fun makeDefaultGeneratedDir(processingEnv: ProcessingEnvironment) =
    processingEnv.arguments(Arguments.KAPT_GENERATED)?.let {
        File(it).apply { mkdirs() }
    }

fun cleanAggregateGeneratedDir(arg: PluginArguments) =
    arg.aggregateGenerated?.let {
        File(it).apply {
            deleteRecursively()
            mkdirs()
        }
    }
