import React, {useEffect} from 'react';
import EditIcon from '@material-ui/icons/Edit';
import Button from '@material-ui/core/Button';


const PatientEdit = (props) => {


    useEffect(() => {
        props.onLoad(props.match.params.id, "edit");
    }, []);


    const onFormSubmit = (e) => {
        e.preventDefault();

        props.onEdit({
            "id" : props.patient.id,
            "name": e.target.name.value,
            "ssn": e.target.ssn.value,
            "gender": e.target.gender.value,
            "email": e.target.email.value,
            "address": e.target.address.value,
            "age": e.target.age.value,
            "contactNo": e.target.contactNo.value
        });

    };

    const handleTermOnChange  = (e) => {
        const paramName = e.target.name;
        const paramValue = e.target.value;
        props.setPatient({...props.patient, [paramName]: paramValue});
    };




    return (

        <div>
            <div className="card-body" >
                <div className="card-text">
                    <form onSubmit={onFormSubmit} id="editForm">
                        <div className="form-group col-md-8">
                            <label>Patient name</label>
                            <input type="text" onChange={handleTermOnChange} name={"name"} value={props.patient.name} className="form-control" placeholder="Enter name"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Patient SSN</label>
                            <input type="text" onChange={handleTermOnChange} name={"ssn"} value={props.patient.ssn}  className="form-control" placeholder="Enter SSN "/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Contact number</label>
                            <input type="text" onChange={handleTermOnChange} name={"contactNo"} value={props.patient.contactNo}  className="form-control" placeholder="Contact number"/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Patient email</label>
                            <input type="text" onChange={handleTermOnChange} name={"email"} value={props.patient.email}  className="form-control" placeholder="Email..."/>
                        </div>
                        <div className="form-group col-md-8">
                            <label>Select Gender</label>
                            <select name={"gender"} onChange={handleTermOnChange} value={props.patient.gender} className="form-control">
                                <option>Male</option>
                                <option>Female</option>
                            </select>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Patient Address</label>
                            <input type="text"  name={"address"} onChange={handleTermOnChange} value={props.patient.address}  className="form-control" placeholder="Enter address ..."/>
                        </div>
                        <div className="form-group  col-md-8">
                            <label>Patient Age</label>
                            <input type="number" name={"age"} onChange={handleTermOnChange} value={props.patient.age}  className="form-control" placeholder="Enter patient age ..."/>
                        </div>

                        <Button
                            variant="contained"
                            color="primary"
                            size="large"
                            type="submit"
                            startIcon={<EditIcon>edit</EditIcon>}
                        >
                            Edit
                        </Button>
                        {/*<button type="submit" id="editbtn" className="btn btn-primary  col-md-2 editButton">Edit</button>*/}
                    </form>
                </div>
            </div>

        </div>
    );
}

export default PatientEdit;
