<!doctype html>
<html lang="en">

<head>
    <script>
        function verify() {
            var name=document.getElementById("name");
            var str_name=name.value;
            var email=document.getElementById("email");
            var str_email=email.value;
            var password=document.getElementById("password");
            var str_password=password.value;
            var pass=document.getElementById("pass");
            var str_pass=pass.value;
            var symbol=document.getElementById("symbol");
            var test=symbol.value;
            if(str_name==test){
                document.getElementById("name_err").innerHTML="请输入账号名称!"
                return false;
            }else if(str_email==test){
                document.getElementById("phone_err").innerHTML="请输入手机号码!"
                return false;
            }else if(str_password==test) {
                document.getElementById("password_err").innerHTML = "请输入账号密码!"
                return false;
            }else if(str_password!=str_pass) {
                    document.getElementById("pass_err").innerHTML = "两次输入密码不一致!"
                    return false;
            }else{
                return true;
            }
        }
    </script>

</head>
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
                            <img class="img-fluid" src="img/login.jpg" alt="">
                            <div class="hover">
                                <h4>已经拥有账户?</h4>
                                <p>这里有最优质商品,给你唯美的购物体验</p>
                                <a class="main_btn" href="/sign_in">登录</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="login_form_inner reg_form">
                    <h3>创建账户</h3>
                    <span style="color: red">${register!}</span>
                    <form class="row login_form" action="register" method="post" id="contactForm" onsubmit="return verify()">
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="name" name="name" placeholder="昵称">
                            <span style="color: red" id="name_err"></span>
                        </div>
                        <input id="symbol" type="hidden" class="form-control"  name="symbol" >
                        <div class="col-md-12 form-group">
                            <input type="email" class="form-control" id="email" name="email" placeholder="邮箱地址">
                            <span style="color: red" id="phone_err"></span>
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="password" class="form-control" id="pass" name="pass" placeholder="密码">
                            <span style="color: red" id="pass_err"></span>
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="password" class="form-control" id="password" name="password" placeholder="确认密码">
                            <span style="color: red" id="password_err"></span>
                        </div>

                        <div class="col-md-12 form-group">
                            <div class="creat_account">
                                <input type="checkbox" id="f-option2" name="selector">
                                <label for="f-option2">记住我</label>
                            </div>
                        </div>
                        <div class="col-md-12 form-group">
                            <button type="submit" value="submit" class="btn submit_btn">注册并登录</button>
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