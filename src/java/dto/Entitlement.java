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
public class Entitlement {

    private int employeeId;
    private String employeeName;
    private int leaveTypeId;
    private String leaveTypeName;
    private int leavePeriodId;
    private String leavePeriodName;
    private float entitlements;
    private int entitlementId;
    private String locationId;
    private String subunitId;
    private Entitlement entitlement;
    private ArrayList<Entitlement> entitlementList;

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

    /**
     * @return the leaveTypeId
     */
    public int getLeaveTypeId() {
        return leaveTypeId;
    }

    /**
     * @param leaveTypeId the leaveTypeId to set
     */
    public void setLeaveTypeId(int leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    /**
     * @return the leavePeriodId
     */
    public int getLeavePeriodId() {
        return leavePeriodId;
    }

    /**
     * @param leavePeriodId the leavePeriodId to set
     */
    public void setLeavePeriodId(int leavePeriodId) {
        this.leavePeriodId = leavePeriodId;
    }

    /**
     * @return the entitlements
     */
    public float getEntitlements() {
        return entitlements;
    }

    /**
     * @param entitlements the entitlements to set
     */
    public void setEntitlements(float entitlements) {
        this.entitlements = entitlements;
    }

    /**
     * @return the entitlementId
     */
    public int getEntitlementId() {
        return entitlementId;
    }

    /**
     * @param entitlementId the entitlementId to set
     */
    public void setEntitlementId(int entitlementId) {
        this.entitlementId = entitlementId;
    }

    /**
     * @return the entitlement
     */
    public Entitlement getEntitlement() {
        return entitlement;
    }

    /**
     * @param entitlement the entitlement to set
     */
    public void setEntitlement(Entitlement entitlement) {
        this.entitlement = entitlement;
    }

    /**
     * @return the entitlementList
     */
    public ArrayList<Entitlement> getEntitlementList() {
        return entitlementList;
    }

    /**
     * @param entitlementList the entitlementList to set
     */
    public void setEntitlementList(ArrayList<Entitlement> entitlementList) {
        this.entitlementList = entitlementList;
    }

    public String saveEntitlement() {
        Entitlement ent = new Entitlement();

        ent.setEmployeeId(getEmployeeId());
        ent.setEmployeeName(getEmployeeName());
        ent.setLeaveTypeId(getLeaveTypeId());
        ent.setLeavePeriodId(getLeavePeriodId());
        ent.setEntitlements(getEntitlements());
        ent.setLocationId(getLocationId());
        ent.setSubunitId(getSubunitId());
        if ("".equals(ent.getEmployeeName())) {
           
            if (LeaveDao.saveEntitlement(ent)) {
                return "success";
            }
        } else {
            
            if (LeaveDao.isEmployeeValid(ent.getEmployeeId(), ent.getEmployeeName())) {
                if (LeaveDao.saveEntitlement(ent)) {
                    return "success";
                }
            }

        }

        return "failure";
    }

    public String searchEntitlement() {

        Entitlement entitlement = new Entitlement();
        entitlement.setEmployeeName(getEmployeeName());
        entitlement.setLeaveTypeId(getLeaveTypeId());
        entitlement.setLeaveTypeName(getLeaveTypeName());
        entitlement.setLeavePeriodId(getLeavePeriodId());
        entitlement.setLeavePeriodName(getLeavePeriodName());
        setEntitlementList(LeaveDao.searchEntitlement(entitlement));

        if (entitlementList.isEmpty()) {
            return "failure";
        }
        return "success";

    }

    /**
     * @return the leaveTypeName
     */
    public String getLeaveTypeName() {
        return leaveTypeName;
    }

    /**
     * @param leaveTypeName the leaveTypeName to set
     */
    public void setLeaveTypeName(String leaveTypeName) {
        this.leaveTypeName = leaveTypeName;
    }

    /**
     * @return the leavePeriodName
     */
    public String getLeavePeriodName() {
        return leavePeriodName;
    }

    /**
     * @param leavePeriodName the leavePeriodName to set
     */
    public void setLeavePeriodName(String leavePeriodName) {
        this.leavePeriodName = leavePeriodName;
    }

    /**
     * @return the locationId
     */
    public String getLocationId() {
        return locationId;
    }

    /**
     * @param locationId the locationId to set
     */
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    /**
     * @return the subunitId
     */
    public String getSubunitId() {
        return subunitId;
    }

    /**
     * @param subunitId the subunitId to set
     */
    public void setSubunitId(String subunitId) {
        this.subunitId = subunitId;
    }

    

}
