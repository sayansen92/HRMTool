<%-- 
    Document   : LeavePeriod
    Created on : 28 Apr, 2017, 3:21:42 PM
    Author     : sayansen
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<select id="leavePeriodDropdown2" class="form-control">
    <option value="0">--Select Leave Period--</option>
    <c:forEach items="${leaveTypes}" var="leave">
        <c:if test="${leave.isCurrentLeavePeriodFlag()==true}">
            <option value="<c:out value="${leave.getLeavePeriodId()}"/> " class="bg-success">
           <c:out value="${leave.getStartDate()}"/> to <c:out value="${leave.getEndDate()}"/>
        </option>
        </c:if>
        <c:if test="${leave.isCurrentLeavePeriodFlag()==false}">
            <option value="<c:out value="${leave.getLeavePeriodId()}"/> ">
        <c:out value="${leave.getStartDate()}"/> to <c:out value="${leave.getEndDate()}"/> 
        </option>
        </c:if>
        
    </c:forEach>
</select>



