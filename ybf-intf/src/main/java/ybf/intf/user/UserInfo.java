package org.simple.user;

import java.util.Date;

import javax.validation.constraints.Size;

import org.simple.CodeBean;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 */
public class UserInfo extends CodeBean {

    private static final long serialVersionUID = 4575640371937372814L;
    private Date joinDate;
    @Size(min = 4, message = "后台JSR303验证:电话号码长度要大于4")
    private String mobileNo;
    private String title;
    private Integer level;
    private boolean gender;
    private boolean manager;
    private String email;
    private int version;

    public UserInfo() {
        super();
    }

    /**
     * @return the mobileNo 手机号码
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo the mobileNo to set
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * @return the title 职务，如开发工程师
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the level 级别
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Integer level) {
        this.level = level;
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
     * @return the gender 性别，True：男，False：女，Null:未知
     */
    public boolean getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(boolean gender) {
        this.gender = gender;
    }

    /**
     * @return the manager 是否经理
     */
    public boolean isManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(boolean manager) {
        this.manager = manager;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 版本控制用的字段，前台不允许访问
     * 
     * @return
     */
    int getVersion() {
        return version;
    }

    void setVersion(int version) {
        this.version = version;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

}