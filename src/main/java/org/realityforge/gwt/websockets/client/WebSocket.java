package org.realityforge.gwt.websockets.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.typedarrays.shared.ArrayBuffer;
import com.google.gwt.typedarrays.shared.ArrayBufferView;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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

  public enum BinaryType
  {
    BLOB, ARRAYBUFFER
  }

  private static SupportDetector g_supportDetector;
  private static Factory g_factory;
  @Nonnull
  private WebSocketListener _listener = NullWebSocketListener.LISTENER;

  /**
   * Create a WebSocket if supported by the platform.
   *
   * This method will use the registered factory to create the WebSocket instance.
   *
   * @return a WebSocket instance, if supported by the platform, null otherwise.
   */
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

  /**
   * Register a factory to be used to construct WebSocket instances.
   * This is not usually used as the built in browser based factory will
   * be detected and used if available. The register method is typically used
   * by test frameworks.
   *
   * @param factory the factory to register.
   */
  public static void register( @Nonnull final Factory factory )
  {
    g_factory = factory;
  }

  /**
   * Deregister factory if the specified factory is the registered factory.
   *
   * @param factory the factory to deregister.
   * @return true if able to deregister, false otherwise.
   */
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

  public abstract void connect( @Nonnull String server, @Nonnull String... protocols )
    throws IllegalStateException;

  public abstract void close()
    throws IllegalStateException;

  public void close( short code )
    throws IllegalStateException
  {
    close( code, null );
  }

  public abstract void close( short code, @Nullable String reason )
    throws IllegalStateException;

  public abstract boolean isConnected();

  public abstract void send( @Nonnull String data )
    throws IllegalStateException;

  public abstract void send( @Nonnull ArrayBuffer data )
    throws IllegalStateException;

  public abstract void send( @Nonnull ArrayBufferView data )
    throws IllegalStateException;

  public abstract int getBufferedAmount()
    throws IllegalStateException;

  public abstract String getProtocol()
    throws IllegalStateException;

  public abstract String getURL()
    throws IllegalStateException;

  public abstract String getExtensions()
    throws IllegalStateException;

  public abstract ReadyState getReadyState()
    throws IllegalStateException;

  public abstract void setBinaryType( @Nonnull BinaryType binaryType )
    throws IllegalStateException;

  public abstract BinaryType getBinaryType()
    throws IllegalStateException;

  @Nonnull
  public final WebSocketListener getListener()
  {
    return _listener;
  }

  public final void setListener( @Nullable final WebSocketListener listener )
  {
    _listener = null == listener ? NullWebSocketListener.LISTENER : listener;
  }

  /**
   * Fire a Connected event.
   */
  protected final void onOpen()
  {
    getListener().onOpen( this );
  }

  /**
   * Fire a Close event.
   */
  protected final void onClose( final boolean wasClean,
                                final int code,
                                @Nullable final String reason )
  {
    getListener().onClose( this, wasClean, code, reason );
  }

  /**
   * Fire a Message event.
   */
  protected final void onMessage( final String data )
  {
    getListener().onMessage( this, data );
  }

  /**
   * Fire a Message event.
   */
  protected final void onMessage( final ArrayBuffer data )
  {
    getListener().onMessage( this, data );
  }

  /**
   * Fire an Error event.
   */
  protected final void onError()
  {
    getListener().onError( this );
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
