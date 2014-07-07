/*
 * Copyright (C), 2002-2013, 苏宁易购电子商务有限公司
 * FileName: RscServerServiceImpl.java
 * Author:   penny_gu/12072585
 * Date:     2013-12-5 下午4:28:53
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.simple.service.impl.rscserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.suning.framework.page.QueryParam;
import com.suning.framework.page.QueryResult;
import com.suning.rsc.server.ESBMsgBean;
import com.suning.rsc.server.job.MessageInfoHolder;
import com.suning.rsc.server.job.MsgDetailInfo;
import org.simple.service.rscserver.EsbMsgVo;
import org.simple.service.rscserver.RscConstant;
import org.simple.service.rscserver.RscServerDao;
import org.simple.service.rscserver.RscServerService;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author penny_gu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RscServerServiceImpl implements RscServerService {

    @Autowired
    RscServerDao rscServerDaoimpl;
    
    static Logger log = LoggerFactory.getLogger(RscServerServiceImpl.class);
    
    /* (non-Javadoc)
     * @see org.simple.service.rscserver.RscServerService#queryEsbMsgList(com.suning.framework.page.QueryParam)
     */
    @Override
    public QueryResult<EsbMsgVo> queryEsbMsgList(QueryParam<EsbMsgVo> pager, String type) {
        EsbMsgVo EsbMsgVo = pager.getQueryParam();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (null != EsbMsgVo) {
            paramMap.put("beginDate", EsbMsgVo.getBeginDate());
            paramMap.put("endDate", EsbMsgVo.getEndDate());
            if(null != EsbMsgVo.getMsgType() && !EsbMsgVo.getMsgType().equals("-2")){
                paramMap.put("msgType", EsbMsgVo.getMsgType());
            } 
            if(EsbMsgVo.getStatus() != -2){
                paramMap.put("status", EsbMsgVo.getStatus());
            } 
        }

        int count = 0;
        if(RscConstant.DISPLAY_QUERY.equals(type)){
            Map<String, Object> m = rscServerDaoimpl.querryEsbMsgNum(paramMap);
            count = (Integer) m.get("count");
        } else if(RscConstant.COUNT_QUERY.equals(type)) {
            Map<String, Object> m = rscServerDaoimpl.querryEsbMsgCountNum(paramMap);
            count = (Integer) m.get("count");
        }
        QueryResult<EsbMsgVo> queryResult = new QueryResult<EsbMsgVo>(count, pager.getPageSize(),
                pager.getPageNumber());

        paramMap.put("startIndex", queryResult.getIndexNumber());
        paramMap.put("maxCount", queryResult.getPageSize());

        List<EsbMsgVo> tmp = null;
        if(RscConstant.DISPLAY_QUERY.equals(type)){
            tmp = displayQueryMsg(paramMap);
        } else if(RscConstant.COUNT_QUERY.equals(type)) {
            tmp = countQueryMsg(paramMap);
        }
        queryResult.setDatas(tmp);
        return queryResult;
    }
    
    protected List<EsbMsgVo> displayQueryMsg(Map<String, Object> paramMap){
        // 得到显示在一页上的所有记录的id（减少查询时间的消耗）
        List<EsbMsgVo> l = rscServerDaoimpl.querryMsgList(paramMap);
        List<EsbMsgVo> tmp = new ArrayList<EsbMsgVo>();

        // 根据id号查询得到记录
        if (!CollectionUtils.isEmpty(l)) {
            for (EsbMsgVo eb : l) {
                // long id = chBean.getId();
                if(null != eb.getErrorMsg()){
//                    String estr = CommonUtil.clobToString(eb.getErrorMsg()).replace("\n", "");
                    String estr = eb.getErrorMsg().replace("\n", "");
                    eb.setErrorMsg(estr);
                }
                if(null != eb.getMsgContent()){
//                    String cstr = CommonUtil.clobToString(eb.getMsgContent()).replace("\n", "");
                    String cstr = eb.getMsgContent().replace("\n", "");
                    eb.setMsgContent(cstr);
                }
                
                MsgDetailInfo info = MessageInfoHolder.getMsgInfoByMsgType(eb.getMsgType());
                if(null != info){
                    eb.setMsgName(info.getMsgName());
                    eb.setMsgDesc(info.getMsgDesc());
                } else {
                    log.error("没有相应的job对应处理----" + eb.getMsgType() + "---此类型的消息！");
                }
                tmp.add(eb);
            }
        }
        return tmp;
    }
    
    /**
     * 
     * 查询esb消息统计数据。以消息类型和消息处理状态为分组条件，统计各类型消息的消费处理情况
     *
     * @param paramMap 数据库查询语句传参
     */
    protected List<EsbMsgVo> countQueryMsg(Map<String, Object> paramMap){
        List<EsbMsgVo> l = rscServerDaoimpl.querryMsgCountList(paramMap);
        if (!CollectionUtils.isEmpty(l)) {
            for (EsbMsgVo eb : l) {
                MsgDetailInfo info = MessageInfoHolder.getMsgInfoByMsgType(eb.getMsgType());
                if(null != info){
                    eb.setMsgName(info.getMsgName());
                    eb.setMsgDesc(info.getMsgDesc());
                } else {
                    log.error("没有相应的job对应处理----" + eb.getMsgType() + "---此类型的消息！");
                }
            }
        }
        return l;
    }
    
    /* (non-Javadoc)
     * @see org.simple.service.rscserver.RscServerService#queryAllMsgTypes()
     */
    @Override
    public Map<String, String> getMsgTypesToNames() {
        List<Map<String,Object>> listMap = rscServerDaoimpl.querryAllMsgTypes();
        if(null != listMap && listMap.size() != 0 && listMap.get(0).get("MSG_TYPE") != null){
            Map<String, String> typeNames = new HashMap<String, String>();
            typeNames.put("-2", "所有");
            for(Map<String,Object> map: listMap){
                String msgType = (String) map.get("MSG_TYPE");
                MsgDetailInfo info = MessageInfoHolder.getMsgInfoByMsgType(msgType);
                if(null != info){
                    typeNames.put(msgType, info.getMsgName());
                } else {
                    log.error("没有相应的job对应处理----" + msgType + "---此类型的消息！");
                }
            }
            return typeNames;
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.simple.service.rscserver.RscServerService#deleteItem(java.lang.Long)
     */
    @Override
    public void deleteItem(Long id) {
        rscServerDaoimpl.deleteEsbMsgById(id);
    }

    /* (non-Javadoc)
     * @see org.simple.service.rscserver.RscServerService#batchDeleteItem(java.util.List)
     */
    @Override
    public void batchDeleteItem(Long[] idList) {
        rscServerDaoimpl.batchDeleteItem(idList);
    }

    /* (non-Javadoc)
     * @see org.simple.service.rscserver.RscServerService#getItemById(java.lang.Long)
     */
    @Override
    public ESBMsgBean getItemById(Long id) {
        return rscServerDaoimpl.getEsbMsgById(id);
    }

}
