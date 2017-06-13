package com.guangxunet.shop.base.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月7日 下午9:38:26 
* 类说明 用户登录日志类
*/
@Setter
@Getter
@ToString
public class Iplog extends BaseDomain {
	public static final int STATE_SUCCESS = 1;//登录成功
    public static final int STATE_FAILED = 0;//登录失败
    
    private String ip;//登录Ip
    private String userName;//用户名
    private Date loginTime;//登录时间
    private int loginState;//登录状态
    private int userType;//用户类型：0前台用户 、1后台用户
}
