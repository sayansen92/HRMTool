/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import dao.LeaveDao;
import java.util.ArrayList;

/**
 *
 * @author sayansen
 */
public class Employee {

    private int employeeId;
    private String employeeName;
    private Employee employee;
    private ArrayList<Employee> employeeList;
    private String location;
    private String subUnit;

    /**
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName the employeeName to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

//    public String validateEmployeeName() {
//        String name = getEmployeeName();
//        if (LeaveDao.isEmployeeValid(name)) {
//            System.out.println("Success");
//            return "success";
//        }
//
//        return "failure";
//    }
    public String employeeSearch() {
        String name = getEmployeeName();
        setEmployeeList(LeaveDao.searchEmployee(name));
        if (!employeeList.isEmpty()) {
            return "success";
        }
        return null;
    }

    public String fetchEmployeeName() {
        int id = getEmployeeId();
        
        setEmployee(LeaveDao.fetchEmployee(id));
        return "success";
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * @return the employeeList
     */
    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    /**
     * @param employeeList the employeeList to set
     */
    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the subUnit
     */
    public String getSubUnit() {
        return subUnit;
    }

    /**
     * @param subUnit the subUnit to set
     */
    public void setSubUnit(String subUnit) {
        this.subUnit = subUnit;
    }

}
