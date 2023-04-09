// Stickey header
$(document).ready(function(){
    $(window).scroll(function(){
      if($(this).scrollTop()){
        $('header').addClass('stickey');
      }else{
        $('header').removeClass('stickey');
      }
    });
  });

  var browserWindow = $(window);
 
  // :: 1.0 Preloader Active Code
  browserWindow.on('load', function () {
      $('.preloader').fadeOut('slow', function () {
          $(this).remove();
      });
  });
