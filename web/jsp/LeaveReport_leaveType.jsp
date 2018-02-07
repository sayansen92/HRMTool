<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<select id="leaveReportLT" class="form-control" onchange="calculateLeaveBal()">
    <option value="0">--Select Leave Type--</option>
    <c:forEach items="${leaveTypes}" var="leavetype">
        
        <option value="<c:out value="${leavetype.getLeaveTypeId()}"/>">
            <c:out value="${leavetype.getLeaveTypeName()}"/>
        </option>
    </c:forEach>
</select>