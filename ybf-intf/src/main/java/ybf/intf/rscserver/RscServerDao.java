package org.simple.rscserver;

import java.util.List;
import java.util.Map;

import com.suning.rsc.server.ESBMsgBean;

public interface RscServerDao {
    
    Map<String, Object> querryEsbMsgNum(Map<String, Object> paramMap);
    
    Map<String, Object> querryEsbMsgCountNum(Map<String, Object> paramMap);
    
    List<EsbMsgVo> querryMsgList(Map<String, Object> paramMap);
    
    ESBMsgBean getEsbMsgById(Long id);
    
    List<EsbMsgVo> querryMsgCountList(Map<String, Object> paramMap);
    
    List<Map<String,Object>> querryAllMsgTypes();
    
    void deleteEsbMsgById(Long id);
    
    void batchDeleteItem(Long[] idList);

}
