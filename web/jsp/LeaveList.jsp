<%-- 
    Document   : LeaveList
    Created on : 15 May, 2017, 5:39:24 PM
    Author     : sayansen
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="panel panel-default" >
    <div class="panel-heading">Leave List</div>
    <div class="panel-body">

        <table class="table table-hover table-condensed " > 
            <thead class="btn-primary">
            <th>ID</th>
            <th>From date</th>
            <th>To Date</th>
            <th>Employee name</th>
            <th>Leave Type</th>
            <th>Applied on</th>
            <th>Number of days</th>
            <th>Balance(Now)</th>
            <th>Status</th>
            <th>Action</th>
            <th>Comments</th>
            <th></th>
            </thead>
            <c:forEach items="${leaveList}" var="leave">    
                <tbody>
                    <tr id="leave<c:out value="${leave.getLeaveId()}"/>">
                        <td><c:out value="${leave.getLeaveId()}"/></td>
                        <td><c:out value="${leave.getStartDate()}"/></td>
                        <td><c:out value="${leave.getEndDate()}"/></td>
                        <td><a href="javascript:addComment('<c:out value="${leave.getLeaveId()}"/>')"><c:out value="${leave.getEmployeeName()}"/></a></td>
                        <td><c:out value="${leave.getLeaveTypeName()}"/></td>
                        <td><c:out value="${leave.getCurrentDate()}"/></td>
                        <td><c:out value="${leave.getLeaveCount()}"/></td>
                        <td><c:out value="${leave.getLeaveBalance()}"/></td>
                        <td><c:out value="${leave.getLeaveStatus()}"/></td>
                        <td></td>
                        <td><c:out value="${leave.getLeaveComment()}"/></td>
                        <td></td>
                    </tr>
                </tbody>
            </c:forEach>   
        </table> 
    </div>
</div>
