package com.meetkiki.other;

import lombok.Builder;
import lombok.Value;


/**
 * 创建员工命令
 */
@Value
@Builder
public class CreateEmployeeCommand {

    // 基础属性名字、部门、照片Id等
    String name;
}
