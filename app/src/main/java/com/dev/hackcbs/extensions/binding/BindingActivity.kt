package com.dev.hackcbs.extensions.binding

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.viewbinding.ViewBinding

open class BindingActivity<B : ViewBinding>(bindingClass: Class<B>) : ComponentActivity(), BindingComponent<B> {

    private val binder = Binder(bindingClass)

    final override val binding: B get() = binder.binding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binder.generateBinding(layoutInflater, null, this)
        binding.initialize()
        setContentView(binding.root)
    }
}