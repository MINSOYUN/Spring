<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 
${pageDto }
/${pageDto.criteria.pageNo }
/${pageDto.criteria.searchField }
/${pageDto.criteria.searchWord } 
-->

<form method="get" name="searchForm" action="/board/list">
	<input type="hidden" name="pageNo" value="1">
	<input type="hidden" name="bno">
	<!-- 가운데 정렬 -->
		<div class="row g-3 justify-content-center">
		 	 <div class="col-auto">
				<select name="searchField" class="form-select" aria-label="Default select example">
				  <option selected>Select option</option>
				  <option value="title" ${ pageDto.criteria.searchField eq "title" ? "selected" : ""}>제목</option>
				  <option value="content" ${ pageDto.criteria.searchField eq "content" ? "selected" : ""}>내용</option>
				  <option value="writer" ${ pageDto.criteria.searchField eq "writer" ? "selected" : ""}>작성자</option>
				</select>
		  </div>
		  <!-- col-sm-5 : 칸 넓게 -->
		  <div class="col-sm-5">
			    <label for="searchWord" class="visually-hidden">text</label>
			    <input type="text" class="form-control" id="searchWord" name="searchWord" placeholder="Search Text" value="${pageDto.criteria.searchWord }">
		  </div>
		  <div class="col-auto">
		   		<button type="submit" class="btn btn-primary mb-3">Search</button>
		  </div>
	  </div>
</form>
  
</body>
</html>