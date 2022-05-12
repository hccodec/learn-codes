


function btnChangeType(obj1) {
    // $("#textInfo").val(obj1.name);

    switch (obj1.name) {
        case "type1":
            //$("#canvas").css("background-image", "url(/static/report2.jpg)");
            break;
        case "type2":
            //$("#canvas").css("background-image", "url(/static/report1.jpg)");
            //this.$ref.canvas.css("background-image", "url(/static/report1.jpg)");
            break;
        case "type3":
            //$("#canvas").css("background-image", "url(/static/report3.jpg)");
            break;
        case "type4":
            // document.body.appendChild(project.exportSVG());
            break;
    }

}

function btnChangeColor(obj) {

    switch (obj.name) {
        case "color1":
            this.penColor = "red";
            break;
        case "color2":
            this.penColor = "green";
            break;
        case "color3":
            this.penColor = "blue";
            break;
    }
}

function btnChangeLineWidth(obj) {

    switch (obj.name) {
        case "lineWidth1":
            this.strokeWidth = 5;
            break;
        case "lineWidth2":
            this.strokeWidth = 10;
            break;
        case "lineWidth3":
            this.strokeWidth = 15;
            break;
    }
}
function btnScore(obj) {
    //$("#textInfo").val(obj.innerText);
    app.tool3.activate();
}




