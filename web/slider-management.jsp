<%-- 
    Document   : slider-management
    Created on : Oct 5, 2023, 9:34:14 AM
    Author     : Mai Vu Bao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords"
              content="wrappixel, admin dashboard, html css dashboard, web dashboard, bootstrap 5 admin, bootstrap 5, css3 dashboard, bootstrap 5 dashboard, Ample lite admin bootstrap 5 dashboard, frontend, responsive bootstrap 5 admin template, Ample admin lite dashboard bootstrap 5 dashboard template">
        <meta name="description"
              content="Ample Admin Lite is powerful and clean admin dashboard template, inpired from Bootstrap Framework">
        <meta name="robots" content="noindex,nofollow">
        <title>Admin DashBoard</title>
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
        <style>
            .url::-webkit-scrollbar {
                display: none;
            }
            
            .active {
                border-bottom: 1px solid #00aff0;
            }
            
            .pre-next:hover {
                cursor: pointer;
                color: #0d6efd;
            }
            
            .pre-next {
                color:#2cabe3 
            }
            
            i:hover {
                cursor: pointer
            }
        </style>
    </head>

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
                <nav class="navbar top-navbar navbar-expand-md navbar-dark">
                    <div class="navbar-header" data-logobg="skin6">
                        <!-- ============================================================== -->
                        <!-- Logo -->
                        <!-- ============================================================== -->
                        <a class="navbar-brand" href="admin" style="text-decoration: none; font-size: 24px; font-weight: bold; color: #333; font-family: Arial, sans-serif;">Admin Dashboard</a>
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
                        <ul class="navbar-nav ms-auto d-flex align-items-center">

                            <!-- ============================================================== -->
                            <!-- Search -->
                            <!-- ============================================================== -->
                            <li class=" in">

                            </li>
                            <!-- ============================================================== -->
                            <!-- User profile and search -->
                            <!-- ============================================================== -->
                            <li>
                                <a class="profile-pic" href="admin-profile.jsp">
                                    <img src="${sessionScope.user.getAvatar()}" alt="user-img" width="36"
                                         class="img-circle"></a>
                                <a href="logout" style="padding-right: 20px"><span class="text-white font-medium">Sign Out</span></a>
                            </li>
                            <!-- ============================================================== -->
                            <!-- User profile and search -->
                            <!-- ============================================================== -->
                        </ul>
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
                            <li class="sidebar-item pt-2">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="admin"
                                   aria-expanded="false">
                                    <i class="far fa-clock" aria-hidden="true"></i>
                                    <span class="hide-menu">Statistic</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="admin-profile.jsp"
                                   aria-expanded="false">
                                    <i class="fa fa-user" aria-hidden="true"></i>
                                    <span class="hide-menu">Profile</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="manageslide"
                                   aria-expanded="false">
                                    <i class="fa fa-table" aria-hidden="true"></i>
                                    <span class="hide-menu">Slider Management</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="managepost"
                                   aria-expanded="false">
                                    <i class="fa fa-table" aria-hidden="true"></i>
                                    <span class="hide-menu">Post Management</span>
                                </a>
                            </li>   
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="usercontrol"
                                   aria-expanded="false">
                                    <i class="fa fa-table" aria-hidden="true"></i>
                                    <span class="hide-menu">User Management</span>
                                </a>
                            </li>                           
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="settingcontrol"
                                   aria-expanded="false">
                                    <i class="fa fa-table" aria-hidden="true"></i>
                                    <span class="hide-menu">Setting Management</span>
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
                            <h4 class="page-title">Slider Management</h4>
                        </div>
                        <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                            <ul class="navbar-nav ms-auto d-flex align-items-center">
                                <li class="in" style="width: 50%; text-align: right">
                                    <form id="search" action="manageslide" role="search" class="app-search d-none d-md-block me-3" style="width: 100%">
                                        <input type="text" placeholder="Search..." class="form-control mt-0" name="searchID" style="border-radius: 20px" value="${searchID}">
                                        <i style="position: absolute; top: 2.5%; right: 21%" onclick="submit()" class="fa fa-search"></i>
                                    </form>
                                </li>
                            </ul>

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
                                <div style="display: flex; justify-content: space-between; align-items: center">
                                    <h3 class="box-title"></h3>
                                    <a href="crudslider?mode=add" style="border: 1px solid; padding: 0.5rem 1rem; border-radius: 20px"> 
                                        Add new
                                    </a>
                                </div>
                                <div class="table-responsive">
                                    <table class="table text-nowrap">
                                        <thead>
                                            <tr>
                                                <th class="border-top-0">ImageID</th>
                                                <th class="border-top-0">ImageURL</th>
                                                <th colspan="2" class="border-top-0" style="text-align: center">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${imageList}" var="i">
                                                <tr>
                                                    <td>${i.getImageID()}</td>
                                                    <td class="url" style="max-width: 500px; overflow: auto">${i.getImageUrl()}</td>
                                                    <td style="text-align: center; display: flex; justify-content: center; align-items: center">
                                                         <a href="javascript:void(0);" onclick="confirmDelete(${i.getImageID()})">Delete</a>
                                                        <a href="crudslider?id=${i.getImageID()}&mode=update" style="margin-left: 20px">View Details</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div>
                                        <ul style="display: flex; list-style: none; width: 30%; margin: auto;">
                                            <c:if test="${endPage != 0}">
                                                <li style="margin-right: 20px"><a href="manageslide?index=${index > 1 ? index - 1 : 1}&searchID=${searchID}">Previous</a></li>
                                            </c:if>
                                            
                                            <c:forEach begin="1" end="${endPage}" var="page">
                                                <li style="margin-right: 20px" ${page == index ? 'class="active"' : ''}>
                                                    <a href="manageslide?index=${page}&searchID=${searchID}">${page}</a>
                                                </li>
                                            </c:forEach>
                                                <c:if test="${endPage != 0}">
                                                <li style="margin-right: 20px"><a href="manageslide?index=${index < endPage ? index + 1 : index}&searchID=${searchID}">Next</a></li>
                                            </c:if>
                                        </ul>
                                    </div>
                                </div>
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

        <script>
            const curPage = document.getElementsByClassName('active')[0].innerHTML;
            
            function changePage(action) {
                if(curPage === 1 && action === -1) curPage++;
                if(curPage === ${endPage} && action === 1) curPage--;
                window.location.href = './manageslide?index=' + (curPage + action);
            }
            
            function submit() {
                document.getElementById('search').submit();
            }
            function confirmDelete(imageID) {
        var result = confirm("Are you sure you want to delete this slider?");
        if (result) {
            window.location.href = "crudslider?id=" + imageID + "&mode=delete";
        }
    }
        </script>
    </body>

</html>
