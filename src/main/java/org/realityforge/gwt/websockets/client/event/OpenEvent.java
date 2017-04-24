package org.realityforge.gwt.websockets.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import javax.annotation.Nonnull;
import org.realityforge.gwt.websockets.client.WebSocket;

/**
 * Event fired when web socket successfully connects.
 */
public class OpenEvent
  extends WebSocketEvent<OpenEvent.Handler>
{
  public interface Handler
    extends EventHandler
  {
    void onOpenEvent( @Nonnull OpenEvent event );
  }

  private static final GwtEvent.Type<Handler> TYPE = new Type<Handler>();

  public static GwtEvent.Type<Handler> getType()
  {
    return TYPE;
  }

  public OpenEvent( @Nonnull final WebSocket webSocket )
  {
    super( webSocket );
  }

  @Override
  public GwtEvent.Type<Handler> getAssociatedType()
  {
    return OpenEvent.getType();
  }

  @Override
  protected void dispatch( @Nonnull final Handler handler )
  {
    handler.onOpenEvent( this );
  }
}
