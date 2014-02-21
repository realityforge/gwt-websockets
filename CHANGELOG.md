## 0.5:

* Add WebSocket.isConnected() method.
* Ensure WebSocket.getExtensions(), WebSocket.getProtocol() and WebSocket.getURL() throw an
  IllegalStateException if the WebSocket is not connected.
* Ensure WebSocket.getReadyState() returns CLOSED when not connected.
* Move WebSocket to using WebSocketListener interface as the primary means of notifying library
  consumers of web socket events. Add an EventBasedWebSocketListener to support the previous
  event-oriented mechanisms of integrating with the WebSocket library.

## 0.4:

* Restore Java 6 compatibility.

## 0.3:

* Support receiving ArrayBuffer messages.
* Implement WebSocket.send(ArrayBuffer) and WebSocket.send(ArrayBufferView).
* Implement WebSocket.getBinaryType() and WebSocket.setBinaryType().
* Implement WebSocket.getExtensions().
* Implement WebSocket.getURL().
* Add support for WebSocket.close method that allows specification of the code and reason parameters.
* Implement WebSocket.getProtocol().

## 0.2:

* Support WebSocket.isSupported() method that will return true if WebSocket.newWebSocketIfSupported()
  will not return a null value.
* Rename WebsocketEvent to WebSocketEvent to maintain consistent casing with the rest of the library.

## 0.1:

* Initial release
