package com.guangxunet.shop.base.service;


import com.guangxunet.shop.base.domain.Logininfo;

/**core登陆相关服务
 * Created by Administrator on 2016/9/30.
 */
public interface ILogininfoService {
    /**
     * 注册
     */
    void register(String username, String password);

    /**
     * 检查用户名是否存在
     * @param username
     * @return
     */
    boolean checkUserNameExist(String username);

    /**
     * 登陆日志
     * @param username
     * @param password
     * @param ip
     * @param userManager
     * @return
     */
    Logininfo login(String username, String password,String ip, int userManager);

    /**
     * 初始化管理员
     */
    void initAdmin();

    /**
     * 根据手机号检查用户是否存在
     * @param mobile
     * @return
     */
    boolean checkUserPhoneNumberExist(String mobile);

    /**
     * 后台登陆
     * @param username
     * @param password
     * @param ip
     * @param usertype
     * @return
     */
    Logininfo supervisorLogin(String username, String password, String ip, int userType);
}
