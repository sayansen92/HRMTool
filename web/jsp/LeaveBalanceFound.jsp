<%-- 
    Document   : LeaveBalanceFound
    Created on : 14 May, 2017, 5:12:33 PM
    Author     : sayansen
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<input type="number" id="leaveBalance" value="<c:out value="${leave.getLeaveBalance()}"/>" disabled style="width: 100px"><span> for current year.</span>
