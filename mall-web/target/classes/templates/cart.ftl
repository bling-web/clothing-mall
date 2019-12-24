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
                <h2>购物车</h2>
                <div class="page_link">
                    <a href="#">Home</a>
                    <a href="#">Cart</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!--================End Home Banner Area =================-->

<!--================Cart Area =================-->
<section class="cart_area">
    <div class="container">
        <div class="cart_inner">
            <div class="table-responsive">
                <form action="/checkout" method="post" >
                <table class="table" >
                    <thead>
                    <tr>
                        <td >是否选择</td>
                        <td >产品</td>
                        <td></td>
                        <td >价格</td>
                        <td >数量</td>
                    </tr>
                    </thead>
                        <tbody>
                            <#list carts as cart >
                             <tr>
                                 <td>
                                     <input type="checkbox" name="checkbox" value="${cart.id!}" class="ace">
                                 </td>
                                 <td>
                                     <div class="media">
                                         <div class="d-flex">
                                             <img width="115" height="135" src="${cart.productImg!}" alt="">
                                         </div>
                                         <div class="media-body">
                                             <p>${cart.productName!}</p>
                                         </div>
                                     </div>
                                 </td>
                                 <td>
                                     <input type="hidden" id="productPrice" name="productPrice" value="${cart.productPrice!}">
                                 </td>
                                 <td>
                                     <h5>$${cart.productPrice!}</h5>
                                 </td>
                                 <td>
                                     <div class="product_count">
                                         <input type="hidden" name="help" value="${cart.id!}">

                                         <input type="text" name="quantity" id="${cart.id!}" maxlength="12" value="${cart.quantity!}" class="input-text qty">
                                         <button onclick="var result = document.getElementById('${cart.id!}'); var sst = result.value; if( !isNaN( sst )) result.value++;return false;"
                                                 class="increase items-count" type="button">
                                             <i class="lnr lnr-chevron-up"></i>
                                         </button>
                                         <button onclick="var result = document.getElementById('${cart.id!}'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 0 ) result.value--;return false;"
                                                 class="reduced items-count" type="button">
                                             <i class="lnr lnr-chevron-down"></i>
                                         </button>
                                     </div>
                                 </td>

                                 <td>
                                     <a class="gray_btn" href="/deleteCart?id=${cart.id!}">删除商品</a>
                                 </td>
                             </tr>
                            </#list>
                            <tr >
                                <td>
                                    <a  href="/updateCart" class="gray_btn" >更新购物车</a>
                                </td>

                        <td>
                            <a class="gray_btn" href="/index">继续购物</a>
                        </td>
                        <td>

                        </td>
                        <td>
                            <div class="cupon_text">
                                <input type="submit" class="main_btn" value="结算" ></input>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                </form>
            </div>
        </div>
    </div>
</section>
<!--================End Cart Area =================-->

<#include "common/footer.ftl">

<#include "common/page_end.ftl">

</body>

</html>