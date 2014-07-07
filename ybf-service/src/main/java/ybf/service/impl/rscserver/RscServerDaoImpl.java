/*
 * Copyright (C), 2002-2013, 苏宁易购电子商务有限公司
 * FileName: ESBMsgPersist.java
 * Author:   penny_gu/12072585
 * Date:     2013-11-4 上午11:33:36
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.simple.service.impl.rscserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.framework.dal.client.DalClient;
import com.suning.rsc.server.ESBMsgBean;
import org.simple.service.rscserver.EsbMsgVo;
import org.simple.service.rscserver.RscServerDao;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author penny_gu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class RscServerDaoImpl implements RscServerDao {

    @Autowired
    DalClient dalClient;

    /**
     * 
     * 获取对应id的消息记录
     * 
     * @param bean
     * @return
     */
    @Override
    public ESBMsgBean getEsbMsgById(Long id) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        return dalClient.queryForObject("rscServer.query_by_id", paramMap, ESBMsgBean.class);
    }

    /**
     * 
     * 删除指定id的消息记录
     * 
     * @param id
     */
    @Override
    public void deleteEsbMsgById(Long id) {
        EsbMsgVo bean = new EsbMsgVo();
        bean.setMsgId(id);
        dalClient.remove(bean);
    }

    /**
     * 
     * 消息数据总数量查询
     * 
     * @param paramMap
     * 
     */
    @Override
    public Map<String, Object> querryEsbMsgNum(Map<String, Object> paramMap) {
        return dalClient.queryForMap("rscServer.query_esbMsg_num", paramMap);
    }

    /**
     * 
     * 消息统计数据总数量查询
     * 
     * @param paramMap
     * 
     */
    @Override
    public Map<String, Object> querryEsbMsgCountNum(Map<String, Object> paramMap) {
        return dalClient.queryForMap("rscServer.query_esbMsg_count_num", paramMap);
    }

    /**
     * 
     * 消息展示记录查询
     * 
     * @param paramMap
     * 
     */
    @Override
    public List<EsbMsgVo> querryMsgList(Map<String, Object> paramMap) {
        return dalClient.queryForList("rscServer.query_esbMsg_list", paramMap, EsbMsgVo.class);
    }

    /**
     * 
     * 消息统计数据查询
     * 
     * @param paramMap
     * 
     */
    @Override
    public List<EsbMsgVo> querryMsgCountList(Map<String, Object> paramMap) {
        return dalClient.queryForList("rscServer.query_msgCount_list", paramMap, EsbMsgVo.class);
    }

    /**
     * 
     * 查询所有的消息类型
     * 
     */
    @Override
    public List<Map<String, Object>> querryAllMsgTypes() {
        return dalClient.queryForList("rscServer.query_msgTypes", null);
    }

    /**
     * 
     * 批量删除指定id列表的消息记录数
     * 
     * @param ids
     */
    @Override
    public void batchDeleteItem(Long[] ids) {
        Map<String, Object>[] batchValues = new HashMap[ids.length];
        int i = 0;
        for (Long id : ids) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("msgId", id);
            batchValues[i++] = item;
        }
        dalClient.batchUpdate("rscServer.batch_delete_by_id", batchValues);
    }

}
