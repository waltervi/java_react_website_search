import React from 'react';

import 'bootstrap/dist/css/bootstrap.min.css';

import Modal from 'react-bootstrap/Modal'
import Tabs from 'react-bootstrap/Tabs'
import Tab from 'react-bootstrap/Tab'

import ExpertSearch from './ExpertSearch.js'
import InterestsTable from './InterestsTable.js'
import UserInfoTab from './UserInfoTab.js'
import FriendsTable from './FriendsTable.js'

class UserDetailsModal  extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      show : this.props.show,
      user : this.props.user,
      headers : this.props.headers
    };
    this.handleClose = this.handleClose.bind(this);
    this.handleShow = this.handleShow.bind(this);

  }


  handleClose(){
    this.setState( { show : false} );
    this.props.hideUserDetails();
  };

  handleShow(){
    this.setState( { show : true} );
  };




  render() {
    return (
      <>
        <Modal size="lg"
                show={this.state.show}
                onHide={this.handleClose}
                onClose={this.handleClose}
                backdrop="static"
                keyboard={false}
                aria-labelledby="example-modal-sizes-title-sm"
              >
          <Modal.Header closeButton>
            <Modal.Title>User Details</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Tabs defaultActiveKey="home" id="uncontrolled-tab-example">
              <Tab eventKey="home" title="User info">
                <UserInfoTab user={this.state.user} />
              </Tab>
              <Tab eventKey="friends" title="Friends">
                <FriendsTable user={this.state.user} />
              </Tab>
              <Tab eventKey="profile" title="Interests">
                <InterestsTable headers={this.state.headers}/>
              </Tab>
                <Tab eventKey="contact" title="Find expert on subject">
                   <ExpertSearch user={this.state.user}/>
                </Tab>
            </Tabs>
          </Modal.Body>
          <Modal.Footer>
          </Modal.Footer>
        </Modal>
      </>
    );
  };
}









export default UserDetailsModal;
