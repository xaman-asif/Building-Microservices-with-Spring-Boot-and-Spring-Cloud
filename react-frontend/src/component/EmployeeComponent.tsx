import type { AxiosResponse } from "axios";
import { Component } from "react";
import EmployeeService from "../service/EmployeeService";

interface EmployeeState {
  departmentDto: any;
  organizationDto: any;
}

class EmployeeComponent extends Component<{}, EmployeeState> {
  constructor(props: {}) {
    super(props);
    this.state = {
      departmentDto: {},
      organizationDto: {},
    };
  }

  componentDidMount() {
    EmployeeService.getEmployee().then((response: AxiosResponse<any>) => {
      this.setState(
        {
          departmentDto: response.data.departmentDto,
          organizationDto: response.data.organizationDto,
        },
        () => {
          console.log(this.state.departmentDto);
          console.log(this.state.organizationDto);
        },
      );
    });
  }
  render() {
    return (
      <div>
        {" "}
        <br />
        <br />
        <div className="card col-md-6 offset-md-3">
          <h3 className="text-center card-header">View Department Details</h3>
          <div className="card-body">
            <div className="row">
              <p>
                <strong>Department Name: </strong>{" "}
                {this.state.departmentDto.departmentName}
              </p>
            </div>
            <div className="row">
              <p>
                <strong>Department Description: </strong>{" "}
                {this.state.departmentDto.departmentDescription}
              </p>
            </div>
            <div className="row">
              <p>
                <strong>Department Code: </strong>{" "}
                {this.state.departmentDto.departmentCode}
              </p>
            </div>
          </div>
        </div>
        <br />
        <br />
        <div className="card col-md-6 offset-md-3">
          <h3 className="text-center card-header">View Organization Details</h3>
          <div className="card-body">
            <div className="row">
              <p>
                <strong>Organization Name: </strong>{" "}
                {this.state.organizationDto.organizationName}
              </p>
            </div>
            <div className="row">
              <p>
                <strong>Organization Description: </strong>{" "}
                {this.state.organizationDto.organizationDescription}
              </p>
            </div>
            <div className="row">
              <p>
                <strong>Organization Code: </strong>{" "}
                {this.state.organizationDto.organizationCode}
              </p>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default EmployeeComponent;
