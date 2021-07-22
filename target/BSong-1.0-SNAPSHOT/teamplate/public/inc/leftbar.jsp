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
    <li><a href="#">Nhạc trẻ</a></li>
    <li><a href="#">Nhạc trữ tình</a></li>
    <li><a href="#">Rock Việt</a></li>
    <li><a href="#">Nhạc Trịnh</a></li>
    <li><a href="#">Hip Hop Việt</a></li>
  </ul>
</div>

<div class="gadget">
  <h2 class="star"><span>Bài hát mới</span></h2>
  <div class="clr"></div>
  <ul class="ex_menu">
    <li><a href="#">Mùa Đã Xa (Single)</a><br />
      Donec libero. Suspendisse bibendum</li>
    <li><a href="#">Chúng Ta Là Bạn Bè</a><br />
      Phasellus suscipit, leo a pharetra</li>
    <li><a href="#">Có Một Người Để Yêu</a><br />
      Tellus eleifend magna eget</li>
    <li><a href="#">Chuyện tình Lan và Điệp</a><br />
      Curabitur vel urna in tristique</li>
    <li><a href="#">Xanh tươi Việt Nam</a><br />
      Cras id urna orbi tincidunt orci ac</li>
    <li><a href="#">Tuổi hồng</a><br />
      purus nec placerat bibendum</li>
  </ul>
</div>