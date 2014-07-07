/*
 * Copyright (C), 2002-2013, 苏宁易购电子商务有限公司
 * FileName: ApplicationContextInit.java
 * Author:   penny_gu/12072585
 * Date:     2013-12-26 上午10:57:21
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.simple.rscserver;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author penny_gu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ApplicationContextInit implements ApplicationContextAware{

    private ApplicationContext act;
    
    /* (non-Javadoc)
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.act = applicationContext;
    }

    /**
     * @return the act
     */
    public ApplicationContext getAct() {
        return act;
    }
    
}
