package br.com.jrmantovani.rideexpress.core

import android.app.AlertDialog
import android.content.Context
import android.view.View
import br.com.jrmantovani.rideexpress.R

class LoadingAlert (private val context: Context){

    private var viewLoading: View? = null
    private var alertDialog: AlertDialog? = null

    fun show(titulo: String ){

        viewLoading = View.inflate(
            context, R.layout.layout_loading_alert, null
        )
        val alertBuilder = AlertDialog.Builder( context )
            .setTitle( titulo )
            .setCancelable( false )
            .setView( viewLoading )

        alertDialog = alertBuilder.create()
        alertDialog?.show()
    }

    fun close(){
        alertDialog?.dismiss()
    }
}