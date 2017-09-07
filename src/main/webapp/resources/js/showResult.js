/**
 * Created by LbcLT on 2017/2/18.
 */

function showBusyCursor(msg)
{
    $("body").mask("正在"+msg+"，请稍等...");
}


function closeBusyCursor()
{
    $("body").unmask();
}

function getData(url, param,tableId) {
    $.ajax({
        type: "get", //使用get方法访问后台
        dataType: "json", //返回json格式的数据
        url: url, //要访问的后台地址
        data: param,//"optype=" +parseJSON optype + "&sequence=" + sequence, //要发送的数据
        success: function (rtnmsg){
            if (rtnmsg!=""){
                var strObj=JSON.stringify(rtnmsg);
                var rtnobj = jQuery.parseJSON(strObj);
                showDataLocalProcess(rtnobj,tableId);//本地化处理
            }
            return true;
        }
    });
}
