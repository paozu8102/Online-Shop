<%@include file="template/header.jsp" %>
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
    #searchIcon:hover{
        font-size: 30px;
        color: #82AE46;
    }
</style>
<script>
function setMaxDate(id){
    var today = new Date();
var dd = today.getDate();
var mm = today.getMonth() + 1; //January is 0!
var yyyy = today.getFullYear();

if (dd < 10) {
   dd = '0' + dd;
}

if (mm < 10) {
   mm = '0' + mm;
} 
    
today = yyyy + '-' + mm + '-' + dd;
document.getElementById(id).setAttribute("max", today);
}

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

function checkDatesAndSubmit() {
    var dateInput1 = new Date(document.getElementById("dateinput1").value);
    var dateInput2 = new Date(document.getElementById("dateinput2").value);

    if (dateInput1 > dateInput2) {
        // First date is after the second date, show an alert
        alert("From date cannot be after To date.");
    } else {
        // Dates are valid, submit the form
        var form = document.getElementById("myForm");
        form.submit();
    }
}
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
        <h3>Posts</h3>
        <div class="row">
            <div class="col-lg-8 ftco-animate" style="overflow: auto; height: 1200px; border: 1px solid">
		<div class="row">
                <c:if test="${fn:length(postList) == 0}">
                    <h2>There isn't any post for now</h2>
                </c:if>
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
        <form id="myForm" method="get" action="Posts" class="search-form">
            <div class="sidebar-box">
                <div class="form-group">
                  <span style="cursor: pointer;" id="searchIcon" class="icon ion-ios-search"></span>
                  <input value="${searchKey}" id="searchInput" name="searchKey" type="text" class="form-control" placeholder="Search...">
                </div>
            </div>
                <div style="border: solid" class="sidebar-box ftco-animate">
                <h3 style="display: inline; margin-right: 30px" class="heading">Categories:</h3>
<!--              <ul class="categories">
                <c:forEach items="${cateAndPostNumList}" var="object">
                    <li><a href="<%=path%>/Posts?cate=${object.CategoryID}">${object.CategoryName}<span>(${object.NumberOfPosts})</span></a></li>
                </c:forEach>
              </ul>-->
        <select style="display: inline" id="category" name="category">
            <option ${category eq "0"?"selected":""} value="0">All</option>
            <c:forEach items="${cateAndPostNumList}" var="object">
                <option ${category eq object.CategoryID?"selected":""} value="${object.CategoryID}">${object.CategoryName}(${object.NumberOfPosts})</option>
            </c:forEach>
        </select>
        <div style="margin-top: 40px; float: left; margin-right: 15px">
            <h3 class="heading" style="margin-bottom: 5px;">From:</h3>
            <input value="${date1}" style="width: 130px;" onkeydown="return false;" onclick="setMaxDate('dateinput1')" type="date" id="dateinput1" name="dateinput1">
        </div>
        <div style="margin-top: 40px; margin-left: 20px;">
            <h3 class="heading" style="margin-bottom: 5px;" >To:</h3>
            <input value="${date2}" style="width: 130px" onkeydown="return false;" onclick="setMaxDate('dateinput2')" type="date" id="dateinput2" name="dateinput2">
        </div>

        <div style="margin-top: 50px">
            <h3 style="display: inline; margin-right: 10px;" class="heading">Sort by:</h3>
            <select style="display: inline;" id="category" name="sortElement">
                <option ${sortElement eq "[CommentNumber]"?"selected":""} value="[CommentNumber]">Popularity</option>
                <option ${sortElement eq "[Date]"?"selected":""} value="[Date]">Date</option>
            </select>
            
            <select name="sortOrder" style="display: inline" id="category">
                <option ${sortOrder eq "ASC"?"selected":""} value="ASC">Asc</option>
                <option ${sortOrder eq "DESC"?"selected":""} value="DESC">Desc</option>
            </select>
        </div>
    <p style="margin-top: 20px; color: black"><a onclick="checkDatesAndSubmit();" class="btn btn-primary py-2 px-3">Filter</a></p>
    </div>
    </form>

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
            </div>

            <div class="sidebar-box ftco-animate">
              <h3 class="heading">Paragraph</h3>
              <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus itaque, autem necessitatibus voluptate quod mollitia delectus aut, sunt placeat nam vero culpa sapiente consectetur similique, inventore eos fugit cupiditate numquam!</p>
            </div>-->
          </div>

        </div>
      </div>
    </section> <!-- .section -->

    <footer class="ftco-footer ftco-section bg-light">
<%@include file="template/footer.jsp" %>