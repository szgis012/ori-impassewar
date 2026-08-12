@copy@

package @namespace@.events
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class @sequence@Event extends CairngormEvent
	{

		public static const @sequenceToUpperCase@_EVENT:String = "@namespace@.events.@sequence@Event";

		public function @sequence@Event() 
		{
			super( @sequenceToUpperCase@_EVENT );
		}
	}
}
