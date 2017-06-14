package springtest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.filter.config.ConfigTools;
import com.guangxunet.shop.base.service.ILogininfoService;
import com.guangxunet.shop.base.util.LoggerUtil;

/**
 * Created by King on 2017/06/06.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class VelocityTest {
    @Autowired
    private ILogininfoService iLogininfoService;
    
    @Test
	public void testinitAdmin() throws Exception {
    	iLogininfoService.initAdmin();
    	System.out.println("====end===============");
	}
    
    /**
     * 使用德鲁伊生成加密字符串
     * @throws Exception
     */
    @Test
    public void testXxx() throws Exception {
        try {
			String admin = ConfigTools.encrypt("admin");
			System.out.println(admin);
			System.out.println(1 / 0);
			LoggerUtil.info("=============sssss");
		} catch (Exception e) {
			// TODO: handle exception
			LoggerUtil.info("=============出错"+e.getMessage());
		}
    }
    
    @Test
	public void testSelectLoginInfoById() throws Exception {
    	boolean nameExist = iLogininfoService.checkUserNameExist("admin");
    	System.out.println("nameExist="+nameExist);
    	System.out.println("====end===============");
	}

    @Test
    public void main() {
        BigDecimal decimal = new BigDecimal("100.3453");
        BigDecimal bigDecimal = decimal.setScale(3,BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal);

        StringBuffer sb = new StringBuffer(40);
        sb.append("gengshuqiang");
        sb.reverse();//字符串反转
        System.out.println(sb.toString());
        StringBuffer buffer = sb.replace(2, 7, "***");
        System.out.println(buffer.toString());

        //将GB2312转换为ISO-8859-1
        String s1 = "你好";

        try {
            String s2 = new String(s1.getBytes("GB2312"),"ISO-8859-1");
            System.out.println(s2);
            String s = new String(s2.getBytes("ISO-8859-1"), "GB2312");
            System.out.println(s);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //日期和时间
        Calendar ca = Calendar.getInstance();
        int i = ca.get(Calendar.DATE);
        int j = ca.get(Calendar.YEAR);
        System.out.println(i);
        ca.add(Calendar.DATE,5);
        System.out.println(ca.getTime().toLocaleString());

    }
}
