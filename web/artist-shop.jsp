<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList" %>
<%@page import="Model.Product" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <% String path = request.getContextPath(); %>
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
            }

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
                margin-top: 10px;
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
                padding: 5px;
                /* Kích thước nút nhỏ hơn */
                color: #fff;
                border: none;
                cursor: pointer;
                font-size: 20px;
                /* Kích thước chữ */
                transition: background-color 0.3s;
                /* Hiệu ứng hover */
                z-index: 1;
                /* Đảm bảo nút điều hướng nằm trên ảnh */
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
                color: white;
                /* Màu xám */
                opacity: 1;
                /* Độ mờ tối thiểu */
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
                display: flex;
                /* Use flexbox to align items in a row */
                align-items: center;
                /* Vertically center items in the flex container */
                gap: 20px;
                /* Space between the two forms */
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
                content: "\25BC";
                /* Unicode down arrow character */
                position: absolute;
                top: 50%;
                right: 10px;
                transform: translateY(-50%);
                pointer-events: none;
            }

            .product-category {
                width: 100%;
                /* Set the width to 100% to expand to the full available width */
                /* You can also add other CSS properties to style the menu, such as background color, padding, etc. */
            }

            .side {
                padding: 1em;
                width: 250px;
                float: left;
            }

            h3 {
                font-size: 14px;
                text-transform: uppercase;
                margin-bottom: 20px;
            }

            ul {
                li {
                    font-size: 14px;

                }

            }


            .custom-container {
                max-width: 1200px;
                /* Adjust the width as per your preference */
                margin: 0 auto;
                /* Center the container horizontally */
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
                padding: 10px;
            }

            .price-container {
                display: flex;
                border: 1px solid #ccc;
                padding: 5px;
                margin-right: 40px;
                width: 120px;
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

            .price-field input[type=range]::-ms-thumb {
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
            .price-wrap #two,
            .price-wrap #one-discount,
            .price-wrap #two-discount {
                width: 30px;
                text-align: right;
                margin: 0;
                padding: 0;
                margin-right: 2px;
                background: 0;
                border: 0;
                outline: 0;
                color: #242424;
                font-family: 'Karla', 'Arial', sans-serif;
                font-size: 14px;
                line-height: 1.2em;
                font-weight: 400;
            }

            #one-discount,
            #two-discount {
                width: 30px;
                text-align: right;
                margin: 0;
                padding: 0;
                margin-right: 2px;
                background: 0;
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
                background: 0;
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
                background: 0;
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

            .circular-image {
                width: 150px;
                height: 150px;
                border-radius: 50%;
                overflow: hidden;
                flex-shrink: 0;
            }

            .circular-image img {
                width: 100%;
                height: 100%;
                object-fit: cover;
            }

            .shop-info-wrapper {
                width: 100%;
                display: flex;
                justify-content: center;
            }

            .shop-info {
                display: flex;
                flex-direction: row;
                width: 80%;
                flex-wrap: nowrap;
                border: solid 1px rgb(223, 220, 220);
                justify-content: space-between;
                margin-top: 2rem;
                padding: 1.5rem 0.75rem;
                box-shadow: 10px 10px 5px 0px rgba(0, 0, 0, 0.07);
            }

            .shop-info__left {
                display: flex;
                flex-direction: row;
                gap: 3rem;
                width: 35%;
            }

            .shop-info__right {
                display: flex;
                align-items: center;
                flex-wrap: wrap;
                flex-direction: row;
                gap: 2rem;
                max-width: 65%;
            }

            .shop-info__detail {
                display: flex;
                width: 30%;
                justify-content: space-between;
            }

            .shop-info__actions {
                display: flex;
                flex-direction: column;
            }

            .shop-info__btn {
                background-image: linear-gradient(-180deg, #00D775, #00BD68);
                border-radius: 5px;
                box-shadow: rgba(0, 0, 0, 0.1) 0 2px 4px;
                color: #FFFFFF;
                cursor: pointer;
                display: inline-block;
                outline: 0;
                overflow: hidden;
                position: relative;
                text-align: center;
                user-select: none;
                white-space: nowrap;
                z-index: 9;
                border: 0;
                text-align: center;
                padding: 0.25rem 0.75rem;
            }

            .shop-info__btn:hover {
                background: #00bd68;
            }

            .bold {
                font-weight: bold;
            }

            .vertical-line {
                width: 1px;
                /* Adjust the width of the line as needed */
                background-color: rgb(223, 220, 220);
                /* Line color */
                margin: 0 1rem;
            }
        </style>
        <title>Palette Joy - Bring art to your home</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link
            href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap"
            rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i&display=swap"
              rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700&display=swap"
              rel="stylesheet">

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
        <link rel="stylesheet" type="text/css"
              href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css">
        <link rel="stylesheet" type="text/css"
              href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css">
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
                                <div class="icon mr-2 d-flex justify-content-center align-items-center">
                                    <span class="icon-phone2"></span>
                                </div>
                                <span class="text">+0368686868</span>
                            </div>
                            <div class="col-md pr-4 d-flex topper align-items-center">
                                <div class="icon mr-2 d-flex justify-content-center align-items-center">
                                    <span class="icon-paper-plane"></span>
                                </div>
                                <span class="text">Palettejoy@gmail.com</span>
                            </div>
                            <div class="col-md-5 pr-4 d-flex topper align-items-center text-lg-right">
                                <span class="text">3-5 Business days delivery &amp; Free
                                    Returns</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
             id="ftco-navbar">
            <div class="container">
                <a class="navbar-brand" href="home">Palette Joy</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="oi oi-menu"></span> Menu
                </button>

                <div class="collapse navbar-collapse" id="ftco-nav">
                    <ul class="navbar-nav ml-auto">

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

        <div class="hero-wrap hero-bread" style="background-image: url('images/bg_1.jpg');">
            <div class="container">
                <div class="row no-gutters slider-text align-items-center justify-content-center">
                    <div class="col-md-9 ftco-animate text-center">
                        <p class="breadcrumbs">
                            <span class="mr-2"></span> <span>Artist</span>
                        </p>
                        <h1 class="mb-0 bread">Products</h1>
                    </div>
                </div>
            </div>
        </div>
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
                        <!--<button class="shop-info__btn">Xem Shop</button> -->
                    </div>
                </div>
                <div class="vertical-line"></div>
                <div class="shop-info__right">
                    <div class="shop-info__detail" id="phoneNumber">
                        <span class="bold">Phone: <h6>${artist.getPhoneNumber()}</h6></span>
                    </div>
                    <div class="shop-info__detail" id="email">
                        <span class="bold">Email: <h6>${artist.getEmail()}</h6></span>
                    </div>
                    <div class="shop-info__detail" id="address">
                        <span class="bold">Adrress: <h6>${artist.getAddress()}</h6></span>
                    </div>
                    <div class="shop-info__detail" id="totalNumberItems">
                        <span class="bold">Total Product: <h6>${saleProducts}</h6></span>
                    </div>
                    <div class="shop-info__detail" id="totalSaleItems">
                        <span class="bold">Total Sale Product: <h6>${boughtProducts}</h6></span>
                    </div>
                </div>
            </div>
        </div>
        <section class="ftco-section">
            <form action="artist-shop?artistId=${artist.getUserID()}" method="post"
                  class="custom-container">
                <div class="row justify-content-center">

                    <div class="col-md-10 col-lg-12 mb-5 text-center">
                        <ul class="product-category">

                            <li>
                                <select name="sortprice" id="sortprice" style="color: #82ae46;">
                                    <option value="" style="color: #82ae46;">Sort By Price</option>
                                    <option value="asc" style="color: #82ae46;" ${param.sortprice=='asc'
                                                                                  ? 'selected' : '' }>Low to High
                                    </option>
                                    <option value="desc" style="color: #82ae46;"
                                            ${param.sortprice=='desc' ? 'selected' : '' }>High to Low
                                    </option>
                                    <option value="best" style="color: #82ae46;"
                                            ${param.sortprice=='best' ? 'selected' : '' }>Best Seller
                                    </option>
                                </select>
                            </li>
                            <li>

                                <select name="sortname" id="sortname" style="color: #82ae46;">
                                    <option value="" style="color: #82ae46;">Sort By Name</option>
                                    <option value="asc" style="color: #82ae46;" ${param.sortname=='asc'
                                                                                  ? 'selected' : '' }>A -> Z
                                    </option>
                                    <option value="desc" style="color: #82ae46;"
                                            ${param.sortname=='desc' ? 'selected' : '' }>Z -> A
                                    </option>
                                </select>


                            </li>
                            <li>
                                <div style="display: inline-block; margin-left: 10px">

                                    <div class="search-container">
                                        <input value="${param.txt}" name="txt" type="text"
                                               placeholder="Search">
                                        <button type="submit"><i class="icon-search"
                                                                 style="color: white;"></i></button>
                                    </div>
                                </div>
                            </li>
                        </ul>

                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-3 col-xl-3">

                        <div class="col-sm-12">
                            <div class="side border mb-1">
                                <h3>Category</h3>


                                <div class="your-ul-class">
                                    <li>
                                        <a href="artist-shop?artistId=${artist.getUserID()}&"
                                           >All</a>
                                    </li>
                                    <c:forEach items="${listC}" var="c">
                                        <li>
                                            <a href="artist-shop?artistId=${artist.getUserID()}&promat=${c.categoryID}"
                                               >
                                                ${c.categoryName}
                                            </a>
                                        </li>
                                    </c:forEach>
                                </div>

                            </div>
                        </div>
                        <div class="col-sm-12">
                            <div class="side border mb-1">
                                <h3>Painting Topic</h3>


                                <div class="your-ul-class">
                                    <c:forEach items="${listT}" var="t">
                                        <li>
                                            <a
                                                href="artist-shop?artistId=${artist.getUserID()}&protopic=${t.categoryID}">${t.categoryName}</a>
                                        </li>
                                    </c:forEach>
                                </div>

                            </div>
                        </div>
                        <div class="col-sm-12">
                            <div class="side border mb-1">
                                <h3>Discount</h3>
                                <div class="wrapper">
                                    <fieldset class="filter-price">
                                        <div class="price-field">
                                            <input type="range" min="0" max="100" value="0" id="lower-discount">
                                            <input type="range" min="0" max="100" value="100" id="upper-discount">
                                        </div>
                                        <div class="price-wrap">
                                            <div class="price-container">
                                                <div class="price-wrap-1">
                                                    <label for="one-discount">%</label>
                                                    <input value="0" name="discountmin" id="one-discount">
                                                </div>
                                                <div class="price-wrap_line">-</div>
                                                <div class="price-wrap-2">
                                                    <label for="two-discount">%</label>
                                                    <input value="100" name="discountmax" id="two-discount">
                                                </div>
                                            </div>
                                            <button type="submit" class="price-title">FILTER</button>
                                        </div>
                                    </fieldset>
                                </div>

                                <script>
                                    const lowerDiscountInput = document.getElementById('lower-discount');
                                    const upperDiscountInput = document.getElementById('upper-discount');
                                    const minDiscountInput = document.getElementById('one-discount');
                                    const maxDiscountInput = document.getElementById('two-discount');

                                    // Function to update discount input values and prevent crossing
                                    function updateDiscountValues() {
                                        const lowerDiscountValue = parseInt(lowerDiscountInput.value);
                                        const upperDiscountValue = parseInt(upperDiscountInput.value);

                                        if (lowerDiscountValue >= upperDiscountValue) {
                                            upperDiscountInput.value = lowerDiscountValue + 1;
                                        }

                                        minDiscountInput.value = lowerDiscountInput.value;
                                        maxDiscountInput.value = upperDiscountInput.value;
                                    }

                                    // Event listeners for discount range input changes
                                    lowerDiscountInput.addEventListener('input', updateDiscountValues);
                                    upperDiscountInput.addEventListener('input', updateDiscountValues);

                                    // Initial call to set default discount values
                                    updateDiscountValues();
                                </script>
                            </div>
                        </div>
                        <div class="col-sm-12">
                            <div class="side border mb-1">
                                <h3>Price</h3>

                                <div class="wrapper">
                                    <fieldset class="filter-price">
                                        <div class="price-field">
                                            <input type="range" min="0" max="1000" value="0" id="lower">
                                            <input type="range" min="0" max="1000" value="1000" id="upper">
                                        </div>
                                        <div class="price-wrap">
                                            <div class="price-container">
                                                <div class="price-wrap-1">
                                                    <label for="one">$</label>
                                                    <input value="${param.minprice}" name="minprice" id="one">
                                                </div>
                                                <div class="price-wrap_line">-</div>
                                                <div class="price-wrap-2">
                                                    <label for="two">$</label>
                                                    <input value="${param.maxprice}" name="maxprice" id="two">
                                                </div>
                                            </div>
                                            <button type="submit" class="price-title">FILTER</button>
                                        </div>
                                    </fieldset>
                                </div>
                                <script>
                                    const lowerInput = document.getElementById('lower');
                                    const upperInput = document.getElementById('upper');
                                    const minPriceInput = document.getElementById('one');
                                    const maxPriceInput = document.getElementById('two');

                                    // Function to update the input values and prevent crossing
                                    function updateValues() {
                                        const lowerValue = parseInt(lowerInput.value);
                                        const upperValue = parseInt(upperInput.value);

                                        if (lowerValue >= upperValue) {
                                            upperInput.value = lowerValue + 1;
                                        }

                                        minPriceInput.value = lowerInput.value;
                                        maxPriceInput.value = upperInput.value;
                                    }

                                    // Event listeners for range input changes
                                    lowerInput.addEventListener('input', updateValues);
                                    upperInput.addEventListener('input', updateValues);

                                    // Initial call to set default values
                                    updateValues();
                                </script>
                            </div>
                        </div>
                        <div class="col-sm-12">
                            <div class="side border mb-1">
                                <h3>Size</h3>

                                <div class="wrapper">
                                    <fieldset class="filter-price">
                                        <h5>Width</h5>
                                        <div class="price-field">
                                            <input type="range" min="0" max="100" value="0" id="widthLower">
                                            <input type="range" min="0" max="100" value="100" id="widthUpper">
                                        </div>
                                        <div class="price-wrap">
                                            <div class="price-container">
                                                <div class="price-wrap-1">
                                                    <input value="${param.minwidth}" name="minwidth" id="minWidth">
                                                </div>
                                                <div class="price-wrap_line">-</div>
                                                <div class="price-wrap-2">
                                                    <input value="${param.maxwidth}" name="maxwidth" id="maxWidth">
                                                    <label for="two">cm</label>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </div>

                                <script>
                                    const widthLowerInput = document.getElementById('widthLower');
                                    const widthUpperInput = document.getElementById('widthUpper');
                                    const minWidthInput = document.getElementById('minWidth');
                                    const maxWidthInput = document.getElementById('maxWidth');

                                    // Function to update the width input values and prevent crossing
                                    function updateWidthValues() {
                                        const widthLowerValue = parseInt(widthLowerInput.value);
                                        const widthUpperValue = parseInt(widthUpperInput.value);

                                        if (widthLowerValue >= widthUpperValue) {
                                            widthUpperInput.value = widthLowerValue + 1;
                                        }

                                        minWidthInput.value = widthLowerInput.value;
                                        maxWidthInput.value = widthUpperInput.value;
                                    }

                                    // Event listeners for width range input changes
                                    widthLowerInput.addEventListener('input', updateWidthValues);
                                    widthUpperInput.addEventListener('input', updateWidthValues);

                                    // Initial call to set default width values
                                    updateWidthValues();
                                </script>
                                <h5>Height</h5>
                                <div class="wrapper">
                                    <fieldset class="filter-price">
                                        <div class="price-field">
                                            <input type="range" min="0" max="100" value="0" id="heightLower">
                                            <input type="range" min="0" max="100" value="100" id="heightUpper">
                                        </div>
                                        <div class="price-wrap">
                                            <div class="price-container">
                                                <div class="price-wrap-1">
                                                    <input value="${param.minheight}" name="minheight" id="minHeight">
                                                </div>
                                                <div class="price-wrap_line">-</div>
                                                <div class="price-wrap-2">
                                                    <input value="${param.maxheight}" name="maxheight" id="maxHeight">
                                                    <label for="two">cm</label>
                                                </div>
                                            </div>
                                            <button type="submit" class="price-title">FILTER</button>
                                        </div>
                                    </fieldset>
                                </div>

                                <script>
                                    const heightLowerInput = document.getElementById('heightLower');
                                    const heightUpperInput = document.getElementById('heightUpper');
                                    const minHeightInput = document.getElementById('minHeight');
                                    const maxHeightInput = document.getElementById('maxHeight');

                                    // Function to update height input values and prevent crossing
                                    function updateHeightValues() {
                                        const heightLowerValue = parseInt(heightLowerInput.value);
                                        const heightUpperValue = parseInt(heightUpperInput.value);

                                        if (heightLowerValue >= heightUpperValue) {
                                            heightUpperInput.value = heightLowerValue + 1;
                                        }

                                        minHeightInput.value = heightLowerInput.value;
                                        maxHeightInput.value = heightUpperInput.value;
                                    }

                                    // Event listeners for height range input changes
                                    heightLowerInput.addEventListener('input', updateHeightValues);
                                    heightUpperInput.addEventListener('input', updateHeightValues);

                                    // Initial call to set default height values
                                    updateHeightValues();
                                </script>


                            </div>
                        </div>

                    </div>
                    <div class="col-lg-9 col-xl-9">
                        <div class="row row-pb-md">

                            <c:forEach items="${listP}" var="o">
                                <div class="col-md-4 ftco-animate">
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
                                            <h3><a
                                                    href="detail?pid=${o.productID}&cid=${o.cateID}">${o.productName}</a>
                                            </h3>
                                            <div class="d-flex">
                                                <div class="pricing">
                                                    <c:if test="${o.discount == 0}">
                                                        <p class="price"><span
                                                                class="price-sale">$${o.price}</span>
                                                        </p>
                                                    </c:if>
                                                    <c:if test="${o.discount != 0}">
                                                        <p class="price"><span
                                                                class="mr-2 price-dc">$${o.price}</span><span
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
                                                    </a>
                                                    <a href="buy?id=${o.productID}"
                                                       class="buy-now d-flex justify-content-center align-items-center mx-1">
                                                        <span><i class="ion-ios-cart"></i></span>
                                                    </a>
                                                    <a href="#"
                                                       class="heart d-flex justify-content-center align-items-center">
                                                        <span><i class="ion-ios-heart"></i></span>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>


                            <div class="col-md-12 text-center">
                                <div class="block-27">
                                    <ul>
                                        <c:if test="${tag > 1}">
                                            <li><a href="shop?index=${tag-1}"><i
                                                        class="ion-ios-arrow-back"></i></a></li>
                                                </c:if>

                                        <c:forEach begin="1" end="${endP}" var="i">
                                            <li class="${tag == i ? 'active' : ''}">
                                                <a href="shop?index=${i}">${i}</a>
                                            </li>
                                        </c:forEach>

                                        <c:if test="${tag < endP}">
                                            <li><a href="shop?index=${tag+1}"><i
                                                        class="ion-ios-arrow-forward"></i></a></li>
                                                </c:if>
                                    </ul>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="promatHidden" value="${promat}">
                <input type="hidden" name="protopicHidden" value="${protopic}">
            </form>
        </section>

        <section class="ftco-section ftco-no-pt ftco-no-pb py-5 bg-light">
            <div class="container py-4">
                <div class="row d-flex justify-content-center py-5">
                    <div class="col-md-6">
                        <h2 style="font-size: 22px;" class="mb-0">Subcribe to our Newsletter</h2>
                        <span>Get e-mail updates about our latest shops and special offers</span>
                    </div>
                    <div class="col-md-6 d-flex align-items-center">
                        <form action="#" class="subscribe-form">
                            <div class="form-group d-flex">
                                <input type="text" class="form-control"
                                       placeholder="Enter email address">
                                <input type="submit" value="Subscribe" class="submit px-3">
                            </div>
                        </form>
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
                };

                // For Price Filter
                var lowerSlider = document.querySelector('#lower');
                var upperSlider = document.querySelector('#upper');

                document.querySelector('#two').value = upperSlider.value;
                document.querySelector('#one').value = lowerSlider.value;

                var lowerVal = parseInt(lowerSlider.value);
                var upperVal = parseInt(upperSlider.value);

                upperSlider.oninput = function () {
                    lowerVal = parseInt(lowerSlider.value);
                    upperVal = parseInt(upperSlider.value);

                    if (upperVal < lowerVal + 4) {
                        lowerSlider.value = upperVal - 4;
                        if (lowerVal === lowerSlider.min) {
                            upperSlider.value = 4;
                        }
                    }
                    document.querySelector('#two').value = this.value;
                };

                lowerSlider.oninput = function () {
                    lowerVal = parseInt(lowerSlider.value);
                    upperVal = parseInt(upperSlider.value);
                    if (lowerVal > upperVal - 4) {
                        upperSlider.value = lowerVal + 4;
                        if (upperVal === upperSlider.max) {
                            lowerSlider.value = parseInt(upperSlider.max) - 4;
                        }
                    }
                    document.querySelector('#one').value = this.value;
                };


                // For Discount Filter

                var lowerSliderDiscount = document.querySelector('#lower-discount');
                var upperSliderDiscount = document.querySelector('#upper-discount');

                document.querySelector('#two-discount').value = upperSliderDiscount.value;
                document.querySelector('#one-discount').value = lowerSliderDiscount.value;

                var lowerValDiscount = parseInt(lowerSlider.value);
                var upperValDiscount = parseInt(upperSlider.value);

                upperSliderDiscount.oninput = function () {
                    lowerValDiscount = parseInt(lowerSlider.value);
                    upperValDiscount = parseInt(upperSlider.value);

                    if (upperValDiscount < lowerValDiscount + 4) {
                        lowerSliderDiscount.value = upperValDiscount - 4;
                        if (lowerValDiscount === lowerSliderDiscount.min) {
                            upperSliderDiscount.value = 4;
                        }
                    }
                    document.querySelector('#two-discount').value = this.value;
                };

                lowerSliderDiscount.oninput = function () {
                    lowerValDiscount = parseInt(lowerSliderDiscount.value);
                    upperValDiscount = parseInt(upperSliderDiscount.value);
                    if (lowerVal > upperVal - 4) {
                        upperSliderDiscount.value = lowerValDiscount + 4;
                        if (upperValDiscount === upperSliderDiscount.max) {
                            lowerSliderDiscount.value = parseInt(upperSliderDiscount.max) - 4;
                        }
                    }
                    document.querySelector('#one-discount').value = this.value;
                };


            </script>
            <script>
                // JavaScript for width range sliders
                var widthLowerSlider = document.querySelector('#widthLower');
                var widthUpperSlider = document.querySelector('#widthUpper');

                // JavaScript for height range sliders
                var heightLowerSlider = document.querySelector('#heightLower');
                var heightUpperSlider = document.querySelector('#heightUpper');

                // Initialize width inputs
                document.querySelector('#minWidth').value = widthLowerSlider.value;
                document.querySelector('#maxWidth').value = widthUpperSlider.value;

                // Initialize height inputs
                document.querySelector('#minHeight').value = heightLowerSlider.value;
                document.querySelector('#maxHeight').value = heightUpperSlider.value;

                // Add event listeners for width sliders
                widthLowerSlider.oninput = function () {
                    lowerVal = parseInt(lowerSlider.value);
                    upperVal = parseInt(upperSlider.value);
                    if (lowerVal > upperVal - 4) {
                        upperSlider.value = lowerVal + 4;
                        if (upperVal === upperSlider.max) {
                            lowerSlider.value = parseInt(upperSlider.max) - 4;
                        }
                    }
                    document.querySelector('#minWidth').value = this.value;
                };

                widthUpperSlider.oninput = function () {
                    lowerVal = parseInt(lowerSlider.value);
                    upperVal = parseInt(upperSlider.value);

                    if (upperVal < lowerVal + 4) {
                        lowerSlider.value = upperVal - 4;
                        if (lowerVal === lowerSlider.min) {
                            upperSlider.value = 4;
                        }
                    }
                    document.querySelector('#maxWidth').value = this.value;
                };

                // Add event listeners for height sliders
                heightLowerSlider.oninput = function () {
                    lowerVal = parseInt(lowerSlider.value);
                    upperVal = parseInt(upperSlider.value);
                    if (lowerVal > upperVal - 4) {
                        upperSlider.value = lowerVal + 4;
                        if (upperVal === upperSlider.max) {
                            lowerSlider.value = parseInt(upperSlider.max) - 4;
                        }
                    }
                    document.querySelector('#minHeight').value = this.value;
                };

                heightUpperSlider.oninput = function () {
                    lowerVal = parseInt(lowerSlider.value);
                    upperVal = parseInt(upperSlider.value);

                    if (upperVal < lowerVal + 4) {
                        lowerSlider.value = upperVal - 4;
                        if (lowerVal === lowerSlider.min) {
                            upperSlider.value = 4;
                        }
                    }
                    document.querySelector('#maxHeight').value = this.value;
                };
            </script>
            <%@include file="template/footer.jsp" %>