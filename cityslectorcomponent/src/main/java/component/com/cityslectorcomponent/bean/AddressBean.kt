package cardiac.live.com.livecardiacandroid.bean

/**
 * @author 汤洪斌
 * @time 2019/6/5 0005
 * @version 1.0
 *@describe
 */
class AddressBean(var province:String? = "",
                  var city:String? = "",
                  var distinct:String? = "") {
    fun clear() {
        province = ""
        city = ""
        distinct = ""
    }

    fun clearCityData() {
        city = ""
        distinct = ""
    }
}