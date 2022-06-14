package com.ceco.common.exception;


public class ErrCode {
	//ERROR 返回编码
	public static final Integer ERR_CODE_0 = 0;
	public static final String ERR_MSG_SUCC = "操作成功";

	public static final Integer ERR_CODE_1 = 1;

	public static final Integer ERR_CODE_UNKNOWN = -200;
	public static final String ERR_MSG_UNKNOWN = "不知道的错误";

	public static final Integer ERR_CODE_CONNECT = -400;
	public static final String ERR_MSG_CONNECT = "网络不稳定导致连接错误，请重试！";

	public static final Integer ERR_CODE_100001 = 100001;
	public static final String ERR_MSG_100001 = "添加记录失败!";

	public static final Integer ERR_CODE_100002 = 100002;
	public static final String ERR_MSG_100002 = "查询记录失败!";

	public static final Integer ERR_CODE_100003 = 100003;
	public static final String ERR_MSG_100003 = "删除记录失败!";

	public static final Integer ERR_CODE_100004 = 100004;
	public static final String ERR_MSG_100004 = "更新记录失败!";

	public static final Integer ERR_CODE_100005 = 100005;
	public static final String ERR_MSG_100005 = "您没有权限操作!";

	public static final Integer ERR_CODE_100006 = 100006;
	public static final String ERR_MSG_100006 = "参数不完整!";


	public static final Integer ERR_CODE_100007 = 100007;
	public static final String ERR_MSG_100007 = "接口调用错误!";

	public static final Integer ERR_CODE_100008 = 100008;
	public static final String ERR_MSG_100008 = "接口未知异常用!";

	public static final Integer ERR_CODE_100009 = 100009;
	public static final String ERR_MSG_100009 = "提交申请失败!";

	public static final Integer ERR_CODE_100010 = 100010;
	public static final String ERR_MSG_100010 = "数据存在异常，请检查数据!";
	//XXX相关错误编码 编号依此类推


}
