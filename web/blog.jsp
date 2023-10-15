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
    #searchIcon:hover{
        font-size: 30px;
        color: #82AE46;
    }
</style>
<script>
document.addEventListener('DOMContentLoaded', function() {
  const searchForm = document.getElementById('searchForm');
  const searchInput = document.getElementById('searchInput');
  const searchIcon = document.getElementById('searchIcon');

  // Handle click event on the search icon
  searchIcon.addEventListener('click', function() {
    searchForm.submit(); // Submit the form
  });

  // Handle keypress event on the input field
  searchInput.addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
      searchForm.submit(); // Submit the form
    }
  });
});
</script>
    <div class="hero-wrap hero-bread" style="background-image: url('images/bg_1.jpg');">
      <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
          <div class="col-md-9 ftco-animate text-center">
          	<p class="breadcrumbs"><span class="mr-2"><a href="index.jsp">Home</a></span> <span>Post</span></p>
            <h1 class="mb-0 bread">Post</h1>
          </div>
        </div>
      </div>
    </div>

    <section class="ftco-section ftco-degree-bg">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 ftco-animate">
		<div class="row">
                <c:forEach items="${postList}" var="post">
			<div class="col-md-12 d-flex ftco-animate">
		            <div class="blog-entry align-self-stretch d-md-flex">
                              <c:if test="${post.ImageUrl ne null}">  
                              <a href="<%=path%>/PostSingle?id=${post.BlogID}" class="block-20" style="background-image: url('${post.ImageUrl}');">
		              </a>
                              </c:if>  
		              <div class="text d-block pl-md-4">
		              	<div class="meta mb-3">
		                  <div><a href="<%=path%>/PostSingle?id=${post.BlogID}">${post.DateTime}</a></div>
		                  <div><a href="<%=path%>/PostSingle?id=${post.BlogID}">${post.UserName}</a></div>
		                  <div><a href="<%=path%>/PostSingle?id=${post.BlogID}" class="meta-chat"><span class="icon-chat"></span> ${post.CommentNumber}</a></div>
		                </div>
		                <h3 class="heading"><a href="<%=path%>/PostSingle?id=${post.BlogID}">${post.Title}</a></h3>
		                <p>${post.Content}</p>
		                <p><a href="<%=path%>/PostSingle?id=${post.BlogID}" class="btn btn-primary py-2 px-3">Read more</a></p>
		              </div>
		            </div>
		        </div>
                </c:forEach>
		          
                </div>
          </div> <!-- .col-md-8 -->
          <div class="col-lg-4 sidebar ftco-animate">
            <div class="sidebar-box">
              <form id="searchForm" method="get" action="Posts" class="search-form">
                <div class="form-group">
                  <span style="cursor: pointer;" id="searchIcon" class="icon ion-ios-search"></span>
                  <input value="${searchKey}" id="searchInput" name="searchKey" type="text" class="form-control" placeholder="Search...">
                </div>
              </form>
            </div>
            <div class="sidebar-box ftco-animate">
            	<h3 class="heading">Categories</h3>
              <ul class="categories">
                <c:forEach items="${cateAndPostNumList}" var="object">
                    <li><a href="<%=path%>/Posts?cate=${object.CategoryID}">${object.CategoryName}<span>(${object.NumberOfPosts})</span></a></li>
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