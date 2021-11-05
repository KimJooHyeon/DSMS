$('#search-student').on('keyup', function() {
	$.ajax({
		url: '/student?cmd=search-counsel-name',
		data: {
			name: $(this).val()
		},
		success: function(res) {
			let code = ""
			$.each(res, function(i, e) {
				const _name = e.name
				const _idx = e.idx
				code += `<li data=idx${_idx}>${_name}</li>`
			})
			$('.list ul').html(code)
		},
		error: function(e) {
			console.log(e)
		},
		dataType: 'JSON'
	})
})

$('.write-btn').on('click', function() {
	const _data = {
		idx: $('#idx').val(),
		name: $('.student-name').val(),
		title: $('.title').val(),
		contents: editor.getHTML()
	}
	$.ajax({
		url: '/student?cmd=counselWriteDo',
		method: 'post',
		data: _data,
		success: function(res) {
			if(res.trim() === 'true') {
				alert('작성 성공!')
				location.replace('/student?cmd=cons')
			} else {
				alert('실패.. 다시 작성해 주세요.')
			}
		},
		error: function(e) {
			console.log(e)
		}
	})
})