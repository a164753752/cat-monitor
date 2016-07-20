package com.oriental.finance.cat.monitor.common.enums.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：系统业务返回码枚举
 * 400  请求有误（格式错误）
 * 403  请求禁止（非格式错误）
 * 404  开头的错误码--找不到资源类（资源找不到，参数验证null,状态码异常等）
 * 500  开头的错误码--系统异常类  （系统级别异常错误码）
 * 200  成功、幂等结果
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum BizCodeEnum {

    /**
     * 200类
     */
    BIZ_CODE_200001("200001", "业务请求成功!"),

    //页面响应
    BIZ_CODE_200101(PageEnum.RESP_RESULT.getCode(), "缓存刷新成功!"),
    BIZ_CODE_200102(PageEnum.RESP_RESULT.getCode(), "添加成功!"),
    BIZ_CODE_200103(PageEnum.RESP_RESULT.getCode(), "修改成功!"),
    BIZ_CODE_200104(PageEnum.RESP_RESULT.getCode(), "删除成功!"),

    /**
     * 400类
     */
    BIZ_CODE_400001("400001", "参数有误!"),

    /**
     * 403类
     */
    BIZ_CODE_403001("403001", "DB操作被拒绝,请检查参数!"),
    BIZ_CODE_403002("403002", "任务被拒绝,请检查配置!"),
    BIZ_CODE_403003("403003", "任务被拒绝,请检查任务状态!"),
    BIZ_CODE_403004("403004", "脚本命令不存在,请检查脚本命令KEY!"),

    /**
     * 404类
     */
    BIZ_CODE_404001("404001", "数据资源不存在!"),
    BIZ_CODE_404002("404002", "响应结果为空!"),


    /**
     * 500类
     */
    //系统内部  0
    BIZ_CODE_500001("500001","系统内部错误!"),
    BIZ_CODE_500002("500002", "任务执行错误!"),
    //远程调用  1
    BIZ_CODE_500101("500101", "服务响应超时!"),

    ;

    private String bizCode;

    private String bizDesc;

    /**
     * 获取完整业务码信息
     * @param bizCodeEnum   业务码枚举
     * @return  业务码信息
     */
    public static String getFullBizInfo(BizCodeEnum bizCodeEnum){
        return bizCodeEnum.bizCode+"-"+bizCodeEnum.bizDesc;
    }

}
