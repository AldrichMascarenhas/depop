package com.nerdcutlet.depop.presentation.herodetail

sealed class HeroDetailActions {
    object OnResume : HeroDetailActions()
    object LoadSquadHeroState : HeroDetailActions()
}
