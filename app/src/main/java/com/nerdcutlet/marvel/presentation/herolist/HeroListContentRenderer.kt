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
            name = heroDomainModel.name,
            url = "${heroDomainModel.heroThumbnailPath}/standard_amazing.${heroDomainModel.heroThumbnailExtension}",
            actionCallback = {
                actionCallback(it)
            }
        )
    }

    fun renderSquadHeaderItem(
        list: List<HeroDomainModel>,
        context: Context,
        actionCallback: ((Int) -> Unit)
    ): Item<GroupieViewHolder> {

        val newlist = list.map {
            buildHeroProfileItem(it) {
                actionCallback(it)
            }
        }
        return buildHorizontalRecyclerItem(newlist, context)
    }

    private fun buildHeroProfileItem(
        heroDomainModel: HeroDomainModel,
        actionCallback: ((Int) -> Unit)
    ): HeroProfileItem {
        return HeroProfileItem(
            heroId = heroDomainModel.id,
            url = "${heroDomainModel.heroThumbnailPath}/standard_amazing.${heroDomainModel.heroThumbnailExtension}",
            title = heroDomainModel.name,
            actionCallback = {
                actionCallback(it)
            }
        )
    }

    private fun buildHorizontalRecyclerItem(
        list: List<Group>,
        context: Context
    ): HorizontalRecyclerItem {
        return HorizontalRecyclerItem(
            title = context.resources.getString(R.string.squad_header_title),
            adapter = GroupAdapter<GroupieViewHolder>().apply { addAll(list) }

        )
    }
}
