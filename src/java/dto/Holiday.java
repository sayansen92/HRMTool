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
public class Holiday extends ActionSupport{

    private int holidayId;
    private String holidayName;
    private String holidayDate;
    private String repeatAnnually;
    private String holidayType;
    private ArrayList<Holiday> holidayList = new ArrayList<Holiday>();
    private String holidayFromDate;
    private String holidayToDate;
    private Holiday holiday;
    private String holidayIdList;
    private int leavePeriodId;
    
    /**
     * @return the holidayId
     */
    public int getHolidayId() {
        return holidayId;
    }

    /**
     * @param holidayId the holidayId to set
     */
    public void setHolidayId(int holidayId) {
        this.holidayId = holidayId;
    }

    /**
     * @return the holidayName
     */
    public String getHolidayName() {
        return holidayName;
    }

    /**
     * @param holidayName the holidayName to set
     */
    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

        /**
     * @return the repeatAnnually
     */
    public String getRepeatAnnually() {
        return repeatAnnually;
    }

    /**
     * @param repeatAnnually the repeatAnnually to set
     */
    public void setRepeatAnnually(String repeatAnnually) {
        this.repeatAnnually = repeatAnnually;
    }

    /**
     * @return the holidayType
     */
    public String getHolidayType() {
        return holidayType;
    }

    /**
     * @param holidayType the holidayType to set
     */
    public void setHolidayType(String holidayType) {
        this.holidayType = holidayType;
    }

    /**
     * @return the holidayList
     */
    public ArrayList<Holiday> getHolidayList() {
        return holidayList;
    }

    /**
     * @param holidayList the holidayList to set
     */
    public void setHolidayList(ArrayList<Holiday> holidayList) {
        this.holidayList = holidayList;
    }

    /**
     * @return the holidayDate
     */
    public String getHolidayDate() {
        return holidayDate;
    }

    /**
     * @param holidayDate the holidayDate to set
     */
    public void setHolidayDate(String holidayDate) {
        this.holidayDate = holidayDate;
    }

    /**
     * @return the holidayFromDate
     */
    public String getHolidayFromDate() {
        return holidayFromDate;
    }

    /**
     * @param holidayFromDate the holidayFromDate to set
     */
    public void setHolidayFromDate(String holidayFromDate) {
        this.holidayFromDate = holidayFromDate;
    }

    /**
     * @return the holidayToDate
     */
    public String getHolidayToDate() {
        return holidayToDate;
    }

    /**
     * @param holidayToDate the holidayToDate to set
     */
    public void setHolidayToDate(String holidayToDate) {
        this.holidayToDate = holidayToDate;
    }

    public String saveHoliday() {
        Holiday holiday = new Holiday();
             
        holiday.setHolidayName(getHolidayName());
        holiday.setHolidayDate(getHolidayDate());
        holiday.setHolidayType(getHolidayType());
        holiday.setLeavePeriodId(getLeavePeriodId());
        holiday.setRepeatAnnually(getRepeatAnnually());
        if(LeaveDao.saveHoliday(holiday))
        {
            return "success";
        }
      return "failure";

    }

    public String searchHoliday() {
       
        Holiday holiday=new Holiday();
        holiday.setHolidayFromDate(getHolidayFromDate());
        holiday.setHolidayToDate(getHolidayToDate());

        setHolidayList(LeaveDao.showHolidays(holiday));
        boolean retval = holidayList.isEmpty();
        if (retval == true) {
            
            return "failure";
        } else {
            
            return "success";
        }
    }
    public String getOneHoliday(){

     setHoliday(LeaveDao.getOneHoliday(getHolidayId()));     
     return "success";
      
    }

    /**
     * @return the holiday
     */
    public Holiday getHoliday() {
        return holiday;
    }

    /**
     * @param holiday the holiday to set
     */
    public void setHoliday(Holiday holiday) {
        this.holiday = holiday;
    }
   public String updateHoliday() {
        Holiday holiday = new Holiday();
        holiday.setHolidayId(getHolidayId());
        holiday.setHolidayName(getHolidayName());
        holiday.setHolidayDate(getHolidayDate());
        holiday.setHolidayType(getHolidayType());
        holiday.setRepeatAnnually(getRepeatAnnually());
        LeaveDao.saveHoliday(holiday);
        setHoliday(LeaveDao.getOneHoliday(holiday.getHolidayId()));     
        return "success";

    } 
   
   public String deleteHoliday()
   {
   String[] holidayIdsList = holidayIdList.split(",");
        int n = holidayIdsList.length;
        int holidayIds[] = new int[n];
        for (int i = 0; i < n; i++) {
            holidayIds[i] = Integer.parseInt(holidayIdsList[i]);}
                  
        if (LeaveDao.deleteHoliday(holidayIds)) {
            String result=searchHoliday();
            
            return result;
        }
        return "Failure";
        
   }

    /**
     * @return the holidayIdList
     */
    public String getHolidayIdList() {
        return holidayIdList;
    }

    /**
     * @param holidayIdList the holidayIdList to set
     */
    public void setHolidayIdList(String holidayIdList) {
        this.holidayIdList = holidayIdList;
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

    

    
    
}
