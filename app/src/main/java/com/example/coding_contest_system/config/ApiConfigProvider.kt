package com.example.coding_contest_system.config

import android.content.Context

object ApiConfigProvider {
    lateinit var config: ApiConfig
        private set

    fun init(context: Context) {
        config = ApiConfigLoader(context).load()
    }
}