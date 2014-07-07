/**
 * SUNING APPLIANCE CHAINS.
 * Copyright (c) 2012-2012 All Rights Reserved.
 */
package org.simple.service.impl.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;

import org.simple.service.dto.Pager;
import org.simple.service.dto.SimpleJsonTree;
import org.simple.service.entity.sys.SysMenu;
import org.simple.service.intf.dao.BaseDao;
import org.simple.service.intf.sys.SysMenuService;
import com.suning.framework.page.QueryResult;

/**
 * 菜单项的权限控制Service方法实现
 * 
 * @author 12061742
 */
public class SysMenuServiceImpl implements SysMenuService {

    Logger logger = LoggerFactory.getLogger(SysMenuServiceImpl.class);

    Cache sysMenuChildCache;

    @Autowired
    private BaseDao baseDaoImpl;

    private static final String SQL_NAMESPACE = "sysMenu.";

    private static final String FIND_BY_PARENT_ID = "findByParentId";

    private static final String FIND_ALL_MENU = "findAllMenu";

    private static final String FIND_BY_CODE = "findByCode";

    private static final String FIND_BY_NAME = "findByName";

    private static final String FIND_PARENT = "findParent";

    private static final String ALL_MENU_CODE = "allMenuCode";

    private static final String SQL_COUNT = "count";

    private static final String SQL_LIST_BY_PAGE = "listByPage";

    /**
     * 根据菜单项的父节点，筛选出其所有的子节点list
     */
    @Override
    public List<SysMenu> findByParentId(Long parentId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("parentId", parentId.toString());
        List<SysMenu> menulist = baseDaoImpl.queryForList(SQL_NAMESPACE + FIND_BY_PARENT_ID, param, SysMenu.class);
        return menulist;
    }

    /**
     * 将所有的菜单项根据级别级别进行归类，此方法中归类了三个级别的菜单项
     */
    @Override
    public List<LinkedHashMap<String, List<SysMenu>>> findSubMenusByParentId(Long parentId) {
        List<LinkedHashMap<String, List<SysMenu>>> results = new ArrayList<LinkedHashMap<String, List<SysMenu>>>();
        List<SysMenu> secondMenus = this.findByParentId(parentId);
        for (SysMenu sysMenu : secondMenus) {
            LinkedHashMap<String, List<SysMenu>> subMenu = new LinkedHashMap<String, List<SysMenu>>();
            List<SysMenu> thirdMenus = findByParentId(sysMenu.getMenuId());
            subMenu.put(sysMenu.getName(), thirdMenus);
            results.add(subMenu);
        }
        return results;
    }

    /**
     * 获取经过权限控制后所允许访问的菜单列表
     */
    @Override
    public List<SimpleJsonTree> findAllJsonMenu() {
        logger.debug("[findAllJsonMenu]-->started");
        List<SysMenu> list = this.findAll(true);
        logger.debug("[findAllJsonMenu]-->result.size=" + list.size());
        return changeToJsonTree(list);
    }

    public List<SimpleJsonTree> findJsonMenuByPid(Long pid) {
        List<SysMenu> list = this.findByParentId(pid);
        return changeToJsonTree(list);
    }

    /**
     * 递归实现 将SysMenu list数据转化为SimpleJsonTree数据格式
     * 
     * @param sysList SysMenu list数据
     * @return 返回SimpleJsonTree格式的菜单数据
     */
    public List<SimpleJsonTree> changeToJsonTree(List<SysMenu> sysList) {
        List<SimpleJsonTree> jsonList = new ArrayList<SimpleJsonTree>();
        for (SysMenu menu : sysList) {
            // 假如当前的菜单项下有子菜单项，则对其进行遍历
            if (null != menu.getChildrenSysMenus() && 0 != menu.getChildrenSysMenus().size()) {
                List<SimpleJsonTree> childTrees = changeToJsonTree(menu.getChildrenSysMenus());
                jsonList.addAll(childTrees);
            }
            SimpleJsonTree jt = new SimpleJsonTree();
            jt.setId("" + menu.getMenuId());
            jt.setName(menu.getName());
            jt.setpId("" + menu.getParentId());
            jt.setIsParent(menu.getIsParent());
            if (StringUtils.isNotEmpty(menu.getLink())) {
                jt.setUrl("" + menu.getLink());
            } else {
                jt.setUrl("");
            }
            if (StringUtils.isNotEmpty(menu.getTarget())) {
                jt.setTarget(menu.getTarget());
            } else {
                jt.setTarget("");
            }

            if (menu.getMenuId() == 0) {
                jt.setOpen(true);
            }
            jsonList.add(jt);
        }

        return jsonList;
    }

    @Override
    public List<SysMenu> findAll() {
        return baseDaoImpl.queryForList(SQL_NAMESPACE + FIND_ALL_MENU, null, SysMenu.class);
    }

    /**
     * 获取所有菜单项数据
     * 
     * @param fromCache 是否自缓存中取用
     * @return 所有菜单项数据
     */
    @SuppressWarnings("unchecked")
    public List<SysMenu> findAll(boolean fromCache) {
        // 假如缓存中存在，则从缓存中获取，否则直接从数据库查找
        if (fromCache) {
            List<SysMenu> menulist = (List<SysMenu>) sysMenuChildCache.get(ALL_MENU_CODE).get();
            if (null == menulist) {
                menulist = baseDaoImpl.queryForList(SQL_NAMESPACE + FIND_ALL_MENU, null, SysMenu.class);
                sysMenuChildCache.put(ALL_MENU_CODE, menulist);
            }
            return menulist;
        } else {
            return baseDaoImpl.queryForList(SQL_NAMESPACE + FIND_ALL_MENU, null, SysMenu.class);
        }
    }

    @Override
    public SysMenu find(SysMenu sysMenu) {
        return baseDaoImpl.find(sysMenu.getClass(), sysMenu);
    }

    @Override
    public SysMenu findById(Long id) {
        if (id != null)
            return baseDaoImpl.find(SysMenu.class, new SysMenu(id));
        else {
            return null;
        }
    }

    /**
     * 分页查询实现
     */
    @SuppressWarnings("unchecked")
    @Override
    public QueryResult<SysMenu> listByPage(Pager<Map> pager) {
        QueryResult<SysMenu> result = null;
        if (pager != null) {
            pager.getQueryParam().put("orderBy", pager.getOrderBy());
            pager.getQueryParam().put("orderType", pager.getOrderType());
            int totalCount = 0;
            Map<String, Object> count = baseDaoImpl.queryForMap(SQL_NAMESPACE + SQL_COUNT, pager.getQueryParam());

            if (count.get(SQL_COUNT) != null) {
                totalCount = (Integer) count.get(SQL_COUNT);
            }

            // 把检索参数置回返回参数中
            result = new QueryResult<SysMenu>(totalCount, pager.getPageSize(), pager.getPageNumber());

            // 如果总数大于0，继续查询
            if (totalCount != 0) {
                if (pager.getQueryParam() == null) {
                    pager.setQueryParam(new HashMap());
                }

                // 设置检索开始行
                pager.getQueryParam().put("startIndex", result.getIndexNumber());
                // 设置检索数据件数
                pager.getQueryParam().put("maxCount", result.getPageSize());

                // 检索数据列表，放到返回结果对象中
                List<SysMenu> retList = baseDaoImpl.queryForList(SQL_NAMESPACE + SQL_LIST_BY_PAGE,
                        pager.getQueryParam(), SysMenu.class);

                result.setDatas(retList);
            }
        }
        return result;
    }

    @Override
    public SysMenu findByCode(String code) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("code", code);
        List<SysMenu> menulist = baseDaoImpl.queryForList(SQL_NAMESPACE + FIND_BY_CODE, param, SysMenu.class);
        if (!menulist.isEmpty()) {
            return menulist.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<SysMenu> findByStatus(String status) {
        return null;
    }

    /**
     * 判断两个菜单项的编码值是否相同
     */
    @Override
    public boolean isUnique(String newValue, String oldValue) {
        if (newValue.equals(oldValue)) {
            return true;
        }

        if (isExists(newValue)) {
            return false;
        }
        return true;
    }

    /**
     * 判断两个菜单项的名称是否相同
     */
    @Override
    public boolean isNameUnique(String newValue, String oldValue) {
        if (newValue.equals(oldValue)) {
            return true;
        }

        if (isNameExists(newValue)) {
            return false;
        }
        return true;
    }

    /**
     * 判断当前菜单项code编码是否已经存在于数据库中
     */
    @Override
    public boolean isExists(String code) {
        if (null == code || code.isEmpty()) {
            logger.warn("[isExists]Parameter roleName is null or empty.");
            return false;
        }
        SysMenu sysMenu = this.findByCode(code);
        if (sysMenu == null) {
            return false;
        }
        return true;
    }

    /**
     * 判断当前菜单项名称是否已经存在于数据库中
     */
    public boolean isNameExists(String name) {
        if (null == name || name.isEmpty()) {
            logger.warn("[isExists]Parameter roleName is null or empty.");
            return false;
        }
        List<SysMenu> list = this.findByName(name);
        if (list == null || list.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 查出所有作为父菜单项的数据
     */
    @Override
    public List<SysMenu> findParentMenu() {
        List<SysMenu> menulist = baseDaoImpl.queryForList(SQL_NAMESPACE + FIND_PARENT, null, SysMenu.class);
        return menulist;
    }

    /**
     * 查出指定名称的菜单项数据
     */
    @Override
    public List<SysMenu> findByName(String name) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("name", name);
        List<SysMenu> menulist = baseDaoImpl.queryForList(SQL_NAMESPACE + FIND_BY_NAME, param, SysMenu.class);
        return menulist;
    }

    @Override
    public List<SysMenu> findChildByCode(String code) {
        return findChildByCode(code, this.getSysMenuChildCache());
    }

    @Override
    public List<SimpleJsonTree> findJsonChildByCode(String code) {
        return changeToJsonTree(findChildByCode(code));
    }

    @SuppressWarnings("unchecked")
    public List<SysMenu> findChildByCode(String code, Cache cache) {
        List<SysMenu> menulist = null;
        if (cache.get(code) != null) {
            menulist = (List<SysMenu>) cache.get(code).get();
        } else {
            SysMenu sysMenu = findByCode(code);
            if (null == sysMenu) {
                return null;
            }
            if (null == sysMenu.getMenuId()) {
                return null;
            }
            menulist = findByParentId(sysMenu.getMenuId());
            cache.put(code, menulist);
        }
        if (null != menulist && menulist.size() != 0) {

            for (SysMenu menu : menulist) {
                if (menu.getIsParent()) {
                    menu.setChildrenSysMenus(findChildByCode(menu.getMenuCode(), cache));
                }
            }

        }
        return menulist;
    }

    /**
     * 刷新当前菜单项数据的缓存（缓存对象中存储的为以父菜单项code编码为key，对应的子菜单项为value的缓存内容）
     */
    @SuppressWarnings("unchecked")
    public void refreshSysMenuCache() {
        sysMenuChildCache.clear();
        List<SysMenu> sysMenuList = findAll();
        // 重新将所有菜单数据list放置到缓存对象中
        sysMenuChildCache.put(ALL_MENU_CODE, sysMenuList);
        for (SysMenu sysMenu : sysMenuList) {
            if (sysMenu.getMenuId() == null) {
                continue;
            } else if (sysMenu.getParentId() == null) {
                continue;
            } else {// 对属于父菜单项的数据的操作
                SysMenu parentSysMenu = findById(sysMenu.getParentId());
                String parentCode = null;
                if(null !=parentSysMenu ){
                    parentCode = parentSysMenu.getMenuCode();
                    // 若缓存中不存在key为该父菜单项code编码的缓存内容，则新添；否则先取出再更新后，重新存入
                    if (sysMenuChildCache.get(parentCode) == null) {
                        List<SysMenu> childList = new ArrayList<SysMenu>();
                        childList.add(sysMenu);
                        // getAllChild(sysMenu.getMenuId(), allChildCache);
                        sysMenuChildCache.put(parentCode, childList);
                    } else {
                        List<SysMenu> childList = (List<SysMenu>) sysMenuChildCache.get(parentCode).get();
                        childList.add(sysMenu);
                        sysMenuChildCache.evict(sysMenu.getParentId());
                        sysMenuChildCache.put(parentCode, childList);
                    }
                }
              
            }
        }
    }

    public Cache getSysMenuChildCache() {
        return sysMenuChildCache;
    }

    public void setSysMenuChildCache(Cache sysMenuChildCache) {
        this.sysMenuChildCache = sysMenuChildCache;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        refreshSysMenuCache();
    }

    @Override
    public Number persist(SysMenu sysMenu) {
        return baseDaoImpl.persist(sysMenu);
    }

    @Override
    public int merge(SysMenu sysMenu) {
        return baseDaoImpl.merge(sysMenu);
    }

    @Override
    public int remove(SysMenu sysMenu) {
        return baseDaoImpl.remove(sysMenu);
    }
}
