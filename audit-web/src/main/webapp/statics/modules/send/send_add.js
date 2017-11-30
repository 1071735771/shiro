/**
 * Created by 12106 on 2016-06-29.
 */
$(function(){
   $("#inputForm").validate({
      rules:{
          receiverEmail:{
              checkPort:true
          }
      },
       messages: {
           receiverEmail: {required: "请填写正确的邮箱地址！", remote: jQuery.format("请填写正确的邮箱地址！")},
       },
       errorElement:'div',
       errorPlacement : function(error, element) {
           if (element.is(":checkbox")
               || element.is(":radio")
               || element.parent().is(
                   ".input-append")) {
               error.appendTo(element.parent()
                   .parent());
           } else {
               // validateMessage(error[0].innerText,element);
               error.insertAfter(element);
           }
       }
   })
});
