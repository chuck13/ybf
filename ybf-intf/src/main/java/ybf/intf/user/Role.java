package org.simple.user;

import org.simple.CodeBean;

/**
 * @author Johnny
 * 
 */
public class Role extends CodeBean {
    
    public static final String RELEASER = "RELEASER";
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    private static final long serialVersionUID = 2163325609600047689L;

    public Role() {
        super();
    }

    /**
     * @param code
     * @param name
     */
    public Role(String code, String name) {
        super(code, name);
    }

    /**
     * @param code
     */
    public Role(String code) {
        super(code);
    }

}
