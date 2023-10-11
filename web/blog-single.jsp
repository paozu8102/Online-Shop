<%@include file="template/header.jsp" %>
<li class="nav-item"><a href="index.jsp" class="nav-link">Home</a></li>
	          <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Shop</a>
              <div class="dropdown-menu" aria-labelledby="dropdown04">
              	<a class="dropdown-item" href="shop.jsp">Shop</a>
              	<a class="dropdown-item" href="wishlist.jsp">Wishlist</a>
                <a class="dropdown-item" href="product-single.jsp">Single Product</a>
                <a class="dropdown-item" href="cart.jsp">Cart</a>
                <a class="dropdown-item" href="checkout.jsp">Checkout</a>
              </div>
            </li>
	          <li class="nav-item"><a href="about.jsp" class="nav-link">About</a></li>
	          <li class="nav-item active"><a href="blog.jsp" class="nav-link">Blog</a></li>
	          <li class="nav-item"><a href="contact.jsp" class="nav-link">Contact</a></li>
                  <c:if test="${sessionScope.acc==null}"><li class="nav-item active"><a href="login" class="nav-link">Sign In</a></li></c:if>
                        <c:if test="${sessionScope.acc!=null}"><li><a class="getstarted scrollto" href="logout"></a></li>
                                                                 <li class="nav-item active"><a href="UserProfile" class="nav-link">Profile</a></li>
                                    <li class="nav-item active"><a href="logout" class="nav-link">Sign Out</a></li> 
                                                            </c:if>
	          <li class="nav-item cta cta-colored"><a href="cart.jsp" class="nav-link"><span class="icon-shopping_cart"></span>[0]</a></li>

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

function showAndHideReply(id){
  var elements = document.querySelectorAll('.'+id);
  var y = document.getElementById('hideShow');
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

function showReply(replyBoxId, bottom) {
    var replyBox = document.getElementById(replyBoxId);
        if (!replyBox.style.display || replyBox.style.display === "none") {
            replyBox.style.display = "block";
        } else {
            replyBox.style.display = "none";
        }
    if (bottom == "true") {
        scrollToBottom();
    }
}

function scrollToBottom() {
        var commentContainer = document.getElementById('commentContainer');
        commentContainer.scrollTop = commentContainer.scrollHeight;
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
            <p style="font-size: 30px; color: black">${post.Content}</p>
            <hr>
            <div class="about-author d-flex p-4 bg-light" style="height: 200px">
              <div class="bio align-self-md-center mr-4">
                  <img style="height: 300px; width: auto" src="${user.getAvatar()}" alt="Image placeholder" class="img-fluid mb-4">
              </div>
              <div class="desc align-self-md-center">
                  <h3 style="padding-top: 80px">${user.getUserName()}</h3>
                <p class="invisible-text unselectable">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus itaque, 
                    autem necessitatibus voluptate quod mollitia delectus aut, sunt placeat nam vero 
                </p>
              </div>
            </div>

            <c:set var="user" value="${sessionScope.user}"/>
            <div class="pt-5 mt-5">
              <h3 class="mb-5">${CommentNumber} ${CommentNumber > 1 ? "Comments":"Comment"}</h3>
              
              <div style="margin-bottom: 30px;">
                <textarea style="width: 100%; font-size: 20px; margin-top: -30px;" class="reply-text" placeholder="Enter your comment here..."></textarea>
                <div class="btn-container">
                    <button style="font-size: 18px" type="submit" class="btn" id="submitBtn${loop.index}">Submit</button>
                </div>
              </div>
              
        <c:if test="${CommentNumber > 0}">
            <div class="comment-container" id="commentContainer">
              <c:forEach items="${rootCommentList}" varStatus="loop">
              <ul class="comment-list">
                <li class="comment">
                  <div class="vcard bio">
                    <img src="${rootCommentList[loop.index].getAvatar()}" alt="Image placeholder">
                  </div>
                  <div class="comment-body">
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
                                <a href="javascript:showReply('replyBox'+'${rootCommentList[loop.index].getCommentID()}', 'true');" class="reply">Reply</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:showReply('replyBox'+'${rootCommentList[loop.index].getCommentID()}', 'false');" class="reply">Reply</a>
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <c:if test="${fn:length(repCommentList[loop.index])>0}">
                        <p style="display: inline"><a id="hideShow" href="javascript:showAndHideReply('rep'+'${rootCommentList[loop.index].getCommentID()}');" class="reply">Hide Reply</a></p>
                    </c:if>
                    <div id="replyBox${rootCommentList[loop.index].getCommentID()}" class="reply-box" >
                        <textarea style="width: 100%" class="reply-text" placeholder="Enter your reply here..."></textarea>
                        <div class="btn-container">
                            <button type="submit" class="btn" id="submitBtn${loop.index}">Submit</button>
                            <button onclick="showReply('replyBox'+'${rootCommentList[loop.index].getCommentID()}', 'false');" class="btn" id="cancelBtn${loop.index}">Cancel</button>
                        </div>
                    </div>
                  </div>
                  <c:if test="${fn:length(repCommentList[loop.index])>0}">
                  <c:forEach items="${repCommentList[loop.index]}" var="repComment" varStatus="repIndex">
                  <ul class="children rep${rootCommentList[loop.index].getCommentID()}">
                    <li class="comment">
                      <div class="vcard bio">
                        <img src="${repComment.getAvatar()}" alt="Image placeholder">
                      </div>
                      <div class="comment-body">
                          <c:choose>
                              <c:when test="${user.getUserID() eq repComment.getUserID()}">
                                  <h3>You</h3>
                              </c:when>
                              <c:otherwise>
                                  <h3>${repComment.getUserName()}</h3>
                              </c:otherwise>
                          </c:choose>
                        <div class="meta">${repComment.getCommentDate()}</div>
                        <p>${repComment.getCommentContent()}</p>
                        <c:choose>
                            <c:when test="${repIndex.last && loop.last}">
                                <p><a href="javascript:showReply('replyBox'+'${repComment.getCommentID()}', 'true');" class="reply">Reply</a></p>
                            </c:when>
                            <c:otherwise>
                                <p><a href="javascript:showReply('replyBox'+'${repComment.getCommentID()}', 'false');" class="reply">Reply</a></p>
                            </c:otherwise>
                        </c:choose>
                        <div class="reply-box" id="replyBox${repComment.getCommentID()}">
                            <textarea style="width: 100%" class="reply-text" placeholder="Enter your reply here..."></textarea>
                            <div class="btn-container">
                            <button class="btn" id="submitBtn${loop.index}">Submit</button>
                            <button onclick="showReply('replyBox'+'${repComment.getCommentID()}', 'false');" class="btn" id="cancelBtn${loop.index}">Cancel</button>
                            </div>
                        </div>
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
              <!-- END comment-list -->
            </div>
                
          </div> <!-- .col-md-8 -->
          <div class="col-lg-4 sidebar ftco-animate">
            <div class="sidebar-box">
              <form action="#" class="search-form">
                <div class="form-group">
                  <span class="icon ion-ios-search"></span>
                  <input type="text" class="form-control" placeholder="Search...">
                </div>
              </form>
            </div>
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
            </div>

<!--            <div class="sidebar-box ftco-animate">
              <h3 class="heading">Tag Cloud</h3>
              <div class="tagcloud">
                <a href="#" class="tag-cloud-link">fruits</a>
                <a href="#" class="tag-cloud-link">tomatoe</a>
                <a href="#" class="tag-cloud-link">mango</a>
                <a href="#" class="tag-cloud-link">apple</a>
                <a href="#" class="tag-cloud-link">carrots</a>
                <a href="#" class="tag-cloud-link">orange</a>
                <a href="#" class="tag-cloud-link">pepper</a>
                <a href="#" class="tag-cloud-link">eggplant</a>
              </div>
            </div>-->

            <div class="sidebar-box ftco-animate">
              <h3 class="heading">Paragraph</h3>
              <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus itaque, autem necessitatibus voluptate quod mollitia delectus aut, sunt placeat nam vero culpa sapiente consectetur similique, inventore eos fugit cupiditate numquam!</p>
            </div>
          </div>

        </div>
      </div>
    </section> <!-- .section -->

    <footer class="ftco-footer ftco-section bg-light">
<%@include file="template/footer.jsp" %>        
