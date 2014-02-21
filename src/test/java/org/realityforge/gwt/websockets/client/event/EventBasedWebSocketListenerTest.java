package org.realityforge.gwt.websockets.client.event;

import com.google.gwt.typedarrays.shared.ArrayBuffer;
import com.google.web.bindery.event.shared.HandlerRegistration;
import org.realityforge.gwt.websockets.client.TestWebSocket;
import org.testng.annotations.Test;
import static org.mockito.Mockito.*;

public class EventBasedWebSocketListenerTest
{
  @Test
  public void handlerInteractions()
  {
    final TestWebSocket webSocket = new TestWebSocket();
    final EventBasedWebSocketListener listener = new EventBasedWebSocketListener();

    {
      final OpenEvent.Handler handler = mock( OpenEvent.Handler.class );
      final HandlerRegistration registration = listener.addOpenHandler( handler );
      listener.onOpen( webSocket );
      verify( handler, only() ).onOpenEvent( any( OpenEvent.class ) );
      registration.removeHandler();
      listener.onOpen( webSocket );
      verify( handler, atMost( 1 ) ).onOpenEvent( any( OpenEvent.class ) );
    }

    {
      final CloseEvent.Handler handler = mock( CloseEvent.Handler.class );
      final HandlerRegistration registration = listener.addCloseHandler( handler );
      listener.onClose( webSocket, true, CloseEvent.CLOSE_NORMAL, null );
      final CloseEvent expected = new CloseEvent( webSocket, true, CloseEvent.CLOSE_NORMAL, null );
      verify( handler, only() ).onCloseEvent( refEq( expected, "source" ) );
      registration.removeHandler();
      listener.onClose( webSocket, true, CloseEvent.CLOSE_NORMAL, null );
      verify( handler, atMost( 1 ) ).onCloseEvent( any( CloseEvent.class ) );
    }

    {
      final MessageEvent.Handler handler = mock( MessageEvent.Handler.class );
      final HandlerRegistration registration = listener.addMessageHandler( handler );
      listener.onMessage( webSocket, "Blah" );
      final MessageEvent expected = new MessageEvent( webSocket, "Blah" );
      verify( handler, only() ).onMessageEvent( refEq( expected, "source" ) );
      registration.removeHandler();
      listener.onMessage( webSocket, "Blah" );
      verify( handler, atMost( 1 ) ).onMessageEvent( any( MessageEvent.class ) );
    }

    {
      final MessageEvent.Handler handler = mock( MessageEvent.Handler.class );
      final HandlerRegistration registration = listener.addMessageHandler( handler );
      final ArrayBuffer arrayBuffer = mock( ArrayBuffer.class );
      listener.onMessage( webSocket, arrayBuffer );
      final MessageEvent expected = new MessageEvent( webSocket, arrayBuffer );
      verify( handler, only() ).onMessageEvent( refEq( expected, "source" ) );
      registration.removeHandler();
      listener.onMessage( webSocket, arrayBuffer );
      verify( handler, atMost( 1 ) ).onMessageEvent( any( MessageEvent.class ) );
    }

    {
      final ErrorEvent.Handler handler = mock( ErrorEvent.Handler.class );
      final HandlerRegistration registration = listener.addErrorHandler( handler );
      listener.onError( webSocket );
      verify( handler, only() ).onErrorEvent( any( ErrorEvent.class ) );
      registration.removeHandler();
      listener.onError( webSocket );
      verify( handler, atMost( 1 ) ).onErrorEvent( any( ErrorEvent.class ) );
    }
  }
}
