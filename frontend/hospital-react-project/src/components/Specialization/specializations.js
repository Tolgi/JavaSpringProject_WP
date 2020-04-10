import React, {useState, useEffect} from 'react';
import { Route, useHistory} from 'react-router-dom';
import SpecializationService from "../../repository/axiosSpecializationRepository";
import SpecializationList from "./SpecializationList/specializationList";
import SpecializationEdit from "./SpecializationEdit/specializationEdit";
import SpecializationDelete from "./SpecializationDelete/deleteSpecialization";



const Specialization = ({match}) => {

    const [specializations, setSpecializations] = useState([]);
    const history = useHistory();


    useEffect(() => {
        loadSpecializations();
    }, []);



    const loadSpecializations = () => {
        SpecializationService.fetchSpecializations().then((response) =>{
            setSpecializations(response.data);
        })
    };



    const editSpecialization = (newSpecialization) => {
        SpecializationService.updateSpecialization(newSpecialization).then((response) => {

            const newSpecialization = response.data;
            const nextState = specializations.map((item) => {
                if(item.id === newSpecialization.id){
                    return newSpecialization;
                }
                return item;
            });

            setSpecializations(nextState);
            history.push("/specialization/list");

            // const nextState = specializations.map((s) => s.id === newSpecialization.id ? {...s, name: newSpecialization.name} : s);
        })
    };

    const deleteSpecialization = (specializationId) => {
        SpecializationService.deleteSpecialization(specializationId).then(() => {
            const nextState = specializations.map((item) => {
              return item.id !== specializationId;
            });

            setSpecializations(nextState);
        })
    };


    return (

        <div>
            <h2 className= "col-md-10 font-weight-bold " >Doctor Specializations</h2>
            <Route path={`${match.path}/list`} exact render={(props) => <SpecializationList  onDelete={deleteSpecialization} specializations={specializations}/>}/>
            <Route path={`${match.path}/edit/:id`} exact render={(props) => <SpecializationEdit onEdit={editSpecialization} {...props} />}/>
        </div>


    );
}

export default Specialization;
