<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    

<%@include file="./header.jsp" %>

	<!-- 게시판 -->
	<div id="page-wrapper">
	<form name="viewFrm" method="get">
	<input type="hidden" name="no" value="${book.no }" readonly>
	<input type="hidden" name="title" value="${book.title }" readonly>
	<div class="row">
		<p></p>
        <div class="col-lg-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    ${book.title }
                </div>
                <div class="panel-body">
                    <p>${book.info }</p>
                </div>
                <div class="panel-footer">
                    ${book.author }
                </div>
            </div>
            	<div class="d-grid gap-2 d-md-flex justify-content-md-center" style="text-align:right">
						<button type="submit" class="btn btn-primary btn-sm" onclick="requestAction('/book/edit')">수정하기</button>
						<button type="button" class="btn btn-secondary btn-sm" onclick="requestAction('/book/delete')">삭제하기</button>
						<button type="reset" class="btn btn-secondary btn-sm">작성 취소</button>
						<button type="button" class="btn btn-primary btn-sm" onclick="location.href='./list';">목록 보기</button>
				</div>	
        </div>
	</div>    
	</form>
	</div>
	<!-- 게시판 끝-->
	
<%@include file="./footer.jsp" %>