import java.util.concurrent.Executors
import kotlin.random.Random


private val mSQLHelper by lazy {
    SQLHelper()
}

private val mExecutorPool = Executors.newFixedThreadPool(32)

fun main() {
    mSQLHelper.createUserTable()
    for (i in 0 until 1000000) {
        mExecutorPool.execute {
            if (mSQLHelper.randomInstallUser()) {
                println("success $i")
            } else {
                println("error $i")
            }
        }
    }
}
