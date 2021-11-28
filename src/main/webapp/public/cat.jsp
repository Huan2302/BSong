<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/teamplate/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
      <%
          CategoryModel cat = (CategoryModel) request.getAttribute("catName");
      %>
    <div class="article">
		<h1><%=cat.getName()%></h1>
    </div>
      <%
          ArrayList<SongModel> list = (ArrayList<SongModel>) request.getAttribute("catId");
          if (list.size() > 0){
              int i=0;
              for (SongModel item:list){
                  i++;
      %>

      <div class="article">
          <h2><a href="<%=request.getContextPath()%>/detail?id=<%=item.getId()%>" title="Đổi thay"><%=item.getName()%></a></h2>
          <p class="infopost">Ngày đăng: <%=item.getDate_create()%>. Lượt xem: <%=item.getCounter()%> <a href="#" class="com"><span><%=i%></span></a></p>
          <div class="clr"></div>
          <a href="<%=request.getContextPath()%>/detail?id=<%=item.getId()%>">
              <div class="img"><img src="<%=request.getContextPath()%>/teamplate/admin/assets/img/<%=item.getPicture()%>" width="177"  alt="Đổi thay" class="fl" /></div>
          </a>
          <div class="post_content">
              <p><%=item.getPreview_text()%></p>
              <p class="spec"><a href="<%=request.getContextPath()%>/detail?id=<%=item.getId()%>" class="rm">Chi tiết &raquo;</a></p>
          </div>
          <div class="clr"></div>
      </div>
      <%
              }
          }else {
              if (request.getAttribute("err")!=null){
                  if (request.getAttribute("err").equals("1")){
                      out.print("Id thương hiệu không tồn tại !!!");
                  }
              }
      %>
      <div class="article">
        <h2>Không có đồng hồ</h2>
      </div>
      <%
          }
      %>
    <p class="pages"><small>Trang 1 của 3</small>
    <span>1</span>
    <a href="">2</a>
    <a href="">3</a>
    <a href="#">&raquo;</a></p>
  </div>
  <div class="sidebar">
      <%@ include file="/teamplate/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/teamplate/public/inc/footer.jsp" %>