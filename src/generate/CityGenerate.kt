package generate

import kotlin.random.Random

class CityGenerate(private val mRandom: Random = Random.Default) : IGenerate {
    companion object {
        private val cityArray =
            arrayOf(
                "西安市",
                "延安市",
                "铜川市",
                "渭南市",
                "咸阳市",
                "宝鸡市",
                "汉中市",
                "榆林市",
                "安康市",
                "商洛市",
                "北京市",
                "上海市",
                "天津市",
                "济南市",
                "青岛市",
                "张家口市",
                "深圳市"
            )
    }

    override fun get(): String {
        return cityArray.getOrElse(mRandom.nextInt(0, cityArray.size)) {
            cityArray[0]
        }
    }
}