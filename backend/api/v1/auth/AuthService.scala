package com.taskmanagement.api.v1.auth.demo1

// dependencies
import com.typesafe.config.{ConfigFactory, Config}
import scala.concurrent.{Future, ExecutionContext}
import com.taskmanagement.lib.postgres.users.demo1.{UserRepository, UserDTO, User}
import com.taskmanagement.lib.auth.demo1.{PasswordHasher, JwtHandler, TokenResponse, LoginRequest, SignupRequest, RefreshTokenRequest}

class AuthService(userRepo: UserRepository)(implicit ec: ExecutionContext) {
    private val config: Config = ConfigFactory.load()
    private val accessTokenExpirationSeconds: Long = config.getLong("auth.jwt-expiration-seconds")


    /**
     * generate token response from User Entity
     * @param userEntity
     * @return access token
     */
    def generateTokenResponse(user: User): Option[TokenResponse] = {
        try
    }

    /**
     * authenticate user and issue access token
     * @param loginRequestData
     * @return access token if it's verified
     */
    def login(loginRequestData: LoginRequest): Future[Option[TokenResponse]] = {
        val userFuture: Future[Option[User]] = userRepo.findByEmail(loginRequestData.email)

        userFuture.map {
            case Some(userModel: User) =>
                val isPasswordCorrect: Boolean = PasswordHasher.verifyPassword(loginRequestData.password, userModel.usr_hashed_password)

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

    /**
     * sign up a new user
     * @param singUpRequestData
     * @return 
     */
    def signup(singUpRequestData: SignupRequest): Future[TokenResponse] = {

    }

}
