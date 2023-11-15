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
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="count" value="1" /> 
                            <c:forEach items="${cartlistPage}" var="i" varStatus="loop">
                                <tr class="text-center">


                                    <td>


                                        <span class="custom-checkbox">
                                            <input type="checkbox" id="checkbox${count}" name="options[]" value="${i.key.productID}">
                                            <label for="checkbox${count}"></label>

                                        </span>

                                    </td>

                            <script>
                                function submitDeleteForm() {
                                    var form = document.getElementById("delete-form");
                                    form.submit();
                                }
                            </script>

                            <td class="image-prod"><div class="img" style="background-image:url(images/${i.key.image});"></div></td>

                            <td class="product-name">
                                <h3>${i.key.productName}</h3>

                            </td>

                            <td class="price">$<fmt:formatNumber pattern="##.#" value="${i.key.price-i.key.price*i.key.discount/100}"/></td>

                            <td class="quantity">

                                <div style="display: flex; align-items: center;">
                                    <button style="margin-right: 5px;"><a href="cart?num=-1&pid=${i.key.productID}">-</a></button>
                                    <input type="text" id="quantity" name="quantity" class="form-control input-number text-center" value="${i.value}" min="1" max="${i.key.quantity}" readonly>
                                    <button style="margin-left: 5px;"><a href="cart?num=1&pid=${i.key.productID}">+</a></button>
                                </div>


                            </td>

                            <td class="total">$<fmt:formatNumber pattern="##.#" value="${(i.key.price-i.key.price*i.key.discount/100)*i.value}"/></td>

                            <td class="product-remove">
                                <a href="javascript:void(0);" onclick="submitDeleteForm()">
                                    <span class="ion-ios-close"></span>
                                </a>
                            </td>
                            <form id="delete-form" action="cart" method="post" style="display: none;">
                                <input type="hidden" name="pid" id="delete-pid" value="${i.key.productID}">
                            </form>

                            </tr><!-- END TR-->

                        </c:forEach> 

                        </tbody>
                    </table>
                </div>
            </div>

            <div class="col text-center" style="margin-top: 30px;">
                <div class="block-27">
                    <ul>
                        <c:if test="${tag > 1}">
                            <li><a href="cart?index=${tag-1}"><i class="ion-ios-arrow-back"></i></a></li>
                                </c:if>

                        <c:forEach begin="1" end="${endP}" var="i">
                            <li class="${tag == i ? 'active' : ''}">
                                <a href="cart?index=${i}">${i}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${tag < endP}">
                            <li><a href="cart?index=${tag+1}"><i class="ion-ios-arrow-forward"></i></a></li>
                                </c:if>
                    </ul>

                </div>
            </div>
        </div>

        
       
                            <p style="text-align: center; margin-top: 20px;"><a href="javascript:void(0);" id="checkoutButton" class="btn btn-primary py-3 px-4" onclick="sendData()">Proceed to Checkout</a></p>
<a style="display: block; text-align: center; margin-top: 20px;" href="shop">Shop more product</a>



            </div>
        </div>
    </div>
</section>
<script>
      function sendData() {
        var checkboxes = document.querySelectorAll('input[name="options[]"]:checked');
        var values = Array.from(checkboxes).map(function (checkbox) {
            return checkbox.value;
        });
 if (values.length > 0) {
        var form = document.createElement('form');
        form.method = 'GET';
        form.action = 'checkout';

        values.forEach(function (value) {
            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'selectedItems';
            input.value = value;
            form.appendChild(input);
        });

        document.body.appendChild(form);
        form.submit();
    }else {
            alert('Please select at least one item to proceed.');
        }
    }

</script> 
<%@include file="template/footerJS.jsp" %>