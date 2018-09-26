package com.health.common;

/**
 * @author Henry
 * @date 2018/7/8 20:47
 */
public class Constant{
	
	public static String STR_SUCCESS = "success";
	public static String STR_ERROR = "error";
	public static String STR_EMPTY = "";
	public static String STR_EXCEPTION = "exception";
	public static String STR_NEW_LINE = "\n";
	public static String STR_VERSION = "Api Version";
	public static String ERROR_SYSTEM = "系统出错了";
	
	public static String PAY_OUT_UNIT = "元起";
	public static String PAY_TELEPHONE_UNIT = "元每20分钟";
	public static String PAY_ONLINE_UNIT = "元每20分钟";
	public static int INT_SUCCESS = 200;
	
	public static int UNKNOWN = 0;
	public static int VALID = 1;
	
	public static Integer TYPE_USER_APP = 1;
	public static Integer TYPE_USER_WEB = 2;
	public static Integer TYPE_USER_DOC = 3;
	
	public static Integer LOGIN_TYPE_CODE = 1;
	public static Integer LOGIN_TYPE_PWD = 2;
	
	public static Integer REGISTER_STATUS_UNKNOWN = 0;
	public static Integer REGISTER_STATUS_ACCESS = 1;
	public static Integer REGISTER_STATUS_REJECT = 2;
	
	public static Integer AUDIT_STATUS_UNKNOWN = 0;
	public static Integer AUDIT_STATUS_ACCESS = 1;
	public static Integer AUDIT_STATUS_REJECT = 2;
	public static Integer AUDIT_STATUS_EXPIRE = 3;
	
	public static Long DEFAULT_VERSION = 1L;
	
	public static int APP_ID = 1400112776;
	public static String APP_KEY = "f4dd64024ff5e944d2f6460ad2ef0fea";
	public static int SMS_TYPE = 0;
	public static String NATIONAL_CODE = "86";
	public static int TEMPLATE_ID_CODE = 158788;
	public static int TEMPLATE_ID_REGISTER_SUCCESS = 160509;
	public static int TEMPLATE_ID_APPLY = 160621;
	public static int TEMPLATE_ID_APPLY_ACCESS = 160507;
	public static int TEMPLATE_ID_APPLY_REJECT = 160622;
	public static int TEMPLATE_ID_DOCTOR_REGISTER = 165394;
	public static int TEMPLATE_ID_DOCTOR_REGISTER_ACCESS = 165395;
	public static int TEMPLATE_ID_DOCTOR_REGISTER_REJECT = 165417;
	public static String SMS_SIGN = "上海宜起实业有限公司";
	
	public static Long CODE_EXPIRE_TIME = 300000L;
	
	
	public static final String TOKEN_HEADER_KEY = "X-Auth-Token";
	public static final String MAGIC_KEY = "obfuscate";
	public static final String SPLIT = ":";
}
