import React, {useState, useEffect} from 'react';
import '../specialization.css';
import SpecializationService from "../../../repository/axiosSpecializationRepository";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import Input from "@material-ui/core/Input";
import Button from "@material-ui/core/Button";
import EditIcon from "@material-ui/icons/Edit";


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
            <Card  variant="outlined">
                <CardContent>
                    <Typography  color="textSecondary" gutterBottom>
                        Edit Doctor Specialization
                    </Typography>
                    <form onSubmit={onFormSubmit}>
                        <Box  color="text.primary" component="div" m={1}>
                            <Input onChange={handleTermOnChange} name={"name"} type="text"
                                   className="form-control"
                                   value={specialization.name}
                                   title="Name"/>
                        </Box>
                        <Button
                            variant="contained"
                            color="primary"
                            size="medium"
                            type="submit"
                            startIcon={<EditIcon />}
                        >
                            Edit
                        </Button>
                    </form>
                </CardContent>
            </Card>
        </div>
    );
}

export default SpecializationEdit;
