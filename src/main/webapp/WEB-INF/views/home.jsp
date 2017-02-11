<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<%-- <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link> --%>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<script src="<c:url value="/static/js/sockjs-0.3.4.js" />"></script>
	<script src="<c:url value="/static/js/stomp.js" />"></script>
	<script type="text/javascript">
        var stompClient = null;

        function setConnected(connected) {
            document.getElementById('connect').disabled = connected;
            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
            document.getElementById('response').innerHTML = '';
        }

        function connect() {
        	try{
	            var socket = new SockJS('/scpp/hello');
	            stompClient = Stomp.over(socket);
	            stompClient.connect({}, function(frame) {
	                setConnected(true);
	                console.log('Connected: ' + frame);
	                stompClient.subscribe('/topic/greetings', function(greeting){
	                	try{
	                        showGreeting(JSON.parse(greeting.body).content);	
	                	} catch (err){
	                		document.getElementById("demo").innerHTML = err.message;
	                	}
	                });
	            });
        	} catch (err){
        		document.getElementById("demo").innerHTML = err.message;
        	}
        }

        function disconnect() {
            if (stompClient != null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
        }

        function sendName() {
            var name = document.getElementById('name').value;
            stompClient.send("/app/hello", {}, JSON.stringify({ 'name': name }));
        }

        function showGreeting(message) {
        	try{
        		
        	
            	var response = document.getElementById('response');
            	var p = document.createElement('p');
            	p.style.wordWrap = 'break-word';
            	p.appendChild(document.createTextNode(message));
            	response.appendChild(p);
        	} catch(err){
        		document.getElementById("demo").innerHTML = err.message;
        	}
        }
    </script>

</head>
<body onload="disconnect()">
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being enabled. Please enable
    Javascript and reload this page!</h2></noscript>
<div>
    <div>
        <button id="connect" onclick="connect();">Connect</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
    </div>
    <div id="conversationDiv">
        <label>What is your name?</label><input type="text" id="name" />
        <button id="sendName" onclick="sendName();">Send</button>
        <p id="response"></p>
    </div>
</div>
<p id="demo"></p>
</body>
</html>
