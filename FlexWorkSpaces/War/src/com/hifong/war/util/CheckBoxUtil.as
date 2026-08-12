// ActionScript file
private function selectAllCheckboxes(obj:Object):void{
	for(var i:int=0;i<obj.length;i++){
		obj[i].selected = true;
	}
}
		
private function unSelectAllCheckboxes(obj:Object):void{
	for(var i:int=0;i<obj.length;i++){
		obj[i].selected = false;
	}
}