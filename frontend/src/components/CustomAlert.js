import React from 'react';

import 'bootstrap/dist/css/bootstrap.min.css';

import Alert from 'react-bootstrap/Alert'

class CustomAlert extends React.Component{
  constructor(props) {
    super(props);
    console.log("CustomAlert.props.show:" + props.show);
    this.state = {
      show : false,
    };

    this.handleClose = this.handleClose.bind(this);
    this.handleShow = this.handleShow.bind(this);
  }

  handleClose(){
    this.setState( { show : false} );
  };
  handleShow(){
    this.setState( { show : true} );
  };


    render() {
      console.log("CustomAlert:" + this.state.show);
      if (this.state.show) {
          return (
            <Alert variant="danger" onClose={this.handleShow} dismissible>
              <Alert.Heading>Username not found</Alert.Heading>
            </Alert>
          );
        }
        return <></>;
    };

}

export default CustomAlert;
