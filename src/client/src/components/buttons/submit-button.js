import React from 'react';
import ReactDOM from 'react-dom';

class SubmitButton extends React.Component {
    constructor(props) {
        super(props);
    }
    onClick = this.props.action;
    render() {
        return (<button onClick={this.onClick}> {this.props.text} </button>)
    }
}

export default SubmitButton;