package com.lego.plugins

import com.lego.plugins.basic.exception.LegoPluginNotApplyException
import com.lego.plugins.context.AllContextController
import com.lego.plugins.module.ModuleController
import org.gradle.api.Project

/**
 * The the plugin of lego config project (gradle root).
 * Provide the ability to define contexts & ext configs.
 *
 * @ Since:1.3
 */
class MainPlugin : LegoPlugin() {

    private var _moduleController: ModuleController? = null
    private val moduleController: ModuleController
        get() = _moduleController ?: throw LegoPluginNotApplyException()

    private var _contextController: AllContextController? = null
    private val contextController: AllContextController
        get() = _contextController ?: throw LegoPluginNotApplyException()

    override fun apply(project: Project) {
        super.apply(project)

        _contextController = AllContextController(project)
        contextController.init()

        _moduleController = ModuleController(project)
        moduleController.init()

    }


}

