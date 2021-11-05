const originTel = $('#user-phone').val()
const originMail = $('#user-email').val()
let licenseCk = false

$('.phone-icon-wrap').on('click', function() {
	$('#user-phone').attr('disabled', false)
	$('#user-license').attr('disabled', false)
})

$('.email-icon-wrap').on('click', function() {
	$('#user-email').attr('disabled', false)
})

$('#user-pw').on('keyup', function() {
	const userPw = $('#user-pw').val()
	const text = $('.pass-text')
	const reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,20}$/g
	const icon = $('.pw-icon-wrap img')

	if (userPw === "") {
		text.html('비밀번호는 8~20자 영어, 숫자, 특수문자를 조합해야 합니다.').css('color', 'black')
		icon.attr('src', '../storage/img/ckIcon.png')
	} else if (reg.test(userPw)) {
		text.html('사용 가능한 비밀번호 입니다.').css('color', 'black')
		icon.attr('src', '../storage/img/ckIconOk.png')
		pwChek = true
	} else {
		text.html('형식에 맞게 입력해 주세요').css('color', 'red')
		icon.attr('src', '../storage/img/ckIconRed.png')
	}
})

$('#user-pw-ck').on('keyup', function() {
	const userPw = $('#user-pw-ck').val()
	const text = $('.pass-ck-text')
	const reg = $('#user-pw').val()
	const icon = $('.pw-ck-icon-wrap img')
	pwDoubleCheck = false

	if (userPw === "") {
		text.html('비밀번호를 한번 더 입력해 주세요.').css('color', 'black')
		icon.attr('src', '../storage/img/ckIcon.png')
	} else if (reg === userPw) {
		text.html('일치 합니다.').css('color', 'black')
		icon.attr('src', '../storage/img/ckIconOk.png')
		pwDoubleCheck = true
	} else {
		text.html('비밀번호가 일치하지 않습니다.').css('color', 'red')
		icon.attr('src', '../storage/img/ckIconRed.png')
	}
})

$('#user-phone').on('keyup', function() {
	const userPhone = $('#user-phone').val()
	const text = $('.phone-text')
	const reg = /^\d{3}\d{4}\d{4}$/
	const icon = $('.phone-icon-wrap img')

	if (userPhone === "") {
		text.html("'-'을 제외한 전화번호를 입력해 주세요").css('color', 'black')
		icon.attr('src', '../storage/img/ckIcon.png')
	} else if (reg.test(userPhone)) {
		$.ajax({
			url: "/member?cmd=phoneCheck.do",
			data: { phone: userPhone },
			method: "post",
			success: function(res) {
				if (res.trim() === 'true') {
					text.html('문자 인증을 진행해 주세요.').css('color', 'black')
					icon.attr('src', '../storage/img/ckIconOk.png')
				} else {
					text.html('이미 사용중인 핸드폰 번호 입니다.').css('color', 'red')
					icon.attr('src', '../storage/img/ckIconRed.png')
				}
			},
			error: function(e) {
				console.log(e)
			}
		})
	} else {
		text.html('전화번호 형식에 맞게 입력해 주세요.').css('color', 'red')
		icon.attr('src', '../storage/img/ckIconRed.png')
	}
})

$('.send-license').on('click', function() {
	const phoneNum = $('#user-phone').val()
	const text = $('.license-text')
	$('#sign-up-phone').attr('readonly', true)

	if (phoneNum !== "") {
		$.ajax({
			url: '/member?cmd=sendLicense.do',
			data: { 'phone': phoneNum },
			method: 'post',
			success: function() {
				$('.send-license').addClass('hidden')
				$('.ck-license').removeClass('hidden')
			},
			error: function(e) {
				console.log(e)
			}
		})
	} else {
		text.html('핸드폰 번호을 입력해 주세요.').css('color', 'red')
	}
})

$('.ck-license').on('click', function() {
	const license = $('#user-license').val()
	const text = $('.license-text')

	$.ajax({
		url: '/member?cmd=licenseCheck.do',
		data: {
			'license': license,
			'phone': $('#user-phone').val()
		},
		method: 'post',
		success: function(res) {
			if (res.trim() === 'true') {
				text.html('인증이 완료되었습니다.').css('color', 'black')
				$('.ck-license').addClass('hidden')
				$('.license-icon-wrap img').removeClass('hidden')
				$('#user-phone').attr('readonly', true)
				licenseCk = true
			} else {
				text.html('잘못 입력하셨습니다.').css('color', 'red')
			}
		},
		error: function(e) {
			console.log(e)
		}
	})
})

$('#user-email').on('keyup', function() {
	const userEmail = $('#user-email').val()
	const text = $('.email-text')
	const reg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
	const icon = $('.email-icon-wrap img')

	if (userEmail === "") {
		text.html('이메일을 입력해 주세요.').css('color', 'black')
		icon.attr('src', '../storage/img/ckIcon.png')
	} else if (reg.test(userEmail)) {
		$.ajax({
			url: "/member?cmd=emailCheck.do",
			data: { email: userEmail },
			method: "post",
			success: function(res) {
				if (res.trim() === 'true') {
					text.html('사용 가능한 이메일 입니다.').css('color', 'green')
					icon.attr('src', '../storage/img/ckIconOk.png')
				} else {
					text.html('이미 사용중인 이메일 입니다.').css('color', 'red')
					icon.attr('src', '../storage/img/ckIconRed.png')
				}
			},
			error: function(e) {
				console.log(e)
			}
		})
	} else {
		text.html('이메일 형식에 맞게 입력해 주세요.').css('color', 'red')
		icon.attr('src', '../storage/img/ckIconRed.png')
	}
})

$('.my-frm-btn').on('click', function() {
	const currentData = $('#my-frm').serialize();
	if(originTel.trim() !== $('#user-phone').val().trim() && !licenseCk) {
		alert('문자 인증을 진행해 주세요')
		return
	}
	$.ajax({
		url: "/member?cmd=modifyInfo.do",
		data: currentData,
		method: 'post',
		success: function(res) {
			if(res.trim() === 'true') {
				alert('변경 완료! 홈화면으로 돌아갑니다.')
				location.href = 'home?cmd=index'
			} else {
				alert('변경 실패... 다시 입력해 주세요.')
				location.reload
			}
		},
		error: function(e) {
			console.log(e)
		}
	})
})