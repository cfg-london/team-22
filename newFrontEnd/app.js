function controlFlow() {
    var person = getParameterByName("name");
    var laureate = getBio(person);
    console.log(laureate);
    setupBio(laureate);
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

function setupBio(laureate){
  $("#laureate-Name").text(laureate.name);
  $("#biography").append("Born: " + laureate.born +
        "<br /> Birth Country: " + laureate.bornCountry +
        " <br /> Prize Category: " + laureate.category +
        " <br /> Year Awarded Nobel Prize: " + laureate.prizeYear +
        " <br /> Motivation: " + laureate.motivation);
}

function getBio(name) {
    var file = JSON.parse(getJSON("http://api.nobelprize.org/v1/laureate.json"));
    for (var i = 0; i < file.laureates.length; i++) {
        var byId = file.laureates[i];
        if (byId.surname === undefined) {
            continue;
        }
        var lastName = byId.surname.toLowerCase();
        var cleanLastName = lastName.normalize("NFKD").replace(/[^ a-z]+/g, "");
        var lastNameArray = cleanLastName.split(" ");
        var finalLastName = lastNameArray[lastNameArray.length - 1];
        // console.log(finalLastName);
        // console.log(name);
        if (name !== finalLastName) {
            continue;
        }
        // foundem
        var prize = byId.prizes[0];
        return {
            "name" : byId.firstname + " " + byId.surname,
            "born" : byId.born.substring(0, 4),
            "died" : byId.died.substring(0, 4),
            "bornCountry" : byId.bornCountry,
            "prizeYear" : prize.year,
            "category" : prize.category,
            "motivation" : prize.motivation
        }
    }

}

function getJSON(url) {
    var resp ;
    var xmlHttp ;

    resp  = '' ;
    xmlHttp = new XMLHttpRequest();

    if(xmlHttp != null)
    {
        xmlHttp.open( "GET", url, false );
        xmlHttp.send( null );
        resp = xmlHttp.responseText;
    }

    return resp ;
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
