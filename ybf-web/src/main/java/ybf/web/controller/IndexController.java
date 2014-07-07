/**
 * SUNING APPLIANCE CHAINS.
 * Copyright (c) 2012-2012 All Rights Reserved.
 */
package yb.snf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br> Controller
 * 〈功能详细描述〉跳转index页面
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("index")
    public String index() {
        return "index.ftl";
    }

}
