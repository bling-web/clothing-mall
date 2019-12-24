package com.tim.mall.web.alipay;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101600697197";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCmbttJHAd/xLsH5vYH/ZWQ0GLkuZPtZQ7yfMEAtg1AyqfoxG9E3R1APPiAg4eiHG/NR0+qLaMxHd2DQbUKPdc3j8M3InBHTSdzTzN4iZqP/o7/EmsKhzEvd8TOHUuRwaQOGFqgwaqtXyw0MI5tlKjHjrl0riInUE5tzMnxnKyJBPBO5ZlLJZkDMpQ2jSl1a9egzQqWohI6KRCyrDWVcxddEBWQhWrZ9Pxxy5L+CIZFiVg9wl6aruhMZB4Pz0v3REzGPis1+WhCAJ4umpNHAG8PQk90EKVegypQzqbRFoMzvRw34aNXIhrbvNEufUz1Q+bJVtqDMc9P0AHfPVKaSw4TAgMBAAECggEAXnM67tqBRvkWSv2/UfmPD4ezF6pZULexK9uwi/7EtqhLccVIgAQbvYDcZdI+/ri0mgKGK0WK/yy7sOlxHEbMBDQJzRg2L8CRStb/lsBAfscvz4FcTbU7LH0DqxTk9eq2d2lKxUAi5BDGvTdxHK7ouZc9mAHaUAU6/QTi9cK8B2VhG+67CFmNcP3dff9/Jp13xa5IIxYI07Wv7QBGnggKqxOdkmdqxU9hK15kfxB7vQbMbEFH1wFBRS4Opx9y5yFWOFS18I2MBw6++KcuLsTOzbMP40hPUAPZI2o1IaJNzjUCKrmqgPrN8dJh1jtRqs5TRJXHTzfiTDvyoraIKVuBwQKBgQDwUDF+dfeaAbnFddwRxHm5kyG0J9M249Dx0bkdo50Apvyu4lV52M1TFuvjEAEqTuS3HSmdL6QKw2cXqSygtTUE7wEh8OvmzrfTIP5zHoQl40yr9KS1shsuP91KcqaHvlqe5shMJ4CaY9zHKoRCF447p0578+alfOlgfo4G/OHvBwKBgQCxTBJiXolrk95b6hEXbcn8VOHsM64e8iKSY0naHh5GYGfP7KcDQPYO0myZ3bHu5UerLu34Co9Z8Gf9uE+Pn/jxNgqNjmBK9XC6s+u0FBczu+NfnP2RH6MBzE7bw6bi82kYRfMIswkyoPOsH9sISnbaFXh0xZgGRzYnJCjcReLZlQKBgCPqqGsRMI95AI3tnpWdOuAsiFYHub6zb/Akezld0cJeMtfkhgfvCkkH3PuT0cjD/9UKa8TNcQQWzlqv8zaVvrXdDttGycs2TC0m+kC0Z+XNwhDVQKTTWNKhwu40koUpQxwKvBb9tG/eywmNZjUGlkB+MfG+s1M6hxrf71RGCXKTAoGAHYMZKFCuNT8G62vZ7nly/tKuTKAJotMRWCa421raFULk5OJypoMAslTqiO4zOR3nQSJtsDUpyYHP/ZbZf4HUcWpadVe2xaN3YUyjtnUFq7Hh1j+OocKPYrQ8iC5YMDzuSIEURGVw6YGLLiKbVhT3FbQvOdjORnVCGV/WgVwMt8UCgYBYtwKvW1xqXghL8nXwsw/456qxZkHXxdzlffb5H2fCdG6/mtof1DMgpoFU9OF745x8+qJFw0zFw2mgX1fxlgWQJz7TyH0jUnulKLYHU/+Q6c1a0+svpr6JXALr8m3cgXyPNOHZN48zIa9VzPcXgyGzH4AMqtzAeEjpaVRLUYMoFQ==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjdvLKXLYqdvbIdNkhgbzWfYSRXwNRqkHKI/+bRuiuMTrAyqIrfDG1vDzAXWf+uPRsW03H24hyXV5W4fPw2zVqxw/pctXkFONGKR5Nx/93LORuwi56F7E3gaXi/XazQI6QxR7q9yJq9TG8I1lUU3zmKqiYVGlhmIsxDkYN4bvUbi0KKZUexHFn4wZ1uEt9n0j9KcCG+Y/CGlsdRQyTDJZ0YzJIeH3Z7FfdnnrMpZCud1gXXcRxENX3/OUMJmgW/3her0cw1/mXAtIN7peDRVf36Q4hKGLdWumHNggHiXhxl10eb3zJhQiaVR3iVHsswsIhia4Fku9HwE6hxsGNdYIyQIDAQAB";

    // 服务器异步通知页面路径,就是支付成功之后需要通知的地址  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8885/alipayNotifyNotice";

    // 页面跳转同步通知页面路径,支付成功后跳转的页面,需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8885/alipayReturnNotice";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

