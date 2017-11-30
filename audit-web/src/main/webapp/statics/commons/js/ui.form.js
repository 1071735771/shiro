(function ($) {

    var UIForm = function (element, options) {
        this.$this = $(element);
        this.$this.bind("submit", $.proxy(this.submit, this));
        this.id = this.$this.attr("id") == undefined ? "from_" + $.fn.UI.uuid(5) : this.$this.attr("id"); //随机生成id
        this.$this.attr("id", this.id);
        this.options = options || {
                layout: {
                    auto: true,
                    label: "col-md-3",
                    input: "col-md-6",
                    button: "col-md-offset-3 col-md-9"
                },
                validate: {}
            };
        this.validateOption = $.extend({
            errorElement : "div",
            errorClass: 'error col-xs-2',
            errorPlacement : function(error, element) {
                error.insertAfter(element.parents('.control-input')[0]);
            }
        }, this.options.validate);
        //添加必填输入标志
        $(".required",this.$this).parents(".control-input").prev().append('<span style="color:red">*</span>');
        //自动布局
        if (this.options.layout.auto) {
            this.autoLayout();
        }
    };

    var re = /col-(md|xs|lg|sm)-\d/;

    UIForm.prototype.autoLayout = function () {
        $(".control-label", this.$this).each($.proxy(function (idx, item) {
            var $this = $(item);
            if ( !re.test($this.attr("class"))) {
                $this.addClass(this.options.layout.label);
            }
        }, this));
        $(".control-input", this.$this).each($.proxy(function (idx, item) {
            var $this = $(item);
            if ( !re.test($this.attr("class"))) {
                $this.addClass(this.options.layout.input);
            }
        }, this));
        $(".form-button", this.$this).each($.proxy(function (idx, item) {
            var $this = $(item);
            if (!re.test($this.attr("class"))) {
                $this.addClass(this.options.layout.button);
            }
        }, this));
    };

    UIForm.prototype.validate = function () {
        this.$this.validate(this.validateOption);
        return this.$this.valid();
    };

    UIForm.prototype.submit = function () {
        if (!this.validate()) {
            return false;
        }
        var flag = confirm("确认提交吗？");
        if(!flag){
            return false;
        }
        if (this.$this.hasClass("ajaxForm")) {
            $.ajax({
                url: this.$this.attr("action"),
                data: this.$this.uiSerializeForm(),
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    if (data.type == 'refresh' || data.type=="data") {
                        parent.addMessage("success",data.data);
                        //关闭弹出层
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    }else if(!data.success){
                        parent.addMessage("error",data.data);
                    }
                }
            });
            return false;
        }
        return true;
    };

    $.fn.UIForm = function (options) {
        var t = options;
        return this.map(function () {
            var $this = $(this);
            var data = $this.data('ui.form');
            if (!data) $this.data('ui.form', (data = new UIForm(this, t)));
            return data;
        })
    };

    $.fn.UI = {
        uuid: function (number) {
            return new Array(number).join("x").replace(/[xy]/g, function (c) {
                var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
                return v.toString(16);
            });
        }
    };
})(jQuery);
