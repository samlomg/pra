/*
 一般常用功能函数
 */

//从URL中去除参数并请求相关控制器。
function getPartUrl(url) {
    var intpos;
    var tmpstr = "";
    url = url.replace("#", "");
    if (intpos > 0) //找到?
    {

        tmpstr = url.substr(0, intpos);
        return tmpstr;
    }
    else {
        return url;
    }
    return tmpstr;
}

//easyui datebox date format rule
function timeFormatter(date) {
    return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
}
function timeParser(date) {
    return new Date(Date.parse(date.replace(/-/g, "/")));
}


//显示窗口,支持模态，非模态
//mode :1 模态  0 非模态
//URL：新窗URL地址
//args:窗口参数传递
//height:高度
//width:宽度
//title:标题
//status:yes,no
function openwin(mode, url, args, height, width, title, status) {
    // var args = {"username" : username};
    var features = 'dialogHeight:' + height + 'px; dialogWidth:' + width + 'px; status:' + status + ';title:' + title;
    var result = window.showModalDialog(url, args, features);
    return result;
}

//获取浏览器高度      
function getClientHeight() {
    //浏览器时下窗口文档body的总高度 包括border padding margin
//          return($(document.body).outerHeight(true));          
    return ($(document).height() + 60);
}

//获取浏览器宽度
function getClientWidth() {
    //浏览器时下窗口文档body的总宽度 包括border padding margin
    //return ($(document.body).outerWidth(true));
    return ($(document).width() + 60);
}

//将JSON返回的内容根据类型转为对像
function ConvertReturnJsontoObject($json) {
    if (typeof($json) == "object") {
        return $json
    }
    else {
        return jQuery.parseJSON($json);
    }

}


//打开DIV式弹窗MODAL窗口,通过IFRAME
function showDivModalDialog(boxid, url, height, width, title) {
    /* TINY.box.show({
     iframe:url,
     boxid:'frameless',
     width:width,
     height:height,
     fixed:false,
     maskid:'bluemask',
     maskopacity:40
     });*/
//       TINY.box.show(
//        {
//        iframe:url,
//        boxid:boxid,width:width,height:height,fixed:false,maskid:'bluemask',
//        maskopacity:40,animate:false
//         });

    var features = 'dialogHeight:' + height + 'px; dialogWidth:' + width + 'px; status:' + status + ';title:' + title;
    var result = window.showModalDialog(url, window, features);
    return result;

}

//取得两位小数
function round2(Num1, Num2) {
    if (isNaN(Num1) || isNaN(Num2)) {
        return (0);
    } else {
        return (Num1.toFixed(Num2));
    }
}

//实现NULL判断并返回值
function StrIsNull(varvalue, deafultvalue) {
    if (varvalue == null) {
        return $.trim(deafultvalue);
    }
    else {
        return $.trim(varvalue);
    }
}

//获取日期格式的当天日期
function getCurrentDate() {
    var tmpdate = new Date();
    var strdate = tmpdate.getYear() + "-" + tmpdate.getMonth() + "-" + tmpdate.getDay();
    return strdate;


}

//实现NULL判断并返回数值
function NumIsNull(varValue, deafultvalue) {
    if ((varValue == undefined) || (varValue == null) || (varValue == "")) {
        return $.trim(deafultvalue);
    }
    else {
        return parseFloat(varValue);
    }
}

//预览打印
function preview(oper) {
    if (oper < 10) {
        bdhtml = window.document.body.innerHTML;//获取当前页的html代码
        sprnstr = "<!--startprint" + oper + "-->";//设置打印开始区域
        eprnstr = "<!--endprint" + oper + "-->";//设置打印结束区域
        prnhtml = bdhtml.substring(bdhtml.indexOf(sprnstr) + 18); //从开始代码向后取html

        prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));//从结束代码向前取html
        window.document.body.innerHTML = prnhtml;
        window.print();
        window.document.body.innerHTML = bdhtml;
    } else {
        window.print();
    }
}

//取得location后面的参数

function getUrlParam(name) {


//构造一个含有目标参数的正则表达式对象  
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
//匹配目标参数  
    var r = window.location.search.substr(1).match(reg);
//返回参数值  
    if (r !== null) return unescape(r[2]);
    return null;

}
//字符转浮点数
function convertstrtofloat(val) {
    var tmpstr;
    var tmprlt;
    tmpstr = StrIsNull(val);
    tmprlt = parseFloat(tmpstr);
    if (isNaN(tmprlt) == true)
        tmprlt = 0;
    return (tmprlt);

}
//生成随机数 
function getRandom(n) {
    var timestamp = (new Date()).valueOf();

    return timestamp;
}

//根据AJAX的返回的内容填充EASYUI GRID
function getDataWithAjax(url, param) {
    showBusyCursor("查询");
    $.ajax({
        type: "get", //使用get方法访问后台
        dataType: "json", //返回json格式的数据
        url: url, //要访问的后台地址
        data: param,//"optype=" +parseJSON optype + "&sequence=" + sequence, //要发送的数据
        success: function (rtnmsg) {
            if (rtnmsg != "") {
                var strObj = JSON.stringify(rtnmsg);
//        		  var rtnobj = eval("("+strObj+")");
//        		  var rtnobj = eval("("+rtnobj+")");
                var rtnobj = jQuery.parseJSON(strObj);
                showDataLocalProcess(rtnobj);//本地化处理
            }
            closeBusyCursor();
            return true;
        }
    });
    //ajax请求处理结束

}

//根据AJAX的返回的内容填充EASYUI GRID
function ajaxPost(url, param, syncs, functionName) {

    $.ajax({
        type: "get", //使用get方法访问后台
        dataType: "json", //返回json格式的数据
        url: url, //要访问的后台地址
        data: param,//"optype=" +parseJSON optype + "&sequence=" + sequence, //要发送的数据
        async: syncs,
        success: function (rtnmsg) {
            if (rtnmsg != "") {
                var strObj = JSON.stringify(rtnmsg);
//        		  var rtnobj = eval("("+strObj+")");
//        		  var rtnobj = eval("("+rtnobj+")");
                var rtnobj = jQuery.parseJSON(strObj);
                functionName(rtnobj);//本地化处理
            }
            return true;
        }
    });
    //ajax请求处理结束

}


//发送AJAX请求进行数据的删除，审核，反审核等JAAX操作
//仅返回操作结果，无明细数据返回。
// url 请求地址
//PARAM 参数行
//operdesc 为操作行为描述，如审批等。
function ProcdssDataWithAjax(url, param, Operdesc) {
    var strmsg = "您确定要" + Operdesc + "吗？";
    if (confirm(strmsg)) {
        showBusyCursor("处理");
        $.ajax({
            type: "post", //使用get方法访问后台
            dataType: "json", //返回json格式的数据
            url: url, //要访问的后台地址
            data: param,
            success: function (rtnmsg) {
                var strObj = JSON.stringify(rtnmsg);
//              var rtnobj = eval("("+strObj+")");
//              var rtnobj = eval("("+rtnobj+")"); 
                var rtnobj = jQuery.parseJSON(strObj);
                if (rtnobj.execResult) {
                    alert(Operdesc + "完成");
                    closeBusyCursor();
                    if (null != window.dialogArguments) {
                        window.dialogArguments.refreshdata(1, 10);
                        window.close();
                    } else {
                        refreshdata(1, 10); //调用发起方页面的本地刷新函数
                    }
                } else {
                    alert(Operdesc + "操作失败，失败原因为：" + rtnobj.msgList);
                }
                closeBusyCursor();
                return true;
            }
        });
        //ajax请求处理结束
    }
    ;

}

function getnum(num) {
    var aNew;
    var re = /([0-9]+\.[0-9]{2})[0-9]*/;
    aNew = num.replace(re, "$1");
    return aNew;
}


//清空TABLE所有明细行
function clearTableRows(table) {
    var tableid = "#" + table + " tr td";
    $(tableid).remove();
}

//更改HTML元素内容
function updateHtmlElemInner(elemid, html) {
    $(elemid).html(html);
}


function showBusyCursor(msg) {
    $("body").mask("正在" + msg + "，请稍等...");
}


function closeBusyCursor() {
    $("body").unmask();
}


/*
 seq              当一个页面多次调用不同文件上传时，加以区分，调用回调成功参数时也会
 返回该SEQ,方便处理
 url　　　　　　　　　　  上传处理程序地址。　　
 elemid　　　　　  需要上传的文件域的ID，即<input type="file">的ID。
 secureuri　　　　　　　 是否启用安全提交，默认为false。
 dataType　　　　　　　 服务器返回的数据类型。可以为xml,script,json,html。如果不填写，jQuery会自动判断。
 * */
function ajaxFileUpload(seq, url, elemid, secureuri, dataType) {

    $.ajaxFileUpload
    (
        {
            url: url, //用于文件上传的服务器端请求地址
            secureuri: secureuri, //是否需要安全协议，一般设置为false
            fileElementId: elemid, //文件上传域的ID
            dataType: dataType, //返回值类型 一般设置为json
            success: function (data, status)  //服务器成功响应处理函数
            {
                processUploadSuccess(seq, data, status);
            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                processUploadFailed(seq, data, status);
            }
        }
    )
    return false;
}

/*格式化货币或金额
 *s:字符串
 *n：小数位数
 *p:千分位符号，不需要可以放空串 
 * */

//格式化金额  
function fmoney(s, n, p) {
    n = n > 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";//更改这里n数也可确定要保留的小数位
    var l = s.split(".")[0].split("").reverse(),
        r = s.split(".")[1];
    t = "";
    for (i = 0; i < l.length; i++) {
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? p : "");
    }
    var tmp = s;
    if (t != null && t != NaN && t != null) {
        var tmp = t.split("").reverse().join("") + "." + r.substring(0, n);//保留2位小数  如果要改动 把substring 最后一位数改动就可
    }
    return tmp;
}

function timeStamp2String (time){
    if (time === "" || time === undefined || time === null){
        return " ";
    }
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1;
    var date = datetime.getDate();
    var hour = datetime.getHours();
    var minute = datetime.getMinutes();
    var second = datetime.getSeconds();
    var mseconds = datetime.getMilliseconds();
    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second+"."+mseconds;
};


function nullToSpace(source) {
    if (source === "" || source === undefined || source === null){
        return " ";
    }else{
        return source;
    }

}

function getStatus(code) {
    var name;
    switch(code)
    {
        case 1:
            name = "待收款";
            break;
        case 400:
            name = "已收款";
            break;
        case 920:
            name = "取消";
            break;
        default:
            name = "未知状态";
    }
    return name;
}