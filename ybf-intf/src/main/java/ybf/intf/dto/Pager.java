/**
 * SUNING APPLIANCE CHAINS.
 * Copyright (c) 2012-2012 All Rights Reserved.
 */
package org.simple.dto;

import com.suning.framework.page.QueryParam;

/**
 * 
 * 功能描述：扩展查询参数基类
 * 
 * @author 作者 123456789abc@cnsuning.com
 * @version 1.0.0
 * @param <T> T代表要传入的对象类型
 */

public class Pager<T> extends QueryParam<T> {
    /**
     * 〈一句话功能简述〉<br> 定义排序方式
     */
    public enum OrderType {
        asc, desc
    }

    private String orderBy = "id";

    private String property; // 查找属性名称

    private String keyword; // 查找关键字

    private OrderType orderType = OrderType.desc; // 排序方式

    /**
     * @return the orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * @param orderBy the orderBy to set
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * @return the property
     */
    public String getProperty() {
        return property;
    }

    /**
     * @param property the property to set
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * @return the keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword the keyword to set
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @return the orderType
     */
    public OrderType getOrderType() {
        return orderType;
    }

    /**
     * @param orderType the orderType to set
     */
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    @Override
    public boolean isOrderAsc() {
        return orderType == OrderType.asc;
    }
}
