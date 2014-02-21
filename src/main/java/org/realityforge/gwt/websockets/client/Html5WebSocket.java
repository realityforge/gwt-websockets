package org.realityforge.gwt.websockets.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.typedarrays.shared.ArrayBuffer;
import com.google.gwt.typedarrays.shared.ArrayBufferView;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

final class Html5WebSocket
  extends WebSocket
{
  public static native boolean isSupported() /*-{
    return !!window.WebSocket;
  }-*/;

  private WebSocketImpl _webSocket;

  static class Factory
    implements WebSocket.Factory
  {
    @Override
    public WebSocket newWebSocket()
    {
      return new Html5WebSocket();
    }
  }

  @Override
  public void close()
  {
    checkConnected();
    _webSocket.close();
    _webSocket = null;
  }

  @Override
  public void close( final short code, @Nullable final String reason )
  {
    checkConnected();
    _webSocket.close( code, reason );
    _webSocket = null;
  }

  @Override
  public void connect( @Nonnull final String server, @Nonnull final String... protocols )
  {
    if ( null != _webSocket )
    {
      throw new IllegalStateException( "WebSocket already connected" );
    }
    _webSocket = WebSocketImpl.create( this, server );
    setBinaryType( BinaryType.ARRAYBUFFER );
  }

  @Override
  public boolean isConnected()
  {
    return null != _webSocket;
  }

  @Override
  public final int getBufferedAmount()
  {
    checkConnected();
    return _webSocket.getBufferedAmount();
  }

  @Override
  public String getProtocol()
  {
    checkConnected();
    return _webSocket.getProtocol();
  }

  @Override
  public String getURL()
  {
    checkConnected();
    return _webSocket.getURL();
  }

  @Override
  public String getExtensions()
  {
    checkConnected();
    return _webSocket.getExtensions();
  }

  @Override
  public final void send( @Nonnull String data )
  {
    checkConnected();
    _webSocket.send( data );
  }

  @Override
  public void send( @Nonnull final ArrayBufferView data )
  {
    checkConnected();
    _webSocket.send( data );
  }

  @Override
  public void send( @Nonnull final ArrayBuffer data )
  {
    checkConnected();
    _webSocket.send( data );
  }

  @Override
  public final ReadyState getReadyState()
  {
    if ( null == _webSocket )
    {
      return ReadyState.CLOSED;
    }
    else
    {
      return ReadyState.values()[ _webSocket.getReadyState() ];
    }
  }

  @Override
  public void setBinaryType( @Nonnull final BinaryType binaryType )
  {
    checkConnected();
    _webSocket.setBinaryType( binaryType.name().toLowerCase() );
  }

  @Override
  public BinaryType getBinaryType()
  {
    checkConnected();
    return BinaryType.valueOf( _webSocket.getBinaryType().toUpperCase() );
  }

  private void checkConnected()
  {
    if ( null == _webSocket )
    {
      throw new IllegalStateException( "WebSocket not connected" );
    }
  }

  private final static class WebSocketImpl
    extends JavaScriptObject
  {
    static native WebSocketImpl create( WebSocket client, String server, String... protocols )
    /*-{
      var ws = new WebSocket( server, protocols );
      ws.onopen = $entry( function ()
                          {
                            client.@org.realityforge.gwt.websockets.client.WebSocket::onOpen()();
                          } );
      ws.onerror = $entry( function ()
                           {
                             client.@org.realityforge.gwt.websockets.client.WebSocket::onError()();
                           } );
      ws.onmessage = $entry( function ( response )
                             {
                               if ( typeof(response.data) == 'string' )
                               {
                                 client.@org.realityforge.gwt.websockets.client.WebSocket::onMessage(Ljava/lang/String;)( response.data );
                               }
                               else
                               {
                                 client.@org.realityforge.gwt.websockets.client.WebSocket::onMessage(Lcom/google/gwt/typedarrays/shared/ArrayBuffer;)( response.data );
                               }
                             } );
      ws.onclose = $entry( function ( event )
                           {
                             client.@org.realityforge.gwt.websockets.client.WebSocket::onClose(ZILjava/lang/String;)( event.wasClean,
                                                                                                                      event.code,
                                                                                                                      event.reason );
                           } );
      return ws;
    }-*/;

    protected WebSocketImpl()
    {
    }

    native int getBufferedAmount() /*-{
      return this.bufferedAmount;
    }-*/;

    native int getReadyState() /*-{
      return this.readyState;
    }-*/;

    native void close() /*-{
      this.close();
    }-*/;

    native void close( int code, String reason ) /*-{
      this.close( code, reason );
    }-*/;

    native void send( String data ) /*-{
      this.send( data );
    }-*/;

    native void send( ArrayBuffer data ) /*-{
      this.send( data );
    }-*/;

    native void send( ArrayBufferView data ) /*-{
      this.send( data );
    }-*/;

    native String getBinaryType()  /*-{
      return this.binaryType;
    }-*/;

    native void setBinaryType( String binaryType )  /*-{
      this.binaryType = binaryType;
    }-*/;

    native String getURL() /*-{
      return this.url;
    }-*/;

    native String getExtensions()  /*-{
      return this.extensions;
    }-*/;

    native String getProtocol()  /*-{
      return this.protocol;
    }-*/;
  }
}
