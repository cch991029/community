function post() {
    var questionId = $("#comment_id").val();
    var content = $("#comment_content").val();

    if(!content){
        alert("不能回复空内容~~~");
    }

    $.ajax({
        type:"post",
        url:"/comment",
        contentType:"application/json",
        dataType:"json",
        data:JSON.stringify({
            "parentId":questionId,
            "content":content,
            "type":1
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