package com.nerdcutlet.marvel.presentation.herodetail

sealed class HeroDetailActions {
    object OnResume : HeroDetailActions()
    object LoadSquadHeroState : HeroDetailActions()
}
