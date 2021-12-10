package com.example.databinding

// import androidx.room.Entity
import com.squareup.moshi.JsonClass
import java.util.*

// @Entity(tableName = "Favorites", primaryKeys = ["id", "userId"])
data class Favorite(
        val id: Int,
        val userId: Int,
        val updatedAt: Date,
        val markedForRemoval: Boolean) {


    @JsonClass(generateAdapter = true)
    class Updates(val additions: List<Int>?, val deletions: List<Int>?, val updatedAt: Date)
}
