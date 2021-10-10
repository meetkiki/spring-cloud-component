package com.meetkiki.other;


import lombok.Value;

import java.util.List;

/**
 * 建立汇报关系命令
 *  如果关系不存在 则创建
 *  如果存在关系则更新
 */
@Value
public class CreateEmployeeReportCommand {

    String employeeId;

    String reportEmployeeId;

    List<String> lowerEmployeeIds;

}
