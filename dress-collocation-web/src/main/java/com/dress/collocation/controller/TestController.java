package com.dress.collocation.controller;

import com.dress.collocation.service.TestService;
import com.dress.collocation.util.ResponseUtils;
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
    @RequestMapping("test.app")
    public Object test() throws Exception {
        Object returnObj = null;
        try {
            String result = testService.testQuery();
            returnObj = ResponseUtils.SuccessResponse(result);
        } catch (Exception e) {
            returnObj = ResponseUtils.systemError();
        }
        return returnObj;
    }
}
