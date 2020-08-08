package com.anifichadia.employeehub.framework

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
object ViewModelInitialiser {
    inline fun <reified ViewModelT : ViewModel> forActivity(
        activity: ComponentActivity,
        noinline viewModelProvider: () -> ViewModelT
    ): ViewModelT {
        return ViewModelProvider(
            activity,
            SimpleViewModelFactory(viewModelProvider)
        ).get(ViewModelT::class.java)
    }

    inline fun <reified ViewModelT : ViewModel> forFragment(
        fragment: Fragment,
        noinline viewModelProvider: () -> ViewModelT
    ): ViewModelT {
        return ViewModelProvider(
            fragment,
            SimpleViewModelFactory(viewModelProvider)
        ).get(ViewModelT::class.java)
    }


    class SimpleViewModelFactory<ViewModelT : ViewModel>(
        private val provider: () -> ViewModelT
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return provider() as T
        }
    }
}
