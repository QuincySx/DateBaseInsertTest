package generate

import kotlin.random.Random

class HeightGenerate(private val mRandom: Random = Random.Default) : IGenerate {

    override fun get(): String {
        return getNum(150, 190).toString()
    }
}