<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bsong.model.CategoryModel" %>
<%@ page import="com.bsong.dao.impl.CategoryDao" %>
<%@ page import="com.bsong.model.SongModel" %>
<%@ page import="com.bsong.dao.impl.SongDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="searchform">
  <form id="formsearch" name="formsearch" method="post" action="#">
    <span>
    <input name="editbox_search" class="editbox_search" id="editbox_search" maxlength="80" value="Tìm kiếm bài hát" type="text" />
    </span>
    <input name="button_search" src="<%=request.getContextPath()%>/teamplate/public/images/search.jpg" class="button_search" type="image" />
  </form>
</div>
<div class="clr"></div>
<div class="gadget">
  <h2 class="star">Danh mục bài hát</h2>
  <div class="clr"></div>
  <ul class="sb_menu">
    <%
      ArrayList<CategoryModel> listCat = (ArrayList<CategoryModel>) new CategoryDao().findAll();
      if (listCat.size()>0){
        for (CategoryModel item : listCat){
    %>
    <li><a href="<%=request.getContextPath()%>/cat?id=<%=item.getId()%>"><%=item.getName()%></a></li>
    <%
        }
      }else{
    %>
    <li><p>Chưa có danh mục bài hát</p></li>
    <%
      }
    %>
  </ul>
</div>

<div class="gadget">
  <h2 class="star"><span>Bài hát mới</span></h2>
  <div class="clr"></div>
  <ul class="ex_menu">
    <%
      ArrayList<SongModel> listSong = (ArrayList<SongModel>) new SongDao().findAll(6);
      if (listSong.size()>0){
        for (SongModel item : listSong){
    %>
    <li><a href="<%=request.getContextPath()%>/detail?id=<%=item.getId()%>"><%=item.getName()%></a><br />
      <%=item.getPreview_text()%></li>
    <%
      }
    }else{
    %>
    <li><p>Chưa có bài hát mới</p></li>
    <%
      }
    %>

  </ul>
</div>