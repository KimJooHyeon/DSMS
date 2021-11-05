$('select').on('change', function() {
	$('#recruitGrid').html("")
	recruitGrid = new Grid({
		el: document.getElementById('recruitGrid'),
		scrollX: true,
		columns: [
			{
				header: '반',
				name: 'room',
				align: 'center'
			},
			{
				header: '이름',
				name: 'name',
				align: 'center'
			},
			{
				header: '담임 교사',
				name: 'teacher',
				align: 'center'
			},
			{
				header: '담임 전화번호',
				name: 'tel',
				editor: 'text'
			}
		],
		data: {
			contentType: 'application/json',
			api: {
				readData: {
					url: '/student?cmd=classRoomSet',
					method: 'get'
				}
			}
		}
	});

	//recruitGrid.resetData(newData); 
	Grid.applyTheme('striped');
})

$('.modify-btn').on('click', function() {
	const { rowKey, columnName } = recruitGrid.getFocusedCell();
	if (rowKey && columnName) {
		recruitGrid.finishEditing(rowKey, columnName);
	}

	const { updatedRows } = recruitGrid.getModifiedRows();
	console.log(updatedRows)
	
	for(const str of updatedRows) {
		delete str.rowKey
		delete str._attributes
	}
	
	$.ajax({
		url: '/student?cmd=modify',
		data: {rows: JSON.stringify(updatedRows)},
		traditional: true,
		method: 'post',
		success: function(res) {
			console.log(res)
		},
		error: function(e) {
			console.log(e)
		}
	})
	
})
