package org.simple.user;

import java.util.Set;

/**
 * @author Johnny
 * 
 */
public class User extends UserInfo {
    
    private static final long serialVersionUID = 1L;
    private Set<Role> roles;

    /**
     * @return the roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
