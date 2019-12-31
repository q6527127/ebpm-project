<!DOCTYPE html>  
  
<html lang="en">  
  
<body>  
	<@in_page num=12;returnlist> 
		<#list returnlist as list>
			${list}
		</#list>
	</@in_page>
	</br>
    Date: ${time?date}  
    <br>  
    Time: ${time?time}  
    <br>  
    Message: ${message} 
    <br>
    	name=${p.name},count=${p.count}
</body>  
  
</html>  