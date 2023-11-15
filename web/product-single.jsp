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
<style>
    .invisible-text {
    color: transparent; /* Make the text transparent */
  }
  
.unselectable {
    user-select: none; /* Prevent text selection */
  }
  
.reply-box {
    display: none;
}

.btn:hover {
    background-color: #82AE46;
}

.btn{
    border-style: groove;
    border-color: #82AE46;
}

.comment-container {
        max-height: 400px; /* Set your desired fixed height */
        overflow-y: scroll; /* Enable vertical scrollbar if necessary */
        border: 1px solid #ccc; /* Add a border or customize styling as needed */
        padding: 10px;
    }
    
    #seemore:hover {
        text-decoration: underline;
    }
</style>
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
            
<!--            comment section-->
            <c:set var="user" value="${sessionScope.user}"/>
            <c:if test="${user eq null}">
                <h3 class="mb-5">You need to login to comment</h3>
            </c:if>
            <div class="pt-5 mt-5">
            <c:if test="${user ne null}">
            <h3 class="mb-5">${CommentNumber} ${CommentNumber > 1 ? "Comments":"Comment"} </h3>
            <form></form>
            <form action="feedback" method="post" onsubmit="saveScrollPositions('comment', 'delete');">
                    <input type="hidden" name="action" value="comment">
                    <input type="hidden" name="userID" value="${user.getUserID()}">
                    <input type="hidden" name="objectID" value="${param.pid}">
              <div style="margin-bottom: 30px;" id="comment">
                    <textarea name="content" style="width: 100%; font-size: 20px; margin-top: -30px;" class="reply-text" placeholder="Enter your comment here..."></textarea>
                    <div class="btn-container">
                        <button style="font-size: 18px" type="submit" class="btn" id="submitBtn${loop.index}">Submit</button>
                    </div>
              </div>
            </form>
            </c:if>
            <c:if test="${CommentNumber eq 0}">
                <h3 class="mb-5">There aren't any comment feedback for this product</h3>
            </c:if>
            <c:if test="${CommentNumber > 0}">
                <div class="comment-container" id="commentContainer">
                <c:forEach items="${rootCommentList}" varStatus="loop" var="comment">
              <ul class="comment-list">
                  <li  class="comment" id="position${comment.getCommentID()}">
                  <div class="vcard bio">
                    <img src="${rootCommentList[loop.index].getAvatar()}" alt="Image placeholder">
                  </div>
                  <div class="comment-body" id="comment${rootCommentList[loop.index].getCommentID()}">
                     <c:choose>
                              <c:when test="${user.getUserID() eq rootCommentList[loop.index].getUserID()}">
                                  <h3>You</h3>
                              </c:when>
                              <c:otherwise>
                                  <h3>${rootCommentList[loop.index].getUserName()}</h3>
                              </c:otherwise>
                     </c:choose>
                    <div class="meta">${rootCommentList[loop.index].getCommentDate()}</div>
                    <p>${rootCommentList[loop.index].getCommentContent()}</p>
                    <p style="display: inline">
                        <c:choose>
                            <c:when test="${loop.last && fn:length(repCommentList[loop.index]) == 0}">
                                <a style="display: inline" href="javascript:showReply('replyBox'+'${comment.getCommentID()}', 'true', '${comment.getUserName()}');" class="reply">Reply</a>
                            </c:when>
                            <c:otherwise>
                                <a style="display: inline" href="javascript:showReply('replyBox'+'${comment.getCommentID()}', 'false', '${comment.getUserName()}');" class="reply">Reply</a>
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <c:if test="${fn:length(repCommentList[loop.index])>0}">
                        <p style="display: inline"><a id="hideShow${rootCommentList[loop.index].getCommentID()}" href="javascript:showAndHideReply('rep'+'${rootCommentList[loop.index].getCommentID()}', 'hideShow'+'${rootCommentList[loop.index].getCommentID()}');" class="reply">Hide Reply</a></p>
                    </c:if>
                    <c:if test="${user.getUserID() eq rootCommentList[loop.index].getUserID()}">
                    <form onsubmit="saveScrollPositions('delete', 'delete');" id="delete${rootCommentList[loop.index].getCommentID()}" action="comment" method="post" style="display: inline" class="reply">
                        <input type="hidden" name="action" value="commentDelete">
                        <input type="hidden" name="objectID" value="${param.id}">
                        <input type="hidden" name="commentID" value="${rootCommentList[loop.index].getCommentID()}">
                        <a style="cursor: pointer" onclick="submitDeleteForm('delete'+'${comment.getCommentID()}');">Delete</a>
                    </form>
                    </c:if>
                <form action="comment" method="post" onsubmit="saveScrollPositions('commentRep', 'position'+'${comment.getCommentID()}');">
                    <div id="replyBox${rootCommentList[loop.index].getCommentID()}" class="reply-box" >
                        <input type="hidden" name="action" value="commentRep">
                        <input type="hidden" name="userID" value="${user.getUserID()}">
                        <input type="hidden" name="objectID" value="${param.id}">
                        <input type="hidden" name="commentRepID" value="${rootCommentList[loop.index].getCommentID()}">
                        <textarea name="content" style="width: 100%" class="reply-text" placeholder="Enter your reply here..."></textarea>
                        <div class="btn-container">
                            <button type="submit" class="btn" id="submitBtn${loop.index}">Submit</button>
                            <button type="button" onclick="showReply('replyBox'+'${rootCommentList[loop.index].getCommentID()}', 'false');" class="btn" id="cancelBtn${loop.index}">Cancel</button>
                        </div>
                    </div>
                </form>
                  </div>
                  <c:if test="${fn:length(repCommentList[loop.index])>0}">
                  <c:forEach items="${repCommentList[loop.index]}" var="repComment" varStatus="repIndex">
                  <ul class="children rep${rootCommentList[loop.index].getCommentID()}">
                      <li class="comment" id="comment${repComment.getCommentID()}">
                      <div class="vcard bio">
                        <img src="${repComment.getAvatar()}" alt="Image placeholder">
                      </div>
                      <div class="comment-body">
                          <c:choose>
                              <c:when test="${user.getUserID() eq repComment.getUserID()}">
                                  <h3 class="yourComment">You</h3>
                              </c:when>
                              <c:otherwise>
                                  <h3>${repComment.getUserName()}</h3>
                              </c:otherwise>
                          </c:choose>
                        <div class="meta">${repComment.getCommentDate()}</div>
                        <p>${repComment.getCommentContent()}</p>
                        <c:choose>
                            <c:when test="${repIndex.last && loop.last}">
                                <p style="display: inline;"><a href="javascript:showReply('replyBox'+'${repComment.getCommentID()}', 'true', '${repComment.getUserName()}');" class="reply">Reply</a></p>
                            </c:when>
                            <c:otherwise>
                                <p style="display: inline;"><a href="javascript:showReply('replyBox'+'${repComment.getCommentID()}', 'false', '${repComment.getUserName()}');" class="reply">Reply</a></p>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${user.getUserID() eq repComment.getUserID()}">
                        <form onsubmit="saveScrollPositions('delete', 'delete');" id="delete${repComment.getCommentID()}" action="comment" method="post" style="display: inline" class="reply">
                            <input type="hidden" name="action" value="commentDelete">
                            <input type="hidden" name="objectID" value="${param.id}">
                            <input type="hidden" name="commentID" value="${repComment.getCommentID()}">
                            <a style="cursor: pointer" onclick="submitDeleteForm('delete'+'${repComment.getCommentID()}');">Delete</a>
                        </form>
                        </c:if>
                    <form action="comment" method="post" onsubmit="saveScrollPositions('commentRep', 'position'+'${comment.getCommentID()}');">
                        <div class="reply-box" id="replyBox${repComment.getCommentID()}">
                            <input type="hidden" name="action" value="commentRep">
                            <input type="hidden" name="userID" value="${user.getUserID()}">
                            <input type="hidden" name="objectID" value="${param.id}">
                            <input type="hidden" name="commentRepID" value="${rootCommentList[loop.index].getCommentID()}">
                            <textarea name="content" style="width: 100%" class="reply-text" placeholder="Enter your reply here..."></textarea>
                          <div class="btn-container">
                            <button type="submit" class="btn" id="submitBtn${loop.index}">Submit</button>
                            <button type="button" onclick="showReply('replyBox'+'${repComment.getCommentID()}', 'false', '${repComment.getUserName()}');" class="btn" id="cancelBtn${loop.index}">Cancel</button>
                          </div>
                        </div>
                    </form>
                      </div>
                    </li>
                  </ul>
                  </c:forEach>
                  </c:if>  
                </li>
              </ul>
              </c:forEach>
            </div>
            </c:if>
            
            
<!--                end of comment section-->
            </div>
        </div>
        </div>
        </div>
    </section>
    
</form>
                         
<script>
    // Function to save scroll positions for both page and the div
function saveScrollPositions(commentType, position) {
    sessionStorage.setItem('pageScrollPosition', window.scrollY);
    if (commentType === "comment") {
    sessionStorage.setItem('containerScrollPosition', 'none');
    }
    else if(commentType === "delete"){
    sessionStorage.setItem('containerScrollPosition', document.getElementById('commentContainer').scrollTop);
    }
    else{
    sessionStorage.setItem('position', position);
    }
}

// Function to retrieve and restore scroll positions for both page and the div
function restoreScrollPositions() {
    var pageScrollPosition = sessionStorage.getItem('pageScrollPosition');
    var containerScrollPosition = sessionStorage.getItem('containerScrollPosition');
    var commentContainer = document.getElementById('commentContainer');
    
    //scroll to comment
    if(sessionStorage.getItem('containerScrollPosition') !== null){
        if (containerScrollPosition === "none") {
            commentContainer.scrollTop = commentContainer.scrollHeight;
        }
        else commentContainer.scrollTop = containerScrollPosition;
    }
    else {
        var position = sessionStorage.getItem('position');
        var comments = document.getElementById(position);
        var yourComments = comments.querySelectorAll('.comment');
        var comment = yourComments[yourComments.length-1];
        var element = document.getElementById(comment.id);
        element.scrollIntoView();
    }
    //scroll to comment section
    if (pageScrollPosition) {
        window.scrollTo(0, pageScrollPosition);
    }
    sessionStorage.clear();
}

// Execute this function when the page is loaded
window.addEventListener('load', function() {
    restoreScrollPositions();
});




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
    
    //for comment section
    // Function to save scroll positions for both page and the div
function saveScrollPositions(commentType, position) {
    sessionStorage.setItem('pageScrollPosition', window.scrollY);
    if (commentType === "comment") {
    sessionStorage.setItem('containerScrollPosition', 'none');
    }
    else if(commentType === "delete"){
    sessionStorage.setItem('containerScrollPosition', document.getElementById('commentContainer').scrollTop);
    }
    else{
    sessionStorage.setItem('position', position);
    }
}

function showAndHideReply(idComment, idButton){
  var elements = document.querySelectorAll('.'+idComment);
  var y = document.getElementById(idButton);
  if (y.textContent == "Hide Reply") {
    y.textContent = "Show Reply";
  }
  else if (y.textContent == "Show Reply") {
    y.textContent = "Hide Reply";
  }
  for (var i = 0; i < elements.length; i++) {
    var x = elements[i];
    if (x.style.display === "none") {
    x.style.display = "block";
    } else {
    x.style.display = "none";
  }
  }
}

function showReply(replyBoxId, bottom, userName) {
    var replyBox = document.getElementById(replyBoxId);
        if (!replyBox.style.display || replyBox.style.display === "none") {
            replyBox.style.display = "block";
        } else {
            replyBox.style.display = "none";
        }
    var textarea = replyBox.querySelector("textarea");
    if (textarea) {
        textarea.value = "Reply to "+userName+": ";
    }
    if (bottom == "true") {
        scrollToBottom();
    }
}

function scrollToBottom() {
        var commentContainer = document.getElementById('commentContainer');
        commentContainer.scrollTop = commentContainer.scrollHeight;
    }   

function submitDeleteForm(formId) {
    var form = document.getElementById(formId);
    saveScrollPositions('delete', 'delete');
    if (confirm('Are you sure you want to delete the comment?')) {
        form.submit();
    }
}

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
