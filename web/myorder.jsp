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
                <p class="breadcrumbs"><span class="mr-2"><a href="home">Home</a></span> <span>Order</span></p>
                <h1 class="mb-0 bread">My Order</h1>
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

                                <th>Product image</th>
                                <th>Product name</th>

                                <th>Quantity</th>
                                <th>Total</th>
                                <th>Status</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${listO}" var="i" >
                                <tr class="text-center">


                                    <td class="image-prod"><div class="img" style="background-image:url(images/${i.productimage});"></div></td>

                                    <td class="product-name">
                                        <h3>${i.productname}</h3>

                                    </td>



                                    <td class="quantity">x${i.quantity}
                                    </td>

                                    <td class="total">$<fmt:formatNumber pattern="##.#" value="${i.price}"/></td>
                                    <td class="price" style="color:
                                        <c:choose>
                                            <c:when test="${i.status == 'pending processing'}">
                                                #FFD700 /* Set text color to yellow for 'pending' status */
                                            </c:when>
                                            <c:when test="${i.status == 'cancel'}">
                                                #8B0000
                                            </c:when>
                                            <c:otherwise>
                                                black /* Set a default text color (e.g., black) for other statuses */
                                            </c:otherwise>
                                        </c:choose>
                                        ;">
                                        ${i.status}
                                    </td>
                                    <td class="total">
                                        <a href="#" data-toggle="modal" data-target="#editOrder" 
                                           data-productname="${i.productname}" 
                                          
                                           data-quantity="${i.quantity}" 
                                           data-price="${i.price}" 
                                           data-date="${i.date}" 
                                           data-sellname="${i.sellname}" 
                                           data-customername="${i.customername}" 
                                           data-address="${i.address}" 
                                           data-phonenumber="${i.phonenumber}" 
                                           data-status="${i.status}"
                                           data-oid="${i.oid}">



                                            <img src="https://media.istockphoto.com/id/1161405325/vi/vec-to/bi%E1%BB%83u-t%C6%B0%E1%BB%A3ng-b%C3%BAt-ch%C3%AC-%C4%91%C6%B0%E1%BB%A3c-c%C3%B4-l%E1%BA%ADp-theo-phong-c%C3%A1ch-ph%E1%BA%B3ng.jpg?s=612x612&w=0&k=20&c=f4xtI6U0w47PmSzYr12a9DflXkhTXr1qFCuJz-GMegk="
                                                 alt="image" width="20" height="20">
                                        </a>
                                    </td>
                                </tr><!-- END TR-->

                            </c:forEach> 

                        </tbody>
                    </table>
                </div>
                   <div class="col text-center" style="margin-top: 30px;">
                <div class="block-27">
                    <ul>
                        <c:if test="${tag > 1}">
                            <li><a href="myorder?index=${tag-1}"><i class="ion-ios-arrow-back"></i></a></li>
                                </c:if>

                        <c:forEach begin="1" end="${endP}" var="i">
                            <li class="${tag == i ? 'active' : ''}">
                                <a href="myorder?index=${i}">${i}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${tag < endP}">
                            <li><a href="myorder?index=${tag+1}"><i class="ion-ios-arrow-forward"></i></a></li>
                                </c:if>
                    </ul>

                </div>
            </div>
        </div>
            </div>

         




        <a style="display: block; text-align: center; margin-top: 20px;" href="shop">Shop more product</a>


        <div id="editOrder" class="modal fade" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="myorder" method="post">
                        <div class="modal-header">
                            <h4 class="modal-title">Order Detail</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                              
                              <input type="hidden" name="oid" >
                            </div>
                            <div class="form-group">
                                <label>Date</label>
                                <p name="date" style="color: black;" ></p>
                            </div>
                            <div class="form-group">
                                <label>Product Name</label>
                                <p name="productname" style="color: black;"></p>
                            </div>
                            
                            <div class="form-group">
                                <label>Quantity </label>
                                <p name="quantity" style="color: black;"></p>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <p name="price" style="color: black;"></p>
                            </div>
                            <div class="form-group">
                                <label>Name</label>
                                <p name="customername" style="color: black;"></p>
                            </div>
                            <div class="form-group">
                                <label>Address</label>
                                <p name="address" style="color: black;"></p>
                            </div>
                            <div class="form-group">
                                <label>Phone</label>
                                <p name="phonenumber" style="color: black;"></p>
                            </div>
                            <div class="form-group">
                                <label>Shop</label>
                                <p name="sellname" style="color: black;"></p>
                            </div>
                            <div class="form-group">
                                <label>Status</label>
                                <p name="status" style="color: black;"></p>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Close">
                           <input type="submit" class="btn btn-success" value="Cancel This Order" style="background-color: red;" onclick="return confirmCancellation();">



                        </div>
                    </form>
                </div>
            </div>
        </div>

</div>


</section>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
    $('a[data-target="#editOrder"]').click(function () {
        var productname = $(this).data('productname');
        var quantity = $(this).data('quantity');
        var price = $(this).data('price');
        var date = $(this).data('date');
        var sellname = $(this).data('sellname');
        var customername = $(this).data('customername');
        var address = $(this).data('address');
        var phonenumber = $(this).data('phonenumber');
        var status = $(this).data('status');
var oid = $(this).data('oid');
        
        $('#editOrder p[name="productname"]').text(productname);
        $('#editOrder p[name="quantity"]').text(quantity);
        $('#editOrder p[name="price"]').text(price);
        $('#editOrder p[name="date"]').text(date);
        $('#editOrder p[name="sellname"]').text(sellname);
        $('#editOrder p[name="customername"]').text(customername);
        $('#editOrder p[name="address"]').text(address);
        $('#editOrder p[name="phonenumber"]').text(phonenumber);
        $('#editOrder p[name="status"]').text(status);
         $('#editOrder input[name="oid"]').val(oid);
    });
</script>


<script>
function confirmCancellation() {
    var confirmation = confirm("Are you sure to cancel this order?");
    return confirmation;
}
</script>




<script>
// When the modal is shown, add the 'modal-open' class to the body
    $('#editOrder').on('show.bs.modal', function () {
        $('body').addClass('modal-open');
    });

// When the modal is hidden, remove the 'modal-open' class from the body
    $('#editOrder').on('hidden.bs.modal', function () {
        $('body').removeClass('modal-open');
    });

</script>

<%@include file="template/footerJS.jsp" %>