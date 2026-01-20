package demo1.taskmanagement.lib.auth

// dependencies
import com.typesafe.config.ConfigFactory
import pdi.jwt.{Jwt, JwtAlgorithm, JwtClaim}
import pdi.jwt.algorithms.JwtHmacAlgorithm
import java.time.Instant
import scala.util.{Failure, Success, Try}
import com.typesafe.scalalogging.LazyLogging

// singleton
object JwtHandler extends LazyLogging {
    private val config = ConfigFactory.load()
    private val secretKey: String = config.getString("jwt.secret-key")
    private val accessTokenExpirationSeconds: Long = config.getLong("jwt.access-token-expiration-seconds")
    private val algorithm: JwtHmacAlgorithm = JwtAlgorithm.HS256

    /**
     * generate access token (short time token)
     * @param usr_id
     * @return Access token: String
     */   
    def generateAccessToken(usr_id: Long): String = {
        val claim: JwtClaim = JwtClaim(
            subject = Some(usr_id.toString),
            expiration = Some(Instant.now().plusSeconds(accessTokenExpirationSeconds).getEpochSecond)
        )

        Jwt.encode(claim, secretKey, algorithm)
    }

    /**
     * verify access token
     * @param token
     * @return result of verification: Try[JwtClaim] => Success[JwtClaim] or Failure
     */
    def verifyToken(token: String): Try[JwtClaim] = { // use Try[] monad because it's parsing String from outside
        Jwt.decode(token, secretKey, Seq(algorithm))
    }

    /**
     * get user ID from access token
     * @param token
     * @return usr_id or None
     */
    def getIdFromToken(token: String): Option[Long] = {
        val verificationResult: Option[JwtClaim] = verifyToken(token).toOption

        if (verificationResult.isEmpty) {
            logger.warn(s"Token verification failed. token: $token")
            None
        }

        val usr_idOpt: Option[Long] = for {
            jwtClaimData: JwtClaim <- verificationResult // Option[JwtClaim]
            usr_idString: String <- jwtClaimData.subject // Option[String]
            usr_id: Long <- Try(usr_idString.toLong).toOption // Option[Long]
        } yield usr_id

        if (usr_idOpt.isEmpty) {
            logger.warn(s"Invaild token subject format. token: $token")
            None
        }

        usr_idOpt

    }
}
