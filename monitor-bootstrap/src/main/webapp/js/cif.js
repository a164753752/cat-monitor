/**
 * cif应用监控脚本
 * Created by lyd on 2016/7/12.
 */

var cifSysStateModule = {
    /**
     * 获取数据源概况
     */
    getDataSourceState:function getDataSourceState(){
        $(function(){
            $.ajax({
                url:contextPath+"/cif/dataSource/get",
                dataType:"json",
                success:function(data){
                    if(!data.success){
                        $("#dataSourceModalBody").text(data.responseCode+"-"+data.responseDesc);
                        return;
                    }
                    var result = data.result;
                    $("#userName").val(result.userName);
                    var url = $("#url");
                    url.val(result.url);
                    url.attr("title",result.url);
                    var driverClassName = $("#driverClassName");
                    driverClassName.val(result.driverClassName);
                    driverClassName.attr("title",result.driverClassName);
                    $("#autoCommit").val(result.autoCommit);
                    $("#numActive").val(result.numActive);
                    $("#numIdle").val(result.numIdle);
                    $("#initialSize").val(result.initialSize);
                    $("#maxSize").val(result.maxSize);
                    $("#minIdle").val(result.minIdle);
                    $("#maxIdle").val(result.maxIdle);
                    $("#maxWait").val(result.maxWait);
                    $("#idleAliveTime").val(result.idleAliveTime);
                    $("#testOnBorrow").val(result.testOnBorrow);
                    $("#idleReleaseTime").val(result.idleReleaseTime);
                },
                error:function(){
                    $("#dataSourceModalBody").text("请求超时,请重试!");
                }
            })
        });
    },
    /**
     * 获取dubbo服务线程池概况
     */
    getProviderThreadState:function getProviderThreadState(){
        $(function(){
            $.ajax({
                url:contextPath+"/cif/providerThread/get",
                dataType:"json",
                success:function(data){
                    if(!data.success){
                        $("#providerThreadModalBody").text(data.responseCode+"-"+data.responseDesc);
                        return;
                    }
                    var result = data.result;
                    $("#port").val(result.port);
                    $("#poolSize").val(result.poolSize);
                    $("#corePoolSize").val(result.corePoolSize);
                    $("#maxPoolSize").val(result.maxPoolSize);
                    $("#largestPoolSize").val(result.largestPoolSize);
                    $("#activeCount").val(result.activeCount);
                    $("#taskCount").val(result.taskCount);
                    $("#completedTaskCount").val(result.completedTaskCount);
                    $("#queueSize").val(result.queueSize);
                    $("#keepAliveTime").val(result.keepAliveTime);
                    $("#allowReleaseCodeIdle").val(result.allowReleaseCodeIdle);
                },
                error:function(){
                    $("#providerThreadModalBody").text("请求超时,请重试!");
                }
            })
        });
    },
    /**
     * 连通性检查
     */
    connexityCheck:function(id){
        $(function(){
            $.ajax({
                url:contextPath+"/connexityCheck/handCheck",
                dataType:"json",
                data:{id:id},
                success:function(data){
                    var infoModalBody = $("#infoModalBody");
                    if(!data.success){
                        infoModalBody.text(data.responseCode+"-"+data.responseDesc);
                        return;
                    }
                    var result = data.result;
                    if("SUCCESS" == result){
                        $("#infoModalLabel").text("连通性检测正常");
                    }else{
                        $("#infoModalLabel").html("<p style='color:#ff6e00'>连通性检测异常</p>");
                    }
                    infoModalBody.text(result);
                },
                error:function(){
                    $("#infoModalBody").text("请求超时,请重试!");
                }
            })
        });
    }
};


