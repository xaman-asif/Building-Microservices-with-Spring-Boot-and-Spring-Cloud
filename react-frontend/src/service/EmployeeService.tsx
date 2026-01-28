import axios from "axios";

const EMPLOYEE_SERVICE_BASE_URL = "http://10.100.59.135:9191/api/employees";

const EMPLOYEE_ID = 1;

class EmployeeService {
  getEmployee() {
    return axios.get(EMPLOYEE_SERVICE_BASE_URL + "/" + EMPLOYEE_ID);
  }
}

const employeeService = new EmployeeService();
export default employeeService;
