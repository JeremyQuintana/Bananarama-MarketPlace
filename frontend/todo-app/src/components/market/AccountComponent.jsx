import React, { Component } from "react";
import { withRouter } from "react-router-dom";


class AccountComponent extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        let retVal = (
            <div className="page-header">
                <h1>Account</h1>
            </div>
        );
        return retVal;
    }

}

export default withRouter(AccountComponent);
