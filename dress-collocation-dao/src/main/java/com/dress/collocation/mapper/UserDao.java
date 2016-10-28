package com.dress.collocation.mapper;

import com.dress.collocation.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Description:
 * Created by xuejiahao on 2016/10/22.
 */
@Repository
public interface UserDao {
    /**
     * 注册用户
     * @param user
     */
    Long addUser(User user);

    /**
     * 根据用于名获取用户对象
     * @param userName 用户名
     * @return
     */
    User getUserByUserName(@Param("userName") String userName);

    /**
     * 根据用户名获取用户数
     * @param userName 用户名
     * @return
     */
    Integer getCountByUserName(@Param("userName") String userName);

    /**
     * 根据用户id修改用户信息
     * @param user
     */
    void updateUserByUserId(User user);

    /**
     * 根据用户id获取用户
     * @param userId 用户名
     * @return
     */
    User getUserByUserId(@Param("userId") Long userId);
}
