let recruitGrid

$('#search-student').on('keyup', function() {
	const name = $('#search-student').val()

	$.ajax({
		url: "/student?cmd=search-student",
		data: {
			student: name
		},
		success: function(res) {
			let code = ""
			$.each(res, function(i, e) {
				const _name = e.name
				const _idx = e.idx
				code += `<li data-idx="${_idx}">- ${_name}</li>`
			})
			$('.list ul').html(code)
		},
		error: function(e) {
			console.log(e)
			console.log(e.responseText)
		},
		dataType: "JSON"
	})
})

$('.list ul').on('click', $('.list ul li'), function(e) {
	$('.modify-btn').removeClass('hidden')
	const idx = e.target.getAttribute('data-idx')
	$('#recruitGrid').html("")
	$.ajax({
		url: '/student?cmd=studentDetail',
		data: {
			studentIdx: idx
		},
		success: function(res) {
			
			const recruitData = []

			$.each(res, function(i, e) {
				const _idx = e.idx
				const _date = e.date
				const _in = e["in"]
				const _out = e.out
				const _div = e.division
				const _year = e.year
				const obj = {
					year: _year.substring(0,2),
					month: _date.substring(3, 5),
					day: _date.substring(6, 8),
					state: _div,
					'check-in': _in.substring(11, 16),
					'check-out': _out.substring(11, 16),
					idx: _idx
				}
				recruitData.push(obj)
			})

			

			recruitGrid = new Grid({
				el: document.getElementById('recruitGrid'),
				scrollX: true,
				columns: [

					{
						header: '년',
						name : 'year',
						hidden: true
					},
					{
						header: '월',
						name: 'month',
						align: 'center'
					},
					{
						header: '일',
						name: 'day',
						align: 'center'
					},
					{
						header: '출결 상태',
						name: 'state',
						editor: 'text',
						align: 'center'
					},
					{
						header: '입실 시간',
						name: 'check-in',
						editor: 'text',
						align: 'center'
					},
					{
						header: '퇴실 시간',
						name: 'check-out',
						editor: 'text',
						align: 'center'
					},
					{
						header: 'idx',
						name: 'idx',
						hidden: true
					}
				],
				data: recruitData
			});

			//recruitGrid.resetData(newData); 
			Grid.applyTheme('striped');
		},
		error: function(e) {
			console.log(e)
		},
		dataType: "JSON"
	})
})

$('.modify-btn').on('click', function() {
	const { rowKey, columnName } = recruitGrid.getFocusedCell();
	if (rowKey && columnName) {
		recruitGrid.finishEditing(rowKey, columnName);
	}
	const { updatedRows } = recruitGrid.getModifiedRows();
	
	for(const str of updatedRows) {
		delete str.rowKey
		delete str._attributes
	}

	console.log(JSON.stringify(updatedRows))
	
	$.ajax({
		url: '/student?cmd=studentDetailModify',
		data: {rows: JSON.stringify(updatedRows)},
		traditional: true,
		method: 'post',
		success: function(res) {
			if(res.trim() === 'true') {
				alert('수정 완료')
			}
		},
		error: function(e) {
			console.log(e)
		}
	})
})























