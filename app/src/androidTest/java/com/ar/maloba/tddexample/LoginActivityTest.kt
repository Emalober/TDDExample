package com.ar.maloba.tddexample

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.rule.ActivityTestRule

import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.runner.AndroidJUnit4
import androidx.test.espresso.matcher.ViewMatchers.*


/**
 * Created by Ezequiel Maloberti on 30/7/2019.
 */
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    private val activityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun checkUserNameEditTextIsDisplayed()
    {
        activityTestRule.launchActivity(Intent());
        onView(withId(R.id.txt_user_name)).check(matches(isDisplayed()))
    }

    @Test
    fun checkPasswordEditTextIsDisplayed() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.txt_password)).check(matches(isDisplayed()))
    }

    @Test
    fun checkErrorMessageIsDisplayedForEmptyData() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.btn_login)).check(matches(isDisplayed())).perform(click())
        onView(withText(R.string.error_user_password)).check(matches(isDisplayed()))
    }

    @Test
    fun checkLoginSuccess() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.txt_user_name)).perform(typeText("user"), closeSoftKeyboard())
        onView(withId(R.id.txt_password)).perform(typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.btn_login)).check(matches(isDisplayed())).perform(click())
        onView(withText(R.string.login_ok)).check(matches(isDisplayed()))

    }

}