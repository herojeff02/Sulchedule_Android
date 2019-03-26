package com.herojeff.sulchedule.helper

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentStateHelper(val fragmentManager: androidx.fragment.app.FragmentManager) {

    private val fragmentSavedStates = mutableMapOf<String, androidx.fragment.app.Fragment.SavedState?>()

    /**
     * Restore a Fragment's previously saved state via [saveState]
     *
     * @param fragment The Fragment whose state should be restored
     * @param key A key that uniquely identifies this Fragment
     */
    fun restoreState(fragment: androidx.fragment.app.Fragment, key: String) {
        fragmentSavedStates[key]?.let { savedState ->
            // We can't set the initial saved state if the Fragment is already added
            // to a FragmentManager, since it would then already be created.
            if (!fragment.isAdded) {
                fragment.setInitialSavedState(savedState)
            }
        }
    }

    /**
     * Save a Fragment's state for later restoration via [restoreState]
     *
     * @param fragment The Fragment whose state should be restored
     * @param key A key that uniquely identifies this Fragment
     */
    fun saveState(fragment: androidx.fragment.app.Fragment, key: String) {
        // We can't save the state of a Fragment that isn't added to a FragmentManager.
        if (fragment.isAdded ?: false) {
            fragmentSavedStates[key] = fragmentManager.saveFragmentInstanceState(fragment)
        }
    }
}