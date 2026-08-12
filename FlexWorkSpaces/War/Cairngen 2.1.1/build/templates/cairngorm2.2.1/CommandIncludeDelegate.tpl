@copy@

package @namespace@.commands
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import @namespace@.business.@sequence@Delegate;
	import @namespace@.events.@sequence@Event;
	import @namespace@.model.ModelLocator;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	
    /**
     *
     * Defines the associated <code>ICommand</code> implementation for 
     * an "@sequence@" use-case.
     *
     * <p>
     * The <code>@sequence@Command</code> is utilized to abstract the 
     * handling of a <code>@sequence@Event</code>.
     * </p>
     *    
     * @see @namespace@.events.@sequence@Event
     * @see com.adobe.cairngorm.commands.ICommand
     *
     */
	public final class @sequence@Command implements ICommand, IResponder
	{
	    /**
	     *
	     * Concrete <code>ICommand</code> implementation which handles 
	     * an <code>@sequence@Event</code>.
	     *
	     */
		public function execute(event:CairngormEvent) : void
		{
			var evt:@sequence@Event = event as @sequence@Event;
			var delegate:@sequence@Delegate = new @sequence@Delegate( this );
		}
		
	    /**
	     *
	     * Handles the service result of the <code>@sequence@Delegate</code> 
	     * service invocation.
	     *
	     * @see mx.rpc.events.ResultEvent
	     *
	     */
		public function result(data:Object) : void
		{
			var result:ResultEvent = data as ResultEvent;
		}
		
	    /**
	     *
	     * Handles the service fault of the <code>@sequence@Delegate</code> 
	     * service invocation.
	     *
	     * @see mx.rpc.events.ResultEvent
	     *
	     */
		public function fault(info:Object) : void
		{
			var fault:FaultEvent = info as FaultEvent;
		}
	}
}
