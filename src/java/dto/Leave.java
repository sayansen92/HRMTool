/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import com.opensymphony.xwork2.ActionSupport;
import dao.LeaveDao;
import java.util.*;


/**
 *
 * @author sayansen
 */
public class Leave extends ActionSupport {

//Leave Period members
    private int leavePeriodId;
    private String startDate;
    private String endDate;
    private String currentDate;
    private boolean currentLeavePeriodFlag;
    private int currentLeavePeriodId;
//    Leave Type members
    private int leaveTypeId;
    private String leaveTypeName;
    private int leaveBalance;
    private int employeeId;
    private String employeeName;
    private String paidLeaveType;
    private String carryForwardLeave;
    private String entitlementCap;
    private String encashable;
    private String leaveActionDate;
     private String leaveActionTime;
    private int startDay;
    private int endDay;
    private double leaveCount;
    private Leave leave;
    private String leaveStatus;
    private String leaveComment;
    private int leaveId;
    private String locationName;
    private String subUnitName;
    private double entitlements;
    private double pending;
    private double scheduled;
    private double taken;
    
    private ArrayList<Leave> leaveTypes, leaveList;

    public void saveLeavePeriod() {
        Leave leave = new Leave();
        leave.setStartDate(getStartDate());
        leave.setEndDate(getEndDate());
        leave.setCurrentLeavePeriodFlag(isCurrentLeavePeriodFlag());
        LeaveDao.insertLeavePeriod(leave);

    }

    public String showCurrentLeavePeriod() {
        String currDate = getCurrentDate();
        setLeave(LeaveDao.getCurrentLeavePeriod(currDate));

        if (leave.getLeavePeriodId() == 0) {
            return "failure";
        } else {
            return "success";
        }
    }

    public String showAllLeavePeriod() {
        setLeaveTypes(LeaveDao.showAllLeavePeriods());
        boolean retval = leaveTypes.isEmpty();
        if (retval == true) {

            return "failure";
        }
        return "success";

    }
    

    public String setLeaveType() {

        String result = "failure";
        Leave leave = new Leave();
        leave.setLeaveTypeName(getLeaveTypeName());
        leave.setPaidLeaveType(getPaidLeaveType());
        leave.setCarryForwardLeave(getCarryForwardLeave());
        leave.setEntitlementCap(getEntitlementCap());
        leave.setEncashable(getEncashable());
        
        if (LeaveDao.insertLeaveType(leave)) {

            setLeaveTypes(LeaveDao.displayLeaveTypes());
            result = "success";
        }
        return result;
    }

    public String showLeaveTypes() {

        setLeaveTypes(LeaveDao.displayLeaveTypes());
        boolean retval = leaveTypes.isEmpty();
        if (retval == true) {
            return "failure";
        } else {
            return "success";
        }
    }

    public void deleteLeavetypes() {

        String[] leaveTypes = leaveTypeName.split(",");
        int n = leaveTypes.length;
        int leaveTypeIds[] = new int[n];
        for (int i = 0; i < n; i++) {
            leaveTypeIds[i] = Integer.parseInt(leaveTypes[i]);
        }
        if (LeaveDao.deleteLeaveTypes(leaveTypeIds)) {
            showLeaveTypes();
        }

    }

    public String getOneLeaveType() {
        String result = "success";
        setLeave(LeaveDao.showOneLeaveType(getLeaveTypeId()));
        return result;

    }

    public String updateLeaveType() {

        String result = "failure";
        Leave leave = new Leave();
        leave.setLeaveTypeId(getLeaveTypeId());
        leave.setLeaveTypeName(getLeaveTypeName());
        leave.setPaidLeaveType(getPaidLeaveType());
        leave.setCarryForwardLeave(getCarryForwardLeave());
        leave.setEntitlementCap(getEntitlementCap());
        leave.setEncashable(getEncashable());
                
        if (LeaveDao.updateLeaveType(leave)) {

            setLeaveTypes(LeaveDao.displayLeaveTypes());
            result = "success";
        }
        return result;
    }

    public String calculateLeaveBal() {
        Leave leave = new Leave();
        leave.setEmployeeId(getEmployeeId());
        leave.setLeaveTypeId(getLeaveTypeId());
//        leave.setStartDate(getStartDate());
//        leave.setEndDate(getEndDate());
        setLeave(LeaveDao.calculateLeaveBal(leave));
        if (getLeave().getLeaveBalance() > 0) {
            return "success";
        }

        return "failure";
    }

    /**
     * @return the leave
     */
    public Leave getLeave() {
        return leave;
    }

    /**
     * @param leave the leave to set
     */
    public void setLeave(Leave leave) {
        this.leave = leave;
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
     * @return the leaveTypes
     */
    public ArrayList<Leave> getLeaveTypes() {
        return leaveTypes;
    }

    /**
     * @param leaveTypes the leaveTypes to set
     */
    public void setLeaveTypes(ArrayList<Leave> leaveTypes) {
        this.leaveTypes = leaveTypes;
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
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the currentDate
     */
    public String getCurrentDate() {
        return currentDate;
    }

    /**
     * @param currentDate the currentDate to set
     */
    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
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
     * @return the currentLeavePeriodFlag
     */
    public boolean isCurrentLeavePeriodFlag() {
        return currentLeavePeriodFlag;
    }

    /**
     * @param currentLeavePeriodFlag the currentLeavePeriodFlag to set
     */
    public void setCurrentLeavePeriodFlag(boolean currentLeavePeriodFlag) {
        this.currentLeavePeriodFlag = currentLeavePeriodFlag;
    }

    /**
     * @return the leaveBalance
     */
    public int getLeaveBalance() {
        return leaveBalance;
    }

    /**
     * @param leaveBalance the leaveBalance to set
     */
    public void setLeaveBalance(int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

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

    public String applyLeave() {
        Leave leave = new Leave();
        leave.setEmployeeId(getEmployeeId());
        leave.setLeaveTypeName(getLeaveTypeName());
        leave.setLeaveBalance(getLeaveBalance());
        leave.setStartDate(getStartDate());
        leave.setEndDate(getEndDate());
        leave.setLeaveCount(getLeaveCount());
        leave.setStartDay(getStartDay());
        leave.setEndDay(getEndDay());
        leave.setCurrentDate(getCurrentDate());
        if (LeaveDao.applyLeave(leave)) {
            return "success";
        }
        return "failure";
    }

    /**
     * @return the leaveCount
     */
    public double getLeaveCount() {
        return leaveCount;
    }

    /**
     * @param leaveCount the leaveCount to set
     */
    public void setLeaveCount(double leaveCount) {
        this.leaveCount = leaveCount;
    }

    /**
     * @return the startDay
     */
    public int getStartDay() {
        return startDay;
    }

    /**
     * @param startDay the startDay to set
     */
    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    /**
     * @return the endDay
     */
    public int getEndDay() {
        return endDay;
    }

    /**
     * @param endDay the endDay to set
     */
    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public String showLeaveList() {
        Leave leave=new Leave();
        leave.setStartDate(getStartDate());
        leave.setEndDate(getEndDate());
        leave.setLeaveStatus(getLeaveStatus());
        leave.setEmployeeName(getEmployeeName());
        leave.setSubUnitName(getSubUnitName());
//        if("".equals(leave.getLeaveStatus())){
//                System.out.println("empname="+leave.getEmployeeName()+"leave status="+leave.getLeaveStatus()+"subunit="+leave.getSubUnitName());
//
//        }
        setLeaveList(LeaveDao.showLeaveList(leave));
        if (leaveList.isEmpty()) {
            System.out.println("empty");
            return "failure";
        }
        return "success";
    }   

    /**
     * @return the leaveList
     */
    public ArrayList<Leave> getLeaveList() {
        return leaveList;
    }

    /**
     * @param leaveList the leaveList to set
     */
    public void setLeaveList(ArrayList<Leave> leaveList) {
        this.leaveList = leaveList;
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
     * @return the leaveStatus
     */
    public String getLeaveStatus() {
        return leaveStatus;
    }

    /**
     * @param leaveStatus the leaveStatus to set
     */
    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    /**
     * @return the leaveComment
     */
    public String getLeaveComment() {
        return leaveComment;
    }

    /**
     * @param leaveComment the leaveComment to set
     */
    public void setLeaveComment(String leaveComment) {
        this.leaveComment = leaveComment;
    }

    /**
     * @return the currentLeavePeriodId
     */
    public int getCurrentLeavePeriodId() {
        return currentLeavePeriodId;
    }

    /**
     * @param currentLeavePeriodId the currentLeavePeriodId to set
     */
    public void setCurrentLeavePeriodId(int currentLeavePeriodId) {
        this.currentLeavePeriodId = currentLeavePeriodId;
    }
    
    public String addComment(){
    
        setLeave(LeaveDao.addComment(getLeaveId()));
        
        return "success";
    }
    
    public String updateLeave(){
        Leave leave=new Leave();
        leave.setLeaveId(getLeaveId());
        leave.setLeaveComment(getLeaveComment());
        leave.setLeaveStatus(getLeaveStatus());
        leave.setLeaveActionDate(getLeaveActionDate());
        leave.setLeaveActionTime(getLeaveActionTime());
        if(LeaveDao.updateLeave(leave)){
            String result=showLeaveList();
            return result;
        }
        
    return "failure";
    }

    /**
     * @return the paidLeaveType
     */
    public String getPaidLeaveType() {
        return paidLeaveType;
    }

    /**
     * @param paidLeaveType the paidLeaveType to set
     */
    public void setPaidLeaveType(String paidLeaveType) {
        this.paidLeaveType = paidLeaveType;
    }

    /**
     * @return the carryForwardLeave
     */
    public String getCarryForwardLeave() {
        return carryForwardLeave;
    }

    /**
     * @param carryForwardLeave the carryForwardLeave to set
     */
    public void setCarryForwardLeave(String carryForwardLeave) {
        this.carryForwardLeave = carryForwardLeave;
    }

    /**
     * @return the entitlementCap
     */
    public String getEntitlementCap() {
        return entitlementCap;
    }

    /**
     * @param entitlementCap the entitlementCap to set
     */
    public void setEntitlementCap(String entitlementCap) {
        this.entitlementCap = entitlementCap;
    }

    /**
     * @return the encashable
     */
    public String getEncashable() {
        return encashable;
    }

    /**
     * @param encashable the encashable to set
     */
    public void setEncashable(String encashable) {
        this.encashable = encashable;
    }

    /**
     * @return the leaveId
     */
    public int getLeaveId() {
        return leaveId;
    }

    /**
     * @param leaveId the leaveId to set
     */
    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    /**
     * @return the leaveActionDate
     */
    public String getLeaveActionDate() {
        return leaveActionDate;
    }

    /**
     * @param leaveActionDate the leaveActionDate to set
     */
    public void setLeaveActionDate(String leaveActionDate) {
        this.leaveActionDate = leaveActionDate;
    }

    /**
     * @return the leaveActionTime
     */
    public String getLeaveActionTime() {
        return leaveActionTime;
    }

    /**
     * @param leaveActionTime the leaveActionTime to set
     */
    public void setLeaveActionTime(String leaveActionTime) {
        this.leaveActionTime = leaveActionTime;
    }
    
    public String viewLeaveReport(){
        Leave leave=new Leave();
        
        leave.setEmployeeName(getEmployeeName());
        leave.setLeavePeriodId(getLeavePeriodId());
        leave.setLeaveTypeId(getLeaveTypeId());
        leave.setLocationName(getLocationName());
        leave.setSubUnitName(getSubUnitName());
        
        setLeaveList(LeaveDao.viewLeaveReport(leave));
        if(leaveList.isEmpty()){
            
        return "failure";
        }
        
        return "success";
    }

    /**
     * @return the locationName
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * @param locationName the locationName to set
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    /**
     * @return the subUnitName
     */
    public String getSubUnitName() {
        return subUnitName;
    }

    /**
     * @param subUnitName the subUnitName to set
     */
    public void setSubUnitName(String subUnitName) {
        this.subUnitName = subUnitName;
    }

    /**
     * @return the entitlements
     */
    public double getEntitlements() {
        return entitlements;
    }

    /**
     * @param entitlements the entitlements to set
     */
    public void setEntitlements(double entitlements) {
        this.entitlements = entitlements;
    }

    /**
     * @return the pending
     */
    public double getPending() {
        return pending;
    }

    /**
     * @param pending the pending to set
     */
    public void setPending(double pending) {
        this.pending = pending;
    }

    /**
     * @return the scheduled
     */
    public double getScheduled() {
        return scheduled;
    }

    /**
     * @param scheduled the scheduled to set
     */
    public void setScheduled(double scheduled) {
        this.scheduled = scheduled;
    }

    /**
     * @return the taken
     */
    public double getTaken() {
        return taken;
    }

    /**
     * @param taken the taken to set
     */
    public void setTaken(double taken) {
        this.taken = taken;
    }
}
