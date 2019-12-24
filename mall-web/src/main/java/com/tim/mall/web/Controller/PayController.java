package com.tim.mall.web.Controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.tim.mall.model.User;
import com.tim.mall.web.Model.Cart;
import com.tim.mall.model.MyOrder;
import com.tim.mall.web.Model.Shipping;
import com.tim.mall.web.Service.CartService;
import com.tim.mall.service.MyOrderService;
import com.tim.mall.web.Service.OrderItemService;
import com.tim.mall.web.Service.ShippingServcie;
import com.tim.mall.web.alipay.AlipayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping
@EnableTransactionManagement
@Slf4j
public class PayController {

    @Resource
    private ShippingServcie shippingServcie;

    @Resource
    private MyOrderService myOrderService;

    @Resource
    private OrderItemService orderItemService;


    @Resource
    private CartService cartService;
    private int count=0;
    //定义订单编号的位数
    private static final int digit=20;
    //该此交易的订单编号
    private static String orderNo="";

    @RequestMapping(value = "pay",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String alipay(Shipping shipping, HttpSession session) throws AlipayApiException {
        //接收地址信息存储到送货地址表
        User current_user = (User) session.getAttribute("current_user");
        Integer id = shippingServcie.setShipping(shipping, current_user);
        //生成订单编号,当前时间+当前用户id+序列号
        orderNo = myOrderService.createOrderNo(current_user,count);
        //从session对象中接收付款信息,商品,价格这些,并加入到order对象中
        MyOrder myOrder = myOrderService.setOrder(session, orderNo, current_user.getId(), id, shipping.getRemark());
        //存储订单
        myOrderService.insert(myOrder);
        //同时也要存储order_item,标识每个订单各个商品的出售价钱
        orderItemService.insertOrder_item(session,orderNo);
        //设置交易内容
        String result = setAlipay(session, orderNo, shipping);
        return result;
    }

    @RequestMapping("alipayReturnNotice")
    public String alipayReturnNotice(HttpServletRequest  request, Model model,HttpSession session) throws Exception {
        //输出日志,支付成功
        log.info("支付宝支付成功,进入同步通知页面...");
        //把支付宝传回的key,value参数放到一个map中,用于验签
        HashMap<String, String> helpMap = RequestParamaterToMap(request);
        //进行验签
        boolean success = AlipaySignature.rsaCheckV1(helpMap, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        if(success){
            //获取商务平台订单编号,支付宝订单编号,交易金额等
            String out_trade_no=new String(helpMap.get("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            String trade_no=new String(helpMap.get("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            String total_amount=new String(helpMap.get("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            if(total_amount.charAt(total_amount.length()-1)=='0'){
                total_amount=total_amount.substring(0,total_amount.length()-1);
            }
            // 处理具体的业务逻辑,验证该编号是否正确,设置订单状态,设置交易流水(这里暂时不设置),同时删除购物车中该订单中的商品
            if(out_trade_no.equals(orderNo)&&total_amount.equals(String.valueOf(session.getAttribute("sum")))){
                //编号,付款金额正确,此次支付成功,设置订单状态已付款
                myOrderService.updateStutes(orderNo,20,new Date());
                //删除购物车中商品
                cartService.deleteGoodsFromCart(session);
                //减少商品库存
                cartService.updateGoodsQuantity(session);
                //返回商品主页面
                return "redirect:index";
            }else{
                log.info("订单编号,金额异常");
                return "payException";
            }
        }else{
            log.info("验签失败");
            return "payException";
        }
    }

    @RequestMapping("alipayNotifyNotice")
    public String alipayNotifyNotice(HttpServletRequest  request, Model model,HttpSession session) throws Exception {
        //输出日志,支付成功
        log.info("支付宝支付成功,进入异步通知页面...");
        //把支付宝传回的key,value参数放到一个map中,用于验签
        HashMap<String, String> helpMap = RequestParamaterToMap(request);
        //进行验签
        boolean success = AlipaySignature.rsaCheckV1(helpMap, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        if(success){
            //获取商务平台订单编号,支付宝订单编号,交易金额等
            String out_trade_no=new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            String total_amount=new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            String trade_status=new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
            if(total_amount.charAt(total_amount.length()-1)=='0'){
                total_amount=total_amount.substring(0,total_amount.length()-1);
            }
            // 处理具体的业务逻辑,验证该编号是否正确,设置订单状态,设置交易流水(这里暂时不设置),同时删除购物车中该订单中的商品
            if(out_trade_no.equalsIgnoreCase(orderNo)&&total_amount==session.getAttribute("sum")){
                if(trade_status.equals("TRADE_FINISHED")){
                    //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                    //更新该订单状态,比如完成时间等,本项目中不含实际退款等操作,暂时不做处理
                    return "redirect:index";
                }else if(trade_status.equals("TRADE_SUCCESS")){
                    //付款完成后,支付宝系统发送该交易状态通知
                    //判断是否对该订单做过处理
                    boolean update = myOrderService.ifUpdated(orderNo);
                    if(update) {
                        return "redirect:index";
                    }
                    else{
                        myOrderService.updateStutes(orderNo,20, new Date());
                        //删除购物车中商品
                        cartService.deleteGoodsFromCart(session);
                        //减少商品库存
                        cartService.updateGoodsQuantity(session);
                        return "redirect:index";
                    }
                }else return "payException";
            }else{
                log.info("订单编号,金额异常");
                return "payException";
            }
        }else{
            log.info("验签失败");
            return "payException";
        }
    }

    /**
     * 把支付宝传回的key,value参数放到一个map中,用于验签
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    private HashMap<String, String> RequestParamaterToMap(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, String[]> map = request.getParameterMap();
        Iterator<String> iterator = map.keySet().iterator();
        //新建一个map用于存储参数
        HashMap<String, String> helpMap = new HashMap<>();
        while(iterator.hasNext()){
            String name = iterator.next();
            String[] values = map.get(name);
            String value="";
            for(int i=0;i<values.length;i++){
                value += (i==values.length-1) ? values[i] : values[i] +",";
            }
            //防止出现乱码,ISO-8859-1是最原始的编码规则,向下兼容所有ASCLL码,也就是用其可以将数据正确的转换成二进制文件
            //不论该数据使用什么编码
            value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
            helpMap.put(name,value);
        }
        return  helpMap;
    }

    /**
     * 调用支付宝接口,设置相关参数
     * @param session
     * @param orderNo
     * @param shipping
     * @return
     * @throws AlipayApiException
     */
    private String setAlipay(HttpSession session, String orderNo, Shipping shipping) throws AlipayApiException {
        //1.初始化alipay,也就是主要的支付代理对象
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key,
                "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //2.设置请求参数
        AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
        alipayTradePagePayRequest.setNotifyUrl(AlipayConfig.notify_url);
        alipayTradePagePayRequest.setReturnUrl(AlipayConfig.return_url);
        //商户订单号
        String out_trade_no=orderNo;
        //付款金额
        String totalAmount=String.valueOf(session.getAttribute("sum"));
        //订单名称
        String subject="";
        List<Cart> checktout_cart = (List<Cart>) session.getAttribute("checktout_cart");
        for (Cart cart : checktout_cart) {
            subject += cart.getProductName()+" ";
        }
        //订单描述,备注
        String body=shipping.getRemark();
        //订单允许的最晚付款时间,逾期将关闭交易
        String timeoutExpire="10m";

        alipayTradePagePayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ totalAmount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+ timeoutExpire +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //支付对象执行支付页面方法,getbody返回的是一个html文本,也就是那个支付二维码页面
        return alipayClient.pageExecute(alipayTradePagePayRequest).getBody();

    }



}
