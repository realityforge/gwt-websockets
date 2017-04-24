gwt-websockets
--------------

[![Build Status](https://secure.travis-ci.org/realityforge/gwt-websockets.png?branch=master)](http://travis-ci.org/realityforge/gwt-websockets)
[<img src="https://img.shields.io/maven-central/v/org.realityforge.gwt.websockets/gwt-websockets.svg?label=latest%20release"/>](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.realityforge.gwt.websockets%22%20a%3A%22gwt-websockets%22)

A simple library to provide web sockets support to GWT.

Quick Start
===========

The simplest way to use the library is to add the following dependency
into the build system. i.e.

```xml
<dependency>
   <groupId>org.realityforge.gwt.websockets</groupId>
   <artifactId>gwt-websockets</artifactId>
   <version>1.2</version>
   <scope>provided</scope>
</dependency>
```

Then you add the following snippet into the .gwt.xml file.

```xml
<module rename-to='myapp'>
  ...

  <!-- Enable the websocket library -->
  <inherits name="org.realityforge.gwt.websockets.WebSockets"/>
</module>
```

Then you can interact with the WebSocket from within the browser.

```java
final WebSocket webSocket = WebSocket.newWebSocketIfSupported();
if ( null != webSocket )
{
  webSocket.setListener( new WebSocketListenerAdapter()
  {
    @Override
    public void onOpen( final WebSocket webSocket )
    {
      // After we have connected we can send
      webSocket.send( "Hello!" );
    }

    @Override
    public void onMessage( final WebSocket webSocket, final String data )
    {
      // After we receive a message back we can close the socket
      webSocket.close();
    }
  } );
  webSocket.connect( "ws://localhost/ws/echo" );
}
```

This should be sufficient to put together a simple WebSocket application.

A very simple example of this code is available in the
[gwt-websockets-example](https://github.com/realityforge/gwt-websockets-example)
project.

TODO
----

* Consider supporting a Blob object from the HTML5 File API with send and receive.

Appendix
--------

* [Mozilla WebSockets Document](https://developer.mozilla.org/en-US/docs/WebSockets)
* [RFC6455: The WebSocket Protocol](http://tools.ietf.org/html/rfc6455)
