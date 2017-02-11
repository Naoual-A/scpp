//var stompClient = null;
//
//function setConnected(connected) {
//    $("#connect").prop("disabled", connected);
//    $("#disconnect").prop("disabled", !connected);
//    if (connected) {
//        $("#conversation").show();
//    }
//    else {
//        $("#conversation").hide();
//    }
//    $("#greetings").html("");
//}
//
//function connect() {
//    var socket = new SockJS('/scpp/notificaciones');
//    stompClient = Stomp.over(socket);
//    stompClient.connect({}, function (frame) {
//        setConnected(true);
//        console.log('Connected: ' + frame);
//        stompClient.subscribe('/topic/greetings', function (greeting) {
//            showGreeting(JSON.parse(greeting.body).content);
//        });
//    });
//}
//
//function disconnect() {
//    if (stompClient != null) {
//        stompClient.disconnect();
//    }
//    setConnected(false);
//    console.log("Disconnected");
//}
//
//function sendName() {
//    stompClient.send("/app/notificacion	es", {}, JSON.stringify({'name': $("#name").val()}));
//}
//
//function showGreeting(message) {
//    $("#greetings").append("<tr><td>" + message + "</td></tr>");
//}
//
//$(function () {
//    $("form").on('submit', function (e) {
//        e.preventDefault();
//    });
//    $( "#connect" ).click(function() { connect(); });
//    $( "#disconnect" ).click(function() { disconnect(); });
//    $( "#send" ).click(function() { sendName(); });
//});
var stompClient = null;
             
            function setConnected(connected) {
//                document.getElementById('connect').disabled = connected;
//                document.getElementById('disconnect').disabled = !connected;
//                document.getElementById('conversationDiv').style.visibility 
//                  = connected ? 'visible' : 'hidden';
//                document.getElementById('response').innerHTML = '';
            }
             
            function connect(destino) {
                var socket = new SockJS('/scpp/notificaciones');
                stompClient = Stomp.over(socket);  
                stompClient.connect({}, function(frame) {
                    setConnected(true);
                    console.log('Connected: ' + frame);
                    stompClient.subscribe('/topic/noti', function(messageOutput) {
                        showMessageOutput(JSON.parse(messageOutput.body));
                    });
                });	

                sendMessage(destino);
            }
             
            function disconnect() {
                if(stompClient != null) {
                    stompClient.disconnect();
                }
                setConnected(false);
                console.log("Disconnected");
            }
             
            function sendMessage(destino) {
//                var from = document.getElementById('from').value;
//                var text = document.getElementById('text').value;
                var text = "Tiene una notificacion pendiente";
                stompClient.send("/app/notificaciones", {}, JSON.stringify({'text':text}));
            }
             
            function showMessageOutput(messageOutput) {
                var response = document.getElementById('response');
                var p = document.createElement('p');
                p.style.wordWrap = 'break-word';
                p.appendChild(document.createTextNode(messageOutput.from + ": " 
                  + messageOutput.text + " (" + messageOutput.time + ")"));
                response.appendChild(p);
            }