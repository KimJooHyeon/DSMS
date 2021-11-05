$('select').on('change', function() {
	$('#recruitGrid').html("")
	recruitGrid = new Grid({
		el: document.getElementById('recruitGrid'),
		scrollX: true,
		columns: [
			{
				header: '이름',
				name: 'name',
				align: 'center'
			},
			{
				header: '나이',
				name: 'age',
				align: 'center'
			},
			{
				header: '반',
				name: 'room',
				align: 'center'
			},
			{
				header: '주소',
				name: 'addr',
				editor: 'text'
			},
			{
				header: '전화번호',
				name: 'tel',
				editor: 'text',
				align: 'center'
			},
			{
				header: '자격증',
				name: 'license',
				editor: 'text'
			},
			{
				header: '학력',
				name: 'education',
				editor: 'text',
				align: 'center'
			},
			{
				header: '초급 프로젝트',
				name: 'basic-project',
				editor: 'text',
				align: 'center'
			},
			{
				header: '중급 프로젝트',
				name: 'middle-project',
				editor: 'text',
				align: 'center'
			},
			{
				header: '최종 프로젝트',
				name: 'last-project',
				editor: 'text',
				align: 'center'
			},
			{
				header: '수료구분',
				name: 'graduate',
				editor: 'text',
				align: 'center'
			}
		],
		data: {
			contentType: 'application/json',
			api: {
				readData: {
					url: '/student?cmd=info&roomNumber=' + $('.room').val().substring(0, 3),
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
