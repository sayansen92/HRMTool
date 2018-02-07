
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<select multiple onchange="selectEmpName(this.value)" class="form-control" style="width: 155%" >
    <c:forEach items="${employeeList}" var="emp">
        <option value="<c:out value="${emp.getEmployeeId()}"/> ">
            <c:out value="${emp.getEmployeeId()}"/>-<c:out value="${emp.getEmployeeName()}"/>
        </option>
    </c:forEach>
</select>

