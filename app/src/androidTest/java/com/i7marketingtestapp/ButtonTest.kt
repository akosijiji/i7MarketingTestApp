package com.i7marketingtestapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
class ButtonTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun cleanUp() {
        Intents.release()
    }

    @Test
    fun performClickTest() {
        onView(withId(R.id.button_1)).check(ViewAssertions.matches(withText(R.string.button_1))).perform(click())
    }

    @Test
    fun performClick2Test() {
        onView(withId(R.id.button_2)).check(ViewAssertions.matches(withText(R.string.button_2))).perform(click())
    }

    @Test
    fun performClick3Test() {
        onView(withId(R.id.button_3)).check(ViewAssertions.matches(withText(R.string.button_3))).perform(click())
    }

    @Test
    fun performClick4Test() {
        onView(withId(R.id.button_4)).check(ViewAssertions.matches(withText(R.string.button_4))).perform(click())
    }
}