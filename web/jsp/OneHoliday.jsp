<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table>
    <td> <input type="checkbox" class="holidayCheck" value="<c:out value="${holiday.getHolidayId()}"/>"></td> 
    <td> <input type="text" id="newHolidayName" value="<c:out value="${holiday.getHolidayName()}"/>" class="form-control"></td>
    <td> <input type="date" id="newHolidayDate" value="<c:out value="${holiday.getHolidayDate()}"/>" class="form-control"></td>
    <td> <select id="newHolidayType" class="form-control">
            <option value="Full Day">Full Day</option>
            <option value="Half Day">Half Day</option>
        </select></td>
    <td> <select id="newRepeatFlag" class="form-control">
            <option value="Yes">Yes</option>
            <option value="No">No</option>
        </select></td>
    <td><button type="button" class="btn btn-success " onclick="updateHoliday(<c:out value="${holiday.getHolidayId()}"/>)">Update</button></td>
    <td><button type="button" class="btn btn-success " onclick="cancelUpdateHoliday(<c:out value="${holiday.getHolidayId()}"/>)">Cancel</button></td>
</table>