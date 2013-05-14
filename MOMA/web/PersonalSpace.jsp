<%-- 
    Document   : PersonalSpace
    Created on : 2013-5-14, 22:34:31
    Author     : bianshujun
--%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" CONTENT="text/html; charset=UTF-8"/>
	<title>个人空间</title>
	<link rel="stylesheet" type="text/css" href="generalStyle.css" media="all" />
    </head>
    <body>
        
        <c:set var="personalPage" scope="request" value="${request.currentPersonalPage}" ></c:set>
        <div class="navbar">
  		<div class="navbar-inner">
    			<ul class="nav">
      				<li class="active"><a href="#">首页</a></li>
      				<li><a href="#">我的好友</a></li>
      				<li><a href="#">我的纪念册</a></li>
   				</ul>
   			<form class="navbar-search pull-right">
  				<input type="text" class="search-query" placeholder="Search for friends">
			</form>
  		</div>
	</div>
        <table border="1">
            <tr>
                <td>${personalPage.userofPage.userName}</td>
        </tr>
        </table> 
    </body>
</html>
