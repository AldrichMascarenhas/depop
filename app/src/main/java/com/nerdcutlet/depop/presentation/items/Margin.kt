package com.nerdcutlet.depop.presentation.items

import com.nerdcutlet.depop.presentation.utils.setMargin
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

data class Margin(
    private val layoutMargin: Int = 0,
    private val topMargin: Int? = null,
    private val rightMargin: Int? = null,
    private val bottomMargin: Int? = null,
    private val leftMargin: Int? = null,
    private val childComponent: () -> Item<GroupieViewHolder>
) : Item<GroupieViewHolder>() {

    private val child by lazy { childComponent() }

    override fun getLayout(): Int = child.getItem(0).layout

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        child.bind(viewHolder, position)

        viewHolder.itemView.apply {

            this.setMargin(
                leftMargin = leftMargin ?: layoutMargin,
                topMargin = topMargin ?: layoutMargin,
                rightMargin = rightMargin ?: layoutMargin,
                bottomMargin = bottomMargin ?: layoutMargin
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is Margin && child == other.child
    }

    override fun hashCode(): Int {
        return child.hashCode()
    }

    override fun getId() = child.id
}

fun Item<GroupieViewHolder>.withMargin(
    layoutMargin: Int = 0,
    topMargin: Int? = null,
    rightMargin: Int? = null,
    bottomMargin: Int? = null,
    leftMargin: Int? = null
) = Margin(layoutMargin, topMargin, rightMargin, bottomMargin, leftMargin) { this }
