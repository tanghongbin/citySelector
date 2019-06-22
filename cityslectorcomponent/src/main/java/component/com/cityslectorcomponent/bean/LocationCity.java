package component.com.cityslectorcomponent.bean;


import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.List;
import java.util.Objects;

/**
 * @author 汤洪斌
 * @version 1.0
 * @time 2019/3/13 0013
 * @describe
 */
public class LocationCity extends LitePalSupport  {
    /**
     * citycode : []
     * adcode : 410000
     * name : 河南省
     * center : 113.665412,34.757975
     * level : province
     * districts : []
     */
    private String adcode;
    private String name;
    private String center;
    private String level;
    @Column(ignore = true)
    private List<LocationDistinct> districts;



    public List<LocationDistinct> getDistricts() {
        return districts;
    }

    public void setDistricts(List<LocationDistinct> districts) {
        this.districts = districts;
    }

    public LocationCity() {
    }

    public LocationCity(String adcode, String name) {
        this.adcode = adcode;
        this.name = name;
    }

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationCity that = (LocationCity) o;
        return adcode.equals(that.adcode);
    }

    @Override
    public int hashCode() {
        return adcode.hashCode();
    }
}

