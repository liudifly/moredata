package com.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.service.MoredataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


/**
 * Created by pure on 2018-05-06.
 */
//@CrossOrigin
@RestController
@RequestMapping(value = "/moredata",method = {RequestMethod.POST,RequestMethod.GET})
public class MoredataController {
    @Autowired
    private MoredataService moredataService;

    @RequestMapping(value = "/getAllData1")
    public List<JSONObject> getDb1AllUser() {
        List<JSONObject> list1= moredataService.getAllData1();
        return list1;
    }


    @RequestMapping(value = "/getAllData2")
    public List<JSONObject> getDb2AllUser() {
        List<JSONObject> list2= moredataService.getAllData2();
        return list2;
    }


    ///http://192.168.0.80:8080/moredata/getAllData2

}
