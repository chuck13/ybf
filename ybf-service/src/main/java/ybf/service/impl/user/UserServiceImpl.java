/**
 * SUNING APPLIANCE CHAINS.
 * Copyright (c) 2012-2012 All Rights Reserved.
 */
package org.simple.service.impl.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.simple.service.CodeBean;
import org.simple.service.intf.dao.BaseDao;
import org.simple.service.user.LoginUser;
import org.simple.service.user.Role;
import org.simple.service.user.User;
import org.simple.service.user.UserInfo;
import org.simple.service.user.UserQueryParam;
import org.simple.service.user.UserService;
import com.suning.framework.dal.transaction.annotation.Transactional;
import com.suning.framework.exception.BaseException;
import com.suning.framework.page.QueryParam;
import com.suning.framework.page.QueryResult;

/**
 * 用户CRUD操作的业务服务层，用来处理用户的创建/删除/修改和查询功能。
 * 
 * @author 作者 Yangdc@cnsuning.com
 * @version 1.0.0
 */
public class UserServiceImpl implements UserService, UserDetailsService {

    /** 检索Map的key：code **/
    private static final String QUERY_PARAM_KEY_CODE = "code";

    @Autowired
    private BaseDao baseDaoImpl;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        // 取得用户信息
        User user = getUser(username);
        // 返回Login用的User对象
        return new LoginUser(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(paramIndex = -1)
    public User getUser(String code) {
        User user = new User();
        // 设置检索参数：code
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(QUERY_PARAM_KEY_CODE, code);

        List<User> userList = baseDaoImpl.queryForList("oms.query_user", param, User.class);
        if (userList != null && !userList.isEmpty()) {
            user = userList.get(0);
        }

        // 检索用户的Role信息，如果用户没有role，则创建空role
        String userCode = user.getCode();
        param.put(QUERY_PARAM_KEY_CODE, userCode);
        List<Role> roleList = baseDaoImpl.queryForList("oms.query_user_role", param, Role.class);
        Set<Role> roleSet = new HashSet<Role>();
        for (Role role : roleList) {
            roleSet.add(role);
        }
        // 将用户role信息添加到用户信息中
        user.setRoles(roleSet);

        return user;
    }

    /**
     * 根据参数取得分页数据
     * 
     * @param param 分页参数
     * @return QueryResult 检索结果
     */
    public QueryResult<UserInfo> pageQuery(QueryParam<Map<String, Object>> param) {
        // 调用取得总件数的SQL，取得满足条件的数据件数
        int totalCount = 0;
        CodeBean count = baseDaoImpl.queryForObject("oms.page_count_user", param.getQueryParam(), CodeBean.class);
        if (count != null) {
            totalCount = Integer.valueOf(count.getCode()).intValue();
        }
        // 将检索参数中的检索开始页、每页件数设置到返回结果中
        QueryResult<UserInfo> result = new QueryResult<UserInfo>(totalCount, 
                                param.getPageSize(), param.getPageNumber());
        // 如果总件数大于0，则检索满足条件的数据
        if (totalCount != 0) {
            // 设置检索开始行
            param.getQueryParam().put("startIndex", result.getIndexNumber());
            // 设置检索数据件数
            param.getQueryParam().put("maxCount", result.getPageSize());

            // 检索数据列表，放到返回结果对象中
            List<UserInfo> retList = baseDaoImpl.queryForList("oms.page_query_user", param.getQueryParam(),
                    UserInfo.class);
            result.setDatas(retList);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(paramIndex = -1)
    public void saveUser(User user) {
        // 定义用户是否存在的sql检索条件
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(QUERY_PARAM_KEY_CODE, user.getCode());

        // 设置更新用户的sql参数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(QUERY_PARAM_KEY_CODE, user.getCode());
        paramMap.put("mobileNo", user.getMobileNo());
        paramMap.put("joinDate", user.getJoinDate());
        paramMap.put("name", user.getName());

        // 检索用户信息
        List<User> result = baseDaoImpl.queryForList("oms.query_user", queryParam, User.class);
        if (result == null || result.isEmpty()) {
            // 如果用户信息不存在，则创建新用户单表信息
            // 设置默认的非空数据
            paramMap.put("gender", 1);
            paramMap.put("manager", false);
            paramMap.put("version", 0);
            paramMap.put("title", null);
            baseDaoImpl.execute("oms.insert_user", paramMap);
        } else if (user.getName() == null) {
            // 删除用户单表信息

            /** for Exception test demo begin */
            throwsBussinessExceptionDemo(user);
            /** for Exception test demo end */

            baseDaoImpl.execute("oms.delete_user", paramMap);
        } else {
            // 更新用户单表信息

            /** for Exception test demo begin */
            catchAndRethrowExceptionDemo(user);
            /** for Exception test demo end */

            baseDaoImpl.execute("oms.update_user", paramMap);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public QueryResult<UserInfo> searchUser(UserQueryParam param) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        // 检索所有用户
        List<UserInfo> obj = baseDaoImpl.queryForList("oms.list_user", paramMap, UserInfo.class);
        QueryResult<UserInfo> retObj = new QueryResult<UserInfo>();
        if (obj != null) {
            // 设置返回数据
            retObj.setDatas(obj);
            retObj.setTotalDataCount(obj.size());
            retObj.setIsLastPage(true);
            retObj.setPageCount(1);
            retObj.setPageNumber(1);
            retObj.setPageSize(obj.size());
        } else {
            retObj.setDatas(new ArrayList<UserInfo>());
            retObj.setTotalDataCount(0);
            retObj.setIsLastPage(true);
            retObj.setPageCount(0);
        }

        return retObj;
        // return baseDaoImpl.pageQuery(param);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Role> getRoles() {
        return baseDaoImpl.queryForList("oms.list_user_role", new HashMap<String, Object>(), Role.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Role getRole(String name) {
        // 检索参数：code
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(QUERY_PARAM_KEY_CODE, name);

        // 检索角色
        List<Role> roleList = baseDaoImpl.queryForList("oms.query_role", paramMap, Role.class);

        // 返回角色对象
        return roleList == null || roleList.isEmpty() ? null : roleList.get(0);
    }

    /**
     * 统一异常demo方法，只为演示异常框架的功能
     */
    private void throwsBussinessExceptionDemo(User user) {
        Object[] param = new Object[] { user.getCode() };
        if (user.getCode().startsWith("exception_test")) {
            throw new BaseException("TEST00001", "删除用户数据失败!", param);
        }
    }

    /**
     * 统一异常demo方法，只为演示异常框架的功能
     */
    private void catchAndRethrowExceptionDemo(User user) {
        if (user.getCode().startsWith("exception_test")) {
            try {
                throwCheckException();
            } catch (IOException ioex) {
                throw new BaseException(ioex);
            }
        }
    }

    /**
     * 统一异常demo方法，只为演示异常框架的功能
     */
    private void throwCheckException() throws IOException {
        throw new IOException("为了演示统一异常处理框架效果手动抛出的Checked异常,仅作测试用.");
    }

}
