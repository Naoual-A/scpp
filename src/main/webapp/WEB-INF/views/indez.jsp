<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Hello WebSocket</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<script src="<c:url value="/static/js/sockjs-0.3.4.js" />"></script>
	<script src="<c:url value="/static/js/stomp.js" />"></script>
	<script src="<c:url value="/static/js/jquery.min.js" />"></script>
	<script src="<c:url value="/static/js/app.js" />"></script>
</head>
<body onload="disconnect()">
        <div>
            <div>
                <input type="text" id="from" placeholder="Choose a nickname"/>
            </div>
            <br />
            <div>
                <button id="connect" onclick="connect();">Connect</button>
                <button id="disconnect" disabled="disabled" onclick="disconnect();">
                    Disconnect
                </button>
            </div>
            <br />
            <div id="conversationDiv">
                <input type="text" id="text" placeholder="Write a message..."/>
                <button id="sendMessage" onclick="sendMessage();">Send</button>
                <p id="response"></p>
            </div>
        </div>
 
    </body>
</html>