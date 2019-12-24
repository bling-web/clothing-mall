<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>订单管理</title>

    <meta name="description" content="Static &amp; Dynamic Tables" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="/assets/css/fonts.googleapis.com.css" />

    <!-- ace styles -->
    <link rel="stylesheet" href="/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

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


<div class="main-container ace-save-state" id="main-container">
    <script type="text/javascript">
        try{ace.settings.loadState('main-container')}catch(e){}
    </script>


    <div class="page-content">
        <div class="col-xs-12">

            <button class="btn btn-white btn-warning btn-bold" onclick="delSelected()">
                <i class="ace-icon fa fa-trash-o bigger-120 orange"></i>
                删除
            </button>
            <div class="table-header" style="margin-top: 10px;">
                订单列表
            </div>

            <!-- div.table-responsive -->

            <!-- div.dataTables_borderWrap -->
            <div>
                <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline no-footer">
                    <form method="get" action="/admin/order" id="sform">
                    <div class="row">
                        <div class="col-xs-6">
                        <div class="dataTables_length" id="dynamic-table_length">
                            <label>每页显示
                                <select name="pageSize" aria-controls="dynamic-table" class="form-control input-sm">
                                <option <#if pageSize==10>selected</#if>  value="10">10</option>
                                <option <#if pageSize==25>selected</#if> value="25">25</option>
                                <option <#if pageSize==50>selected</#if> value="50">50</option>
                                <option <#if pageSize==100>selected</#if> value="100">100</option>
                                </select>条
                            </label>
                            &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                            <label>订单状态
                                <select name="status"  aria-controls="dynamic-table" class="form-control input-sm">
                                    <option value="-1" <#if status==-1>selected</#if>>全部状态</option>
                                    <option value="0"  <#if status==0>selected</#if>>已取消</option>
                                    <option value="10"  <#if status==10>selected</#if>>未付款</option>
                                    <option value="20"  <#if status==20>selected</#if>>已付款</option>
                                    <option value="40"  <#if status==40>selected</#if>>已发货</option>
                                    <option value="50"  <#if status==50>selected</#if>>交易成功</option>
                                </select>
                            </label>
                        </div>
                    </div>
                        <div class="col-xs-6">
                            <div id="dynamic-table_filter" class="dataTables_filter"><label>搜索:
                                <input type="search" name="query" value="${query!}" class="form-control input-sm" placeholder="" aria-controls="dynamic-table"></label>
                            </div>
                        </div>
                    </div>
                    </form>
                    <table id="dynamic-table" class="table table-striped table-bordered table-hover dataTable no-footer" role="grid" aria-describedby="dynamic-table_info">
                        <thead>
                        <tr role="row">
                            <th class="center sorting_disabled" rowspan="1" colspan="1" aria-label="">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" id="check_all">
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th class="sorting_disabled" tabindex="0" rowspan="1" colspan="1" >ID</th>
                            <th class="sorting_disabled" tabindex="0"  rowspan="1" colspan="1">订单编号</th>
                            <th class="hidden-480 sorting_disabled" tabindex="0" rowspan="1" colspan="1">用户id</th>
                            <th class="sorting_disabled" tabindex="0"  rowspan="1" colspan="1" >发货地址id</th>
                            <th class="hidden-480 sorting_disabled" tabindex="0"  rowspan="1" colspan="1" >付款金额</th>
                            <th class="sorting_disabled" rowspan="1" colspan="1" aria-label="">快递费</th>
                            <th class="sorting_disabled" rowspan="1" colspan="1" aria-label="">订单备注</th>
                            <th class="sorting_disabled" rowspan="1" colspan="1" aria-label="">订单状态</th>
                            <th class="sorting_disabled" rowspan="1" colspan="1" aria-label="">支付时间</th>
                            <th class="sorting_disabled" rowspan="1" colspan="1" aria-label="">操作</th>
                        </tr>
                        </thead>

                        <tbody>
                        <#list myOrders as myOrder >

                        <tr good="row" class="odd">
                            <td class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" name="checkbox" value="${myOrder.id}" class="ace">
                                    <span class="lbl"></span>
                                </label>
                            </td>
                            <td>
                                <a href="#">${myOrder.id}</a>
                            </td>
                            <td>${myOrder.orderNo!}</td>
                            <td class="hidden-480">${myOrder.userId!}</td>
                            <td class="hidden-480">${myOrder.shippingId!}</td>
                            <td class="hidden-480">${myOrder.payment!}</td>
                            <td class="hidden-480">${myOrder.postage!}</td>
                            <td class="hidden-480">${myOrder.remark!}</td>
                            <td class="hidden-480">
                                <#if myOrder.status==0>已取消</#if>
                                <#if myOrder.status==10>未付款</#if>
                                <#if myOrder.status==20>已付款</#if>
                                <#if myOrder.status==40>已发货</#if>
                                <#if myOrder.status==50>交易成功</#if>
                            </td>
                            <td class="hidden-480">
                                <#if myOrder.paymentTime??>
                                    ${myOrder.paymentTime?string("yyyy-MM-dd")}
                                <#else>
                                    还未支付
                                </#if>

                            </td>
                            <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                ${permission_attributes?seq_contains("/admin/order/edit")?string('<a class="green" href="/admin/order/edit?id=${myOrder.id}">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a>','')}

                                </div>
                            </td>
                        </tr>
                        </#list>

                        </tbody>
                    </table>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="dataTables_info" id="dynamic-table_info" role="status" aria-live="polite">第${nowBegin}-${nowEnd}条，共${count}条
                            </div>
                        </div>
                        <div class="col-xs-6"><div class="dataTables_paginate paging_simple_numbers" id="dynamic-table_paginate">
                            <ul class="pagination" id="pagination"></ul>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div><!-- /.page-content -->


</div><!-- /.main-container -->
<!-- basic scripts -->

<!--[if !IE]> -->
<script src="/assets/js/jquery-2.1.4.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->
<script src="/assets/js/jquery.dataTables.min.js"></script>
<script src="/assets/js/jquery.dataTables.bootstrap.min.js"></script>
<script src="/assets/js/dataTables.buttons.min.js"></script>
<script src="/assets/js/dataTables.select.min.js"></script>

<!-- ace scripts -->
<script src="/assets/js/ace-elements.min.js"></script>
<script src="/assets/js/ace.min.js"></script>
<script src="/assets/js/page.js" ></script>

<script src="/assets/js/bootbox.js"></script>
<script src="/assets/js/utils.js"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function($) {
        $("#check_all").change(function () {
            if($("#check_all").is(':checked')){
                $("input[name=checkbox]").prop('checked', true);
            }else{
                $("input[name=checkbox]").prop('checked', false);
            }
        });
        //这个就是选择某一个内容后提交的关键
        $("select[name=pageSize]").change(function () {
            $("#sform").submit();
        });
        //
        $("select[name=status]").change(function () {
            $("#sform").submit();
        });
        
        $("input[name=checkbox]").change(function () {
            var isAll = true;
            $("input[name=checkbox]").each(function () {
                if(!$(this).is(':checked')){
                    isAll = false;
                    $("#check_all").prop('checked', false);
                    return;
                }
            });
            if(isAll){
                $("#check_all").prop('checked', true);
            }
        });
        new page({pageMain:"pagination",nowPage:${page!'1'},count:${count},pageSize:${pageSize!'10'},
            url:"/admin/order",params:"?pageSize=${pageSize}&query=${query}",pakey:"page"});
    });
    /**
     * 删除角色
     */
    function del(id) {
        bootbox.confirm({
                    message: "是否删除?",
                    buttons: {
                        confirm: {
                            label: "删除",
                            className: "btn-sm",
                        },
                        cancel: {
                            label: "取消",
                            className: "btn-sm btn-primary",
                        }
                    },
                    callback: function(result) {
                        if(result) {
                            quickAjax({
                                url: '/admin/order/delete',
                                method:"POST",
                                data:{
                                    id:id
                                },
                                success: function (response) {
                                    if (response.code == 1){
                                        alert("删除成功",function(){
                                            location.reload();
                                        });

                                    }
                                },
                                error: function (response) {
                                    alert("链接服务器失败");
                                }
                            });
                        }
                    }
                }
        );
    }
    /**
     * 批量删除
     */
    function delSelected(){
        var id = "";
        $("input[name=checkbox]").each(function () {
            if($(this).is(':checked')){
               id +=  $(this).val()+",";
            }
        });
        if(id != ""){
            del(id);
        }
    }
</script>
</body>
</html>
