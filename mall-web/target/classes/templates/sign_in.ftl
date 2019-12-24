<!doctype html>
<html lang="en">



<#include "common/page_start.ftl">

<body>

<#include "common/main_menu.ftl">

<!--================Login Box Area =================-->
<section class="login_box_area p_120">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <div class="row">
                    <div class="col-lg-10 mg-40">
                        <div class="login_box_img">
                            <img class="img-fluid" src="/img/login.jpg" alt="">
                            <div class="hover">
                                <h4>第一次来我们的购物网站吗?</h4>
                                <p>这里有最优质商品,给你唯美的购物体验</p>
                                <a class="main_btn" href="/register">创建一个新的账户</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="login_form_inner">
                    <h3>登录</h3>
                    <span >${error!''}</span>
                    <form class="row login_form" action="/login" method="post" id="contactForm" novalidate="novalidate">

                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="name" name="name" placeholder="Username">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="password" class="form-control" id="psw" name="psw" placeholder="Password">
                        </div>
                        <div class="col-md-12 form-group">
                            <div class="creat_account">
                                <input type="checkbox" id="f-option2" name="selector">
                                <label for="f-option2">记住我</label>
                            </div>
                        </div>
                        <div class="col-md-12 form-group">
                            <button type="submit" value="submit" class="btn submit_btn">登录</button>
                            <#--<a href="#">忘记密码?</a>-->
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<!--================End Login Box Area =================-->

<#include "common/footer.ftl">

<#include "common/page_end.ftl">
</body>
</html>