<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!-- 헤더 -->

<%@include file="./header.jsp" %>


	<!-- 게시판 -->
	<div id="page-wrapper">
	    <div class="row">
	        <div class="col-lg-12">
	           <h1 class="page-header">Library</h1>
	        </div>
	        <!-- /.col-lg-12 -->
	    </div>
	    <!-- /.row -->
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    	Book List
	                </div>
	                <!-- 검색폼 -->
                	<form name="searchForm" action="/book/list">
		                <input type="hidden" name="pageNo" value="1">
		                <div class="form-inline text-center"> 
		                <p></p>
				            <div class="form-group">
				                <select class="form-control" name='searchField'>
				                    <option value='title' ${ cri.searchField eq "title" ? "selected" : ""}>Title</option>
				                    <option value='author' ${ cri.searchField eq "author" ? "selected" : ""}>Author</option>
				                </select>
				            </div>
				            <div class="form-group">
				                <input class="form-control" name='searchWord' value="${cri.searchWord }">
				            </div>
				            <button type="submit" class="btn btn-default">Search</button>
				        </div>
	                </form>
	                <!-- 검색폼끝 -->
	                <!-- /.panel-heading -->
	                <div class="panel-body">
	                Total : ${totalCnt } 
	                <p></p>
	                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
	                        <thead>
	                            <tr>
	                                <th></th>
	                                <th style="text-align:center" width="25%">TITLE</th>
	                                <th style="text-align:center" width="15%">AUTHOR</th>
	                                <th style="text-align:center" width="15%">PUBLISHER</th>
	                                <th style="text-align:center" width="15%">POSTDATE</th>
	                                <th style="text-align:center" width="15%">Rental Status</th>
	                                <th style="text-align:center" width="10%">VISITCOUNT</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                        <c:forEach items="${list }" var="book">
	                            <tr class="odd gradeX">
	                                <td><input type="checkbox" name="no" value="${book.no }"></td>
	                                <td><a href="/book/view?no=${book.no }">${book.title }</a></td>
	                                <td>${book.author }</td>
	                                <td class="center">${book.publisher }</td>
	                                <td class="center">${book.postdate }</td>
	                                <td class="center">${book.rentynStr }</td>
	                                <td class="center">${book.visitcount }</td>
	                            </tr>
	                        </c:forEach>
	                        </tbody>
	                    </table>
	                    
	                    <!-- 페이징 -->
	                    <div style="text-align:center">
	          			 	<%@include file="../common/pageNavi.jsp" %>
	          			</div>
	          			<!-- 버튼 -->
	          			<div style="text-align:right">
	          				 <button id="registerButton" onclick="location.href='/book/write'">Book Register</button>
	          				 <button id="registerButton" onclick="location.href='/book/delete'">Delete a book</button>
	          			</div>
	          			<p></p>
	          			 <!-- information -->
                             <div class="well">
                                <h4>DataTables Usage Information</h4>
                                <p>DataTables is a very flexible, advanced tables plugin for jQuery. In SB Admin, we are using a specialized version of DataTables built for Bootstrap 3. We have also customized the table headings to use Font Awesome icons in place of images. For complete documentation on DataTables, visit their website at <a target="_blank" href="https://datatables.net/">https://datatables.net/</a>.</p>
                                <a class="btn btn-default btn-lg btn-block" target="_blank" href="https://datatables.net/">View DataTables Documentation</a>
                            </div>
	                    <!-- /.table-responsive -->
	                </div>
	                <!-- /.panel-body -->
	            </div>
	            <!-- /.panel -->
	        </div>
	        <!-- /.col-lg-12 -->
	    </div>
	    
	    
	</div>
	<!-- 게시판 끝-->

<!-- 푸터 -->
<%@include file="./footer.jsp" %>