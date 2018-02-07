<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="table-responsive">
    <table class="table table-hover">
        <thead>
            <tr>
                <th></th>
                <th>Name</th>
                <th>Paid?</th>
                <th>Carry forward</th>
                <th>Limited annually</th>
                <th>Encashable</th>
                <th></th>  
            </tr>
        </thead>

        <tbody>
            <c:forEach items="${leaveTypes}" var="leave">
                <tr id="row<c:out value="${leave.getLeaveTypeId()}"/>">
                    <td> <input type="checkbox" value="<c:out value="${leave.getLeaveTypeId()}"/>"></td>
                    <td><a href="javascript:show(<c:out value="${leave.getLeaveTypeId()}"/>)"> <c:out value="${leave.getLeaveTypeName()}"/></a></td>
                    <td><c:out value="${leave.getPaidLeaveType()}"/></td>
                    <td><c:out value="${leave.getCarryForwardLeave()}"/></td>
                    <td><c:out value="${leave.getEntitlementCap()}"/></td>
                    <td><c:out value="${leave.getEncashable()}"/></td>
                    <td></td>
                </tr>      
            </c:forEach>
        </tbody>

    </table>
</div>       



</table> 

