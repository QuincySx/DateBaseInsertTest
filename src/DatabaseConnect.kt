import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection
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
            val ds = HikariDataSource()
            ds.jdbcUrl = url
            ds.username = user
            ds.password = password
            return ds.connection
        }
    }
}

data class User(
    val id: Long,
    val name: String,
    val sex: Int,
    val height: Int,
    val weight: Int,
    val phone: String,
    val email: String,
    val address: String,
    val birthday: Date
)