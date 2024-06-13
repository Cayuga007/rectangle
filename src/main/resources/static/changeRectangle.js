function changeRectangle() {
    var uid = document.getElementById("uid").value;
    var name = document.getElementById("name").innerText;
    var width = parseInt(document.getElementById("width").innerText);
    var height = parseInt(document.getElementById("height").innerText);
    var color = document.getElementById("color").innerText;
    var link = document.getElementById("link");
    link.hidden = false;
    link.href = "/rectangles/change/" + uid + "/" + encodeURIComponent(name) + "/" + width + "/" + height + "/" + encodeURIComponent(color);
}