/**
 * 
 */
(function(){
	console.log('Hi from JS!');
})()

$(function(){
	$('.link-delete').on('click', function(){
		//@{/review/delete/{idProduct}(idProduct=${rvw.id})}
		var that = $(this);
		var _url = that.attr('data-link');
		$.ajax({
			url: _url,
			//Tha jQuery AJAX approach does not match very well with Spring mvc's controller :-(
//			type:'DELETE',
			type:'POST',
			success: function(e){
				console.log('Done! :-)');
			},
			error:function(e){
				console.error('Error! :-( ' + e);
				var log = ''
				for(var p in e){
					log += e[p] + '\n';
				}
				console.error(log);
			}
		});
	});
});

