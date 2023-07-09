<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>

<div class="card" style="width: 18rem; margin:auto" id="container">
  <div class="card-content">
    <img src="/resources/Img/꿀벌.jpg" class="card-img-top" alt="도서이미지">
    <div class="card-body">
      <h5 class="card-title">Book registration</h5>
      <p class="card-text">For book registration, please enter the title, author, publisher, and description of the book</p>
    </div>
  </div>
</div>



<form class="row g-3 needs-validation" novalidate style="width:80%; margin:auto" method="post" action="/book/write">
  <div class="col-md-4">
    <label for="title" class="form-label">Title</label>
    <input type="text" class="form-control" id="title" name="title" required>
    <div class="valid-feedback">
      Please enter the title of the book
    </div>
  </div>
  <div class="col-md-4">
    <label for="author" class="form-label">Author</label>
    <input type="text" class="form-control" id="author" name="author" required>
    <div class="valid-feedback">
      Please enter the author's name
    </div>
  </div>
  <div class="col-md-4">
    <label for="publisher" class="form-label">Publisher</label>
    <input type="text" class="form-control" id="publisher" name="publisher" required>
    <div class="valid-feedback">
      Please enter your publisher
    </div>
  </div>

    <div class="col-12" width=100%>
    <button class="btn btn-primary" type="submit">Registration</button>
  </div>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>