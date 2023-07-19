import React from 'react';

import 'bootstrap/dist/css/bootstrap.min.css';
import Table from 'react-bootstrap/Table'
import Button from 'react-bootstrap/Button'
import UserDetailsModal from './UserDetailsModal.js'

// tag::UserTable[]
class UserTable extends React.Component{
	constructor(props) {
		super(props);
	}


	render() {
		const users = this.props.users.map(user =>
			<UserRow key={user.id} user={user} showUserDetails={this.showUserDetails}/>
		);
		return (
				<>

		<Table striped bordered hover size="sm">
    <thead>
      <tr>
        <th>#</th>
        <th>User</th>
        <th>Website</th>
        <th>Friends</th>
				<th>User Details</th>
      </tr>
    </thead>
     <tbody>
     {users}
      </tbody>

			</Table>
			</>
		)
	}
}
// end::UserTable[]

// tag::UserRow[]
class UserRow extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			show : false,
			user : this.props.user
		};
		this.showUserDetails = this.showUserDetails.bind(this);
		this.hideUserDetails = this.hideUserDetails.bind(this);
	}

  hideUserDetails(){
		this.setState({ show : false});
	}

	showUserDetails(e){
			let userId = e.target.dataset.id;
			let url = '/api/siteHeaders/search/findByUserId?userId=' + userId;
			console.log(url);
			fetch(url, {
				method: 'GET',
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json',
				}
			})
			.then(response => response.json())
			.then(
				(result) => {
					console.log(result);
					this.setState({ show : true, headers : result._embedded.siteHeaders});
					console.log(this.state.show);
				},
				(error) => {
					console.log(error);
				}
			);
	}


	render() {

		if (this.state.show === true) {
			return (
				<>
					<tr key={this.props.user.id} >
		        <td>{this.props.user.id}</td>
		        <td>{this.props.user.name}</td>
						<td>{this.props.user.shortUrl}</td>
						<td>{this.props.user.friendsQty}</td>
						<td>
							<Button  variant="light" data-id={this.props.user.id}  onClick={this.showUserDetails}>View Details</Button>
						</td>
					</tr>

					<UserDetailsModal show={this.state.show} user={this.state.user} headers={this.state.headers}
						hideUserDetails={this.hideUserDetails}/>
				</>
			)
		}

		return (
			<>
				<tr key={this.props.user.id} >
					<td>{this.props.user.id}</td>
					<td>{this.props.user.name}</td>
					<td>{this.props.user.shortUrl}</td>
					<td>{this.props.user.friendsQty}</td>
					<td>
						<Button  variant="light" data-id={this.props.user.id}  onClick={this.showUserDetails}>View Details</Button>
					</td>
				</tr>
			</>
		)
	}
}
// end::UserRow[]
export default UserTable;
