package com.fevziomurtekin.kotlin_coroutines_example.Model.User

data class Plan(
    val collaborators: Int,
    val name: String,
    val private_repos: Int,
    val space: Int
)