<%-- 
    Document   : AddComment
    Created on : 17 May, 2017, 12:07:13 AM
    Author     : sayansen
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table>
<td><c:out value="${leave.getLeaveId()}"/></td>
<td><c:out value="${leave.getStartDate()}"/></td>
<td><c:out value="${leave.getEndDate()}"/></td>
<td><c:out value="${leave.getEmployeeName()}"/></td>
<td><c:out value="${leave.getLeaveTypeName()}"/></td>
<td><c:out value="${leave.getCurrentDate()}"/></td>
<td><c:out value="${leave.getLeaveCount()}"/></td>
<td><c:out value="${leave.getLeaveBalance()}"/></td>
<td><c:out value="${leave.getLeaveStatus()}"/></td>
<td><select id="leaveAction" class="form-control">
        <option value="Pending">Withhold</option>
        <option value="Approved">Approve</option>
        <option value="Rejected">Reject</option>
        <option value="Cancelled">Cancel</option>
    </select></td>
<td><input type="text" id="leaveCommentInput" class="form-control"></td>
<td><button type="button" class="btn btn-success" onclick="updateLeave(<c:out value="${leave.getLeaveId()}"/>)">Update</button></td>

</table>


