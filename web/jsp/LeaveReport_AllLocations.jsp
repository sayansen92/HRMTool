<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<select id="leaveReportLocations" class="form-control">
    <option value="all">--All--</option>
    <c:forEach items="${locationList}" var="loc">
        <option value="<c:out value="${loc.getLocationName()}"/> ">
            <c:out value="${loc.getLocationName()}"/>
        </option>
    </c:forEach>
</select>