<%-- 
    Document   : OneEmployee
    Created on : 2 May, 2017, 9:48:27 PM
    Author     : sayansen
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${employee.getEmployeeId()==0}">
    <div class="input-group ">
        <input id="selectedEmpId" type="number" value="0" min="0" style="width: 50px" placeholder="ID" class="form-control">
        <input id="selectOneEmp"  type="text" onkeyup="employeeValidate('selectOneEmp')" style="width: 250px" placeholder="Type for hints" data-toggle="tooltip" title="Please choose the correct name from the list only" class="form-control">
    </div>
</c:if>
<c:if test="${employee.getEmployeeId()!=0}">
    <div class="input-group">
        <input id="selectedEmpId" type="number" min="0" value="<c:out value="${employee.getEmployeeId()}"/>"  placeholder="ID" style="width: 50px" class="form-control">
        <input id="selectOneEmp"  type="text" onkeyup="employeeValidate('selectOneEmp')" style="width: 250px" value="<c:out value="${employee.getEmployeeName()}"/>" placeholder="Type for hints" data-toggle="tooltip" title="Please choose the correct name from the list only" class="form-control">
    </div>
</c:if>