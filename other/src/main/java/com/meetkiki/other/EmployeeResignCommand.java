package com.meetkiki.other;

import lombok.Value;

/**
 * 员工离职命令
 *  转岗 = 离职 + 入职别岗位
 */
@Value
public class EmployeeResignCommand {

    String employeeId;

}
