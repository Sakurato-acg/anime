let loginBtn_btn=document.querySelector(".loginBtn_btn");
let loginBtn_user = document.querySelector(".loginBtn_user");
let item = window.sessionStorage.getItem("username");
let p = document.querySelector(".loginBtn_user p");
let a = document.querySelector(".loginBtn_user a");
if (item==null){
    loginBtn_btn.style.display="block";
    loginBtn_user.style.display="none";
}else{
    loginBtn_btn.style.display="none";
    loginBtn_user.style.display="block";
    p.innerHTML=item;
}
let url=$(location).attr("hostname");
a.addEventListener("click",function (){
    alert("注销");
    axios({
        method:"post",
        url:"http://"+url+":80/anime/userServlet/logout"
    }).then(function (resp){
        if (resp.data=='success'){
            alert(resp.data);
            window.sessionStorage.clear();
            location.href="http://"+url+":80/anime/index.html"
        }
    })
})
