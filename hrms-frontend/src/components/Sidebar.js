import { Link } from "react-router-dom";


function Sidebar() {
  return (
    <div style={{
      width: "200px",
      height: "100vh",
      background: "#2c3e50",
      color: "#fff",
      padding: "20px"
    }}>
      <h2>HRMS</h2>
      <li>
  <a href="/employees">Employees</a>
</li>

      <Link to="/employees" style={{ color: "#fff" }}>
        Employees
      </Link>
      <li>
  <Link to="/attendance">Attendance</Link>
</li>
    </div>
  );
}

export default Sidebar;