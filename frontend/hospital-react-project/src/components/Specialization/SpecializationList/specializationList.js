import React from 'react';

import '../specialization.css';
import SingleSpecialization from "../SingleSpecialization/singleSpecialization";

const SpecializationList = (props) => {

    const OneSpecialization = props.specializations.map((specialization) => {
        return(
             <SingleSpecialization onDelete={props.onDelete} specialization={specialization} key={specialization.id}/>
        );
    });


    const onFormSubmit = (e) => {
        e.preventDefault();
        props.onAdd(e.target.firstName.value);
    };

    return (
        <div>
            <div>
                <div className="card-body">
                    <div className="card-text">
                        <div className="specialization">
                            <form onSubmit={onFormSubmit}>
                                <div className="row form-group">
                                    <div className="col-md-6 font-weight-bold"> Add new specialization</div>
                                    <div className="col-md-6">
                                        <div className="row">
                                            <div className="col-md-5">
                                                <input className="form-control" type="text" name={"firstName"} placeholder={"Enter doctor specialization ..."}/>
                                            </div>

                                        </div>
                                    </div>

                                    <div className="speciButton col-md-10">

                                        <button type="submit" className="btn btn-primary" title="Submit">
                                            <i className="fa fa-fw fa-save"></i> Add
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <hr/>
                        </div>

                    </div>
                </div>

            </div>


            <br />
            <div className="table-wrapper">
                <table className="fl-table">
                    <thead>
                    <tr>
                        <th>#ID</th>
                        <th>Name</th>
                        <th>Creation date</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    {OneSpecialization}
                    </tbody>
                </table>
            </div>
        </div>

    );
}

export default SpecializationList;
