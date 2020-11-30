package com.nerdcutlet.marvel.presentation.herodetail

import android.content.Context
import com.nerdcutlet.marvel.R
import com.nerdcutlet.marvel.presentation.items.*
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class HeroDetailContentRenderer {

    fun renderItems(
        state: HeroDetailState,
        context: Context,
        actionCallback: (() -> Unit)
    ): List<Item<GroupieViewHolder>> {

        val list = mutableListOf<Item<GroupieViewHolder>>()

        if (state.hero != null) {


            val imageList = mutableListOf<Item<GroupieViewHolder>>()

             state.hero.image.forEach {
                imageList +=  HeroBannerItem(it)
            }

           list += buildHorizontalRecyclerItem(
               list = imageList,
               context = context
           )

            if (state.hero.description.isNotEmpty()) {
                list += TextItem(
                    title = state.hero.description,
                    textTitleStyle = TextItem.TextTitleStyle.Body1
                ).withMargin(
                    topMargin = context.resources.getDimension(R.dimen.spacing_all_large).toInt(),
                    leftMargin = context.resources.getDimension(R.dimen.spacing_all_medium).toInt(),
                    rightMargin = context.resources.getDimension(R.dimen.spacing_all_medium)
                        .toInt(),
                    bottomMargin = context.resources.getDimension(R.dimen.spacing_all_medium)
                        .toInt()
                )
            }
        }

        return list
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
