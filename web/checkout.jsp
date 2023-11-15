<%@include file="template/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://www.paypal.com/sdk/js?client-id=AV7LmUldlsdqpSWgHyWSJHiW-fZFHJoEys_YXJqbmSefiuT4XL9Qb25FI-UokdBzLsRPXSD0f0FpOR7-"></script>
<c:if test="${sessionScope.acc.roleID==null}">
    <li class="nav-item"><a href="home" class="nav-link">Home</a></li>

    <li class="nav-item"><a href="shop" class="nav-link">Shop</a></li>

    <li class="nav-item"><a href="Posts" class="nav-link">Post</a></li>

    <li class="nav-item"><a href="login" class="nav-link">Sign In</a></li>

</c:if>
<c:if test="${sessionScope.acc.roleID==3}">
    <li class="nav-item"><a href="home" class="nav-link">Home</a></li>

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
         <form id="checkout"  action="checkout" method="post" >
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

                            
                               <h3 class="mb-4 billing-heading">Customer Information</h3>
<div class="row align-items-end">
    <div class="col-md-6">
        <div class="form-group">
            <label for="firstname">Name</label>
            <input style="display: inline-block; width: 400px; color:black;" type="text" name="customername"  id="customername"  class="form-control" value="${user.userName}" required>
            <!-- You can add 'required' to make the field mandatory -->
        </div>
    </div>

    <div class="w-100"></div>

    <div class="col-md-6">
        <div class="form-group">
            <label for="towncity">Address</label>
            <textarea style="width: 400px; color: black;" id="address" name="address" class="form-control" required>${user.address}</textarea>
            <!-- 'required' attribute added for mandatory field -->
        </div>
    </div>

    <div class="w-100"></div>

    <div class="col-md-6">
        <div class="form-group">
            <label for="phone">Phone</label>
            <input style="display: inline-block; width: 400px; color:black; " id="phone" type="text" name="phone" class="form-control" value="${user.phoneNumber}" pattern="[0-9]{10}" title="Please enter a valid 10-digit phone number" required>
            <!-- 'required' attribute added for mandatory field, 'pattern' for a 10-digit phone number -->
        </div>
    </div>
</div>

                            

                        </div>
                    </div>
                 
                        <div class="cart-detail p-3 p-md-4">
                            <h3 class="billing-heading mb-4">Payment Method</h3>

                         <div id="paypal-button-container"></div>

                          <p><a href="" class="btn btn-primary paypal-like-button" id="place-order-button">Cash Delivery</a></p>
<input type="hidden" name="payment" id="payment" value="">


                        </div>
                    </div>
                                        
                </div>
            </div> <!-- .col-md-8 -->
        </div>
                                        </form>
    </div>
</section> <!-- .section -->
<script>
    paypal.Buttons({
        createOrder: function(data, actions) {
            // Validate name
            var nameInput = document.getElementById("customername");
            if (!validateName(nameInput.value)) {
                alert("Please enter a valid name.");
                return actions.reject();
            }

            // Validate address
            var addressInput = document.getElementById("address");
            if (!validateAddress(addressInput.value)) {
                alert("Please enter a valid address.");
                return actions.reject();
            }

            // Validate phone
            var phoneInput = document.getElementById("phone");
            if (!validatePhone(phoneInput.value)) {
                alert("Please enter a valid 10-digit phone number.");
                return actions.reject();
            }

            // Proceed with creating the order
            return actions.order.create({
                purchase_units: [{
                    amount: {
                        value: ${total}
                    }
                }]
            });
        },
        onApprove: function(data, actions) {
            // Capture the payment details
            return actions.order.capture().then(function(details) {
                document.getElementById('checkout').submit();
            });
        }
    }).render('#paypal-button-container');

    function validateName(name) {
        // Add your name validation logic here
        return name.trim() !== "";
    }

    function validateAddress(address) {
        // Add your address validation logic here
        return address.trim() !== "";
    }

    function validatePhone(phone) {
        // Add your phone validation logic here
        var phonePattern = /^[0-9]{10}$/;
        return phonePattern.test(phone);
    }
</script>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        var cashDeliveryButton = document.getElementById("place-order-button");

        cashDeliveryButton.addEventListener("click", function(event) {
            event.preventDefault();

            // Validate name
            var nameInput = document.getElementById("customername");
            if (!validateName(nameInput.value)) {
                alert("Please enter a valid name.");
                return;
            }

            // Validate address
            var addressInput = document.getElementById("address");
            if (!validateAddress(addressInput.value)) {
                alert("Please enter a valid address.");
                return;
            }

            // Validate phone
            var phoneInput = document.getElementById("phone");
            if (!validatePhone(phoneInput.value)) {
                alert("Please enter a valid 10-digit phone number.");
                return;
            }

            var paymentInput = document.getElementById("payment");
            if (paymentInput) {
                paymentInput.value = "cod";
            }

            document.getElementById('checkout').submit();
        });
    });

    function validateName(name) {
        // Add your name validation logic here
        return name.trim() !== "";
    }

    function validateAddress(address) {
        // Add your address validation logic here
        return address.trim() !== "";
    }

    function validatePhone(phone) {
        // Add your phone validation logic here
        var phonePattern = /^[0-9]{10}$/;
        return phonePattern.test(phone);
    }
</script>

<%@include file="template/footerJS.jsp" %>