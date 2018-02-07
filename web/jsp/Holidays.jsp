<%-- 
    Document   : Holidays
    Created on : 24 Apr, 2017, 4:02:59 PM
    Author     : sayansen
--%>

<html>
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
       
       
    </head>
    <body>

        <div class="panel panel-default" >
            <div class="panel-heading">Holidays</div>
            <div class="panel-body">
                
                <button type="button" class="btn btn-danger " onclick="cancelUpdateHoliday()" id="holidayUpdateCancelbtn"style="display:none">Cancel</button>
                <table class="table table-hover table-condensed " > 
                    <thead>
                    <td></td>
                    <td>Name</td>
                    <td>Date</td>
                    <td>Half Day/Full Day</td>
                    <td>Repeats annually?</td>
                    </thead>
                    <c:forEach items="${holidayList}" var="holiday">    
                        <tbody>
                            <tr id="holidayrow<c:out value="${holiday.getHolidayId()}"/>">
                                <td><input type="checkbox" class="holidayCheck" value="<c:out value="${holiday.getHolidayId()}"/>"></td>
                                <td><a href="javascript:editHoliday(<c:out value="${holiday.getHolidayId()}"/>)"><c:out value="${holiday.getHolidayName()}"/></a></td>
                                <td><c:out value="${holiday.getHolidayDate()}"/></td>
                                <td><c:out value="${holiday.getHolidayType()}"/></td>
                                <td><c:out value="${holiday.getRepeatAnnually()}"/></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </tbody>
                    </c:forEach>   
                </table> 
            </div>
        </div>


    </body>
</html>
