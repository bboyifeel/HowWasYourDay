package com.lu.uni.igorzfeel.howwasyourday.models

data class UserItem (
    val username: String = "",
    val activities: List<UserActivityItem>
)