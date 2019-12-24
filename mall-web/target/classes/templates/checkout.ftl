<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <script>
            function verify() {
                var name=document.getElementById("name");
                var str1=name.value;
                var phone=document.getElementById("phone");
                var str2=phone.value;
                var detailAddress=document.getElementById("detailAddress");
                var str3=detailAddress.value;
                var symbol=document.getElementById("symbol");
                var test=symbol.value;
                if(str1==test){
                    document.getElementById("name_err").innerHTML="收货人名称不能为空!"
                    return false;
                }else if(str2==test){
                    document.getElementById("phone_err").innerHTML="收货人手机号不能为空!"
                    return false;
                }else if(str3==test) {
                    document.getElementById("detailAddress_err").innerHTML = "收货地址不能为空!"
                    return false;
                }else{
                    return true;
                }
            }
    </script>

</head>

<#include "common/page_start.ftl">

<body>

<#include "common/main_menu_index.ftl">



<!--================Home Banner Area =================-->
<section class="banner_area">
    <div class="banner_inner d-flex align-items-center">
        <div class="container">
            <div class="banner_content text-center">
                <h2>Shopping Cart</h2>
                <div class="page_link">
                    <a href="/index">Home</a>
                    <a href="/cart">Cart</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!--================End Home Banner Area =================-->

<!--================Checkout Area =================-->
<section class="checkout_area section_gap">
    <div class="container">
        <div class="billing_details">
            <div class="row">
                <div class="col-lg-8">
                    <h3>收货地址详情</h3>
                    <form class="row contact_form" action="/pay" method="post" onsubmit="return verify()">
                        <div class="col-md-6 form-group p_star">
                            <input id="name" type="text" class="form-control"  name="name" placeholder="请输入收货人姓名" value="${shipping.name!""}">
                            <input id="symbol" type="hidden" class="form-control"  name="symbol" >
                            <a style="color: red" href="#" id="name_err"></a>
                        </div>
                        <div class="col-md-6 form-group p_star">
                            <input  id="phone" type="text" class="form-control" name="phone" placeholder="请输入联系电话" value="${shipping.phone!""}">
                            <a style="color: red" href="#" id="phone_err"></a>
                        </div>
                        <div class="col-md-12 form-group">
                            <input  id="detailAddress" type="text" class="form-control" name="detailAddress" placeholder="请输入收货地址" value="${shipping.detailAddress!""}">
                            <a style="color: red" href="#" id="detailAddress_err"></a>
                        </div>

                        <div class="col-md-12 form-group">
                            <div class="creat_account">
                                <h3>备注信息</h3>
                            </div>
                            <textarea class="form-control" name="remark" id="message" rows="1" placeholder="额外备注信息"></textarea>
                        </div>

                </div>
                <div class="col-lg-4">
                    <div class="order_box">
                        <h2>你的订单</h2>
                        <ul class="list">
                            <li>
                                <a href="#">产品
                                    <span>价格*数量</span>
                                </a>
                            </li>
                             <#list checktout_cart as cart >
                            <li>
                                <a href="#">${cart.productName!""}
                                    <span class="after">×${cart.quantity!""}</span>
                                    <span class="last">${cart.productPrice!""}</span>
                                </a>
                            </li>
                            </#list>
                        </ul>
                        <ul class="list list_2">
                            <li>
                                <a href="#">总价
                                    <span>$${sum!}</span>
                                    <input type="hidden" name="payment" value="${sum!}">
                                </a>
                            </li>
                            <li>
                                <a href="#">快递费
                                    <span>$0.0</span>
                                    <input type="hidden" name="pastage" value="0">
                                </a>
                            </li>
                            <li>
                                <a href="#">最终价格
                                    <span>$${sum!}</span>
                                </a>
                            </li>
                        </ul>
                        <div class="payment_item">
                            <div class="radion_btn">
                                <label for="f-option5">请用支付宝支付</label>
                            </div>
                        </div>
                        <div class="creat_account">
                            <input type="checkbox" id="f-option4" name="selector">
                            <label for="f-option4">我已经阅读并且接受 </label>
                            <a href="#">平台支付细则声明</a>
                        </div>
                        <input type="submit" class="main_btn" value="付款">
                        </form>

                    </div>

                </div>
            </div>
        </div>
    </div>
</section>
<!--================End Checkout Area =================-->

<#include "common/footer.ftl">

<#include "common/page_end.ftl">


</body>

</html>