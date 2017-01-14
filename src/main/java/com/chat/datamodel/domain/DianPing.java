package com.chat.datamodel.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by xiangtianyu on 2017/1/14.
 */
@Entity
@Indexed
@Table(name = "dianping")
public class DianPing {
    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int    id;

    @Column(name = "shopId")
    private String shopId;

    @Column(name = "restaurantName")
    private String restaurantName;

    @Column(name = "averagePrice")
    private String averagePrice;

    @Column(name = "tasteScore")
    private String tasteScore;

    @Column(name = "envScore")
    private String envScore;

    @Column(name = "serviceScore")
    private String serviceScore;

    @Column(name = "address")
    private String address;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "sellTime")
    private String sellTime;

}
