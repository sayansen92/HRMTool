<%-- 
    Document   : LeavePeriodFound
    Created on : 12 Apr, 2017, 11:44:30 AM
    Author     : sayansen
--%>


        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <p style="color: green"><c:out value="${leave.getStartDate()}"/> To <c:out value="${leave.getEndDate()}"/></p>
                
