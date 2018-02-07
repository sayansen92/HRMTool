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
public class WorkWeek {

    private int dayId;
    private String dayName;
    private String dayType;
    private ArrayList<WorkWeek> workWeekList = new ArrayList<WorkWeek>();
    private String workWeek;
    private String workWeekTypes;

    /**
     * @return the dayId
     */
    public int getDayId() {
        return dayId;
    }

    /**
     * @param dayId the dayId to set
     */
    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    /**
     * @return the dayName
     */
    public String getDayName() {
        return dayName;
    }

    /**
     * @param dayName the dayName to set
     */
    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    /**
     * @return the dayType
     */
    public String getDayType() {
        return dayType;
    }

    /**
     * @param dayType the dayType to set
     */
    public void setDayType(String dayType) {
        this.dayType = dayType;
    }

    public String showWorkWeek() {
       
        setWorkWeekList(LeaveDao.displayWorkWeek());
        boolean retval = workWeekList.isEmpty();
        if (retval == true) {
            
            return "failure";
        } else {
            
            return "success";
        }
    }
    
    public String saveWorkWeek() {

        String[] workWeekNew = getWorkWeek().split(",");
        String[] workWeekTypesNew = getWorkWeekTypes().split(",");
        
        if (LeaveDao.insertWorkWeek(workWeekNew, workWeekTypesNew)) {
            
        }
        if(showWorkWeek()=="success")
        {
            return "success";
        }
        return "failure";
    }
    
    public String editWorkWeek()
    {
    return "success";
    }

    /**
     * @return the WorkWeekList
     */
    public ArrayList<WorkWeek> getWorkWeekList() {
        return workWeekList;
    }

    /**
     * @param WorkWeekList the WorkWeekList to set
     */
    public void setWorkWeekList(ArrayList<WorkWeek> WorkWeekList) {
        this.workWeekList = WorkWeekList;
    }

    /**
     * @return the workWeek
     */
    public String getWorkWeek() {
        return workWeek;
    }

    /**
     * @param workWeek the workWeek to set
     */
    public void setWorkWeek(String workWeek) {
        this.workWeek = workWeek;
    }

    /**
     * @return the workWeekTypes
     */
    public String getWorkWeekTypes() {
        return workWeekTypes;
    }

    /**
     * @param workWeekTypes the workWeekTypes to set
     */
    public void setWorkWeekTypes(String workWeekTypes) {
        this.workWeekTypes = workWeekTypes;
    }
}
