package com.nerdcutlet.marvel.domain

import com.nerdcutlet.marvel.domain.gateway.MarvelGateway
import com.nerdcutlet.marvel.domain.gateway.MarvelGatewayImpl
import com.nerdcutlet.marvel.domain.usecase.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val domainModule = DI.Module(name = "domainModule") {

    bind() from singleton {
        GetHeroesUseCase(
            instance()
        )
    }

    bind() from singleton {
        GetHeroByIdUseCase(
            instance()
        )
    }

    bind() from singleton {
        AddHeroToSquadUseCase(
            instance()
        )
    }

    bind() from singleton {
        GetSquadHeroesUseCase(
            instance()
        )
    }

    bind() from singleton {
        RemoveHeroFromSquadUseCase(
            instance()
        )
    }

    bind<MarvelGateway>() with singleton {
        MarvelGatewayImpl(
            instance(),
            instance(),
            instance(),
            instance(),
            instance()
        )
    }
}
