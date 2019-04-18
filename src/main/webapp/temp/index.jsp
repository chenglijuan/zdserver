<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
Welcome<br/><input id="text" type="text"/>
<button onclick="send()">发送消息</button>
<hr/>
<button onclick="closeWebSocket()">关闭WebSocket连接</button>
<hr/>
<div id="message"></div>
</body>

<script type="text/javascript">

    var userId =randomNum(1,10);

    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://192.168.1.13:8080/websocket?userId="+userId);
    }
    else {
        alert('当前浏览器 不支持 webSocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
        heartCheck.start();
        setMessageInnerHTML("WebSocket连接成功");
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        heartCheck.reset();
        setMessageInnerHTML(event.data);
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("WebSocket连接关闭");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }

    //发送消息
    function send() {
        var message = document.getElementById('text').value;
        websocket.send(message);
    }


    var heartCheck = {
        timeout: 30000,
        timeoutObj: null,
        reset: function(){
            clearTimeout(this.timeoutObj);
            this.start();
        },
        start: function(){
            this.timeoutObj = setTimeout(function(){
                websocket.send('HeartBeat');
            },this.timeout)
        },
    };

    //生成从minNum到maxNum的随机数
    function randomNum(minNum,maxNum){
        switch(arguments.length){
            case 1:
                return parseInt(Math.random()*minNum+1,10);
                break;
            case 2:
                return parseInt(Math.random()*(maxNum-minNum+1)+minNum,10);
                break;
            default:
                return 0;
                break;
        }
    }
</script>
</html>