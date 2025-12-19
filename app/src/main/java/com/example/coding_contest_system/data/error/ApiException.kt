package com.example.coding_contest_system.data.error

class ApiException(
    override val message: String
) : Exception(message)