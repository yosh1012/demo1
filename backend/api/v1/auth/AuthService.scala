package com.taskmanagement.api.v1.auth.demo1

// dependencies
import com.typesafe.config.{ConfigFactory, Config}
import scala.concurrent.{Future, ExecutionContext}
import com.taskmanagement.lib.postgres.users.demo1.{UserRepository, UserDTO, User}
import com.taskmanagement.lib.auth.demo1.{PasswordHasher, JwtHandler, TokenResponse}

class AuthService(userRepo: UserRepository)(implicit ec: ExecutionContext) {
    private val config: Config = ConfigFactory.load()
    private val accessTokenExpirationSeconds: Long = config.getLong("auth.jwt-expiration-seconds")

    /**
     * authenticate user and issue access token
     * @param email
     * @param password
     * @return access token if it's verified
     */
    def login(email: String, password: String): Future[Option[TokenResponse]] = {
        val userFuture: Future[Option[User]] = userRepo.findByEmail(email)

        userFuture.map {
            case Some(userModel: User) =>
                val isPasswordCorrect: Boolean = PasswordHasher.verifyPassword(password, userModel.usr_hashed_password)

                if (isPasswordCorrect) {
                    val accessTokenData: String = JwtHandler.generateAccessToken(userModel.usr_id)
                    val userDTO: UserDTO = UserDTO.create(userModel)

                    // construct for case class
                    val tokenResponse = TokenResponse(
                        access_token = accessTokenData,
                        token_type = "Bearer",
                        expires_in = accessTokenExpirationSeconds,
                        user = userDTO
                    )
                    Some(tokenResponse)
                } else {
                    // when password is incorrect
                    None
                }
            // userFuture has nothing (userRepo.findByEmail(email) didn't find anything)
            case None =>
                None
        }
    }
}
