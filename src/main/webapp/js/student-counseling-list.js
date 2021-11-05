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

$('.list ul').on('click', $('.list ul li'), function(e) {
	const _data = {
		_name : e.target.innerText,
		_idx : e.target.getAttribute('data-idx')
	}
	
	$.ajax({
		url: '/student?cmd=counselDetail',
		data: _data,
		success: function(res) {
			let code = ``
			let s_idx
			$.each(res, function(i, e) {
				const c_idx = e["counsel-idx"]
				const m_idx = e["member-idx"]
				s_idx = e["student-idx"]
				const title = e.title
				const _date = e.date
				
				code += `<li>
							<a href="/student?cmd=goToDetail&idx=${c_idx}">
								<span>${title}</span>
								<span>${_date}</span>
							</a>
						</li>`
			})
			$('.items').html(code)
			$('.write-btn').attr('href', `/student?cmd=goToWrite&idx=${_data._idx}`)
		},
		error: function(e) {
			console.log(e.responseText)
		},
		dataType: 'JSON'
	})
})
