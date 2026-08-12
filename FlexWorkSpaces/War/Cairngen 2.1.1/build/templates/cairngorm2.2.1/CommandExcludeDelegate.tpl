@copy@

package @namespace@.commands
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import @namespace@.events.@sequence@Event;
	import @namespace@.model.ModelLocator;

	import mx.rpc.IResponder;

	public final class @sequence@Command implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:@sequence@Event = event as @sequence@Event;
		}
		
		public function result(data:Object) : void
		{
			
		}
		
		public function fault(info:Object) : void
		{
		
		}
		
	}
}