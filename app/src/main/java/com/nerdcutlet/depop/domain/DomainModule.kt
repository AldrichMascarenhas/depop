package com.nerdcutlet.depop.domain

import com.nerdcutlet.depop.domain.gateway.DepopGateway
import com.nerdcutlet.depop.domain.gateway.DepopGatewayImpl
import com.nerdcutlet.depop.domain.usecase.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val domainModule = DI.Module(name = "domainModule") {

    bind() from singleton {
        GetProductsUseCase(
            instance()
        )
    }

    bind() from singleton {
        GetProductByIdUseCase(
            instance()
        )
    }

    bind<DepopGateway>() with singleton {
        DepopGatewayImpl(
            instance(),
            instance()
        )
    }
}
