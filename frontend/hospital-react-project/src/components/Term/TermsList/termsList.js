import React, {useEffect, useState} from 'react';
import SingleTerm from "../SingleTerm/singleTerm";
import {Divider} from "@material-ui/core";


const TermsList = (props) => {

   const[terms, setTerms] = useState([]);

    useEffect(() => {
        if(props.flag === "byDoctor") {
            props.onClick(props.match.params.id);
        }
        else{
            props.onClick(props.match.params.id, props.match.params.status);
        }

        setTerms(props.terms);
    }, []);



    const singleTerm = props.terms.map((term) => {
        return(
            <SingleTerm  term={term} onDelete={props.onDelete} doctorId={props.match.params.id} key={term.id}/>
        );
    });


    return (
        <div>
            <h4>MANAGE YOUR TERMS</h4>
            <Divider />
            <br />

            <div className="table-wrapper">
                <table className="fl-table">
                    <thead>
                    <tr>
                        <th>#ID</th>
                        <th>Date</th>
                        <th>Time of Admission</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    {singleTerm}
                    </tbody>
                </table>
            </div>
        </div>

    );
}

export default TermsList;
