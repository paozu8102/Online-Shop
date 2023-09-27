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
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/@emailjs/browser@3/dist/email.min.js"></script>
    <script type="text/javascript">
        (function () {
            emailjs.init("EmLaRi-DiE1Jm-sxY");
        })();
    </script>
    <script>
        let param = {
            to: '${emailAddress}',
            link: 'http://localhost:${port}/SWP391_SE1729_Group3/forgot-password?service=changePassword&&email=${emailAddress}&&createTime=${milis}'
        }

        emailjs.send('service_ufy9vus', 'template_xte91rb', param)
    </script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            max-width: 400px;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 10px 20px rgba(0,0,0,0.1);
            background-color: whitesmoke;
            text-align: center;
            position: relative;
            z-index: 1; /* ??m b?o form n?m phía trên ?nh */
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 10px;
            color: #555;
        }
        input[type="text"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 20px;
            box-sizing: border-box;
            border: 3px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        input[type="submit"] {
            padding: 15px 30px;
            background-color: #3498db;
            color: #fff;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            font-size: 18px;
            transition: background-color 0.3s ease;
            margin-right: 10px; /* Kho?ng cách gi?a nút "Send Email" và "Cancel" */
        }
        input[type="submit"]:hover {
            background-color: #2980b9;
        }
        .cancel-button {
            padding: 15px 30px;
            background-color: #ccc;
            color: #fff;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            font-size: 18px;
            transition: background-color 0.3s ease;
        }
        .cancel-button:hover {
            background-color: #999;
        }
        .image-container {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 0; /* ?nh n?m phía sau form */
        }
        .image-container img {
            width: 100%;
            height: 100%;
            object-fit: cover; /* ??m b?o hình ?nh không b? méo */
        }
            .loader {
        border: 5px solid #f3f3f3;
        border-top: 5px solid #3498db;
        border-radius: 50%;
        width: 50px;
        height: 50px;
        animation: spin 2s linear infinite;
        display: none;
    }

    @keyframes spin {
        0% { transform: rotate(0deg); }
        100% { transform: rotate(360deg); }
    }
    </style>
</head>

<body>
    <div class="image-container">
        <img src="images/forgot2.jpg" alt="Image Description">
    </div>
    <div class="container">
        <h2>Forgot Password</h2>
        <form method="post" action="forgot-password?service=submitEmail">
            <label for="email">Enter your email:</label>
            <b>${mess}</b>
            <input type="text" id="email" required="" name="email" value="${emailAddress}"><br><br>
            <input type="submit" value="Send Email" id="sendEmailButton">
            <a href="login.jsp" class="cancel-button">Cancel</a>
        </form>
    </div>

  <script>
    // Check if a success message should be displayed
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const successMessage = urlParams.get('success');

    if (successMessage === 'true') {
        // Create a success notification element
        const successNotification = document.createElement('div');
        successNotification.textContent = 'Send Mail Successfully! Check your mail!!!!';
        successNotification.classList.add('success-notification');

        // Find the container to append the success notification
        const container = document.querySelector('.container');
        container.appendChild(successNotification);
        
        // Automatically remove the success message after a few seconds (e.g., 5 seconds)
        setTimeout(function() {
            container.removeChild(successNotification);
        }, 5000);
    }
</script>

<script>
    // Display a loading spinner when sending an email
    document.getElementById('sendEmailButton').addEventListener('click', function() {
        // Create a loader element
        const loader = document.createElement('div');
        loader.className = 'loader';

        // Append the loader to the document body
        document.body.appendChild(loader);

        // Simulate email sending (replace with actual email sending logic)
        simulateEmailSending();
    });

    // Function to simulate sending an email
    function simulateEmailSending() {
        // Simulate email sending (replace with actual email sending logic)
        setTimeout(function() {
            // Remove the loader after email sending is complete
            const loader = document.querySelector('.loader');
            if (loader) {
                loader.remove();
            }
            
            // Display a success message
            displayNotification('Email sent successfully!');

            // Redirect to a confirmation page or perform other actions
            redirectToConfirmation();
        }, 3000); // Simulate a 3-second delay for email sending, replace with actual time
    }

    // Function to display a notification in a modal
    function displayNotification(message) {
        const notificationMessage = document.getElementById('notificationMessage');
        notificationMessage.textContent = message;
        $('#notificationModal').modal('show'); // Show the modal
    }

    // Function to redirect to a confirmation page
    function redirectToConfirmation() {
        // Redirect to the confirmation page
        window.location.href = '#';
    }
</script>

</body>

</html>
