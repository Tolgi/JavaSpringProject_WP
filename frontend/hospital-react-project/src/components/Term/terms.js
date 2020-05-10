import React, {useState, useEffect} from 'react';
import { Route, useHistory} from 'react-router-dom';
import TermService from "../../repository/axiosTermRepository";
import TermsList from "./TermsList/termsList";
import TermAdd from "./TermAdd/termAdd";

const Term = ({match}) => {

    const [allTerms, setAllTerms] = useState([]);
    const [terms, setTerms] = useState([]);
    const history = useHistory();

    useEffect(() => {
        loadAllTerms();
    }, []);


    const loadAllTerms = () => {
      TermService.fetchAllTerms().then((response) => {
          setAllTerms(response.data);
      })
    };

    const loadTermsByDoctor = (doctorId) => {
        TermService.fetchTermsByDoctorId(doctorId).then((response) => {
            setTerms(response.data);
        })
    };

    const loadTermsByDoctorAndStatus = (doctorId, status) => {
        TermService.fetchTermsByDoctorIdAndStatus(doctorId, status).then((response) => {
            setTerms(response.data);
        })
    };

    const deleteTerm = (termId) => {
        setAllTerms(allTerms.filter(term => term.id !== termId));
        TermService.deleteTerm(termId);

        history.push("/patient/list");
    };

    const addTerm = (newTerm) => {
      TermService.addTerm(newTerm).then((response) => {
          const newTerm = response.data;
          const nextState = [...allTerms, newTerm];
          setAllTerms(nextState);
      });

        history.push("/patient/list");
        console.log(newTerm);
    };


    return (
        <div>
            <Route path={`${match.path}/doctor/:id`} exact render={(props) => <TermsList onClick={loadTermsByDoctor} flag={"byDoctor"} terms={terms} {...props} />}/>
            <Route path={`${match.path}/doctor/:id/status/:status`} exact render={(props) => <TermsList onClick={loadTermsByDoctorAndStatus} flag={"byDoctorStatus"} terms={terms} {...props} />}/>
            <Route path={`${match.path}/delete/:id`}  render={(props) => deleteTerm(props.match.params.id)} />
            <Route path={`${match.path}/add/:id`}  render={(props) => <TermAdd onAdd={addTerm} {...props}/>}/>
        </div>
    );
}

export default Term;
