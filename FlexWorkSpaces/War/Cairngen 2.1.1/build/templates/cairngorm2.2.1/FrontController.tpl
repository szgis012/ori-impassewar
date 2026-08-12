@copy@

package @namespace@.@control@
{
	import com.adobe.cairngorm.control.FrontController;
    import @namespace@.commands.*;
    import @namespace@.events.*;
    
    /**
     *
     * Defines the <code>@projectname@Controller</code> which is 
     * utilized by the application to map each <code>Event</code>
     * object to an associated <code>ICommand</command> object.
     * 
     * @see com.adobe.cairngorm.control.FrontController
     *
     */
	public final class @projectname@Controller extends FrontController
	{
	    /**
	     *
	     * The <code>@projectname@Controller</code> constructor invokes
	     * the <code>initialize()</code> method so as to abstract the 
	     * <code>Event</code> / <code>Command</code> mappings from the
	     * constructor.
	     *
	     */
		public function @projectname@Controller()
		{
			this.initialize();
		}
		
	    /**
	     *
	     * The <code>initialize()</code> method maps each defined 
	     * <code>Event</code> to it's associated <code>ICommand</code>
	     * implementation.
	     *
	     */
		private function initialize() : void
		{
			//todo: add commands
		}
	}
}
