package org.realityforge.gwt.websockets.client;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class WebSocketTest
{
  @Test
  public void registryTest()
  {
    assertNull( WebSocket.newWebSocketIfSupported() );
    assertFalse( WebSocket.isSupported() );
    final TestWebSocket.Factory factory = new TestWebSocket.Factory();
    WebSocket.register( factory );
    assertNotNull( WebSocket.newWebSocketIfSupported() );
    assertTrue( WebSocket.isSupported() );
    assertTrue( WebSocket.deregister( factory ) );
    assertNull( WebSocket.newWebSocketIfSupported() );
    assertFalse( WebSocket.isSupported() );
    assertFalse( WebSocket.deregister( factory ) );
  }
}
