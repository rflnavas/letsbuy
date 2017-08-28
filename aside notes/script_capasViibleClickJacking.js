
$(function(){
	function recursive(node){
		debugger;
		if(node.children().length == 0){
			node.css(
			{
				'opacity':1, 
				'visibility':'visible', 
				'display':'block', 
				'color':'green'
			});
			return;
		} else{
			node.children().each(function(){
				var node=$(this);
				recursive(node);
			});
		}
	}
	
	$(document).children().each(function(){
		var node=$(this);
		recursive(node);
	});
})();	

