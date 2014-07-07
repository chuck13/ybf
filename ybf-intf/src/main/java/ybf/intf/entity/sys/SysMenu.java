package org.simple.entity.sys;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Menu generated by hbm2java
 */
@Entity(name = "SYS_MENU")
public class SysMenu implements java.io.Serializable {

    public static final Long ROOT_PARENT_ID = 0L;
    
    private static final long serialVersionUID = 2954860747967957423L;

    private Long menuId;

    private Long parentId;

    private String menuCode;

    private String name;

    private String ancestors;

    private String description;

    private String icon;

    private String iconOpen;

    private String link;

    private String status;

    private String createdBy;

    private Date dateCreated = new Date();

    private String updatedBy;

    private Date dateUpdated;

    private String auditedBy;

    private Date dateAudited;

    private String target;

    private String type;

    private Boolean isParent;

    private String parentName;

    private String parentCode;

    private SysMenu parentSysMenu;

    private List<SysMenu> childrenSysMenus;
    
    public SysMenu() {

    }

    public SysMenu(Long id) {
        this.menuId = id;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    /**
     * @return the menuId
     */
    @Id
    @Column(name = "MENU_ID", unique = true, nullable = false)
    public Long getMenuId() {
        return menuId;
    }

    /**
     * @param menuId the menuId to set
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Column(name = "MENU_CODE", length = 200)
    public String getMenuCode() {
        return this.menuCode;
    }

    @Column(name = "PARENT_ID")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    @Column(name = "NAME", length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "ANCESTORS")
    public String getAncestors() {
        return this.ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    @Column(name = "DESCRIPTION", length = 200)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "ICON")
    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column(name = "ICON_OPEN")
    public String getIconOpen() {
        return this.iconOpen;
    }

    public void setIconOpen(String iconOpen) {
        this.iconOpen = iconOpen;
    }

    @Column(name = "LINK")
    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Column(name = "STATUS")
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "CREATED_BY")
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED")
    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "UPDATED_BY")
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_UPDATED")
    public Date getDateUpdated() {
        return this.dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Column(name = "AUDITED_BY")
    public String getAuditedBy() {
        return this.auditedBy;
    }

    public void setAuditedBy(String auditedBy) {
        this.auditedBy = auditedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_AUDITED")
    public Date getDateAudited() {
        return this.dateAudited;
    }

    public void setDateAudited(Date dateAudited) {
        this.dateAudited = dateAudited;
    }

    @Column(name = "TARGET")
    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Column(name = "TYPE")
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean equals(Object menu) {
        if (menu == null){
            return false;
        }
        if (menu instanceof SysMenu) {
            SysMenu menuObj = (SysMenu) menu;
            return menuObj.getMenuId().equals(menuId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * @return the isParent
     */
    @Column(name = "IS_PARENT")
    public Boolean getIsParent() {
        return isParent;
    }

    /**
     * @param isParent the isParent to set
     */
    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    /**
     * @return the parentSysMenu
     */
    public SysMenu getParentSysMenu() {
        return parentSysMenu;
    }

    /**
     * @param parentSysMenu the parentSysMenu to set
     */
    public void setParentSysMenu(SysMenu parentSysMenu) {
        this.parentSysMenu = parentSysMenu;
    }

    /**
     * @return the childrenSysMenus
     */
    public List<SysMenu> getChildrenSysMenus() {
        return childrenSysMenus;
    }

    /**
     * @param childrenSysMenus the childrenSysMenus to set
     */
    public void setChildrenSysMenus(List<SysMenu> childrenSysMenus) {
        this.childrenSysMenus = childrenSysMenus;
    }

}
