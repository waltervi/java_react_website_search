import React from 'react';

import 'bootstrap/dist/css/bootstrap.min.css';
import Table from 'react-bootstrap/Table'

import {API} from "../network/apis";

class FriendsTable extends React.Component{
	constructor(props) {
		super(props);
    this.state = {
        friendsList : []

    };
    this.loadFriends = this.loadFriends.bind(this);
    this.loadFriends();
	}

  loadFriends(){

    API.findFriends(this.props.user.id,
      (result) => this.setState({ friendsList : result.content}),
      (error) => console.log(error)
    );

  }

	render() {
		const friends = this.state.friendsList.map(friend =>
			<FriendRow key={friend.friendId} friend={friend}/>
		);
		return (
				<>

		<Table striped bordered hover size="sm">
    <thead>
      <tr>
        <th>#</th>
        <th>Name</th>
        <th>Shortened url</th>
        <th>Friends</th>
      </tr>
    </thead>
     <tbody>
     {friends}
      </tbody>

			</Table>
			</>
		)
	}
}

class FriendRow extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			show : false,
			user : this.props.user
		};
	}

	render() {

		return (
			<>
				<tr key={this.props.friend.id} >
					<td>{this.props.friend.id}</td>
					<td>{this.props.friend.name}</td>
					<td>{this.props.friend.webSite}</td>
					<td>{this.props.friend.friendsQty}</td>
				</tr>
			</>
		)
	}
}

export default FriendsTable;
