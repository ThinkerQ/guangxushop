package springtest;

import java.util.HashMap;
import java.util.Map;


import org.apache.http.HttpResponse;

import com.guangxunet.shop.base.util.HttpUtils;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月25日 下午2:12:01 
* 类说明 调用阿里云新闻接口
*/
public class NewTest {
	public static void main(String[] args) {
	    String host = "http://jisunews.market.alicloudapi.com";
	    String path = "/news/get";
	    String method = "GET";
	    String appcode = "0321917d14054197ad4eb30e26f8cc2d";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("channel", "%E5%A4%B4%E6%9D%A1");
	    querys.put("num", "10");
	    querys.put("start", "0");


	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	System.out.println(response.toString());
	    	//获取response的body
	    	//System.out.println(EntityUtils.toString(response.getEntity()));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}
