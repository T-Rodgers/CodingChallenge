package com.example.fetchcodingchallenge

class ListItemRepository {

    suspend fun getListItems() =
        FetchApi.retrofitService.getListItemsAsync().await()
            .sortedWith(compareBy(ListItem::listId, ListItem::name))
            .filter { !it.name.isNullOrBlank() }
}