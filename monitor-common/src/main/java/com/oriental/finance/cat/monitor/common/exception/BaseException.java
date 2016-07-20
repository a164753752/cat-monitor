package com.oriental.finance.cat.monitor.common.exception;

import com.oriental.finance.cat.monitor.common.enums.system.BizCodeEnum;
import lombok.Data;

/**
 * 描述：异常基类
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/4 ProjectName:cat-monitor Version:
 */
@Data
public abstract class BaseException extends RuntimeException{

    protected BizCodeEnum bizCodeEnum;

    protected String bizCode;

    protected String bizDesc;

    public BaseException() {
        super();
    }

    public BaseException(String bizCode, String bizDesc) {
        super(bizCode +"-"+ bizDesc);
        this.bizCode = bizCode;
        this.bizDesc = bizDesc;
    }

    public BaseException(BizCodeEnum bizCodeEnum) {
        super(bizCodeEnum.getBizCode() + "-" + bizCodeEnum.getBizDesc());
        this.bizCode = bizCodeEnum.getBizCode();
        this.bizDesc = bizCodeEnum.getBizDesc();
    }

    public BaseException(String bizDesc) {
        super(bizDesc);
        this.bizDesc = bizDesc;
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String bizDesc, Throwable cause) {
        super(bizDesc, cause);
        this.bizDesc = bizDesc;
    }

    public BaseException(String bizCode, String bizDesc, Throwable cause) {
        this(bizCode +"-"+ bizDesc, cause);
        this.bizCode = bizCode;
        this.bizDesc = bizDesc;
    }

    /**
     * 获取完整异常信息
     * @return  异常信息
     */
    public String getFullExceptionInfo(){
        return getBizCode()+"-"+getBizDesc();
    }

}
