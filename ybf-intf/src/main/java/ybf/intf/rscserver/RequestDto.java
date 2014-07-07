/*
 * Copyright (C), 2002-2013, 苏宁易购电子商务有限公司
 * FileName: RequestDto.java
 * Author:   penny_gu/12072585
 * Date:     2013-11-28 下午5:13:03
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.simple.rscserver;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author penny_gu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@XStreamAlias("RequestDto")
public class RequestDto {
    @XStreamAlias("Name")
    private String name;
    
    @XStreamAlias("Age")
    private int age;

    @XStreamAlias("Test_Name")
    private String name_test;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName_test() {
        return name_test;
    }

    public void setName_test(String nameTest) {
        name_test = nameTest;
    }
    
}
