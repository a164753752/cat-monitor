package com.oriental.finance.cat.monitor.common.exception;

import com.oriental.finance.cat.monitor.common.enums.system.BizCodeEnum;
import lombok.Data;

/**
 * 描述：业务异常
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
@Data
public class BizException extends BaseException {

    public BizException() {
        super();
    }

    public BizException(BizCodeEnum bizCodeEnum) {
        super(bizCodeEnum);
    }

    public BizException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

}
