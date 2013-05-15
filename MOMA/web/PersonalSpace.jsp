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
        <title>Welcome to MOMA</title>
        <meta http-equiv="Content-Type" CONTENT="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="generalStyle.css" media="all" />
    </head>
    <body>
        <div class="navbar">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <a class="brand" href="#">MOMA</a>
                    <ul class="nav">
                        <li><a href="#">首页</a></li>
                        <li class="active"><a href="#">我的纪念册</a></li>
                        <li><a href="#">我的好友</a></li>

                    </ul>
                    <form class="navbar-search pull-right" method="POST" action="SearchServlet">
                        <input type="text" class="search-query" placeholder="Search for friends" name="SearchName"/>
                    </form>
                    <ul class="nav pull-right ">
                        <li><a href="#">新建纪念册</a></li>
                    </ul>

                </div>
            </div>
        </div>

        <div class="row" >
            <div class="row">

                <div class=" pull-right">
                    <div class="span2">
                        <a href="#"><img src="portrait.png" /></a><br/>
                        <a class="btn btn-warning" href="#">编辑个人资料</a>
                    </div>
                    <div class="span3 text-left badge badge-warning">
                        <h4	>
                            姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名<br/>
                            性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别<br/>
                            年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄<br/>
                            邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱<br />
                            地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址<br />
                            联系方式<br/>

                        </h4>
                    </div>
                    <div class="span3">

                    </div>
                </div>
            </div>

            <br/>
            <br/>

            <div class="row">
                <div class="span1"> </div>
                <ul class="thumbnails">
                    <li class="span3">
                        <div class="thumbnail">
                            <img src="brochure.jpg" alt="">
                            <h4>The first brochure</h4>
                            <p>Description is...</p>
                            <a class="btn btn-primary" href="#">浏览</a>
                            <a class="btn btn-success" href="#">编辑</a>
                        </div>
                    </li>
                    <li class="span3">
                        <div class="thumbnail">
                            <img src="brochure.jpg" alt="">
                            <h4>The second brochure</h4>
                            <p>Description is...</p>
                            <a class="btn btn-primary" href="#">浏览</a>
                            <a class="btn btn-success" href="#">编辑</a>
                        </div>
                    </li>
                    <li class="span3">
                        <div class="thumbnail">
                            <img src="brochure.jpg" alt="">
                            <h4>The third brochure</h4>
                            <p>Description is...</p>
                            <a class="btn btn-primary" href="#">浏览</a>
                            <a class="btn btn-success" href="#">编辑</a>
                        </div>
                    </li>
                    <li class="span3">
                        <div class="thumbnail">
                            <img src="brochure.jpg" alt="">
                            <h4>The forth brochure</h4>
                            <p>Description is...</p>
                            <a class="btn btn-primary" href="#">浏览</a>
                            <a class="btn btn-success" href="#">编辑</a>
                        </div>
                    </li>
                </ul>
            </div>

            <div class="row pull-right">
                <a class="span1 btn btn-primary" href="#">新建一个</a>
                <div class="span2"> </div>
            </div>

        </div>



        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="bootstrap.js"></script>

        
            <table border="1">
                <tr>
                    <th>userName</th>
                    <td>${currentPersonalPage.userofPage.userName}</td>
            </tr>
        </table> 
    </body>
</html>
