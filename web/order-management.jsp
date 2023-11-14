<%-- 
    Document   : post-management
    Created on : Oct 5, 2023, 9:34:49 AM
    Author     : Mai Vu Bao
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords"
              content="wrappixel, admin dashboard, html css dashboard, web dashboard, bootstrap 5 admin, bootstrap 5, css3 dashboard, bootstrap 5 dashboard, Ample lite admin bootstrap 5 dashboard, frontend, responsive bootstrap 5 admin template, Ample admin lite dashboard bootstrap 5 dashboard template">
        <meta name="description"
              content="Ample Admin Lite is powerful and clean admin dashboard template, inpired from Bootstrap Framework">
        <meta name="robots" content="noindex,nofollow">
        <title>Manage Order</title>
        <link rel="canonical" href="https://www.wrappixel.com/templates/ample-admin-lite/" />
        <!-- Favicon icon -->
        <link rel="icon" type="image/png" sizes="16x16" href="plugins/images/favicon.png">
        <!-- Custom CSS -->
        <link href="css/style.min.css" rel="stylesheet">
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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
            .modal-header {
                background-color: #337ab7;
                color: #fff;
            }

            /* CSS cho các nhãn (labels) */
            label {
                font-weight: bold;
            }

            /* CSS cho nút "Cancel This Order" */
            #cancelButton {
                background-color: #d9534f;
            }

            /* CSS cho nút Close */
            .modal-footer .btn-default {
                background-color: #777;
            }

            /* CSS cho phần thông tin đơn hàng và sản phẩm */
            .order-details {
                display: flex;
                justify-content: space-between;
            }

            .left-column, .right-column {
                width: 48%;
            }

            /* CSS cho các thông tin đơn hàng và sản phẩm */
            .order-info, .product-info {
                margin-bottom: 10px;
            }


            #progressbar {
                margin-bottom: 30px;
                overflow: hidden;
                color: #455A64;
                padding-left: 0px;
                margin-top: 30px
            }

            #progressbar li {
                list-style-type: none;
                font-size: 13px;
                width: 33.33%;
                float: left;
                position: relative;
                font-weight: 400;
                color: #455A64 !important;

            }

            #progressbar #step1:before {
                content: "1";
                color: #fff;
                width: 29px;
                margin-left: 15px !important;
                padding-left: 11px !important;
            }


            #progressbar #step2:before {
                content: "2";
                color: #fff;
                width: 29px;

            }

            #progressbar #step3:before {
                content: "3";
                color: #fff;
                width: 29px;
                margin-right: 15px !important;
                padding-right: 11px !important;
            }

            #progressbar li:before {
                line-height: 29px;
                display: block;
                font-size: 12px;
                background: #455A64 ;
                border-radius: 50%;
                margin: auto;
            }

            #progressbar li:after {
                content: '';
                width: 121%;
                height: 2px;
                background: #455A64;
                position: absolute;
                left: 0%;
                right: 0%;
                top: 15px;
                z-index: -1;
            }

            #progressbar li:nth-child(2):after {
                left: 50%;
            }

            #progressbar li:nth-child(1):after {
                left: 25%;
                width: 121%;
            }
            #progressbar li:nth-child(3):after {
                left: 25% !important;
                width: 50% !important;
            }

            #progressbar li.active:before,
            #progressbar li.active:after {
                background: #4bb8a9;
            }

            .card {
                background-color: #fff;
                box-shadow: 2px 4px 10px 0px rgba(0, 108, 170, 0.5); /* Adjust the last value (alpha) to control opacity */
                z-index: 0;
            }

        </style>
        <title>Sale Dashboard</title>
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
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords"
              content="wrappixel, admin dashboard, html css dashboard, web dashboard, bootstrap 5 admin, bootstrap 5, css3 dashboard, bootstrap 5 dashboard, Ample lite admin bootstrap 5 dashboard, frontend, responsive bootstrap 5 admin template, Ample admin lite dashboard bootstrap 5 dashboard template">
        <meta name="description"
              content="Ample Admin Lite is powerful and clean admin dashboard template, inpired from Bootstrap Framework">
        <meta name="robots" content="noindex,nofollow">
        <link rel="canonical" href="https://www.wrappixel.com/templates/ample-admin-lite/" />
        <!-- Favicon icon -->
        <link rel="icon" type="image/png" sizes="16x16" href="plugins/images/favicon.png">
        <!-- Custom CSS -->
        <link href="css/style.min.css" rel="stylesheet">
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    </head>

    <body>
        <!-- ============================================================== -->
        <!-- Preloader - style you can find in spinners.css -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Main wrapper - style you can find in pages.scss -->
        <!-- ============================================================== -->
        <div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5" data-sidebartype="full"
             data-sidebar-position="absolute" data-header-position="absolute" data-boxed-layout="full">
            <!-- ============================================================== -->
            <!-- Topbar header - style you can find in pages.scss -->
            <!-- ============================================================== -->
            <header class="topbar" data-navbarbg="skin5">
                <nav class="navbar top-navbar navbar-expand-md navbar-dark" style="background-color: #82ae46;">
                    <div class="navbar-header" data-logobg="skin6">
                        <!-- ============================================================== -->
                        <!-- Logo -->
                        <!-- ============================================================== -->
                        <a class="navbar-brand" href="statistic" style="text-decoration: none; font-size: 20px; font-weight: bold; color: #333; font-family: Arial, sans-serif;">Saler Dashboard</a>
                        <!-- ============================================================== -->
                        <!-- End Logo -->
                        <!-- ============================================================== -->
                        <!-- ============================================================== -->
                        <!-- toggle and nav items -->
                        <!-- ============================================================== -->
                        <a class="nav-toggler waves-effect waves-light text-dark d-block d-md-none"
                           href="javascript:vdid(0)"><i class="ti-menu ti-close"></i></a>
                    </div>
                    <!-- ============================================================== -->
                    <!-- End Logo -->
                    <!-- ============================================================== -->
                    <div class="navbar-collapse collapse" id="navbarSupportedContent" data-navbarbg="skin5">
                        <ul class="navbar-nav d-none d-md-block d-lg-none">
                            <li class="nav-item">
                                <a class="nav-toggler nav-link waves-effect waves-light text-white"
                                   href="javascript:vdid(0)"><i class="ti-menu ti-close"></i></a>
                            </li>
                        </ul>
                        <!-- ============================================================== -->
                        <!-- Right side toggle and nav items -->
                        <!-- ============================================================== -->

                    </div>
                </nav>
            </header>
            <!-- ============================================================== -->
            <!-- End Topbar header -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Left Sidebar - style you can find in sidebar.scss  -->
            <!-- ============================================================== -->
            <aside class="left-sidebar" data-sidebarbg="skin6">
                <!-- Sidebar scroll-->
                <div class="scroll-sidebar">
                    <!-- Sidebar navigation-->
                    <nav class="sidebar-nav">
                        <ul id="sidebarnav">
                            <!-- User Profile-->
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="statistic" aria-expanded="false">
                                    <i class="fa fa-chart-bar" aria-hidden="true"></i>
                                    <span class="hide-menu">Statistic</span>
                                </a>
                            </li>

                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="salerprofile"
                                   aria-expanded="false">
                                    <i class="fa fa-user" aria-hidden="true"></i>
                                    <span class="hide-menu">Profile</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="manage"
                                   aria-expanded="false">
                                    <i class="fa fa-table" aria-hidden="true"></i>
                                    <span class="hide-menu">Product Management</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="manageorder" aria-expanded="false">
                                    <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                    <span class="hide-menu">Order Management</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="customercontrol" aria-expanded="false">
                                    <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                    <span class="hide-menu">Customer Management</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="javascript:history.back()" aria-expanded="false">
                                    <i class="fa fa-arrow-left" aria-hidden="true"></i>
                                    <span class="hide-menu">Return</span>
                                </a>
                            </li>                
                        </ul>

                    </nav>
                    <!-- End Sidebar navigation -->
                </div>
                <!-- End Sidebar scroll-->
            </aside>
            <!-- ============================================================== -->
            <!-- End Left Sidebar - style you can find in sidebar.scss  -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Page wrapper  -->
            <!-- ============================================================== -->
            <div class="page-wrapper">
                <!-- ============================================================== -->
                <!-- Bread crumb and right sidebar toggle -->
                <div style="padding: 25px 25px 0 25px">
                    <!-- ============================================================== -->
                    <!-- Start Page Content -->
                    <!-- ============================================================== -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="white-box">
                                <h4 style="color: #82ae46">
                                    <i class="fa fa-filter"></i>
                                    Filter
                                </h4>
                                <div style="display: inline-block; margin-left: 10px">
                                    <form action="manageorder" method="get" class="search-form">
                                        <div class="search-container">
                                            <input name="txt" type="text"  placeholder="Search" >
                                            <button type="submit"><i class="icon-search" style="color: white;"></i></button>
                                        </div>
                                    </form>
                                </div>
                                <div>
                                    <form action="manageorder" onsubmit="return validateDate()">
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
 </form>
                                            <tr>
                                                <td> 
                                                    <form action="manageorder" method="get">
                                                        <select name="sortprice" id="sortprice" style="color: black;">
                                                            <option value="" style="color: black;">Sort By Price</option>
                                                            <option value="asc" style="color: black;"
                                                                    ${param.sortprice == 'asc' ? 'selected' : ''}>Low to High</option>
                                                            <option value="desc" style="color: black;"
                                                                    ${param.sortprice == 'desc' ? 'selected' : ''}>High to Low</option>
                                                        </select>

                                                    </form>
                                                </td>
                                            </tr>
                                        </table>
                                   
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <!-- Container fluid  -->
                <!-- ============================================================== -->
                <div class="container-fluid">
                    <!-- ============================================================== -->
                    <!-- Start Page Content -->
                    <!-- ============================================================== -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="white-box">
                                <h3 class="box-title">Order Table</h3>
                                <div class="table-responsive">
                                    <table class="table text-nowrap">
                                        <thead>
                                            <tr>
                                                <th class="border-top-0">OrderID</th>
                                                <th class="border-top-0">Product Name</th>
                                                <th class="border-top-0">Customer Name</th>
                                                <th class="border-top-0">Date of Order</th>
                                                <th class="border-top-0">Expense</th>
                                                <th class="border-top-0">Quantity</th>
                                                <th class="border-top-0">Status</th>
                                                <th class="border-top-0">Action</th>                                                 
                                            </tr
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${listO}" var="o">
                                                <tr>
                                                    <td>${o.did}</td>
                                                    <td>${o.productname}</td>
                                                    <td>${o.customername}</td>
                                                    <td>${o.date}</td>
                                                    <td>${o.price}$</td>
                                                    <td>${o.quantity}</td>
                                                    <td class="price" style="color:
                                                        <c:choose>
                                                            <c:when test="${o.status == 'pending'}">
                                                                #3399CC /* Set text color to yellow for 'pending' status */
                                                            </c:when>
                                                            <c:when test="${o.status == 'cancel'}">
                                                                #FF3399 /* Set text color to pink for 'cancel' status */
                                                            </c:when>
                                                            <c:when test="${o.status == 'completed'}">
                                                                #00FF00 /* Set text color to green for 'completed' status */
                                                            </c:when>
                                                            <c:otherwise>
                                                                black /* Set a default text color (e.g., black) for other statuses */
                                                            </c:otherwise>
                                                        </c:choose>
                                                        ;">
                                                        ${o.status}
                                                    </td>

                                                    <td class="total">
                                                      
                                                        <a href="#" data-toggle="modal" data-target="#editOrder" 
                                                           data-productname="${o.productname}" 
                                                           data-productimage="${o.productimage}" 
                                                           data-quantity="${o.quantity}" 
                                                           data-price="${o.price}" 
                                                           data-date="${o.date}" 
                                                           data-sellname="${o.sellname}" 
                                                           data-customername="${o.customername}" 
                                                           data-address="${o.address}" 
                                                           data-phonenumber="${o.phonenumber}" 
                                                           data-status="${o.status}"
                                                           data-did="${o.did}"
                                                           data-payment="${o.payment}"
                                                           data-expdate="${o.expDate}"
                                                           data-deldate="${o.delDate}"
                                                           >



                                                            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRiDm6Azm2GrmLl2y8FntgjrLtEnZ9oiacF2A&usqp=CAU"
                                                                 alt="image" width="20" height="20">
                                                        </a>
                                                    </td>

                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col text-center">
                        <div class="block-27">
                            <ul>
                                <c:if test="${tag > 1}">
                                    <li><a href="manageorder?index=${tag-1}"><i class="ion-ios-arrow-back"></i></a></li>
                                        </c:if>

                                <c:forEach begin="1" end="${endP}" var="i">
                                    <li class="${tag == i ? 'active' : ''}">
                                        <a href="manageorder?index=${i}">${i}</a>
                                    </li>
                                </c:forEach>

                                <c:if test="${tag < endP}">
                                    <li><a href="manageorder?index=${tag+1}"><i class="ion-ios-arrow-forward"></i></a></li>
                                        </c:if>
                            </ul>

                        </div>
                    </div>


                    <!-- ============================================================== -->
                    <!-- End PAge Content -->
                    <!-- ============================================================== -->
                    <!-- ============================================================== -->
                    <!-- Right sidebar -->
                    <!-- ============================================================== -->
                    <!-- .right-sidebar -->
                    <!-- ============================================================== -->
                    <!-- End Right sidebar -->
                    <!-- ============================================================== -->
                    <div id="editOrder" class="modal fade" tabindex="-1">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <form action="orderstatus" method="post">
                                    <div class="modal-header">
                                        <h4 class="modal-title">Order Detail</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="order-details">
                                            <div class="left-column">
                                                <input type="hidden" name="did" >
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

                                        <button type="submit" class="btn btn-primary" id="processingButton" name="action" value="processing" onclick="return confirmprocessation();">Processing Order</button>
                                        <button type="submit" class="btn btn-primary" id="completeButton" name="action" value="completed" onclick="return confirmcompleteation();">Completed Order</button>
                                        <button type="submit" class="btn btn-primary" id="cancelButton" name="action" value="cancel" onclick="return confirmCancellation();">Cancel This Order</button>
                                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Close">

                                        <div id="cancellationMessage" style="color: red;"></div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <script>

                $('a[data-target="#editOrder"]').click(function () {
                 
                    var expdate = $(this).data('expdate') || "Unknown";
                    var deldate = $(this).data('deldate') || "Unknown";

                   
                    $('#editOrder p[name="expDate"]').text(expdate);
                    $('#editOrder p[name="delDate"]').text(deldate);


                  
                });
                    </script>

           
                    <script>
                            // When the modal is shown, add the 'modal-open' class to the body
                            $('#editOrder').on('show.bs.modal', function () {
                                $('body').addClass('modal-open');
                            });

                            // When the modal is hidden, remove the 'modal-open' class from the body
                            $('#editOrder').on('hidden.bs.modal', function () {
                                $('body').removeClass('modal-open');
                            }
                            );

                    </script>
<script>
    // Define variables for buttons outside of the click event


    $('a[data-target="#editOrder"]').click(function () {
        // Clear button references when opening a new modal
     

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
        var did = $(this).data('did');
        var expdate = $(this).data('expdate') || "Unknown";
        var deldate = $(this).data('deldate') || "Unknown";

        var editOrderModal = $('#editOrder');
        editOrderModal.find('#cancellationMessage').hide();
processingButton = editOrderModal.find("#processingButton");
            cancelButton = editOrderModal.find("#cancelButton");
            completeButton = editOrderModal.find("#completeButton");
            processingButton.show();
            cancelButton.show();
            completeButton.show();
        editOrderModal.find('p[name="productname"]').text(productname);
        editOrderModal.find('p[name="quantity"]').text(quantity);
        editOrderModal.find('p[name="price"]').text(price);
        editOrderModal.find('p[name="date"]').text(date);
        editOrderModal.find('p[name="sellname"]').text(sellname);
        editOrderModal.find('p[name="customername"]').text(customername);
        editOrderModal.find('p[name="address"]').text(address);
        editOrderModal.find('p[name="phonenumber"]').text(phonenumber);
        editOrderModal.find('p[name="status"]').text(status);
        editOrderModal.find('input[name="did"]').val(did);
        editOrderModal.find('p[name="payment"]').text(payment);
        editOrderModal.find('p[name="expDate"]').text(expdate);
        editOrderModal.find('p[name="delDate"]').text(deldate);

        // Get the progress bar steps
        var step1 = editOrderModal.find("#step1");
        var step2 = editOrderModal.find("#step2");
        var step3 = editOrderModal.find("#step3");

        // Reset the classes
        step1.attr('class', "step0");
        step2.attr('class', "step0 text-center");
        step3.attr('class', "step0 text-muted text-right");

        // Set the active class based on the status
        if (status === "pending") {
            step1.attr('class', "step0 active");
            completeButton.hide();
        } else if (status === "processing") {
            step1.attr('class', "step0 active");
            step2.attr('class', "step0 active text-center");
            processingButton.hide();
        } else if (status === "completed") {
            step1.attr('class', "step0 active");
            step2.attr('class', "step0 active text-center");
            step3.attr('class', "step0 active text-muted text-right");
            processingButton.hide();
            cancelButton.hide();
            completeButton.hide();
        } else if (status === "cancel") {
            editOrderModal.find('#cancellationMessage').text("This Order is canceled").show();
            processingButton.hide();
            completeButton.hide();
            cancelButton.hide();
        }
      
    });

      function confirmCancellation() {
        var confirmation = confirm("Are you sure to cancel this order?");
        return confirmation;
    }
    
     function confirmprocessation() {
        var confirmation = confirm("Are you sure you want to process this order?");
        return confirmation;
    }
    
       function confirmcompleteation() {
        var confirmation = confirm("Are you sure you want to mark this order as completed?");
        return confirmation;
    }
</script>




                    <script>

                            document.getElementById("sortprice").onchange = function () {
                                this.form.submit();
                            };

                            document.getElementById("sortname").onchange = function () {
                                this.form.submit();
                            }
                            ;
                            document.getElementById("pending").onchange = function () {
                                this.form.submit();
                            }
                            ;
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

                </div>
                <!-- ============================================================== -->
                <!-- End Container fluid  -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- footer -->
                <!-- ============================================================== -->
                <footer class="footer text-center"> 2023 © Palette Joy Bring Art To You
                </footer>
                <!-- ============================================================== -->
                <!-- End footer -->
                <!-- ============================================================== -->
            </div>
            <!-- ============================================================== -->
            <!-- End Page wrapper  -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- End Wrapper -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- All Jquery -->
        <!-- ============================================================== -->

        <script src="plugins/bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap tether Core JavaScript -->
        <script src="bootstrap/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/app-style-switcher.js"></script>
        <!--Wave Effects -->
        <script src="js/waves.js"></script>
        <!--Menu sidebar -->
        <script src="js/sidebarmenu.js"></script>
        <!--Custom JavaScript -->
        <script src="js/custom.js"></script>
    </body>

</html>
