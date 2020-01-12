import generate.AgeGenerate
import generate.BirthdayGenerate
import generate.CityGenerate
import generate.UserNameGenerate
import utils.DateFormatUtil
import java.lang.Exception
import java.util.*

class SQLHelper {
    companion object {
        private val mAgeGenerate = AgeGenerate()
        private val mBirthdayGenerate = BirthdayGenerate()
        private val mCityGenerate = CityGenerate()
        private val mUserNameGenerate = UserNameGenerate()
    }

    private val mDatabaseConnect by lazy {
        val url = "jdbc:mariadb://192.168.3.175:3306/test_db"
        val user = "root"
        val password = "123123"
        DatabaseConnect.getConnect(url, user, password)
    }

    fun randomInstallUser(): Boolean {
        val birthdayDate = DateFormatUtil.parse(mBirthdayGenerate.get())
        return installUser(
            User(
                0,
                mUserNameGenerate.get(),
                mAgeGenerate.getAge(birthdayDate),
                mCityGenerate.get(),
                birthdayDate
            )
        )
    }

    fun installUser(user: User): Boolean {
        try {
            val insertUserSql =
                """INSERT INTO `user`  (name,age,address,birthday) VALUE('${user.name}',${user.age},'${user.address}','${DateFormatUtil.format(
                    Date(user.birthday.time)
                )}')"""
            mDatabaseConnect?.createStatement()?.let {
                it.execute(insertUserSql)
                return true
            }
            return false
        } catch (e: Exception) {
            return false
        }
    }

    fun createUserTable() {
        val createUserTable = """
            CREATE TABLE IF NOT EXISTS `user`  (
            `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键自增非空',
            `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
            `age` int(3) NULL DEFAULT NULL,
            `address` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
            `birthday` datetime(0) NULL DEFAULT NULL,
            PRIMARY KEY (`id`) USING BTREE
            ) ENGINE = InnoDB AUTO_INCREMENT = 11000010 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;
        """
        val createStatement = mDatabaseConnect?.createStatement()
        createStatement?.execute(createUserTable)
    }
}