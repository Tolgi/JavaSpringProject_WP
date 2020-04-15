import React from 'react';

const TermAdd = (props) => {

    const onFormSubmit = (e) => {
        e.preventDefault();

        props.onAdd({
            "date": e.target.date.value,
            "time": e.target.timeOfAdmission.value,
            "status": e.target.status.value,
            "doctorId" : props.match.params.id
        });
    };


    return (

        <div>
            <div className="card-body" >
                <div className="card-text">
                    <form onSubmit={onFormSubmit} id="editForm">
                        <div className="form-group col-md-8">
                            <label>Date</label>
                            <input type="text" name={"date"} className="form-control" placeholder="Enter date"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Time</label>
                            <input type="text" name={"timeOfAdmission"} className="form-control" placeholder="Enter weight "/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Status</label>
                            <input type="text" name={"status"} className="form-control" placeholder="Enter blood sugar"/>
                        </div>

                        <button type="submit" id="editbtn" className="btn btn-primary  col-md-2 editButton">Add</button>
                    </form>
                </div>
            </div>
        </div>
    );

};

export default TermAdd;
