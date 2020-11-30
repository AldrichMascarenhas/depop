package com.nerdcutlet.depop.presentation

import android.app.Application
import com.nerdcutlet.depop.data.dataModule
import com.nerdcutlet.depop.domain.domainModule
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule

class DepopApplication : Application(), DIAware {

    override val di by DI.lazy {

        // Android DI
        import(androidXModule(this@DepopApplication))

        // Setup Modules
        import(presentationModule)
        import(dataModule)
        import(domainModule)
    }
}
