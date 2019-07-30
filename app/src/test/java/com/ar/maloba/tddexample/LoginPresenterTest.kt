package com.ar.maloba.tddexample

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Ezequiel Maloberti on 30/7/2019.
 */
class LoginPresenterTest {

    @Test
    fun checkIfLoginAttemptIsExceeded() {
        val loginPresenter = LoginPresenter()
        Assert.assertEquals(1, loginPresenter.newLogginAttempt())
        Assert.assertEquals(2, loginPresenter.newLogginAttempt())
        Assert.assertEquals(3, loginPresenter.newLogginAttempt())
        Assert.assertTrue( loginPresenter.isLogginAttemptExceeded())
    }

    @Test
    fun checkIfLoginAttemptIsNoExceeded() {
        val loginPresenter = LoginPresenter()
        Assert.assertFalse( loginPresenter.isLogginAttemptExceeded())
        Assert.assertEquals(1, loginPresenter.newLogginAttempt())
        Assert.assertFalse( loginPresenter.isLogginAttemptExceeded())
        Assert.assertEquals(2, loginPresenter.newLogginAttempt())
        Assert.assertFalse( loginPresenter.isLogginAttemptExceeded())
    }

    @Test
    fun checkUserAndPasswordIsCorrect() {
        val loginPresenter = LoginPresenter()
        Assert.assertTrue(loginPresenter.checkUserPassword("user", "password"))
    }

    @Test
    fun checkUserAndPasswordIsIncorrect() {
        val loginPresenter = LoginPresenter()
        Assert.assertFalse(loginPresenter.checkUserPassword("banana", "pera"))
        Assert.assertFalse(loginPresenter.checkUserPassword("user", "pera"))
        Assert.assertFalse(loginPresenter.checkUserPassword("banana", "password"))
        Assert.assertFalse(loginPresenter.checkUserPassword("user", "password"))
    }
}