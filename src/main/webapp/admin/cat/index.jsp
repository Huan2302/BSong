<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bsong.model.CategoryModel" %>
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
        <%
            if(request.getParameter("err")!=null){
                String msg = request.getParameter("err");
                if ("1".equals(msg)){
                    out.print("<span>Không có mục này </span>");
                }else if ("3".equals(msg)){
                    out.print("<span>Xóa không thành công</span>");
                }
            }
        %>
        <%
            if(request.getParameter("del")!=null){
                String msg = request.getParameter("del");
                if ("1".equals(msg)){
                    out.print("<span>Xóa thành công</span>");
                }
            }
        %>
        <%
            if(request.getParameter("msg")!=null){
                String msg = request.getParameter("msg");
                if ("1".equals(msg)){
                    out.print("<span>Thêm thành công</span>");
                }else if ("2".equals(msg)){
                    out.print("<span>Sửa thành công</span>");
                }else if ("3".equals(msg)){
                    out.print("<span>Xóa thành công</span>");
                }
            }
        %>
        <hr />

            <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">
                                <div class="col-sm-6">
                                    <a href="<%=request.getContextPath()%>/admin/cat/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="<%=request.getContextPath()%>/admin/cat/search">
                                        <input type="submit" name="searchs" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name="search" class="form-control input-sm" placeholder="Nhập tên bài hát" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

                            <form action="<%=request.getContextPath()%>/admin/cats" id="formSubmit" method="GET">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>


                                        <tr>
                                            <th>ID</th>
                                            <th>Tên danh mục</th>
                                            <th width="160px">Chức năng</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                        if (request.getAttribute("categories")!=null){
                                            CategoryModel cat = (CategoryModel) request.getAttribute("categories");
                                            ArrayList<CategoryModel> list = (ArrayList<CategoryModel>) cat.getListResult();
                                            for (CategoryModel item:list){
                                    %>
                                        <tr>
                                            <td><%=item.getId()%></td>
                                            <td class="center"><%=item.getName()%></td>
                                            <td class="center">
                                                <a href="<%=request.getContextPath()%>/admin/cat/edit?id=<%=item.getId()%>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                                <a href="<%=request.getContextPath()%>/admin/cat/del?id=<%=item.getId()%>" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                            </td>
                                        </tr>
                                        <%
                                                }
                                            }
                                        %>
                                    </tbody>
                                </table>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ 1 đến 5 của 24 truyện</div>
                                    </div>
                                    <div class="col-sm-6" style="text-align: right;">
                                        <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                            <ul class="pagination" id="pagination"></ul>
                                            <input type="hidden" value="" id="page" name="page"/>
                                            <input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>

    </div>
</div>
<script type="text/javascript">
    var totalPages = ${categories.totalPage};
    var currentPage = ${categories.page};
    var visiblePages = ${categories.maxPageItem};
    var limit = 4;
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 5,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page){
                    $('#page').val(page);
                    $('#maxPageItem').val(limit);
                    $('#formSubmit').submit();
                }
            }
        });
    });
</script>
<script>
    document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/teamplate/admin/inc/footer.jsp" %>