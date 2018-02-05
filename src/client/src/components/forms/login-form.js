import React from 'react';
import ReactDOM from 'react-dom';

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
            <form action="" className="p-5">
                <h3 className="display-4 text-center"> {this.props.title} </h3>
                <div className="form-group">
                    <input className="form-control form-control-lg" placeholder="E-mail" type="text" />
                </div>
                <div className="form-group">
                    <input className="form-control form-control-lg" placeholder="Senha" type="password" />
                </div>
                <div className="form-group">
                    <button className="btn btn-info btn-lg btn-block" onClick={this.submit}>Entrar</button>
                    <button className="btn btn-lg btn-block" onClick={this.googleLogin}>Entrar com o Google</button>
                </div>
            </form>
        );
    }
}

export default LoginForm;