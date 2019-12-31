<!DOCTYPE html>  
  
<html lang="en">  
  <head>
  	<script>
  	$('#upFile').click(function (event) {
  		var form = $(this).parent("#upDownFileForm");
		$.ajax({
                url:${form}.attr("action"),
                type:"post",
                data:$(form).serialize(),
                success:function(data){
                	alert(data);
                },
                error:function(e){
                    alert("错误！！");
                }
            });
	 });
  	
	</script>
  </head>
<body>  

	<div class="upDownFile">
				<form id="upDownFileForm" name="upDownFileForm" action="${base}/up.do" enctype="multipart/form-data" method="post">
			         上传用户：<input type="text" name="username"><br/>
			         上传文件1：<input type="file" name="file1"><br/>
			         上传文件2：<input type="file" name="file2"><br/>
			         <input id="upFile" type="button">提交</input>
			     </form>
			</div>
</body>  
  
</html>  