package com.guangxunet.shop.base.util;
/** 
* @author 作者 E-mail: ShuQiang
* @version 创建时间：2017年6月6日 下午10:08:06 
* 类说明 系统中的常量
* Created by King on 2017/06/06.
*/
public class BidConst {
	/**
     * 默认的管理员名称和密码
     */
    public static final String DEFAULT_ADMIN_NAME = "admin";
    public static final String DEFAULT_ADMIN_PASSWORD = "1111";
    
    /**
     * 短信验证码有效期120s
     */
    public static final long SEND_VERIFY_EXPIRY_DATE = 120;
    
    /**
     * 短信验证码发送时间间隔60s
     */
    public static final long SEND_VERIFY_INTERVAL = 60;

    /**
     * 获取图片路径前缀
     */
    public static final String GET_IMAGES_PATH = "http:106.15.46.108:8081/";
}
