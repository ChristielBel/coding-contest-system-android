package com.example.coding_contest_system.config

import android.content.Context
import com.example.coding_contest_system.R
import com.google.gson.Gson

class ApiConfigLoader(
    private val context: Context,
    private val gson: Gson = Gson()
) {

    fun load(): ApiConfig {
        return loadFromAssets()
            ?: loadFromRaw()
            ?: default()
    }

    private fun loadFromAssets(): ApiConfig? =
        runCatching {
            context.assets.open("api_config.json")
                .bufferedReader()
                .use { gson.fromJson(it, ApiConfig::class.java) }
        }.getOrNull()

    private fun loadFromRaw(): ApiConfig? =
        runCatching {
            context.resources.openRawResource(R.raw.api_config_default)
                .bufferedReader()
                .use { gson.fromJson(it, ApiConfig::class.java) }
        }.getOrNull()

    private fun default() = ApiConfig(
        authUrl = "http://10.0.2.2:8081/api/v1/auth/",
        managerUrl = "http://10.0.2.2:8080/api/v1/"
    )
}