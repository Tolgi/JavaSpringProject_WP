import React, {useEffect} from 'react';
import SingleTerm from "../SingleTerm/singleTerm";


const TermsList = (props) => {

    useEffect(() => {
        if(props.flag === "byDoctor") {
            props.onClick(props.match.params.id)
        }
        else{
            props.onClick(props.match.params.id, props.match.params.status)
        }
    }, []);

    const singleTerm = props.terms.map((term) => {
        return(
            <SingleTerm  term={term} key={term.id}/>
        );
    });


    return (
        <div>
            <hr/>
            <br />
            <div className="table-wrapper">
                <table className="fl-table">
                    <thead>
                    <tr>
                        <th>#ID</th>
                        <th>Date</th>
                        <th>Time of Admission</th>
                        <th>Status</th>
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
