package com.nerdcutlet.marvel.presentation.items

import androidx.core.content.ContextCompat
import com.nerdcutlet.marvel.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.item_button.view.*

data class ButtonItem(
    private val title: String,
    private val style: SquadButtonStyle,
    val actionCallback: (() -> Unit)
) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.item_button.apply {
            text = title

            when (style) {
                SquadButtonStyle.HIRE -> item_button.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorPrimary
                    )
                )
                SquadButtonStyle.FIRE -> item_button.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.fireFromSquad
                    )
                )
            }
            setOnClickListener {
                actionCallback()
            }
        }
    }

    override fun getLayout() = R.layout.item_button

    enum class SquadButtonStyle {
        HIRE,
        FIRE
    }
}
