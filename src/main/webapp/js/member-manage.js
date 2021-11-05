$('#allCheck').on('click', function() {
	if ($(this).prop('checked')) {
		$('[type="checkbox"]').prop('checked', true)
	} else {
		$('[type="checkbox"]').prop('checked', false)
	}
})

$('button').on('click', function() {

	const _data = []
	const checkbox = $('input:checkbox[name=idx]:checked')

	checkbox.each(function(i) {
		const tr = checkbox.parent().parent().eq(i)
		const td = tr.children()

		const s = td.eq(5)
		const _state = s.children().prop('value')
		const _idx = checkbox[i].value
		const result = {
			idx: _idx,
			state: _state
		}
		_data.push(result)
	})

	const _json = JSON.stringify(_data)

	$.ajax({
		url: '/member?cmd=manageModify',
		data: {
			json : _json	
		},
		success: function(res) {
			if(res.trim() === 'true') {
				alert('수정 완료!')
			}
		},
		error: function(e) {
			console.log(e)
		}
	})
})