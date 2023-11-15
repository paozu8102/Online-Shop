<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>QPS</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/q-icon.png" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/stylesFogetPassword.css" rel="stylesheet" />
       <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
        <link rel="stylesheet" href="css/animate.css">

        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">

        <link rel="stylesheet" href="css/aos.css">

        <link rel="stylesheet" href="css/ionicons.min.css">

        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="css/jquery.timepicker.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/style.css">
</head>

<body>

    <section class="vh-100" style="background-color: #eee;">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-lg-12 col-xl-11">
                    <div class="card text-black" style="border-radius: 5px;">
                        <div class="card-body p-md-5">
                            <div class="row justify-content-center">
                                <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
                                    <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Change Password</p>
                                    <form id="passwordChangeForm" class="mx-1 mx-md-4" action="forgot-password?service=changePassword" method="post">
                                        <input type="text" name="email" value="${emailAddress}" hidden="hidden">
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label" for="form3Example1c">New Password</label>
                                                <input type="password" id="form3Example1c" class="form-control" required name="newPassword"/>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label" for="form3Example3c">Confirm Password</label>
                                                <input type="password" id="form3Example3c" class="form-control" required name="confirmPassword"/>
                                            </div>
                                        </div>
                                        <div class="d-block mx-4 mb-3 mb-lg-4 text-danger"><b>${mess}</b></div>
                                        <div class="d-block mx-4 mb-3 mb-lg-4 text-center">
                                            <button class="btn btn-primary btn-block px-5 w-100" type="button" onclick="validateAndSubmit()">Confirm</button>
                                        </div>

                                        <div class="text-center pt-2">
                                            <a href="home">Back to Home</a>
                                        </div>
                                    </form>
                                </div>
                                <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">
                                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                                         class="img-fluid" alt="Sample image">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <div class="modal fade" id="notificationModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Thông báo</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p id="notificationMessage"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>

    <<script>
    // Function to validate and submit the form
    function validateAndSubmit() {
        // Get the values of the new password and confirm password fields
        const newPassword = document.getElementById('form3Example1c').value;
        const confirmPassword = document.getElementById('form3Example3c').value;

        // Check if the password has at least 8 characters
        if (newPassword.length < 8) {
            // Display an error message in the modal
            displayNotification('Password must have at least 8 characters.');
            return;
        }
if (newPassword.trim() === "") {
    // Display an error message
    displayNotification('Password must not all be spaces.');
    return;
}
        // Check if the new password matches the confirm password
        if (newPassword !== confirmPassword) {
            // Display an error message in the modal
            displayNotification('Password and confirm password do not match.');
            return;
        }

        // If all checks pass, display a success message and reset the form data
        displayNotification('Password changed successfully!');
        resetForm();

        // Simulate sending an email (replace with actual email sending logic)
        sendEmail();
    }

    // Function to display a notification in a modal
    function displayNotification(message) {
        const notificationMessage = document.getElementById('notificationMessage');
        notificationMessage.textContent = message;
        $('#notificationModal').modal('show'); // Show the modal
    }

    // Function to reset the form data
    function resetForm() {
        // Reset the form fields to their initial values
        document.getElementById('form3Example1c').value = '';
        document.getElementById('form3Example3c').value = '';
    }

    // Function to simulate sending an email
    function sendEmail() {
        // Simulate email sending (replace with actual email sending logic)
        setTimeout(function() {
            alert('Password change successful!');

            // Redirect to the login page after a successful password change
            redirectToLogin();
        }, 3000); // Simulate a 3-second delay for email sending, replace with actual time
    }

    // Function to redirect to the login page
    function redirectToLogin() {
        // Redirect to the login page
        window.location.href = 'login.jsp';
    }
</script>

    <!-- jQuery và Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>
