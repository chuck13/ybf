package org.simple.user;

import com.suning.framework.page.QueryParam;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉查询用户
 */
public class UserQueryParam extends QueryParam<UserInfo> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
