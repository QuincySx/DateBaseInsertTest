import java.sql.Connection
import java.sql.DriverManager
import java.util.*

class DatabaseConnect {
    init {
        Class.forName("org.mariadb.jdbc.Driver")
    }

    companion object {
        fun getConnect(
            url: String,
            user: String,
            password: String
        ): Connection? {
            return DriverManager.getConnection(
                url,
                user,
                password
            );
        }
    }
}

data class User(
    val id: Long,
    val name: String,
    val age: Int,
    val address: String,
    val birthday: Date
)