/*
 * Copyright (C), 2002-2013, 苏宁易购电子商务有限公司
 * FileName: ESBMsgBean.java
 * Author:   penny_gu/12072585
 * Date:     2013-11-4 上午11:40:03
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.simple.rscserver;

import java.util.Date;

import com.suning.rsc.server.ESBMsgBean;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author penny_gu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class EsbMsgVo extends ESBMsgBean {
    
    private Date beginDate; 
    
    private Date endDate;
    
    private int succCount = 0;//处理成功的数量
    
    private int failCount = 0;//处理失败的数量
    
    private int unprocessedCount = 0;//未处理的数量
    

    /**
     * @return the succCount
     */
    public int getSuccCount() {
        return succCount;
    }

    /**
     * @param succCount the succCount to set
     */
    public void setSuccCount(int succCount) {
        this.succCount = succCount;
    }

    /**
     * @return the failCount
     */
    public int getFailCount() {
        return failCount;
    }

    /**
     * @param failCount the failCount to set
     */
    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    /**
     * @return the unprocessedCount
     */
    public int getUnprocessedCount() {
        return unprocessedCount;
    }

    /**
     * @param unprocessedCount the unprocessedCount to set
     */
    public void setUnprocessedCount(int unprocessedCount) {
        this.unprocessedCount = unprocessedCount;
    }

    /**
     * @return the beginDate
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * @param beginDate the beginDate to set
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
