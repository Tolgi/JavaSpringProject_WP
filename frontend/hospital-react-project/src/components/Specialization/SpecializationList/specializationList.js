import React, {useState, useEffect} from 'react';

import '../specialization.css';
import SingleSpecialization from "../SingleSpecialization/singleSpecialization";

const SpecializationList = (props) => {

    const OneSpecialization = props.specializations.map((specialization) => {
        return(
             <SingleSpecialization onDelete={props.onDelete} specialization={specialization} key={specialization.id}/>
        );
    });

    return (
        <div>
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
