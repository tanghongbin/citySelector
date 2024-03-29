package component.com.cityslectorcomponent.bean;

import java.util.List;

/**
 * @author 汤洪斌
 * @version 1.0
 * @time 2019/3/12 0012
 * @describe
 */
public class LocationBean {


    /**
     * status : 1
     * info : OK
     * infocode : 10000
     * count : 1
     * suggestion : {"keywords":[],"cities":[]}
     * districts : [{"citycode":[],"adcode":"100000","name":"中华人民共和国","center":"116.3683244,39.915085","level":"country","districts":[{"citycode":[],"adcode":"410000","name":"河南省","center":"113.665412,34.757975","level":"province","districts":[]},{"citycode":[],"adcode":"440000","name":"广东省","center":"113.280637,23.125178","level":"province","districts":[]},{"citycode":[],"adcode":"150000","name":"内蒙古自治区","center":"111.670801,40.818311","level":"province","districts":[]},{"citycode":[],"adcode":"230000","name":"黑龙江省","center":"126.642464,45.756967","level":"province","districts":[]},{"citycode":[],"adcode":"650000","name":"新疆维吾尔自治区","center":"87.617733,43.792818","level":"province","districts":[]},{"citycode":[],"adcode":"420000","name":"湖北省","center":"114.298572,30.584355","level":"province","districts":[]},{"citycode":[],"adcode":"210000","name":"辽宁省","center":"123.429096,41.796767","level":"province","districts":[]},{"citycode":[],"adcode":"370000","name":"山东省","center":"117.000923,36.675807","level":"province","districts":[]},{"citycode":[],"adcode":"610000","name":"陕西省","center":"108.948024,34.263161","level":"province","districts":[]},{"citycode":"021","adcode":"310000","name":"上海市","center":"121.472644,31.231706","level":"province","districts":[]},{"citycode":[],"adcode":"520000","name":"贵州省","center":"106.713478,26.578343","level":"province","districts":[]},{"citycode":"023","adcode":"500000","name":"重庆市","center":"106.504962,29.533155","level":"province","districts":[]},{"citycode":[],"adcode":"540000","name":"西藏自治区","center":"91.132212,29.660361","level":"province","districts":[]},{"citycode":[],"adcode":"340000","name":"安徽省","center":"117.283042,31.86119","level":"province","districts":[]},{"citycode":[],"adcode":"350000","name":"福建省","center":"119.306239,26.075302","level":"province","districts":[]},{"citycode":[],"adcode":"430000","name":"湖南省","center":"112.982279,28.19409","level":"province","districts":[]},{"citycode":[],"adcode":"460000","name":"海南省","center":"110.33119,20.031971","level":"province","districts":[]},{"citycode":[],"adcode":"320000","name":"江苏省","center":"118.767413,32.041544","level":"province","districts":[]},{"citycode":[],"adcode":"630000","name":"青海省","center":"101.778916,36.623178","level":"province","districts":[]},{"citycode":[],"adcode":"450000","name":"广西壮族自治区","center":"108.320004,22.82402","level":"province","districts":[]},{"citycode":[],"adcode":"640000","name":"宁夏回族自治区","center":"106.278179,38.46637","level":"province","districts":[]},{"citycode":[],"adcode":"360000","name":"江西省","center":"115.892151,28.676493","level":"province","districts":[]},{"citycode":[],"adcode":"330000","name":"浙江省","center":"120.153576,30.287459","level":"province","districts":[]},{"citycode":[],"adcode":"130000","name":"河北省","center":"114.502461,38.045474","level":"province","districts":[]},{"citycode":"1852","adcode":"810000","name":"香港特别行政区","center":"114.173355,22.320048","level":"province","districts":[]},{"citycode":"1886","adcode":"710000","name":"台湾省","center":"121.509062,25.044332","level":"province","districts":[]},{"citycode":"1853","adcode":"820000","name":"澳门特别行政区","center":"113.54909,22.198951","level":"province","districts":[]},{"citycode":[],"adcode":"620000","name":"甘肃省","center":"103.823557,36.058039","level":"province","districts":[]},{"citycode":[],"adcode":"510000","name":"四川省","center":"104.065735,30.659462","level":"province","districts":[]},{"citycode":[],"adcode":"220000","name":"吉林省","center":"125.3245,43.886841","level":"province","districts":[]},{"citycode":"022","adcode":"120000","name":"天津市","center":"117.190182,39.125596","level":"province","districts":[]},{"citycode":[],"adcode":"530000","name":"云南省","center":"102.712251,25.040609","level":"province","districts":[]},{"citycode":"010","adcode":"110000","name":"北京市","center":"116.405285,39.904989","level":"province","districts":[]},{"citycode":[],"adcode":"140000","name":"山西省","center":"112.549248,37.857014","level":"province","districts":[]}]}]
     */

    private String status;
    private String info;
    private String infocode;
    private String count;
    private List<CountryBean> districts;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }


    public List<CountryBean> getDistricts() {
        return districts;
    }

    public void setDistricts(List<CountryBean> districts) {
        this.districts = districts;
    }

    public static class CountryBean{
        private String adcode;
        private String name;
        private String center;
        private String level;
        private List<LocationProvince> districts;

        public String getAdcode() {
            return adcode;
        }

        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCenter() {
            return center;
        }

        public void setCenter(String center) {
            this.center = center;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public List<LocationProvince> getDistricts() {
            return districts;
        }

        public void setDistricts(List<LocationProvince> districts) {
            this.districts = districts;
        }
    }
}
