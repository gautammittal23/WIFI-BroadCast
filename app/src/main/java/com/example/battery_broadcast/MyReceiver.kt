package com.example.battery_broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.wifi.WifiManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MyReceiver : BroadcastReceiver() {

     var wifiManager:WifiManager?=null

    override fun onReceive(context: Context, intent: Intent) {


        val action = intent.action
        val wifiStateExtra = intent.getIntExtra(
            WifiManager.EXTRA_WIFI_STATE,
            WifiManager.WIFI_STATE_UNKNOWN
        )

        when (wifiStateExtra) {
            WifiManager.WIFI_STATE_ENABLED -> {
                Toast.makeText(context,"Wifi ON",Toast.LENGTH_LONG).show()
            }
            WifiManager.WIFI_STATE_DISABLED -> {
                Toast.makeText(context,"This Application is required High Data Usage Kindly please Enable Your Wifi",Toast.LENGTH_LONG).show()


                val dialogBuilder = AlertDialog.Builder(context)
                dialogBuilder.setMessage("Do you want to Enable the Wifi ?")
                    .setCancelable(true)
                    .setPositiveButton("Proceed", DialogInterface.OnClickListener {

                            dialog, id -> wifiManager?.setWifiEnabled(true)
                        //dialog,wifiManager.setWifiEnabled(true)

                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                            dialog, id -> dialog.cancel()
                    })

                val alert = dialogBuilder.create()
                alert.setTitle("Enable WIFI")

                alert.show()

            }
        }


        if ("android.net.conn.CONNECTIVITY_CHANGE" == action)
        {
//            Toast.makeText(context, "WIFI State Changed", Toast.LENGTH_LONG).show()
        }

    }
}