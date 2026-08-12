// ActionScript file
import com.adobe.cairngorm.control.CairngormEventDispatcher;
import com.adobe.serialization.json.JSON;
import com.hifong.war.events.common.GetCityResourcesEvent;
import com.hifong.war.util.MsgBox;
import com.hifong.war.util.SystemUtil;

public var gameSocket:Socket = new Socket;

public function initGameSocket():void{
	
	//注册 Socket连接事件
	gameSocket.addEventListener(Event.CONNECT,socketConnect);
	//注册 Socket关闭事件
	gameSocket.addEventListener(Event.CLOSE,socketClose);
	//注册 安全错误事件
	gameSocket.addEventListener(SecurityErrorEvent.SECURITY_ERROR,securityError);
	//注册 处理Socket接收数据事件
	gameSocket.addEventListener(ProgressEvent.SOCKET_DATA,socketData);
	//注册 IO错误事件
	gameSocket.addEventListener(IOErrorEvent.IO_ERROR,socketIOError);
	
	gameSocket.connect(model.SERVER_IP,model.GAME_SOCKET_PORT);
	
}

/**
 * 初始化套接字
 */
private function initSocket():void{
	var byte:ByteArray = new ByteArray();
	byte.writeUTFBytes("init:" + ModelLocator.getInstance().playerInfo.playerID + "\n");
	gameSocket.writeBytes(byte);
	gameSocket.flush();
}

//连接建立
private function socketConnect(event:Event):void{
	model.app.chatPanel.addSystemMessage("欢迎您来到《绝地战争》的世界！");
	initSocket();
}

//连接关闭
private function socketClose(event:Event):void{
	model.app.chatPanel.addSystemMessage("与聊天服务器连接中断。");
	MsgBox.showMessage("与服务器连接中断，请尝试重新进入游戏。",false,SystemUtil.refreshBrowser);
	model.globalTimer.stop();
}

//安全错误
private function securityError(event:SecurityErrorEvent):void{
	model.app.chatPanel.addSystemMessage("连接聊天服务器失败，请刷新后重新进入。错误原因：安全错误。");
}

//IO错误
private function socketIOError(event:Event):void{
	model.app.chatPanel.addSystemMessage("连接聊天服务器失败，请刷新后重新进入。错误原因：输入输出错误。");
}

//接收数据
private function socketData(event:ProgressEvent):void{
	
	var msg:String = gameSocket.readUTFBytes(gameSocket.bytesAvailable);
	var json:Object = JSON.decode(msg);
	
	switch(json.type){
		case 1:
			//系统消息
			scrollCanvas.scrollText(json.message);
			var chatMessage:String = "<font color=\"#E9060F\"><b>[系统]</b>：" + json.message + "</font>";
			model.app.chatPanel.addChatMessage(chatMessage,1);
			break;
		case 2:
			//聊天
			model.app.chatToolbar.receiveMessage(json);
			break;
		case 3:
			//新报告
			model.haveNewReport = true;
			break;
		case 4:
			//新消息
			model.haveNewMessage = true;
			break;
		case 11:
			//帐号在其他地方登录
			model.globalTimer.stop();
			MsgBox.showMessage("您的帐号已在另一地点登录，您被迫离开游戏。", false, SystemUtil.refreshBrowser);
			break;
		case 21:
			//服务端强制刷新客户端城市资源信息，防止脏数据
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityResourcesEvent(model.cityInfo.cityID));
			break;
		default:
			break;
	}
	
}
