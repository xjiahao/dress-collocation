package com.dress.collocation.mapper;

import com.dress.collocation.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by admin on 2016/10/10.
 */
@Component
public interface TestDao {

    List<User> testQuery() throws Exception;
}
