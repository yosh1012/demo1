package demo1.taskmanagement.api.v1.auth

// dependencies
import com.typesafe.config.{ConfigFactory, Config}
import com.typesafe.scalalogging.LazyLogging
import scala.concurrent.{Future, ExecutionContext}

import demo1.taskmanagement.lib.postgres.users.{UserRepository, UserDTO, User}
import demo1.taskmanagement.lib.auth.{PasswordHasher, JwtHandler, TokenResponse, LoginRequest, SignupRequest, RefreshTokenRequest}

class AuthService(userRepo: UserRepository)(implicit ec: ExecutionContext) extends LazyLogging {
    private val config: Config = ConfigFactory.load()
    private val accessTokenExpirationSeconds: Long = config.getLong("jwt.access-token-expiration-seconds")

    /**
     * generate token response from User Entity
     * @param userModel
     * @return access token
     */
    def createTokenResponse(userModel: User): TokenResponse = {
        val accessTokenData: String = JwtHandler.generateAccessToken(userModel.usr_id)
        val userDTO: UserDTO = UserDTO.createDTO(userModel)

        TokenResponse(
            access_token = accessTokenData,
            refresh_token = "", // todo
            token_type = "Bearer",
            expires_in = accessTokenExpirationSeconds,
            user = userDTO
        )
    }

    /**
     * authenticate user and issue access token
     * @param loginRequestData
     * @return access token if it's verified
     */
    def login(loginRequestData: LoginRequest): Future[TokenResponse] = {
        logger.info(s"Login attempt for email: ${loginRequestData.email}")
        userRepo.findByEmail(loginRequestData.email).flatMap { (foundUserOpt: Option[User]) =>
            foundUserOpt match {
                case Some(foundUser: User) =>
                    val isPasswordCorrect: Boolean = PasswordHasher.verifyPassword(loginRequestData.password, foundUser.usr_hashed_password)

                    if(isPasswordCorrect) {
                        logger.info(s"login successful: usr_id=${foundUser.usr_id}")
                        Future.successful(createTokenResponse(foundUser))
                    } else {
                        logger.warn(s"login failed: Invalid credentials for ${loginRequestData.email}")
                        Future.failed(new IllegalArgumentException("Invalid email or password")) // intentionally return the same messages
                    }
                case None =>
                    logger.warn(s"login failed: Invalid credentials for ${loginRequestData.email}")
                    Future.failed(new IllegalArgumentException("Invalid email or password")) // intentionally return the same messages
            }

        }
    }

    /**
     * sign up a new user
     * @param signupRequestData
     * @return access token if signup succeeds
     */
    def signup(signupRequestData: SignupRequest): Future[TokenResponse] = {
        logger.info(s"Signup attempt for email: ${signupRequestData.email}")

        userRepo.findByEmail(signupRequestData.email).flatMap { (foundUserOpt: Option[User]) =>

            foundUserOpt match {
                // if user already exists, return Future.failed()
                case Some(foundUser: User) =>
                    logger.warn(s"Signup failed: Email already exists. ${signupRequestData.email}")
                    Future.failed(new IllegalArgumentException("Email already registered"))

                case None =>
                    val hashedPassword: String = PasswordHasher.hashPassword(signupRequestData.password)
                    val userModel: User = SignupRequest.createModel(signupRequestData, hashedPassword)
                    userRepo.create(userModel).map { (createdUser: User) =>
                        logger.info(s"Signup successful: usr_id=${createdUser.usr_id}")
                        createTokenResponse(createdUser)
                    }              
            }
        }
    }

}
