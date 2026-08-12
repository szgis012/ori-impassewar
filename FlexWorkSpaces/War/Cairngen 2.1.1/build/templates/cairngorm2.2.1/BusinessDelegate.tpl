@copy@

package @namespace@.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import @namespace@.business.Services;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	
    /**
     *
     * Defines the associated Business Delegate for the "@sequence@"  
     * use-case.
     *
     * <p>
     * The <code>@sequence@Delegate</code> is utilized to abstract 
     * an asynchronous service invocation in which the response is
     * to be relayed to an <code>IResponder</code> implementation.
     * </p>
     *
     */
	public final class @sequence@Delegate
	{
	    /**
	     *
	     * Defines the reference to the <code>@sequence@Command<code>
	     * instance.
	     *
	     */
		private var responder:IResponder;
		
		/**
		 *
		 * Instantiates a new instance of <code>@sequence@Delegate</code>
		 * and initializes a reference to the <code>IResponder<code> instance.
		 *
		 */
		public function @sequence@Delegate(responder:IResponder)
		{
			this.responder = responder;
		}
	}
}
