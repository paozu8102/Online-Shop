<%@include file="template/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<li class="nav-item"><a href="index.jsp" class="nav-link">Home</a></li>
<li class="nav-item active dropdown">
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
<li class="nav-item"><a href="blog.jsp" class="nav-link">Blog</a></li>
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

<div class="hero-wrap hero-bread" style="background-image: url('images/bg_1.jpg');">
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center">
                <p class="breadcrumbs"><span class="mr-2"><a href="index.jsp">Home</a></span> <span>Checkout</span></p>
                <h1 class="mb-0 bread">Checkout</h1>
            </div>
        </div>
    </div>
</div>

<section class="ftco-section">
    <div class="container">
         <form id="checkout"  action="checkout" method="post" onsubmit="return handleFormSubmit()">
        <div class="row justify-content-center">
            <div class="col-xl-7 ftco-animate">
             
                
               
                <h3 class="mb-4 billing-heading">Billing Details</h3>
                
                       <div class="cart-detail cart-total p-3 p-md-4">
                    
                    <div class="row align-items-end">
                        <div class="col-md-6">
                            <h3 class="billing-heading mb-4">Cart Total</h3>
                            
                              <c:forEach items="${pList}" var="i" varStatus="loop">
                                  <c:set var="total" value="${total + (i.key.price-i.key.price*i.key.discount/100)*i.value }"></c:set>
                                 <c:set var="numpro" value="${numpro + i.value }"></c:set>
                            <p  style="display: inline-block; width: 1000px;" class="d-flex">
                          
                            <span>${i.value} x ${i.key.productName}</span>
 
                           
<span >$<fmt:formatNumber pattern="##.#" value="${(i.key.price-i.key.price*i.key.discount/100)*i.value}"/></span>

                            </p>
                            </c:forEach>
                            <p style="display: inline-block; width: 1000px;" class="d-flex">
                                <span >Delivery</span>
                                
                                <span>$0.00</span>
                            </p>
                            <p style="display: inline-block; width: 1000px;" class="d-flex">
                                <span>Discount</span>
                                 
                                <span>$0.00</span>
                            </p>
                            <hr style="display: inline-block; width: 550px;" >
                            <p style="display: inline-block; width: 1000px;"class="d-flex total-price">
                                <span>Total  ${numpro} product</span>
                                <span>$${total}</span>
                                  <input type="hidden" name="total" value="${total}">
                            </p>
</div>

 </div>
           </div>           
              
              
                </div>
            <div class="col-xl-5">
                <div class="row mt-5 pt-3">
                    <div class="col-md-12 d-flex mb-5">
                        <div class="cart-detail cart-total p-3 p-md-4">

                            
                                <h3 class="mb-4 billing-heading">Billing Details</h3>
                                <div class="row align-items-end">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="firstname">Name</label>
                                            <input  style="display: inline-block; width: 400px; color:black;" type="text" name="customername" class="form-control" value="${user.userName}">
                                        </div>
                                    </div>

                                    <div class="w-100"></div>

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="towncity">Address</label>
                                   <textarea style="width: 400px; color: black;" name="address" class="form-control">${user.address}</textarea>

                                        </div>
                                    </div>

                                    <div class="w-100"></div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="phone">Phone</label>
                                            <input  style="display: inline-block; width: 400px; color:black; " type="text" name="phone" class="form-control" value="${user.phoneNumber}">
                                        </div>
                                    </div>



                                </div>
                            

                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="cart-detail p-3 p-md-4">
                            <h3 class="billing-heading mb-4">Payment Method</h3>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <div class="radio">
                                          <input type="radio" name="optradio" value="Bank-transfer" class="mr-2">Bank Transfer
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <div class="radio">
                                       <input type="radio" name="optradio" value="Cash-on-delivery" class="mr-2">Cash on delivery
                                    </div>
                                </div>
                            </div>
                          
                         
                            <p><a href="#" class="btn btn-primary py-3 px-4" id="place-order-button">Place an order</a></p>
                        </div>
                    </div>
                                        
                </div>
            </div> <!-- .col-md-8 -->
        </div>
                                        </form>
    </div>
</section> <!-- .section -->
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const placeOrderButton = document.getElementById("place-order-button");
        const radioButtons = document.querySelectorAll('input[type="radio"][name="optradio"]');

        placeOrderButton.addEventListener("click", function (event) {
            // Find the selected radio button
            const selectedRadioButton = Array.from(radioButtons).find(radio => radio.checked);

            if (selectedRadioButton) {
                if (selectedRadioButton.value === "Cash-on-delivery") {
                    // Submit the form for "Cash on Delivery"
                    document.getElementById("checkout").submit();
                } else {
                    // Do nothing for "Direct Bank Transfer"
                }
            } else {
                // No radio button selected; you can add error handling here
            }
        });
    });
</script>
<%@include file="template/footerJS.jsp" %>
