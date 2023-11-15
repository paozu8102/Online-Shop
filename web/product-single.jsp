<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="template/header.jsp" %>
<link href="./css/product-single.css" rel="stylesheet" type="text/css">
<c:if test="${sessionScope.acc.roleID==null}">
    <li class="nav-item"><a href="home" class="nav-link">Home</a></li>

    <li class="nav-item active"><a href="shop" class="nav-link">Shop</a></li>

    <li class="nav-item"><a href="Posts" class="nav-link">Post</a></li>

    <li class="nav-item"><a href="login" class="nav-link">Sign In</a></li>

</c:if>
<c:if test="${sessionScope.acc.roleID==3}">
    <li class="nav-item"><a href="home" class="nav-link">Home</a></li>

    <li class="nav-item active"><a href="shop" class="nav-link">Shop</a></li>


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

<form name="f" action="" method="post">
    <section class="ftco-section">
        <div class="container">
            <div class="row">

                <div class="col-lg-6 mb-5 ftco-animate">

                    <div class="slider-container">
                        <div class="slider">
                            <c:forEach items="${listI}" var="i">
                                <div class="slide">
                                    <img src="images/${i.imageUrl}">
                                </div>
                            </c:forEach>
                        </div>
                        <div class="thumbnails">
                            <c:forEach items="${listI}" var="i">
                                <img src="images/${i.imageUrl}">

                            </c:forEach>
                        </div>
                    </div>


                    <button class="prev-button">&#9668;</button>
                    <button class="next-button">&#9658;</button>
                </div>
                <div class="col-lg-6 product-details pl-md-5 ftco-animate">
                    <h3>${detail.productName}</h3>
                    <div class="rating d-flex"></div>
                    <p class="price">
                        <span>$${detail.price}</span>
                    </p>
                    <p>${detail.description}</p>
                    <p>${detail.quantity} productleft</p>
                    <div class="row mt-4">
                        <div class="col-md-6">
                            <div class="form-group d-flex"></div>
                        </div>

                        <div class="w-100"></div>
                        <div class="input-group col-md-6 d-flex mb-3">
                            <span class="input-group-btn mr-2">
                                <button type="button" class="quantity-left-minus btn"
                                        data-type="minus" data-field="">
                                    <i class="ion-ios-remove"></i>
                                </button>
                            </span> <input type="number" id="quantity" name="num"
                                           class="form-control input-number" value="1" min="1"
                                           max="${detail.quantity}"
                                           oninput="if (this.value > ${detail.quantity}) this.value = ${detail.quantity}">

                            <span class="input-group-btn ml-2">
                                <button type="button" class="quantity-right-plus btn"
                                        data-type="plus" data-field="">
                                    <i class="ion-ios-add"></i>
                                </button>
                            </span>
                        </div>
                        <div class="w-100"></div>

                    </div>
                    <p class="addtocart">
                        <a onclick="buy('${detail.productID}', '${detail.cateID}')"
                           value="Buy item" class="btn btn-primary btn-addtocart white">Add
                            to Cart <i class="icon-shopping-cart"></i>
                        </a>
                    </p>
                </div>
            </div>
        </div>
    </section>
    <div class="shop-info-wrapper">
        <div class="shop-info">
            <div class="shop-info__left">
                <div class="shop-info__avt circular-image">
                    <img src="${artist.getAvatar()}" alt="Your Image">
                </div>
                <div class="shop-info__actions">
                    <h4 class="shop-info__name">
                        ${artist.getUserName()}
                    </h4>
                    <button onclick="viewArtistShop()" type="button" class="shop-info__btn">View Shop</button>
                </div>
            </div>
            <div class="vertical-line"></div>
            <div class="shop-info__right">
                <div class="shop-info__detail" id="phoneNumber">
                    <span class="bold">Phone:</span> ${artist.getPhoneNumber()}
                </div>
                <div class="shop-info__detail" id="email">
                    <span class="bold">Email:</span> ${artist.getEmail()}
                </div>
                <div class="shop-info__detail" id="address">
                    <span class="bold">Address:</span> ${artist.getAddress()}
                </div>
                <div class="shop-info__detail" id="totalNumberItems">
                    <span class="bold">Total Product:</span> ${saleProducts}
                </div>
                <div class="shop-info__detail" id="totalSaleItems">
                    <span class="bold">Total Sale Product:</span> ${boughtProducts}
                </div>
            </div>
        </div>
    </div>
    <section class="ftco-section">
        <div class="container">
            <div class="row justify-content-center mb-3 pb-3">
                <div class="col-md-12 heading-section text-center ftco-animate">
                    <span class="subheading">Products</span>
                    <h2 class="mb-4">Related Products</h2>
                    <p>Painting is an illusion, a piece of magic, so what you see
                        is not what you see.</p>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <c:forEach items="${listP}" var="o">
                    <div class="col-md-6 col-lg-3 ftco-animate">
                        <div class="product">
                            <a href="detail?pid=${o.productID}&cid=${o.cateID}"
                               class="img-prod">

                                <div style="width: 345px; height: 234px; overflow: hidden;">
                                    <img src="images/${o.image}" alt="Colorlib Template"
                                         style="width: 100%; height: 100%; object-fit: cover;">
                                </div>
                                <c:if test="${o.discount != 0}">
                                    <span class="status">${o.discount}%</span>
                                </c:if>
                                <div class="overlay"></div>
                            </a>
                            <div class="text py-3 pb-4 px-3 text-center">
                                <h3>
                                    <a href="detail?pid=${o.productID}&cid=${o.cateID}">${o.productName}</a>
                                </h3>
                                <div class="d-flex">
                                    <div class="pricing">
                                        <c:if test="${o.discount == 0}">
                                            <p class="price">
                                                <span class="price-sale">$${o.price}</span>
                                            </p>
                                        </c:if>
                                        <c:if test="${o.discount != 0}">
                                            <p class="price">
                                                <span class="mr-2 price-dc">$${o.price}</span><span
                                                    class="price-sale">$${o.price-o.price*o.discount/100}</span>
                                            </p>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="bottom-area d-flex px-3">
                                    <div class="m-auto d-flex">
                                        <a href="detail?pid=${o.productID}&cid=${o.cateID}"
                                           class="add-to-cart d-flex justify-content-center align-items-center text-center">
                                            <span><i class="ion-ios-menu"></i></span>
                                        </a> <a href="buy?id=${o.productID}"
                                                class="buy-now d-flex justify-content-center align-items-center mx-1">
                                            <span><i class="ion-ios-cart"></i></span>
                                        </a> <a href="#"
                                                class="heart d-flex justify-content-center align-items-center">
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
        </div>
        </div>
    </section>
</form>
<script>
    // script.js
    // script.js
    document.addEventListener("DOMContentLoaded", function () {
        const slides = document.querySelectorAll(".slide");
        const thumbnails = document.querySelectorAll(".thumbnails img");
        const prevButton = document.querySelector(".prev-button");
        const nextButton = document.querySelector(".next-button");

        let currentSlide = 0;

        // Hiển thị slide đầu tiên
        slides[currentSlide].style.display = "block";

        // Xử lý khi nhấp vào các ảnh nhỏ
        thumbnails.forEach(function (thumbnail, index) {
            thumbnail.addEventListener("click", function () {
                slides[currentSlide].style.display = "none";
                currentSlide = index;
                slides[currentSlide].style.display = "block";

                // Tắt tất cả các ảnh nhỏ khỏi trạng thái tối màu
                thumbnails.forEach(function (thumb) {
                    thumb.style.opacity = 1;
                });

                // Đặt ảnh nhỏ tương ứng vào trạng thái tối màu
                thumbnail.style.opacity = 0.7;
            });
        });

        // Xử lý nút "Previous"
        prevButton.addEventListener("click", function () {
            slides[currentSlide].style.display = "none";
            currentSlide = (currentSlide - 1 + slides.length) % slides.length;
            slides[currentSlide].style.display = "block";

            // Tắt tất cả các ảnh nhỏ khỏi trạng thái tối màu
            thumbnails.forEach(function (thumb) {
                thumb.style.opacity = 1;
            });

            // Đặt ảnh nhỏ tương ứng vào trạng thái tối màu
            thumbnails[currentSlide].style.opacity = 0.7;
        });

        // Xử lý nút "Next"
        nextButton.addEventListener("click", function () {
            slides[currentSlide].style.display = "none";
            currentSlide = (currentSlide + 1) % slides.length;
            slides[currentSlide].style.display = "block";

            // Tắt tất cả các ảnh nhỏ khỏi trạng thái tối màu
            thumbnails.forEach(function (thumb) {
                thumb.style.opacity = 1;
            });

            // Đặt ảnh nhỏ tương ứng vào trạng thái tối màu
            thumbnails[currentSlide].style.opacity = 0.7;
        });

    });


</script>
<script>
    document.querySelector('.quantity-left-minus').addEventListener('click', function () {
        var quantityInput = document.getElementById('quantity');
        var currentValue = parseInt(quantityInput.value);

        if (currentValue > 1) {
            quantityInput.value = currentValue - 1;
        }
    });

    document.querySelector('.quantity-right-plus').addEventListener('click', function () {
        var quantityInput = document.getElementById('quantity');
        var currentValue = parseInt(quantityInput.value);

        if (currentValue < ${detail.quantity}) {
            quantityInput.value = currentValue + 1;
        }
    });

</script>
<script type="text/javascript">
    function buy(id, cid) {
        var m = document.f.num.value;
        document.f.action = "addtocart?id=" + id + "&num=" + m + "&cid=" + cid;
        document.f.submit();
    }
    function viewArtistShop() {
        window.location.replace("${pageContext.request.contextPath}/artist-shop?artistId=${artist.getUserID()}");
            }
</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
<%@include file="template/footerJS.jsp" %>
