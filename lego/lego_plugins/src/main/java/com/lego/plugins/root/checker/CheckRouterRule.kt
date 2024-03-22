package com.lego.plugins.root.checker

import com.ktnail.x.Logger
import com.lego.annotations.source.RContextLib
import com.lego.annotations.source.RGenerated
import com.lego.plugins.basic.Constants
import com.lego.plugins.basic.LogTags
import com.lego.plugins.basic.exception.LegoRouterAndContextVersionsNotMatchException
import com.lego.plugins.basic.utility.Ext
import com.lego.plugins.basic.utility.p
import com.lego.plugins.basic.utility.propertyOr
import javassist.CtClass
import org.gradle.api.Project

interface CheckRouterRule {
    companion object{
        val RULES = arrayOf<CheckRouterRule>(CheckLowerThan1Dot9Rule())
    }
    fun doCheck(project: Project, ctClass: CtClass)
}

class CheckLowerThan1Dot9Rule : CheckRouterRule {
    override fun doCheck(project: Project, ctClass: CtClass){
        if (ctClass.packageName.startsWith("${Constants.Router.GENERATE_BASIC_PACKAGE_NAME}.context")){
            val routerVersion = project.propertyOr(Ext.LEGO_ROUTER_VERSION) { "not-set" }
            val kaptVersion = project.propertyOr(Ext.LEGO_KAPT_VERSION) { "not-set" }
            (ctClass.getAnnotation(RGenerated::class.java) as? RGenerated)?.let { generated ->
                if (generated.kind == "context") {
                    val version = generated.by.removePrefix("lego-kapt:").split(".")
                    Logger.p(LogTags.CHECK_ROUTER_VERSION, project) { " find RGenerated context class:${ctClass.name} by:${generated.by}" }
                    if (version[0].toIntOrNull() ?: -1 <= 1 && version[1].toIntOrNull() ?: -1 < 9) {
                        val uri = (ctClass.getAnnotation(RContextLib::class.java) as? RContextLib)?.uri
                        throw LegoRouterAndContextVersionsNotMatchException(generated.by, uri,
                            routerVersion,"please re-publish context by [${kaptVersion}] or higher version!")
                    }
                }
            }
        }
    }
}