package com.nerdcutlet.marvel.presentation.items

import com.nerdcutlet.marvel.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.item_error.view.*

data class ConnectionErrorItem(
    private val customTitle: String,
    val retryActionactionCallback: (() -> Unit)
) : Item<GroupieViewHolder>() {

    override fun getLayout(): Int = R.layout.item_error

    override fun getId() = hashCode().toLong()
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.itemView.apply {

            connection_error_title.text = customTitle

            button_retry_connect_error.setOnClickListener {
                retryActionactionCallback()
            }

            lottie_unable_to_connect.setAnimation("lottie_animations/network_failure.json")
            if (!lottie_unable_to_connect.isAnimating) {
                lottie_unable_to_connect.playAnimation()
            }
        }
    }
}
