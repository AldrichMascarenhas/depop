package com.nerdcutlet.marvel.presentation.herolist

import android.content.Context
import com.nerdcutlet.marvel.R
import com.nerdcutlet.marvel.domain.model.HeroDomainModel
import com.nerdcutlet.marvel.presentation.items.HeroItem
import com.nerdcutlet.marvel.presentation.items.HeroProfileItem
import com.nerdcutlet.marvel.presentation.items.HorizontalRecyclerItem
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class HeroListContentRenderer {

    fun renderItems(
        list: List<HeroDomainModel>,
        actionCallback: ((Int) -> Unit)
    ): List<Item<GroupieViewHolder>> {

        val newlsit = list.map {
            buildHeroItem(it) { heroId ->
                actionCallback(heroId)
            }
        }

        return newlsit
    }

    private fun buildHeroItem(
        heroDomainModel: HeroDomainModel,
        actionCallback: ((Int) -> Unit)
    ): HeroItem {
        return HeroItem(
            heroId = heroDomainModel.id,
            name = heroDomainModel.description,
            url = heroDomainModel.image,
            actionCallback = {
                actionCallback(it)
            }
        )
    }
}
