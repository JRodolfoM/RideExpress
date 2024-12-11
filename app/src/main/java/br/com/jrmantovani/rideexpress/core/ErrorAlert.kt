package br.com.jrmantovani.rideexpress.core

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import br.com.jrmantovani.rideexpress.R

class ErrorAlert(private val context: Context) {

    private var viewAlert: View? = null
    private var alertDialog: AlertDialog? = null

    fun show(titulo: String, mensagem: String) {

        viewAlert = View.inflate(context, R.layout.layout_alert_error, null)


        viewAlert?.let { view ->
            // Configura os textos
            val textTitle = view.findViewById<TextView>(R.id.textErrorTitle)
            val textMessage = view.findViewById<TextView>(R.id.textErrorMessage)
            val btnClose = view.findViewById<Button>(R.id.btnClose)

            textTitle.text = titulo
            textMessage.text = mensagem


            btnClose.setOnClickListener {
                close()
            }


            val alertBuilder = AlertDialog.Builder(context)
                .setCancelable(false)
                .setView(view)

            alertDialog = alertBuilder.create()
            alertDialog?.show()
        }
    }

    fun close() {
        alertDialog?.dismiss()
    }
}