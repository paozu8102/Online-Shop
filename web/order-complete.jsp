<%@include file="template/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<li class="nav-item"><a href="index.jsp" class="nav-link">Home</a></li>
<li class="nav-item active dropdown">
    <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Shop</a>
    <div class="dropdown-menu" aria-labelledby="dropdown04">
        <a class="dropdown-item" href="shop">Shop</a>
        <a class="dropdown-item" href="wishlist.jsp">Wishlist</a>
        <a class="dropdown-item" href="product-single.jsp">Single Product</a>
        <a class="dropdown-item" href="cart">Cart</a>
        <a class="dropdown-item" href="checkout.jsp">Checkout</a>
    </div>
</li>
<li class="nav-item"><a href="about.jsp" class="nav-link">About</a></li>
<li class="nav-item"><a href="blog.jsp" class="nav-link">Blog</a></li>
<li class="nav-item"><a href="contact.jsp" class="nav-link">Contact</a></li>
<c:if test="${sessionScope.acc==null}"><li class="nav-item active"><a href="login" class="nav-link">Sign In</a></li></c:if>
<c:if test="${sessionScope.acc!=null}"><li><a class="getstarted scrollto" href="logout"></a></li>
    <li class="nav-item active"><a href="UserProfile" class="nav-link">Profile</a></li>
    <li class="nav-item active"><a href="logout" class="nav-link">Sign Out</a></li> 
    </c:if>
<li class="nav-item cta cta-colored"><a href="cart" class="nav-link"><span class="icon-shopping_cart"></span>[${cartlist.size()}]</a></li>

</ul>
</div>
</div>
</nav>
<!-- END nav -->

<div class="hero-wrap hero-bread" style="background-image: url('images/bg_1.jpg');">
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center">
                <p class="breadcrumbs"><span class="mr-2"><a href="home">Home</a></span> <span>Cart</span></p>
                <h1 class="mb-0 bread">My Cart</h1>
            </div>
        </div>
    </div>
</div>

<section class="ftco-section ftco-cart">
    <div class="container">
        <div class="row">
            <div class="col-md-12 ftco-animate">
                
                <div class="col-sm-10 offset-sm-1 text-center">
						<p class="icon-addcart"><span><i class="icon-check"></i></span></p>
						<h2 class="mb-4">Thank you for purchasing, Your order is complete</h2>
						<p>
							<a href="home"class="btn btn-primary btn-outline-primary">Home</a>
                                                        <a href="shop"class="btn btn-primary btn-outline-primary">Your Order</a>
                                                        
							
						</p>
                                                <a style="display: block; text-align: center; margin-top: 20px;" href="shop">Shop more product</a>
					</div>
               </div>
        </div>
    </div>
</section>

<%@include file="template/footerJS.jsp" %>