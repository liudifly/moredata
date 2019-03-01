package com.springboot.dao;


import com.alibaba.fastjson.JSONObject;
import com.springboot.config.DS;
import com.springboot.mapper.DBOneMapper;
import com.springboot.mapper.DBTwoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


/**
 * dao层
 * Created by pure on 2018-05-06.
 */
@Repository
public class MoredataDao {
    //使用xml配置形式查询

    @Autowired(required = true)
    private DBOneMapper dBOneMapper;

    @Autowired(required = true)
    private DBTwoMapper dBTwoMapper;

    @DS("datasource1")
    public List<JSONObject> getdata1(){
        //获取菜单信息
        List<JSONObject> data1 = dBOneMapper.getdata1();
        return data1;
    }

    @DS("datasource2")
    public List<JSONObject> getdata2(){
        //获取菜单信息
        List<JSONObject> data2 = dBTwoMapper.getdata2();
        return data2;
    }

}
