package com.dev.hackcbs.extensions.binding

import androidx.viewbinding.ViewBinding

/**
 * Interface for components which will encapsulate generating bindings.
 * */
interface BindingComponent<B : ViewBinding> {

    /**
     * Property to access the generated binding.
     * */
    val binding : B

    /**
     * Called after binding is generated.
     * Override this to do things like initializing views, etc.
     * */
    fun B.initialize() {  }
}