<%@page import="vo.MemberVO"%>
<%@page import="vo.ChatRoomVO"%>
<%@page import="vo.ChatListVO"%>
<%@page import="vo.ChatDetailVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<ChatListVO> chatRoomList = (List<ChatListVO>) request.getAttribute("chatRoom");
List<MemberVO> memberList = (List<MemberVO>) request.getAttribute("memberList");
// int member_idx = (int) request.getSession().getAttribute("member_idx");
MemberVO mvo = (MemberVO) request.getSession().getAttribute("user");
int member_idx = mvo.getMember_idx();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>
<link rel="stylesheet" href="../css/chat.css" />
</head>
<body>
<div class="body__container">


	<jsp:include page="main-nav.jsp" flush="false">
  		<jsp:param name="job" value="${user.getMember_job()}"></jsp:param>
  	</jsp:include>
	
	<main>
		<section>
		
			<article class="list">
				<form action="">
					<input type="text" placeholder="대화방 검색" id="search-chat-room"/>
					<button type="button"><img src="../storage/img/addBtn.png" alt="add" /></button>
				</form>
					<div class="layer-wrap hidden">
			            <div class="pop-up">
			              <form action="" id="pop-up-frm"></form>
			              <ul class="mem-list">
			                <li><button type="button" id="create-room-btn">방 생성</button></li>
			                <%
			                for (MemberVO list : memberList) {
			                %>
			                <li>
			                  <label><input form="pop-up-frm" type="checkbox" id="member-check" name="name" value="<%=list.getMember_idx()%>"><%=list.getMember_name() %></label>
			                </li>
			                <%
							}
							%>
			              </ul>
			            </div>
			        </div>
				<ul class="chat-list-wrap">
				<%
				for (ChatListVO list : chatRoomList) {
				%>
					<li class="chat-list-item" name="<%=list.getChat_room_idx() %>" onclick="chatRoomIn(<%=list.getChat_room_idx() %>)">
						<span name="<%=list.getChat_room_idx() %>">- <%=list.getMember_name() %></span>
						<span name="<%=list.getChat_room_idx() %>" class="last-msg"><%=list.getChat_detail_content() %></span>
					</li>
				<%
				}
				%>
				</ul>
			</article>
			
			<article class="contents-wrap">
				<div class="user"></div>
				<div class="contents">
					<!-- 채팅 내용 -->
				</div>
				<form onsubmit="return false">
					<input type="text" id="chat-input" autocomplete="off" onkeyup="enterKey()"/>
					<button type="button" id="submit-btn" onclick="sendMessage()">전송</button>
<!-- 					<button type="button" id="submit-btn">전송</button> -->
				</form>
			</article>
			
		</section>
	</main>

</div>


<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/global.js"></script>
<!-- <script type="text/javascript" src="../js/chat.js"></script> -->

<script type="text/javascript">
let roomNumber;

// 	$('.chat-list-item').on('click', function() {
	$('.list ul').on('click', $('.chat-list-item'), function(e) {
		roomNumber = e.target.getAttribute('name')
		if(e.currentTarget.classList[0] === 'mem-list') {
			return;
		}
		$.ajax({
			url: '/chat?cmd=chattingDetail',
			data: {
				room : roomNumber
			},
			success: function(res) {
				let code = '<ul>';
				$.each(res, function(i) {
					let str = res[i].allMemberName;
					$('.user').html(str);
					
					if ("<%=member_idx%>" === res[i].memberIdx) {
						code += '<li class="me">'
					} else {
						code += '<li class="other">'
						code += 	'<span class="name">' + res[i].memberName + '</span>';
					}
				code +=		'<span class="item">' + res[i].chatDetailContent + '</span>';
				code +=		'<span class="date">' + res[i].chatDetailDate + '</span>';						
				code += '</li>';
				});
				code += '</ul>';
				$('.contents').html(code);
				contents.scrollTop = 9999;
// 				console.log(webSocket);
			},
			dataType : 'json'
		})
	})
	
	$('#search-chat-room').keyup(function(){
		let word = $(this).val()
		$.ajax({
			url : '/chat?cmd=searchChatRoom',
			data : {
				searchWord : word
			},
			success : function(res){
				let code = '';
				$.each(res, function(i) {
					code += '<li class="chat-list-item" name="' + res[i].chatRoomIdx + '" onclick="chatRoomIn(' + res[i].chatRoomIdx + ')">';
					code += '<span name="' + res[i].chatRoomIdx + '">- ' + res[i].memberName + '</span>';
					code += '<span name="' + res[i].chatRoomIdx + '" class="last-msg">' + res[i].chatDetailContent + '</span>';
					code += '</li>';
				});
				$('.list .chat-list-wrap').html(code);
			},
			error : function(e) {
				console.log(e)
			},
			dataType : 'json'
		});
	});
	
	// -------------------------------------------------------------------------------
	// 웹소켓
	// -------------------------------------------------------------------------------
	var webSocket = null; // 웹소켓 변수 선언
    var contents = document.querySelector(".contents"); // 채팅방 내용 출력되는 부분
   	var chatInput = document.querySelector("#chat-input"); // 채팅 내용 입력하는 부분
   	var userText = <%=member_idx%>
//    	var date = new Date();
//    	const formatDate = (current_datetime) => {
// 		let format_date = current_datetime.getFullYear() + "-" + (current_datetime.getMonth() + 1) + "-" + current_datetime.getDate() + " " + current_datetime.getHours() + ":" + current_datetime.getMinutes();
// 		return format_date;
//    	}

   	if(webSocket==null){
		connecting();
	}
   	
   	function connecting() {
   		//웹소켓 초기화
   	    webSocket = new WebSocket("ws://192.168.0.4:8088/webSocketChatting");
   	    // 처음 접속 성공하면 
   	    webSocket.onopen = function onOpen(event) {
			console.log('접속 성공 ~ ~ ')
   	        webSocket.send(createMessage("connect", "9999", userText));
   	    }
   	    
   	    //메시지가 오면 contents요소에 메시지를 추가한다.
   	    webSocket.onmessage = function processMessage(message) {
   	        //Json 풀기
   	        var jsonData = JSON.parse(message.data);
   	        if (jsonData.message != null) {
   	        	console.log(jsonData)
	   	        let msg = '';
				if (<%=member_idx%> === parseInt(jsonData.member_idx.trim())) {
// 		   	   		msg = '<li class="me">';
// 			   	   	msg +=       '<span class="item">'+ chatInput.val() +'</span><br>';
// 				    msg +=       '<span class="date">'+ currentTime.toLocaleTimeString() +'</span>';
				} else {
					msg += '<li class="other">';
				    msg +=       '<span class="name">' + jsonData.name + '</span>';
				    msg +=       '<span class="item">' + jsonData.message + '</span>';
				    msg +=       '<span class="date">' + jsonData.date + '</span>';
				}
			    msg +=    '</li>';
			    console.log(msg)
	    		$('.contents ul').append(msg);
	    		lastMsgPrint(roomNumber, $('[name="' + roomNumber + '"] .last-msg'));
   	            contents.scrollTop = 9999;
   	        };
   	    }

   	    webSocket.onerror = function showErrorMsg(event) {
   	        alert("오류 : " + event.data);
   	    }
   	    
	    webSocket.onclose = function(event){
			console.log(event);
	    	contents.value = "";
	        chatInput.value = "";
	    }
   	}

   	// 메시지 구조  {"command" : "명령종류", "room" : "채팅방이름", "message" : "메시지" }
   	// 명령 종류 : "create" - 채팅방 만들기, "change" - 채팅방 이동, "message" - 메시지 전송, "connect" - 처음 접속

   	//메시지 보내기
   	function sendMessage() {
   		if (chatInput.value.trim() == "") {
   	        chatInput.focus();
   	        return;
   	    }
   		
   	    var room = roomNumber
   	    const resultStr = chatInput.value.replaceAll('"', '\\\"');
   	    console.log('resultStr >> ' + resultStr);
   	    
   	    webSocket.send(createMessage("message", room, (resultStr + "." + room)));
   	 	let msg = '<li class="me">';
	   	msg +=       '<span class="item">'+ resultStr +'</span><br>';
// 	    msg +=       '<span class="date">'+ currentTime.toLocaleTimeString() +'</span>';
	    msg +=       '<span class="date">'+ new Date().toISOString().split("T")[0] + ' ' + new Date().toTimeString().split(" ")[0] +'</span>';
// 	    msg +=       '<span class="date">'+ formatDate(date) +'</span>';
	    msg += '</li>';
	    $('.contents ul').append(msg);
	    chatInput.value = "";
	    
	    lastMsgPrint(room, $('[name="' + room + '"] .last-msg'));
	    
	    const _data = {
	    	room_idx : roomNumber,
	    	msg : resultStr,
	    	mem_idx : "<%=member_idx%>"
	    }
	    
	    $.ajax({
	    	url: '/chat?cmd=saveMsg.Do',
	    	data : _data,
	    	success: function(res) {
	    		if(res.trim() === 'false') {
	    			alert('뭔가 알 수 없는 오류로 실패')
	    		}
	    	},
	    	error: function(e) {
	    		console.log(e)
	    	}
	    })
   	}

   	// 채팅방 이동
   	function chatRoomIn(roomNumber){
//    	    var selectRoom = document.querySelector(".chat-list-item");
//    	    if(selectRoom.selectedIndex==-1 || selectRoom.value==""){
//    	        alert("이동할 채팅방을 선택한 후 사용하세요.");
//    	        return;
//    	    }
// 		console.log(selectRoom.value)
		console.log('changeevent: '+roomNumber)
   	    webSocket.send( createMessage("change", roomNumber, null) );			
   	}   	
   	
   	// 전송할 메시지를 작성하는 함수
   	function createMessage(command, room, message) {
   	    return '{"command" : "' + command + '", "room" : "' + room + '", "message" : "' + message + '"}'
   	}

   	// 엔터키
   	function enterKey() {
   	    if (window.event.keyCode == 13) {
   	        sendMessage();
   	    }
   	}
   	
   	// layer-pop-up
   	$('.list form > button').on('click', function() {
   	  $('.layer-wrap').removeClass('hidden')
   	})

   	$('.layer-wrap').on('click', function(e) {
   	  if(e.target.classList[0] === 'layer-wrap') {
   	    $('.layer-wrap').addClass('hidden')
   	  }
   	})
   	
   	$('#create-room-btn').on('click', function(){
   		const frm = $('#pop-up-frm').serializeArray()
   		const _data = JSON.stringify(frm)
   		$.ajax({
    	  url: '/chat?cmd=createRoom',
    	  data: {
    		  json:_data
    	  },
    	  success: function(res) {
    		  if(res.roomNumber !== -1) {
    			  const room = res.roomNumber
    			  const name = res.name
    			  
    			  let code = '<li class="chat-list-item" name="' + room + '" onclick="chatRoomIn(' + room + ')">';
				  code += '<span name="' + room + '">- ' + name + '</span>';
				  code += '<span name="' + room + '" class="last-msg"></span></li>';
    			  $('.list > ul').append(code);
    			  $("input:checkbox[id='member-check']").prop("checked", false);
    			  $('.layer-wrap').addClass('hidden');
    		  } else {
    			  alert('서버에서 오류가 터졌을껄? 빨리 고쳐라 노예야..!!')
    		  }
    	  },
    	  error: function(e) {
    		  console.log(e)
    	  },
    	  dataType: 'JSON'
      })
   	});
   	
   	function lastMsgPrint(roomNum, el) {
   		$.ajax({
   			url: '/chat?cmd=getLastestChat',
   			data: {
   				"roomNum" : roomNum
   			},
   			success: function(res) {
   				const lastMsg = res.trim();
   				el[0].innerHTML = lastMsg;
   			},
   			error: function(e) {
   				console.log(e)
   			}
   		})
   	}
</script>
</body>
</html>