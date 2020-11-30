package com.nerdcutlet.depop.presentation.herolist

import com.nerdcutlet.depop.domain.model.ProductDomainModel
import com.nerdcutlet.depop.presentation.items.ProductItem
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class HeroListContentRenderer {

    fun renderItems(
        list: List<ProductDomainModel>,
        actionCallback: ((Int) -> Unit)
    ): List<Item<GroupieViewHolder>> {

        return list.map {
            buildProductItem(it) { heroId ->
                actionCallback(heroId)
            }
        }
    }

    private fun buildProductItem(
        productDomainModel: ProductDomainModel,
        actionCallback: ((Int) -> Unit)
    ): ProductItem {
        return ProductItem(
            heroId = productDomainModel.id,
            name = productDomainModel.description,
            url = productDomainModel.image,
            actionCallback = {
                actionCallback(it)
            }
        )
    }
}
