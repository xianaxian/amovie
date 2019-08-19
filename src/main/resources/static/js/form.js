"use strict";

//Plaeholder handler
$(function () {

    if (!Modernizr.input.placeholder) {             //placeholder for old brousers and IE

        $('[placeholder]').focus(function () {
            var input = $(this);
            if (input.val() == input.attr('placeholder')) {
                input.val('');
                input.removeClass('placeholder');
            }
        }).blur(function () {
            var input = $(this);
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.addClass('placeholder');
                input.val(input.attr('placeholder'));
            }
        }).blur();
        $('[placeholder]').parents('form').submit(function () {
            $(this).find('[placeholder]').each(function () {
                var input = $(this);
                if (input.val() == input.attr('placeholder')) {
                    input.val('');
                }
            })
        });
    }

    $('#contact-form').submit(function (e) {

        e.preventDefault();
        var error = 0;
        var self = $(this);

        var $name = self.find('[name=user-name]');
        var $email = self.find('[type=email]');
        var $message = self.find('[name=user-message]');


        var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

        if (!emailRegex.test($email.val())) {
            createErrTult('Error! Wrong email!', $email);
            error++;
        }

        if ($name.val().length > 1 && $name.val() != $name.attr('placeholder')) {
            $name.removeClass('invalid_field');
        } else {
            createErrTult('Error! Write your name!', $name);
            error++;
        }

        if ($message.val().length > 2 && $message.val() != $message.attr('placeholder')) {
            $message.removeClass('invalid_field');
        } else {
            createErrTult('Error! Write message!', $message);
            error++;
        }


        if (error != 0) return;
        self.find('[type=submit]').attr('disabled', 'disabled');

        self.children().fadeOut(300, function () {
            $(this).remove()
        });
        $('<p class="success"><span class="success-huge">Thank you!</span> <br> your message successfully sent</p>').appendTo(self)
            .hide().delay(300).fadeIn();


        var formInput = self.serialize();
        $.post(self.attr('action'), formInput, function (data) {
        }); // end post
    }); // end submit

    $('.login').submit(function (e) {
        e.preventDefault();
        var error = 0;
        var self = $(this);

        var $email = self.find('[type=email]').val();
        var $pass = self.find('[type=password]');


        var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

        //校验正则
        if (!emailRegex.test($email)) {
            createErrTult("Error! Wrong email!", $email);
            error++;
            //不对，error+1；
        }
        //密码校验，长度大于一，
        if ($pass.val().length > 1) {
            $pass.removeClass('invalid_field');
        } else {
            //错误，error+1
            createErrTult('Error! Wrong password!', $pass);
            error++;
        }
        if (error !== 0) {
            return;
        }


        //禁用登陆按钮，等待结果返回
        self.find('[type=submit]').attr('disabled', 'disabled');
        var formInput = self.serialize();

        $.post('/user/login', formInput)
        //定义获取结果成功的函数
            .success(data => {
                alert(data.message);
                if (data.code !== 200) {
                    return;
                }
                //定义登陆成功的界面字符串
                let successMsg = `<p class="login__title">sign in <br><span class="login-edition">welcome to A.Movie</span></p><p class="success">You have successfully<br> signed in!</p>`;
                //删除登录输入框
                self.children().fadeOut(300, function () {
                    $(this).remove()
                });
                $(successMsg).appendTo(self).hide().delay(300).fadeIn();

                setTimeout(()=>{
                    window.location.href='/';
                },300);
            })
            //定义网络错误的函数
            .error(() => alert("网络错误"))
            //成功或者失败都调用的函数，启用按钮
            .complete(() => self.find('[type=submit]').removeAttr('disabled'));
    });


    function createErrTult(text, $elem) {
        $elem.focus();
        $('<p />', {
            'class': 'inv-em alert alert-danger',
            'html': '<span class="icon-warning"></span>' + text + ' <a class="close" data-dismiss="alert" href="#" aria-hidden="true"></a>',
        })
            .appendTo($elem.addClass('invalid_field').parent())
            .insertAfter($elem)
            .delay(4000).animate({'opacity': 0}, 300, function () {
            $(this).slideUp(400, function () {
                $(this).remove()
            })
        });
    }
});
