package org.simple.dto;

/**
 * tree bean
 * 记录菜单tree节点的bean
 */
public class SimpleJsonTree {
    String id;
    String pId;
    String name;
    Boolean open;
    String url;
    String target;
    Boolean isParent;

    // String isLeaf;

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * @return the isParent
     */
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
     * @return the open
     */
    public Boolean getOpen() {
        return open;
    }

    /**
     * @param open the open to set
     */
    public void setOpen(Boolean open) {
        this.open = open;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }
}
