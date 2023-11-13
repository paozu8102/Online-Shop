<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Post creating</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap">
  </head>
  <body>
<style>
/* Import Google Font - Poppins */
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap');
*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
}
body{
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background: linear-gradient(#00C6FF, #0078FF);
}
::selection{
  color: #fff;
  background: #1a81ff;
}
.container{
  width: 28%;
  height: 500px;
  overflow: hidden;
  background: #fff;
  border-radius: 10px;
  transition: height 0.2s ease;
  box-shadow: 0 12px 28px rgba(0, 0, 0, 0.12);
}
.container.active{
  height: 590px;
}
.container .wrapper{
  width: 1000px;
  display: flex;
}
.container .wrapper section{
  width: 500px;
  background: #fff;
}
.container img{
  cursor: pointer;
}
.container .post{
  transition: margin-left 0.18s ease;
}
.container.active .post{
  margin-left: -500px;
}
.post header{
  font-size: 22px;
  font-weight: 600;
  padding: 17px 0;
  text-align: center;
  border-bottom: 1px solid #bfbfbf;
}
.post form{
  margin: 20px 25px;
}
.post form .content,
.audience .list li .column{
  display: flex;
  align-items: center;
}
.post form .content img{
  cursor: default;
  max-width: 52px;
}
.post form .content .details{
  margin: -3px 0 0 12px;
}
form .content .details p{
  font-size: 17px;
  font-weight: 500;
}
.content .details .privacy{
  display: flex;
  height: 25px;
  cursor: pointer;
  padding: 0 10px;
  font-size: 11px;
  margin-top: 3px;
  border-radius: 5px;
  align-items: center;
  max-width: 98px;
  background: #E4E6EB;
  justify-content: space-between;
}
.details .privacy span{
  font-size: 13px;
  margin-top: 1px;
  font-weight: 500;
}
.details .privacy i:last-child{
  font-size: 13px;
  margin-left: 1px;
}
form :where(textarea, button){
  width: 100%;
  outline: none;
  border: none;
}
form textarea{
  resize: none;
  font-size: 18px;
  margin-top: 30px;
  min-height: 100px;
}
form textarea::placeholder{
  color: #858585;
}
form textarea:focus::placeholder{
  color: #b3b3b3;
}
form :where(.theme-emoji, .options){
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.theme-emoji img:last-child{
  max-width: 24px;
}
form .options{
  height: 57px;
  margin: 15px 0;
  padding: 0 15px;
  border-radius: 7px;
  border: 1px solid #B9B9B9;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
}
form .options :where(.list, li),
.audience :where(.arrow-back, .icon, li .radio){
  display: flex;
  align-items: center;
  justify-content: center;
}
form .options p{
  color: #595959;
  font-size: 15px;
  font-weight: 500;
  cursor: default;
}
form .options .list{
  list-style: none;
}
.options .list li{
  height: 42px;
  width: 42px;
  margin: 0 -1px;
  cursor: pointer;
  border-radius: 50%;
}
.options .list li:hover{
  background: #f0f1f4;
}
.options .list li img{
  width: 23px;
}

#submitButton {
  height: 53px;
  color: #fff;
  font-size: 18px;
  font-weight: 500;
  cursor: pointer;
  color: #BCC0C4;
  cursor: no-drop;
  border-radius: 7px;
  background: #e2e5e9;
  transition: all 0.3s ease;
}

#cancelButton{
    height: 53px;
    font-size: 18px;
    font-weight: 500;
    cursor: pointer;
    border-radius: 7px;
    background: #4599FF;
    width: 48%;
    color: white;
    border-style: hidden;
}

#cancelButton:hover{
    background: #1a81ff;
}

form textarea:valid ~ #submitButton{
  color: #fff;
  cursor: pointer;
  background: #4599FF;
}
form textarea:valid ~ #submitButton:hover{
  background: #1a81ff;
}
.container .audience{
  opacity: 0;
  transition: opacity 0.12s ease;
}
.container.active .audience{
  opacity: 1;
}
.audience header{
  padding: 17px 0;
  text-align: center;
  position: relative;
  border-bottom: 1px solid #bfbfbf;
}
.audience header .arrow-back{
  position: absolute;
  left: 25px;
  width: 35px;
  height: 35px;
  cursor: pointer;
  font-size: 15px;
  color: #747474;
  border-radius: 50%;
  background: #E4E6EB;
}
.audience header p{
  font-size: 22px;
  font-weight: 600;
}
.audience .content{
  margin: 20px 25px 0;
}
.audience .content p{
  font-size: 17px;
  font-weight: 500;
}
.audience .content span{
  font-size: 14px;
  color: #65676B;
}
.audience .list{
  margin: 15px 16px 20px;
  list-style: none;
}
.audience .list li{
  display: flex;
  cursor: pointer;
  margin-bottom: 5px;
  padding: 12px 10px;
  border-radius: 7px;
  align-items: center;
  justify-content: space-between;
}
.list li.active,
.audience .list li.active:hover{
  background: #E5F1FF;
}
.audience .list li:hover{
  background: #f0f1f4;
}
.audience .list li .column .icon{
  height: 50px;
  width: 50px;
  color: #333;
  font-size: 23px;
  border-radius: 50%;
  background: #E4E6EB;
}
.audience .list li.active .column .icon{
  background: #cce4ff;
}
.audience .list li .column .details{
  margin-left: 15px;
}
.list li .column .details p{
  font-weight: 500;
}
.list li .column .details span{
  font-size: 14px;
  color: #65676B;
}
.list li .radio{
  height: 20px;
  width: 20px;
  border-radius: 50%;
  position: relative;
  border: 1px solid #707070;
}
.list li.active .radio{
  border: 2px solid #4599FF;
}
.list li .radio::before{
  content: "";
  width: 12px;
  height: 12px;
  border-radius: inherit;
}
.list li.active .radio::before{
  background: #4599FF;
}

input[type='checkbox'] {
    -webkit-appearance:none;
    width:25px;
    height:25px;
    background:white;
    border-radius:50%;
    border:1px solid #82ae46;
}
input[type='checkbox']:checked {
    background: #82ae46;
}

.scrollable-container {
  height: 450px; /* Set the desired height for your scrollable area */
  overflow: auto; /* This enables the scrollbars when the content overflows */
}

/* Style for the image thumbnails in the second div */
.image-thumbnail {
  display: inline-block;
  margin: 5px;
  position: relative;
}

.image-container input{
    height: 100%;
    width: 100%; 
    position: relative;
    padding: 5px;
}

.image-container{
    height: 100%;
    width: 20%;
    display: inline-block;
}

.close-button{
    font-size: 25px;
    margin-top: -10px;
    cursor: pointer;
}

</style>


<div class="container" style="overflow-y: scroll;">
    
      <div class="wrapper">
          
        <section class="post">
            <form action="PostCreate" id="formPost" method="post">
            <header>Create Post</header>
            <input oninput="checkInputLength(100);" id="title" name="title" placeholder="Give your post a title" style="width: 100%; margin-bottom: 10px;" type="text" >
            <div class="content">
              <img src="${user.getAvatar()}" alt="logo">
              <div class="details">
                <p>${user.getUserName()}</p>
                <div class="privacy">
                  <span>Categories</span>
                  <i class="fas fa-caret-down"></i>
                </div>
              </div>
            </div>
            <input type="hidden" name="userID" value="${user.getUserID()}">
            <textarea id="postContent" name="content" placeholder="What's on your mind?" spellcheck="false" ></textarea>
            
            <div class="options">
              <p>Add images to Your Post</p>
              <ul class="list">
                <li><img onclick="openFileDialog();" src="images/gallery.jpg" alt="gallery"></li>
                <input type="file" id="imageInput" accept="image/*" style="display: none">
              </ul>
            </div>
            
            <div  id="imageList" style="margin-top: 20px; overflow-x: scroll; width: 100%; white-space: nowrap; display: none">
            
            </div>
            <button id="submitButton" style="width: 48%;" onclick="checkAndSubmit();return false;">Post</button>
            <input onclick="window.history.back();" id="cancelButton" type="button" value="Cancel">
            </form>
        </section>
        <section class="audience">
          <header>
            <div class="arrow-back"><i class="fas fa-arrow-left"></i></div>
            <p>Select Categories</p>
          </header>
          <div class="content">
            <p>What category will your post is?</p>
            <span>You can choose as many category as you want</span>
          </div>
          <div class="scrollable-container">
          <ul class="list">
            <c:forEach items="${cateList}" var="cate">
            <li>
              <div class="column">
                <div class="details">
                  <p>${cate.CategoryName}</p>
                  <span style="width: 100%;">${cate.Description}</span>
                </div>
              </div>
                  <input value="${cate.CategoryID}" type="checkbox" class="checkbox">
            </li>
            </c:forEach>
          </ul>
          </div>
        </section>
      </div>
                          
    </div>
  </body>
<script>
//check category and submit data if category are valid
function checkAndSubmit() {
  var checkboxes = document.querySelectorAll('input[type="checkbox"]');
  var titleBox = document.getElementById('title');
  var content = document.getElementById('postContent');
  var formContainer = document.getElementById('formPost');
  var atLeastOneChecked = false;
  
  for (var i = 0; i < checkboxes.length; i++) {
    if (checkboxes[i].checked) {
      const hiddenInput = document.createElement('input');
      hiddenInput.type = 'hidden';
      hiddenInput.name = 'category';
      hiddenInput.value = checkboxes[i].value;
      hiddenInput.multiple = 'true';
      formContainer.appendChild(hiddenInput);
      atLeastOneChecked = true;
    }
  }
  if (atLeastOneChecked === false) {
    alert("Please choose at least one category");
  }
  else if (titleBox.value.length === 0) {
    alert("You forgot to add a title");
  }
  else if (content.value.length === 0) {
    alert("You forgot to write some content");
  }
  else {
    document.getElementById('formPost').submit();
    alert("Post successfully, let's wait for the admin to accept the post ok? ^^");
  }
}

  //remove image inside image box, remove the box if image box is empty
  function removeElement(id){
      const elementToRemove = document.getElementById(id);
      elementToRemove.remove();
      const motherDiv = document.getElementById('imageList');
      const subDivs = motherDiv.querySelectorAll('div'); // Change 'div' to the appropriate selector for your sub divs
      if (subDivs.length === 0) {
          motherDiv.style.display = 'none'; // Hide the mother div
          var mainElement = document.querySelector(".container");
          mainElement.style.height = "450px";
      }
  }
  
      //show and hide the category list
      const container = document.querySelector(".container"),
      privacy = container.querySelector(".post .privacy"),
      arrowBack = container.querySelector(".audience .arrow-back");

      privacy.addEventListener("click", () => {
        container.classList.add("active");
      });

      arrowBack.addEventListener("click", () => {
        container.classList.remove("active");
      });

//add event to filedialog
const input = document.getElementById('imageInput');
let timeout = null;
let dialogopen = false;

input.addEventListener('change', () => {
  clearTimeout(timeout);
  checkFiles();
});
input.addEventListener('click', () => {
  clearTimeout(timeout);
  dialogopen = true;
});
window.addEventListener('focus', () => {
  if (dialogopen) {
    input.value = '';
    input.files = null;
    clearTimeout(timeout);
    timeout = setTimeout(checkFiles(), 100);
  }
});
      
//open file dialog when click on gallery button      
function openFileDialog(){
    document.getElementById("imageInput").click();
}

//add image to image box
function addImage(imageLink){
    const now = new Date();
    const minutes = now.getMinutes();
    const seconds = now.getSeconds();
    const uniqueId = minutes+''+seconds;
    
    const imageContainer = document.createElement('div');
    imageContainer.classList.add('image-container');
    imageContainer.id = uniqueId;
    
    const closeButton = document.createElement('div');
    closeButton.classList.add('close-button');
    closeButton.textContent = '×';
    closeButton.onclick = function () {
        removeElement(uniqueId);
    };
    
    const inputImage = document.createElement('input');
    inputImage.type = 'image';
    inputImage.src = imageLink;
    inputImage.value = imageLink;
    
    const hiddenInput = document.createElement('input');
    hiddenInput.type = 'hidden';
    hiddenInput.name = 'image';
    hiddenInput.value = imageLink;
    hiddenInput.multiple = 'true';
    
    inputImage.onclick = function () {
        const byteCharacters = atob(imageLink.split(',')[1]);
        const byteNumbers = new Array(byteCharacters.length);
        for (let i = 0; i < byteCharacters.length; i++) {
            byteNumbers[i] = byteCharacters.charCodeAt(i);
        }
        const byteArray = new Uint8Array(byteNumbers);
        const blob = new Blob([byteArray], { type: 'image/jpeg' });

        const objectURL = URL.createObjectURL(blob);
        const newTab = window.open(objectURL, '_blank');
        return false;
    };
    
    imageContainer.appendChild(hiddenInput);
    imageContainer.appendChild(closeButton);
    imageContainer.appendChild(inputImage);
    const imageList = document.getElementById('imageList');
    imageList.appendChild(imageContainer);
}

//check length of title input
function checkInputLength(maxLength) {
    var input = document.getElementById("title");
    var inputValue = input.value;

    if (inputValue.length > maxLength) {
        alert("Title is too long. Please keep it within " + maxLength + " characters.");
        input.value = inputValue.substring(0, maxLength);
    }
}

//add image for each time user choose image from file dialog
function checkFiles() {
  dialogopen = false;
  if(input.files.length > 0){
      const motherDiv = document.getElementById('imageList');
      const images = input.files;
      const reader = new FileReader();
      motherDiv.style.display = 'block'; // show the mother div
      var mainElement = document.querySelector(".container");
      mainElement.style.height = "450px";
      for (let i = 0; i < images.length; i++) {
          var image = images[i];
          reader.onload = function () {
            addImage(reader.result);
          };
          reader.readAsDataURL(image);
    }
  }
}
</script>
</html>