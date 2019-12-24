<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>Dashboard - Ace Admin</title>

<meta name="description" content="overview &amp; stats" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="/assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

<!-- page specific plugin styles -->

<!-- text fonts -->
<link rel="stylesheet" href="/assets/css/fonts.googleapis.com.css" />

<!-- ace styles -->
<link rel="stylesheet" href="/assets/css/ace.min.css"
	class="ace-main-stylesheet" id="main-ace-style" />

<!--[if lte IE 9]>
			<link rel="stylesheet" href="/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
<link rel="stylesheet" href="/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="/assets/css/ace-rtl.min.css" />

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="/assets/css/ace-ie.min.css" />
		<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->
<script src="/assets/js/ace-extra.min.js"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
		<script src="/assets/js/html5shiv.min.js"></script>
		<script src="/assets/js/respond.min.js"></script>
		<![endif]-->
</head>

<body class="no-skin">
	<div id="navbar" class="navbar navbar-default          ace-save-state">
		<div class="navbar-container ace-save-state" id="navbar-container">
			<button type="button" class="navbar-toggle menu-toggler pull-left"
				id="menu-toggler" data-target="#sidebar">
				<span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>

				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<div class="navbar-header pull-left">
				<a href="index.ftl" class="navbar-brand"> <small> <i
						class="fa fa-leaf"></i> 业务后台
				</small>
				</a>
			</div>

			<div class="navbar-buttons navbar-header pull-right"
				role="navigation">
				<ul class="nav ace-nav">
					<li class="light-blue dropdown-modal">
						<a data-toggle="dropdown" href="#" class="dropdown-toggle">
						<img class="nav-user-photo" src="/assets/images/avatars/avatar.png"
							alt="Jason's Photo" />

						<span class="user-info">
							<small>${.now?string("yyyy-MM-dd")}</small>
						    ${current_user.name}
						</span>
					<i class="ace-icon fa fa-caret-down"></i>
					</a>

						<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						    ${permission_attributes?seq_contains("/admin/user/updatepass")?string('<li><a href="javascript:addTabs({id:\'-1\',title:\'修改密码\' ,close: true,url:\'/admin/user/updatepass\'})"> <i class="ace-icon fa fa-cog"></i> 修改密码
							</a></li>','')}
							<li class="divider"></li>

							<li>
								<a href="/admin/loginout"> <i class="ace-icon fa fa-power-off"></i>
									退出登录
							     </a>
							</li>
						</ul></li>
				</ul>
			</div>
		</div>
		<!-- /.navbar-container -->
	</div>
	<!-- BEGIN 页面主体 -->
	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.loadState('main-container')
			} catch (e) {
			}
		</script>
		<!-- BEGIN 左边导航栏-->
		<div id="sidebar"
			class="sidebar                  responsive                    ace-save-state">
			<script type="text/javascript">
				try {
					ace.settings.loadState('sidebar')
				} catch (e) {
				}
			</script>

			<!-- BEGIN 导航菜单 -->
			<ul class="nav nav-list" id="nav">
			<#if treeMenu ??>
			<#macro bpTree children>
				<#if children?? && children?size gt 0>
					<#list children as child>
						<#--如果该元素有子节点,显示名字,并执行下面的循环,-->
						<#if child.children?? && child.children?size gt 0>
						<#--这里相当于与一个超链接,不过链接的是增加Tab页的一个方法-->
						<#--增加的页面从哪链接?就是取到的url-->
                        <li ><a class="dropdown-toggle" href="javascript:addTabs({id:'${child.id},title: '${child.name}',close: true,url:'${child.url}'})">
							       <i class="menu-icon fa ${child.icon!""}"></i>
							       <span class="menu-text">
									   ${child.name}
     						       </span>
							       <b class="fa fa-angle-down arrow"></b>
						     </a>
							<ul class="submenu" style="display: block;" id="nav${child.id}">
								<#--循环的关键,将元素的子节点赋值为当前元素-->
									<@bpTree children=child.children />
							</ul>
                        </li>
						<#else>
                            <li >
								<a href="javascript:addTabs({id:'${child.id}',title: '${child.name}',close: true,url: '${child.url}'})">
								    <i class="menu-icon fa ${child.icon!''}"></i>
									<span class="menu-text">
										${child.name}
									</span>
								</a>
							</li>
						</#if>
					</#list>
				</#if>
			</#macro>
                <!-- 调用宏 生成递归树 -->
			<@bpTree children=treeMenu />
			</#if>
			</ul>
			<!-- /.nav-list -->
			<!-- END 导航菜单 -->
			<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
				<i id="sidebar-toggle-icon"
					class="ace-icon fa fa-angle-double-left ace-save-state"
					data-icon1="ace-icon fa fa-angle-double-left"
					data-icon2="ace-icon fa fa-angle-double-right"></i>
			</div>
		</div>

		<!-- END 左边导航栏 -->
		<!-- BEGIN 页面内容 -->
		<div class="main-content" style="height:100%">
			<div class="page-content" style="padding: 8px 12px 0px 18px;">
				<div class="row">
					<div class="col-xs-12" style="padding-left: 0px;padding-right: 0px;">
						<ul class="nav nav-tabs" role="tablist">
							<li class="active"><a href="#Index" role="tab"
								data-toggle="tab">首页</a></li>
						</ul>
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="Index">
								欢迎使用 业务后台管理系统
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.main-content -->
		<!-- END  页面内容 -->
		
	</div>
	<!-- /.main-container -->
	<!-- END 页面主体 -->
	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script src="/assets/js/jquery-2.1.4.min.js"></script>

	<!-- <![endif]-->

	<!--[if IE]>
<script src="/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='/assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
	<script src="/assets/js/bootstrap.min.js"></script>

	<!-- page specific plugin scripts -->

	<!--[if lte IE 8]>
		  <script src="/assets/js/excanvas.min.js"></script>
		<![endif]-->
	<script src="/assets/js/jquery-ui.custom.min.js"></script>
	<script src="/assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="/assets/js/jquery.easypiechart.min.js"></script>
	<script src="/assets/js/jquery.sparkline.index.min.js"></script>
	<script src="/assets/js/jquery.flot.min.js"></script>
	<script src="/assets/js/jquery.flot.pie.min.js"></script>
	<script src="/assets/js/jquery.flot.resize.min.js"></script>

	<!-- ace scripts -->
	<script src="/assets/js/ace-elements.min.js"></script>
	<script src="/assets/js/ace.min.js"></script>
	<!--<script src="/assets/js/sidebar-menu.js"></script>-->
	<script src="/assets/js/bootstrap-tab.js"></script>

	<script src="/assets/js/utils.js"></script>
	<!-- inline scripts related to this page -->

</body>
</html>
