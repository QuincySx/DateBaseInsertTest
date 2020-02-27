package generate

import kotlin.random.Random

class EmailGenerate(private val mRandom: Random = Random.Default) : IGenerate {
    companion object {
        private val email_suffix =
            "@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn"
                .split(",".toRegex())
                .toTypedArray()

        private var base = "abcdefghijklmnopqrstuvwxyz0123456789"
    }

    override fun get(): String {
        return getEmail(5, 11) ?: ""
    }

    fun getEmail(lMin: Int, lMax: Int): String? {
        val length = getNum(lMin, lMax)
        val sb = StringBuffer()
        for (i in 0 until length) {
            val number = (Math.random() * base.length).toInt()
            sb.append(base.getOrElse(number) { _ -> 'a' })
        }
        sb.append(email_suffix[(Math.random() * email_suffix.size).toInt()])
        return sb.toString()
    }
}