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
public class Location {
    private int locationId;
    private String locationName;
    private ArrayList<Location> locationList;
    private ArrayList<Location> subunitList;
    private Location location;
    private Location subunit;
    private int subUnitId;
    private String subUnitName;

    /**
     * @return the locationId
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * @param locationId the locationId to set
     */
    public void setLocationId(int locationId) {
        this.locationId = locationId;
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
     * @return the locationList
     */
    public ArrayList<Location> getLocationList() {
        return locationList;
    }

    /**
     * @param locationList the locationList to set
     */
    public void setLocationList(ArrayList<Location> locationList) {
        this.locationList = locationList;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }
    
    public String getAllLocations(){
    setLocationList(LeaveDao.getAllLocations());
    if (!locationList.isEmpty()) {
            return "success";
        }
    return "failure";
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
     * @return the subUnitId
     */
    public int getSubUnitId() {
        return subUnitId;
    }

    /**
     * @param subUnitId the subUnitId to set
     */
    public void setSubUnitId(int subUnitId) {
        this.subUnitId = subUnitId;
    }
    
    public String getAllSubUnits(){
   
    setSubunitList(LeaveDao.getAllSubUnits());
 
    if (!subunitList.isEmpty()) {
     
            
        return "success";
        }
    return "failure";
    }

    /**
     * @return the subunitList
     */
    public ArrayList<Location> getSubunitList() {
        return subunitList;
    }

    /**
     * @param subunitList the subunitList to set
     */
    public void setSubunitList(ArrayList<Location> subunitList) {
        this.subunitList = subunitList;
    }
}
