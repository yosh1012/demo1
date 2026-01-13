package com.taskmanagement.lib.hashids.demo1

// dependencies
import com.typesafe.config.ConfigFactory
import org.hashids.Hashids
import com.typesafe.scalalogging.LazyLogging

// singleton
object HashidsHandler extends LazyLogging {
    private val config = ConfigFactory.load()
    private val salt: String = config.getString("hashids.salt")
    private val minHashLength: Int = config.getInt("hashids.min-hash-length")

    private val hashids = new Hashids(salt, minHashLength)

    /**
     * encoding primaryKey to a String by using salt
     * @param primaryKey PK of each table
     * @return hashed text: String 
     */
    def encode(primaryKey: Long): String = {
        hashids.encode(primaryKey)
    }

    /**
     * decoding encoded String to PK by using salt
     * @param encodedString
     * @return PK number or None: Option[Long]
     */
    def decode(encodedString: String): Option[Long] = {
        // hashids returns Array
        val decodedArray: Array[Long] = hashids.decode(encodedString)

        if (decodedArray.length > 0) {
            // return the first element of decodedArray
            Some(decodedArray(0))
        } else {
            logger.warn(s"Failed to decode Hashids, invalid format. encodedString: $encodedString")
            None
        }
    }
}