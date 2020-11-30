package com.nerdcutlet.depop.presentation

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nerdcutlet.depop.presentation.productdetail.ProductDetailContentRenderer
import com.nerdcutlet.depop.presentation.productdetail.ProductDetailViewModel
import com.nerdcutlet.depop.presentation.productlist.ProductListContentRenderer
import com.nerdcutlet.depop.presentation.productlist.ProductListViewModel
import org.kodein.di.*
import org.kodein.di.android.x.AndroidLifecycleScope

val presentationModule = DI.Module("presentationModule") {

    bind<ProductListViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {

        @Suppress("UNCHECKED_CAST")
        val vmFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProductListViewModel(instance()) as T
            }
        }
        return@singleton ViewModelProvider(context, vmFactory)[ProductListViewModel::class.java]
    }

    bind<ProductDetailViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {

        @Suppress("UNCHECKED_CAST")
        val vmFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProductDetailViewModel(instance(), instance()) as T
            }
        }
        return@singleton ViewModelProvider(context, vmFactory)[ProductDetailViewModel::class.java]
    }

    bind() from singleton { ProductListContentRenderer() }

    bind() from singleton { ProductDetailContentRenderer() }
}
