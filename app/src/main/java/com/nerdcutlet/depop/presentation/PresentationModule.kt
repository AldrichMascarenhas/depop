package com.nerdcutlet.depop.presentation

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nerdcutlet.depop.presentation.herodetail.HeroDetailContentRenderer
import com.nerdcutlet.depop.presentation.herodetail.HeroDetailViewModel
import com.nerdcutlet.depop.presentation.herolist.HeroListContentRenderer
import com.nerdcutlet.depop.presentation.herolist.HeroListViewModel
import org.kodein.di.*
import org.kodein.di.android.x.AndroidLifecycleScope

val presentationModule = DI.Module("presentationModule") {

    bind<HeroListViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {

        @Suppress("UNCHECKED_CAST")
        val vmFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HeroListViewModel(instance()) as T
            }
        }
        return@singleton ViewModelProvider(context, vmFactory)[HeroListViewModel::class.java]
    }

    bind<HeroDetailViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {

        @Suppress("UNCHECKED_CAST")
        val vmFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HeroDetailViewModel(instance(), instance()) as T
            }
        }
        return@singleton ViewModelProvider(context, vmFactory)[HeroDetailViewModel::class.java]
    }

    bind() from singleton { HeroListContentRenderer() }

    bind() from singleton { HeroDetailContentRenderer() }
}
