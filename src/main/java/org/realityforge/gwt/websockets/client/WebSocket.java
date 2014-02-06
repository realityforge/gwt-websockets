package org.realityforge.gwt.websockets.client;

import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.realityforge.gwt.websockets.client.event.CloseEvent;
import org.realityforge.gwt.websockets.client.event.ErrorEvent;
import org.realityforge.gwt.websockets.client.event.MessageEvent;
import org.realityforge.gwt.websockets.client.event.OpenEvent;
import org.realityforge.gwt.websockets.client.html5.Html5WebSocket;

public abstract class WebSocket
{
  public interface Factory
  {
    WebSocket newWebSocket();
  }

  public enum ReadyState
  {
    CONNECTING, OPEN, CLOSING, CLOSED
  }

  private static SupportDetector g_supportDetector;
  private static Factory g_factory;
  private final EventBus _eventBus;

  public static WebSocket newWebSocketIfSupported()
  {
    if ( null == g_factory && GWT.isClient() && getSupportDetector().isSupported() )
    {
      register( getSupportDetector().newFactory() );
      return g_factory.newWebSocket();
    }
    return ( null != g_factory ) ? g_factory.newWebSocket() : null;
  }

  /**
   * @return true if newWebSocketIfSupported() will return a non-null value, false otherwise.
   */
  public static boolean isSupported()
  {
    return ( null != g_factory ) || GWT.isClient() && getSupportDetector().isSupported();
  }

  public static void register( @Nonnull final Factory factory )
  {
    g_factory = factory;
  }

  public static boolean deregister( @Nonnull final Factory factory )
  {
    if ( g_factory != factory )
    {
      return false;
    }
    else
    {
      g_factory = null;
      return true;
    }
  }

  public WebSocket( final EventBus eventBus )
  {
    _eventBus = eventBus;
  }

  public abstract void connect( @Nonnull String server, @Nonnull String... protocols );

  public abstract void close();

  public abstract void send( @Nonnull String data );

  public abstract int getBufferedAmount();

  public abstract String getProtocol();

  public abstract ReadyState getReadyState();

  @Nonnull
  public final HandlerRegistration addOpenHandler( @Nonnull OpenEvent.Handler handler )
  {
    return _eventBus.addHandler( OpenEvent.getType(), handler );
  }

  @Nonnull
  public final HandlerRegistration addCloseHandler( @Nonnull CloseEvent.Handler handler )
  {
    return _eventBus.addHandler( CloseEvent.getType(), handler );
  }

  @Nonnull
  public final HandlerRegistration addMessageHandler( @Nonnull MessageEvent.Handler handler )
  {
    return _eventBus.addHandler( MessageEvent.getType(), handler );
  }

  @Nonnull
  public final HandlerRegistration addErrorHandler( @Nonnull ErrorEvent.Handler handler )
  {
    return _eventBus.addHandler( ErrorEvent.getType(), handler );
  }

  /**
   * Fire a Connected event.
   */
  protected final void onOpen()
  {
    _eventBus.fireEventFromSource( new OpenEvent( this ), this );
  }

  /**
   * Fire a Close event.
   */
  protected final void onClose( final boolean wasClean,
                                final int code,
                                @Nullable final String reason )
  {
    _eventBus.fireEventFromSource( new CloseEvent( this, wasClean, code, reason ), this );
  }

  /**
   * Fire a Message event.
   */
  protected final void onMessage( final String data )
  {
    _eventBus.fireEventFromSource( new MessageEvent( this, data ), this );
  }

  /**
   * Fire an Error event.
   */
  protected final void onError()
  {
    _eventBus.fireEventFromSource( new ErrorEvent( this ), this );
  }

  /**
   * Detector for browser support.
   */
  private static class SupportDetector
  {
    public boolean isSupported()
    {
      return Html5WebSocket.isSupported();
    }

    public Factory newFactory()
    {
      return new Html5WebSocket.Factory();
    }
  }

  /**
   * Detector for browsers without WebSocket support.
   */
  @SuppressWarnings( "unused" )
  private static class NoSupportDetector
    extends SupportDetector
  {
    @Override
    public boolean isSupported()
    {
      return false;
    }

    @Override
    public Factory newFactory()
    {
      return null;
    }
  }

  private static SupportDetector getSupportDetector()
  {
    if ( null == g_supportDetector )
    {
      g_supportDetector = com.google.gwt.core.shared.GWT.create( SupportDetector.class );
    }
    return g_supportDetector;
  }
}
