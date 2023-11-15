
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

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
          .url::-webkit-scrollbar {
                display: none;
            }

            .active {
                border-bottom: 1px solid #00aff0;
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
        <title>Manage Product</title>
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
    </head>
 <script src="js/jquery.min.js"></script>
    <body>
        <!-- ============================================================== -->
        <!-- Preloader - style you can find in spinners.css -->
        <!-- ============================================================== -->
        <div class="preloader">
            <div class="lds-ripple">
                <div class="lds-pos"></div>
                <div class="lds-pos"></div>
            </div>
        </div>
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
                        <a class="navbar-brand" href="statistic" style="text-decoration: none; font-size: 19px; font-weight: bold; color: #333; font-family: Arial, sans-serif;">Saler Dashboard</a>
                        <!-- ============================================================== -->
                        <!-- End Logo -->
                        <!-- ============================================================== -->
                        <!-- ============================================================== -->
                        <!-- toggle and nav items -->
                        <!-- ============================================================== -->
                        <a class="nav-toggler waves-effect waves-light text-dark d-block d-md-none"
                           href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
                    </div>
                    <!-- ============================================================== -->
                    <!-- End Logo -->
                    <!-- ============================================================== -->
                    <div class="navbar-collapse collapse" id="navbarSupportedContent" data-navbarbg="skin5">
                        <ul class="navbar-nav d-none d-md-block d-lg-none">
                            <li class="nav-item">
                                <a class="nav-toggler nav-link waves-effect waves-light text-white"
                                   href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
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
                                    <i class="fa fa-table"" aria-hidden="true"></i>
                                    <span class="hide-menu">Customer Management</span>
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
                <!-- ============================================================== -->
                <div class="page-breadcrumb bg-white">
                    <div class="row align-items-center">
                        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                            <h4 class="page-title">Customer Management</h4>
                        </div>
                        <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        </div>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- ============================================================== -->
                <!-- End Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- Container fluid  -->
                <!-- ============================================================== -->
                <div class="container-fluid">
                    <!-- ============================================================== -->
                    <!-- Start Page Content -->
                    <!-- ============================================================== -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="white-box">
                            
                                <ul class="product-category">


            


                                  

                                   <div>
                                               <form action="customercontrol" method="get" style="display: inline-block; margin-right: 10px;">        
<select  id="city" name="city" aria-label=".form-select-sm" style="color: #82ae46;">
<option value="" selected>Select City</option>           
</select>
          </form>


    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
    <script>
	var citis = document.getElementById("city");

var Parameter = {
  url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json", 
  method: "GET", 
  responseType: "application/json", 
};
var promise = axios(Parameter);
promise.then(function (result) {
  renderCity(result.data);
});

function renderCity(data) {
    for (const x of data) {
      var cityName = x.Name;

      // Remove "Tỉnh" and "Thành phố" from the city name
      cityName = cityName.replace("Tỉnh", "").replace("Thành phố", "").trim();

      citis.options[citis.options.length] = new Option(cityName, cityName);
    }

 
  }
</script>
                                 

<form action="customercontrol" method="get" style="display: inline-block; margin-right: 10px;">
    <select name="userstatus" id="user-status" style="color: #82ae46;">
        <option value="" style="color: #82ae46;">Select Statuses</option>
        <option value="all" style="color: #82ae46;" ${param.userstatus == 'all' ? 'selected' : ''}>All Statuses</option>
        <option value="active" style="color: #82ae46;" ${param.userstatus == 'active' ? 'selected' : ''}>Active</option>
        <option value="blocked" style="color: #82ae46;" ${param.userstatus == 'blocked' ? 'selected' : ''}>Blocked</option>
    </select>
</form>

  <form action="customercontrol" method="get" style="display: inline-block; margin-right: 10px;">
    <select name="sortname" id="sortname" style="color: #82ae46;">
      <option value="" style="color: #82ae46;">Sort By Name</option>
      <option value="asc" style="color: #82ae46;" ${param.sortname == 'asc' ? 'selected' : ''}>A -> Z</option>
      <option value="desc" style="color: #82ae46;" ${param.sortname == 'desc' ? 'selected' : ''}>Z -> A</option>
    </select>
  </form>

  <div style="display: inline-block; margin-right: 10px;">
    <form action="searchcustomer" method="post" class="search-form">
      <div class="search-container">
        <input name="txt" type="text" placeholder="Search">
        <button type="submit"><i class="icon-search" style="color: white;"></i></button>
      </div>
    </form>
  </div>

      <p style="margin-top: 20px; margin-left: 20px;">Total Customer: ${total}</p>

                                </ul>
                                <div class="table-responsive">
                                    <table class="table text-nowrap">
                                        <thead>
                                              <tr>
                                            <th style="width: 100px;">ID</th>
                                            <th style="width: 300px;">Name</th>
                                            <th style="width: 300px;">Email</th>
                                            <th style="width: 300px;">Mobile</th>
                                            <th style="width: 300px;">Status</th>
                                            <th style="width: 300px;">Action</th>

                                        </tr>
                                        </thead>

                                        <tbody>
                                                 <c:forEach items="${listU}" var="o">
                                       
                                            <tr class="text-center">
                                          
                                            <td class="price"><span>${o.userID}</span></td>
                                            <td  class="price">${o.userName}</td>
                                            <td class="price">${o.email}</td>
                                            <td class="price">${o.phoneNumber}</td>
                                            <td  class="price" style="color: ${o.status == 1 ? 'green' : 'red'}">${o.status == 1 ? 'Active' : 'Blocked'}</td>
                                            <td class="total">
                                                <a href="customer?cid=${o.userID}" >
                                                    <img src="https://cdn-icons-png.flaticon.com/512/709/709612.png"
                                                         alt="Mô tả ảnh" width="20" height="20">
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
                <div class="row mt-5">
                                <div class="col text-center">
                                    <div class="block-27">
                                        <ul>
                                            <c:if test="${tag > 1}">
                                                <li><a href="customercontrol?index=${tag-1}"><i class="ion-ios-arrow-back"></i></a></li>
                                                    </c:if>

                                            <c:forEach begin="1" end="${endP}" var="i">
                                                <li class="${tag == i ? 'active' : ''}">
                                                    <a href="customercontrol?index=${i}">${i}</a>
                                                </li>
                                            </c:forEach>

                                            <c:if test="${tag < endP}">
                                                <li><a href="customercontrol?index=${tag+1}"><i class="ion-ios-arrow-forward"></i></a></li>
                                                    </c:if>
                                        </ul>
                                    </div>
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
        
        

            <script>
                var emailExists = <%= session.getAttribute("emailExists") %>;

                if (emailExists === true) {
                    alert("Email already exists.");

                }
            </script>
 

        
           <script>
 document.getElementById("city").onchange = function () {
                    this.form.submit();
                }
                ;

                document.getElementById("user-status").onchange = function () {
                    this.form.submit();
                };
            </script>
        
        
        <script>
                document.getElementById("sortname").onchange = function () {
                    this.form.submit();
                }
                ;
        </script>
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
  
            <script src="js/jquery-migrate-3.0.1.min.js"></script>
            <script src="js/popper.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/jquery.easing.1.3.js"></script>
            <script src="js/jquery.waypoints.min.js"></script>
            <script src="js/jquery.stellar.min.js"></script>
            <script src="js/owl.carousel.min.js"></script>
            <script src="js/jquery.magnific-popup.min.js"></script>
            <script src="js/aos.js"></script>
            <script src="js/jquery.animateNumber.min.js"></script>
            <script src="js/bootstrap-datepicker.js"></script>
            <script src="js/scrollax.min.js"></script>
            <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
            <script src="js/google-map.js"></script>
            <script src="js/main.js"></script>
    </body>
</html>
