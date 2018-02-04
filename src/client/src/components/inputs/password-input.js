import React from 'react';
import ReactDOM from 'react-dom';

class PasswordInput extends React.Component {
    constructor(props) {
        super(props);
    }

    handleChange = this.props.handleChange;
    onKeyPress = this.props.onKeyPress;

    render() {
        return (
            <div>
                <label> {this.props.label} </label>
                <input type='password'
                    required='{this.props.required}'
                    onChange={this.handleChange}
                    onKeyPress={this.onKeyPress} />
            </div>
        );
    }
}

export default PasswordInput;