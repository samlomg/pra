<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="description" content="">
    <meta name="author" content="Sam.BC.Liu">


    <%--重写的head --%>
    <title>Chat</title>

</head>
<body>
<%--主体部分 --%>
<div id="wrapper">
        <div id="heading" class="masthead">
            <h3 class="muted">Chat Application</h3>
        </div>
    <p>
        lbc
    </p>
        <div>
            <!--<form>-->
            <!--<select id="protocol">-->
            <!--<option value="auto">Automatic</option>-->
            <!--<option value="websocket">websocket</option>-->
            <!--<option value="xhr-streaming">xhr-streaming</option>-->
            <!--<option value="xhr-polling">xhr-polling</option>-->
            <!--<option value="iframe-eventsource">iframe-eventsource</option>-->
            <!--<option value="iframe-htmlfile">iframe-htmlfile</option>-->
            <!--<option value="iframe-xhr-polling">iframe-xhr-polling</option>-->
            <!--<option value="jsonp-polling">jsonp-polling</option>-->
            <!--<option value="xdr-streaming">xdr-streaming</option>-->
            <!--<option value="xdr-polling">xdr-polling</option>-->
            <!--</select>-->
            <!--</form>-->
        </div>
        <div id="main-content">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>User</th>
                    <th></th>
                </tr>
                </thead>
                <tbody data-bind="foreach: friends()">
                <tr>
                    <td data-bind="text: username"></td>
                    <td class="trade-buttons">
                        <button class="btn btn-primary" data-bind="click: $root.conversation().chat">Chat</button>
                    </td>
                </tr>
                </tbody>
            </table>

            <div id="trade-dialog" class="modal hide fade" tabindex="-1">
                <div class="modal-body">
                    <h3 id="chat-title">Chat with <span data-bind="text: conversation().to().username"></span></h3>
                    <div id="chat" data-bind="foreach: conversation().messages()">
                        <div data-bind="text: messageFormatted"></div>
                    </div>
                    <form class="form-horizontal" data-bind="submit: conversation().send">
                        <input type="text" data-bind="value: conversation().draft"/>
                        <button class="btn btn-primary" type="submit">Send</button>
                    </form>
                </div>
            </div>
            <div class="alert alert-warning">
                <h5>Notifications</h5>
                <ul data-bind="foreach: notifications">
                    <li data-bind="text: notification"></li>
                </ul>
            </div>
        </div>
    </div>


    <!-- 3rd party -->
<script src="${jQuery}"></script>
<script src="${BootstrapCoreJavaScript}"></script>
    <script src="resources/js/knockout/knockout.js"></script>
    <script src="resources/js/sockjs/sockjs.js"></script>
    <script src="resources/js/stomp/lib/stomp.js"></script>

    <!-- application -->
    <script src="resources/js/message.js"></script>
    <script type="text/javascript">
        (function() {
            var socket = new SockJS('/sample/chat');
            var stompClient = Stomp.over(socket);

            var appModel = new ApplicationModel(stompClient);
            ko.applyBindings(appModel);

            appModel.connect();
        })();
    </script>

</div>
<!-- /#wrapper -->


<%--脚本部分--%>




<!--补充部分  -->
<script>


</script>

</body>
</html>