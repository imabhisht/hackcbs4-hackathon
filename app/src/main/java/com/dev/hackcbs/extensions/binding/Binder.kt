package com.dev.hackcbs.extensions.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding

/**
 * Convenience class to help implement [BindingComponent].
 * */
class Binder<B : ViewBinding>(private val bindingClass: Class<B>) : DefaultLifecycleObserver {

    private var _binding: B? = null
    val binding : B get() = requireNotNull(_binding) { "Binding is either not initialized yet or already destroyed." }

    fun generateBinding(inflater: LayoutInflater, viewGroup: ViewGroup?, lifecycleOwner: LifecycleOwner){
        _binding = bindingClass.inflate(inflater, viewGroup, false).apply {
            if (this is ViewDataBinding)
                this.lifecycleOwner = lifecycleOwner
        }
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        _binding = null
    }
}

@Suppress("Unchecked_Cast")
internal fun <B : ViewBinding> Class<B>.inflate(inflater: LayoutInflater, viewGroup: ViewGroup?, attachToParent : Boolean) : B {
    return getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        .invoke(null, inflater, viewGroup, attachToParent) as B
}