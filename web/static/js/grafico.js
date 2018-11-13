/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var config = {
    "type": "line",
    "data": {
        "labels": [
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July"
        ],
        "datasets": [{
                "label": "My First Dataset",
                "data": [65, 59, 80, 81, 56, 55, 40],
                "fill": false,
                "borderColor": "rgb(75, 192, 192)",
                "lineTension": 0.1
            }]
    },
    "options": {
    }
};

window.onload = function () {
    var ctx = document.getElementById('canvas').getContext('2d');
    window.myLine = new Chart(ctx, config);
};

