package com.leo.hogwarts.enums;

/**
 * @ClassName ResultCodeEnum
 * @Description
 * @Author Leo
 * @Date 2020/3/30 16:23
 */

public enum  ResultCodeEnum {
    /**
     * 返回状态码
     */
    SUCCESS(10000,"成功"),
    FAIL(10001,"失败")
    ;


    private Integer code;
    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultCodeEnum getInstance(Integer code){
        for (ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()){
            if (resultCodeEnum.code.equals(code)){
                return resultCodeEnum;
            }
        }
        return null;
    }
}
