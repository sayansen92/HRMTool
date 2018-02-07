<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>HRM Tool</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css">
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
        <script src="js/alljavascripts.js"></script>
        <style>
            div.row{margin-bottom: 20px;}
            body {
                background-image: url("images/bg-1.jpg");
                background-size: 1366px 680px;

            }

            button {
                background: green;
                cursor: pointer;
                color: white;
            }

            td{
                cursor: pointer;

            }
            /*            .panel-default {
                            background: transparent;
                        }*/
            .panel-default .panel-heading{
                background: darkseagreen;
            }
            .panel-footer{
                background: transparent;
            }
            .table-head{
                background: #91ccd7;
            }
            .dropdown-menu{
                background: #bce8f1;

            }
            /*            .modal-content{
                            background: transparent;
                            background-image: url("images/bg-1.jpg");    
                            background-size:cover;
                        }*/
            .modal-header{

                background: darkseagreen;
            }

            .navbar{
                cursor:pointer ;
                background:transparent;
                background-color: transparent;
                background-image:none;
                border-color:transparent;
                box-shadow:none;
                padding-top: 15px;
                padding-bottom: 0px;
                border: black;
                border-radius: 0;
                margin-bottom: 0;
                font: 20px Montserrat, Berlin Sans FB;
                font-size: 12px;
                letter-spacing: 5px;
            }
            /*----------*/
            ul ul ul {
                width: 300px;
            }
            li:hover ul
            {
                display: block;

            }

        </style>

    </head>
    <body>
        <!--NAVIGATION PANEL-->
        <div class="container-fluid">
            <nav class="navbar navbar-default navbar-static-top">
                <div class="container-fluid">
                    <ul class="nav nav-justified navbar-left">
                        <li class="btn-danger"><span class="glyphicon glyphicon-time"></span><span id="clock"></span></li>
                        <li class="btn-danger"><span id="date"></span></li>
                        <li><a href="#">Admin</a></li>
                        <li><a href="#">PIM</a></li>
                        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">Leave<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a>Configure</a>
                                    <ul>
                                        <li><a href="javascript:showLeave('leavePeriod');">Configure Leave period</a></li>
                                        <li><a href="javascript:showLeave('leaveTypeDiv');">Configure Leave Types</a></li>
                                        <li><a href="javascript:showLeave('workWeek');">Configure Work Week</a></li>
                                        <li><a href="javascript:showLeave('holiday');">Configure Holidays</a></li>
                                    </ul>
                                </li>
                                <li><a href="javascript:showLeave('entitlement');">Entitlements</a></li>
                                <li><a href="javascript:showLeave('leaveReport');">Reports</a></li>
                                <li><a href="javascript:showLeave('leaveList');">Leave List</a></li>
                                <li><a data-toggle="modal" data-target="#leaveApplydiv" onclick="showLeaveApply()">Assign Leave</a></li>
                                <li><a data-toggle="modal" data-target="#leaveApplydiv" onclick="showLeaveApply()">Apply Leave</a></li>
                            </ul>
                        </li>  
                        <li><a href="#">Time</a></li>
                        <li><a href="#">Recruitment</a></li> 
                        <li><a href="#">Performance</a></li> 
                        <li><a href="#">Dashboard</a></li> 
                        <li><a href="#">Directory</a></li> 

                    </ul>

                </div>
            </nav>
        </div>
        <!--LEAVE PERIOD-->
        <div class="container-fluid" style="display: none" id="leavePeriod">
            <div class="modal fade" id="addLeavePeriod" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Leave Period</h4>
                        </div>
                        <div class="modal-body">
                            <form id="leavePeriodform" >
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-xs-6">
                                            <label for="startDate">Start Period:<span style="color: red">*</span></label>
                                            <input type="date" id="startDate" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-xs-6">
                                            <label for="endDate">End Period:<span style="color: red">*</span></label>
                                            <input type="date" id="endDate" class="form-control"></div>
                                    </div>
                                </div>
                                <label for="currentLeavePeriodcheck">Make it Current Leave Period:</label>
                                <input type="checkbox" value="currentLeavePeriodFlag" id="currentLeavePeriodcheck" >
                            </form>
                        </div>
                        <div class="modal-footer">
                            <span style="color: red; float: left">*Required field.</span>
                            <button type="button" class="btn btn-success" onclick="insertLeavePeriod()">Save</button>
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                            <p style="float: left"></p>    
                        </div>
                    </div>
                </div>
            </div>

            <div class="container-fluid"> 
                <div class="panel panel-default">
                    <div class="panel-heading"><h5>Add Leave Period</h5> </div>
                    <div class="panel-body">
                        <button type="button" class="btn btn-success"  data-toggle="modal" data-target="#addLeavePeriod">Add</button>
                    </div>

                </div>
            </div>
            <div class="container-fluid" id="leavePeriodconfig" style="display:none">
                <div class="panel panel-default">
                    <div class="panel-heading">Current Leave Period </div>
                    <div class="panel-body">
                        <div class="panel-body" id="leavePeriodDisplay"> </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">Other Leave Periods </div>
                    <div class="panel-body">
                        <div class="panel-body" id="otherLeavePeriodDisplay"> </div>
                    </div>
                </div>
            </div>
        </div>
        <!--LEAVE TYPE-->
        <div class="container-fluid" id="leaveTypeDiv" style="display:none">
            <div class="modal fade" id="leaveTypeConfig" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Leave Type</h4>
                        </div>
                        <div class="modal-body">
                            <form id="leaveTypeform"  title="Leave Type" >

                                <div class="row">
                                    <div class="col-xs-6">
                                        <label for="leaveTypeName">Type name:<span style="color: red">*</span></label>
                                        <input type="text" id="leaveTypeName" autofocus class="form-control"></div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <label for="leaveConstraintsAdd">Does it have any constraints?</label>
                                        <input type="checkbox" id="leaveConstraintsAdd" onclick="toggleLeaveConstraints()">
                                    </div>
                                </div>
                                <div class="container-fluid" id="leaveInnerConstraints" style="display:none" >
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <label for="paidLeaveType">Paid Leave?<span style="color: red">*</span></label>
                                            <input type="checkbox" id="paidLeaveType">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <label for="carryForwardLeave">Carry forward leave next year?<span style="color: red">*</span></label>
                                            <input type="checkbox" id="carryForwardLeave">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <label for="entitlementCap">Any capping on entitlement?<span style="color: red">*</span></label>
                                            <input type="checkbox" id="entitlementCap">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <label for="encashableLeaveType">Encashable?<span style="color: red">*</span></label>
                                            <input type="checkbox" id="encashableLeaveType">
                                        </div>
                                    </div>
                                </div>

                            </form>
                        </div>
                        <div class="modal-footer">
                            <span style="color: red; float: left">*Required field.</span>
                            <button type="button" class="btn btn-success" onclick="setleaveTypes()">Save</button>
                            <button type="button" class="btn btn-warning" onclick="reset('leaveTypeform')">Reset</button>
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-fluid">
                <div class="panel panel-default">
                    <div class="panel-heading"><h5>Add/Delete Leave Type</h5></div>
                    <div class="panel-body">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#leaveTypeConfig">Add</button>
                        <button type="button" class="btn btn-danger" onclick="deleteLeaveType()">Delete</button>
                    </div>
                </div>
            </div>

            <div id="displayLeaveTypes" class="container-fluid" style="display:none">

                <div id="displayLeaveTypesPanel" class="panel panel-default">

                </div>

            </div>
        </div>
        <!--WORKWEEK-->
        <div class="container-fluid" id="workWeek" style="display:none;" >

        </div>
        <!--HOLIDAY-->
        <div class="container-fluid" id="holiday" style="display:none">
            <div id="holidayConfig" class="modal fade" role="dialog"> 
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Add Holiday</h4>
                        </div>
                        <div class="modal-body">
                            <form id="holidayAddForm">
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-xs-3">
                                            <label for="holidayName">Name:<span style="color: red">*</span></label></div>
                                        <div class="col-xs-6"><input type="text" id="holidayName" onclick="canceldiv('holidaySaveResult')" class="form-control"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-3">
                                            <label for="holidayDate">Date:<span style="color: red">*</span></label></div>
                                        <div class="col-xs-6"><input type="date" id="holidayDate" class="form-control"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-3">
                                            <label for="holidayType">Full Day/Half Day:<span style="color: red">*</span></label></div>
                                        <div class="col-xs-6"> <select id="holidayType" class="form-control">
                                                <option value="Full Day">Full Day</option>
                                                <option value="Half Day">Half Day</option>
                                            </select></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-3">
                                            <label for="currentLP">For leave period:</label></div>
                                        <div class="col-xs-6" id="currentLP"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-3">
                                            <label for="repeatAnnually">Repeat annually</label></div>
                                        <div class="col-xs-2"><input type="checkbox" id="repeatAnnually"></div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <span style="color: red; float: left">*Required fields.</span>

                            <button type="button" class="btn btn-success" id="btn1" onclick="saveHoliday()">Save</button>
                            <button type="button" class="btn btn-info" id="btn2" onclick="reset('holidayAddForm')">Reset</button>
                            <button type="button" class="btn btn-danger" id="btn3" data-dismiss="modal">Cancel</button>

                            <p id="holidaySaveResult" style="display:none; float: left"></p>
                        </div>
                    </div>                  
                </div>
            </div>
            <div id="holidaybtns" class="panel panel-default">
                <div class="panel-heading"><h5>Add/Delete Holidays</h5></div>
                <div class="panel-body">
                    <div class="form-group">
                        <button type="button" class="btn btn-success" id="btn1" data-toggle="modal" data-target="#holidayConfig">Add</button>
                        <button type="button" class="btn btn-danger" id="btn3" onclick="deleteHoliday()">Delete</button>
                    </div></div>
            </div>
            <!--holiday search panel-->
            <div id="holidaySearch" class="panel panel-default"> 
                <div class="panel-heading"><h5>Search Holidays</h5></div>
                <div class="panel-body">
                    <form id="holidaySearchForm">
                        <div class="form-group">
                            <div class="row">
                                <div class="col-xs-1">
                                    <label for="holidayFromDate">From:<span style="color: red">*</span></label></div>
                                <div class="col-xs-3"><input type="date" id="holidayFromDate" class="form-control"></div>
                            </div>
                            <div class="row">
                                <div class="col-xs-1">
                                    <label for="holidayToDate">To:<span style="color: red">*</span></label></div>
                                <div class="col-xs-3"><input type="date" id="holidayToDate" class="form-control"></div>
                            </div>
                            <button type="button" class="btn btn-success " onclick="holidaySearch()">Search</button>
                            <button type="button" class="btn btn-info " onclick="resetHolidaySearch()">Reset</button>
                            <button type="button" class="btn btn-danger " onclick="canceldiv('displayHolidays')">Cancel</button>
                            <span style="color: red; float: right">*Required fields.</span>
                        </div>
                    </form>
                </div>
            </div>
            <!--            holiday display panel-->
            <div id="displayHolidays" style="display:none">

            </div>
        </div>
        <!--ENTITLEMENTS-->
        <div class="container-fluid" style="display: none" id="entitlement">
            <div class="modal fade" id="entitlementConfig" role="dialog">
                <div class="modal-dialog" style="width:1000px">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Entitlements</h4>
                        </div>
                        <div class="modal-body">
                            <form id="entitlementForm">
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-xs-2"><label for="selectAllEmp">Add to Multiple Employees</label> </div>
                                        <div class="col-xs-2"><input id="selectAllEmp" type="checkbox" onclick="showInnerEntitlement('selectAllEmp')"></div>
                                    </div>
                                    <div class="row" id="location" style="display:none">
                                        <div class="col-xs-2"> <label for="location">Location<span style="color: red">*</span></label></div>
                                        <div class="col-xs-3" id="selectLocation"> </div>
                                        <div class="col-xs-2"> <label for="subunit">Sub-Unit<span style="color: red">*</span></label></div>
                                        <div class="col-xs-3" id="selectSubunit"> </div>
                                    </div>

                                    <div class="row" id="entitlementEmpname" style="display:block">
                                        <div class="col-xs-2" > <label for="empName" >Employee:<span style="color: red">*</span></label></div>
                                        <div class="col-xs-10">
                                            <div class="input-group" id="setEmpName"></div>
                                            <div class="input-group" id="employeeValid"></div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-2"><label for="typleave">Leave Type<span style="color: red">*</span></label></div>
                                        <div class="col-xs-3" id="selectLeaveType"></div>
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-2"> <label for="entitleLeavePeriod">Leave Period<span style="color: red">*</span></label></div>
                                        <div class="col-xs-3" id="selectLeavePeriod"></div>
                                    </div> 
                                    <div class="row">
                                        <div class="col-xs-2"><label for="entitlements">Entitlements<span style="color: red">*</span></label></div>
                                        <div class="col-xs-3"><input id="entitlements" type="number" min="0" max="365" class="form-control"></div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <span style="color: red; float: left" >*Required fields.</span>
                            <button type="button" class="btn btn-success" id="saveEntitlementbtn" onclick="saveEntitlements()">Save</button>
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                            <p id="saveEntitlementResult" style="float: left"></p>    
                        </div>
                    </div>
                </div>

            </div>
            <div class="container-fluid"> 
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-group">
                            <button type="button" class="btn btn-success" id="btn1" data-toggle="modal" data-target="#entitlementConfig" onclick="showEntitlementElements()">Add</button>
                            
                        </div></div>

                </div></div>
            <div class="container-fluid" id="entitlementSearch">
                <div class="panel panel-default">
                    <div class="panel-heading">Leave Entitlements </div>
                    <div class="panel-body">
                        <div class="row">
                            <form id="searchEntForm" class="form-inline">
                                <div class="form-group">
                                    <div class="col-xs-4">
                                        <label for="entitlementSearchempName">Employee<span style="color: red">*</span></label>
                                        <input id="entitlementSearchempName"type="text" class="form-control">
                                    </div>
                                    <div class="col-xs-4">
                                        <label for="entitlementSearchLeaveType">Leave Type<span style="color: red">*</span></label>
                                        <div id="entitlementSearchLeaveType"></div>
                                    </div>
                                    <div class="col-xs-4">
                                        <label for="entitlementSearchLeavePeriod">Leave Period<span style="color: red">*</span></label>
                                        <div  id="entitlementSearchLeavePeriod"></div>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>
                    <div class="panel-footer">
                        <button type="button" class="btn btn-success" onclick="searchEntitlement()">Search</button>
                        <button type="button" class="btn btn-default" onclick="canceldiv('displayEntitlements')" >Cancel</button>
                        <span style="color: red">*Required Fields</span>
                    </div>
                </div>
            </div>

            <div id="displayEntitlements" class="container-fluid">

            </div>

        </div>
        <!--APPLY LEAVE-->
        <div class="modal fade" id="leaveApplydiv" role="dialog">
            <div class="modal-dialog" style="width:500px;" >
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Apply for Leave</h4>
                    </div>
                    <div class="modal-body">
                        <form id="leaveApplyform">
                            <div class="row">
                                <div class="col-xs-3"><label for="applyEmployeeId">Employee ID:<span style="color: red">*</span></label></div>
                                <div class="col-xs-6"><input type="number" id="applyEmployeeId" min="0" onchange="calculateLeaveBal()" class="form-control"></div>
                            </div>
                            <div class="row">
                                <div class="col-xs-3"><label for="applyLeavetype">Leave type:<span style="color: red">*</span></label></div>
                                <div class="col-xs-6" id="applyLeavetype"></div>
                            </div>

                            <div class="row">
                                <div class="col-xs-3"><label for="applyLeaveFrom">From:<span style="color: red">*</span></label></div>
                                <div class="col-xs-6"><input type="date" id="applyLeaveFrom" onchange="calculateLeaveBal()" class="form-control"></div>
                            </div>                        
                            <div class="row">
                                <div class="col-xs-3"><label for="applyLeaveTo">To:<span style="color: red">*</span></label></div>
                                <div class="col-xs-6"><input type="date" id="applyLeaveTo" onchange="calculateLeaveBal()" class="form-control"></div>
                            </div>
                            <div class="row" style="display:none" id="leaveBalancediv">
                                <div class="col-xs-3"><label for="applyLeaveTypeBalance">Balance:</label></div>
                                <div class="col-xs-6" id="applyLeaveTypeBalance"></div>
                            </div>
                        </form>
                        <p id="leaveApplyErrormsg" style="display:none"></p>
                        <p id="leaveApplyOthermsg" style="display:none"></p>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success" onclick="applyLeave()">Apply</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                        <span style="color: red">*Required Fields</span>                  
                    </div>
                </div>
            </div>
        </div>
        <!--LEAVE LIST-->
        <div id="leaveList" class="container-fluid" style="display: none">
            <div id="leaveListSearch" class="container-fluid">
                <div class="panel panel-default">
                    <div class="panel-heading"><h5>Leave List Search</h5></div>
                    <div class="panel-body">
                        <form id="leaveListSearchForm">
                            <div class="row">
                                <div class="col-xs-2">
                                    <label for="leaveListFrom">From:<span style="color: red">*</span></label>
                                </div>
                                <div class="col-xs-4">
                                    <input type="date" id="leaveListFrom" class="form-control">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-2">
                                    <label for="leaveListFrom">To:<span style="color: red">*</span></label>
                                </div>
                                <div class="col-xs-4">
                                    <input type="date" id="leaveListTo" class="form-control">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-2">
                                    <label for="leaveStatus">Show leave with status:</label>
                                </div>
                                <div class="col-xs-4">
                                    <select id="leaveStatus" class="form-control">
                                        <option value="all">--All--</option>
                                        <option value="Pending">Pending</option>
                                        <option value="Approved">Approved</option>
                                        <option value="Rejected">Rejected</option>
                                        <option value="Canceled">Canceled</option>
                                        <option value="Taken">Taken</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-2">
                                    <label for="leaveListEmployee">Employee:</label>
                                </div>
                                <div class="col-xs-4">
                                    <input id="leaveListEmployee" type="text" class="form-control">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-2">
                                    <label for="leaveListSubunit">Sub-Unit:</label>
                                </div>
                                <div class="col-xs-4" id="llSubunit">

                                </div>
                            </div>
                    </div>
                    </form>
                    <div class="panel-footer">
                        <button class="btn btn-success" onclick="searchLeave()">Search</button>
                        <button class="btn btn-info">Reset</button>
                        <p id="leaveListErrorMsg" style="float: right"></p>
                    </div>
                </div>
            </div>
            <div id="showLeaveList" class="container-fluid" style="display: none">

            </div>

        </div>
        <!--LEAVE REPORT-->
        <div class="container-fluid" id="leaveReport" style="display:none"> 
            <div class="panel panel-default">
                <div class="panel-heading"><h5>Leave Usage Report</h5></div>
                <div class="panel-body">
                    <form id="leaveReportform">
                        <div class="row">
                            <div class="col-xs-2">
                                <label for="generateReportFor" class="input-group">Generate for:<span style="color: red">*</span></label>
                                <select id='generateReportFor' onchange='showInnerReportsDiv(this.value)' class="form-control">
                                    <option value="0">--Select--</option>
                                    <option value="1">Employee</option>
                                    <option value="2">Leave Type</option>
                                </select>
                            </div>
                        </div>
                        <div class="row" id='forEmployee' style="display:none"> 
                            <div class="col-xs-2">
                                <label for="leaveReportEmpName">Employee:<span style="color: red">*</span></label>
                                <input typ="text" id="leaveReportEmpName" class="form-control">
                            </div>
                            <div class="col-xs-2">
                                <label for="leaveReportLPselect1">For:<span style="color: red">*</span></label>
                                <div id="leaveReportLPselect1"></div>
                            </div>
                        </div>
                        <div class="row" id='forLeaveType' style="display:none"> 
                            <div class="col-xs-3">
                                <label for="leaveReportLTnsme" class="input-group">Leave Type:<span style="color: red">*</span></label>
                                <div id="leaveReportLTnsme" class="input-group"></div>
                            </div>
                            <div class="col-xs-3">
                                <label for="leaveReportLPselect2" class="input-group">For:<span style="color: red">*</span></label>
                                <div  id="leaveReportLPselect2" class="input-group"></div>
                            </div>
                            <div class="col-xs-3">
                                <label for="leaveReportLocation" class="input-group">Location:<span style="color: red">*</span></label>
                                <div  id="leaveReportLocation" class="input-group"></div>
                            </div>
                            <div class="col-xs-3">
                                <label for="leaveReportSubunit" class="input-group">Subunit:<span style="color: red">*</span></label>
                                <div  id="leaveReportSubunit" class="input-group"></div>
                            </div>


                        </div>
                    </form>
                </div>
                <div class="panel-footer">
                    <button class="btn btn-warning" onclick="viewReport()">View</button>
                    <p id="leaveReportErrormsg" style="float: right"></p>
                </div>                     
            </div>

            <div class="panel panel-default">
                <div class="panel-heading"><h5>Leave Usage Report</h5></div>
                <div class="panel-body" id="leaveReportView">

                </div>
            </div>
        </div>
    </body>
</html>
