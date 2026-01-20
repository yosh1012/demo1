package demo1.taskmanagement.lib.auth

// dependencies
import org.apache.pekko.http.scaladsl.server.{Directives => D, Directive1, AuthenticationFailedRejection}
import org.apache.pekko.http.scaladsl.model.headers.{Authorization, HttpChallenge, OAuth2BearerToken}
import com.typesafe.scalalogging.LazyLogging

// OAuth2BearerToken is to handle OAuth2Bearer token, not handling connection with OAuth authentication servers

// singleton
object AuthHandler extends LazyLogging {
    private val bearerChallenge: HttpChallenge = HttpChallenge("Bearer", "demo1")

    /**
     * if there is usr_id, return it as Pekko Type Directive1[T]
     * @param usr_idOpt
     * @return formatted usr id: Directive1[Long]
     */
    private def usr_idVerify(usr_idOpt: Option[Long]): Directive1[Long] = {
        usr_idOpt match {
            case Some(usr_id: Long) =>
                D.provide(usr_id)
            case None =>
                D.reject(AuthenticationFailedRejection(
                    AuthenticationFailedRejection.CredentialsRejected,
                    bearerChallenge
                ))
        }
    }

    /**
     * get JWT from Authentication HTTP Header
     * @return formatted usr id: Directive1[Long]
     */
    def authenticate: Directive1[Long] = {
        D.optionalHeaderValueByType(classOf[Authorization]).flatMap {
            case Some(Authorization(OAuth2BearerToken(token))) =>
                val usr_idOpt: Option[Long] = JwtHandler.getIdFromToken(token)
                usr_idVerify(usr_idOpt)
            
            case Some(_) =>
                D.reject(AuthenticationFailedRejection(
                    AuthenticationFailedRejection.CredentialsRejected,
                    bearerChallenge
                ))
            
            case None =>
                D.reject(AuthenticationFailedRejection(
                    AuthenticationFailedRejection.CredentialsMissing,
                    bearerChallenge
                ))
        }
    }
}