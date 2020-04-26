import React from 'react';
import SaveIcon from "@material-ui/icons/Save";
import Button from "@material-ui/core/Button";

const MedicalHistoryAdd = (props) => {

    const onFormSubmit = (e) => {
        e.preventDefault();

        props.onAdd({
            "bloodPressure": e.target.bloodPressure.value,
            "weight": e.target.weight.value,
            "bloodSugar": e.target.bloodSugar.value,
            "bodyTemperature": e.target.bodyTemperature.value,
            "medicalPrescription": e.target.medicalPrescription.value,
            "patientId": props.match.params.id
        });
    };


    return (

        <div>
            <div className="card-body" >
                <div className="card-text">
                    <form onSubmit={onFormSubmit} id="editForm">
                        <div className="form-group col-md-8">
                            <label>Blood Pressure</label>
                            <input type="text" name={"bloodPressure"} className="form-control" placeholder="Enter blood pressure"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Weight</label>
                            <input type="number" name={"weight"} className="form-control" placeholder="Enter weight "/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Blood Sugar</label>
                            <input type="number" name={"bloodSugar"} className="form-control" placeholder="Enter blood sugar"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Body Temperature</label>
                            <input type="text"  name={"bodyTemperature"}  className="form-control" placeholder="Enter body temperature"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Medical Prescription</label>
                            <textarea type="text"  name={"medicalPrescription"} className="form-control" placeholder="Medical prescription"/>
                        </div>

                        <Button
                            variant="contained"
                            color="primary"
                            size="small"
                            type="submit"
                            id="editbtn"
                            startIcon={<SaveIcon />}
                        >
                            Save
                        </Button>
                    </form>
                </div>
            </div>
        </div>
    );

};

export default MedicalHistoryAdd;
