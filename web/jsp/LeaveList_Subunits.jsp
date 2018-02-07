
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<select id="leaveListSubunit" class="form-control">
    <option value="all">--All--</option>
    <c:forEach items="${subunitList}" var="sub">
        <option value="<c:out value="${sub.getSubUnitName()}"/> ">
            <c:out value="${sub.getSubUnitName()}"/>
        </option>
    </c:forEach>
</select>