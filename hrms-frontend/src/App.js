import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Attendance from "./pages/Attendance";
import LeaveManagement from "./pages/LeaveManagement";
import Employees from "./pages/Employees";
import Dashboard from "./pages/Dashboard";
import "./App.css";

function App() {
  return (
    <Router>
      <div className="app-container">

        {/* Sidebar */}
        <div className="sidebar">
          <h2>HRMS</h2>
          <ul>
            <li><Link to="/dashboard">Dashboard</Link></li>
            <li><Link to="/employees">Employees</Link></li>
            <li><Link to="/attendance">Attendance</Link></li>
            <li><Link to="/leave">Leave Management</Link></li>
          </ul>
        </div>

        {/* Main */}
        <div className="main">
          <Routes>
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/employees" element={<Employees />} />
            <Route path="/attendance" element={<Attendance />} />
            <Route path="/leave" element={<LeaveManagement />} />
          </Routes>
        </div>

      </div>
    </Router>
  );
}

export default App;