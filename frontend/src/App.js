import React from 'react';

import 'bootstrap/dist/css/bootstrap.min.css';

import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Nav from 'react-bootstrap/Nav'

import CreateAccountModal from './components/CreateAccountModal.js'
import UserTable from './components/UserTable.js'
import AddFriendModal from './components/AddFriendModal.js'

import {API} from "./network/apis";

function App() {
  const [users, setusers] = React.useState([]);


  const handleRefresh = () => {
    API.getUsers(
      (result) => setusers(result._embedded.users),
      (error) => setusers([])
    );
  }

  React.useEffect(() => {
    handleRefresh()
  }, []);

  return (
    <Container fluid>
      <Row>
      <Nav
        activeKey="/"
        onSelect={(selectedKey) => alert(`selected ${selectedKey}`)}
      >
        <Nav.Item>
          <CreateAccountModal handleRefresh={handleRefresh} />
        </Nav.Item>
        <Nav.Item>
          <AddFriendModal handleRefresh={handleRefresh} />
        </Nav.Item>
      </Nav>
      </Row>
      <Row>
        <Col><UserTable users={users} /></Col>
      </Row>
    </Container>
  )

}

export default App;
