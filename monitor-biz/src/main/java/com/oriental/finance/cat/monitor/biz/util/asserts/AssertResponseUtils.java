package com.oriental.finance.cat.monitor.biz.util.asserts;

import com.oriental.finance.cat.monitor.common.enums.system.BizCodeEnum;
import com.oriental.finance.cat.monitor.common.exception.BizException;
import com.oriental.finance.cif.common.model.Response;

/**
 * 结果断言处理 TODO 异常描述暂时未加
 * <p/>
 * <ul>
 * <li>校验远程调用Response   checkResponse</li>
 * </ul>
 *
 * @author User:lyd Date:2015/5/27 Time:9:12
 * @version 1.6
 * @since 1.0.0
 */
public final class AssertResponseUtils {

    /**
     * 校验远程调用Response
     * @param response          远程调用响应
     */
    public static void checkResponse(Response response){
        if(null == response){
            throw new BizException(BizCodeEnum.BIZ_CODE_500101);
        }
        if(!response.isSuccess()){
            throw new BizException(response.getResponseCode(),response.getResponseDesc());
        }
    }

    /**
     * 验证外部响应
     *
     * @param response            响应信息
     * @param isCheckResultIsNull 是否验证响应结果为空 true：验证 false：不验证
     */
    public static void checkResponse(Response response, boolean isCheckResultIsNull) {
        if (response == null) {
            throw new BizException(BizCodeEnum.BIZ_CODE_500101);
        }
        if (!response.isSuccess()) {
            throw new BizException(response.getResponseCode(), response.getResponseDesc());
        }
        if (isCheckResultIsNull && null == response.getResult()) {
            throw new BizException(BizCodeEnum.BIZ_CODE_404002);
        }
    }

}
