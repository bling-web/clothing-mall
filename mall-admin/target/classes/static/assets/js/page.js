/**
 * 
 */
function page(params){
	this.pageMain = params.pageMain;
	// 现在的页数
	this.nowPage = params.nowPage;
	// this.maxPage = params.maxPage;
	this.count = params.count;
	this.pageSize = params.pageSize;
	this.url = params.url;
	this.params = params.params;

	this.pakey = params.pakey;
	this.init();
}
 page.prototype = {
		 init:function(){
		 	// 计算最大页数,查询之后也要有这个页数
			 var maxPage = 0;
			 if(this.count % this.pageSize == 0){
				 maxPage = this.count / this.pageSize;
			 }else{
				 maxPage = parseInt(this.count / this.pageSize) + 1;
			 }
			 //标记第几页
			 var id = this.pageMain;
			 var pageDiv = $("#"+id);
			
			 if(this.nowPage > 1){
                 // laquo就是那个<<
				 //this.pakey传到后台的就是page,page=this.nowPage-1
				 pageDiv.html('<li ><a href="'+this.url+this.params+'&'+this.pakey+'='+(this.nowPage-1)+'" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>');
				 var i = this.nowPage - 5;
				 if(i < 1){
					 i = 1;
				 }
				 // 就是又下角显示123那个,每个都是一个超链接
				 for(;i < this.nowPage;i++){
					 pageDiv.append('<li ><a href="'+this.url+this.params+'&'+this.pakey+'='+i+'">'+i+' <span class="sr-only">(current)</span></a></li>');
				 }
				 
			 }

			 pageDiv.append('<li class="active"><a>'+this.nowPage+' <span class="sr-only">(current)</span></a></li>');
			 if(this.nowPage < maxPage){
				 for(var i=this.nowPage+1;i <= maxPage&&i <= this.nowPage+5;i++){
					 pageDiv.append('<li><a href="'+this.url+this.params+'&'+this.pakey+'='+i+'">'+i+' <span class="sr-only">(current)</span></a></li>');
				 }
                 // raquo就是那个>>
				 pageDiv.append('<li><a href="'+this.url+this.params+'&'+this.pakey+'='+(this.nowPage+1)+'">&raquo; <span class="sr-only">(current)</span></a></li>');
			 }
		 }
 };