/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.post-method').forEach(function(form) {
        form.addEventListener('submit', function(e) {
            e.preventDefault();
            var postId = this.querySelector('input[name="post_id"]').value;
            var commentContent = this.querySelector('textarea[name="commentContent"]').value;

            fetch('commentServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: 'post_id=' + postId + '&commentContent=' + encodeURIComponent(commentContent)
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    // Create new comment element
                    var commentHtml = `
                        <div class="comment mb-2" style="margin-left: 20px;">
                            <div class="comment-header">
                                <img src="assets/profile_avt/${data.profilePic}" class="img-fluid rounded-circle avatar me-2" style="width: 30px; height: 30px;">
                                <small><strong>${data.firstName} ${data.lastName}</strong></small>
                            </div>
                            <div class="comment-body">
                                <p style="margin-bottom: 0;">${data.commentText}</p>
                            </div>
                        </div>
                    `;

                    // Add new comment to the post
                    var postElement = this.closest('.post');
                    var commentsContainer = postElement.querySelector('.post-comments');
                    commentsContainer.insertAdjacentHTML('beforeend', commentHtml);

                    // Clear the comment input
                    this.querySelector('textarea[name="commentContent"]').value = '';
                } else {
                    console.error('Error:', data.error);
                }
            })
            .catch((error) => {
                console.error('Error:', error);
            });
        });
    });
});