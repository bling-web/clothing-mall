<!doctype html>
<html lang="en">

<#include "common/page_start.ftl">

<body>
<#include "common/main_menu_category.ftl">

<!--================Category Product Area =================-->
<br>
<br>
<br>
<br>

<section class="cat_product_area section_gap">
    <div class="container-fluid">
        <div class="row flex-row-reverse">
            <div class="col-lg-9">
                <div class="product_top_bar">
                        <#--下边页数设置-->
                    <div class="right_page ml-auto">
                        <nav class="cat_page" aria-label="Page navigation example">
                            <ul class="pagination">
                                <li class="page-item">
                                    <a class="page-link" href="#">
                                        <i class="fa fa-long-arrow-left" aria-hidden="true"></i>
                                    </a>
                                </li>
                                <li class="page-item active">
                                    <a class="page-link" href="#">1</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="#">2</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="#">3</a>
                                </li>
                                <li class="page-item blank">
                                    <a class="page-link" href="#">...</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="#">6</a>
                                </li>
                                <li class="page-item">
                                    <a class="page-link" href="#">
                                        <i class="fa fa-long-arrow-right" aria-hidden="true"></i>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="latest_product_inner row">
                     <#list goods_img as good >
                         <div class="col col1">
                             <div class="f_p_item">
                                 <div class="f_p_img">
                                     <img class="img-fluid" src="${good.imageUrl}" alt="">
                                     <div class="p_icon">
                                         <a href="#">
                                             <i class="lnr lnr-heart"></i>
                                         </a>
                                         <a href="/cart?get_id=${good.id}">
                                             <i class="lnr lnr-cart"></i>
                                         </a>
                                     </div>
                                 </div>
                                 <a href="/goods_details?name=${good.name}">
                                     <h4>${good.name}</h4>
                                 </a>
                                 <h5>$${good.sellingPrice}</h5>
                             </div>
                         </div>
                     </#list>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="left_sidebar_area">
                    <aside class="left_widgets cat_widgets">
                        <div class="l_w_title">
                            <h3>服装分类</h3>
                        </div>
                        <div class="widgets_inner">
                            <ul class="list">
                                <li>
                                    <a href="/category?categoryId=1">男装</a>
                                </li>
                                <li>
                                    <a href="/category?categoryId=0">女装</a>
                                </li>
                            </ul>
                        </div>
                    </aside>
                    <aside class="left_widgets cat_widgets">
                        <div class="l_w_title">
                            <h3>排序</h3>
                        </div>
                        <div class="widgets_inner">
                            <ul class="list">
                                <li>
                                    <a href="/category?categoryId=${categoryId!}&&price=asc">价格升序</a>
                                </li>
                                <li>
                                    <a href="/category?categoryId=${categoryId!}&&price=desc">价格降序</a>
                                </li>
                            </ul>
                        </div>
                    </aside>
                </div>
            </div>
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
                        <a class="page-link" href="#">01</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="#">02</a>
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
    </div>
</section>
<!--================End Category Product Area =================-->

<#include "common/footer.ftl">

<#include "common/page_end.ftl">

</body>

</html>