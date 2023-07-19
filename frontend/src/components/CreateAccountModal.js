import React from 'react';

import 'bootstrap/dist/css/bootstrap.min.css';

import Button from 'react-bootstrap/Button';
import Nav from 'react-bootstrap/Nav'
import Modal from 'react-bootstrap/Modal'
import Form from 'react-bootstrap/Form'

class CreateAccountModal  extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
        users : this.props.users,
        show : false,
        username: "",
        webSite : ""
      };
      this.handleClose = this.handleClose.bind(this);
      this.handleShow = this.handleShow.bind(this);
      this.handleOnSubmit = this.handleOnSubmit.bind(this);


  }

  handleClose(){
    this.setState( { show : false,  username: "",  webSite : ""} );
  };

  handleShow(){
    this.setState( { show : true} );
  };

  handleOnSubmit() {
      fetch('/api/users', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          name: this.state.username,
          webSite : this.state.webSite,
          friendsQty : 0
        })
      })
      .then(response => response.json())
      .then(
        (result) => {
          this.props.handleRefresh(result);
        },
        (error) => {
          console.log(error);
        }
      );
      this.setState( { show : false,  username: "",  webSite : ""} );
    };


    render() {
      return (
        <>

          <Nav.Link onClick={this.handleShow}>Create Account</Nav.Link>

          <Modal
                  show={this.state.show}
                  onHide={this.handleClose}
                  backdrop="static"
                  keyboard={false}
                  aria-labelledby="example-modal-sizes-title-sm"
                >
            <Modal.Header closeButton>
              <Modal.Title>Create new user account</Modal.Title>
            </Modal.Header>
            <Modal.Body>
            <Form>
              <Form.Group controlId="formUsername">
                <Form.Label>Username</Form.Label>
                <Form.Control type="text" placeholder="username"
                  value={this.state.username}
                  onChange={e => this.setState({ username: e.target.value })}/>
              </Form.Group>

              <Form.Group controlId="formWebSite">
                <Form.Label>Website</Form.Label>
                <Form.Control type="text" placeholder="Website"
                  value={this.state.webSite}
                  onChange={e => this.setState({ webSite: e.target.value })}
                />
              </Form.Group>

              <Button variant="primary" onClick={this.handleOnSubmit}>
                Submit
              </Button>
            </Form>
            </Modal.Body>
            <Modal.Footer>
              <Button variant="secondary" onClick={this.handleClose}>
                Close
              </Button>
            </Modal.Footer>
          </Modal>
        </>
      );
    };
}

export default CreateAccountModal;
