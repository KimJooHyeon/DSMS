// Student
$('.manage-nav > .admin-open').on('click', function() {
  $('.manage-nav > .admin-open').toggleClass('clicked')

  $('.admin-nav-wrap').toggleClass('nav-hidden')
})

$('.manage-nav > .student-open').on('click', function() {
  $('.manage-nav > .student-open').toggleClass('clicked')

  $('.student-nav-wrap').toggleClass('nav-hidden')
})