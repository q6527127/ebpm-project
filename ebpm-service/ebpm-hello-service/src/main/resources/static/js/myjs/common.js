var base = $("#base").val();
$(function(){
	 //cookie 用户名密码 get
	 if (getCookie("userName")!=null && getCookie("userName")!=undefined) {
		 
		 $('input[type="text"]').val(getCookie("userName"));
		 $('input[type="password"]').val(getCookie("passWord"));
	 }
	 if (getCookie("loginDate")!=null && getCookie("loginDate")!=undefined) {
		 //$('h2').html(getCookie("loginDate"));
	 }
	 $("h1:first").hide();
	 $("#upFile").hide();

	 
	 
	 $('#login-button').click(function (event) {
			event.preventDefault();
			$("h1:first").show();
			$(".upDownFile").show();
			$('#loginForm').fadeOut(500);
			$('.wrapper').addClass('form-success');
			 //cookie 用户名密码 set
			var userName= $('input[type="text"]').val();
			var passWord= $('input[type="password"]').val();
			var expdate = new Date();
            expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000));
            //将用户名和密码写入到Cookie
            setCookie("userName", userName, expdate);
            setCookie("passWord", passWord, expdate);
		});
	 $('#upFile').click(function (event) {
		 alert(base+"/upDownIndex.do");
		 window.location.href=base+"/upDownIndex.do"; 
	 });

 });

     
 function getCookie(c_name)
 {
 if (document.cookie.length>0)
   {
   c_start=document.cookie.indexOf(c_name + "=")
   if (c_start!=-1)
     { 
     c_start=c_start + c_name.length+1 
     c_end=document.cookie.indexOf(";",c_start)
     if (c_end==-1) c_end=document.cookie.length
     return unescape(document.cookie.substring(c_start,c_end))
     } 
   }
 return ""
 }

 function setCookie(c_name,value,expiredays)
 {
 var exdate=new Date()
 exdate.setDate(exdate.getDate()+expiredays)
 document.cookie=c_name+ "=" +escape(value)+
 ((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
 }

 function checkCookie()
 {
 username=getCookie('username')
 if (username!=null && username!="")
   {alert('Welcome again '+username+'!')}
 else 
   {
   username=prompt('Please enter your name:',"")
   if (username!=null && username!="")
     {
     setCookie('username',username,365)
     }
   }
 }