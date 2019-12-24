<!doctype html>
<html lang="en">
<#include "common/page_start.ftl">
<body>
<#include "common/main_menu_index.ftl">

<!--================Home Banner Area =================-->
<section class="home_banner_area">
    <div class="overlay"></div>
    <div class="banner_inner d-flex align-items-center">
        <div class="container">
            <div class="banner_content row">
                <div class="offset-lg-2 col-lg-8">
                    <h3>购买衣物为
                        <br/>万物复苏的春天</h3>
                    <p>这里有精品男装,精品女装,在这个美好的季节,相信你会不虚此行</p>
                    <#--<a class="white_bg_btn" href="#"></a>-->
                </div>
            </div>
        </div>
    </div>
</section>
<!--================End Home Banner Area =================-->

<!--================Hot Deals Area =================-->
<section class="hot_deals_area section_gap">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-6">
                <div class="hot_deal_box">
                    <img class="img-fluid" src="img/product/hot_deals/deal1.jpg" alt="">
                    <div class="content">
                        <h2>男装</h2>
                        <p>go</p>
                    </div>
                    <a class="hot_deal_link" href="/category?categoryId=1"></a>
                </div>
            </div>

            <div class="col-lg-6">
                <div class="hot_deal_box">
                    <img class="img-fluid" src="img/product/hot_deals/deal1.jpg" alt="">
                    <div class="content">
                        <h2>女装</h2>
                        <p>go</p>
                    </div>
                    <a class="hot_deal_link" href="/category?categoryId=0"></a>
                </div>
            </div>
        </div>
    </div>
</section>
<!--================End Hot Deals Area =================-->

<!--================Clients Logo Area =================-->
<#--中间的五个图标-->
<section class="clients_logo_area">
    <div class="container-fluid">
        <div class="clients_slider owl-carousel">
            <div class="item">
                <img src="img/clients-logo/c-logo-1.png" alt="">
            </div>
            <div class="item">
                <img src="img/clients-logo/c-logo-2.png" alt="">
            </div>
            <div class="item">
                <img src="img/clients-logo/c-logo-3.png" alt="">
            </div>
            <div class="item">
                <img src="img/clients-logo/c-logo-4.png" alt="">
            </div>
            <div class="item">
                <img src="img/clients-logo/c-logo-5.png" alt="">
            </div>
        </div>
    </div>
</section>
<!--================End Clients Logo Area =================-->

<!--================Feature Product Area =================-->
<#--真正的商品区-->
<section class="feature_product_area section_gap">

    <div class="main_box">
        <div class="container-fluid">
            <div class="row">
                <div class="main_title">
                    <h2>全部热销商品</h2>
                    <p>看一下有没有你喜欢的吧</p>
                </div>
            </div>
        </div>
     </div>
            <div class="row">
            <#list goods_img! as good >
            <div class="col col1">
                <div class="f_p_item">
                    <div class="f_p_img">
                        <img class="img-fluid" src="${good.imageUrl!}" alt="">
                        <div class="p_icon">
                            <a href="">
                                <i class="lnr lnr-heart"></i>
                            </a>
                            <a href="/cart?get_id=${good.id!}">
                                <i class="lnr lnr-cart"></i>
                            </a>
                        </div>
                    </div>
                    <a href="/goods_details?name=${good.name!}">
                        <h4>${good.name!}</h4>
                    </a>
                    <h5>$${good.sellingPrice!}</h5>
                </div>
            </div>
            </#list>
            </div>
            <div class="row">
                <nav class="cat_page mx-auto" aria-label="Page navigation example">
                    <ul class="pagination">
                        <li class="page-item">
                            <a class="page-link" href="#">
                                <i class="fa fa-chevron-left" aria-hidden="true"></i>
                            </a>
                        </li>
                        <li class="page-item active">
                            <a class="page-link" href="/index?page=1">01</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="/index?page=2">02</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="#">03</a>
                        </li>
                        <li class="page-item blank">
                            <a class="page-link" href="#">...</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="#">09</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="#">
                                <i class="fa fa-chevron-right" aria-hidden="true"></i>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
</section>
<!--================End Feature Product Area =================-->

<#include "common/footer.ftl">

<#include "common/page_end.ftl">

</body>

</html>