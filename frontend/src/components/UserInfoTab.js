import React from 'react';

import 'bootstrap/dist/css/bootstrap.min.css';
import Form from 'react-bootstrap/Form'


class UserInfoTab extends React.Component {
  constructor(props) {
		super(props);

	}



  render(){
    return(
      <>
      <table>
        <tr>
          <td>
            <Form.Label >Name : </Form.Label>
          </td>
          <td>
            <Form.Control size="sm" type="text" placeholder={this.props.user.name} readOnly />
          </td>
        </tr>

        <tr>
          <td>
            <Form.Label >WebSite</Form.Label>
          </td>
          <td>
            <Form.Control size="sm" type="text" placeholder={this.props.user.webSite} readOnly />
          </td>
        </tr>

        <tr>
          <td>
            <Form.Label>Shortened Url</Form.Label>
          </td>
          <td>
            <Form.Control type="text" placeholder={this.props.user.shortUrl} readOnly />
          </td>
        </tr>

        <tr>
          <td>
            <Form.Label>Total friends</Form.Label>
          </td>
          <td>
            <Form.Control type="text" placeholder={this.props.user.friendsQty} readOnly />
          </td>
        </tr>

        </table>

      </>


    )

  }

}




export default UserInfoTab;
