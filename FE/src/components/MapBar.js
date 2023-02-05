import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';

const useStyles = makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: theme.spacing(1),
        },
    },
}));

export default function TextButtons() {
    const classes = useStyles();
    var Comp = () => {
        return <Button onClick={Btn}>서울</Button>
    }
    const Btn = () => {
        console.log('click!')
    }
    
    return (
        <div className={classes.root}>
            <Comp></Comp>
            <Button >부산</Button>
            <Button >강릉</Button>
            <Button >경주</Button>
            <Button >전주</Button>
        </div>
    );
}