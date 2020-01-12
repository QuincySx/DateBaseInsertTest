package generate

import utils.DateFormatUtil
import java.util.*
import kotlin.random.Random


class BirthdayGenerate(private val mRandom: Random = Random.Default) : IGenerate {

    override fun get(): String {
        return DateFormatUtil.format(Date(mRandom.nextInt(631123200, 1577808000) * 1000L))
    }
}