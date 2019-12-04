package com.example.battery_broadcast

import android.content.Context
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    var myReceiver:MyReceiver=MyReceiver()
    var intentFilter:IntentFilter= IntentFilter()


    lateinit var wifiManager:WifiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        wifiManager=applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    }


    override fun onStart() {
        super.onStart()

        intentFilter = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(myReceiver, intentFilter)
    }

    override fun onResume() {
        super.onResume()


        intentFilter = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")


        registerReceiver(myReceiver,intentFilter)
       // registerReceiver(wifiState)

    }


    override fun onStop() {
        super.onStop()
        unregisterReceiver(myReceiver)
    }
}
