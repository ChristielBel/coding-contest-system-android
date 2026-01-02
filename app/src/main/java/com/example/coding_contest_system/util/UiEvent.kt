package com.example.coding_contest_system.util

sealed class UiEvent {
    data class ShowMessage(val message: String) : UiEvent()
}