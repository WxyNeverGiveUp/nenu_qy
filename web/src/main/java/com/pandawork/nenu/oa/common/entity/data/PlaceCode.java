package com.pandawork.nenu.oa.common.entity.data;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * user: lishicao
 * date 15/4/15
 * time 上午9:56
 */
@Entity
@Table(name = "t_place_code")
public class PlaceCode extends AbstractEntity {
    //唯一表示
    @Id
    private Integer id;

    @Column(name = "place_id")
    private int placeId;

    @Column(name = "place")
    private String place;

    @Column(name = "show_name")
    private String showName;

    //拼音简写
    @Column(name = "pinyin_initial")
    private String pinyinInitial;

    @Column(name = "place_class_id")
    private int placeClassId;

    @Column(name = "place_class")
    private String placeClass;

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getPinyinInitial() {
        return pinyinInitial;
    }

    public void setPinyinInitial(String pinyinInitial) {
        this.pinyinInitial = pinyinInitial;
    }

    public String getPlaceClass() {
        return placeClass;
    }

    public void setPlaceClass(String placeClass) {
        this.placeClass = placeClass;
    }

    public int getPlaceClassId() {
        return placeClassId;
    }

    public void setPlaceClassId(int placeClassId) {
        this.placeClassId = placeClassId;
    }
}
