package org.realityforge.gwt.websockets.client;

import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import javax.annotation.Nonnull;

final class TestWebSocket
  extends WebSocket
{
  static class Factory
    implements WebSocket.Factory
  {
    @Override
    public WebSocket newWebSocket()
    {
      return new TestWebSocket( new SimpleEventBus() );
    }
  }

  TestWebSocket( final EventBus eventBus )
  {
    super( eventBus );
  }

  @Override
  public void connect( @Nonnull final String server, @Nonnull final String... protocols )
  {
  }

  @Override
  public void close()
  {
  }

  @Override
  public void send( @Nonnull final String data )
  {
  }

  @Override
  public int getBufferedAmount()
  {
    return 0;
  }

  @Override
  public ReadyState getReadyState()
  {
    return null;
  }
}
