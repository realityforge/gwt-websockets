package org.realityforge.gwt.websockets.client;

import com.google.gwt.typedarrays.shared.ArrayBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Adapter listener to make sub-classing easier.
 */
public abstract class WebSocketListenerAdapter
  implements WebSocketListener
{
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
