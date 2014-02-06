## 0.3:

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
