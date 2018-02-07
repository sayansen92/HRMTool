<%-- 
    Document   : LeaveReport
    Created on : 19 May, 2017, 3:16:48 PM
    Author     : sayansen
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<table class="table"> 
    
<tr>
<th>Employee ID</th>

<th>Entitlements</th>
<th>Pending</th>
<th>Scheduled</th>
<th>Taken</th>
<th>Balance</th>
</tr>    


    <tbody>
        <c:forEach items="${leaveList}" var="leave">
        <tr>
            <td> <c:out value="${leave.getEmployeeId()}"/></td>
            <td><c:out value="${leave.getEntitlements()}"/></td>
            <td><c:out value="${leave.getPending()}"/></td>
            <td><c:out value="${leave.getScheduled()}"/></td>
            <td><c:out value="${leave.getTaken()}"/></td>
            <td><c:out value="${leave.getLeaveCount()}"/></td>
        </tr>      
</c:forEach>
    </tbody>

</table> 

