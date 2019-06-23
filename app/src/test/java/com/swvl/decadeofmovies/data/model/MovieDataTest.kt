package com.swvl.decadeofmovies.data.model

import android.content.Context
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MovieDataTest {

    @Mock
    lateinit var context: Context

    val json = "{\n" +
            "    \"movies\": [\n" +
            "        {\n" +
            "            \"title\": \"(500) Days of Summer\",\n" +
            "            \"year\": 2009,\n" +
            "            \"cast\": [\n" +
            "                \"Joseph Gordon-Levitt\",\n" +
            "                \"Zooey Deschanel\"\n" +
            "            ],\n" +
            "            \"genres\": [\n" +
            "                \"Romance\",\n" +
            "                \"Comedy\"\n" +
            "            ],\n" +
            "            \"rating\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"title\": \"12 Rounds\",\n" +
            "            \"year\": 2009,\n" +
            "            \"cast\": [\n" +
            "                \"John Cena\",\n" +
            "                \"Ashley Scott\",\n" +
            "                \"Steve Harris\",\n" +
            "                \"Aidan Gillen\",\n" +
            "                \"Brian J. White\",\n" +
            "                \"Taylor Cole\"\n" +
            "            ],\n" +
            "            \"genres\": [\n" +
            "                \"Action\"\n" +
            "            ],\n" +
            "            \"rating\": 1\n" +
            "        }\n" +
            "    ]\n" +
            "}"


    @Before
    fun setUp() {
        context = mock(Context::class.java)
    }

    @Test
    fun parseMovies() {

        val result = MovieData.parseMovies(json)
        Assert.assertTrue(result.isNotEmpty())
    }
}