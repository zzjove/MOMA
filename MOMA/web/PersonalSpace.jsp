<%-- 
    Document   : PersonalSpace
    Created on : 2013-5-14, 22:34:31
    Author     : bianshujun
--%>
<%@page import="com.formerServlet.moma.PersonalPageServlet"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
    <head>
        <title>Welcome to MOMA</title>
        <meta http-equiv="Content-Type" CONTENT="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="./css/generalStyle.css" media="all" />
    </head>
    <body>
        <%
            PersonalPageServlet personalPage = (PersonalPageServlet) request.getAttribute("currentPersonalPage");
            session.setAttribute("currentPersonalPageforSearch", personalPage);
            out.println(personalPage.getUserofPage().getUserName());
            request.setAttribute("currentPersonalPageforSearch", personalPage);
        %>
        <div class="navbar">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <a class="brand" href="#">MOMA</a>
                    <ul class="nav">
                        <li><a href="#">首页</a></li>
                        <li class="active"><a href="#">我的纪念册</a></li>
                        <li><a href="Friends.jsp">我的好友</a></li>
                    </ul>
                    <form class="navbar-search pull-right" method="POST" action="SearchServlet">
                        out.println(personalPage.getUserofPage().getUserName());
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
                        <a href="#"><img src="img/portrait.png" /></a><br/>
                        <a href="#editMyInfo" role="button" class="btn" data-toggle="modal">编辑个人资料</a>
                        <div id="editMyInfo" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <h3 id="myModalLabel">个人资料</h3>
                            </div>
                            <div class="modal-body">
                                <form>
                                    <input type="text" name="userRealName" placeholder="真实姓名"/><br/>
                                    <input type="text" name="userGender" placeholder="性别" /><br />
                                    <input type="text" name="userAge" placeholder="年龄"/><br />
                                    <input type="text" name="userEmail" placeholder="邮箱"/><br />
                                    <input type="text" name="userAdress" placeholder="地址"/><br />
                                    <input type="text" name="userContact" placeholder="联系方式	"/><br />
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                                <button class="btn btn-primary">Save changes</button>
                            </div>
                        </div>
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
                            <img src="img/brochure.jpg" alt="">
                            <h4>The first brochure</h4>
                            <p>Description is...</p>
                            <a class="btn btn-primary" href="#">浏览</a>
                            <a class="btn btn-success" href="#">编辑</a>
                        </div>
                    </li>
                    <li class="span3">
                        <div class="thumbnail">
                            <img src="img/brochure.jpg" alt="">
                            <h4>The second brochure</h4>
                            <p>Description is...</p>
                            <a class="btn btn-primary" href="#">浏览</a>
                            <a class="btn btn-success" href="#">编辑</a>
                        </div>
                    </li>
                    <li class="span3">
                        <div class="thumbnail">
                            <img src="img/brochure.jpg" alt="">
                            <h4>The third brochure</h4>
                            <p>Description is...</p>
                            <a class="btn btn-primary" href="#">浏览</a>
                            <a class="btn btn-success" href="#">编辑</a>
                        </div>
                    </li>
                    <li class="span3">
                        <div class="thumbnail">
                            <img src="img/brochure.jpg" alt="">
                            <h4>The forth brochure</h4>
                            <p>Description is...</p>
                            <a class="btn btn-primary" href="#">浏览</a>
                            <a class="btn btn-success" href="#">编辑</a>
                        </div>
                    </li>
                </ul>
            </div>

            <div class="row pull-right">
                <a href="#newBrochure" role="button" class="btn btn-primary" data-toggle="modal">新建一个</a>
                <div id="newBrochure" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">新的纪念册</h3>
                    </div>
                    <div class="modal-body">
                        <form>
                            <input type="text" name="brochureName" placeholder="请给纪念册起个名字吧"/><br/>
                            <input type="text" name="brochureDescription" placeholder="请给纪念册添加描述" /><br />
                            <input type="button" class="btn-primary" name="brochureImgUrl" value="上传封面" />
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button class="btn btn-primary">Save changes</button>
                    </div>
                </div>
                <div class="span2"> </div>
            </div>

        </div>



        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="generalJS.js"></script>
    </body>
</html>