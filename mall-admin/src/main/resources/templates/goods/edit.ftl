<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>编辑商品</title>

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
                <h4 class="widget-title lighter smaller">编辑商品</h4>
            </div>
        <form class="form-horizontal" id="goodform" style="margin-top: 20px;">
            <input type="hidden" name="id" id="id" value="${(good.id)!}" />
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 商品名称 </label>
                <div class="col-sm-9">
                    <input type="text" id="name" name="name" value="${(good.name)!}" placeholder="商品名称" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 商品简称 </label>
                <div class="col-sm-9">
                    <input type="text"   name="shortName"  placeholder="商品简称" value="${(good.shortName)!}" class="col-xs-10 col-sm-5"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 长 </label>
                <div class="col-sm-9">
                    <input type="text"  name="length" value="${(good.length)!}" placeholder="长" class="col-xs-10 col-sm-5"/>
                </div>
            </div >
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 宽 </label>
                <div class="col-sm-9">
                    <input type="text" name="width" value="${(good.width)!}" placeholder="高" class="col-xs-10 col-sm-5"/>
                </div>
            </div >
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 高 </label>
                <div class="col-sm-9">
                    <input type="text" name="high" value="${(good.high)!}" placeholder="高" class="col-xs-10 col-sm-5"/>
                </div>
            </div >
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 重量 </label>
                <div class="col-sm-9">
                    <input type="text"  name="weight" value="${(good.weight)!}" placeholder="重量" class="col-xs-10 col-sm-5"/>
                </div>
            </div >
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 打折价格 </label>
                <div class="col-sm-9">
                    <input type="text"  name="costPrice" value="${(good.costPrice)!}" placeholder="打折价格" class="col-xs-10 col-sm-5"/>
                </div>
            </div >
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 销售价格 </label>
                <div class="col-sm-9">
                    <input type="text"  name="sellingPrice" value="${(good.sellingPrice)!}" placeholder="销售价格" class="col-xs-10 col-sm-5"/>
                </div>
            </div >
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 原价 </label>
                <div class="col-sm-9">
                    <input type="text"  name="originalPrice" value="${(good.originalPrice)!}" placeholder="原价" class="col-xs-10 col-sm-5"/>
                </div>
            </div >

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 库存 </label>
                <div class="col-sm-9">
                    <input type="text" name="basicStock" value="${(good.basicStock)!}" placeholder="库存" class="col-xs-10 col-sm-5"/>
                </div>
            </div >
             <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 是否上架 </label>
                 &nbsp&nbsp&nbsp
                <select  name="upperShelf" aria-controls="dynamic-table" class="input-sm">
                    <option value="N" <#if good?? && good.upperShelf=="N">selected</#if>>&nbsp&nbsp&nbsp未上架&nbsp&nbsp&nbsp</option>
                    <option value="Y" <#if good?? && good.upperShelf=="Y">selected</#if>>&nbsp&nbsp&nbsp已上架&nbsp&nbsp&nbsp</option>
                </select>
            </div >
             <div class="form-group">
                 <label class="col-sm-3 control-label no-padding-right" > 是否审核 </label>
                 &nbsp&nbsp&nbsp
                 <select  name="examine" aria-controls="dynamic-table" class="input-sm">
                     <option value="N" <#if good?? && good.examine=="N">selected</#if>>&nbsp&nbsp&nbsp未审核&nbsp&nbsp&nbsp</option>
                     <option value="Y" <#if good?? && good.examine=="Y">selected</#if>>已审核</option>
                 </select>
             </div >
             <div class="form-group">
                 <label class="col-sm-3 control-label no-padding-right" > 是否删除 </label>
                 &nbsp&nbsp&nbsp
                 <select  name="deleted"  class="input-sm">
                     <option value="N" <#if good?? && good.deleted=="N">selected</#if>>&nbsp&nbsp&nbsp未删除&nbsp&nbsp&nbsp</option>
                     <option value="Y" <#if good?? && good.deleted=="Y">selected</#if>>&nbsp&nbsp&nbsp已删除&nbsp&nbsp&nbsp</option>
                 </select>&nbsp&nbsp&nbsp
             </div >
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 商品分类 </label>
                &nbsp&nbsp&nbsp
                <select name="categoryId"  class="input-sm">
                    <option value="-1"<#if good?? && good.categoryId==-1>selected</#if>>&nbsp&nbsp&nbsp请选择&nbsp&nbsp&nbsp    </option>
                    <option value="0" <#if good?? && good.categoryId==0>selected</#if>>&nbsp&nbsp&nbsp男装&nbsp&nbsp&nbsp</option>
                    <option value="1" <#if good?? && good.categoryId==1>selected</#if>>&nbsp&nbsp&nbsp女装&nbsp&nbsp&nbsp</option>
                    <#--<option value="2" <#if good?? && good.categoryId==2>selected</#if>>&nbsp&nbsp&nbsp生鲜&nbsp&nbsp&nbsp</option>-->
                    <#--<option value="3" <#if good?? && good.categoryId==3>selected</#if>>&nbsp&nbsp&nbsp运动&nbsp&nbsp&nbsp</option>-->
                    <#--<option value="4" <#if good?? && good.categoryId==4>selected</#if>>&nbsp&nbsp&nbsp百货&nbsp&nbsp&nbsp</option>-->
                    <#--<option value="5" <#if good?? && good.categoryId==5>selected</#if>>&nbsp&nbsp&nbsp美妆&nbsp&nbsp&nbsp</option>-->
                </select>
            </div >
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" > 详细 </label>
                <div class="col-sm-9">
                    <textarea class="form-control" id="detail" name="detail" id="form-field-8" placeholder="详细" style="margin: 0px -0.015625px 0px 0px; height: 150px; width: 300px;">${(good.detail)!}</textarea>
                </div>
            </div >

              ${permission_attributes?seq_contains("/admin/goods/edit")?string('<button class="btn btn-info" type="button" style="margin-left: 20px;" onclick="add()">
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
            if (!vali($("#name"))) {
                alert("请输入商品名称");
                return;
            }
            quickAjax({
                url: '/admin/goods/edit',
                method: "POST",
                data: {
                    id: $("#id").val(),
                    name: $("#name").val(),
                    shortName: $("input[name=shortName]").val(),
                    length: $("input[name=length]").val(),
                    width: $("input[name=width]").val(),
                    high: $("input[name=high]").val(),
                    weight: $("input[name=weight]").val(),
                    costPrice: $("input[name=costPrice]").val(),
                    sellingPrice: $("input[name=sellingPrice]").val(),
                    originalPrice: $("input[name=originalPrice]").val(),
                    basicStock: $("input[name=basicStock]").val(),
                    upperShelf: $("select[name=upperShelf]").val(),
                    examine: $("select[name=examine]").val(),
                    deleted: $("select[name=deleted]").val(),
                    categoryId: $("select[name=categoryId]").val(),
                    detail: $("#detail").val()
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