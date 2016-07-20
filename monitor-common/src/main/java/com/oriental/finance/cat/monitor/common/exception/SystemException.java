package com.oriental.finance.cat.monitor.common.exception;

import com.oriental.finance.cat.monitor.common.enums.system.BizCodeEnum;
import lombok.AllArgsConstructor;

/**
 * 描述：系统异常类
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/4 ProjectName:cat-monitor Version:
 */
@AllArgsConstructor
public class SystemException extends BaseException {

    public SystemException(String bizCode, String bizDesc) {
        super(bizCode, bizDesc);
    }

    public SystemException(BizCodeEnum bizCodeEnum) {
        super(bizCodeEnum);
    }
}


