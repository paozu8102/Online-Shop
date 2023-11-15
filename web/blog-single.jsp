<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="Model.Product" %>
<!DOCTYPE html>
<html lang="en">
  <head>
  <%
    String path = request.getContextPath();
  %>
  
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
  font-size: 20px;
}

#myBtn {
  display: none;
  position: fixed;
  bottom: 20px;
  left: 20px;
  z-index: 99;
  font-size: 18px;
  border: none;
  outline: none;
  background-color: #82AE46;
  color: white;
  cursor: pointer;
  padding: 10px 15px;
  border-radius: 50px;
}

#myBtn:hover {
  background-color: #555;
}


.slider-container {
    width: 80%;
    margin: 0 auto;
    overflow: hidden;
    position: relative;
    margin-top:  10px;
}

.slider {
    width: 100%;
}

.slide {
    display: none;
}

.slide img {
    max-width: 100%;
    height: auto;
}

.thumbnails {
    text-align: center;
    margin-top: 10px;
}

.thumbnails img {
    max-width: 80px;
    height: auto;
    margin: 0 10px;
    cursor: pointer;
    transition: opacity 0.3s;
}

.thumbnails img:hover {
    opacity: 0.7;
}

/* Định dạng nút điều hướng */
.prev-button,
.next-button {
    position: absolute;
    top: 50%;
    padding: 5px; /* Kích thước nút nhỏ hơn */
    
    color: #fff;
    border: none;
    cursor: pointer;
    font-size: 20px; /* Kích thước chữ */
    transition: background-color 0.3s; /* Hiệu ứng hover */
    z-index: 1; /* Đảm bảo nút điều hướng nằm trên ảnh */
}

.prev-button {
    left: 10px;
    transform: translateY(-50%);
}

.next-button {
    right: 10px;
    transform: translateY(-50%);
}

/* Hiệu ứng hover */
.prev-button:hover,
.next-button:hover {
   color: white; /* Màu xám */
    opacity: 1; /* Độ mờ tối thiểu */
    background-color: gray;
}
</style>
    <title>Palette Joy - Bring art to your home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.css">
    
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">

    <link rel="stylesheet" href="css/aos.css">

    <link rel="stylesheet" href="css/ionicons.min.css">

    <link rel="stylesheet" href="css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="css/jquery.timepicker.css">

    
    <link rel="stylesheet" href="css/flaticon.css">
    <link rel="stylesheet" href="css/icomoon.css">
    <link rel="stylesheet" href="css/style.css">
     <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css">
  </head>
  <body class="goto-here">
      <button onclick="topFunction()" id="myBtn" title="Go to top">&#8679;</button>
<script>
// Function to save scroll positions for both page and the div
function saveScrollPositions(commentType, position) {
    sessionStorage.setItem('pageScrollPosition', window.scrollY);
    if (commentType === "comment") {
    sessionStorage.setItem('containerScrollPosition', 'none');
    }
    else if(commentType === "delete"){
    sessionStorage.setItem('containerScrollPosition', document.getElementById('commentContainer').scrollTop);
    }
    else{
    sessionStorage.setItem('position', position);
    }
}

// Function to retrieve and restore scroll positions for both page and the div
function restoreScrollPositions() {
    var pageScrollPosition = sessionStorage.getItem('pageScrollPosition');
    var containerScrollPosition = sessionStorage.getItem('containerScrollPosition');
    var commentContainer = document.getElementById('commentContainer');
    
    //scroll to comment
    if(sessionStorage.getItem('containerScrollPosition') !== null){
        if (containerScrollPosition === "none") {
            commentContainer.scrollTop = commentContainer.scrollHeight;
        }
        else commentContainer.scrollTop = containerScrollPosition;
    }
    else {
        var position = sessionStorage.getItem('position');
        var comments = document.getElementById(position);
        var yourComments = comments.querySelectorAll('.comment');
        var comment = yourComments[yourComments.length-1];
        var element = document.getElementById(comment.id);
        element.scrollIntoView();
    }
    //scroll to comment section
    if (pageScrollPosition) {
        window.scrollTo(0, pageScrollPosition);
    }
    sessionStorage.clear();
}

// Execute this function when the page is loaded
window.addEventListener('load', function() {
    restoreScrollPositions();
});


</script>
		<div class="py-1 bg-primary">
    	<div class="container">
    		<div class="row no-gutters d-flex align-items-start align-items-center px-md-0">
	    		<div class="col-lg-12 d-block">
		    		<div class="row d-flex">
		    			<div class="col-md pr-4 d-flex topper align-items-center">
					    	<div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-phone2"></span></div>
						    <span class="text">+0368686868</span>
					    </div>
					    <div class="col-md pr-4 d-flex topper align-items-center">
					    	<div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-paper-plane"></span></div>
						    <span class="text">Palettejoy@gmail.com</span>
					    </div>
					    <div class="col-md-5 pr-4 d-flex topper align-items-center text-lg-right">
						    <span class="text">3-5 Business days delivery &amp; Free Returns</span>
					    </div>
				    </div>
			    </div>
		    </div>
		  </div>
    </div>
    <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="home">Palette Joy</a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>

	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
                 <c:if test="${sessionScope.acc.roleID==null}">
    <li class="nav-item"><a href="home" class="nav-link">Home</a></li>

    <li class="nav-item"><a href="shop" class="nav-link">Shop</a></li>

    <li class="nav-item active"><a href="Posts" class="nav-link">Post</a></li>

    <li class="nav-item"><a href="login" class="nav-link">Sign In</a></li>

</c:if>
<c:if test="${sessionScope.acc.roleID==3}">
    <li class="nav-item"><a href="home" class="nav-link">Home</a></li>

    <li class="nav-item"><a href="shop" class="nav-link">Shop</a></li>


    <li class="nav-item active"><a href="Posts" class="nav-link">Post</a></li>

    <li class="nav-item"><a href="UserProfile" class="nav-link">Profile</a></li>
    <li class="nav-item"><a href="myorder" class="nav-link">My Order</a></li>
    <li class="nav-item"><a href="logout" class="nav-link">Sign Out</a></li> 

</c:if>

<li class="nav-item cta cta-colored"><a href="cart" class="nav-link"><span class="icon-shopping_cart"></span>[${cartlist.size()}]</a></li>
</ul>
</div>
</div>
</nav>
<!-- END nav -->
<style>
.slider-container1 {
  position: relative;
  width: 100%; /* Set your desired fixed width */
  height: 400px; /* Set your desired fixed height */
  overflow: hidden;
}

.slider1 {
  display: flex;
  width: 100%;
  height: 100%;
  transition: transform 0.5s ease-in-out;
}

.slide1 {
  flex: 0 0 100%;
  height: 100%;
  overflow: hidden;
}

img {
  width: 100%;
  height: 100%;
  object-fit: contain;
}

.slider-nav1 {
  top: 50%;
  left: 0;
  right: 0;
  text-align: center;
  padding-top: 5px;
  padding-bottom: 5px;
  background-color: none;
}

.prev-btn1, .next-btn1 {
  background: #82AE46;
  color: #fff;
  border: none;
  cursor: pointer;
  padding: 10px 20px;
  font-size: 18px;
}

.slide-number1 {
  color: black;
  font-size: 18px;
}

.invisible-text {
    color: transparent; /* Make the text transparent */
  }
  
.unselectable {
    user-select: none; /* Prevent text selection */
  }
  
.reply-box {
    display: none;
}

.btn:hover {
    background-color: #82AE46;
}

.btn{
    border-style: groove;
    border-color: #82AE46;
}

.comment-container {
        max-height: 400px; /* Set your desired fixed height */
        overflow-y: scroll; /* Enable vertical scrollbar if necessary */
        border: 1px solid #ccc; /* Add a border or customize styling as needed */
        padding: 10px;
    }
    
    #seemore:hover {
        text-decoration: underline;
    }
</style>
    
<script>
let slideIndex = 1;
showSlide(slideIndex);

function changeSlide(n) {
  showSlide(slideIndex += n);
}

function showSlide(n) {
  let slides = document.querySelectorAll(".slide1");
  if (n > slides.length) {slideIndex = 1}
  if (n < 1) {slideIndex = slides.length}
  for (let i = 0; i < slides.length; i++) {
    if (i === slideIndex - 1) {
      slides[i].style.display = "block"; // Show the current slide
    } else {
      slides[i].style.display = "none"; // Hide all other slides
    }
  }
  updateSlideNumber(slideIndex);
}

function updateSlideNumber(n) {
  let slideNumber = document.querySelector(".slide-number1");
  slideNumber.textContent = n;
}

function showAndHideReply(idComment, idButton){
  var elements = document.querySelectorAll('.'+idComment);
  var y = document.getElementById(idButton);
  if (y.textContent == "Hide Reply") {
    y.textContent = "Show Reply";
  }
  else if (y.textContent == "Show Reply") {
    y.textContent = "Hide Reply";
  }
  for (var i = 0; i < elements.length; i++) {
    var x = elements[i];
    if (x.style.display === "none") {
    x.style.display = "block";
    } else {
    x.style.display = "none";
  }
  }
}

function showReply(replyBoxId, bottom, userName) {
    var replyBox = document.getElementById(replyBoxId);
        if (!replyBox.style.display || replyBox.style.display === "none") {
            replyBox.style.display = "block";
        } else {
            replyBox.style.display = "none";
        }
    var textarea = replyBox.querySelector("textarea");
    if (textarea) {
        textarea.value = "Reply to "+userName+": ";
    }
    if (bottom == "true") {
        scrollToBottom();
    }
}

function scrollToBottom() {
        var commentContainer = document.getElementById('commentContainer');
        commentContainer.scrollTop = commentContainer.scrollHeight;
    }   

function submitDeleteForm(formId) {
    var form = document.getElementById(formId);
    saveScrollPositions('delete', 'delete');
    if (confirm('Are you sure you want to delete the comment?')) {
        form.submit();
    }
}

</script>

<div class="hero-wrap hero-bread" style="background-image: url('images/bg_1.jpg');">
      <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
          <div class="col-md-9 ftco-animate text-center">
          	<p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home</a></span> <span>Post</span></p>
            <h1 class="mb-0 bread">Post</h1>
          </div>
        </div>
      </div>
    </div>
    <section class="ftco-section ftco-degree-bg">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 ftco-animate">
              <h2 class="mb-3" style="font-weight: bold">${post.Title}</h2>
          
<c:if test="${fn:length(imageList) > 0}">    
<div class="slider-container1">
  <div class="slider1">
      <c:forEach items="${imageList}" var="image">
          <div class="slide1">
            <img src="${image.getImageUrl()}">
          </div>
      </c:forEach>
  </div>
</div>
<div class="slider-nav1">
    <button class="prev-btn1" onclick="changeSlide(-1)">&#10094;</button>
    <span class="slide-number1">1</span><span class="slide-number1">/${fn:length(imageList)}</span>
    <button class="next-btn1" onclick="changeSlide(1)">&#10095;</button>
</div>
</c:if> 
            <c:set var="user" value="${sessionScope.user}"/>
            <p style="font-size: 30px; color: black">${post.Content}</p>
            <hr>
            <div class="about-author d-flex p-4 bg-light" style="height: 200px">
              <div class="bio align-self-md-center mr-4">
                  <img style="height: 300px; width: auto" src="${userPost.getAvatar()}" alt="Image placeholder" class="img-fluid mb-4">
              </div>
              <div class="desc align-self-md-center">
                  <c:if test="${user.getUserID() eq userPost.getUserID()}">
                      <h3 style="padding-top: 80px">You</h3>
                  </c:if>
                  <c:if test="${user.getUserID() ne userPost.getUserID()}">
                      <h3 style="padding-top: 80px">${userPost.getUserName()}</h3>
                  </c:if>
                <p class="invisible-text unselectable">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus itaque, 
                    autem necessitatibus voluptate quod mollitia delectus aut, sunt placeat nam vero 
                </p>
              </div>
            </div>
<!--start comment section-->
            <c:if test="${user eq null}">
                <h3 class="mb-5" style="margin-top: 40px">You need to log in to comment</h3>
            </c:if>
            <c:if test="${user ne null}">
            <div class="pt-5 mt-5">
              <h3 class="mb-5">${CommentNumber} ${CommentNumber > 1 ? "Comments":"Comment"} </h3>
            <form action="comment" method="post" onsubmit="saveScrollPositions('comment', 'delete');">
              <input type="hidden" name="action" value="comment">
              <input type="hidden" name="userID" value="${user.getUserID()}">
              <input type="hidden" name="objectID" value="${param.id}">
              <div style="margin-bottom: 30px;" id="comment">
                <textarea name="content" style="width: 100%; font-size: 20px; margin-top: -30px;" class="reply-text" placeholder="Enter your comment here..."></textarea>
                <div class="btn-container">
                    <button style="font-size: 18px" type="submit" class="btn" id="submitBtn${loop.index}">Submit</button>
                </div>
              </div>
            </form>
        <c:if test="${CommentNumber > 0}">
            <div class="comment-container" id="commentContainer">
                <c:forEach items="${rootCommentList}" varStatus="loop" var="comment">
              <ul class="comment-list">
                  <li  class="comment" id="position${comment.getCommentID()}">
                  <div class="vcard bio">
                    <img src="${rootCommentList[loop.index].getAvatar()}" alt="Image placeholder">
                  </div>
                  <div class="comment-body" id="comment${rootCommentList[loop.index].getCommentID()}">
                     <c:choose>
                              <c:when test="${user.getUserID() eq rootCommentList[loop.index].getUserID()}">
                                  <h3>You</h3>
                              </c:when>
                              <c:otherwise>
                                  <h3>${rootCommentList[loop.index].getUserName()}</h3>
                              </c:otherwise>
                     </c:choose>
                    <div class="meta">${rootCommentList[loop.index].getCommentDate()}</div>
                    <p>${rootCommentList[loop.index].getCommentContent()}</p>
                    <p style="display: inline">
                        <c:choose>
                            <c:when test="${loop.last && fn:length(repCommentList[loop.index]) == 0}">
                                <a style="display: inline" href="javascript:showReply('replyBox'+'${comment.getCommentID()}', 'true', '${comment.getUserName()}');" class="reply">Reply</a>
                            </c:when>
                            <c:otherwise>
                                <a style="display: inline" href="javascript:showReply('replyBox'+'${comment.getCommentID()}', 'false', '${comment.getUserName()}');" class="reply">Reply</a>
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <c:if test="${fn:length(repCommentList[loop.index])>0}">
                        <p style="display: inline"><a id="hideShow${rootCommentList[loop.index].getCommentID()}" href="javascript:showAndHideReply('rep'+'${rootCommentList[loop.index].getCommentID()}', 'hideShow'+'${rootCommentList[loop.index].getCommentID()}');" class="reply">Hide Reply</a></p>
                    </c:if>
                    <c:if test="${user.getUserID() eq rootCommentList[loop.index].getUserID()}">
                    <form onsubmit="saveScrollPositions('delete', 'delete');" id="delete${rootCommentList[loop.index].getCommentID()}" action="comment" method="post" style="display: inline" class="reply">
                        <input type="hidden" name="action" value="commentDelete">
                        <input type="hidden" name="objectID" value="${param.id}">
                        <input type="hidden" name="commentID" value="${rootCommentList[loop.index].getCommentID()}">
                        <a style="cursor: pointer" onclick="submitDeleteForm('delete'+'${comment.getCommentID()}');">Delete</a>
                    </form>
                    </c:if>
                <form action="comment" method="post" onsubmit="saveScrollPositions('commentRep', 'position'+'${comment.getCommentID()}');">
                    <div id="replyBox${rootCommentList[loop.index].getCommentID()}" class="reply-box" >
                        <input type="hidden" name="action" value="commentRep">
                        <input type="hidden" name="userID" value="${user.getUserID()}">
                        <input type="hidden" name="objectID" value="${param.id}">
                        <input type="hidden" name="commentRepID" value="${rootCommentList[loop.index].getCommentID()}">
                        <textarea name="content" style="width: 100%" class="reply-text" placeholder="Enter your reply here..."></textarea>
                        <div class="btn-container">
                            <button type="submit" class="btn" id="submitBtn${loop.index}">Submit</button>
                            <button type="button" onclick="showReply('replyBox'+'${rootCommentList[loop.index].getCommentID()}', 'false');" class="btn" id="cancelBtn${loop.index}">Cancel</button>
                        </div>
                    </div>
                </form>
                  </div>
                  <c:if test="${fn:length(repCommentList[loop.index])>0}">
                  <c:forEach items="${repCommentList[loop.index]}" var="repComment" varStatus="repIndex">
                  <ul class="children rep${rootCommentList[loop.index].getCommentID()}">
                      <li class="comment" id="comment${repComment.getCommentID()}">
                      <div class="vcard bio">
                        <img src="${repComment.getAvatar()}" alt="Image placeholder">
                      </div>
                      <div class="comment-body">
                          <c:choose>
                              <c:when test="${user.getUserID() eq repComment.getUserID()}">
                                  <h3 class="yourComment" id="${repComment.getCommentID()}">You</h3>
                              </c:when>
                              <c:otherwise>
                                  <h3>${repComment.getUserName()}</h3>
                              </c:otherwise>
                          </c:choose>
                        <div class="meta">${repComment.getCommentDate()}</div>
                        <p>${repComment.getCommentContent()}</p>
                        <c:choose>
                            <c:when test="${repIndex.last && loop.last}">
                                <p style="display: inline;"><a href="javascript:showReply('replyBox'+'${repComment.getCommentID()}', 'true', '${repComment.getUserName()}');" class="reply">Reply</a></p>
                            </c:when>
                            <c:otherwise>
                                <p style="display: inline;"><a href="javascript:showReply('replyBox'+'${repComment.getCommentID()}', 'false', '${repComment.getUserName()}');" class="reply">Reply</a></p>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${user.getUserID() eq repComment.getUserID()}">
                        <form onsubmit="saveScrollPositions('delete', 'delete');" id="delete${repComment.getCommentID()}" action="comment" method="post" style="display: inline" class="reply">
                            <input type="hidden" name="action" value="commentDelete">
                            <input type="hidden" name="objectID" value="${param.id}">
                            <input type="hidden" name="commentID" value="${repComment.getCommentID()}">
                            <a style="cursor: pointer" onclick="submitDeleteForm('delete'+'${repComment.getCommentID()}');">Delete</a>
                        </form>
                        </c:if>
                    <form action="comment" method="post" onsubmit="saveScrollPositions('commentRep', 'position'+'${comment.getCommentID()}');">
                        <div class="reply-box" id="replyBox${repComment.getCommentID()}">
                            <input type="hidden" name="action" value="commentRep">
                            <input type="hidden" name="userID" value="${user.getUserID()}">
                            <input type="hidden" name="objectID" value="${param.id}">
                            <input type="hidden" name="commentRepID" value="${rootCommentList[loop.index].getCommentID()}">
                            <textarea name="content" style="width: 100%" class="reply-text" placeholder="Enter your reply here..."></textarea>
                          <div class="btn-container">
                            <button type="submit" class="btn" id="submitBtn${loop.index}">Submit</button>
                            <button type="button" onclick="showReply('replyBox'+'${repComment.getCommentID()}', 'false', '${repComment.getUserName()}');" class="btn" id="cancelBtn${loop.index}">Cancel</button>
                          </div>
                        </div>
                    </form>
                      </div>
                    </li>
                  </ul>
                  </c:forEach>
                  </c:if>  
                </li>
              </ul>
              </c:forEach>
            </div>
        </c:if>
        </div>
        </c:if>
              <!-- END comment-list -->
                
          </div> <!-- .col-md-8 -->
          <div class="col-lg-4 sidebar ftco-animate">
<!--            <div class="sidebar-box">
              <form action="#" class="search-form">
                <div class="form-group">
                  <span class="icon ion-ios-search"></span>
                  <input type="text" class="form-control" placeholder="Search...">
                </div>
              </form>
            </div>-->
            <div class="sidebar-box ftco-animate">
            	<h3 class="heading">Categories</h3>
              <ul class="categories">
                <c:forEach items="${cateAndPostNumList}" var="object">
                    <li><a href="#">${object.CategoryName}<span>(${object.NumberOfPosts})</span></a></li>
                </c:forEach>
              </ul>
            </div>

            <div class="sidebar-box ftco-animate">
              <h3 class="heading">Recent Post</h3>
              <c:forEach items="${recentPostList}" var="object">
                  <div class="block-21 mb-4 d-flex" style="border: groove; padding: 5px;">
                <c:if test="${object.ImageUrl ne null}">
                <a class="blog-img mr-4" style="background-image: url(${object.ImageUrl});"></a>
                </c:if>
                <div class="text">
                  <h3 class="heading-1"><a href="<%=path%>/PostSingle?id=${object.BlogID}">${object.Title}</a></h3>
                  <div class="meta">
                    <div><a href="<%=path%>/PostSingle?id=${object.BlogID}"><span class="icon-calendar"></span>${object.DateTime}</a></div>
                    <div><a href="<%=path%>/PostSingle?id=${object.BlogID}"><span class="icon-person"></span>${object.UserName}</a></div>
                    <div><a href="<%=path%>/PostSingle?id=${object.BlogID}"><span class="icon-chat"></span>${object.CommentNumber}</a></div>
                  </div>
                </div>
              </div>
              </c:forEach>
              <h3 id="seemore" style="color: #82AE46; cursor: pointer"><a href="<%=path%>/Posts">See more post</a></h3>
            </div>


          </div>

        </div>
      </div>
    </section> <!-- .section -->

    <footer class="ftco-footer ftco-section bg-light">
<%@include file="template/footer.jsp" %>        
