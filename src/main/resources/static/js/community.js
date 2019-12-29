/**
 * 提交回复
 */
function post() {
    var questionId = $("#comment_id").val();
    var content = $("#comment_content").val();
    addComment(questionId,1,content);
}

function addComment(targetId,type,content) {
    if(!content){
        alert("不能回复空内容~~~");
    }

    $.ajax({
        type:"post",
        url:"/comment",
        contentType:"application/json",
        dataType:"json",
        data:JSON.stringify({
            "parentId":targetId,
            "content":content,
            "type":type
        }),
        success:function (data) {
            if(data.code == 200){
                window.location.reload();
            }else{
                if(data.code == 2003){
                    var isAccepted = confirm(data.message);
                    if(isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=3fa274ba7fa0eb87e601&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closeable","true");
                    }
                }else {
                    alert(data.message);
                }
            }
        }
    });
}

function comment(e) {
    var commentId = $(e).data("id");
    var content = $("#input-"+commentId).val();
    addComment(commentId,2,content);
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = $(e).data("id");
    var comments = $("#comment-"+id);

    //获取二级评论的展开状态
    var collapse = $(e).data("collapse");
    if(collapse){
        //折叠二级评论
        $(e).removeData("collapse");
        comments.toggleClass("in");
        $(e).toggleClass("active");
    }else{
        var subCommentContainer = $("#comment-"+id);
        if(subCommentContainer.children().length != 1){
            //展开二级评论
            $(e).data("collapse","in");
            comments.toggleClass("in");
            $(e).toggleClass("active");
        }else {
            //展开二级评论
            $.ajax({
                type:"get",
                url:"/comment/"+id,
                contentType:"application/json",
                dataType:"json",
                success:function (data) {
                    console.log(data);
                    $.each(data.data.reverse(),function (index,comment) {
                        var mediaLeftElement = $("<div/>",{
                            "class":"media-left"
                        }).append($("<img/>",{
                            "class":"media-object img-rounded",
                            "src":comment.user.avatarUrl
                        }));

                        var mediaBodyElement = $("<div/>",{
                            "class":"media-body"
                        }).append($("<h5/>",{
                            "class":"media-heading",
                            "html":comment.user.name
                        })).append($("<div/>",{
                            "html":comment.content
                        })).append($("<div/>",{
                            "class":"menu"
                        }).append($("<span/>",{
                            "class":"pull-right",
                            "html":moment([comment.gmtCreate]).fromNow()/*moment(comment.gmtCreate, "YYYYMMDD").fromNow()*/
                        })));

                        var mediaElement = $("<div/>",{
                            "class":"media"
                        }).append(mediaLeftElement).append(mediaBodyElement);

                        var commentElement = $("<div/>",{
                            "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                        }).append(mediaElement);

                        subCommentContainer.prepend(commentElement);
                    });
                }
            });
            $(e).data("collapse","in");
            comments.toggleClass("in");
            $(e).toggleClass("active");
        }
    }
}