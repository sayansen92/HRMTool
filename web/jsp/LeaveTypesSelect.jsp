<%-- 
    Document   : LeaveTypesSelect.jsp
    Created on : 26 Apr, 2017, 8:53:08 PM
    Author     : sayansen
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<select id="leaveTypeId" class="form-control">
    <option value="0">--Select Leave Type--</option>
    <c:forEach items="${leaveTypes}" var="leavetype">
        <option value="<c:out value="${leavetype.getLeaveTypeId()}"/>">
            <c:out value="${leavetype.getLeaveTypeName()}"/>
        </option>
    </c:forEach>
</select>