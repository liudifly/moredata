package com.springboot.service;

import com.alibaba.fastjson.JSONObject;
import com.springboot.config.DS;
import com.springboot.dao.MoredataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * service层
 * Created by pure on 2018-05-06.
 */
@Service
public class MoredataService {
    @Autowired
    private MoredataDao moredataDao;

    //使用数据源1查询
    public List<JSONObject> getAllData1(){

        return moredataDao.getdata1();

    }


    //使用数据源2查询
    public List<JSONObject> getAllData2(){

       return moredataDao.getdata2();

    }


}
