package com.lego.plugins.extension.root.model

import com.lego.plugins.extension.context.source.MavenExtension
import com.lego.plugins.extension.root.packing.NoSourceExtension
import com.lego.plugins.root.router.RouterRegister

interface PickMode{
    val register: RouterRegister
}

class ProjectMode : PickMode {
    override val register: RouterRegister = RouterRegister.NEW_INSTANCE
}

data class MavenMode(
    val maven: MavenExtension?
) : PickMode {
    override val register: RouterRegister = RouterRegister.NEW_INSTANCE
}

data class NoSourceMode(
    val dynamic: NoSourceExtension?
) : PickMode {
    override val register: RouterRegister
        get() = if (dynamic == null) RouterRegister.NEW_INSTANCE
        else if (!dynamic.enableAggregate) RouterRegister.NONE
        else if (dynamic.enableReflect) RouterRegister.REFLECT_INSTANCE
        else RouterRegister.NEW_INSTANCE
}