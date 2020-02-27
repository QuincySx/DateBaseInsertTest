package generate

import kotlin.random.Random

class PhoneGenerate(private val mRandom: Random = Random.Default) : IGenerate {
    companion object {
        private val telFirst =
            "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153,187,189,170,175"
                .split(",".toRegex())
                .toTypedArray()
    }

    override fun get(): String {
        val index: Int = getNum(0, telFirst.size - 1)
        val first: String = telFirst.getOrElse(index) { _ -> "138" }
        val second: String = java.lang.String.valueOf(getNum(1, 888) + 10000).substring(1)
        val thrid: String = java.lang.String.valueOf(getNum(1, 9100) + 10000).substring(1)
        return first + second + thrid
    }
}