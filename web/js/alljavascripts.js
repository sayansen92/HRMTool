




function showLeave(divid) {
    document.getElementById('leavePeriod').style.display = "none";
    document.getElementById('leavePeriodconfig').style.display = "none";
    document.getElementById('workWeek').style.display = "none";
    document.getElementById('holiday').style.display = "none";
    document.getElementById('entitlement').style.display = "none";
    document.getElementById('leaveTypeDiv').style.display = "none";
    document.getElementById('leaveList').style.display = "none";
    document.getElementById('leaveReport').style.display = "none";
    document.getElementById(divid).style.display = "block";

    if (divid === 'leavePeriod')
    {

        getLeavePeriod();
        getOtherLeavePeriods();
    }
    if (divid === 'leaveTypeDiv')
    {
        var formdata = "xyz";
        $.ajax({
            type: "post",
            data: formdata,
            url: "DisplayLeaveType",
            success: function (data)
            {
                document.getElementById('displayLeaveTypes').style.display = "block";
                $('#displayLeaveTypesPanel').html(data);
            }

        });
    }

    if (divid === 'leaveTypeConfig')
    {
        document.getElementById('leaveTypeConfig').style.display = "block";
        document.getElementById('displayLeaveTypes').style.display = "block";
    }
    if (divid === 'entitlement') {
        var formdata = "xyz";
        $.ajax({
            type: "post",
            data: formdata,
            url: "ShowLeaveTypes",

            success: function (data)
            {
                $('#entitlementSearchLeaveType').html(data);

            }
        });
        showAllLeaveperiod('#entitlementSearchLeavePeriod');

    }
    if (divid === 'workWeek') {

        var formdata = "xyz";
        $.ajax({
            type: "post",
            data: formdata,
            url: "ShowWorkWeek",
            success: function (data)
            {
                $('#workWeek').html(data);
                $('select.day').on('change', function () {
                    document.getElementById("saveWorkWeekbtn").style.display = "block";
                    document.getElementById("editWorkWeekbtn").style.display = "none";
                });
            }
        });

    }
    if (divid === 'leaveList') {
        document.getElementById('leaveListSearchForm').reset();
        document.getElementById('showLeaveList').style.display="none";
        var formdata = "xyz";
        $.ajax({
            type: "post",
            data: formdata,
            url: "LeaveListSubunits",
            success: function (data)
            {
                $('#llSubunit').html(data);
            }
        });
    }
    if (divid === 'holiday') {

        var formdata = "xyz";
        $.ajax({
            type: "post",
            data: formdata,
            url: "DisplayAllLeavePeriods",
            success: function (data)
            {
                $('#currentLP').html(data);

            }

        });

    }

    if (divid === 'leaveReport') {


    }
}
function showLeavePeriodConfigModal() {
    document.getElementById('addLeavePeriod').style.display = "block";

}

function configWorkWeek()
{

    var formdata = "xyz";
    $.ajax({
        type: "post",
        data: formdata,
        url: "EditWorkWeek",
        success: function (data)
        {
            document.getElementById('leavePeriod').style.display = "none";
            document.getElementById('leavePeriodconfig').style.display = "none";
            document.getElementById('displayLeaveTypes').style.display = "none";
            document.getElementById('workWeek').style.display = "block";
            document.getElementById('workWeekList').style.display = "none";
            $('#workWeek').html(data);
            $('select.day').on('change', function () {
                document.getElementById("saveWorkWeekbtn").style.display = "block";

            });
        }
    });
}

function reset(divid)
{
    document.getElementById(divid).reset();


}
function canceldiv(divid)
{
    document.getElementById(divid).style.display = "none";
    if (divid === 'displayEntitlements') {
        reset('searchEntForm');
    }
}
function edit(divid, task)
{
    document.getElementById(divid).disabled = task;
}

function insertLeavePeriod()
{
    var formdata = "xyz";
    var a = new Date(document.getElementById('startDate').value);
    var b = new Date(document.getElementById('endDate').value);
    var startDate = String(a.getFullYear()) + "/" + String(a.getMonth() + 1) + "/" + String(a.getDate());
    var endDate = String(b.getFullYear()) + "/" + String(b.getMonth() + 1) + "/" + String(b.getDate());
    var currentLeavePeriodcheck = document.getElementById('currentLeavePeriodcheck').checked;
    var paramlist = "?startDate=" + startDate + "&endDate=" + endDate + "&currentLeavePeriodFlag=" + currentLeavePeriodcheck;
    $.ajax({
        type: "post",
        data: formdata,
        url: "SaveLeavePeriod" + paramlist,
        success: function (data)
        {
            document.getElementById('leavePeriodconfig').style.display = "block";
            document.getElementById("leavePeriodform").reset();
            getLeavePeriod();
        }

    });
}

function getLeavePeriod() {
    var a = new Date();
    var currentDate = String(a.getFullYear()) + "/" + String(a.getMonth() + 1) + "/" + String(a.getDate());

    var paramlist = "?currentDate=" + currentDate;
    var formdata = "xyz";
    $.ajax({
        type: "post",
        data: formdata,
        url: "GetLeavePeriod" + paramlist,
        success: function (data)
        {
            document.getElementById('leavePeriodconfig').style.display = "block";
            $('#leavePeriodDisplay').html(data);

        }

    });

}
function getOtherLeavePeriods() {
    var formdata = "xyz";
    $.ajax({
        type: "post",
        data: formdata,
        url: "GetOtherLeavePeriods",
        success: function (data)
        {

            $('#otherLeavePeriodDisplay').html(data);

        }

    });

}
function showAllLeaveperiod(divid)
{
    var formdata = "xyz";
    if (divid === "#entitlementSearchLeavePeriod")
    {
        $.ajax({
            type: "post",
            data: formdata,
            url: "LeavePeriodSelect",
            success: function (data)
            {
                $(divid).html(data);

            }

        });

    } else {

        $.ajax({
            type: "post",
            data: formdata,
            url: "DisplayAllLeavePeriods",
            success: function (data)
            {
                $(divid).html(data);

            }

        });
    }
}
function setleaveTypes()
{
    var formdata = "xyz";
    var leaveTypeName = document.getElementById('leaveTypeName').value;

    var paidLeaveType = document.getElementById('paidLeaveType').checked;
    var carryForwardLeave = document.getElementById('carryForwardLeave').checked;
    var entitlementCap = document.getElementById('entitlementCap').checked;
    var encashable = document.getElementById('encashableLeaveType').checked;
    var paramlist = "?leaveTypeName=" + leaveTypeName + "&paidLeaveType=" + paidLeaveType + "&carryForwardLeave=" + carryForwardLeave + "&entitlementCap=" + entitlementCap + "&encashable=" + encashable;

    alert(paramlist);
    $.ajax({
        type: "post",
        data: formdata,
        url: "SaveLeaveType" + paramlist,
        success: function (data)
        {
            document.getElementById("leaveTypeform").reset();
            document.getElementById('displayLeaveTypes').style.display = "block";
            document.getElementById("leaveConstraintsAdd").checked = false;
            $('#displayLeaveTypesPanel').html(data);


        }

    });
}
function deleteLeaveType()
{
    var formdata = "xyz";
    var leaveTypeList = [];
    $.each($("input:checked"), function () {
        leaveTypeList.push($(this).val());
    });

    var paramlist = "?leaveTypeName=" + leaveTypeList;
    $.ajax({
        type: "post",
        data: formdata,
        url: "DeleteleaveType" + paramlist,

        success: function (data)
        {
            showLeave('leaveTypeDiv');

        }
    });
}

function show(id)
{
    var leaveTypeId = id;
    var formdata = "xyz";
    var paramlist = "?leaveTypeId=" + leaveTypeId;
    $.ajax({
        type: "post",
        data: formdata,
        url: "GetLeaveType" + paramlist,
        success: function (data)
        {
            $('#row' + id).html(data);
        }
    });

}

function updateLeaveType(id)
{
    var formdata = "xyz";
    var leaveTypeId = id;
    var leaveTypeName = document.getElementById("leaveTypeNameNew").value;
    var paidLeaveType = document.getElementById('paidLeaveTypeNew').checked;
    var carryForwardLeave = document.getElementById('carryForwardLeaveNew').checked;
    var entitlementCap = document.getElementById('entitlementCapNew').checked;
    var encashable = document.getElementById('encashableLeaveTypeNew').checked;
    var paramlist = "?leaveTypeId=" + leaveTypeId + "&leaveTypeName=" + leaveTypeName + "&paidLeaveType=" + paidLeaveType + "&carryForwardLeave=" + carryForwardLeave + "&entitlementCap=" + entitlementCap + "&encashable=" + encashable;

    $.ajax({
        type: "post",
        data: formdata,
        url: "UpdateLeaveType" + paramlist,
        success: function (data)
        {
            document.getElementById("leaveTypeform").reset();
            $('#displayLeaveTypes').html(data);
        }
    });

}

function saveWorkWeek()
{

    var formdata = "xyz";
    var monday = document.getElementById("monday").value;
    var tuesday = document.getElementById("tuesday").value;
    var wednesday = document.getElementById("wednesday").value;
    var thursday = document.getElementById("thursday").value;
    var friday = document.getElementById("friday").value;
    var saturday = document.getElementById("saturday").value;
    var sunday = document.getElementById("sunday").value;
    var workWeekTypes = monday + "," + tuesday + "," + wednesday + "," + thursday + "," + friday + "," + saturday + "," + sunday;
    var workweek = "Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday";

    var paramlist = "?workWeek=" + workweek + "&workWeekTypes=" + workWeekTypes;

    $.ajax({
        type: "post",
        data: formdata,
        url: "SaveWorkWeek" + paramlist,
        success: function (data)
        {
            $('#workWeek').html(data);
        }
    });
}
function saveHoliday()
{
    var formdata = "xyz";
    var holidayName = document.getElementById("holidayName").value;
    var a = new Date(document.getElementById('holidayDate').value);
    var holidayDate = String(a.getFullYear()) + "/" + String(a.getMonth() + 1) + "/" + String(a.getDate());
    var leavePeriodId = document.getElementById("leavePeriodDropdown").value;
    var repeatAnnually = document.getElementById('repeatAnnually').checked;
    if (repeatAnnually)
    {
        repeatAnnually = "Yes";
    } else
    {
        repeatAnnually = "No";
    }
    var holidayType = document.getElementById('holidayType').value;
    var paramlist = "?holidayName=" + holidayName + "&holidayDate=" + holidayDate + "&repeatAnnually=" + repeatAnnually + "&holidayType=" + holidayType + "&leavePeriodId=" + leavePeriodId;

    $.ajax({
        type: "post",
        data: formdata,
        url: "SaveHoliday" + paramlist,
        success: function (data)
        {
            document.getElementById("holidayAddForm").reset();
            document.getElementById("holidaySaveResult").style.display = "block";
            $('#holidaySaveResult').html(data);
        }
    });


}

function holidaySearch()
{
    var formdata = "xyz";
    var a = new Date(document.getElementById('holidayFromDate').value);
    var holidayFromDate = String(a.getFullYear()) + "/" + String(a.getMonth() + 1) + "/" + String(a.getDate());
    var b = new Date(document.getElementById('holidayToDate').value);
    var holidayToDate = String(b.getFullYear()) + "/" + String(b.getMonth() + 1) + "/" + String(b.getDate());
    var paramlist = "?holidayFromDate=" + holidayFromDate + "&holidayToDate=" + holidayToDate;
    $.ajax({
        type: "post",
        data: formdata,
        url: "SearchHoliday" + paramlist,
        success: function (data)
        {

            document.getElementById("displayHolidays").style.display = "block";
            $('#displayHolidays').html(data);

        }
    });
}

function editHoliday(id)
{

    var formdata = "xyz";
    var paramlist = "?holidayId=" + id;
    $.ajax({
        type: "post",
        data: formdata,
        url: "GetOneHoliday" + paramlist,
        success: function (data)
        {
            $('#holidayrow' + id).html(data);

        }
    });
}

function updateHoliday(id)
{

    var newHolidayName = document.getElementById('newHolidayName').value;
    var newHolidayDate = document.getElementById('newHolidayDate').value;
    var newHolidayType = document.getElementById('newHolidayType').value;
    var newRepeatFlag = document.getElementById('newRepeatFlag').value;
    var formdata = "xyz";
    var paramlist = "?holidayId=" + id + "&holidayName=" + newHolidayName + "&holidayDate=" + newHolidayDate + "&holidayType=" + newHolidayType + "&repeatAnnually=" + newRepeatFlag;

    $.ajax({
        type: "post",
        data: formdata,
        url: "UpdateHoliday" + paramlist,
        success: function (data)
        {
            $('#holidayrow' + id).html(data);
        }
    });

}
function cancelUpdateHoliday(id)
{
    var formdata = "xyz";
    var paramlist = "?holidayId=" + id;
    $.ajax({
        type: "post",
        data: formdata,
        url: "ShowOneHoliday" + paramlist,
        success: function (data)
        {
            $('#holidayrow' + id).html(data);
        }
    });
}

function deleteHoliday()
{
    var formdata = "xyz";
    var holidayList = [];
    $.each($("input.holidayCheck:checked"), function () {
        holidayList.push($(this).val());
    });
    var a = new Date(document.getElementById('holidayFromDate').value);
    var holidayFromDate = String(a.getFullYear()) + "/" + String(a.getMonth() + 1) + "/" + String(a.getDate());
    var b = new Date(document.getElementById('holidayToDate').value);
    var holidayToDate = String(b.getFullYear()) + "/" + String(b.getMonth() + 1) + "/" + String(b.getDate());

    var paramlist = "?holidayIdList=" + holidayList + "&holidayFromDate=" + holidayFromDate + "&holidayToDate=" + holidayToDate;
    $.ajax({
        type: "post",
        data: formdata,
        url: "DeleteHoliday" + paramlist,

        success: function (data)
        {
            document.getElementById("displayHolidays").style.display = "block";
            $('#displayHolidays').html(data);
        }
    });
}

function resetHolidaySearch()
{
    document.getElementById("holidaySearchForm").reset();
    document.getElementById("displayHolidays").style.display = "none";
}

function showInnerEntitlement(divid)
{
    var mark = document.getElementById(divid).checked;
    if (mark)
    {
        document.getElementById("location").style.display = "block";
        document.getElementById("entitlementEmpname").style.display = "none";
        getAllLocations();
        getAllSubUnits();
// bring location and subunit list in select options

    } else
    {
        document.getElementById("location").style.display = "none";
        document.getElementById("entitlementEmpname").style.display = "block";
        showEmpNameInput();

    }
}


function showEntitlementElements()
{
    $('[data-toggle="tooltip"]').tooltip();
    showEmpNameInput();
    var formdata = "xyz";
    document.getElementById('entitlementConfig').style.display = "block";
    $.ajax({
        type: "post",
        data: formdata,
        url: "AllLeaveTypes",

        success: function (data)
        {
            $('#selectLeaveType').html(data);

        }
    });
    showAllLeaveperiod('#selectLeavePeriod');
}

function employeeValidate(divid)
{
    var e = document.getElementById(divid).value;
    var paramlist = "?employeeName=" + e;
    if (e === "")
    {
        paramlist = "?employeeName=" + null;
    }

    var formdata = "xyz";
    $.ajax({
        type: "post",
        data: formdata,
        url: "EmployeeValidate" + paramlist,

        success: function (data)
        {
            $('#employeeValid').html(data);

        }
    });
}
//work under progress
function selectEmpName(id)
{
    var paramlist = "?employeeId=" + id;
    var formdata = "xyz";
    $.ajax({
        type: "post",
        data: formdata,
        url: "GetEmployeeName" + paramlist,

        success: function (data)
        {
//            document.getElementById('empName').style.display = "none";
            $('#setEmpName').html(data);
            clearEmployeeList();

        }
    });
}
function showEmpNameInput()
{
    var paramlist = "?employeeId=" + null;
    var formdata = "xyz";
    $.ajax({
        type: "post",
        data: formdata,
        url: "GetEmployeeName" + paramlist,

        success: function (data)
        {
            $('#setEmpName').html(data);
            clearEmployeeList();

        }
    });

}
function clearEmployeeList()
{
    var paramlist = "?employeeName=" + null;
    var formdata = "xyz";
    $.ajax({
        type: "post",
        data: formdata,
        url: "EmployeeValidate" + paramlist,

        success: function (data)
        {
            $('#employeeValid').html(data);

        }
    });
}

function getAllLocations()
{
    var formdata = "xyz";
    $.ajax({
        type: "post",
        data: formdata,
        url: "getAllLocations",

        success: function (data)
        {
            $('#selectLocation').html(data);

        }
    });


}

function getAllSubUnits()
{
    var formdata = "xyz";
    $.ajax({
        type: "post",
        data: formdata,
        url: "getAllSubUnits",

        success: function (data)
        {
            $('#selectSubunit').html(data);

        }
    });


}

function saveEntitlements()
{
    var formdata = "xyz";

    if (document.getElementById('selectAllEmp').checked) {
        var locationId = document.getElementById('allLocationList').value;
        var subunitId = document.getElementById('allSubunitList').value;
        var name = "";

    } else {
        var locationId = "";
        var subunitId = "";
        var name = document.getElementById('selectOneEmp').value;
    }
    var id = document.getElementById('selectedEmpId').value;

    var leaveTypeId = document.getElementById('leaveTypeId').value;
    var leavePeriodId = document.getElementById('leavePeriodDropdown').value;
    var entitlements = document.getElementById('entitlements').value;
    var paramlist = "?employeeId=" + id + "&employeeName=" + name + "&leaveTypeId=" + leaveTypeId + "&leavePeriodId=" + leavePeriodId + "&entitlements=" + entitlements + "&locationId=" + locationId + "&subunitId=" + subunitId;
    $.ajax({
        type: "post",
        data: formdata,
        url: "saveEntitlement" + paramlist,

        success: function (data)
        {
            $('#saveEntitlementResult').html(data);
        }
    });
}



function searchEntitlement() {

    document.getElementById('displayEntitlements').style.display = "block";
    var formdata = "xyz";
    var name = document.getElementById('entitlementSearchempName').value;

    var leaveTypeId = document.getElementById('leaveTypeId').value;
    var leaveType = document.getElementById("leaveTypeId");
    var leaveTypeName = leaveType.options[leaveType.selectedIndex].text;

    var leavePeriodId = document.getElementById('leavePeriodDropdown2').value;
    var leavePeriod = document.getElementById("leavePeriodDropdown2");
    var leavePeriodName = leavePeriod.options[leavePeriod.selectedIndex].text;

    var paramlist = "?employeeName=" + name + "&leaveTypeId=" + leaveTypeId + "&leaveTypeName=" + leaveTypeName + "&leavePeriodId=" + leavePeriodId + "&leavePeriodName=" + leavePeriodName;

    $.ajax({
        type: "post",
        data: formdata,
        url: "searchEntitlement" + paramlist,

        success: function (data)
        {
            $('#displayEntitlements').html(data);
        }
    });

}
function showLeaveApply() {
    document.getElementById('leaveApplyform').reset();
    document.getElementById('leaveBalancediv').style.display = "none";
    document.getElementById('leaveApplyOthermsg').style.display = "none";
    document.getElementById('leaveApplyErrormsg').style.display = "none";
    var formdata = "xyz";
    $.ajax({
        type: "post",
        data: formdata,
        url: "LeaveApplyLeaveTypes",

        success: function (data)
        {
            $('#applyLeavetype').html(data);

        }
    });

}
function calculateLeaveBal() {
    var formdata = "xyz";
    try {
        var employeeId = document.getElementById('applyEmployeeId').value;
        var leaveTypeId = document.getElementById('leaveTypeId2').value;
//        var a = new Date(document.getElementById('applyLeaveFrom').value);
//        var startDate = String(a.getFullYear()) + "/" + String(a.getMonth() + 1) + "/" + String(a.getDate());
//        var b = new Date(document.getElementById('applyLeaveTo').value);
//        var endDate = String(b.getFullYear()) + "/" + String(b.getMonth() + 1) + "/" + String(b.getDate());
        var paramlist = "?employeeId=" + employeeId + "&leaveTypeId=" + leaveTypeId;
        $.ajax({
            type: "post",
            data: formdata,
            url: "CalculateLeaveBal" + paramlist,

            success: function (data)
            {
                document.getElementById('leaveBalancediv').style.display = "block";
                $('#applyLeaveTypeBalance').html(data);

            }
        });

    } catch (e) {
        document.getElementById("leaveApplyErrormsg").innerHTML = "*Fill all the data above.";
        document.getElementById('leaveApplyOthermsg').style.display = "none";
    }
}

function applyLeave() {
    try {

        var formdata = "xyz";
        var date = new Date();
        var leaveApplyDate = date.getFullYear() + "/" + date.getMonth() + "/" + date.getDate();
        var employeeId = document.getElementById('applyEmployeeId').value;
        var leaveTypeName = document.getElementById('leaveTypeId2');
        var leaveType = leaveTypeName.options[leaveTypeName.selectedIndex].text;
        var balance = document.getElementById('leaveBalance').value;
        var a = new Date(document.getElementById('applyLeaveFrom').value);
        var startDate = String(a.getFullYear()) + "/" + String(a.getMonth() + 1) + "/" + String(a.getDate());
        var startDay = a.getDay();
        var b = new Date(document.getElementById('applyLeaveTo').value);
        var endDate = String(b.getFullYear()) + "/" + String(b.getMonth() + 1) + "/" + String(b.getDate());
        var endDay = b.getDay();
        if (a <= b) {
            var x = (b.getTime() - a.getTime()) / 86400000;

            var paramlist = "?employeeId=" + employeeId + "&leaveTypeName=" + leaveType + "&leaveBalance=" + balance + "&startDate=" + startDate + "&endDate=" + endDate + "&leaveCount=" + (x + 1) + "&startDay=" + startDay + "&endDay=" + endDay;
            paramlist = paramlist + "&currentDate=" + leaveApplyDate;
            $.ajax({
                type: "post",
                data: formdata,
                url: "ApplyLeave" + paramlist,

                success: function (data)
                {
                    document.getElementById('leaveApplyOthermsg').style.display = "block";
                    document.getElementById('leaveApplyErrormsg').style.display = "none";
                    $('#leaveApplyOthermsg').html(data);

                }
            });



        } else {
            throw 2;
        }
    } catch (err) {
        if (err === 2) {
            document.getElementById('leaveApplyErrormsg').style.display = "block";
            document.getElementById('leaveApplyOthermsg').style.display = "none";
            document.getElementById("leaveApplyErrormsg").innerHTML = "*Check dates.";
        } else {
            document.getElementById('leaveApplyOthermsg').style.display = "none";
            document.getElementById('leaveApplyErrormsg').style.display = "block";
            document.getElementById("leaveApplyErrormsg").innerHTML = "*Fill all the required fields carefully.";
        }
    }

}

function addComment(id) {

    var formdata = "xyz";
    var paramlist = "?leaveId=" + id;
    $.ajax({
        type: "post",
        data: formdata,
        url: "AddComment" + paramlist,

        success: function (data)
        {
            $('#leave' + id).html(data);

        }
    });
}
function updateClock( )
{
    var currentTime = new Date( );
    var currentHours = currentTime.getHours( );
    var currentMinutes = currentTime.getMinutes( );
    var currentSeconds = currentTime.getSeconds( );
    var currentTimeString = currentHours + ":" + currentMinutes + ":" + currentSeconds;
    $("#clock").html(currentTimeString);

}

$(document).ready(function ()
{
    setInterval('updateClock()', 1000);
    var currentDate = new Date( );
    var date = currentDate.getDate() + "/" + currentDate.getMonth() + "/" + currentDate.getFullYear();
    $("#date").html(date);
//    updateLeavePeriod();
});

function reportoptn(e)
{
    if (e === "1")
    {
        document.getElementById("leavetypesection").style.display = "block";
//        document.getElementById("employeesection").style.display = "none";

    } else if (e === "2")
    {
//        document.getElementById("employeesection").style.display = "block";
        document.getElementById("leavetypesection").style.display = "none";
    } else
    {
        document.getElementById("leavetypesection").style.display = "none";
//        document.getElementById("employeesection").style.display = "none";

    }
}

function toggleLeaveConstraints() {
    if (document.getElementById("leaveConstraintsAdd").checked) {
        document.getElementById("leaveInnerConstraints").style.display = "block";
    } else {
        document.getElementById("leaveInnerConstraints").style.display = "none";
    }


}

function updateLeave(id) {
    var leaveStatus = document.getElementById("leaveAction").value;
    var leaveComment = document.getElementById("leaveCommentInput").value;
    var date = new Date();
    var leaveActionDate = date.getFullYear() + "/" + date.getMonth() + "/" + date.getDate();
    var leaveActionTime = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
    alert(leaveActionTime);
    var formdata = "xyz";
    var paramlist = "?leaveId=" + id + "&leaveStatus=" + leaveStatus + "&leaveComment=" + leaveComment + "&leaveActionDate=" + leaveActionDate + "&leaveActionTime=" + leaveActionTime;
    $.ajax({
        type: "post",
        data: formdata,
        url: "UpdateLeave" + paramlist,

        success: function (data)
        {
            $('#showLeaveList').html(data);

        }
    });
}

function showInnerReportsDiv(val) {

    if (val === "1") {
        document.getElementById("forEmployee").style.display = "block";
        document.getElementById("forLeaveType").style.display = "none";
        var formdata = "xyz";
        $.ajax({
            type: "post",
            data: formdata,
            url: "LeaveReportLeavePeriods2",
            success: function (data)
            {
                $('#leaveReportLPselect1').html(data);

            }

        });
    } else if (val === "2") {
        document.getElementById("forLeaveType").style.display = "block";
        document.getElementById("forEmployee").style.display = "none";
        var formdata = "xyz";
        $.ajax({
            type: "post",
            data: formdata,
            url: "LeaveReportLeavePeriods",
            success: function (data)
            {
                $('#leaveReportLPselect2').html(data);

            }

        });

        $.ajax({
            type: "post",
            data: formdata,
            url: "LeaveReportLeaveTypes",

            success: function (data)
            {
                $('#leaveReportLTnsme').html(data);

            }
        });

        $.ajax({
            type: "post",
            data: formdata,
            url: "LeaveReportAllLocations",

            success: function (data)
            {
                $('#leaveReportLocation').html(data);

            }
        });
        $.ajax({
            type: "post",
            data: formdata,
            url: "LeaveReportAllSubunits",

            success: function (data)
            {
                $('#leaveReportSubunit').html(data);

            }
        });
    }

}

function viewReport() {
    var paramlist = "";
    var formdata = "xyz";
    if (document.getElementById('generateReportFor').value === "1") {
        document.getElementById("leaveReportErrormsg").innerHTML = "";
        var employeeName = document.getElementById("leaveReportEmpName").value;
        var leavePeriodId = document.getElementById("leaveReportLP2").value;
        paramlist = "?employeeName=" + employeeName + "&leavePeriodId=" + leavePeriodId;

    } else if (document.getElementById('generateReportFor').value === "2") {
        document.getElementById("leaveReportErrormsg").innerHTML = "";
        var leaveTypeId = document.getElementById("leaveReportLT").value;
        var leavePeriodId = document.getElementById("leaveReportLP").value;
        var locationName = document.getElementById("leaveReportLocations").value;
        var subUnitName = document.getElementById("leaveReportSubunits").value;

        paramlist = "?employeeName=" + "x" + "&leaveTypeId=" + leaveTypeId + "&leavePeriodId=" + leavePeriodId + "&locationName=" + locationName + "&subUnitName=" + subUnitName;
    } else {

        document.getElementById("leaveReportErrormsg").innerHTML = "*Choose one";
    }
    if (paramlist !== "") {

        $.ajax({
            type: "post",
            data: formdata,
            url: "ViewLeaveReport" + paramlist,

            success: function (data)
            {
                $('#leaveReportView').html(data);

            }
        });

    }
}

function searchLeave() {
    document.getElementById('leaveListErrorMsg').style.display = "none";
    try {
        var formdata = "xyz";
        var a = new Date(document.getElementById('leaveListFrom').value);
        var startDate = String(a.getFullYear()) + "/" + String(a.getMonth() + 1) + "/" + String(a.getDate());
        a = new Date(document.getElementById('leaveListTo').value);
        var endDate = String(a.getFullYear()) + "/" + String(a.getMonth() + 1) + "/" + String(a.getDate());
        var leaveStatus = document.getElementById('leaveStatus').value;
        var employeeName = document.getElementById('leaveListEmployee').value;
        var subUnitName = document.getElementById('leaveListSubunit').value;
     
        if (employeeName === null) {
            employeeName = "";
        }
        if (leaveStatus === 'all') {
            leaveStatus = "";
        }
        if (subUnitName === 'all') {
            subUnitName = "";
        }
        if (startDate === '' || endDate === '') {
            throw 1;
        }
        var paramlist = "?startDate=" + startDate + "&endDate=" + endDate + "&leaveStatus=" + leaveStatus + "&employeeName=" + employeeName + "&subUnitName=" + subUnitName;

        $.ajax({
            type: "post",
            data: formdata,
            url: "ShowLeaveList" + paramlist,
            success: function (data)
            {
                document.getElementById('showLeaveList').style.display = "block";
                $('#showLeaveList').html(data);
            }
        });
    } catch (e)
    {
        document.getElementById('leaveListErrorMsg').style.display = "block";
        document.getElementById('leaveListErrorMsg').innerHTML = "Fill up the required fields carefully.";
    }

}
