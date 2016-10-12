package com.dress.collocation.controller;

import com.dress.collocation.service.TestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2016/10/10.
 */
@Controller
@RequestMapping
public class TestController {
    public static final Logger LOGGER = Logger.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @ResponseBody
    @RequestMapping("test.do")
    public String test() throws Exception {

        return testService.testQuery();
    }
}
