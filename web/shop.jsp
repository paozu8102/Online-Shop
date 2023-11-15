<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="Model.Product" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%
          String path = request.getContextPath();
        %>
        <script>
            //from 11-31: ThanhNX
            // Function to set the scroll position in session storage
            function saveScrollPosition() {
                sessionStorage.setItem('scrollPosition', window.scrollY);
            }

            // Function to retrieve and restore the scroll position
            function restoreScrollPosition() {
                var scrollPosition = sessionStorage.getItem('scrollPosition');
                if (scrollPosition) {
                    window.scrollTo(0, scrollPosition);
                }
            }

            // Execute this function when the page is loaded
            window.addEventListener('load', function () {
                restoreScrollPosition();
            });

            // Execute this function when navigating to other pages
            function navigateToOtherPage() {
                // Save the current scroll position before navigating
                saveScrollPosition();
        </script>
        <style>
            body {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 20px;
            }

            #myBtn {
                display: none;
                position: fixed;
                bottom: 20px;
                left: 20px;
                z-index: 99;
                font-size: 18px;
                border: none;
                outline: none;
                background-color: #82AE46;
                color: white;
                cursor: pointer;
                padding: 10px 15px;
                border-radius: 50px;
            }

            #myBtn:hover {
                background-color: #555;
            }


            .slider-container {
                width: 80%;
                margin: 0 auto;
                overflow: hidden;
                position: relative;
                margin-top:  10px;
            }

            .slider {
                width: 100%;
            }

            .slide {
                display: none;
            }

            .slide img {
                max-width: 100%;
                height: auto;
            }

            .thumbnails {
                text-align: center;
                margin-top: 10px;
            }

            .thumbnails img {
                max-width: 80px;
                height: auto;
                margin: 0 10px;
                cursor: pointer;
                transition: opacity 0.3s;
            }

            .thumbnails img:hover {
                opacity: 0.7;
            }

            /* Định dạng nút điều hướng */
            .prev-button,
            .next-button {
                position: absolute;
                top: 50%;
                padding: 5px; /* Kích thước nút nhỏ hơn */

                color: #fff;
                border: none;
                cursor: pointer;
                font-size: 20px; /* Kích thước chữ */
                transition: background-color 0.3s; /* Hiệu ứng hover */
                z-index: 1; /* Đảm bảo nút điều hướng nằm trên ảnh */
            }

            .prev-button {
                left: 10px;
                transform: translateY(-50%);
            }

            .next-button {
                right: 10px;
                transform: translateY(-50%);
            }

            /* Hiệu ứng hover */
            .prev-button:hover,
            .next-button:hover {
                color: white; /* Màu xám */
                opacity: 1; /* Độ mờ tối thiểu */
                background-color: gray;
            }


            .search-container input [type=text] {
                padding: 6px;
                margin-top: 8px;
                font-size: 10px;


            }

            .search-container button {
                float: right;
                padding: 1px 10px;
                margin-right: 16px;
                background: #82ae46;
                font-size: 17px;
                border: none;
                cursor: pointer;
            }

            .search-container button:hover {
                background: #82ae46;
            }



            .sorting {
                display: flex; /* Use flexbox to align items in a row */
                align-items: center; /* Vertically center items in the flex container */
                gap: 20px; /* Space between the two forms */
            }

            /* Style the select elements */
            form select {
                padding: 10px;
                border: 1px solid #ccc;
                background-color: #fff;
                border-radius: 5px;
                outline: none;
            }

            /* Style the select arrow */
            form select::after {
                content: "\25BC"; /* Unicode down arrow character */
                position: absolute;
                top: 50%;
                right: 10px;
                transform: translateY(-50%);
                pointer-events: none;
            }
            .product-category {
                width: 100%; /* Set the width to 100% to expand to the full available width */
                /* You can also add other CSS properties to style the menu, such as background color, padding, etc. */
            }

            .side{
                padding: 1em;
                width: 250px;
                float: left ;
                h3{
                    font-size: 14px;
                    text-transform: uppercase;

                    margin-bottom: 20px;
                }
                ul{
                    li{
                        font-size: 14px;
                    }

                }
            }
            .custom-container {
                max-width: 1200px; /* Adjust the width as per your preference */
                margin: 0 auto; /* Center the container horizontally */
            }
            .your-ul-class {
                margin-right: 50px;
                list-style-type: none;
                /* Add any other styling you want for the list here */
            }

            .wrapper {

                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
            }

            .filter-price {
                width: 220px;
                border: 0;
                padding: 0;
                margin: 0;
            }

            .price-title {
                position: relative;
                color: #fff;
                font-size: 14px;
                line-height: 1.2em;
                font-weight: 400;
                background: #82ae46;
                padding:10px;
            }

            .price-container {
                display: flex;
                border: 1px solid #ccc;
                padding: 5px;
                margin-right: 40px;
                width:120px;
            }

            .price-field {
                position: relative;
                width: 100%;
                height: 36px;
                box-sizing: border-box;
                padding-top: 15px;
                padding-left: 0px;
            }

            .price-field input[type=range] {
                position: absolute;
            }

            /* Reset style for input range */

            .price-field input[type=range] {
                width: 100%;
                height: 7px;
                border: 1px solid #000;
                outline: 0;
                box-sizing: border-box;
                border-radius: 5px;
                pointer-events: none;
                -webkit-appearance: none;
                background: #82ae46;
            }

            .price-field input[type=range]::-webkit-slider-thumb {
                -webkit-appearance: none;
            }

            .price-field input[type=range]:active,
            .price-field input[type=range]:focus {
                outline: 0;
            }

            .price-field input[type=range]::-ms-track {
                width: 188px;
                height: 2px;
                border: 0;
                outline: 0;
                box-sizing: border-box;
                border-radius: 5px;
                pointer-events: none;
                background: transparent;
                border-color: transparent;
                color: red;
                border-radius: 5px;
            }

            /* Style toddler input range */

            .price-field input[type=range]::-webkit-slider-thumb {
                /* WebKit/Blink */
                position: relative;
                -webkit-appearance: none;
                margin: 0;
                border: 0;
                outline: 0;
                border-radius: 50%;
                height: 10px;
                width: 10px;
                margin-top: -4px;
                background-color: #fff;
                cursor: pointer;
                cursor: pointer;
                pointer-events: all;
                z-index: 100;
            }

            .price-field input[type=range]::-moz-range-thumb {
                /* Firefox */
                position: relative;
                appearance: none;
                margin: 0;
                border: 0;
                outline: 0;
                border-radius: 50%;
                height: 10px;
                width: 10px;
                margin-top: -5px;
                background-color: #fff;
                cursor: pointer;
                cursor: pointer;
                pointer-events: all;
                z-index: 100;
            }

            .price-field input[type=range]::-ms-thumb  {
                /* IE */
                position: relative;
                appearance: none;
                margin: 0;
                border: 0;
                outline: 0;
                border-radius: 50%;
                height: 10px;
                width: 10px;
                margin-top: -5px;
                background-color: #242424;
                cursor: pointer;
                cursor: pointer;
                pointer-events: all;
                z-index: 100;
            }

            /* Style track input range */

            .price-field input[type=range]::-webkit-slider-runnable-track {
                /* WebKit/Blink */
                width: 188px;
                height: 2px;
                cursor: pointer;
                background: #555;
                border-radius: 5px;
            }

            .price-field input[type=range]::-moz-range-track {
                /* Firefox */
                width: 188px;
                height: 2px;
                cursor: pointer;
                background: #242424;
                border-radius: 5px;
            }

            .price-field input[type=range]::-ms-track {
                /* IE */
                width: 188px;
                height: 2px;
                cursor: pointer;
                background: #242424;
                border-radius: 5px;
            }

            /* Style for input value block */

            .price-wrap {
                display: flex;
                color: #242424;
                font-size: 14px;
                line-height: 1.2em;
                font-weight: 400;
                margin-bottom: 0px;
            }

            .price-wrap-1,
            .price-wrap-2 {
                display: flex;
                margin-left: 0px;

            }

            .price-title {
                margin-right: 5px;
            }

            .price-wrap_line {
                margin: 6px 0px 5px 5px;
            }

            .price-wrap #one,
            .price-wrap #two {
                width: 30px;
                text-align: right;
                margin: 0;
                padding: 0;
                margin-right: 2px;
                background:  0;
                border: 0;
                outline: 0;
                color: #242424;
                font-family: 'Karla', 'Arial', sans-serif;
                font-size: 14px;
                line-height: 1.2em;
                font-weight: 400;
            }
            .price-wrap #minWidth,
            .price-wrap #maxWidth {
                width: 30px;
                text-align: right;
                margin: 0;
                padding: 0;
                margin-right: 2px;
                background:  0;
                border: 0;
                outline: 0;
                color: #242424;
                font-family: 'Karla', 'Arial', sans-serif;
                font-size: 14px;
                line-height: 1.2em;
                font-weight: 400;
            }
            .price-wrap #minHeight,
            .price-wrap #maxHeight {
                width: 30px;
                text-align: right;
                margin: 0;
                padding: 0;
                margin-right: 2px;
                background:  0;
                border: 0;
                outline: 0;
                color: #242424;
                font-family: 'Karla', 'Arial', sans-serif;
                font-size: 14px;
                line-height: 1.2em;
                font-weight: 400;
            }
            .price-wrap label {
                text-align: right;
                margin-top: 6px;
                padding-left: 5px;
            }

            /* Style for active state input */

            .price-field input[type=range]:hover::-webkit-slider-thumb {
                box-shadow: 0 0 0 0.5px #242424;
                transition-duration: 0.3s;
            }

            .price-field input[type=range]:active::-webkit-slider-thumb {
                box-shadow: 0 0 0 0.5px #242424;
                transition-duration: 0.3s;
            }

        </style>
        <title>Palette Joy - Bring art to your home</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
        <link rel="stylesheet" href="css/animate.css">

        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">

        <link rel="stylesheet" href="css/aos.css">

        <link rel="stylesheet" href="css/ionicons.min.css">

        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="css/jquery.timepicker.css">


        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css">
    </head>
    <body class="goto-here">
        <button onclick="topFunction()" id="myBtn" title="Go to top">&#8679;</button>
        <script>
                //from 91-107 ThanhNX
                let mybutton = document.getElementById("myBtn");
                // When the user scrolls down 20px from the top of the document, show the button
                window.onscroll = function () {
                    scrollFunction()
                };
                function scrollFunction() {
                    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                        mybutton.style.display = "block";
                    } else {
                        mybutton.style.display = "none";
                    }
                }

                // When the user clicks on the button, scroll to the top of the document
                function topFunction() {
                    document.body.scrollTop = 0;
                    document.documentElement.scrollTop = 0;
                }
        </script>
        <div class="py-1 bg-primary">
            <div class="container">
                <div class="row no-gutters d-flex align-items-start align-items-center px-md-0">
                    <div class="col-lg-12 d-block">
                        <div class="row d-flex">
                            <div class="col-md pr-4 d-flex topper align-items-center">
                                <div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-phone2"></span></div>
                                <span class="text">+0368686868</span>
                            </div>
                            <div class="col-md pr-4 d-flex topper align-items-center">
                                <div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-paper-plane"></span></div>
                                <span class="text">Palettejoy@gmail.com</span>
                            </div>
                            <div class="col-md-5 pr-4 d-flex topper align-items-center text-lg-right">
                                <span class="text">3-5 Business days delivery &amp; Free Returns</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
            <div class="container">
                <a class="navbar-brand" href="home">Palette Joy</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="oi oi-menu"></span> Menu
                </button>

                <div class="collapse navbar-collapse" id="ftco-nav">
                    <ul class="navbar-nav ml-auto">

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

        <div class="hero-wrap hero-bread" style="background-image: url('images/bg_1.jpg');">
            <div class="container">
                <div class="row no-gutters slider-text align-items-center justify-content-center">
                    <div class="col-md-9 ftco-animate text-center">
                        <p class="breadcrumbs"><span class="mr-2"><a href="index.jsp">Home</a></span> <span>Products</span></p>
                        <h1 class="mb-0 bread">Products</h1>
                    </div>
                </div>
            </div>
        </div>

        <section class="ftco-section">
            <div class="custom-container">
                <div class="row justify-content-center">

                    <div class="col-md-10 col-lg-12 mb-5 text-center">
                        <ul class="product-category">
                            <li>
                                <a href="shop" class="${param.promat != 0 ? '' : 'active'}">All</a>
                            </li>
                            <c:forEach items="${listC}" var="c">
                                <li>
                                    <a href="shop?promat=${c.categoryID}" class="${param.promat == c.categoryID ? 'active' : ''}">
                                        ${c.categoryName}
                                    </a>
                                </li>
                            </c:forEach>

                            <li>
                                <form action="shop" method="post">
                                    <select name="sortprice" id="sortprice" style="color: #82ae46;">
                                        <option value="" style="color: #82ae46;">Sort By Price</option>
                                        <option value="asc" style="color: #82ae46;"
                                                ${param.sortprice == 'asc' ? 'selected' : ''}>Low to High</option>
                                        <option value="desc" style="color: #82ae46;"
                                                ${param.sortprice == 'desc' ? 'selected' : ''}>High to Low</option>
                                    </select>

                                </form>
                            </li>
                            <li>
                                <form action="shop" method="post">
                                    <select name="sortname" id="sortname" style="color: #82ae46;">
                                        <option value="" style="color: #82ae46;">Sort By Name</option>
                                        <option value="asc" style="color: #82ae46;"
                                                ${param.sortname == 'asc' ? 'selected' : ''}>A -> Z</option>
                                        <option value="desc" style="color: #82ae46;"
                                                ${param.sortname == 'desc' ? 'selected' : ''}>Z -> A</option>
                                    </select>

                                </form>
                            </li>
                            <li>
                                <div style="display: inline-block; margin-left: 10px">
                                    <form action="shop" method="post" class="search-form">
                                        <div class="search-container">
                                            <input name="txt" type="text"  placeholder="Search" >
                                            <button type="submit"><i class="icon-search" style="color: white;"></i></button>
                                        </div>
                                    </form>
                                </div>
                            </li>
                        </ul>

                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-3 col-xl-3">

                        <div class="col-sm-12">
                            <div class="side border mb-1">
                                <h3>Painting Topic</h3>



                                <div class="your-ul-class">
                                    <c:forEach items="${listT}" var="t">
                                        <li><a href="shop?protopic=${t.categoryID}">${t.categoryName}</a></li>
                                        </c:forEach>
                                </div>

                            </div>
                        </div>
                        <div class="col-sm-12">
                            <div class="side border mb-1">
                                <h3>Discount</h3>
                                <div class="your-ul-class">
                                    <li><a href="shop?discountmin=0&discountmax=20">0-20%</a></li>
                                    <li><a href="shop?discountmin=20&discountmax=40">20-40%</a></li>
                                    <li><a href="shop?discountmin=40&discountmax=60">40-60%</a></li>
                                    <li><a href="shop?discountmin=60&discountmax=80">60-80%</a></li>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-12">
                            <div class="side border mb-1">
                                <h3>Price</h3>
                                <form action="shop" method="post">
                                    <div class="wrapper">
                                        <fieldset class="filter-price">

                                            <div class="price-field">
                                                <input type="range" min="0" max="1000" value="300" id="lower">
                                                <input  type="range" min="0" max="1000" value="800" id="upper">
                                            </div>
                                            <div class="price-wrap">

                                                <div class="price-container">
                                                    <div class="price-wrap-1">

                                                        <label for="one">$</label>
                                                        <input name="minprice" id="one">
                                                    </div>
                                                    <div class="price-wrap_line">-</div>
                                                    <div class="price-wrap-2">
                                                        <label for="two">$</label>
                                                        <input name="maxprice" id="two">

                                                    </div>
                                                </div>
                                                <button type="submit" class="price-title">FILTER</button>
                                            </div>
                                        </fieldset>
                                    </div>

                                </form>
                            </div>



                        </div>
                        <div class="col-sm-12">
                            <div class="side border mb-1">
                                <h3>Size</h3>
                                <form action="shop" method="post">
                                    <div class="wrapper">
                                        <fieldset class="filter-price">
                                            <h5>Width</h5>
                                            <div class="price-field">
                                                <input type="range" min="0" max="100" value="20" id="widthLower">
                                                <input type="range" min="0" max="100" value="50" id="widthUpper">
                                            </div>
                                            <div class="price-wrap">

                                                <div class="price-container" > 
                                                    <div class="price-wrap-1">

                                                        <input name="minwidth" id="minWidth">

                                                    </div>
                                                    <div class="price-wrap_line">-</div>
                                                    <div class="price-wrap-2">

                                                        <input name="maxwidth" id="maxWidth">
                                                        <label for="two">cm</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </fieldset>
                                    </div>
                                    <h5>Height</h5>
                                    <fieldset class="filter-price">

                                        <div class="price-field">
                                            <input type="range" min="0" max="100" value="20" id="heightLower">
                                            <input type="range" min="0" max="100" value="50" id="heightUpper">
                                        </div>
                                        <div class="price-wrap">

                                            <div class="price-container">
                                                <div class="price-wrap-1">

                                                    <input name="minheight" id="minHeight">
                                                </div>
                                                <div class="price-wrap_line">-</div>
                                                <div class="price-wrap-2">

                                                    <input name="maxheight" id="maxHeight">
                                                    <label for="two">cm</label>
                                                </div>
                                            </div>
                                            <button type="submit" class="price-title">FILTER</button>
                                        </div>
                                    </fieldset>

                                </form>
                            </div>
                        </div>

                    </div>
                    <div class="col-lg-9 col-xl-9">
                        <div class="row row-pb-md">

                            <c:forEach items="${listP}" var="o">
                                <div class="col-md-4 ftco-animate">
                                    <div class="product">
                                        <a href="detail?pid=${o.productID}&cid=${o.cateID}" class="img-prod">

                                            <div style="width: 345px; height: 234px; overflow: hidden;">
                                                <img src="images/${o.image}" alt="Colorlib Template" style="width: 100%; height: 100%; object-fit: cover;">
                                            </div>

                                            <c:if test="${o.discount != 0}">
                                                <span class="status">${o.discount}%</span>
                                            </c:if>
                                            <div class="overlay"></div>
                                        </a>
                                        <div class="text py-3 pb-4 px-3 text-center">
                                            <h3><a href="detail?pid=${o.productID}&cid=${o.cateID}">${o.productName}</a></h3>
                                            <div class="d-flex">
                                                <div class="pricing">
                                                    <c:if test="${o.discount == 0}">
                                                        <p class="price"><span class="price-sale">$${o.price}</span></p>
                                                    </c:if>
                                                    <c:if test="${o.discount != 0}">
                                                        <p class="price"><span class="mr-2 price-dc">$${o.price}</span><span class="price-sale">$${o.price-o.price*o.discount/100}</span></p>
                                                    </c:if>
                                                </div>
                                            </div>
                                            <div class="bottom-area d-flex px-3">
                                                <div class="m-auto d-flex">
                                                    <a href="detail?pid=${o.productID}&cid=${o.cateID}" class="add-to-cart d-flex justify-content-center align-items-center text-center">
                                                        <span><i class="ion-ios-menu"></i></span>
                                                    </a>
                                                    <a href="buy?id=${o.productID}" class="buy-now d-flex justify-content-center align-items-center mx-1">
                                                        <span><i class="ion-ios-cart"></i></span>
                                                    </a>
                                                    <a href="#" class="heart d-flex justify-content-center align-items-center">
                                                        <span><i class="ion-ios-heart"></i></span>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>


                            <div class="col text-center">
                                <div class="block-27">
                                    <ul>
                                        <c:if test="${tag > 1}">
                                            <li><a href="shop?index=${tag-1}"><i class="ion-ios-arrow-back"></i></a></li>
                                                </c:if>

                                        <c:forEach begin="1" end="${endP}" var="i">
                                            <li class="${tag == i ? 'active' : ''}">
                                                <a href="shop?index=${i}">${i}</a>
                                            </li>
                                        </c:forEach>

                                        <c:if test="${tag < endP}">
                                            <li><a href="shop?index=${tag+1}"><i class="ion-ios-arrow-forward"></i></a></li>
                                                </c:if>
                                    </ul>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </section>


        <footer class="ftco-footer ftco-section">
            <script>

                    document.getElementById("sortprice").onchange = function () {
                        this.form.submit();
                    };
                    document.getElementById("sortname").onchange = function () {
                        this.form.submit();
                    }
                    ;
              var lowerSlider = document.querySelector('#lower');
var upperSlider = document.querySelector('#upper');
var lowerInput = document.querySelector('#one');
var upperInput = document.querySelector('#two');

// Initialize input values
lowerInput.value = lowerSlider.value;
upperInput.value = upperSlider.value;

lowerSlider.oninput = function () {
    // Ensure upper value is greater than lower value
    if (parseInt(this.value) > parseInt(upperSlider.value)) {
        upperSlider.value = this.value;
        upperInput.value = this.value;
    }
    lowerInput.value = this.value;
};

upperSlider.oninput = function () {
    // Ensure lower value is less than upper value
    if (parseInt(this.value) < parseInt(lowerSlider.value)) {
        lowerSlider.value = this.value;
        lowerInput.value = this.value;
    }
    upperInput.value = this.value;
};


            </script>


            <script>
                  // Width sliders
var widthLowerSlider = document.querySelector('#widthLower');
var widthUpperSlider = document.querySelector('#widthUpper');
var minWidthInput = document.querySelector('#minWidth');
var maxWidthInput = document.querySelector('#maxWidth');

minWidthInput.value = widthLowerSlider.value;
maxWidthInput.value = widthUpperSlider.value;

widthLowerSlider.oninput = function () {
    if (parseInt(this.value) > parseInt(widthUpperSlider.value)) {
        widthUpperSlider.value = this.value;
        maxWidthInput.value = this.value;
    }
    minWidthInput.value = this.value;
};

widthUpperSlider.oninput = function () {
    if (parseInt(this.value) < parseInt(widthLowerSlider.value)) {
        widthLowerSlider.value = this.value;
        minWidthInput.value = this.value;
    }
    maxWidthInput.value = this.value;
};

// Height sliders
var heightLowerSlider = document.querySelector('#heightLower');
var heightUpperSlider = document.querySelector('#heightUpper');
var minHeightInput = document.querySelector('#minHeight');
var maxHeightInput = document.querySelector('#maxHeight');

minHeightInput.value = heightLowerSlider.value;
maxHeightInput.value = heightUpperSlider.value;

heightLowerSlider.oninput = function () {
    if (parseInt(this.value) > parseInt(heightUpperSlider.value)) {
        heightUpperSlider.value = this.value;
        maxHeightInput.value = this.value;
    }
    minHeightInput.value = this.value;
};

heightUpperSlider.oninput = function () {
    if (parseInt(this.value) < parseInt(heightLowerSlider.value)) {
        heightLowerSlider.value = this.value;
        minHeightInput.value = this.value;
    }
    maxHeightInput.value = this.value;
};

            </script>
            <%@include file="template/footer.jsp" %>        
