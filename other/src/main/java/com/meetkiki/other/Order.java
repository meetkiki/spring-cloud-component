package com.meetkiki.other;

import java.util.List;

public class Order {

    private String orderNo; // 订单编码唯一标识
    private String status;  // 订单状态
    private List<OrderLine> orderLines;     // 订单详情
    private String receiveProvince;    // 省
    private String receiveCity;        // 市
    private String receiveCountry;     // 国家
    private String receiveStreet;      // 街道
    private String remark;  // 备注

    public static class OrderLine {
        // 订单详情省略...
    }

    // 地址的属性
    public static class ReceiveAddress {
        private String province;    // 省
        private String city;        // 市
        private String country;     // 国家
        private String street;      // 街道
    }
}
