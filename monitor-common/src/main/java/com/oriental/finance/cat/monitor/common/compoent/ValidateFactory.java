package com.oriental.finance.cat.monitor.common.compoent;

import com.oriental.finance.cat.monitor.common.enums.system.BizCodeEnum;
import com.oriental.finance.cat.monitor.common.exception.BizException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 描述：model校验工厂
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/18 ProjectName:cat-monitor Version: 1.0
 */
public final class ValidateFactory {

    /**
     * 校验工厂
     */
    private final static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    /**
     * 请求参数非空、格式验证，请求对象
     *
     * @param t 请求校验参数
     */
    public static <T> void validateModel(T t) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if (violations.size() == 0) return;
        for (ConstraintViolation<T> violation : violations){
            throw new BizException(BizCodeEnum.BIZ_CODE_400001.getBizCode(),violation.getMessage());
        }
    }

}
