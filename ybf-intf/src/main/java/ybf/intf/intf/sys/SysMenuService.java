package org.simple.intf.sys;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;

import org.simple.dto.Pager;
import org.simple.dto.SimpleJsonTree;
import org.simple.entity.sys.SysMenu;
import com.suning.framework.page.QueryResult;

/**
 * 
 * 功能描述： Menu相关的类
 * 
 * @author 作者 陈琦
 * @version 1.0.0
 */
public interface SysMenuService extends InitializingBean {

    /**
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */

    List<SimpleJsonTree> findJsonChildByCode(String code);

    /**
     * 功能描述： 输入参数：无
     * 
     * @param 参数说明 返回值: SimpleJsonTree类型的List为了能使zTree生成树形结构
     * @return List
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    List<SimpleJsonTree> findAllJsonMenu();

    /**
     * 功能描述： 输入参数：parentId
     * 
     * @param 父类菜单的ID 返回值: List
     * @return 子菜单的List
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    List<SysMenu> findByParentId(Long parentId);

    /**
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    List<SimpleJsonTree> findJsonMenuByPid(Long pid);

    /**
     * 功能描述： 输入参数：parentId
     * 
     * @param 父类菜单的ID 返回值: 类型 List
     * @return List<LinkedHashMap<String, List<SysTabMenuInfo>> 子菜单的hashMap，以二级菜单的名称作为key值， 所对应的三级菜单的List作为value值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    List<LinkedHashMap<String, List<SysMenu>>> findSubMenusByParentId(Long parentId);

    /**
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    SysMenu find(SysMenu sysMenu);

    /**
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    SysMenu findById(Long id);

    /**
     * 
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    List<SysMenu> findAll();

    /**
     * 
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    QueryResult<SysMenu> listByPage(Pager<Map> pager);

    /**
     * 
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    SysMenu findByCode(String code);

    /**
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    List<SysMenu> findByStatus(String status);

    /**
     * 
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    boolean isUnique(String newValue, String oldValue);

    /**
     * 
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    boolean isNameUnique(String newValue, String oldValue);

    /**
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */

    boolean isExists(String code);

    /**
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    List<SysMenu> findParentMenu();

    /**
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    List<SysMenu> findChildByCode(String code);

    /**
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    List<SysMenu> findByName(String name);

    /**
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    void refreshSysMenuCache();

    /**
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    Cache getSysMenuChildCache();

    /**
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    Number persist(SysMenu sysMenu);

    /**
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    int merge(SysMenu sysMenu);

    /**
     * 功能描述： 输入参数：<按照参数定义顺序>
     * 
     * @param 参数说明 返回值: 类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    int remove(SysMenu sysMenu);
}
