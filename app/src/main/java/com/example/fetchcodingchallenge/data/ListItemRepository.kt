package com.example.fetchcodingchallenge.data

import com.example.fetchcodingchallenge.FetchApi

class ListItemRepository {

    /**
     * Retrieves our items from our API first groups and sorts them by listID then name
     * Also removes all values where name value is null or ""
     */
    suspend fun getListItems() =
        FetchApi.retrofitService.getListItemsAsync().await()
            .sortedWith(compareBy(ListItem::listId, ListItem::name))
            .filter { !it.name.isNullOrBlank() }
}