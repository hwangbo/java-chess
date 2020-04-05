$(document).ready(function () {
    document.getElementById("startBtn").onclick = function () {
        window.location.href = '/start';
    };
    document.getElementById("resumeBtn").onclick = function () {
        window.location.href = '/resume';
    };
    if (window.location.pathname !== "/") {
        document.getElementById("startBtn").innerHTML = "재시작";
        document.getElementById("resumeBtn").style.display = "none";
    }
});