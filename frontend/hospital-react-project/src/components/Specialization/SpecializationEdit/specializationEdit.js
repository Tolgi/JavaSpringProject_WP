import React, {useState, useEffect} from 'react';
import {useHistory, useParams} from 'react-router-dom';
import { Link } from 'react-router-dom';
import '../specialization.css';
import SpecializationService from "../../../repository/axiosSpecializationRepository";


const SpecializationEdit = (props) => {

    useEffect(() => {
        loadSpecialization();
    }, []);


    const [specialization, setSpecialization] = useState({});

    const loadSpecialization = () => {
        SpecializationService.getSpecialization(props.match.params.id).then(response =>{
          setSpecialization(response.data);
      })
    };


    const onFormSubmit = (e) => {
        e.preventDefault();
        console.log(specialization);
        props.onEdit(specialization);
    };

    const handleTermOnChange  = (e) => {
        const paramName = e.target.name;
        const paramValue = e.target.value;
        setSpecialization({...specialization, [paramName]: paramValue});
    };


    return (

        <div>
            <div className="card-body">
                <div className="card-text">
                    <div className="specialization">
                        <form onSubmit={onFormSubmit}>
                            <div className="row form-group">
                                <div className="col-md-6 font-weight-bold"> Specialization name:</div>
                                <div className="col-md-6">
                                    <div className="row">
                                        <div className="col-md-5">
                                            <input onChange={handleTermOnChange} name={"name"} type="text"
                                                   className="form-control"
                                                   value={specialization.name}
                                                   title="Name"/>
                                        </div>

                                </div>
                            </div>

                            <div className="col-md-10">

                                <button type="submit" className="btn btn-primary" title="Submit">
                                    <i className="fa fa-fw fa-save"></i> Edit
                                </button>
                            </div>
                            </div>
                        </form>
                        <hr/>
                    </div>

                </div>
            </div>

        </div>
    );
}

export default SpecializationEdit;
