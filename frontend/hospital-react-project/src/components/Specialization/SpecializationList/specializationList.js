import React from 'react';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import '../specialization.css';
import SingleSpecialization from "../SingleSpecialization/singleSpecialization";
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import Input from '@material-ui/core/Input';
import SaveIcon from "@material-ui/icons/Save";
import Box from '@material-ui/core/Box';
import {Divider} from "@material-ui/core";

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
         <h4>ADMIN | DOCTOR SPECIALIZATION </h4>
         <Divider />
         <br />
        <Card  variant="outlined">
            <CardContent>
                <Typography  color="textSecondary" gutterBottom>
                    Add new specialization
                </Typography>
                <form onSubmit={onFormSubmit}>
                    <Box  color="text.primary" component="div" m={1}>
                    <Input placeholder="enter specialization name" name={"firstName"} type="text" d aria-describedby="my-helper-text" />
                    </Box>
                    <Button
                        variant="contained"
                        color="primary"
                        size="medium"
                        type="submit"
                        startIcon={<SaveIcon />}
                    >
                        Save
                    </Button>
                </form>
            </CardContent>
        </Card>

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
