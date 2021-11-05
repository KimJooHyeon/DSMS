if($(".file").length > 0) {
	const str = $('.file a').html()
	if(str.length > 5) {
		console.log(12)
		$('.file a').text(str.substring(0, 5) + "...")
	}
}