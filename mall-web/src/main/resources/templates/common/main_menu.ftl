<!--================Header Menu Area =================-->
<header class="header_area">
    <div class="top_menu row m0">
        <div class="container-fluid">
            <div class="float-left">
                <p>联系我: bling_web@163.com</p>
            </div>
            <div class="float-right">
                <ul class="right_side">
                    <li>
                        <#if current_user??>
                     <li>
                         <a href="/sign_out">
                             退出登录
                         </a>
                     </li>
                        <#else>
                         <li>
                             <a href="/sign_in">
                                 登录
                             </a>
                         </li>
                        </#if>
                    </li>
                    <li>
                        <a href="#">
                            个人中心
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="main_menu">
        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <span class="navbar-brand logo_h" href="/index">
                    <div>购物商城</div>
                    <#--<img src="/img/logo.png" alt="">-->
                </span>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                    <div class="row w-100">
                        <div class="col-lg-7 pr-0">
                            <ul class="nav navbar-nav center_nav pull-right">
                                <li class="nav-item active">
                                    <a class="nav-link" href="/index">首页</a>
                                </li>
                                <li class="nav-item submenu">
                                    <a href="/category" class="nav-link" >分类</a>
                                </li>
                            </ul>
                        </div>

                        <div class="col-lg-5">
                            <ul class="nav navbar-nav navbar-right right_nav pull-right">
                                <hr>

                                <li class="nav-item">
                                    <a href="#" class="icons">
                                        <i class="fa fa-user" aria-hidden="true"></i>
                                    </a>
                                </li>

                                <hr>

                                <li class="nav-item">
                                    <a href="#" class="icons">
                                        <i class="fa fa-heart-o" aria-hidden="true"></i>
                                    </a>
                                </li>

                                <hr>

                                <li class="nav-item">
                                    <a href="/cart" class="icons">
                                        <i class="lnr lnr lnr-cart"></i>
                                    </a>
                                </li>

                                <hr>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</header>
<!--================Header Menu Area =================-->