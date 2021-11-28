<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/teamplate/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
      <%
          SongModel songs = (SongModel) request.getAttribute("songs");
          ArrayList<SongModel> list = (ArrayList<SongModel>) songs.getListResult();
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
      %>
      <br><br>
      <h3>Không có bài hát nào</h3>
      <%
              }
      %>
      <form action="<%=request.getContextPath()%>/home" id="formSubmit" method="get">
          <div class="row">
              <div class="col-sm-9" style="text-align: right;">
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
  <div class="sidebar">
      <%@ include file="/teamplate/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
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
    document.getElementById("home").classList.add('active');
</script>
<%@ include file="/teamplate/public/inc/footer.jsp" %>
