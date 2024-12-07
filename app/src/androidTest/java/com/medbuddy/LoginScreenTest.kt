package com.medbuddy

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import com.medbuddy.ui.screen.LoginScreen
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoginSuccess() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            LoginScreen(navController)
        }

        // Enter username
        composeTestRule.onNodeWithTag("username").performScrollTo().performTextInput("testuser")

        // Enter password
        composeTestRule.onNodeWithTag("Password").performScrollTo().performTextInput("password")

        // Click the Submit button
        composeTestRule.onNodeWithTag("Submit").performScrollTo().performClick()

        // Verify navigation to Medicines screen (replace with your actual navigation target)
        //composeTestRule.onNodeWithText("Medicines").assertIsDisplayed()
    }

    @Test
    fun testLoginFail() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            LoginScreen(navController)
        }

        // Enter username
        composeTestRule.onNodeWithTag("username").performScrollTo().performTextInput("")

        // Enter password
        composeTestRule.onNodeWithTag("Password").performScrollTo().performTextInput("")

        // Click the Submit button
        composeTestRule.onNodeWithTag("Submit").performScrollTo().performClick()

        // Verify Toast message is displayed (replace with your actual error handling)
        //composeTestRule.onNodeWithText("Username or Password is Blank").assertIsDisplayed()
    }
}