package springtest;


import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;

/**生成数据库加密的密码
 * Created by King on 2016/10/10.
 */
public class DruidPassworeTest {
    @Test
    public void testXxx() throws Exception {
        String admin = ConfigTools.encrypt("admin");
        System.out.println(admin);
        String GSQ11024a = ConfigTools.encrypt("GSQ11024a");
        System.out.println(GSQ11024a);
    }

    @Test
    public void testMath(){
        System.out.println(Math.round(11.5));//12
        System.out.println(Math.round(-11.4));//-11
        System.out.println(Math.round(-11.5));//-11
        System.out.println(Math.round(-11.6));//-12
        System.out.println(Math.round(-0.5));//0
    }

    /**
     * final修饰的变量是引用不能变还是内容不能变？
     * 是
     */
    @Test
    public void testFinal(){
        final StringBuffer sb = new StringBuffer("sbsb");

        System.out.println(sb.hashCode());
        System.out.println(sb.toString());
        sb.append("我变了");//内容变了
        System.out.println(sb.toString());
        System.out.println(sb.hashCode());//引用地址依然不变

        String str = "string";
        System.out.println(str);
        System.out.println(str.hashCode());
        str = "bcdddd";
        System.out.println(str);
        System.out.println(str.hashCode());
    }

    /**
     *
     */
    @Test
    public void testURl(){

    }
}
