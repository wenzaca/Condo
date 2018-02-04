import React from 'react';
import ReactDOM from 'react-dom';
import InputWrapper from '../inputs/input';
import SubmitButton from '../buttons/submit-button';

class LoginForm extends React.Component {
    constructor(props) {
        super(props);
    }

    submit(event) {
        event.preventDefault();
        alert("Now you're logged in >:) ");
    }

    render() {
        return (
            <form>
                <h3> {this.props.title} </h3>
                <InputWrapper label="Username">
                    <input type="text" id="username" />
                </InputWrapper>
                <InputWrapper label="Password">
                    <input type="password" id="password" />
                </InputWrapper>
                <SubmitButton text="Enviar" action={this.submit} />
            </form>
        );
    }
}

export default LoginForm;