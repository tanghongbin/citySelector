package component.com.cityslectorcomponent.view

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import component.com.cityslectorcomponent.bean.LocationBean
import component.com.cityslectorcomponent.bean.LocationCity
import component.com.cityslectorcomponent.bean.LocationDistinct
import component.com.cityslectorcomponent.bean.LocationProvince
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.litepal.LitePal
import java.io.InputStreamReader

class AddressSelectorHelper{
    companion object {
        val SHARE_PREFERENCES_NAME = "city_selctor"

        /**
        @author 汤洪斌
        @time 16:34
        @version 1.0
        @describe 保存地区数据到数据库,改成从city.json文件读，因为文件太大，返回的数据不正确
         */
        fun initAreaData(context:Context) {

            if (context.getSharedPreferences(SHARE_PREFERENCES_NAME,Context.MODE_PRIVATE).getBoolean("hasStoredArea", false)) {
                return
            }
            Observable.just("")
                    .map {
                        var reader = InputStreamReader(context.assets.open("city.json"))
                        var jsonStr = reader.readText()
                        reader.close()
                        var bean: LocationBean = toJsonObject(jsonStr, LocationBean::class.java)
                        bean
                    }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        saveDataToDb(context,it)
                    })

        }


        /**
        @author 汤洪斌
        @time 2019/3/12 0012 10:48
        @version 1.0
        @describe 将字符串转换成对象
         */
        private fun <T> toJsonObject(string: String?, clas: Class<T>): T {
            return Gson().fromJson(string, clas)
        }


        /**
        @author 汤洪斌
        @time 2019/3/12 0012 17:16
        @version 1.0
        @describe 保存地区数据到数据库
         */
        private fun saveDataToDb(context: Context,it: LocationBean?) {
            LitePal.deleteAll(LocationProvince::class.java)
            LitePal.deleteAll(LocationCity::class.java)
            LitePal.deleteAll(LocationDistinct::class.java)
            Observable.just(it)
                    .map {
                        it.districts[0]
                    }
                    .flatMap {
                        Observable.fromIterable(it?.districts)
                    }.map {
                        var result = it.save()
                        it
                    }.flatMap {
                        Observable.fromIterable(it?.districts)
                    }.map {
                        var result = it.save()
                        it
                    }.flatMap {
                        Observable.fromIterable(it?.districts)
                    }.map {
                        var result = it.save()
                    }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({

                    }, {
                        Log.d("TAG","地址存储失败")
                    }, {
                        context.getSharedPreferences(SHARE_PREFERENCES_NAME,Context.MODE_PRIVATE).edit().putBoolean("hasStoredArea", true).commit()
                    })
        }
    }




}