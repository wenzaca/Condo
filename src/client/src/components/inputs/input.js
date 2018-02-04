import React from 'react';
import ReactDOM from 'react-dom';

class Input extends React.Component {
    constructor(props) {
        super(props);
    }

    handleChange = this.props.handleChange;
    onKeyPress = this.props.onKeyPress;

    render() {
        return (
            <div>
                <label> {this.props.label} </label>
                {this.props.children}
            </div>
        );
    }
}

export default Input;