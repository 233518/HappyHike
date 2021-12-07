package com.example.filmatory.utils.items

/**
 * Data class for Lists
 *
 * @property list_name : Listname
 * @property list_author : Author
 * @property image : Images
 * @property list_total_tv : Total Tv-shows in list
 * @property list_total_movies : Total movies in list
 * @property list_id : ID
 */
data class ListItem(val list_name: String, val list_author: String, val image: String, val list_total_tv : String, val list_total_movies : String, val list_id : String)
