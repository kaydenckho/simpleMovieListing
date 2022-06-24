package com.example.simpleMovieListing.model

data class Movie(
    var uuid: String,
    var name: String,
    var openDate: Long,
    var poster: String,
    var rating: Float,
    var likeCount: Int,
    var reviewCount: Int,
    var duration: Int
)