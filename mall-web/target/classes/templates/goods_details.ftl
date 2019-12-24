<!doctype html>
<html lang="en">

<#include "common/page_start.ftl">

<body>

<#include "common/main_menu.ftl">
<br>
<br>
<!--================Single Product Area =================-->
<div class="product_image_area">
    <div class="container">
        <div class="row s_product_inner">
            <div class="col-lg-6">
                <div class="s_product_img">
                    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img class="d-block w-100" src="${good.imageUrl}" alt="First slide">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-5 offset-lg-1">
                <div class="s_product_text">
                    <h3>${good.name}</h3>
                    <h2>$${good.sellingPrice}</h2>
                    <ul class="list">
                        <li>
                            <a class="active" href="#">
                                <span>分类</span> :
                                 <#if good.categoryId==0>女装</#if>
                                 <#if good.categoryId==1>男装</#if>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span>是否有库存</span> : 有</a>
                        </li>
                    </ul>
                    <p>${good.detail}</p>
                    <form action="/cart" method="post">
                        <input type="hidden" name="productPrice" value="${good.sellingPrice!}">
                        <input type="hidden" name="productName" value="${good.name!}">
                        <input type="hidden" name="productId" value="${good.id!}">
                        <input type="hidden" name="productImg" value="${good.imageUrl!}">
                    <#--数量加减-->
                            <div class="product_count">
                                <input type="text" name="quantity" id="sst" maxlength="12" value="1" title="Quantity:" class="input-text qty">
                                <button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst )) result.value++;return false;"
                                        class="increase items-count" type="button">
                                    <i class="lnr lnr-chevron-up"></i>
                                </button>
                                <button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 0 ) result.value--;return false;"
                                        class="reduced items-count" type="button">
                                    <i class="lnr lnr-chevron-down"></i>
                                </button>
                            </div>
                    <#--增加购物车-->
                        <div class="card_area">
                            <input  class="main_btn" type="submit" value="增加购物车">
                            <a class="icon_btn" href="#">
                                <i class="lnr lnr lnr-diamond"></i>
                            </a>
                            <a class="icon_btn" href="#">
                                <i class="lnr lnr lnr-heart"></i>
                            </a>
                        </div>


                    </form>


                </div>
            </div>
        </div>
    </div>
</div>
<!--================End Single Product Area =================-->

<!--================Product Description Area =================-->
<section class="product_description_area">
    <div class="container">
        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item">
                <a class="nav-link" id="profile-tab" data-toggle="tab" href="#" role="tab" aria-controls="profile" aria-selected="false">描述</a>
            </li>
        </ul>
        </ul>
        <div class="tab-content" id="myTabContent">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td>
                                <h5>宽</h5>
                            </td>
                            <td>
                                <h5>${good.width}mm</h5>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h5>高</h5>
                            </td>
                            <td>
                                <h5>${good.high}mm</h5>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h5>长</h5>
                            </td>
                            <td>
                                <h5>${good.length}mm</h5>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h5>重量</h5>
                            </td>
                            <td>
                                <h5>${good.weight}g</h5>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h5>质量检查</h5>
                            </td>
                            <td>
                                <h5>经过检验</h5>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h5>商品简称</h5>
                            </td>
                            <td>
                                <h5>${good.shortName}</h5>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h5>商品库存</h5>
                            </td>
                            <td>
                                <h5>${good.basicStock}</h5>
                            </td>
                        </tr>
                        </tbody>
                    </table>
        </div>
    </div>
</section>
<!--================End Product Description Area =================-->

<#include "common/footer.ftl">

<#include "common/page_end.ftl">

</body>

</html>