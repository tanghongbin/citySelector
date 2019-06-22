package cardiac.live.com.livecardiacandroid.view.dialog

import android.app.Dialog
import android.content.Context
import android.net.MacAddress
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.View
import cardiac.live.com.livecardiacandroid.bean.AddressBean
import component.com.cityslectorcomponent.R
import component.com.cityslectorcomponent.bean.LocationCity
import component.com.cityslectorcomponent.bean.LocationDistinct
import component.com.cityslectorcomponent.bean.LocationProvince
import component.com.cityslectorcomponent.view.CustomWheelView
import org.litepal.LitePal
import org.litepal.extension.find
import java.util.*

/**
 * @author 汤洪斌
 * @version 1.0
 * @time 2019/3/13 0013
 * @describe
 */
class AddressSelectorDialog(mContext: Context) : Dialog(mContext, R.style.CustomDialogTheme) {


    private var mSelectPostion: Int = 0

    var onConfirmListener: ((AddressBean?) -> Unit)? = null

    var mAddrssBean = AddressBean()

    var mHandler = Handler(Looper.getMainLooper())

    var mProvinceWheel:CustomWheelView? = null
    var mCityWheel:CustomWheelView? = null
    var mDistinctWheel:CustomWheelView? = null


    init {
        window!!.setWindowAnimations(R.style.DialogTranslateAnimation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(View.inflate(context,R.layout.address_selector,null))
        findViews()

        findViewById<View>(R.id.mCancel).setOnClickListener { dismiss() }
        findViewById<View>(R.id.mConfirm).setOnClickListener {
            if (onConfirmListener != null) {
                onConfirmListener?.invoke(mAddrssBean)
            }
            dismiss()
        }


        var provinceList = queryProvince()
        var provinceStr = provinceList.map {
            it.name
        }

        if (TextUtils.isEmpty(mAddrssBean.province)
                && provinceList != null
                && !provinceList.isEmpty()) {
            //说明是新的选择
            mAddrssBean.province = provinceList[0].name
            var cityList = queryCityByProvince(provinceList[0].adcode)
            if (cityList != null && !cityList.isEmpty()) {
                mAddrssBean.city = cityList[0].name
                var distinctList = queryDistinctByCity(cityList[0].adcode)
                if (distinctList != null && !distinctList.isEmpty()) {
                    mAddrssBean.distinct = distinctList[0].name
                }
            }
        }

        mProvinceWheel!!!!.setData(ArrayList(provinceStr))

        // 初始化显示
        if (!TextUtils.isEmpty(mAddrssBean.province)) {
            var provinceItem = provinceList.find {
                it.name == mAddrssBean.province
            }
            var provinceIndex = provinceList.indexOf(provinceItem)
            provinceIndex = if (provinceIndex == -1) 0 else provinceIndex
            mProvinceWheel!!.setDefault(provinceIndex)

            // 设置城市
            if (provinceItem != null && !TextUtils.isEmpty(mAddrssBean.city)) {
                var cityList = queryCityByProvince(provinceItem?.adcode)
                var cityItem = cityList.find {
                    it.name == mAddrssBean.city
                }
                var cityStr = cityList?.map {
                    it.name
                }
                var cityIndex = cityList.indexOf(cityItem)
                cityIndex = if (cityIndex == -1) 0 else cityIndex
                if (cityStr != null && !cityStr.isEmpty()) {
                    mCityWheel!!.setData(ArrayList((cityStr)))
                    mCityWheel!!.setDefault(cityIndex)
                    mCityWheel!!.setOnSelectListener(object : CustomWheelView.OnSelectListener {
                        override fun selecting(id: Int, text: String?) {

                        }

                        override fun endSelect(index: Int, text: String?) {
                            mHandler.post {
                                mAddrssBean.clearCityData()
                                mAddrssBean.city = text
                                setupDistinctWheel(cityList, index)
                            }

                        }
                    })
                }


                // 设置县市
                if (cityItem != null && !TextUtils.isEmpty(mAddrssBean.distinct)) {
                    var distinctList = queryDistinctByCity(cityItem?.adcode)
                    var distinctItem = distinctList?.find {
                        it.name == mAddrssBean.distinct
                    }
                    var distinctStr = distinctList?.map {
                        it.name
                    }
                    var distinctIndex = distinctList?.indexOf(distinctItem)
                    distinctIndex = if (distinctIndex == -1) 0 else distinctIndex
                    if (distinctStr != null && !distinctStr.isEmpty()) {
                        mDistinctWheel!!.setData(ArrayList(distinctStr))
                        mDistinctWheel!!.setDefault(distinctIndex ?: 0)
                        mDistinctWheel!!!!.setOnSelectListener(object : CustomWheelView.OnSelectListener {
                            override fun selecting(id: Int, text: String?) {

                            }

                            override fun endSelect(index: Int, text: String?) {
                                mAddrssBean.distinct = text
                            }
                        })
                    }

                }

            }


        }



        initWheel(provinceList)

    }

    fun initData(addressBean: AddressBean){
        mAddrssBean = addressBean
    }

    private fun findViews() {
        mProvinceWheel = findViewById<CustomWheelView>(R.id.mProvinceWheel!!)
        mCityWheel = findViewById<CustomWheelView>(R.id.mCityWheel!!)
        mDistinctWheel = findViewById<CustomWheelView>(R.id.mDistinctWheel!!)
    }


    /**
    @author 汤洪斌
    @time 2019/6/5 0005 21:07
    @version 1.0
    @describe 显示城市
     */
    private fun initWheel(provinceList: List<LocationProvince>) {
        mProvinceWheel!!.setOnSelectListener(object : CustomWheelView.OnSelectListener {
            override fun selecting(id: Int, text: String?) {
            }

            override fun endSelect(index: Int, text: String?) {
                mHandler.post {
                    mAddrssBean.clear()
                    mAddrssBean.province = text
                    setupCityWheel(provinceList, index)
                }
            }
        })


    }

    private fun setupCityWheel(
        provinceList: List<LocationProvince>,
        index: Int
    ) {
        var cityList = queryCityByProvince(provinceList[index].adcode)
        var cityStr = cityList.map {
            it.name
        }
        mCityWheel!!.setOnSelectListener(object : CustomWheelView.OnSelectListener {
            override fun selecting(id: Int, text: String?) {

            }

            override fun endSelect(index: Int, text: String?) {
                mHandler.post {
                    mAddrssBean.clearCityData()
                    mAddrssBean.city = text
                    setupDistinctWheel(cityList, index)
                }

            }
        })
        mCityWheel!!.resetData(ArrayList(cityStr))
        if (cityList != null && !cityList.isEmpty()) {
            mCityWheel!!.setDefault(0)
            mAddrssBean.city = cityList[0].name

            var distinctList = queryDistinctByCity(cityList[0].adcode)
            var distinctStr = distinctList?.map {
                it.name
            }
            mDistinctWheel!!!!.resetData(ArrayList(distinctStr))
            if (distinctStr != null && !distinctStr.isEmpty()) {
                mDistinctWheel!!.setDefault(0)
                mAddrssBean.distinct = distinctList!![0].name
            }
        } else {
            mDistinctWheel!!!!.resetData(ArrayList())
        }


    }

    private fun setupDistinctWheel(
            cityList: List<LocationCity>,
            index: Int
    ) {


        var distinctList = queryDistinctByCity(cityList[index].adcode)
        var distinctStr = distinctList?.map {
            it.name
        }
        mDistinctWheel!!!!.resetData(ArrayList(distinctStr))
        if (distinctStr != null && !distinctStr.isEmpty()) {
            mDistinctWheel!!.setDefault(0)
            mAddrssBean.distinct = distinctStr!![0]
        }

        mDistinctWheel!!!!.setOnSelectListener(object : CustomWheelView.OnSelectListener {
            override fun selecting(id: Int, text: String?) {

            }

            override fun endSelect(index: Int, text: String?) {
                mAddrssBean.distinct = text
            }
        })


    }

    fun queryProvince(): List<LocationProvince> {
        var provinceList = LitePal.findAll(LocationProvince::class.java)
        logd("查询到省市数据")
        return provinceList
    }


    fun queryCityByProvince(provinceAdCode: String?): List<LocationCity> {
        var code = provinceAdCode?.substring(0, 2)
        var cityList = LitePal.where(
            "substr(adcode,1,2) = ?", code
        ).find<LocationCity>()
        logd("查询到城市数据${cityList}")
        return cityList
    }


    fun queryDistinctByCity(cityAdCode: String?): List<LocationDistinct>? {
        var code = cityAdCode?.substring(0, 4)
        var distinctList = LitePal.where(
            "substr(adcode,1,4) = ?", code
        ).find<LocationDistinct>()
        return distinctList
    }


    /**
     * @author 汤洪斌
     * @time 2019/3/13 0013 14:13
     * @version 1.0
     * @describe 找到已经显示的那个省的下表
     */
//    private fun findIndex(areaList: List<LocationProvince>?): Int {
//        if (areaList == null) {
//            return 0
//        }
//
////        val index = areaList.indexOf(initArea)
//
////        return if (index < 0) 0 else index
//    }

    private fun logd(str:String){
        Log.d("TAG",str)
    }

}

