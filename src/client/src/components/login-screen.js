import React from 'react';
import LoginForm from './forms/login-form';
import LoginScreenStyle from '../css/login-screen.css';

class LoginScreen extends React.Component {
    constructor() {
        super();
    }
    render() {
        return (
            <div id="login-screen" className="w-100 h-100 position-absolute d-flex align-items-center justify-content-center">
                <div className="border card  d-flex align-items-center flex-column">
                    <LoginForm title="Kondo." />
                </div>
            </div>
        );
    }
}

export default LoginScreen;