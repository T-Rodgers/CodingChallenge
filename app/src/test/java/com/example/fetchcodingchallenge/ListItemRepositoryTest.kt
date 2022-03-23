package com.example.fetchcodingchallenge

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test


class ListItemRepositoryTest {

    private lateinit var repository: ListItemRepository
    private var itemList = listOf<ListItem>()

    @Before
    fun createRepository() {
         repository = ListItemRepository()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getListItems_returnTrue() = runBlocking {
        itemList = repository.getListItems()

        assertThat(itemList.isNotEmpty(), `is`(true))
    }
}