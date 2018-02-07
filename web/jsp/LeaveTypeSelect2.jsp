<%-- 
    Document   : LeaveTypeSelect2
    Created on : 14 May, 2017, 4:44:40 PM
    Author     : sayansen
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<select id="leaveTypeId2" class="form-control" onchange="calculateLeaveBal()">
    <option value="0">--Select Leave Type--</option>
    <c:forEach items="${leaveTypes}" var="leavetype">
        
        <option value="<c:out value="${leavetype.getLeaveTypeId()}"/>">
            <c:out value="${leavetype.getLeaveTypeName()}"/>
        </option>
    </c:forEach>
</select>
