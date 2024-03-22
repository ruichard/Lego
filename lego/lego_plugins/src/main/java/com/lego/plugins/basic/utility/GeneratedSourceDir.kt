package com.lego.plugins.basic.utility

import com.lego.plugins.basic.Constants
import org.gradle.api.Project
import java.io.File


fun getGeneratedSourceDir(project: Project, variantName: String, uri: String) =
        File(Constants.Lib.generatedSourceDir(project, variantName, uri)).apply {
                mkdirs()
        }