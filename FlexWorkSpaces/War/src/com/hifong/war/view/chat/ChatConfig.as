package com.hifong.war.view.chat
{
	public class ChatConfig
	{
		//表情占位符列表
		public static var CHAT_FACE_NAME:Array=['⓪','①','②','③','④','⑤','⑥','⑦','⑧','⑨','⑩'];
		//表情占位符
		public static var CHAT_FACE_SPACE:String="　";
		//表情占位符 间距
		public static var CHAT_FACE_LETTER_SPACE:int=0;
		//表情正则 替代符
		public static var CHAT_REG:RegExp=/[⓪,①,②,③,④,⑤,⑥,⑦,⑧,⑨,⑩,⑪,⑫,⑬,⑭,⑮,⑯,⑰,⑱,⑲,⑳]/g;
	}
}