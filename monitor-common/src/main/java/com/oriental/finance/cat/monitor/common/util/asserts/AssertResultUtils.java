package com.oriental.finance.cat.monitor.common.util.asserts;

import com.oriental.finance.cat.monitor.common.enums.system.BizCodeEnum;
import com.oriental.finance.cat.monitor.common.exception.DBException;

/**
 * 结果断言处理 TODO 异常描述暂时未加
 * <p/>
 * <ul>
 * <li>检查数据库记录操作数   checkUpdNum</li>
 * <li>检查数据库记录操作数   checkUpdZero</li>
 * <li>检查数据库记录结果   checkDBResult</li>
 * <li>检查数据库记录结果   checkUpdResultRtn</li>
 * <li>校验远程调用Response   checkResponse</li>
 * </ul>
 *
 * @author User:lyd Date:2015/5/27 Time:9:12
 * @version 1.6
 * @since 1.0.0
 */
public final class AssertResultUtils {

    /**
     * 检查数据库记录操作数
     *
     * @param updNum        更新数
     */
    public static void checkUpdNum(int updNum) {
        checkUpdZero(updNum) ;
        if (updNum > 1) {
            throw new DBException(BizCodeEnum.BIZ_CODE_403001);
        }
    }

    /**
     * 检查数据库记录操作数
     *
     * @param updNum        更新数
     */
    public static void checkUpdZero(int updNum) {
        if (updNum == 0) {
            throw new DBException(BizCodeEnum.BIZ_CODE_403001);
        }
    }

    /**
     * 检查数据库记录结果
     *
     */
    public static void checkDBResult(boolean result) {
        if (!result) {
            throw new DBException(BizCodeEnum.BIZ_CODE_403001);
        }
    }

    /**
     * 检查数据库记录结果
     *
     */
    public static boolean checkUpdResultRtn(boolean result) {
        checkDBResult(result);
        return result;
    }

    /**
     * 结果为true则抛指定错误码和描述的ValidateException
     */
    public static void checkTrueByException(boolean result,String errorCode,String errorMsg) {
        if(result){
            throw new DBException(errorCode,errorMsg);
        }
    }

}
