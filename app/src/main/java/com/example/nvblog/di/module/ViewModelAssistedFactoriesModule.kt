/*
 * Copyright (C) Hanwha S&C Ltd., 2019. All rights reserved.
 *
 * This software is covered by the license agreement between
 * the end user and Hanwha S&C Ltd., and may be
 * used and copied only in accordance with the terms of the
 * said agreement.
 *
 * Hanwha S&C Ltd., assumes no responsibility or
 * liability for any errors or inaccuracies in this software,
 * or any consequential, incidental or indirect damage arising
 * out of the use of the software.
 */

package com.example.nvblog.di.module

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.MapKey
import dagger.Module
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2019-09-10 <p/>
 */

@AssistedModule
@Module(includes = [AssistedInject_ViewModelAssistedFactoriesModule::class])
abstract class ViewModelAssistedFactoriesModule

interface ViewModelAssistedFactory<T : ViewModel> {
    fun create(stateHandle: SavedStateHandle): T
}

////////////////////////////////////////////////////////////////////////////////////
//
// https://proandroiddev.com/saving-ui-state-with-viewmodel-savedstate-and-dagger-f77bcaeb8b08
//
////////////////////////////////////////////////////////////////////////////////////

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@MapKey
annotation class AssistedViewModelKey(val value: KClass<out ViewModel>)

// https://satoshun.github.io/2019/05/viewmodel-savedstate-dagger/

class DaggerSavedStateViewModelFactory @Inject constructor(
    private val viewModelMap: MutableMap<Class<out ViewModel>, ViewModelAssistedFactory<out ViewModel>>,
    owner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(owner, null) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, stateHandle: SavedStateHandle): T {
        return viewModelMap[modelClass]?.create(stateHandle) as? T ?: throw IllegalStateException("Unknown ViewModel class") as Throwable
    }
}