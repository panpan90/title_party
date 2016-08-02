/*
 * Copyright 2013 sohu.com All right reserved. This software is the
 * confidential and proprietary information of sohu.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with sohu.com.
 */
package com.sohu.mrd.netty;
import java.util.Map;
/**
 * 类Handler.java的实现描述：TODO 类实现描述 
 * @author hongfengwang 2013-10-18 上午10:21:10
 */
public interface Handler {
    public String doGet(Map<String, String> map);
    public String doPost(Map<String, String> map,String content);
}
