<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bsong.model.ContactModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/teamplate/admin/inc/header.jsp" %>
<%@ include file="/teamplate/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý bài hát</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">

                                <div class="col-sm-6" >
                                    <form method="post" action="">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" class="form-control input-sm" placeholder="Nhập tên bài hát" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form>
                                    <br />

                                </div>
                            </div>
                            <%
                                if (request.getParameter("msg")!=null){
                                    String msg = request.getParameter("msg");
                                    if ("1".equals(msg)){
                                        out.print("<span>Xóa thành công</span>");
                                    }
                                }
                            %>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên</th>
                                        <th>Email</th>
                                        <th>Website</th>
                                        <th>Thông tin liên hệ</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <%
                                    if (request.getAttribute("listcontact")!= null){
                                        ArrayList<ContactModel> list = (ArrayList<ContactModel>)request.getAttribute("listcontact");
                                        for (ContactModel item:list){
                                %>
                                <tbody>
                                    <tr>
                                        <td><%=item.getId()%></td>
                                        <td class="center"><%=item.getName()%></td>
                                        <td class="center"><%=item.getEmail()%></td>
                                        <td class="center"><%=item.getWebsite()%></td>
                                        <td class="center"><%=item.getMessage()%></td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath()%>/admin/contacts/del?id=<%=item.getId()%>" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                </tbody>
                                <%
                                        }
                                    }
                                %>
                            </table>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ 1 đến 5 của 24 truyện</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="#">Trang trước</a></li>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="">1</a></li>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="">2</a></li>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="">3</a></li>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="">4</a></li>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="">5</a></li>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="#">Trang tiếp</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("contact").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/teamplate/admin/inc/footer.jsp" %>