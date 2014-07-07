package org.simple.user;

import com.suning.framework.exception.BaseException;

/**
 * 〈一句话功能简述〉<br> 封装异常
 * 〈功能详细描述〉封装找不到用户异常
 */
public class UserNotFoundException extends BaseException {

    /**
     * 
     */
    private static final long serialVersionUID = 3807637725812539787L;

    public UserNotFoundException(String code, String logMsg) {
        super(code, logMsg);
    }

}
