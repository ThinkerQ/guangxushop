package springtest;

import org.junit.Test;

import com.guangxunet.shop.base.util.PhoneFormatCheckUtils;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月15日 下午11:07:36 
* 类说明 
*/
public class PhoneTest {
	
	/*
	 * 验证手机号
	 */
	 @Test
		public void testinitAdmin() throws Exception {
	    	System.out.println(PhoneFormatCheckUtils.isPhoneLegal("18211784443"));
		}
}
