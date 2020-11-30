package com.nerdcutlet.depop.presentation.items

import androidx.core.widget.TextViewCompat
import com.nerdcutlet.depop.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.item_text.view.*

data class TextItem(
    private val title: String,
    private val textTitleStyle: TextTitleStyle = TextTitleStyle.Headline4
) : Item<GroupieViewHolder>() {

    override fun getLayout() = R.layout.item_text

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.item_text.apply {
            text = title

            when (textTitleStyle) {
                TextTitleStyle.Headline4 -> {
                    TextViewCompat.setTextAppearance(
                        this,
                        R.style.TextAppearance_MaterialComponents_Headline3
                    )
                }
                TextTitleStyle.Body1 -> {
                    TextViewCompat.setTextAppearance(
                        this,
                        R.style.TextAppearance_MaterialComponents_Body1
                    )
                }
            }
        }
    }

    enum class TextTitleStyle {
        Headline4,
        Body1
    }
}
