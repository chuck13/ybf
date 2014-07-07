/*
 * Copyright (C), 2002-2013, 苏宁易购电子商务有限公司
 * FileName: RscServerService.java
 * Author:   penny_gu/12072585
 * Date:     2013-12-5 下午4:26:00
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.simple.rscserver;

import java.util.Map;

import com.suning.framework.page.QueryParam;
import com.suning.framework.page.QueryResult;
import com.suning.rsc.server.ESBMsgBean;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author penny_gu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface RscServerService {
    QueryResult<EsbMsgVo> queryEsbMsgList(QueryParam<EsbMsgVo> pager, String type);
    
    Map<String, String> getMsgTypesToNames();
    
    void deleteItem(Long id);
    
    void batchDeleteItem(Long[] idList);
    
    ESBMsgBean getItemById(Long id);
}
