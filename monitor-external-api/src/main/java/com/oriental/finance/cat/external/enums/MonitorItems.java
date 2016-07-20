package com.oriental.finance.cat.external.enums;

import static com.oriental.finance.cat.external.enums.Platform.*;

/**
 * 描述：监控项枚举
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/11 ProjectName:cat-monitor Version: 1.0
 */
public enum MonitorItems {

    /**
     * 开户监控项
     */
    OPEN("OPEN",CIF,"开户"),
    /**
     * 绑卡协议申请项
     */
    AGREE_APPLY("AGREE_APPLY",CIF,"绑卡协议申请"),
    /**
     * 绑卡协议签约项
     */
    AGREE_SIGN("AGREE_SIGN",CIF,"绑卡协议签约"),

    ;

    private String code;

    private Platform platform;

    private String desc;

    MonitorItems(String code, String desc) {
        this.code = code;
        this.platform = Platform.CIF;
        this.desc = desc;
    }

    MonitorItems(String code, Platform platform, String desc) {
        this.code = code;
        this.platform = platform;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 是否包含指定监控项
     * @param items 指定监控项
     * @return  true包含|false不包含
     */
    public static boolean containItems(MonitorItems items){
        for(MonitorItems monitorItems : MonitorItems.values()){
            if(monitorItems == items){
                return true;
            }
        }
        return false;
    }
}
