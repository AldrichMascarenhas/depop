package com.nerdcutlet.depop.test

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class TestTest {

    private val calendar: Calendar = mock<Calendar>()

    private lateinit var timeClass: TimeClass

    @Before
    fun before() {
        timeClass = TimeClass(
            calendar = calendar
        )
    }


    @Test
    fun testTimeFunction() {

        // Given
        whenever(calendar.timeInMillis).thenReturn(10000L)

        // When
        val time = timeClass.getCurrentTime()

        // Then
        assertEquals(time, timeClass.getCurrentTime())

    }


}


class TimeClass(
    private val calendar: Calendar
) {

    fun getCurrentTime() = calendar.timeInMillis

}