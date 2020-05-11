import React, {useEffect} from 'react';
import {Divider} from "@material-ui/core";


const TermAdd = (props) => {

    const onFormSubmit = (e) => {
        e.preventDefault();

        props.onAdd({
            "date": e.target.date.value,
            "time": e.target.timeOfAdmission.value,
            "status": "free",
            "doctorId" : props.match.params.id
        });
    };

    useEffect(() => {
        var today = new Date().toISOString().split('T')[0];
        document.getElementsByName("date")[0].setAttribute('min', today);
    },[]);


    return (

        <div>
            <h4>ADD NEW TERM</h4>
            <Divider />
            <br />
            <div className="card-body" >
                <div className="card-text">
                    <form onSubmit={onFormSubmit} id="editForm">
                        <div className="form-group col-md-8">
                            <label>Date</label>
                            <input type="date" id="datePicker" name={"date"} className="form-control" placeholder="Enter date"/>

                        </div>
                        <div className="form-group  col-md-8">
                            <label>Time</label>
                            <input type="time" name={"timeOfAdmission"} className="form-control" placeholder="Enter time [hh:mm] "/>
                        </div>

                        <button type="submit" id="editbtn" className="btn btn-primary col-md-2 editButton">Add</button>
                    </form>
                </div>
            </div>
        </div>
    );

};

export default TermAdd;
