<%@include file="template/header.jsp" %>


<c:if test="${sessionScope.acc.roleID==null}">
    <li class="nav-item active"><a href="home" class="nav-link">Home</a></li>

    <li class="nav-item"><a href="shop" class="nav-link">Shop</a></li>

    <li class="nav-item"><a href="Posts" class="nav-link">Post</a></li>

    <li class="nav-item"><a href="login" class="nav-link">Sign In</a></li>

</c:if>
<c:if test="${sessionScope.acc.roleID==3}">
    <li class="nav-item active"><a href="home" class="nav-link">Home</a></li>

    <li class="nav-item"><a href="shop" class="nav-link">Shop</a></li>


    <li class="nav-item"><a href="Posts" class="nav-link">Post</a></li>

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
<script>
    function goToImage(link) {
        window.location.href = link;
    }
</script>
<style>
    .grid-container {
        display: grid;
        grid-template-columns: repeat(2, 1fr); /* Create a 2x2 grid */
        gap: 20px; /* Adjust the gap between grid items */
        padding: 20px; /* Add padding around the grid container */
    }

    .grid-item {
        /*        border: 2px solid greenyellow;*/
        /*        padding: 50px;*/
        /*        border-radius: 50px;*/
        margin-top: -50px;
        margin-left: 0%;
    }
</style>
<section id="home-section" class="hero">
    <div class="home-slider owl-carousel">
        <div class="slider-item" onclick="return false" 
             style="background-image: url(images/slider1.jpg);">
            <div class="overlay"></div>
            <div class="container">
                <div class="row slider-text justify-content-center align-items-center" data-scrollax-parent="true">
                    <div class="col-md-12 ftco-animate text-center">
                        <h1 class="mb-2">Bring the world of art to you</h1>
                        <h2 class="subheading mb-4">Not only art, but your satisfy</h2>
                        <p><a href="shop" class="btn btn-primary">View Others</a></p><!--link to shop with this kind of category-->
                    </div>
                </div>
            </div>
        </div>

        <div class="slider-item" onclick="return false" 
             style="background-image: url(images/slider2.jpg);">            
            <div class="overlay"></div>
            <div class="container">
                <div class="row slider-text justify-content-center align-items-center" data-scrollax-parent="true">

                    <div class="col-md-12 ftco-animate text-center">
                        <h1 class="mb-2">We have art that will give you chill</h1>
                        <h2 class="subheading mb-4">We deliver high quality art</h2>
                        <p><a href="#" class="btn btn-primary">View Others</a></p>
                    </div>

                </div>
            </div>
        </div>         

        <div class="slider-item" onclick="return false" 
             style="background-image: url(images/slider3.jpg);">
            <div class="overlay"></div>
            <div class="container">
                <div class="row slider-text justify-content-center align-items-center" data-scrollax-parent="true">

                    <div class="col-sm-12 ftco-animate text-center">
                        <h1 class="mb-2">Colab with talent artist</h1>
                        <h2 class="subheading mb-4">All kind of style and category</h2>
                        <p><a href="#" class="btn btn-primary">View Others</a></p>
                    </div>
                </div>
            </div>
        </div>

    </div>


</section>

<section class="ftco-section">
    <div class="container">
        <div class="row no-gutters ftco-services">
            <div class="col-md-4 text-center d-flex align-self-stretch ftco-animate">
                <div class="media block-6 services mb-md-0 mb-4">
                    <div class="icon bg-color-1 active d-flex justify-content-center align-items-center mb-2">
                        <span class="flaticon-shipped"></span>
                    </div>
                    <div class="media-body">
                        <h3 class="heading">Free Shipping</h3>
                        <span>On order over $100</span>
                    </div>
                </div>      
            </div>
            <div class="col-md-4 text-center d-flex align-self-stretch ftco-animate">
                <div class="media block-6 services mb-md-0 mb-4">
                    <div class="icon bg-color-3 d-flex justify-content-center align-items-center mb-2">
                        <span class="flaticon-award"></span>
                    </div>
                    <div class="media-body">
                        <h3 class="heading">Superior Quality</h3>
                        <span>Quality Products</span>
                    </div>
                </div>      
            </div>
            <div class="col-md-4 text-center d-flex align-self-stretch ftco-animate">
                <div class="media block-6 services mb-md-0 mb-4">
                    <div class="icon bg-color-4 d-flex justify-content-center align-items-center mb-2">
                        <span class="flaticon-customer-service"></span>
                    </div>
                    <div class="media-body">
                        <h3 class="heading">Support</h3>
                        <span>24/7 Support</span>
                    </div>
                </div>      
            </div>
        </div>
    </div>
</section>

<section class="ftco-section ftco-category ftco-no-pt">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="row">
                    <div class="col-md-6 order-md-last align-items-stretch d-flex">
                        <div class="category-wrap-2 ftco-animate img align-self-stretch d-flex" style="background-image: url(images/cate4.jpg);">
                            <div class="text text-center">
                                <h2>Palette Joy</h2>
                                <p>Bring the beauty to every home</p>
                                <p><a href="shop" class="btn btn-primary">Shop now</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="category-wrap ftco-animate img mb-4 d-flex align-items-end" style="background-image: url(images/cate1.jpg);">
                            <div class="text px-3 py-1">
                                <h2 class="mb-0"><a href="shop?promat=1">Oil Painting</a></h2>
                            </div>
                        </div>
                        <div class="category-wrap ftco-animate img d-flex align-items-end" style="background-image: url(images/cate2.png);">
                            <div class="text px-3 py-1">
                                <h2 class="mb-0"><a href="shop?promat=2">Watercolor Painting</a></h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="category-wrap ftco-animate img mb-4 d-flex align-items-end" style="background-image: url(images/cate3.png);">
                    <div class="text px-3 py-1">
                        <h2 class="mb-0"><a href="shop?promat=5">Silk Painting</a></h2>
                    </div>		
                </div>
                <div class="category-wrap ftco-animate img d-flex align-items-end" style="background-image: url(images/cate4.png);">
                    <div class="text px-3 py-1">
                        <h2 class="mb-0"><a href="shop?promat=3">Pastel Painting</a></h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section">
    <div class="container">
        <div class="row justify-content-center mb-3 pb-3">
            <div class="col-md-12 heading-section text-center ftco-animate">
                <span class="subheading">Featured Products</span>
                <h2 class="mb-4">Our Products</h2>
                <p>From all over cities, there are al lots of talent artist ready to bring art to you. Get ready to be amazed</p>
            </div>
        </div>   		
    </div>
    <div class="container">
        <div class="row">
            <c:forEach items="${homeProduct}" var="product" varStatus="loop">
                <div class="col-md-6 col-lg-3 ftco-animate">
                    <div class="product">
                        <a href="<%=path%>/detail?pid=${product.getProductID()}&cid=${listCate[loop.index]}" class="img-prod"><img class="img-fluid" src="images/${picOfProduct[loop.index]}" alt="Yeallow Girl">
                            <c:if test="${homeProduct[loop.index].getDiscount() > 0}">
                                <span class="status">${homeProduct[loop.index].getDiscount()}%</span>
                            </c:if>
                            <div class="overlay"></div>
                        </a>
                        <div class="text py-3 pb-4 px-3 text-center">
                            <h3><a href="<%=path%>/detail?pid=${product.getProductID()}&cid=${listCate[loop.index]}">${homeProduct[loop.index].getProductName()}</a></h3>
                            <div class="d-flex">
                                <div class="pricing">
                                    <c:if test="${homeProduct[loop.index].getDiscount() > 0}">
                                        <p class="price"><span class="mr-2 price-dc">$${homeProduct[loop.index].getPrice()}</span><span class="price-sale">$${homeProduct[loop.index].getPrice()-homeProduct[loop.index].getPrice()/100*homeProduct[loop.index].getDiscount()}</span></p>
                                    </c:if>
                                    <c:if test="${homeProduct[loop.index].getDiscount() eq 0}">
                                        <p class="price"><span>$${homeProduct[loop.index].getPrice()}</span></p>
                                    </c:if>
                                </div>
                            </div>
                            <div class="bottom-area d-flex px-3">
                                <div class="m-auto d-flex">
                                    <a href="<%=path%>/detail?pid=${product.getProductID()}&cid=${listCate[loop.index]}" class="add-to-cart d-flex justify-content-center align-items-center text-center">
                                        <span><i class="ion-ios-menu"></i></span>
                                    </a>
                                    <a href="<%=path%>/buy?id=${product.getProductID()}" class="buy-now d-flex justify-content-center align-items-center mx-1">
                                        <span><i class="ion-ios-cart"></i></span>
                                    </a>
                                    <a href="#" class="heart d-flex justify-content-center align-items-center ">
                                        <span><i class="ion-ios-heart"></i></span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<div class="row justify-content-center mb-5 pb-3">
    <div class="col-md-7 heading-section ftco-animate text-center" style="margin-top: -50px">
        <h2 class="mb-4">Posts you might interested in</h2>
    </div>
</div>
<div class="grid-container" style="width: 100%">
    <c:forEach items="${postInfoList}" var="object">
        <div class="col-md-12 d-flex ftco-animate grid-item">
            <div class="blog-entry align-self-stretch d-md-flex">
                <c:if test="${object.ImageUrl ne null}">
                    <a href="<%=path%>/PostSingle?id=${object.BlogID}" class="block-20" style="background-image: url('${object.ImageUrl}');">
                    </c:if>
                </a>
                <div class="text d-block pl-md-4">
                    <div class="meta mb-3">
                        <div><a href="<%=path%>/PostSingle?id=${object.BlogID}">${object.DateTime}</a></div>
                        <div><a href="#">${object.UserName}</a></div>
                        <div><a href="#" class="meta-chat"><span class="icon-chat"></span>${object.CommentNumber}</a></div>
                    </div>
                    <h3 class="heading"><a href="<%=path%>/PostSingle?id=${object.BlogID}">${object.Title}</a></h3>
                    <p>${object.Content}</p>
                    <p><a href="<%=path%>/PostSingle?id=${object.BlogID}" class="btn btn-primary py-2 px-3">Read more</a></p>
                </div>
            </div>
        </div>
    </c:forEach>  
</div>
<div class="row justify-content-center mb-5 pb-3">
    <div class="col-md-7 heading-section ftco-animate text-center" style="margin-top: -50px">
        <a href="<%=path%>/Posts">
            <span style="font-size: 45px; margin-top: -50px; text-decoration: underline; cursor: pointer" class="subheading">Visit our forum</span>
        </a>
    </div>
</div>
<hr>

<section class="ftco-section testimony-section" style="margin-top: -100px">
    <div class="container">
        <div class="row justify-content-center mb-5 pb-3">
            <div class="col-md-7 heading-section ftco-animate text-center">
                <span class="subheading">Testimony</span>
                <h2 class="mb-4">Our staff's perspective</h2>
                <p>We all have a common goal, that is bring the beauty of arts and passion of artists to everyone.</p>
            </div>
        </div>
        <div class="row ftco-animate">
            <div class="col-md-12">
                <div class="carousel-testimony owl-carousel">

                    <div class="item">
                        <div class="testimony-wrap p-4 pb-5">
                            <div class="user-img mb-5" style="background-image: url('https://bizweb.dktcdn.net/100/438/408/files/anh-dep-3d-yodyvn4.jpg?v=1683534900596');">
                                <span class="quote d-flex align-items-center justify-content-center">
                                    <i class="icon-quote-left"></i>
                                </span>
                            </div>
                            <div class="text text-center">
                                <p class="mb-5 pl-4 line">We would want to connect art to everyone, create opportunity for artists to shine and cherish their creativity</p>
                                <p class="name">Mai Vu Bao</p>
                                <span class="position">Admin</span>
                            </div>
                        </div>
                    </div>

                    <div class="item">
                        <div class="testimony-wrap p-4 pb-5">
                            <div class="user-img mb-5" style="background-image: url('https://genzrelax.com/wp-content/uploads/2022/03/anh-dai-dien-dep-1.jpg');">
                                <span class="quote d-flex align-items-center justify-content-center">
                                    <i class="icon-quote-left"></i>
                                </span>
                            </div>
                            <div class="text text-center">
                                <p class="mb-5 pl-4 line">I want no art is wasted, i want people to feel the beauty and pay more attension to artists</p>
                                <p class="name">Nguyen Xuan Thanh</p>
                                <span class="position">BE Developer</span>
                            </div>
                        </div>
                    </div>

                    <div class="item">
                        <div class="testimony-wrap p-4 pb-5">
                            <div class="user-img mb-5" style="background-image: url('https://khoinguonsangtao.vn/wp-content/uploads/2022/05/anh-avatar-dep-ngau-hinh-dai-dien-510x560.jpg');">
                                <span class="quote d-flex align-items-center justify-content-center">
                                    <i class="icon-quote-left"></i>
                                </span>
                            </div>
                            <div class="text text-center">
                                <p class="mb-5 pl-4 line">Im look at what i do as a FE developer to create a beautiful interface for user and look at the artist and think that they did the same, just not on computer</p>
                                <p class="name">Le Van Duc</p>
                                <span class="position">FE Developer</span>
                            </div>
                        </div>
                    </div>

                    <div class="item">
                        <div class="testimony-wrap p-4 pb-5">
                            <div class="user-img mb-5" style="background-image: url('https://pgddttieucan.edu.vn/wp-content/uploads/2022/08/1660864210_Anh-Avatar-Dep-Cute-Chat-Ngau-Lam-Hinh-Dai-Dien-696x619.jpg');">
                                <span class="quote d-flex align-items-center justify-content-center">
                                    <i class="icon-quote-left"></i>
                                </span>
                            </div>
                            <div class="text text-center">
                                <p class="mb-5 pl-4 line">I look at what we do and think that this will definitely make the world of art shine again by combine it with the world of technology</p>
                                <p class="name">Hoang Chinh Minh</p>
                                <span class="position">Doc Master</span>
                            </div>
                        </div>
                    </div>

                    <div class="item">
                        <div class="testimony-wrap p-4 pb-5">
                            <div class="user-img mb-5" style="background-image: url('https://top10kontum.com/wp-content/uploads/2022/10/anh-dai-dien-zalo-y-nghia-2.jpg');">
                                <span class="quote d-flex align-items-center justify-content-center">
                                    <i class="icon-quote-left"></i>
                                </span>
                            </div>
                            <div class="text text-center">
                                <p class="mb-5 pl-4 line">My dad is a non-pro artist but everything he made, he was so proud of it. I take part in this project to make sure every artist out there proud of themselves</p>
                                <p class="name">Nguyen Huy Hoang</p>
                                <span class="position">Full Stack Developer</span>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</section>
<hr>




<footer class="ftco-footer ftco-section">
    <%@include file="template/footer.jsp" %>