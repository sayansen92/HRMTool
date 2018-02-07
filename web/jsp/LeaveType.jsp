<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table>
    <td> <input type="checkbox" value="<c:out value="${leave.getLeaveTypeId()}"/>"></td> 
    <td> <input type="text" id="leaveTypeNameNew" value="<c:out value="${leave.getLeaveTypeName()}"/>" class="form-control"></td>
    <td><input type="checkbox" id="paidLeaveTypeNew"></td>
    <td><input type="checkbox" id="carryForwardLeaveNew"></td>
    <td><input type="checkbox" id="entitlementCapNew"></td>
    <td><input type="checkbox" id="encashableLeaveTypeNew"></td>
    <td><button type="button" class="btn btn-xs" onclick="updateLeaveType(<c:out value="${leave.getLeaveTypeId()}"/>)">Update</button></td>
    </table>