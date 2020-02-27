import generate.*
import utils.DateFormatUtil
import java.lang.Exception
import java.util.*

class SQLHelper {
    companion object {
        private val mBirthdayGenerate = BirthdayGenerate()
        private val mRoadGenerate = RoadGenerate()
        private val mUserNameGenerate = UserNameGenerate()
        private val mPhoneGenerate = PhoneGenerate()
        private val mEmailGenerate = EmailGenerate()
        private val mHeightGenerate = HeightGenerate()
        private val mWeightGenerate = WeightGenerate()
    }

    private val mDatabaseConnect by lazy {
        //        val url = "jdbc:mariadb://127.0.0.1:3306/test_db"
        val url = "jdbc:mariadb://192.168.3.175:3306/test_db"
        val user = "root"
        val password = "123123"
        DatabaseConnect.getConnect(url, user, password)
    }

    fun randomInstallUser(): Boolean {
        val birthdayDate = DateFormatUtil.parse(mBirthdayGenerate.get())
        val userNamePair = mUserNameGenerate.getPair()
        return installUser(
            User(
                0,
                userNamePair.first,
                userNamePair.second,
                mHeightGenerate.get().toInt(),
                mWeightGenerate.get().toInt(),
                mPhoneGenerate.get(),
                mEmailGenerate.get(),
                mRoadGenerate.get(),
                birthdayDate
            )
        )
    }

    fun installUser(user: User): Boolean {
        try {
            val insertUserSql =
                """INSERT INTO `user` (name,sex,height,weight,phone,email,address,birthday) VALUE('${user.name}',${user.sex},${user.height},${user.weight},'${user.phone}','${user.email}','${user.address}','${DateFormatUtil.format(
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
            `sex` int(2) NULL DEFAULT NULL,
            `height` int(3) NULL DEFAULT NULL,
            `weight` int(3) NULL DEFAULT NULL,
            `phone` varchar(13) NULL DEFAULT NULL,
            `email` varchar(50) NULL DEFAULT NULL,
            `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
            `birthday` datetime(0) NULL DEFAULT NULL,
            PRIMARY KEY (`id`) USING BTREE
            ) ENGINE = InnoDB AUTO_INCREMENT = 11000010 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;
        """
        val createStatement = mDatabaseConnect?.createStatement()
        createStatement?.execute(createUserTable)
    }
}