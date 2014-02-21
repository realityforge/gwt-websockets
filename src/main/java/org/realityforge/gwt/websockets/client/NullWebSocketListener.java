package org.realityforge.gwt.websockets.client;

import com.google.gwt.typedarrays.shared.ArrayBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Null listener so as to avoid the need to null value.
 */
final class NullWebSocketListener
  implements WebSocketListener
{
  static final NullWebSocketListener LISTENER  = new NullWebSocketListener();

  @Override
  public void onOpen( @Nonnull final WebSocket webSocket )
  {
  }

  @Override
  public void onClose( @Nonnull final WebSocket webSocket,
                       final boolean wasClean,
                       final int code,
                       @Nullable final String reason )
  {
  }

  @Override
  public void onMessage( @Nonnull final WebSocket webSocket, @Nonnull final String data )
  {
  }

  @Override
  public void onMessage( @Nonnull final WebSocket webSocket, @Nonnull final ArrayBuffer data )
  {
  }

  @Override
  public void onError( @Nonnull final WebSocket webSocket )
  {
  }
}
