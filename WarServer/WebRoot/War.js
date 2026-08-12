function moveIFrame(x,y,w,h)
{
    var frameRef = document.getElementById("content");
    if(document.body.clientWidth>=1000){
		frameRef.style.left = x+(document.body.clientWidth-1000)/2 + "px";
	}else {
		frameRef.style.left = 0 + "px";
	}
    frameRef.style.top = y + "px";
    frameRef.style.width = w-20 + "px";
    frameRef.style.height = h + "px";
}

function setIFrameContent( contentSource )
{
	document.getElementById("content").innerHTML = contentSource;
}

function hideIFrame()
{
    document.getElementById("content").style.visibility = "hidden";
}

function showIFrame()
{
    document.getElementById("content").style.visibility = "visible";
}

