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
.slider-container {
  position: relative;
  width: 100%; /* Set your desired fixed width */
  height: 400px; /* Set your desired fixed height */
  overflow: hidden;
}

.slider {
  display: flex;
  width: 100%;
  height: 100%;
  transition: transform 0.5s ease-in-out;
}

.slide {
  flex: 0 0 100%;
  height: 100%;
  overflow: hidden;
}

img {
  width: 100%;
  height: 100%;
  object-fit: contain;
}

.slider-nav {
  top: 50%;
  left: 0;
  right: 0;
  text-align: center;
  padding-top: 5px;
  padding-bottom: 5px;
  background-color: none;
}

.prev-btn, .next-btn {
  background: #82AE46;
  color: #fff;
  border: none;
  cursor: pointer;
  padding: 10px 20px;
  font-size: 18px;
}

.slide-number {
  color: black;
  font-size: 18px;
  
}
</style>
    
<script>
let slideIndex = 1;
showSlide(slideIndex);

function changeSlide(n) {
  showSlide(slideIndex += n);
}

function showSlide(n) {
  let slides = document.querySelectorAll(".slide");
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
  let slideNumber = document.querySelector(".slide-number");
  slideNumber.textContent = n;
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
<div class="slider-container">
  <div class="slider">
      <c:forEach items="${imageList}" var="image">
          <div class="slide">
            <img src="${image.getImageUrl()}">
          </div>
      </c:forEach>
  </div>
</div>
<div class="slider-nav">
    <button class="prev-btn" onclick="changeSlide(-1)">&#10094;</button>
    <span class="slide-number">1</span><span class="slide-number">/${fn:length(imageList)}</span>
    <button class="next-btn" onclick="changeSlide(1)">&#10095;</button>
</div>
</c:if> 
            <p style="font-size: 30px; color: black">${post.Content}</p>
            <hr>
            <div class="about-author d-flex p-4 bg-light">
              <div class="bio align-self-md-center mr-4">
                <img src="images/person_1.jpg" alt="Image placeholder" class="img-fluid mb-4">
              </div>
              <div class="desc align-self-md-center">
                <h3>Lance Smith</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus itaque, autem necessitatibus voluptate quod mollitia delectus aut, sunt placeat nam vero culpa sapiente consectetur similique, inventore eos fugit cupiditate numquam!</p>
              </div>
            </div>


            <div class="pt-5 mt-5">
              <h3 class="mb-5">${CommentNumber} ${CommentNumber > 1 ? "Comments":"Comment"}</h3>
              <c:if test="${CommentNumber > 0}">
              <c:forEach items="${rootCommentList}" varStatus="loop">
              <ul class="comment-list">
                <li class="comment">
                  <div class="vcard bio">
                    <img src="${rootCommentList[loop.index].getAvatar()}" alt="Image placeholder">
                  </div>
                  <div class="comment-body">
                    <h3>${rootCommentList[loop.index].getUserName()}</h3>
                    <div class="meta">${rootCommentList[loop.index].getCommentDate()}</div>
                    <p>${rootCommentList[loop.index].getCommentContent()}</p>
                    <p><a href="#" class="reply">Reply</a></p>
                  </div>
                  <c:if test="${fn:length(repCommentList[loop.index])>0}">
                  <c:forEach items="${repCommentList[loop.index]}" var="repComment">
                  <ul class="children">
                    <li class="comment">
                      <div class="vcard bio">
                        <img src="${repComment.getAvatar()}" alt="Image placeholder">
                      </div>
                      <div class="comment-body">
                        <h3>${repComment.getUserName()}</h3>
                        <div class="meta">${repComment.getCommentDate()}</div>
                        <p>${repComment.getCommentContent()}</p>
                        <p><a href="#" class="reply">Reply</a></p>
                      </div>

                    </li>                      
                  </ul>
                  </c:forEach>
                  </c:if>  
                </li>
              </ul>
              </c:forEach>
              </c:if>
              <!-- END comment-list -->
              
              <div class="comment-form-wrap pt-5">
                <h3 class="mb-5">Leave a comment</h3>
                <form action="#" class="p-5 bg-light">
                  <div class="form-group">
                    <label for="name">Name *</label>
                    <input type="text" class="form-control" id="name">
                  </div>
                  <div class="form-group">
                    <label for="email">Email *</label>
                    <input type="email" class="form-control" id="email">
                  </div>
                  <div class="form-group">
                    <label for="website">Website</label>
                    <input type="url" class="form-control" id="website">
                  </div>

                  <div class="form-group">
                    <label for="message">Message</label>
                    <textarea name="" id="message" cols="30" rows="10" class="form-control"></textarea>
                  </div>
                  <div class="form-group">
                    <input type="submit" value="Post Comment" class="btn py-3 px-4 btn-primary">
                  </div>

                </form>
              </div>
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
                <li><a href="#">Vegetables <span>(12)</span></a></li>
                <li><a href="#">Fruits <span>(22)</span></a></li>
                <li><a href="#">Juice <span>(37)</span></a></li>
                <li><a href="#">Dries <span>(42)</span></a></li>
              </ul>
            </div>

            <div class="sidebar-box ftco-animate">
              <h3 class="heading">Recent Blog</h3>
              <div class="block-21 mb-4 d-flex">
                <a class="blog-img mr-4" style="background-image: url(images/image_1.jpg);"></a>
                <div class="text">
                  <h3 class="heading-1"><a href="#">Even the all-powerful Pointing has no control about the blind texts</a></h3>
                  <div class="meta">
                    <div><a href="#"><span class="icon-calendar"></span> April 09, 2019</a></div>
                    <div><a href="#"><span class="icon-person"></span> Admin</a></div>
                    <div><a href="#"><span class="icon-chat"></span> 19</a></div>
                  </div>
                </div>
              </div>
              <div class="block-21 mb-4 d-flex">
                <a class="blog-img mr-4" style="background-image: url(images/image_2.jpg);"></a>
                <div class="text">
                  <h3 class="heading-1"><a href="#">Even the all-powerful Pointing has no control about the blind texts</a></h3>
                  <div class="meta">
                    <div><a href="#"><span class="icon-calendar"></span> April 09, 2019</a></div>
                    <div><a href="#"><span class="icon-person"></span> Admin</a></div>
                    <div><a href="#"><span class="icon-chat"></span> 19</a></div>
                  </div>
                </div>
              </div>
              <div class="block-21 mb-4 d-flex">
                <a class="blog-img mr-4" style="background-image: url(images/image_3.jpg);"></a>
                <div class="text">
                  <h3 class="heading-1"><a href="#">Even the all-powerful Pointing has no control about the blind texts</a></h3>
                  <div class="meta">
                    <div><a href="#"><span class="icon-calendar"></span> April 09, 2019</a></div>
                    <div><a href="#"><span class="icon-person"></span> Admin</a></div>
                    <div><a href="#"><span class="icon-chat"></span> 19</a></div>
                  </div>
                </div>
              </div>
            </div>

            <div class="sidebar-box ftco-animate">
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
            </div>

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
