<%-- 
    Document   : OtherLeavePeriods
    Created on : 18 May, 2017, 8:04:19 PM
    Author     : sayansen
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach items="${leaveTypes}" var="leave">
    <c:if test="${leave.isCurrentLeavePeriodFlag()==false}">
         <p><c:out value="${leave.getLeavePeriodId()}"/>: <c:out value="${leave.getStartDate()}"/> to <c:out value="${leave.getEndDate()}"/></p>
   </c:if>
</c:forEach>