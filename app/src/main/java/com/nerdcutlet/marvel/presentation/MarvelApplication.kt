package com.nerdcutlet.marvel.presentation

import android.app.Application
import com.nerdcutlet.marvel.data.dataModule
import com.nerdcutlet.marvel.domain.domainModule
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule

class MarvelApplication : Application(), DIAware {

    override val di by DI.lazy {

        // Android DI
        import(androidXModule(this@MarvelApplication))

        // Setup Modules
        import(presentationModule)
        import(dataModule)
        import(domainModule)
    }
}
