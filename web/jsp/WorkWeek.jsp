<%-- 
    Document   : WorkWeek
    Created on : 14 Apr, 2017, 3:56:58 PM
    Author     : sayansen
--%>

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        


        <div class="panel panel-default" style="width:50%"  id="workWeekList">
            <div class="panel-heading"><span style="color: green">Current Work Week</span></div>
            <div class="panel-body">
                <form id="workWeekform"  title="Work Week">
                   
                        <div class="form-group">
                            <c:forEach items="${workWeekList}" var="workDay">        
                                <div class="row">
                                    <div class="col-xs-4"><label><c:out value="${workDay.getDayName()}"/></label></div>
                                    <div class="col-xs-4"><select class="day form-control" disabled >
                                            <option><c:out value="${workDay.getDayType()}"/></option>
                                        </select></div>
                                </div>
                            </c:forEach>

                        </div>
       
                    <div class="row">
                        <div class="col-xs-2">
                            <button type="button" id="editWorkWeekbtn" class="btn btn-warning" onclick="configWorkWeek()" title="Click to edit form">Edit</button></div>
                    </div>

                </form>
            </div>
            <div class='panel-footer'>
                <span style="color: red ">* Click on Edit button to edit.  </span>
            </div>
        </div>











