<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bsong.model.SongModel" %>
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
                                <div class="col-sm-6">
                                    <a href="<%=request.getContextPath()%>/admin/song/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" class="form-control input-sm" placeholder="Nhập tên bài hát" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>
                            <form action="<%=request.getContextPath()%>/admin/songs" id="formSubmit" method="GET">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Tên bài hát</th>
                                            <th>Danh mục</th>
                                            <th>Lượt đọc</th>
                                            <th>Hình ảnh</th>
                                            <th width="160px">Chức năng</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                        SongModel songModel =(SongModel) request.getAttribute("songs");
                                        ArrayList<SongModel> list = (ArrayList<SongModel>) songModel.getListResult();
                                        if (list!=null){
                                            for (SongModel item:list){
                                    %>

                                        <tr>
                                            <td><%=item.getId()%></td>
                                            <td class="center"><%=item.getName()%></td>
                                            <td class="center"><%=item.getCat_name().getName()%></td>
                                            <td class="center"><%=item.getCounter()%></td>
                                            <td class="center">
                                                <img width="200px" height="200px" src="<%=request.getContextPath()%>/teamplate/admin/assets/img/<%=item.getPicture()%>" alt="<%=item.getName()%>"/>
                                            </td>
                                            <td class="center">
                                                <a href="<%=request.getContextPath()%>/admin/song/edit?id=<%=item.getId()%>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                                <a href="<%=request.getContextPath()%>/admin/song/del?id=<%=item.getId()%>" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
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
                                            <input type="hidden" value="" id="sortBy" name="sortBy"/>
                                            <input type="hidden" value="" id="sortName" name="sortName"/>
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
    var totalPages = ${songs.totalPage};
    var currentPage = ${songs.page};
    var visiblePages = ${songs.maxPageItem};
    var limit = 4;
    var sortName = "${songs.sortName}";
    var sortBy = "${songs.sortBy}";
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 5,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page){
                    $('#page').val(page);
                    $('#maxPageItem').val(limit);
                    $('#sortName').val(sortName);
                    $('#sortBy').val(sortBy);
                    $('#formSubmit').submit();
                }
            }
        });
    });
</script>
<script>
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/teamplate/admin/inc/footer.jsp" %>