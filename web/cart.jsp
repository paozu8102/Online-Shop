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
                <div class="cart-list">

                    <table class="table">
                        <thead class="thead-primary">
                            <tr class="text-center">
                                <th>&nbsp;</th>
                                <th>Product image</th>
                                <th>Product name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${cartlist}" var="i">
                                <tr class="text-center">
                                    <td class="product-remove"><a href="#"><span class="ion-ios-close"></span></a></td>

                                    <td class="image-prod"><div class="img" style="background-image:url(images/${i.key.image});"></div></td>

                                    <td class="product-name">
                                        <h3>${i.key.productName}</h3>
                                       
                                    </td>

                                    <td class="price">$<fmt:formatNumber pattern="##.#" value="${i.key.price-i.key.price*i.key.discount/100}"/></td>

                                    <td class="quantity">
                                        <div class="input-group mb-3">
                                            <input type="text" name="quantity" class="quantity form-control input-number" value="1" min="1" max="100">
                                        </div>
                                    </td>

                                    <td class="total">$<fmt:formatNumber pattern="##.#" value="${(i.key.price-i.key.price*i.key.discount/100)*i.value}"/></td>
                                </tr><!-- END TR-->
                            </c:forEach> 
                           
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row justify-content-end">
            <div class="col-lg-4 mt-5 cart-wrap ftco-animate">
                <div class="cart-total mb-3">
                    <h3>Coupon Code</h3>
                    <p>Enter your coupon code if you have one</p>
                    <form action="#" class="info">
                        <div class="form-group">
                            <label for="">Coupon code</label>
                            <input type="text" class="form-control text-left px-3" placeholder="">
                        </div>
                    </form>
                </div>
                <p><a href="checkout.jsp" class="btn btn-primary py-3 px-4">Apply Coupon</a></p>
            </div>
            <div class="col-lg-4 mt-5 cart-wrap ftco-animate">
                <div class="cart-total mb-3">
                    <h3>Estimate shipping and tax</h3>
                    <p>Enter your destination to get a shipping estimate</p>
                    <form action="#" class="info">
                        <div class="form-group">
                            <label for="">Country</label>
                            <input type="text" class="form-control text-left px-3" placeholder="">
                        </div>
                        <div class="form-group">
                            <label for="country">State/Province</label>
                            <input type="text" class="form-control text-left px-3" placeholder="">
                        </div>
                        <div class="form-group">
                            <label for="country">Zip/Postal Code</label>
                            <input type="text" class="form-control text-left px-3" placeholder="">
                        </div>
                    </form>
                </div>
                <p><a href="checkout.jsp" class="btn btn-primary py-3 px-4">Estimate</a></p>
            </div>
            <div class="col-lg-4 mt-5 cart-wrap ftco-animate">
                <div class="cart-total mb-3">
                    <h3>Cart Totals</h3>
                    <p class="d-flex">
                        <span>Subtotal</span>
                        <span>$20.60</span>
                    </p>
                    <p class="d-flex">
                        <span>Delivery</span>
                        <span>$0.00</span>
                    </p>
                    <p class="d-flex">
                        <span>Discount</span>
                        <span>$3.00</span>
                    </p>
                    <hr>
                    <p class="d-flex total-price">
                        <span>Total</span>
                        <span>$17.60</span>
                    </p>
                </div>
                <p><a href="checkout.jsp" class="btn btn-primary py-3 px-4">Proceed to Checkout</a></p>
            </div>
        </div>
    </div>
</section>
<%@include file="template/footerJS.jsp" %>