package com.ar.maloba.tddexample

import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.junit.Before
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


/**
 * Created by Ezequiel Maloberti on 30/7/2019.
 */
class LoginPresenterTest {

    private lateinit var loginView: LoginView

    @Before
    @Throws(Exception::class)
    fun setUp() {
        loginView = mock(LoginView::class.java)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {

    }
    @Test
    fun checkIfLoginAttemptIsExceeded() {
        val loginPresenter = LoginPresenter(loginView)
        Assert.assertEquals(1, loginPresenter.newLogginAttempt())
        Assert.assertEquals(2, loginPresenter.newLogginAttempt())
        Assert.assertEquals(3, loginPresenter.newLogginAttempt())
        Assert.assertTrue( loginPresenter.isLogginAttemptExceeded())
    }

    @Test
    fun checkIfLoginAttemptIsNoExceeded() {
        val loginPresenter = LoginPresenter(loginView)
        Assert.assertFalse( loginPresenter.isLogginAttemptExceeded())
        Assert.assertEquals(1, loginPresenter.newLogginAttempt())
        Assert.assertFalse( loginPresenter.isLogginAttemptExceeded())
        Assert.assertEquals(2, loginPresenter.newLogginAttempt())
        Assert.assertFalse( loginPresenter.isLogginAttemptExceeded())
    }

    @Test
    fun checkUserAndPasswordIsCorrect() {
        val loginPresenter = LoginPresenter(loginView)
        Assert.assertTrue(loginPresenter.checkUserPassword("user", "password"))
        verify(loginView).showLoginSuccessMessage()
    }

    @Test
    fun checkUserAndPasswordIsNotCorrect() {
        val loginPresenter = LoginPresenter(loginView)
        loginPresenter.checkUserPassword("user1", "password1")
        verify(loginView).showErrorMessageForUserPassword()
    }

    @Test
    fun checkIfLoginAttemptIsExceededWithMessage() {
        val loginPresenter = LoginPresenter(loginView)
        Assert.assertFalse(loginPresenter.checkUserPassword("banana", "pera"))
        Assert.assertFalse(loginPresenter.checkUserPassword("user", "pera"))
        Assert.assertFalse(loginPresenter.checkUserPassword("banana", "password"))
        Assert.assertFalse(loginPresenter.checkUserPassword("user", "password"))
        verify(loginView).showErrorMessageForMaxLoginAttempt()
    }
}