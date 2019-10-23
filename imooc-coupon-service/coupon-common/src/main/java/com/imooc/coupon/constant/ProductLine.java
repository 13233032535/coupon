package com.imooc.coupon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum ProductLine {

    DAMAO("大猫",1),
    DABAO("大包",2);

    /** 产品线描述 */
    private String description;

    /** 产品线编码 */
    private Integer code;

    public static ProductLine of(Integer code){
        return Stream.of(values())
                .filter(bean->bean.equals(code))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException(code+"not exists"));
    }
}
