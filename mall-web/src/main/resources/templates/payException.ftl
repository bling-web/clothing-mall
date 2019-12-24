<!doctype html>
<html lang="en">

<#include "common/page_start.ftl">

<body>

<#include "common/main_menu.ftl">

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
                    <h3>返回异常,请勿担心,系统会记录此订单,并进行处理</h3>
                        <div class="col-md-6 form-group p_star">
                           <a href="/index">返回主页面</a>
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