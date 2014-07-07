/**
 * 
 */
package org.simple.user;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * LoginUser stores user login information
 * 
 * @author Johnny
 * 
 */
public class LoginUser implements UserDetails {
    private static final long serialVersionUID = 7015579602153328296L;

    /**
     * 〈一句话功能简述〉<br> GrantedAuthority接口实现
     * 〈功能详细描述〉实现spring security的GrantedAuthority接口，实现对自己的用户授权
     */
    static class ASGrantedAuthority implements GrantedAuthority {
        private static final long serialVersionUID = 4650581131083725656L;
        private Role role;

        public ASGrantedAuthority(Role role) {
            super();
            this.role = role;
        }

        public String getAuthority() {
            return role.getCode();
        }

        /*
         * (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return role.getCode().hashCode();
        }

        /*
         * (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if(obj instanceof ASGrantedAuthority){
                if(((ASGrantedAuthority) obj).role == null){
                    return false;
                }
            }
            return role.getCode().equals(((ASGrantedAuthority) obj).role.getCode());
        }

        /*
         * (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return role.toString();
        }

    }

    private Collection<GrantedAuthority> authorities;
    private User user;

    public LoginUser(User user) {
        super();
        this.user = user;
        authorities = new HashSet<GrantedAuthority>();
        if (user.getRoles() != null) {
            for (Role role : user.getRoles()) {
                authorities.add(new ASGrantedAuthority(role));
            }
        }
        // 真实的项目请注意：组织及所有上级组织的角色全部应该拿来放到organizations集合中。
        // 并且把所有用户所属的组织及上组组织的集合缓存起来，因为这可能经常会用到
    }

    // private Collection<Object> organizations;//把所有用户所属的组织及上组组织的集合缓存起来，因为这可能经常会用到
    /**
     * detect if user has the specified role.
     * 
     * @param roleCode
     * @return
     */
    public boolean hasRole(String roleCode) {
        return getAuthorities().contains(new ASGrantedAuthority(new Role(roleCode)));
    }

    /**
     * detect if user has the specified role.
     * 
     * @param role
     * @return
     */
    public boolean hasRole(Role role) {
        return getAuthorities().contains(new ASGrantedAuthority(role));
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
     */
    public String getPassword() {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
     */
    public String getUsername() {

        return user.getCode();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
     */
    public boolean isAccountNonExpired() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     */
    public boolean isAccountNonLocked() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     */
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
     */
    public boolean isEnabled() {
        return true;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

}
