import React from 'react';

import 'bootstrap/dist/css/bootstrap.min.css';

import Button from 'react-bootstrap/Button';
import Nav from 'react-bootstrap/Nav'
import Modal from 'react-bootstrap/Modal'
import Form from 'react-bootstrap/Form'
import CustomAlert from './CustomAlert.js'

import {API} from "../network/apis";

class AddFriendModal  extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
        show : false,
        username: "",
        friendName : "",
        showUsernameNotFound: false,
        showFriendNotFound: false
      };
      this.handleClose = this.handleClose.bind(this);
      this.handleShow = this.handleShow.bind(this);
      this.handleOnSubmit = this.handleOnSubmit.bind(this);
      this.findUserAndFriendAndAddFriendShip = this.findUserAndFriendAndAddFriendShip.bind(this);
      this.addFriends = this.addFriends.bind(this);
      this.findFriend = this.findFriend.bind(this);
  }


  handleClose(){
    this.setState( { show : false} );
  };
  handleShow(){
    this.setState( { show : true} );
  };

  addFriends(userId,friendId){
    let request = {
      userId: userId,
      friendId : friendId
    };
    console.log(request);

    API.addFriend(request,
      (result) => {
        this.props.handleRefresh(result);
        this.handleClose();
      },
      (error) => console.log(error)
    );
  }

  findFriend(userId){
    API.findFriend(this.state.friendName,
      (result) => {
        let friendFound = false;
        if (result._embedded.users.length == 0) {
          this.setState({showFriendNotFound : true});
        }
        else {
          friendFound = true;
        }

        if (friendFound == true ) {
              this.setState( {
                  show : false,
                  username: "",
                  friendName : "",}
                );
             this.addFriends(userId,result._embedded.users[0].id);
        }

      } ,
      (error) => console.log(error)
      
      )

  }

  findUserAndFriendAndAddFriendShip(){
    API.addFriend(this.state.username,
      (result) => {
        if (result._embedded.users.length === 0) {
          this.setState({showUsernameNotFound : true});
        }
        else {
          this.findFriend(result._embedded.users[0].id);
        }
      },
      (error) => console.log(error)
    );

  }


  handleOnSubmit() {
      //validate username
      this.findUserAndFriendAndAddFriendShip();

    };


    render() {
      return (
        <>

          <Nav.Link onClick={this.handleShow}>Add Friend</Nav.Link>

          <Modal
                  show={this.state.show}
                  onHide={this.handleClose}
                  backdrop="static"
                  keyboard={false}
                  aria-labelledby="example-modal-sizes-title-sm"
                >
            <Modal.Header closeButton>
              <Modal.Title>Add Friend</Modal.Title>
            </Modal.Header>
            <Modal.Body>
            <Form>
              <Form.Group controlId="formUsername">
                <Form.Label>Username</Form.Label>
                <Form.Control type="text" placeholder="username"
                  value={this.state.username}
                  onChange={e => this.setState({ username: e.target.value })}/>

              </Form.Group>

              <CustomAlert show={this.state.showUsernameNotFound} />

              <Form.Group controlId="formWebSite">
                <Form.Label>Friend username</Form.Label>
                <Form.Control type="text" placeholder="Friend username"
                  value={this.state.friendName}
                  onChange={e => this.setState({ friendName: e.target.value })}
                />
              </Form.Group>

              <CustomAlert show={this.state.showFriendNotFound}/>

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

export default AddFriendModal;
