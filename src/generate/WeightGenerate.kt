package generate

import kotlin.random.Random

class WeightGenerate(private val mRandom: Random = Random.Default) : IGenerate {

    override fun get(): String {
        return getNum(45, 120).toString()
    }
}