package com.example.irgiuts

import com.google.gson.annotations.SerializedName

data class GithubRes(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<UsersItem>
)

data class UsersItem (

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("bio")
    val bio: String
)