<%@ page import="com.bsong.model.CategoryModel" %>
<%@ page import="com.bsong.model.UserModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/teamplate/admin/inc/header.jsp" %>
<%@ include file="/teamplate/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Sửa danh mục</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
            if(request.getParameter("err")!=null){
                String msg = request.getParameter("err");
                if ("1".equals(msg)){
                    out.print("<span>Vui lòng nhập đầy đủ tất cả các mục</span>");
                }
            }
        %>
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form action="" role="form" method="post" id="form">
                                    <%
                                        if (request.getAttribute("user")!=null){
                                            UserModel user = (UserModel) request.getAttribute("user");
                                    %>

                                    <div class="form-group">
                                        <label for="name">Tên người dùng</label>
                                        <input type="text" id="name" value="<%=user.getName()%>" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Mật khẩu</label>
                                        <input type="password" id="password" value="<%=user.getPass()%>" name="password" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Tên đầy đủ</label>
                                        <input type="text" id="fullname" value="<%=user.getFullName()%>" name="fullname" class="form-control" />
                                    </div>

                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
                                    <%
                                        }
                                    %>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Form Elements -->
            </div>
        </div>
        <!-- /. ROW  -->
    </div>
    <!-- /. PAGE INNER  -->
</div>
<script>
    document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/teamplate/admin/inc/footer.jsp" %>