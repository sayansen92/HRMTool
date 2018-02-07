/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Employee;
import dto.Entitlement;
import dto.Holiday;
import dto.Leave;
import dto.Location;
import dto.WorkWeek;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import utilities.DBUtility;

/**
 *
 * @author sayansen
 */
public class LeaveDao {

    static Logger log = Logger.getLogger(LeaveDao.class.getName());

    public static boolean insertLeavePeriod(Leave leave) {
        boolean result = false;
        int id = 0;
        try {
            Connection conn = DBUtility.getConnection();
            String sql = "SELECT * FROM hrmdb.leaveperiod;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                sql = "SELECT * FROM  hrmdb.leaveperiod WHERE startDate=? AND endDate=?;";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, leave.getStartDate());
                stmt.setString(2, leave.getEndDate());
                ResultSet r = stmt.executeQuery();
                if (r.next()) {
                    if (leave.isCurrentLeavePeriodFlag()) {

                        sql = "UPDATE hrmdb.leaveperiod SET  currentLeavePeriod =? WHERE  currentLeavePeriod=?;";
                        stmt = conn.prepareStatement(sql);
                        stmt.setBoolean(1, false);
                        stmt.setBoolean(2, true);
                        stmt.execute();
                    }
                    sql = "UPDATE hrmdb.leaveperiod SET currentLeavePeriod=? WHERE startDate=? AND endDate=?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setBoolean(1, leave.isCurrentLeavePeriodFlag());
                    stmt.setString(2, leave.getStartDate());
                    stmt.setString(3, leave.getEndDate());
                    int row = stmt.executeUpdate();
                    if (row > 0) {
                        result = true;
                    } else {
                        result = false;
                    }
                } else {
                    sql = "SELECT MAX(periodId)FROM  hrmdb.leaveperiod;";
                    stmt = conn.prepareStatement(sql);
                    ResultSet rset = stmt.executeQuery();
                    if (rset.next()) {
                        id = rset.getInt("Max(periodId)");
                        if (leave.isCurrentLeavePeriodFlag()) {

                            sql = "UPDATE hrmdb.leaveperiod SET  currentLeavePeriod =? WHERE  currentLeavePeriod=?;";
                            stmt = conn.prepareStatement(sql);
                            stmt.setBoolean(1, false);
                            stmt.setBoolean(2, true);
                            stmt.execute();
                        }
                        sql = "INSERT INTO hrmdb.leaveperiod (periodId,startDate,endDate,currentLeavePeriod) VALUES  (?,?,?,?) ;";
                        stmt = conn.prepareStatement(sql);
                        stmt.setInt(1, id + 1);
                        stmt.setString(2, leave.getStartDate());
                        stmt.setString(3, leave.getEndDate());
                        stmt.setBoolean(4, leave.isCurrentLeavePeriodFlag());
                        int row = stmt.executeUpdate();
                        if (row > 0) {
                            result = true;
                        } else {
                            result = false;
                        }
                    }
                }
            } else {
                sql = "INSERT INTO hrmdb.leaveperiod (periodId,startDate,endDate,currentLeavePeriod) VALUES  (?,?,?,?) ;";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id + 1);
                stmt.setString(2, leave.getStartDate());
                stmt.setString(3, leave.getEndDate());
                stmt.setBoolean(4, leave.isCurrentLeavePeriodFlag());
                int row = stmt.executeUpdate();
                if (row > 0) {
                    result = true;
                } else {
                    result = false;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }

        return result;
    }

    public static boolean insertLeaveType(Leave leave) {
        boolean result = true;
        int id = 0;
        try {
            Connection conn = DBUtility.getConnection();
            String sql = "SELECT   leaveTypeId FROM  hrmdb.leavetype WHERE leaveTypeName=? ;"; //update leaveType
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, leave.getLeaveTypeName());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) //simply update the tuple
            {
                sql = "UPDATE   hrmdb.leavetype SET  paidLeaveType= ?, carryForward=?, entitlementCap=?, encashable=? WHERE leaveTypeName = ? ;";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, leave.getPaidLeaveType());
                stmt.setString(2, leave.getCarryForwardLeave());
                stmt.setString(3, leave.getEntitlementCap());
                stmt.setString(4, leave.getEncashable());
                stmt.setString(5, leave.getLeaveTypeName());
                int row = stmt.executeUpdate();
                if (row > 0) {
                    result = true;
                } else {
                    result = false;
                }
            } else //insert new tuple
            {
                sql = "SELECT * FROM  hrmdb.leavetype;";
                stmt = conn.prepareStatement(sql);
                ResultSet r = stmt.executeQuery();
                if (r.next()) {
                    sql = "select max(leaveTypeId) from  hrmdb.leavetype;";
                    stmt = conn.prepareStatement(sql);
                    ResultSet rset = stmt.executeQuery();
                    if (rset.next()) {
                        id = rset.getInt("max(leaveTypeId)");
                    }
                }

                sql = "insert into hrmdb.leavetype (leaveTypeId, leaveTypeName, paidLeaveType, carryForward, entitlementCap,encashable ) values  (?,?,?,?,?,?) ;";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id + 1);
                stmt.setString(2, leave.getLeaveTypeName());
                stmt.setString(3, leave.getPaidLeaveType());
                stmt.setString(4, leave.getCarryForwardLeave());
                stmt.setString(5, leave.getEntitlementCap());
                stmt.setString(6, leave.getEncashable());

                int row = stmt.executeUpdate();
                if (row > 0) {
                    result = true;
                } else {
                    result = false;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }
        return result;
    }

    public static boolean updateLeaveType(Leave leave) {
        boolean result = true;
        try {
            Connection conn = DBUtility.getConnection();
            String sql = "UPDATE   hrmdb.leavetype SET leaveTypeName=?, paidLeaveType= ?, carryForward=?, entitlementCap=?, encashable=? WHERE leaveTypeId = ? ;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, leave.getLeaveTypeName());
            stmt.setString(2, leave.getPaidLeaveType());
            stmt.setString(3, leave.getCarryForwardLeave());
            stmt.setString(4, leave.getEntitlementCap());
            stmt.setString(5, leave.getEncashable());
            stmt.setInt(6, leave.getLeaveTypeId());
            int row = stmt.executeUpdate();
            if (row > 0) {
                result = true;
            } else {
                result = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }
        return result;
    }

    public static ArrayList<Leave> displayLeaveTypes() {
        ArrayList<Leave> leaveTypeList = new ArrayList<Leave>();
        try {
            Connection conn = DBUtility.getConnection();
            String sql = "SELECT  * FROM  hrmdb.leavetype ;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Leave leave = new Leave();
                leave.setLeaveTypeId(rs.getInt("leaveTypeId"));
                leave.setLeaveTypeName(rs.getString("leaveTypeName"));
                leave.setPaidLeaveType(rs.getString("paidLeaveType"));
                leave.setCarryForwardLeave(rs.getString("carryForward"));
                leave.setEntitlementCap(rs.getString("entitlementCap"));
                leave.setEncashable(rs.getString("encashable"));
                leaveTypeList.add(leave);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }
        return leaveTypeList;

    }

    public static boolean deleteLeaveTypes(int leaveTypeIds[]) {
        boolean result = false;
        try {
            Connection conn = DBUtility.getConnection();
            for (int i = 0; i < leaveTypeIds.length; i++) {
                String sql = "DELETE FROM  hrmdb.leavetype WHERE leaveTypeId = ? ;";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, leaveTypeIds[i]);
                int row = stmt.executeUpdate();
                if (row > 0) {
                    result = true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }
        return result;
    }

    public static Leave showOneLeaveType(int leaveTypeId) {
        Leave leave = new Leave();
        try {
            Connection conn = DBUtility.getConnection();
            String sql = "SELECT   leaveTypeId,  leaveTypeName FROM  hrmdb.leavetype WHERE leaveTypeId= ? ;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, leaveTypeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                leave.setLeaveTypeId(rs.getInt("leaveTypeId"));
                leave.setLeaveTypeName(rs.getString("leaveTypeName"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }

        return leave;
    }

    public static boolean insertWorkWeek(String[] workWeekNew, String[] workWeekTypesNew) {
        boolean result = false;
        try {
            Connection conn = DBUtility.getConnection();
            String sql = "DELETE FROM  hrmdb.workweek ;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();

            for (int i = 0; i < workWeekTypesNew.length; i++) {

                sql = "INSERT INTO hrmdb.workweek (dayId,dayName, dayType) VALUES  (?,?,?) ;";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, i + 1);
                stmt.setString(2, workWeekNew[i]);
                stmt.setString(3, workWeekTypesNew[i]);
                int row = stmt.executeUpdate();
                if (row > 0) {
                    result = true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }
        return result;

    }

    public static ArrayList<WorkWeek> displayWorkWeek() {

        ArrayList<WorkWeek> workWeekList = new ArrayList<WorkWeek>();

        try {
            Connection conn = DBUtility.getConnection();
            String sql = "SELECT * FROM hrmdb.workweek;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                WorkWeek workday = new WorkWeek();
                workday.setDayId(rs.getInt("dayId"));
                workday.setDayName(rs.getString("dayName"));
                workday.setDayType(rs.getString("dayType"));
                workWeekList.add(workday);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }
        return workWeekList;
    }

    public static boolean saveHoliday(Holiday holiday) {

        boolean result = false;
        try {
            int id = 0;
            Connection conn = DBUtility.getConnection();
            String sql = "SELECT * FROM  hrmdb.holidays WHERE holidayName=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, holiday.getHolidayName());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                sql = "UPDATE hrmdb.holidays SET holidayDate=?,repeatFlag=?,holidayType=?,leavePeriodId=? WHERE holidayName=?;";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, holiday.getHolidayDate());
                stmt.setString(2, holiday.getRepeatAnnually());
                stmt.setString(3, holiday.getHolidayType());
                stmt.setInt(4, holiday.getLeavePeriodId());
                stmt.setString(5, holiday.getHolidayName());
                int row = stmt.executeUpdate();
                if (row > 0) {
                    result = true;
                } else {
                    result = false;
                }

            } else {
                sql = "SELECT * FROM  hrmdb.holidays;";
                stmt = conn.prepareStatement(sql);
                ResultSet r = stmt.executeQuery();
                if (r.next()) {
                    sql = "SELECT MAX(holidayId) FROM  hrmdb.holidays;";
                    stmt = conn.prepareStatement(sql);
                    ResultSet rsNew = stmt.executeQuery();
                    if (rsNew.next()) {
                        id = rsNew.getInt("max(holidayId)");
                    }
                }
                sql = "INSERT INTO hrmdb.holidays (holidayId,holidayName,holidayDate,repeatFlag,holidayType,leavePeriodId) \n"
                        + "VALUES(?,?,?,?,?,?) ;";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id + 1);
                stmt.setString(2, holiday.getHolidayName());
                stmt.setString(3, holiday.getHolidayDate());
                stmt.setString(4, holiday.getRepeatAnnually());
                stmt.setString(5, holiday.getHolidayType());
                stmt.setInt(6, holiday.getLeavePeriodId());
                int row = stmt.executeUpdate();
                if (row > 0) {
                    result = true;
                } else {
                    result = false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }
        return result;
    }

    public static ArrayList<Holiday> showHolidays(Holiday holiday) {

        ArrayList<Holiday> holidayList = new ArrayList<>();
        try {
            Connection conn = DBUtility.getConnection();
            String sql = "SELECT * FROM  hrmdb.holidays WHERE holidayDate>=? AND holidayDate<=? ORDER BY holidayDate;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, holiday.getHolidayFromDate());
            stmt.setString(2, holiday.getHolidayToDate());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Holiday holidayNew = new Holiday();
                holidayNew.setHolidayId(rs.getInt("holidayId"));
                holidayNew.setHolidayName(rs.getString("holidayName"));
                holidayNew.setHolidayDate(rs.getString("holidayDate"));
                holidayNew.setRepeatAnnually(rs.getString("repeatFlag"));
                holidayNew.setHolidayType(rs.getString("holidayType"));
                holidayList.add(holidayNew);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }

        return holidayList;
    }

    public static Leave getCurrentLeavePeriod(String currDate) {
        Leave leave = new Leave();

        try {
            Connection conn = DBUtility.getConnection();
            String sql = "SELECT * FROM  hrmdb.leaveperiod WHERE currentLeavePeriod=TRUE;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                leave.setLeavePeriodId(rs.getInt("periodId"));
                leave.setStartDate(rs.getString("startDate"));
                leave.setEndDate(rs.getString("endDate"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }
        return leave;
    }

    public static Holiday getOneHoliday(int holidayId) {
        Holiday holiday = new Holiday();
        try {
            Connection conn = DBUtility.getConnection();
            String sql = "SELECT *FROM  hrmdb.holidays WHERE holidayId=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, holidayId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                holiday.setHolidayId(rs.getInt("holidayId"));
                holiday.setHolidayName(rs.getString("holidayName"));
                holiday.setHolidayDate(rs.getString("holidayDate"));
                holiday.setRepeatAnnually(rs.getString("repeatFlag"));
                holiday.setHolidayType(rs.getString("holidayType"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }
        return holiday;
    }

    public static ArrayList<Leave> showAllLeavePeriods() {
        ArrayList<Leave> leavePeriodsList = new ArrayList<>();
        try {
            Connection conn = DBUtility.getConnection();
            String sql = "SELECT *  FROM  hrmdb.leaveperiod;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Leave lp = new Leave();
                lp.setLeavePeriodId(rs.getInt("periodId"));
                lp.setStartDate(rs.getString("startDate"));
                lp.setEndDate(rs.getString("endDate"));
                lp.setCurrentLeavePeriodFlag(rs.getBoolean("currentLeavePeriod"));
                leavePeriodsList.add(lp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }

        return leavePeriodsList;

    }

    public static boolean deleteHoliday(int[] holidayIds) {
        boolean result = false;
        try {
            Connection conn = DBUtility.getConnection();
            for (int i = 0; i < holidayIds.length; i++) {
                String sql = "DELETE FROM  hrmdb.holidays WHERE holidayId = ? ;";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, holidayIds[i]);
                int row = stmt.executeUpdate();
                if (row > 0) {
                    result = true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }
        return result;

    }

    public static boolean saveEntitlement(Entitlement entitlement) {
        boolean result = false;
        int rowUpdate = 0, i = 0;
        try {
            Connection conn = DBUtility.getConnection();
            String sql;
            PreparedStatement stmt;
            ResultSet rs;

            String sqlCheck = "SELECT * FROM hrmdb.leavedetails WHERE employeeId=? AND leaveTypeId=? AND leavePeriodId=?;";
            PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck);
            ResultSet rsCheck;

            String sqlNew = "insert into hrmdb.leavedetails ( employeeId,  leavePeriodId,  leaveTypeId,  entitlements,  leavesPending,  leaveScheduled,  leavesTaken,  balance) \n"
                    + "values  (?,?,?,?,?,?,?,? ) ;";
            PreparedStatement stmtNew = conn.prepareStatement(sqlNew);
            String sqlUpdate = "update hrmdb.leavedetails set  entitlements = ?, balance = ? \n"
                    + "where employeeId = ?  and leavePeriodId = ? and leaveTypeId = ?  and entitlements =balance ;";
            PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);

            if ("".equals(entitlement.getEmployeeName())) //no name present
            {

                if (("all".equals(entitlement.getLocationId())) && ("all".equals(entitlement.getSubunitId()))) {
                    sql = "select employeeId from hrmdb.jobs";

                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                } else if (("all".equals(entitlement.getLocationId())) && !("all".equals(entitlement.getSubunitId()))) {
                    sql = "select employeeId from hrmdb.jobs where subunitId=?;";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, entitlement.getSubunitId());
                    rs = stmt.executeQuery();
                } else if (!("all".equals(entitlement.getLocationId())) && ("all".equals(entitlement.getSubunitId()))) {
                    sql = "select employeeId from hrmdb.jobs where locationId=?;";

                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, entitlement.getLocationId());
                    rs = stmt.executeQuery();
                } else {
                    sql = "select employeeId from hrmdb.jobs where locationId=? and subunitId=?;";

                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, entitlement.getLocationId());
                    stmt.setString(2, entitlement.getSubunitId());
                    rs = stmt.executeQuery();
                }
                while (rs.next()) //for those every empids check if they are already present
                {
                    i++;
                    stmtCheck.setInt(1, rs.getInt("employeeId"));
                    stmtCheck.setInt(2, entitlement.getLeaveTypeId());
                    stmtCheck.setInt(3, entitlement.getLeavePeriodId());
                    rsCheck = stmtCheck.executeQuery();
                    if (rsCheck.next()) {
                        stmtUpdate.setFloat(1, entitlement.getEntitlements());
                        stmtUpdate.setFloat(2, entitlement.getEntitlements());
                        stmtUpdate.setInt(3, rsCheck.getInt("employeeId"));
                        stmtUpdate.setInt(4, rsCheck.getInt("leavePeriodId"));
                        stmtUpdate.setInt(5, rsCheck.getInt("leaveTypeId"));
                        rowUpdate = rowUpdate + stmtUpdate.executeUpdate();

                    } else {

                        stmtNew.setInt(1, rs.getInt("employeeId"));
                        stmtNew.setInt(2, entitlement.getLeavePeriodId());
                        stmtNew.setInt(3, entitlement.getLeaveTypeId());
                        stmtNew.setFloat(4, entitlement.getEntitlements());
                        stmtNew.setFloat(5, 0);
                        stmtNew.setFloat(6, 0);
                        stmtNew.setFloat(7, 0);
                        stmtNew.setFloat(8, entitlement.getEntitlements());
                        rowUpdate = rowUpdate + stmtNew.executeUpdate();

                    }
                }
                if (rowUpdate == i) {
                    result = true;
                } else {
                    result = false;
                }

            } else //name is present, so update/insert for only one 
            {
                stmtCheck.setInt(1, entitlement.getEmployeeId());
                stmtCheck.setInt(2, entitlement.getLeaveTypeId());
                stmtCheck.setInt(3, entitlement.getLeavePeriodId());
                rsCheck = stmtCheck.executeQuery();
                if (rsCheck.next()) {
                    stmtUpdate.setFloat(1, entitlement.getEntitlements());
                    stmtUpdate.setFloat(2, entitlement.getEntitlements());
                    stmtUpdate.setInt(3, rsCheck.getInt("employeeId"));
                    stmtUpdate.setInt(4, rsCheck.getInt("leavePeriodId"));
                    stmtUpdate.setInt(5, rsCheck.getInt("leaveTypeId"));
                    rowUpdate = stmtUpdate.executeUpdate();
                    if (rowUpdate > 0) {
                        result = true;
                    } else {
                        result = false;
                    }
                } else {
                    stmtNew = conn.prepareStatement(sqlNew);
                    stmtNew.setInt(1, entitlement.getEmployeeId());
                    stmtNew.setInt(2, entitlement.getLeavePeriodId());
                    stmtNew.setInt(3, entitlement.getLeaveTypeId());
                    stmtNew.setFloat(4, entitlement.getEntitlements());
                    stmtNew.setFloat(5, 0);
                    stmtNew.setFloat(6, 0);
                    stmtNew.setFloat(7, 0);
                    stmtNew.setFloat(8, entitlement.getEntitlements());
                    rowUpdate = stmtNew.executeUpdate();
                    if (rowUpdate > 0) {
                        result = true;
                    } else {
                        result = false;
                    }
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }
        return result;
    }

    public static boolean isEmployeeValid(int employeeId, String employeeName) {
        boolean result = false;
        try {
            Connection conn = DBUtility.getConnection();
            String sql = "SELECT  *FROM  hrmdb.employee WHERE employeeId=? AND employeeName=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, employeeId);
            stmt.setString(2, employeeName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }
        return result;
    }

    public static ArrayList<Entitlement> searchEntitlement(Entitlement entitlement) {
        ArrayList<Entitlement> entitlementList = new ArrayList<Entitlement>();

        try {
            Connection conn = DBUtility.getConnection();
            String sql = "SELECT * FROM hrmdb.leavedetails WHERE employeeId IN (SELECT employeeId FROM hrmdb.employee WHERE employeeName LIKE \"%\"?\"%\") AND leaveTypeId=? AND leavePeriodId=? ;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, entitlement.getEmployeeName());
            stmt.setInt(2, entitlement.getLeaveTypeId());
            stmt.setInt(3, entitlement.getLeavePeriodId());

            String sqlNew = "SELECT employeeName FROM hrmdb.employee WHERE employeeId=? ";
            PreparedStatement stmtNew = conn.prepareStatement(sqlNew);

            ResultSet rs = stmt.executeQuery();
            ResultSet rsNew;
            while (rs.next()) {
                Entitlement ent = new Entitlement();
                ent.setEmployeeId(rs.getInt("employeeId"));
                stmtNew.setInt(1, rs.getInt("employeeId"));
                rsNew = stmtNew.executeQuery();
                if (rsNew.next()) {
                    ent.setEmployeeName(rsNew.getString("employeeName"));
                }
                ent.setEntitlements(rs.getInt("entitlements"));
                ent.setLeaveTypeName(entitlement.getLeaveTypeName());
                ent.setLeavePeriodName(entitlement.getLeavePeriodName());
                entitlementList.add(ent);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }
        return entitlementList;
    }

    public static Leave calculateLeaveBal(Leave leave) {

        Leave leaveNew = new Leave();
        leaveNew.setLeaveBalance(0);
        try {
            Connection conn = DBUtility.getConnection();

            String sql = "SELECT  balance FROM  hrmdb.leavedetails WHERE  employeeId=? AND leaveTypeId=? AND leavePeriodId IN(SELECT periodId FROM hrmdb.leaveperiod WHERE currentLeavePeriod=1);";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, leave.getEmployeeId());
            stmt.setInt(2, leave.getLeaveTypeId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                leaveNew.setLeaveBalance(rs.getInt("balance"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }
        return leaveNew;
    }

    public static boolean applyLeave(Leave leave) {

        int leaveTypeId = 0, periodId = 0, leaveId = 0;
        double holidays = 0;
        try {
            Connection conn = DBUtility.getConnection();
            String sql = "select * from  hrmdb.holidays where holidayDate>=? and holidayDate<=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, leave.getStartDate());
            stmt.setString(2, leave.getEndDate());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ++holidays;
            }
            int i = leave.getStartDay();
            int n = 0;
            while (n < leave.getLeaveCount()) {
                if (i == 0) {
                    i++;
                }
                sql = "SELECT dayType FROM  hrmdb.workweek WHERE dayId=? ;";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, i);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    if ("Half day".equals(rs.getString("dayType"))) {
                        holidays = holidays + 0.5;
                    } else if ("Non-working day".equals(rs.getString("dayType"))) {
                        holidays = holidays + 1;
                    }
                }
                n++;
                i = (i + 1) % 8;
            }
            if ((leave.getLeaveCount() - holidays) <= leave.getLeaveBalance()) {
                sql = "SELECT * FROM  hrmdb.leavestatus ;";
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    sql = "SELECT MAX(appliedleaveID) FROM  hrmdb.leavestatus ;";
                    stmt = conn.prepareStatement(sql);
                    ResultSet rsNew = stmt.executeQuery();
                    if (rsNew.next()) {
                        leaveId = rsNew.getInt("max(appliedleaveID)");
                    }
                }
                sql = "insert into hrmdb.leavestatus (  employeeId,  leaveType,  fromDate,  toDate,  leaveStatus, totalLeaveCount,actualLeaveCount,comment, leaveApplyDate, appliedLeaveId) \n"
                        + "values  (?,?,?,?,?,?,?,?,?,?) ;";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, leave.getEmployeeId());
                stmt.setString(2, leave.getLeaveTypeName());
                stmt.setString(3, leave.getStartDate());
                stmt.setString(4, leave.getEndDate());
                stmt.setString(5, "Pending");
                stmt.setDouble(6, leave.getLeaveCount());
                stmt.setDouble(7, (leave.getLeaveCount() - holidays));
                stmt.setString(8, "--No comments--");
                stmt.setString(9, leave.getCurrentDate());
                stmt.setInt(10, leaveId + 1);
                int rowUpdate = stmt.executeUpdate();
                if (rowUpdate > 0) {
                    sql = "SELECT   periodId FROM  hrmdb.leaveperiod WHERE currentLeavePeriod=1;";
                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        periodId = rs.getInt("periodId");
                    }
                    sql = "SELECT leaveTypeId FROM  hrmdb.leavetype WHERE leaveTypeName=? ;";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, leave.getLeaveTypeName());
                    ResultSet rsNew = stmt.executeQuery();
                    if (rsNew.next()) {
                        leaveTypeId = rsNew.getInt("leaveTypeId");
                    }
                    sql = "UPDATE   hrmdb.leavedetails \n"
                            + "SET  leavesPending = leavesPending+?\n"
                            + "WHERE employeeId = ?  AND leavePeriodId = ?  AND leaveTypeId = ?;";
                    stmt = conn.prepareStatement(sql);
                    stmt.setDouble(1, (leave.getLeaveCount() - holidays));
//                    stmt.setDouble(2, (leave.getLeaveCount() - holidays));
                    stmt.setInt(2, leave.getEmployeeId());
                    stmt.setInt(3, periodId);
                    stmt.setInt(4, leaveTypeId);
                    int rowUpdt = stmt.executeUpdate();
                    if (rowUpdt > 0) {
                        return true;
                    } else {
                        return false;
                    }

                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }
        return false;
    }

    public static ArrayList<Leave> showLeaveList(Leave leave) {
        ArrayList<Leave> leavelist = new ArrayList();

        try {
            Connection conn = DBUtility.getConnection();
            if ("".equals(leave.getEmployeeName())) {
            String sql = "SELECT * FROM  hrmdb.leavestatus WHERE leaveStatus LIKE \"%\"?\"%\" AND fromDate>=? AND toDate<=?;";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, leave.getLeaveStatus());
            stmt.setString(2, leave.getStartDate());
            stmt.setString(3, leave.getEndDate());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Leave leaveNew = new Leave();
//              set the default members
                leaveNew.setEmployeeId(rs.getInt("employeeId"));
                leaveNew.setLeaveTypeName(rs.getString("leaveType"));
                leaveNew.setStartDate(rs.getString("fromDate"));
                leaveNew.setEndDate(rs.getString("toDate"));
                leaveNew.setLeaveStatus(rs.getString("leaveStatus"));
                leaveNew.setLeaveCount(rs.getDouble("totalLeaveCount"));
                leaveNew.setLeaveComment(rs.getString("comment"));
                leaveNew.setCurrentDate(rs.getString("leaveApplydate"));
                leaveNew.setLeaveId(rs.getInt("appliedLeaveId"));
//              set employeeName
                sql = "SELECT employeeName FROM  hrmdb.employee WHERE employeeId=? ;";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, rs.getInt("employeeId"));
                ResultSet rsNew = stmt.executeQuery();
                if (rsNew.next()) {
                    leaveNew.setEmployeeName(rsNew.getString("employeeName"));
                }
//              set leaveTypeid
                sql = "SELECT leaveTypeId FROM  hrmdb.leavetype WHERE leaveTypeName=? ;";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, rs.getString("leaveType"));
                rsNew = stmt.executeQuery();
                if (rsNew.next()) {
                    leaveNew.setLeaveTypeId(rsNew.getInt("leaveTypeId"));
                }
//              set current leave period
                sql = "SELECT   periodId FROM  hrmdb.leaveperiod WHERE currentLeavePeriod=1;";
                stmt = conn.prepareStatement(sql);
                rsNew = stmt.executeQuery();
                if (rsNew.next()) {
                    leaveNew.setCurrentLeavePeriodId(rsNew.getInt("periodId"));
                }
                sql = "SELECT balance FROM  hrmdb.leavedetails WHERE employeeId=? AND leavePeriodId=? AND leaveTypeId=?;";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, leaveNew.getEmployeeId());
                stmt.setInt(2, leaveNew.getCurrentLeavePeriodId());
                stmt.setInt(3, leaveNew.getLeaveTypeId());
                rsNew = stmt.executeQuery();
                if (rsNew.next()) {
                    leaveNew.setLeaveBalance(rsNew.getInt("balance"));
                }
                leavelist.add(leaveNew);
//              rs ends
            }
            } else {
                String sql = "SELECT * FROM  hrmdb.leavestatus WHERE leaveStatus LIKE \"%\"?\"%\" AND fromDate>=? AND toDate<=? AND employeeId IN(SELECT employeeId FROM hrmdb.employee WHERE employeeName LIKE \"%\"?\"%\");";

                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, leave.getLeaveStatus());
                stmt.setString(2, leave.getStartDate());
                stmt.setString(3, leave.getEndDate());
                stmt.setString(4, leave.getEmployeeName());
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Leave leaveNew = new Leave();
//              set the default members
                    leaveNew.setEmployeeId(rs.getInt("employeeId"));
                    leaveNew.setLeaveTypeName(rs.getString("leaveType"));
                    leaveNew.setStartDate(rs.getString("fromDate"));
                    leaveNew.setEndDate(rs.getString("toDate"));
                    leaveNew.setLeaveStatus(rs.getString("leaveStatus"));
                    leaveNew.setLeaveCount(rs.getDouble("totalLeaveCount"));
                    leaveNew.setLeaveComment(rs.getString("comment"));
                    leaveNew.setCurrentDate(rs.getString("leaveApplydate"));
                    leaveNew.setLeaveId(rs.getInt("appliedLeaveId"));
//              set employeeName
                    sql = "SELECT employeeName FROM  hrmdb.employee WHERE employeeId=? ;";
                    stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, rs.getInt("employeeId"));
                    ResultSet rsNew = stmt.executeQuery();
                    if (rsNew.next()) {
                        leaveNew.setEmployeeName(rsNew.getString("employeeName"));
                    }
//              set leaveTypeid
                    sql = "SELECT leaveTypeId FROM  hrmdb.leavetype WHERE leaveTypeName=? ;";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, rs.getString("leaveType"));
                    rsNew = stmt.executeQuery();
                    if (rsNew.next()) {
                        leaveNew.setLeaveTypeId(rsNew.getInt("leaveTypeId"));
                    }
//              set current leave period
                    sql = "SELECT   periodId FROM  hrmdb.leaveperiod WHERE currentLeavePeriod=1;";
                    stmt = conn.prepareStatement(sql);
                    rsNew = stmt.executeQuery();
                    if (rsNew.next()) {
                        leaveNew.setCurrentLeavePeriodId(rsNew.getInt("periodId"));
                    }
                    sql = "SELECT balance FROM  hrmdb.leavedetails WHERE employeeId=? AND leavePeriodId=? AND leaveTypeId=?;";
                    stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, leaveNew.getEmployeeId());
                    stmt.setInt(2, leaveNew.getCurrentLeavePeriodId());
                    stmt.setInt(3, leaveNew.getLeaveTypeId());
                    rsNew = stmt.executeQuery();
                    if (rsNew.next()) {
                        leaveNew.setLeaveBalance(rsNew.getInt("balance"));
                    }
                    leavelist.add(leaveNew);
//              rs ends
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }

        return leavelist;
    }

    public static Leave addComment(int leaveId) {
        Leave leave = new Leave();
        try {

            Connection conn = DBUtility.getConnection();
            String sql = "select *from  hrmdb.leavestatus where appliedleaveID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, leaveId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

//              set the default members
                leave.setEmployeeId(rs.getInt("employeeId"));
                leave.setLeaveTypeName(rs.getString("leaveType"));
                leave.setStartDate(rs.getString("fromDate"));
                leave.setEndDate(rs.getString("toDate"));
                leave.setLeaveStatus(rs.getString("leaveStatus"));
                leave.setLeaveCount(rs.getDouble("totalLeaveCount"));
                leave.setLeaveComment(rs.getString("comment"));
                leave.setCurrentDate(rs.getString("leaveApplydate"));
                leave.setLeaveId(rs.getInt("appliedLeaveId"));
                //              set employeeName
                sql = "SELECT employeeName FROM  hrmdb.employee WHERE employeeId=? ;";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, rs.getInt("employeeId"));
                ResultSet rsNew = stmt.executeQuery();
                if (rsNew.next()) {
                    leave.setEmployeeName(rsNew.getString("employeeName"));
                }
//              set leaveTypeid
                sql = "SELECT leaveTypeId FROM  hrmdb.leavetype WHERE leaveTypeName=? ;";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, rs.getString("leaveType"));
                rsNew = stmt.executeQuery();
                if (rsNew.next()) {
                    leave.setLeaveTypeId(rsNew.getInt("leaveTypeId"));
                }
//              set current leave period
                sql = "SELECT   periodId FROM  hrmdb.leaveperiod WHERE currentLeavePeriod=1;";
                stmt = conn.prepareStatement(sql);
                rsNew = stmt.executeQuery();
                if (rsNew.next()) {
                    leave.setCurrentLeavePeriodId(rsNew.getInt("periodId"));
                }
                sql = "SELECT balance FROM  hrmdb.leavedetails WHERE employeeId=? AND leavePeriodId=? AND leaveTypeId=?;";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, leave.getEmployeeId());
                stmt.setInt(2, leave.getCurrentLeavePeriodId());
                stmt.setInt(3, leave.getLeaveTypeId());
                rsNew = stmt.executeQuery();
                if (rsNew.next()) {
                    leave.setLeaveBalance(rsNew.getInt("balance"));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }
        return leave;
    }

    public static boolean updateLeave(Leave leave) {
        boolean result = false;
        try {
            Connection conn = DBUtility.getConnection();

            String sql = "UPDATE hrmdb.leavestatus SET  leaveStatus = ?,  COMMENT = ?,  leaveActionDate = ?,  leaveActionTime = ? WHERE appliedleaveID =? ;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, leave.getLeaveStatus());
            stmt.setString(2, leave.getLeaveComment());
            stmt.setString(3, leave.getLeaveActionDate());
            stmt.setString(4, leave.getLeaveActionTime());
            stmt.setInt(5, leave.getLeaveId());
            int rowUpdate = stmt.executeUpdate();
            if (rowUpdate > 0) {
                result = true;
            } else {
                result = false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }

        return result;

    }

    public static ArrayList<Leave> viewLeaveReport(Leave leave) {
        ArrayList<Leave> leaveList = new ArrayList<Leave>();
        try {
            Connection conn = DBUtility.getConnection();
            ResultSet rs;
            String sql = "";
            PreparedStatement stmt;
            if ("x".equals(leave.getEmployeeName())) {

                if (("all".equals(leave.getLocationName())) && ("all".equals(leave.getSubUnitName()))) {
                    sql = "select employeeId from hrmdb.jobs";

                    stmt = conn.prepareStatement(sql);
                    rs = stmt.executeQuery();
                } else if (("all".equals(leave.getLocationName())) && !("all".equals(leave.getSubUnitName()))) {
                    sql = "select employeeId from hrmdb.jobs where subunitId=?;";

                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, leave.getSubUnitName());
                    rs = stmt.executeQuery();
                } else if (!("all".equals(leave.getLocationName())) && ("all".equals(leave.getSubUnitName()))) {
                    sql = "select employeeId from hrmdb.jobs where locationId=?;";

                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, leave.getLocationName());
                    rs = stmt.executeQuery();
                } else {
                    sql = "select employeeId from hrmdb.jobs where locationId=? and subunitId=?;";

                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, leave.getLocationName());
                    stmt.setString(2, leave.getSubUnitName());
                    rs = stmt.executeQuery();
                }
                while (rs.next()) {

                    sql = "SELECT  entitlements,leavesPending,leaveScheduled,leavesTaken,balance,previousYrSurplus \n"
                            + "FROM  hrmdb.leavedetails WHERE employeeId=? AND leavePeriodId=? AND leaveTypeId=?;";

                    stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, rs.getInt("employeeId"));
                    stmt.setInt(2, leave.getLeavePeriodId());
                    stmt.setInt(3, leave.getLeaveTypeId());
                    ResultSet rsNew = stmt.executeQuery();
                    while (rsNew.next()) {
                        Leave leaveNew = new Leave();
                        leaveNew.setEmployeeId(rs.getInt("employeeId"));
                        leaveNew.setEntitlements(rsNew.getFloat("entitlements"));
                        leaveNew.setPending(rsNew.getFloat("leavesPending"));
                        leaveNew.setTaken(rsNew.getFloat("leavesTaken"));
                        leaveNew.setScheduled(rsNew.getFloat("leaveScheduled"));
                        leaveNew.setLeaveCount(rsNew.getFloat("balance"));
                        leaveList.add(leaveNew);

                    }

                }
            } else {
                sql = "select employeeId from  hrmdb.employee where employeeName LIKE \"%\"?\"%\";";

                stmt = conn.prepareStatement(sql);
                stmt.setString(1, leave.getEmployeeName());
                rs = stmt.executeQuery();
                while (rs.next()) {

                    sql = "SELECT  leaveTypeId,entitlements,leavesPending,leaveScheduled,leavesTaken,balance,previousYrSurplus \n"
                            + "FROM  hrmdb.leavedetails WHERE employeeId=? AND leavePeriodId=?;";

                    stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, rs.getInt("employeeId"));
                    stmt.setInt(2, leave.getLeavePeriodId());
                    ResultSet rsNew = stmt.executeQuery();
                    while (rsNew.next()) {
                        Leave leaveNew = new Leave();
                        sql = "SELECT leaveTypeName FROM  hrmdb.leavetype WHERE leaveTypeId=?;";

                        stmt = conn.prepareStatement(sql);
                        stmt.setInt(1, rsNew.getInt("leaveTypeId"));
                        ResultSet rSet = stmt.executeQuery();
                        if (rSet.next()) {
                            leaveNew.setLeaveTypeName(rSet.getString("leaveTypeName"));

                        }
                        leaveNew.setEmployeeId(rs.getInt("employeeId"));
                        leaveNew.setEntitlements(rsNew.getFloat("entitlements"));
                        leaveNew.setPending(rsNew.getFloat("leavesPending"));
                        leaveNew.setTaken(rsNew.getFloat("leavesTaken"));
                        leaveNew.setScheduled(rsNew.getFloat("leaveScheduled"));
                        leaveNew.setLeaveCount(rsNew.getFloat("balance"));
                        leaveList.add(leaveNew);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
        }

        return leaveList;
    }

    public static ArrayList<Employee> searchEmployee(String name) {
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        if (name == "") {
            return employeeList;
        } else {

            try {
                Connection conn = DBUtility.getConnection();
                String sql = "SELECT * FROM hrmdb.employee WHERE employeeName LIKE \"%\"?\"%\"  ;";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Employee emp = new Employee();
                    emp.setEmployeeName(name);
                    emp.setEmployeeId(rs.getInt("employeeId"));
                    emp.setEmployeeName(rs.getString("employeeName"));
                    employeeList.add(emp);
                }
            } catch (Exception ex) {
                ex.printStackTrace();

            }

            return employeeList;
        }
    }

    public static Employee fetchEmployee(int id) {
        Employee employee = new Employee();
        try {
            Connection conn = DBUtility.getConnection();
            String sql = "SELECT   employeeId,  employeeName FROM  hrmdb.employee WHERE employeeId=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                employee.setEmployeeId(rs.getInt("employeeId"));
                employee.setEmployeeName(rs.getString("employeeName"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return employee;
    }

    public static ArrayList<Location> getAllLocations() {
        ArrayList<Location> locationList = new ArrayList<Location>();
        try {

            Connection conn = DBUtility.getConnection();
            String sql = "SELECT   * FROM  hrmdb.location ;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Location loc = new Location();
                loc.setLocationId(rs.getInt("locationId"));
                loc.setLocationName(rs.getString("locationName"));
                locationList.add(loc);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return locationList;
    }

    public static ArrayList<Location> getAllSubUnits() {

        ArrayList<Location> subunitList = new ArrayList<Location>();
        try {

            Connection conn = DBUtility.getConnection();
            String sql = "SELECT   * FROM  hrmdb.subunit ;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Location subunit = new Location();
                subunit.setSubUnitId(rs.getInt("subunitId"));
                subunit.setSubUnitName(rs.getString("subunitname"));
                subunitList.add(subunit);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return subunitList;

    }

}
