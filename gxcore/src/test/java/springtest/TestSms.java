package springtest;


import com.aliyun.mns.client.CloudAccount;  
import com.aliyun.mns.client.CloudTopic;  
import com.aliyun.mns.client.MNSClient;  
import com.aliyun.mns.common.ServiceException;  
import com.aliyun.mns.model.BatchSmsAttributes;  
import com.aliyun.mns.model.MessageAttributes;  
import com.aliyun.mns.model.RawTopicMessage;  
import com.aliyun.mns.model.TopicMessage;  
  
/**  
 * @author: py 
 * @version:2017年5月4日 下午2:57:31  
 * com.kp.test.TestNewSms.java 
 * @Desc  
 */  
public class TestSms {  
      
    /**********需要准备的参数**************/  
    public static String YourAccessId="LTAI2bGnu9lbWKE2";//需要修改  
    public static String YourAccessKeySecret="mLB2NOwUgZHcpFPNgBNyRAPMrCUbNB";//需要修改  
    //Endpoint  需要修改  
    public static String YourMNSEndpoint="https://1195938657583057.mns.cn-hangzhou.aliyuncs.com/";  
    public static String YourTopic="sms.topic-cn-hangzhou";//主题名称    选择性修改  
    public static String YourSMSTemplateCode="SMS_70545298";//短信模板code  需要修改  
    public static String YourSignName="光续云购APP";//短信签名名称， 需要修改  
    public static String phone1="18211674995";//手机号码需要修改  
    public static String phone2="13120705656";//手机号码需要修改  
    /**********需要准备的参数**************/  
      
      
    public static void main(String[] args) {  
        /** 
         * Step 1. 获取主题引用 
         */  
        CloudAccount account = new CloudAccount(YourAccessId, YourAccessKeySecret, YourMNSEndpoint);  
        MNSClient client = account.getMNSClient();  
        CloudTopic topic = client.getTopicRef(YourTopic);  
        /** 
         * Step 2. 设置SMS消息体（必须） 
         * 
         * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。 
         */  
        RawTopicMessage msg = new RawTopicMessage();  
        msg.setMessageBody("sms-message");  
        /** 
         * Step 3. 生成SMS消息属性 
         */  
        MessageAttributes messageAttributes = new MessageAttributes();  
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();  
        // 3.1 设置发送短信的签名（SMSSignName）  
        batchSmsAttributes.setFreeSignName(YourSignName);  
        // 3.2 设置发送短信使用的模板（SMSTempateCode）  
        batchSmsAttributes.setTemplateCode(YourSMSTemplateCode);  
        // 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）  
        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();  
        smsReceiverParams.setParam("code", "2323");  
        smsReceiverParams.setParam("product", getChinaDateByMM(System.currentTimeMillis()));  
        // 3.4 增加接收短信的号码  
        batchSmsAttributes.addSmsReceiver(phone1, smsReceiverParams);  
//        batchSmsAttributes.addSmsReceiver(phone2, smsReceiverParams);  
        messageAttributes.setBatchSmsAttributes(batchSmsAttributes);  
        try {  
            /** 
             * Step 4. 发布SMS消息 
             */  
            TopicMessage ret = topic.publishMessage(msg, messageAttributes);  
            System.out.println("MessageId: " + ret.getMessageId());  
            System.out.println("MessageMD5: " + ret.getMessageBodyMD5());  
        } catch (ServiceException se) {  
            System.out.println(se.getErrorCode() + se.getRequestId());  
            System.out.println(se.getMessage());  
            se.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        client.close();  
    }  
      
      
    /** 
     * 使用毫秒转换为中文日期 
     * @param tmpDateInt 
     * @return 
     */  
    public static String getChinaDateByMM(long time){  
        String ret_date = "";  
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy年MM月dd日");  
        ret_date = formatter.format(time);  
        return ret_date;  
    }  
  
}  