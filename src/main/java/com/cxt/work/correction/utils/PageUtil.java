package com.cxt.work.correction.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * <p>
 * 分页工具类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
public class PageUtil {
    public static Map<String, Object> page(String url, int pageNum, int pageSize, int count, String name) {
        int pagecount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
        int a = 0;
        if (pageNum == 1) {
            a = 1;
        } else {
            a = pageNum - 1;
        }
        int b = 0;
        if (pageNum == pagecount) {
            b = pagecount;
        } else {
            b = pageNum + 1;
        }
        String first = "<a href=" + url + "?cpage=1&name=" + name + ">首页</a>";
        String prev = "<a href=" + url + "?cpage=" + a + "&name=" + name + ">上一页</a>";
        String next = "<a href=" + url + "?cpage=" + b + "&name=" + name + ">下一页</a>";
        String last = "<a href=" + url + "?cpage=" + pagecount + "&name=" + name + ">尾页</a>";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageNum", pageNum);
        map.put("count", count);
        map.put("pagecount", pagecount);
        map.put("page", first + " " + prev + " " + next + " " + last);
        return map;
    }


    public static Map<String, Object> pageMap(String url, int pageNum, int pageSize, int count, Map<String, Object> paramMap) {
        int pagecount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
        int a = 0;
        if (pageNum == 1) {
            a = 1;
        } else {
            a = pageNum - 1;
        }
        int b = 0;
        if (pageNum == pagecount) {
            b = pagecount;
        } else {
            b = pageNum + 1;
        }

        String param = "";
        Set<String> keySet = paramMap.keySet();
        for (String key : keySet) {
            param += "&" + key + "=" + paramMap.get(key);
        }

        String first = "<a href=" + url + "?cpage=1" + param + ">首页</a>";
        String prev = "<a href=" + url + "?cpage=" + a + param + ">上一页</a>";
        String next = "<a href=" + url + "?cpage=" + b + param + ">下一页</a>";
        String last = "<a href=" + url + "?cpage=" + pagecount + param + ">尾页</a>";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageNum", pageNum);
        map.put("count", count);
        map.put("pagecount", pagecount);
        map.put("page", first + " " + prev + " " + next + " " + last);
        return map;
    }

}

