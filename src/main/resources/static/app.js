var stompClient = null;
var autogenerate = null;
//let GEN = "/app/start";
//let AUTOGEN = "/app/autostart";

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#generate").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/generates', function (generate) {
            showNumbers(JSON.parse(generate.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendData() {
    stompClient.send("/app/start", {}, JSON.stringify({
        'lengthArray': [
            $("#length1").val(),
            $("#length2").val(),
            $("#length3").val(),
            $("#length4").val(),
            $("#length5").val()
        ]
    }));
}

function sendDataAutoStart() {
    autogenerate = setInterval(
        sendData
        , 1000);
}

function sendDateAutoStop() {
    clearInterval(autogenerate);
}


function showNumbers(message) {
    $("#generates").append(jsonToTable(message));
}

function jsonToTable(json) {
    return json.map(p =>
        `<tr>${[p[0], p[1], p[2], p[3], p[4], p[5]].map(el => `<td>${el}</td>`).join('')}</tr>`).join('');
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        /*sendData(GEN);*/
        sendData();

    });
    $("#send_auto_start").click(function () {
        sendDataAutoStart();
        /*sendData(AUTOGEN);*/
    });

    $("#send_auto_stop").click(function () {
        sendDateAutoStop();
    });
})