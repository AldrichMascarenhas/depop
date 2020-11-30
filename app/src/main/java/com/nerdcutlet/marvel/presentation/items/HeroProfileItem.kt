package com.nerdcutlet.marvel.presentation.items

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.nerdcutlet.marvel.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.item_hero_profile.view.*

data class HeroProfileItem(
    val heroId: Int,
    val url: String,
    val title: String,
    val actionCallback: ((Int) -> Unit)
) : Item<GroupieViewHolder>() {

    override fun getLayout() = R.layout.item_hero_profile

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {

            item_hero_profile_name.text = title
            setOnClickListener {
                actionCallback(heroId)
            }

            Glide.with(item_hero_profile_imageview.context)
                .load(url)
                .transform(CircleCrop())
                .into(item_hero_profile_imageview)
        }
    }
}
