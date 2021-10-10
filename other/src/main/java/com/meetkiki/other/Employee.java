package com.meetkiki.other;


import lombok.Value;

@Value
public class Employee {

    /**
     * 唯一标识
     */
    String id;

    /**
     * 对应职级的节点Id
     */
    String rankNodeId;

    /**
     * 其他属性 名字、部门等
     */
    String name;


}
