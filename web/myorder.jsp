<%@include file="template/header.jsp" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <li class="nav-item active"><a href="myorder" class="nav-link">My Order</a></li>
    <li class="nav-item"><a href="logout" class="nav-link">Sign Out</a></li> 

</c:if>

<li class="nav-item cta cta-colored"><a href="cart" class="nav-link"><span class="icon-shopping_cart"></span>[${cartlist.size()}]</a></li>
</ul>
</div>
</div>
</nav>
<!-- END nav -->
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
          <div style="display: inline-block;">
    <form action="myorder" method="get" class="search-form" style="margin: 0; padding: 0;">
        <div class="search-container" style="display: flex;">
            <input name="txt" type="text" placeholder="Search" style="margin: 0; padding: 10px;">
            <button type="submit" style="vertical-align: top; border: none; padding: 10px; margin: 0; cursor: pointer;">
                <i class="icon-search" style="color: white;"></i>
            </button>
        </div>
    </form>
</div>


            <div class="col-md-12 ftco-animate">
                
                <div class="cart-list">
                 
   <form action="myorder" onsubmit="return validateDate()">
                                        <table class="table text-nowrap">                                  
                                            <tr>
                                                <td>Date: </td>
                                                <td>From</td>
                                                <td>
                                                    <input type="date" name="from" value="${from}">
                                                </td>
                                                <td>To</td>
                                                <td>
                                                    <input type="date" name="to" value="${to}">
                                                </td>
                                                <td colspan="4" style="text-align: center">
                                                    <input style="padding: 0 3rem; color: black; background-color: white;box-shadow: 1px 2px " type="submit" value="Filter">
                                                </td>
                                            </tr>
                                            </table>
 </form>
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
                                            <c:when test="${i.status == 'pending'}">
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
                                           data-productimage="${i.productimage}" 
                                           data-quantity="${i.quantity}" 
                                           data-price="${i.price}" 
                                           data-date="${i.date}" 
                                           data-sellname="${i.sellname}" 
                                           data-customername="${i.customername}" 
                                           data-address="${i.address}" 
                                           data-phonenumber="${i.phonenumber}" 
                                           data-status="${i.status}"
                                           data-oid="${i.did}"
                                           data-payment="${i.payment}"
                                           data-expdate="${i.expDate}"
                                           data-deldate="${i.delDate}"
                                           >



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
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form action="myorder" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Order Detail</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="order-details">
                        <div class="left-column">
                              <input type="hidden" name="oid" >
                            <div class="order-info">
                                <label>Order Date:</label>
                                <p name="date" style="color: black;"></p>
                            </div>
                            <div class="order-info">
                                <label>Expected Date:</label>
                                <p name="expDate" style="color: black;"></p>
                            </div>
                            <div class="order-info">
                                <label>Delivery Date:</label>
                                <p name="delDate" style="color: black;"></p>
                            </div>
                            <div class="order-info">
                                <label>Name:</label>
                                <p name="customername" style="color: black;"></p>
                            </div>
                            <div class="order-info">
                                <label>Address:</label>
                                <p name="address" style="color: black;"></p>
                            </div>
                            <div class="order-info">
                                <label>Phone:</label>
                                <p name="phonenumber" style="color: black;"></p>
                            </div>
                        </div>
                        <div class="right-column">
                            <div class="product-info">
                                <label>Product Name:</label>
                                <p name="productname" style="color: black;"></p>
                            </div>
                            <div class="product-info">
                                <label>Quantity:</label>
                                <p name="quantity" style="color: black;"></p>
                            </div>
                            <div class="product-info">
                                <label>Price:</label>
                                <p name="price" style="color: black;"></p>
                            </div>
                            <div class="product-info">
                                <label>Shop:</label>
                                <p name="sellname" style="color: black;"></p>
                            </div>
                            <div class="product-info">
                                <label>Status:</label>
                                <p name="status" style="color: black;"></p>
                            </div>
                            <div class="product-info">
                                <label>Payment Method:</label>
                                <p name="payment" style="color: black;"></p>
                            </div>
                          
                        </div>
                        
                    </div>
                         <div class="card px-2">
                             <div class="row px-3">
            <div class="col">
                <ul id="progressbar" >
                    <li class="step0 active " id="step1">PLACED</li>
                    <li class="step0 active text-center" id="step2">SHIPPED</li>
                    <li class="step0  text-muted text-right" id="step3">DELIVERED</li>
                </ul>
            </div>
        </div>
                          </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Close">
                    <input type="submit" id="cancelButton" class="btn btn-danger" value="Cancel This Order" onclick="return confirmCancellation();">
                    <div id="cancellationMessage" style="color: red;"></div>
                </div>
            </form>
        </div>
    </div>
</div>


    </div>


</section>

<script>
    $('a[data-target="#editOrder"]').click(function () {
        var status = $(this).data('status');
        var expdate = $(this).data('expdate') || "Unknown";
        var deldate = $(this).data('deldate') || "Unknown";

        $('#editOrder p[name="status"]').text(status);
        $('#editOrder p[name="expDate"]').text(expdate);
        $('#editOrder p[name="delDate"]').text(deldate);

        var cancelButton = $('#cancelButton');
        if (status === 'cancel') {
            cancelButton.hide();
            $('#cancellationMessage').text("This Order is canceled");
        } else {
            cancelButton.show();
            $('#cancellationMessage').text(""); // Reset the message
        }
    });
</script>

<script>
                       $(document).ready(function() {
    var status = $('#editOrder p[name="status"]').text();
    if (status === 'cancel') {
        $('input[type="submit"]').hide();
    }
});

                    </script>
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
                                    var payment = $(this).data('payment');
                                    var oid = $(this).data('oid');
                                    var expdate = $(this).data('expdate') || "Unknown";
                                    var deldate = $(this).data('deldate') || "Unknown";
                                    
     var editOrderModal = $('#editOrder');

            cancelButton = editOrderModal.find("#cancelButton");
            cancelButton.show();
    

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
                                    $('#editOrder p[name="payment"]').text(payment);
                                    $('#editOrder p[name="expDate"]').text(expdate);
                                    $('#editOrder p[name="delDate"]').text(deldate);
                              
  // Get the progress bar steps
  var step1 = document.getElementById("step1");
  var step2 = document.getElementById("step2");
  var step3 = document.getElementById("step3");

  // Reset the classes
  step1.className = "step0";
  step2.className = "step0 text-center";
  step3.className = "step0 text-muted text-right";

  // Set the active class based on the status
  if (status === "pending" ) {
    step1.className = "step0 active";
  } 
   if (status === "cancel" ) {
    step1.className = "step0 active";
          cancelButton.hide();
          
  }else if (status === "processing") {
    step1.className = "step0 active";
    step2.className = "step0 active text-center";
 
  }else if (status === "completed") {
    step1.className = "step0 active";
    step2.className = "step0 active text-center";
    step3.className = "step0 active text-muted text-right";
         cancelButton.hide();
  }


                                });


</script>
<script>
    function validateDate() {
        var fromDate = document.getElementsByName('from')[0].value;
        var toDate = document.getElementsByName('to')[0].value;

        // Check if both dates are entered
        if (!fromDate || !toDate) {
            alert('Please enter both "From" and "To" dates.');
            return false;
        }

        // Convert date strings to Date objects for comparison
        var fromDateObj = new Date(fromDate);
        var toDateObj = new Date(toDate);

        // Check if the "To" date is greater than or equal to the "From" date
        if (toDateObj < fromDateObj) {
            alert('"To" date must be equal to or later than "From" date.');
            return false;
        }

        // Validation passed
        return true;
    }
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