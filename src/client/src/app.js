import React from 'react';
import LoginForm from './components/forms/login-form';

class App extends React.Component{
    constructor(){
        super();
    }
    render(){
        return (
            <div>
                <h1> Welcome </h1>
                <LoginForm title="Please log-in"/>
            </div>
        );
    }
}

export default App;