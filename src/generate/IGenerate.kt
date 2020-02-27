package generate

import kotlin.random.Random

interface IGenerate {
    fun get(): String

    fun getRandom(): Random.Default {
        return Random.Default
    }

    fun getNum(start: Int, end: Int): Int {
        return (getRandom().nextInt(end - start + 1) + start)
    }
}