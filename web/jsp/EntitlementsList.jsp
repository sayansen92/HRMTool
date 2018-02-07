<%-- 
    Document   : EntitlementsList
    Created on : 5 May, 2017, 12:50:31 AM
    Author     : sayansen
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="panel panel-default">
    <div class="panel-heading">Leave Types</div>
    <div class="panel-body">
        <table class="table "> 
            <thead>
                
                <td>Employee Id</td>
                <td>Employee Name</td>
                <td>Leave Type</td>
                <td>Leave Period</td>
                <td>Entitled leaves</td>
            </thead>
            <c:forEach items="${entitlementList}" var="ent">
                <tbody>
                    <tr id="row<c:out value="${ent.getEntitlementId()}"/>">
                        
                        <td><c:out value="${ent.getEmployeeId()}"/></td>
                        <td><a href="#"> <c:out value="${ent.getEmployeeName()}"/></a></td>
                        <td><c:out value="${ent.getLeaveTypeName()}"/></td>
                        <td><c:out value="${ent.getLeavePeriodName()}"/></td>
                        <td><c:out value="${ent.getEntitlements()}"/></td>
                        <td></td>
                    </tr>      

                </tbody>
            </c:forEach>
        </table> 

    </div>
</div>
