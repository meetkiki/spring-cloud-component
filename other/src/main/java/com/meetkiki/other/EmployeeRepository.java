package com.meetkiki.other;


import java.util.List;
import java.util.Optional;

/**
 * 员工仓储
 */
public interface EmployeeRepository {

    /**
     * 持久化员工模型
     * @param employee
     * @return
     */
    Employee save(Employee employee);


    /**
     * 查询员工模型
     * @param employeeId
     * @return
     */
    Optional<Employee> findById(String employeeId);


    /**
     * 根据rankNodeId查询员工模型
     * @param rankNodeId
     * @return
     */
    Optional<Employee> findByRankId(String rankNodeId);

    /**
     * 批量查询员工模型
     * @param employeeId
     * @return
     */
    List<Employee> findByIds(List<String> employeeIds);

    /**
     * 删除员工
     * @param employeeId
     * @return
     */
    void delete(Employee employee);
}
