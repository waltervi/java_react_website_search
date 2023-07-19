import React from 'react';

import 'bootstrap/dist/css/bootstrap.min.css';

import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form'
import Breadcrumb from 'react-bootstrap/Breadcrumb'

import {API} from "../network/apis";

class ExpertSearch extends React.Component{
  constructor(props) {
		super(props);
    this.state = {
      searchText : "",
      user : this.props.user,
      paths : null

    };
    this.findExpertsOn = this.findExpertsOn.bind(this);
    this.textChanged = this.textChanged.bind(this);

	}

  textChanged(e) {
    this.setState({ searchText: e.target.value });
  };

  findExpertsOn() {
    console.log(this.state.searchText);
    console.log(this.state.user);

    API.findExpert(this.state.user.id,this.state.searchText,
      (result) => this.setState({ paths : result.paths}),
      (error) => console.log(error)
      
    );

  };

  render() {

    var paths = [];
    console.log("this.props.headers");
    console.log(this.props.headers);

    if (this.state.paths !=  null) {
      paths = this.state.paths.map(path =>
        <CustomBredCrumb  path={path} />
      );

    }


    return (
        <>
          <table>
            <tbody>
              <tr>
                <td>
                  <Button variant="light" onClick={this.findExpertsOn}>
                    Search :
                  </Button>
                </td>
                <td>
                  <Form.Control size="sm" type="text" placeholder="Subject..."
                    value={this.state.searchText} onChange={this.textChanged}/>

                </td>
              </tr>
            </tbody>
          </table>
          <table>
            <tbody>
            {paths}
            </tbody>
          </table>
        </>
    )
  }
}

class CustomBredCrumb  extends React.Component{

  constructor(props) {
		super(props);
	}

	render() {
		return (
      <tr>
        <td>
      <Breadcrumb >
        <Breadcrumb.Item href="#">{this.props.path[0]}</Breadcrumb.Item>
        <Breadcrumb.Item href="#">{this.props.path[1]}</Breadcrumb.Item>
      </Breadcrumb>
      </td>
    </tr>

		)
	}

}

export default ExpertSearch;
