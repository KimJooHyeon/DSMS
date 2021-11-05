<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>

<link rel="stylesheet" href="../util/fullCalendar/vendor/css/fullcalendar.min.css" />
<link rel="stylesheet" href="../util/fullCalendar/vendor/css/bootstrap.min.css">
<link rel="stylesheet" href='../util/fullCalendar/vendor/css/select2.min.css' />
<link rel="stylesheet" href='../util/fullCalendar/vendor/css/bootstrap-datetimepicker.min.css' />

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,500,600">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<link rel="stylesheet" href="../util/fullCalendar/css/main.css">

<link rel="stylesheet" href="../css/schedule.css" />

</head>
<body>
<div class="body__container">

	<jsp:include page="main-nav.jsp" flush="false">
  		<jsp:param name="job" value="${user.getMember_job()}"></jsp:param>
  	</jsp:include>
	
	<main>
		<section>
		    
		    <!-- 일자 클릭시 메뉴오픈 -->
	        <div id="contextMenu" class="dropdown clearfix">
	            <ul class="dropdown-menu dropNewEvent" role="menu" aria-labelledby="dropdownMenu"
	                style="display:block;position:static;margin-bottom:5px;">
	                <li><a tabindex="-1" href="#">일반</a></li>
	                <li><a tabindex="-1" href="#">휴가</a></li>
	                <li><a tabindex="-1" href="#">출장</a></li>
	                <li><a tabindex="-1" href="#">공지</a></li>
	            </ul>
	        </div>
	
	        <div id="wrapper">
	            <div id="loading"></div>
	            <div id="calendar"></div>
	        </div>
	
	
	        <!-- 일정 추가 MODAL -->
	        <div class="modal fade" tabindex="-1" role="dialog" id="eventModal">
	            <div class="modal-dialog" role="document">
	                <div class="modal-content">
	                    <div class="modal-header">
	                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
	                                aria-hidden="true">&times;</span></button>
	                        <h4 class="modal-title"></h4>
	                    </div>
	                    <div class="modal-body">
	
	                        <div class="row">
	                            <div class="col-xs-12">
	                                <label class="col-xs-4" for="edit-title">일정명</label>
	                                <input class="inputModal" type="text" name="edit-title" id="edit-title"
	                                    required="required" />
	                            </div>
	                        </div>
	                        <div class="row">
	                            <div class="col-xs-12">
	                                <label class="col-xs-4" for="edit-start">시작</label>
	                                <input class="inputModal" type="text" name="edit-start" id="edit-start" />
	                            </div>
	                        </div>
	                        <div class="row">
	                            <div class="col-xs-12">
	                                <label class="col-xs-4" for="edit-end">끝</label>
	                                <input class="inputModal" type="text" name="edit-end" id="edit-end" />
	                            </div>
	                        </div>
	                        <div class="row">
	                            <div class="col-xs-12">
	                                <label class="col-xs-4" for="edit-type">구분</label>
	                                <select class="inputModal" type="text" name="edit-type" id="edit-type">
	                                    <option value="일반">일반</option>
	                                    <option value="휴가">휴가</option>
	                                    <option value="출장">출장</option>
	                                    <option value="공지">공지</option>
	                                </select>
	                            </div>
	                        </div>
	                        <div class="row">
	                            <div class="col-xs-12">
	                                <label class="col-xs-4" for="edit-color">중요도</label>
	                                <select class="inputModal" name="color" id="edit-color">
	                                    <option value="#D25565" style="color:#D25565;">상</option>
	                                    <option value="#a9e34b" style="color:#a9e34b;">중</option>
	                                    <option value="#74c0fc" style="color:#74c0fc;">하</option>
	                                </select>
	                            </div>
	                        </div>
	                        <div class="row">
	                            <div class="col-xs-12">
	                                <label class="col-xs-4" for="edit-desc">설명</label>
	                                <textarea rows="4" cols="50" class="inputModal" name="edit-desc"
	                                    id="edit-desc"></textarea>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="modal-footer modalBtnContainer-addEvent">
	                        <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
	                        <button type="button" class="btn btn-primary" id="save-event">저장</button>
	                    </div>
	                    <div class="modal-footer modalBtnContainer-modifyEvent">
	                        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
	                        <button type="button" class="btn btn-danger" id="deleteEvent">삭제</button>
	                        <button type="button" class="btn btn-primary" id="updateEvent">저장</button>
	                    </div>
	                </div><!-- /.modal-content -->
	            </div><!-- /.modal-dialog -->
	        </div><!-- /.modal -->
		</section>
	</main>


</div>


<script src="../util/fullCalendar/vendor/js/jquery.min.js"></script>
<script src="../util/fullCalendar/vendor/js/bootstrap.min.js"></script>
<script src="../util/fullCalendar/vendor/js/moment.min.js"></script>
<script src="../util/fullCalendar/vendor/js/fullcalendar.min.js"></script>
<script src="../util/fullCalendar/vendor/js/ko.js"></script>
<script src="../util/fullCalendar/vendor/js/select2.min.js"></script>
<script src="../util/fullCalendar/vendor/js/bootstrap-datetimepicker.min.js"></script>
<script src="../util/fullCalendar/js/main.js"></script>
<script src="../util/fullCalendar/js/addEvent.js"></script>
<script src="../util/fullCalendar/js/editEvent.js"></script>
<script src="../util/fullCalendar/js/etcSetting.js"></script>
    
<script type="text/javascript" src="../js/global.js"></script>

</body>
</html>