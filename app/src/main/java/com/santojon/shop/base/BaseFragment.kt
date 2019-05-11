package com.santojon.shop.base

import android.os.Bundle
import android.support.v4.app.Fragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.closestKodein

/**
 * Base Fragments for all needed ones
 */
abstract class BaseFragment : Fragment(), KodeinAware {
    // Dependency injection
    override val kodein: Kodein by closestKodein()
    override val kodeinTrigger = KodeinTrigger()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        kodeinTrigger.trigger()
    }
}