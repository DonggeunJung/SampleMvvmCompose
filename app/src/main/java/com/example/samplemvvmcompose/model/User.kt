package com.example.samplemvvmcompose.model

/**
 * Data class representing a GitHub user profile.
 *
 * @param name The user's display name or username
 * @param location The user's location
 * @param bio A short biography of the user
 */
data class User(
    val name: String = "",
    val location: String = "",
    val bio: String = ""
)