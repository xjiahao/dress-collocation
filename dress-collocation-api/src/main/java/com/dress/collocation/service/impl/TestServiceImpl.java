package com.dress.collocation.service.impl;

import com.dress.collocation.mapper.TestDao;
import com.dress.collocation.pojo.User;
import com.dress.collocation.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2016/10/10.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestDao testDao;

    public String testQuery() throws Exception {
        List<User> users = testDao.testQuery();
        String res = "";
        if (users != null && users.size() > 0) {
            for (User user : users) {
                res += user.toString() + "|";
            }
        } else {
            res = "Not found.";
        }
        return res;
    }

    public String testQuery1(){
        return "1111";
    }
}
