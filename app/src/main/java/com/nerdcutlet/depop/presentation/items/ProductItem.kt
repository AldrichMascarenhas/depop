package com.nerdcutlet.depop.presentation.items

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.nerdcutlet.depop.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.item_product.view.*

data class ProductItem(
    val productId: Int,
    val name: String,
    val url: String,
    val actionCallback: ((Int) -> Unit)
) : Item<GroupieViewHolder>() {

    override fun getLayout() = R.layout.item_product

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.itemView.apply {

            item_hero_textview.text = name

            setOnClickListener {
                actionCallback(productId)
            }

            Glide.with(item_hero_imageview.context)
                .load(url)
                .transform(CircleCrop())
                .into(item_hero_imageview)
        }
    }

    override fun getId() = productId.toLong()
}
