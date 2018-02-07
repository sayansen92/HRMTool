<%-- 
    Document   : NoWorkWeek
    Created on : 14 Apr, 2017, 4:00:32 PM
    Author     : sayansen
--%>

<div class="panel panel-default" style="width:50%" id="configWorkWeek">
    <div class="panel-heading"><span style="color: green">Set Work Week</span></div>
    <div class="panel-body">
        <form id="configWorkWeekform"  title="Work Week">
            <fieldset id="workWeekFields">
                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-4"><label for="monday" >Monday</label></div>
                        <div class="col-xs-4"><select id="monday" class="day form-control">
                                <option value="Full day">Full day</option>
                                <option value="Half day">Half day</option>
                                <option value="Non-working day">Non-working day</option>
                            </select></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-4"><label for="tuesday">Tuesday</label></div>
                        <div class="col-xs-4"><select id="tuesday" class="day form-control" >
                                <option value="Full day">Full day</option>
                                <option value="Half day">Half day</option>
                                <option value="Non-working day">Non-working day</option>
                            </select></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-4"><label for="wednesday">Wednesday</label></div>
                        <div class="col-xs-4"><select id="wednesday" class="day form-control" >
                                <option value="Full day">Full day</option>
                                <option value="Half day">Half day</option>
                                <option value="Non-working day">Non-working day</option>
                            </select></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-4"><label for="thursday">Thursday</label></div>
                        <div class="col-xs-4"><select id="thursday" class="day form-control" >
                                <option value="Full day">Full day</option>
                                <option value="Half day">Half day</option>
                                <option value="Non-working day">Non-working day</option>
                            </select></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-4"><label for="friday">Friday</label></div>
                        <div class="col-xs-4"><select id="friday" class="day form-control" >
                                <option value="Full day">Full day</option>
                                <option value="Half day">Half day</option>
                                <option value="Non-working day">Non-working day</option>
                            </select></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-4"><label for="saturday">Saturday</label></div>
                        <div class="col-xs-4"><select id="saturday" class="day form-control" >
                                <option value="Full day">Full day</option>
                                <option value="Half day">Half day</option>
                                <option value="Non-working day">Non-working day</option>
                            </select></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-4">
                            <label for="sunday">Sunday</label>
                        </div>
                        <div class="col-xs-4">
                            <select id="sunday" class="day form-control" >
                                <option value="Full day">Full day</option>
                                <option value="Half day">Half day</option>
                                <option value="Non-working day">Non-working day</option>
                            </select>
                        </div>
                    </div>
                </div>
            </fieldset>
            <div class="row">

                <div class="col-xs-2">
                    <button type="button" id="saveWorkWeekbtn" class="btn btn-success" onclick="saveWorkWeek()">Save</button>
                </div>
                <div class="col-xs-2">
                    <button type="button" id="resetWorkWeekbtn" class="btn btn-primary" onclick="reset('configWorkWeekform')">Reset</button>
                </div>
                <div class="col-xs-2">
                    <button type="button" id="cancelWorkWeekbtn" class="btn btn-danger" onclick="showLeave('workWeek')">Cancel</button>
                </div>
            </div>
        </form>

    </div>
    <div class='panel-footer' id="workweeEditfooter">
        <span >* Click on <span style="color: green"> Save </span> button to save.  </span><br>
        <span >* Click on <span style="color: blue"> Reset </span> button to reset.  </span><br>
        <span >* Click on <span style="color: red"> Cancel </span>button to cancel.  </span>
    </div>

</div>