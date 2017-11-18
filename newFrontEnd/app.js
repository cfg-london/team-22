function readIn(file) {
    $.get("localhost:1235", function(data, status){
        alert(data);
    });
}

var element = document.createElement("span");
var text = document.createTextNode(readIn("../Scraper/outLiterature/agnon-bio.html"));
element.appendChild(text);
$("#bio").append(element);

let test = () => {
    $.getJSON( "http://127.0.0.1:1235", function( data ) {
        alert("hi")
        var items = [];
        $.each( data, function( key, val ) {
            items.push( "<li id='" + key + "'>" + val + "</li>" );
        });

        $( "<ul/>", {
            "class": "my-new-list",
            html: items.join( "" )
        }).appendTo( "body" );
    });
}



