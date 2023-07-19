import React from 'react';

import 'bootstrap/dist/css/bootstrap.min.css';

import Table from 'react-bootstrap/Table'

class InterestsTable extends React.Component{
  constructor(props) {
		super(props);
	}

  render() {
    var headerRows = [];
    console.log("this.props.headers");
    console.log(this.props.headers);

    if (this.props.headers !=  undefined) {
      headerRows = this.props.headers.map(header =>
        <HeaderRow key={header.id} header={header} />
      );

    }

    return(
      <Table striped bordered hover size="sm">
      <thead>
        <tr>
          <th>Interests list</th>
        </tr>
      </thead>
       <tbody>
       {headerRows}
        </tbody>

      </Table>

    )

  }
}


class HeaderRow extends React.Component{
	constructor(props) {
		super(props);
	}

	render() {
		return (

			<tr key={this.props.header.id} >
        <td>{this.props.header.text}</td>
			</tr>
		)
	}
}

export default InterestsTable;
