package com.taskmanagement.lib.auth.demo1

// dependencies
import org.mindrot.jbcrypt.BCrypt

// singleton
object PasswordHasher {
    private val BCryptRounds: Int = 10

    /**
     * hash plain text password
     * @param plainPassword
     * @return hashed text: String
     */
    def hashPassword(plainPassword: String): String = {
        BCrypt.hashpw(plainPassword, BCrypt.gensalt(BCryptRounds))
    }

    /**
     * verify hashed password
     * @param plainPassword
     * @param hashedPassword
     * @return verification result: Boolean => true or false
     */
    def verifyPassword(plainPassword: String, hashedPassword: String): Boolean = {
        BCrypt.checkpw(plainPassword, hashedPassword)
    }
}