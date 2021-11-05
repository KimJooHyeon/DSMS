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

$('.wrap button').on('click', function() {
	const _idx = $(this).attr('data-idx')
	const check = confirm('삭제하시겠습니까?')
	if(check) {
		$.ajax({
			url: '/student?cmd=counselDelDo',
			data: {
				idx: _idx 
			},
			success: function(res) {
				if(res.trim() === 'true') {
					alert('삭제 성공')
					location.replace('/student?cmd=cons')
				} else {
					alert('삭제 실패...')
				}
			},
			error: function(e) {
				console.log(e)
			}
		})
	}
})