<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<select id="leaveReportLP2" class="form-control">
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