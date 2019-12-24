<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>编辑订单</title>

    <meta name="description" content="with selectable elements and custom icons" />
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
    <button class="btn btn-white btn-warning btn-bold" onclick="javascript:history.back();">
        <i class="ace-icon fa fa-chevron-left bigger-120 orange"></i>返回上一页</button>

    <div class="page-content">
        <div class="col-sm-5 widget-box widget-color-blue2">
            <div class="widget-header">
                <h4 class="widget-title lighter smaller">编辑订单</h4>
            </div>
        <form class="form-horizontal" id="goodform" style="margin-top: 20px;">
            <input type="hidden" name="id" id="id" value="${(myOrder.id)!}" />
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 订单编号 </label>
                <div class="col-sm-9">
                    <input type="text" id="orderNo" name="orderNo" value="${(myOrder.orderNo)!}" placeholder="订单编号" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 用户id </label>
                <div class="col-sm-9">
                    <input type="text"   name="userId"  placeholder="用户id" value="${(myOrder.userId)!}" class="col-xs-10 col-sm-5"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 发货地址id </label>
                <div class="col-sm-9">
                    <input type="text"  name="shippingId" value="${(myOrder.shippingId)!}" placeholder="发货地址id" class="col-xs-10 col-sm-5"/>
                </div>
            </div >
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 付款金额 </label>
                <div class="col-sm-9">
                    <input type="text" name="payment" value="${(myOrder.payment)!}" placeholder="付款金额" class="col-xs-10 col-sm-5"/>
                </div>
            </div >
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 快递费 </label>
                <div class="col-sm-9">
                    <input type="text" name="postage" value="${(myOrder.postage)!}" placeholder="快递费" class="col-xs-10 col-sm-5"/>
                </div>
            </div >
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 订单备注 </label>
                <div class="col-sm-9">
                    <input type="text"  name="remark" value="${(myOrder.remark)!}" placeholder="订单备注" class="col-xs-10 col-sm-5"/>
                </div>
            </div >
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 订单状态 </label>
                &nbsp&nbsp&nbsp
                <select name="status"  class="input-sm">
                    <option value="-1"<#if myOrder?? && myOrder.status==-1>selected</#if>>&nbsp&nbsp&nbsp请选择&nbsp&nbsp&nbsp    </option>
                    <option value="10"<#if myOrder?? && myOrder.status==10>selected</#if>>&nbsp&nbsp&nbsp未付款&nbsp&nbsp&nbsp    </option>
                    <option value="20"<#if myOrder?? && myOrder.status==20>selected</#if>>&nbsp&nbsp&nbsp已付款&nbsp&nbsp&nbsp    </option>
                    <option value="40"<#if myOrder?? && myOrder.status==40>selected</#if>>&nbsp&nbsp&nbsp已发货&nbsp&nbsp&nbsp    </option>
                    <option value="50"<#if myOrder?? && myOrder.status==50>selected</#if>>&nbsp&nbsp&nbsp交易成功&nbsp&nbsp&nbsp    </option>
                </select>
            </div >
              ${permission_attributes?seq_contains("/admin/order/edit")?string('<button class="btn btn-info" type="button" style="margin-left: 20px;" onclick="add()">
                                  <i class="ace-icon fa fa-check bigger-110"></i>
                                  保存
                              </button>','')}
            </form>
        </div>
    </div>
</div>
<!--[if !IE]> -->
<script src="/assets/js/jquery-2.1.4.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="/assets/js/jquery-1.11.3.min.js"></script>
<!--<![endif]-->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->
<script src="/assets/js/tree.min.js"></script>

<script src="/assets/js/spinbox.min.js"></script>
<script src="/assets/js/jquery.inputlimiter.min.js"></script>
<script src="/assets/js/jquery.maskedinput.min.js"></script>
<!-- ace scripts -->
<script src="/assets/js/ace-elements.min.js">@Param("")</script>
<script src="/assets/js/ace.min.js"></script>

<script src="/assets/js/bootbox.js"></script>
<script src="/assets/js/utils.js"></script>
<script>

        function add() {
            if (!vali($("#orderNo"))) {
                alert("请输入订单编号");
                return;
            }
            quickAjax({
                url: '/admin/order/edit',
                method: "POST",
                data: {
                    id: $("#id").val(),
                    orderNo: $("#orderNo").val(),
                    userId: $("input[name=userId]").val(),
                    shippingId: $("input[name=shippingId]").val(),
                    payment: $("input[name=payment]").val(),
                    postage: $("input[name=postage]").val(),
                    remark: $("input[name=remark]").val(),
                    status: $("select[name=status]").val(),
                },
                success: function (response) {
                    if (response.code == 1) {
                        alert("更新成功", function () {
                            self.location = document.referrer;
                        });

                    }
                },
                error: function (response) {
                    alert("链接服务器失败");
                }
            });
        }

</script>
</body>

</html>