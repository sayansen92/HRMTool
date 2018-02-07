<%-- 
    Document   : LeaveTypeSelect
    Created on : 5 May, 2017, 4:48:54 PM
    Author     : sayansen
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<select id="leaveTypeId" class="form-control">
    <option value="0">--Select Leave Type--</option>
    <c:forEach items="${leaveTypes}" var="leavetype">
        <c:if test="${leavetype.getEntitlementCap()==true}">
            <option value="<c:out value="${leavetype.getLeaveTypeId()}"/>">
            <c:out value="${leavetype.getLeaveTypeName()}"/>
        </option>
        </c:if>
        
    </c:forEach>
</select>
