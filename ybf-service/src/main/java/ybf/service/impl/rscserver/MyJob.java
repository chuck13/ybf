/*
 * Copyright (C), 2002-2013, 苏宁易购电子商务有限公司
 * FileName: MyJob.java
 * Author:   penny_gu/12072585
 * Date:     2013-12-26 下午3:01:56
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.simple.service.impl.rscserver;

import com.suning.rsc.server.job.AbstractJob;
import org.simple.service.rscserver.RequestDto;

/**
 * 该类为处理持久化后的报文的job类，需要将该bean配置到配置文件中，并且在调度系统添加这个job，即可使用
 * 注意:每增加一个job，均需要在classpath目录下的msginfo_mapping_job_json.txt中添加一条记录
 *
 * @author penny_gu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MyJob extends AbstractJob<RequestDto> {

    /* 
     * job处理报文所涉及的业务逻辑操作在此定义
     */
    @Override
    public String handleBizz(RequestDto object) {
        // 业务报文处理逻辑。比如说更新库存那边“是否已到货通知”状态
        System.out.println("myJob测试业务逻辑------");
        return "";
    }
}
