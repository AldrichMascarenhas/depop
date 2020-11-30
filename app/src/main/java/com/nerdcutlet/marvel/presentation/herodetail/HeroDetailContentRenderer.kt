package com.nerdcutlet.marvel.presentation.herodetail

import android.content.Context
import com.nerdcutlet.marvel.R
import com.nerdcutlet.marvel.presentation.items.*
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

            list += HeroBannerItem(
                url = "${state.hero.heroThumbnailPath}/standard_amazing.${state.hero.heroThumbnailExtension}"
            )

            list += TextItem(
                title = state.hero.name,
                textTitleStyle = TextItem.TextTitleStyle.Headline4
            ).withMargin(
                topMargin = context.resources.getDimension(R.dimen.spacing_all_medium).toInt(),
                leftMargin = context.resources.getDimension(R.dimen.spacing_all_medium).toInt(),
                rightMargin = context.resources.getDimension(R.dimen.spacing_all_medium).toInt()
            )

            list += ButtonItem(
                title = if (state.isLiked) context.resources.getString(R.string.fire_from_squad)
                else context.resources.getString(
                    R.string.hire_to_squad
                ),
                style = if (state.isLiked) ButtonItem.SquadButtonStyle.FIRE
                else ButtonItem.SquadButtonStyle.HIRE
            ) {
                actionCallback()
            }.withMargin(
                topMargin = context.resources.getDimension(R.dimen.spacing_all_medium).toInt(),
                leftMargin = context.resources.getDimension(R.dimen.spacing_all_medium).toInt(),
                rightMargin = context.resources.getDimension(R.dimen.spacing_all_medium).toInt()
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
}
