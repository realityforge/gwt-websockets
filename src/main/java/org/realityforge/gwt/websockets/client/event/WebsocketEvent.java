package org.realityforge.gwt.websockets.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import javax.annotation.Nonnull;
import org.realityforge.gwt.websockets.client.WebSocket;

/**
 * Base class of all events originating from web socket.
 */
public abstract class WebSocketEvent<H extends EventHandler>
  extends GwtEvent<H>
{
  private final WebSocket _webSocket;

  protected WebSocketEvent( @Nonnull final WebSocket webSocket )
  {
    _webSocket = webSocket;
  }

  @Nonnull
  public final WebSocket getWebSocket()
  {
    return _webSocket;
  }
}
