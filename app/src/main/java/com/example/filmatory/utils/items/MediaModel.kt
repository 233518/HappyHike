package com.example.filmatory.utils.items

/**
 * Data class for movies and tv-show items
 *
 * @property viewType : ViewType
 * @property itemTitle : Title
 * @property itemDate : Date
 * @property itemImage : Imagepath
 * @property itemId : ID
 */
data class MediaModel(val viewType : Int, val itemTitle : String, val itemDate : String, val itemImage : String, val itemId : Int)
