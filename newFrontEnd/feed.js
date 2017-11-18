//Not Working
$(".show-more a").on("click", () => {
    var $this = $(this);
    var $content = $this.parent().prev("div.content");
    var linkText = $this.text().toUpperCase();

    if(linkText === "SHOW MORE"){
        linkText = "Show less";
        $content.switchClass("hideContent", "showContent", 400);
    } else {
        linkText = "Show more";
        $content.switchClass("showContent", "hideContent", 400);
    };

    $this.text(linkText);
});​

if (window.File && window.FileReader && window.FileList && window.Blob) {
  var json = JSON.parse(window.FileReader('./practiceInputs/Martin-Luther-King.json'));
  console.log(json);
} else {
  alert('The File APIs are not fully supported in this browser.');
}
