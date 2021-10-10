package com.meetkiki.other;


import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class EmployeeDomainService {

    private final EmployeeRepository employeeRepository;

    private final NodeDomainService nodeDomainService;

    private final RankNodeFactory rankNodeFactory;

    public EmployeeDomainService(EmployeeRepository employeeRepository, NodeDomainService nodeDomainService, RankNodeFactory rankNodeFactory) {
        this.employeeRepository = employeeRepository;
        this.nodeDomainService = nodeDomainService;
        this.rankNodeFactory = rankNodeFactory;
    }

    /**
     * 入职员工
     * @param createEmployeeCommand
     * @return
     */
    public Employee create(CreateEmployeeCommand createEmployeeCommand) {
        RankNode node = nodeDomainService.create(rankNodeFactory.createNode());
        // 装换成员工
        Employee employee = Mapper.convertToEmployee(createEmployeeCommand, node);

        return employeeRepository.save(employee);
    }


    /**
     * 建立汇报关系
     * @param createEmployeeReportCommand 包含当前员工Id,上下级员工Id
     * @return
     */
    public Employee createEmployeeReport(CreateEmployeeReportCommand createEmployeeReportCommand) {
        // 查询当前操作员工
        final Employee employee = employeeRepository.findById(createEmployeeReportCommand.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("cannot find employee."));

        // 查询当前汇报员工
        final Employee reportEmployee = employeeRepository.findById(createEmployeeReportCommand.getReportEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("cannot find report employee."));

        // 绑定关系
        this.bindNodeRelation(employee, reportEmployee);

        // 查询下级
        final List<Employee> lowerEmployees = employeeRepository.findByIds(createEmployeeReportCommand.getLowerEmployeeIds());

        // 绑定下级汇报关系
        lowerEmployees.forEach(lowerEmployee -> this.bindNodeRelation(lowerEmployee, employee));

        return employeeRepository.save(employee);
    }

    /**
     * 员工离职
     * @param employeeResignCommand
     */
    public void employeeResign(EmployeeResignCommand employeeResignCommand) {
        // 查询当前操作员工
        final Employee employee = employeeRepository.findById(employeeResignCommand.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("cannot find employee."));

        // 查询当前汇报员工
        final RankNode parentRankNode = nodeDomainService.findById(employee.getRankNodeId()).getDirectParentRankNode();
        Employee reportEmployee = employeeRepository.findByRankId(parentRankNode.getId())
                .orElseThrow(() -> new IllegalArgumentException("cannot find report employee."));

        // 解除绑定关系
        this.releaseNodeRelation(employee, reportEmployee);

        employeeRepository.delete(employee);
    }


    /**
     * 绑定关联节点关系
     */
    private void bindNodeRelation(Employee employee, Employee reportEmployee) {
        nodeDomainService.bindNodeRelation(employee.getRankNodeId(), reportEmployee.getRankNodeId());
    }

    /**
     * 解除关联节点关系
     * @param employee
     * @param reportEmployee
     */
    private void releaseNodeRelation(Employee employee, Employee reportEmployee) {
        nodeDomainService.releaseNodeRelation(employee.getRankNodeId(), reportEmployee.getRankNodeId());
    }


}
