/**
 * Created by lyd on 2016/7/12.
 */

/**
 * 信息modal模块
  * @type {{onDelete: Function}}
 */
var infoModalModule = {
    showDelete:function(id,title){
        $("#infoModalLabel").text("删除"+title);
        $("#infoModalBody").text("确认删除ID为 "+id+" 的信息？");
        $("#infoModalFoot input").val(id);
    },
    deleteBy:function(model){
        var id = $("#infoModalFoot input").val();
        $(function(){
            $.ajax({
                url: contextPath+"/"+model+"/delete",
                data:{id:id},
                success:function(data){
                    if(!data.success){
                        alert(data.responseCode+"-"+data.responseDesc);
                        return;
                    }
                    $("#tr_"+model+"_"+id).fadeOut();
                },
                error:function(){
                    $("#infoModalBody").text("请求超时,请重试!");
                }
            });
        });
    }
};

/**
 * quartz插件类
 * @type {{showQuartzExpression: Function, quartzExpression: Function}}
 */
var quartzClass = {
    /**
     * 显示quartz插件
     * @param reqUrl    跟路径
     */
    showQuartzExpression:function(){
        $("#quartzModalBody").load(contextPath+"/pages/layout/quartz.jsp");
    },
    /**
     * 生成表达式
     */
    quartzExpression:function(){
        $("#jobConfig_jobCronExpress").val($("#cron").val());
    }
};


/**
 * job配置模块
 * @type {{add: Function, modify: Function, showDelete: Function, deleteJob: Function}}
 */
var jobConfigModule = {
    add:function(){
        var jobConfig_jobName = $("#jobConfig_jobName");
        var jobConfig_jobGroup = $("#jobConfig_jobGroup");
        var jobConfig_jobClass = $("#jobConfig_jobClass");
        jobConfig_jobName.val("");
        jobConfig_jobGroup.val("");
        jobConfig_jobClass.val("");
        $("#jobConfig_jobCronExpress").val("");
        $("#jobConfig_jobCommandKey").val("");
        $("#jobConfig_retryTimes").val("0");
        $("#jobConfig_isClusters").val("NO");
        $("#jobConfig_jobStatus").val("UN_LOADING");
        jobConfig_jobName.attr("readonly",false);
        jobConfig_jobGroup.attr("readonly",false);
        jobConfig_jobClass.attr("readonly",false);
    },
    /**
     * 显示修改jobConfig信息
     * @param index    下标
     */
    modify:function(index){
        var jobConfig_jobName = $("#jobConfig_jobName");
        var jobConfig_jobGroup = $("#jobConfig_jobGroup");
        var jobConfig_jobClass = $("#jobConfig_jobClass");
        jobConfig_jobName.attr("readonly",true);
        jobConfig_jobGroup.attr("readonly",true);
        jobConfig_jobClass.attr("readonly",true);
        $("#jobConfig_id").val($("#id_"+index).val());
        jobConfig_jobName.val($("#jobName_"+index).text());
        jobConfig_jobGroup.val($("#jobGroup_"+index).text());
        jobConfig_jobClass.val($("#jobClass_"+index).attr("title"));
        jobConfig_jobClass.attr("title",jobConfig_jobClass.val());
        $("#jobConfig_jobCronExpress").val($("#jobCronExpress_"+index).text());
        $("#jobConfig_lastExecTime").val($("#lastExecTime_"+index).text());
        $("#jobConfig_nextExecTime").val($("#nextExecTime_"+index).text());
        $("#jobConfig_jobUsedTime").val($("#jobUsedTime_"+index).text());
        $("#jobConfig_jobCommandKey").val($("#jobCommandKey_"+index).text());
        $("#jobConfig_jobStatus").val($("#jobStatus_"+index).val());
        $("#jobConfig_retryTimes").val($("#retryTimes_"+index).val());
        $("#jobConfig_isClusters").val($("#isClusters_"+index).val());
        $("#jobConfigForm").attr("action",contextPath+"/jobConfig/update");
    },
    /**
     * 显示jobConfig信息
     * @param id    job主键
     */
    showDelete:function(id,title){
        infoModalModule.showDelete(id,title);
    },
    /**
     * 删除jobConfig信息
     */
    deleteJobConfig:function(){
        infoModalModule.deleteBy("jobConfig");
    }
};

/**
 * 脚本命令配置模块
 * @type {{modify: Function, showDelete: Function, delete: Function}}
 */
var scriptCommandConfigModule = {
    modify:function(index){
        $("#scriptCommandConfig_id").val($("#id_"+index).val());
        $("#scriptCommandConfig_commandKey").val($("#commandKey_"+index).text());
        $("#scriptCommandConfig_command").val($("#command_"+index).text());
        $("#scriptCommandConfigForm").attr("action",contextPath+"/scriptCommandConfig/update");
    },
    showDelete:function(id,title){
        infoModalModule.showDelete(id,title);
    },
    deleteScriptCommandConfig:function(){
        infoModalModule.deleteBy("scriptCommandConfig");
    }
};