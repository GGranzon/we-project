package com.woniuxy.dto;

public class StatusCode {
    //自定义状态码 静态常量
    public static final int OK = 2000; //操作成功
    public static final int FAILED = 2001; //操作失败
    public static final int NULL = 2002; //没有选中删除的值
    public static final int ISNOTEMPTY = 2003; //会员已存在
    public static final int ISEMPTYDIS = 2004; //渠道商不存在
}
