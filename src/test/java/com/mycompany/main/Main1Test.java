package com.mycompany.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the Main1 registration and login features.
 */
public class Main1Test {

    // Reset the stored registration details before each test so that
    // one test can never affect another one.
    @BeforeEach
    public void setUp() {
        Main1.registeredUsername = null;
        Main1.registeredPassword = null;
        Main1.registeredCellPhone = null;
    }

    // ---------- checkUserName ----------

    @Test
    @DisplayName("Username is correct: 5 characters and contains an underscore")
    public void testUsernameCorrectlyFormatted() {
        assertTrue(Main1.checkUserName("kyl_1"));
        assertTrue(Main1.checkUserName("_abcd"));
        assertTrue(Main1.checkUserName("abcd_"));
    }

    @Test
    @DisplayName("Username is incorrect: too long")
    public void testUsernameTooLong() {
        assertFalse(Main1.checkUserName("kyle_1"));
    }

    @Test
    @DisplayName("Username is incorrect: too short")
    public void testUsernameTooShort() {
        assertFalse(Main1.checkUserName("ky_1"));
    }

    @Test
    @DisplayName("Username is incorrect: no underscore")
    public void testUsernameNoUnderscore() {
        assertFalse(Main1.checkUserName("kyle1"));
    }

    // ---------- checkPasswordComplexity ----------

    @Test
    @DisplayName("Password is correct: meets all four complexity rules")
    public void testPasswordCorrectlyFormatted() {
        assertTrue(Main1.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertTrue(Main1.checkPasswordComplexity("Passw0rd!"));
    }

    @Test
    @DisplayName("Password is incorrect: fewer than 8 characters")
    public void testPasswordTooShort() {
        assertFalse(Main1.checkPasswordComplexity("Pa1!"));
    }

    @Test
    @DisplayName("Password is incorrect: no capital letter")
    public void testPasswordNoCapitalLetter() {
        assertFalse(Main1.checkPasswordComplexity("password1!"));
    }

    @Test
    @DisplayName("Password is incorrect: no lowercase letter")
    public void testPasswordNoLowerCaseLetter() {
        assertFalse(Main1.checkPasswordComplexity("PASSWORD1!"));
    }

    @Test
    @DisplayName("Password is incorrect: no number")
    public void testPasswordNoNumber() {
        assertFalse(Main1.checkPasswordComplexity("Password!"));
    }

    @Test
    @DisplayName("Password is incorrect: no special character")
    public void testPasswordNoSpecialCharacter() {
        assertFalse(Main1.checkPasswordComplexity("Password1"));
    }

    // ---------- checkCellPhoneNumber ----------

    @Test
    @DisplayName("Cellphone number is correct: +27 followed by 9 digits")
    public void testCellPhoneCorrectlyFormatted() {
        assertTrue(Main1.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    @DisplayName("Cellphone number is incorrect: missing the +27 code")
    public void testCellPhoneMissingInternationalCode() {
        assertFalse(Main1.checkCellPhoneNumber("0838968976"));
    }

    @Test
    @DisplayName("Cellphone number is incorrect: too many digits")
    public void testCellPhoneTooManyDigits() {
        assertFalse(Main1.checkCellPhoneNumber("+278389689769"));
    }

    @Test
    @DisplayName("Cellphone number is incorrect: too few digits")
    public void testCellPhoneTooFewDigits() {
        assertFalse(Main1.checkCellPhoneNumber("+2783896"));
    }

    @Test
    @DisplayName("Cellphone number is incorrect: contains letters")
    public void testCellPhoneContainsLetters() {
        assertFalse(Main1.checkCellPhoneNumber("+27abc968976"));
    }

    // ---------- loginUser ----------

    @Test
    @DisplayName("Login succeeds when both details match the registered details")
    public void testLoginSuccessful() {
        Main1.registeredUsername = "kyl_1";
        Main1.registeredPassword = "Ch&&sec@ke99!";

        assertTrue(Main1.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    @DisplayName("Login fails when the username is wrong")
    public void testLoginWrongUsername() {
        Main1.registeredUsername = "kyl_1";
        Main1.registeredPassword = "Ch&&sec@ke99!";

        assertFalse(Main1.loginUser("kyl_2", "Ch&&sec@ke99!"));
    }

    @Test
    @DisplayName("Login fails when the password is wrong")
    public void testLoginWrongPassword() {
        Main1.registeredUsername = "kyl_1";
        Main1.registeredPassword = "Ch&&sec@ke99!";

        assertFalse(Main1.loginUser("kyl_1", "WrongPass1!"));
    }

    @Test
    @DisplayName("Login fails when both details are wrong")
    public void testLoginBothDetailsWrong() {
        Main1.registeredUsername = "kyl_1";
        Main1.registeredPassword = "Ch&&sec@ke99!";

        assertFalse(Main1.loginUser("abc_2", "WrongPass1!"));
    }

    // ---------- returnLoginStatus ----------

    @Test
    @DisplayName("Returns the welcome message after a successful login")
    public void testReturnLoginStatusSuccessful() {
        assertEquals("Welcome, it is great to see you again.",
                Main1.returnLoginStatus(true));
    }

    @Test
    @DisplayName("Returns the error message after a failed login")
    public void testReturnLoginStatusFailed() {
        assertEquals("Username or password incorrect, please try again.",
                Main1.returnLoginStatus(false));
    }
}
