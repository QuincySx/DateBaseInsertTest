import generate.PhoneGenerate
import java.util.concurrent.Executors


private val mSQLHelperThreadLocal: ThreadLocal<SQLHelper> = object : ThreadLocal<SQLHelper>() {
    override fun initialValue(): SQLHelper {
        return SQLHelper()
    }
}

private val mSQLHelper by lazy {
    SQLHelper()
}

private val mExecutorPool = Executors.newFixedThreadPool(16)

fun main() {
    mSQLHelper.createUserTable()
    for (i in 0 until 1500000) {
        mExecutorPool.execute {
            mSQLHelperThreadLocal.get()
                .randomInstallUser()
            if (i % 1000 == 0) {
                println("success $i")
            }
        }
    }
}
