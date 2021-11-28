<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bsong.model.CategoryModel" %>
<%@ page import="com.bsong.model.SongModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/teamplate/admin/inc/header.jsp" %>
<%@ include file="/teamplate/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm bài hát</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <%
                            ArrayList<CategoryModel> cat = (ArrayList<CategoryModel>) request.getAttribute("categories");
                            if (request.getAttribute("song")!=null){
                                SongModel song = (SongModel) request.getAttribute("song");
                        %>
                        <div class="row">
                            <div class="col-md-12">
                                <form action="" role="form" method="post" enctype="multipart/form-data" id="form">
                                    <div class="form-group">
                                        <label for="name">Tên </label>
                                        <input type="text" id="name" value="<%=song.getName()%>" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="category">Thương hiệu</label>
                                        <select id="category" name="category" class="form-control">
                                            <%
                                                if (cat != null && cat.size()>0){
                                                    for (CategoryModel item:cat){
                                                        if(item.getId()==song.getCat_id()){
                                            %>
                                            <option value="<%=item.getId()%>" selected="selected"><%=item.getName()%></option>
                                            <%
                                            }else {
                                            %>
                                            <option value="<%=item.getId()%>"><%=item.getName()%></option>
                                            <%
                                                        }
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="picture">Hình ảnh</label>
                                        <input type="file" value="<%=song.getPicture()%>" name="picture" />
                                        <%
                                            if (song.getPicture()!=null){
                                        %>
                                            <br>
                                            <img style="width: 150px" src="<%=request.getContextPath()%>/teamplate/admin/assets/img/<%=song.getPicture()%>" alt="0">
                                        <%
                                            }
                                        %>
                                    </div>
                                    <div class="form-group">
                                        <label for="preview">Mô tả</label>
                                        <textarea id="preview" class="form-control" rows="3" name="preview_text"><%=song.getPreview_text()%></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="detail">Chi tiết</label>
                                        <textarea id="detail" class="form-control" rows="10" cols="80" name="detail_text"><%=song.getDetail_text()%></textarea>
                                    </div>
                                    <%
                                            }
                                        }
                                    %>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
                                </form>
                                <script>
                                    var editor = CKEDITOR.replace('detail');
                                    CKFinder.setupCKEditor(editor, 'ckfinder/');
                                </script>
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
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/teamplate/admin/inc/footer.jsp" %>