<%-- 
    Document   : OneHolidayUpdated
    Created on : 26 Apr, 2017, 2:45:31 PM
    Author     : sayansen
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table>
    <td> <input type="checkbox" class="holidayCheck" value="<c:out value="${holiday.getHolidayId()}"/>" class="form-control"></td> 
    <td> <a href="javascript:editHoliday(<c:out value="${holiday.getHolidayId()}"/>)"><c:out value="${holiday.getHolidayName()}"/></a></td>
    <td> <c:out value="${holiday.getHolidayDate()}"/></td>
    <td> <c:out value="${holiday.getHolidayType()}"/></td>
    <td> <c:out value="${holiday.getRepeatAnnually()}"/></td>
    <td></td>
    <td></td>
</table>
