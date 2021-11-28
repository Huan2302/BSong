<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/teamplate/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <%
      SongModel song = (SongModel) request.getAttribute("song");
    %>
    <div class="article">
      <h1><%=song.getName()%></h1>
      <div class="clr"></div>
      <p>Ngày đăng: <%=song.getDate_create()%>. Lượt xem: <%=song.getCounter()%></p>
      <div class="vnecontent">
        <%=song.getPreview_text()%>
      </div>
      <img src="<%=request.getContextPath()%>/teamplate/admin/assets/img/<%=song.getPicture()%>" width="300" alt="">
      <div class="vnecontent">
          <%=song.getDetail_text()%>
      </div>
    </div>
    <div class="article">
      <h2>Bài viết liên quan</h2>
      <div class="clr"></div>
      <%
        ArrayList<SongModel> listRelated = (ArrayList<SongModel>) request.getAttribute("related");
        if(listRelated.size()>0){
          for (SongModel item : listRelated){
      %>
      <div class="comment"> <a href=""><img src="<%=request.getContextPath()%>/teamplate/admin/assets/img/<%=item.getPicture()%>" width="40" height="40" alt="" class="userpic" /></a>
        <h2><a href=""><%=item.getName()%></a></h2>
        <p><%=item.getPreview_text()%></p>
      </div>
      <%
          }
        }else {
      %>
      <h2>Không có bài hát liên quan</h2>
      <%
          }
      %>
    </div>
  </div>
  <div class="sidebar">
  <%@ include file="/teamplate/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/teamplate/public/inc/footer.jsp" %>
