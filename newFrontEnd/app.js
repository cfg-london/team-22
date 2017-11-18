function controlFlow() {
    var person = getParameterByName("name");
    addCard("bio");
    addText(person + "-bio.html", "#bio");
    addCard("lectureText")
    addText(person + "-lecture.html", "#lectureText")
}

function addCard(tagId) {
    $("#cardContainer").append(" <div class=\"card\">\n" +
        "            <div id=\"lecture\" class=\"card-content\">\n" +
        "              <p id=\"" + tagId + "\"></p>\n" +
        "            </div>\n" +
        "            <div class=\"card-action\">\n" +
        "              <i class=\"material-icons\">comment</i>\n" +
        "              <i class=\"material-icons\">thumb_up</i>\n" +
        "              <i class=\"material-icons\">share</i>\n" +
        "            </div>\n" +
        "          </div>");
}


function addText(filename, tagId) {
    $.get("http://localhost:1235/" + filename, function(data){
        $(tagId).html(data);
    });
}

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}



