package com.example.liangmutian.searchview;


import java.io.Serializable;

/**
 * Created by lmton 16/9/9.
 */
public class Customer implements Serializable {


    /**
     * 客户名称
     */
    public Customer(String organizationName){

        this.organizationName=organizationName;
    }
    public String name;


    //企业title信息。
    public String organizationName;    //String		客户名称

}
