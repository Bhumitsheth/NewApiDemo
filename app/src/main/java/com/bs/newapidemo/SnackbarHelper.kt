package com.bs.newapidemo

import android.view.View
import com.google.android.material.snackbar.Snackbar

object SnackbarHelper {
    fun showLoading(view: View, message: String = "Loading...") {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).apply {
            setAction("Dismiss") { dismiss() }
        }.show()
    }

    fun showSuccess(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

    fun showError(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).apply {
            setAction("Retry") { dismiss() }
        }.show()
    }
}
