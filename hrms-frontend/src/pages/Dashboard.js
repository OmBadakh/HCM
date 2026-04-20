import { useState } from "react";
import API from "../api/axios";

function Dashboard() {
  const [employeeId, setEmployeeId] = useState("");
  const [month, setMonth] = useState("");
  const [year, setYear] = useState("");
  const [data, setData] = useState(null);

  const fetchDashboard = async () => {
    const res = await API.get("/dashboard", {
      params: { employeeId, month, year }
    });
    setData(res.data);
  };

  return (
    <div>

      <h2>Dashboard</h2>

      <div className="card">
        <input placeholder="Employee ID" onChange={(e) => setEmployeeId(e.target.value)} />
        <input placeholder="Month" onChange={(e) => setMonth(e.target.value)} />
        <input placeholder="Year" onChange={(e) => setYear(e.target.value)} />
        <button onClick={fetchDashboard}>Search</button>
      </div>

      {data && (
        <div className="dashboard-grid">

          <div className="stat-card green">
            Present <br /> {data.presentDays}
          </div>

          <div className="stat-card red">
            Absent <br /> {data.absentDays}
          </div>

          <div className="stat-card blue">
            PL <br /> {data.plCount}
          </div>

          <div className="stat-card orange">
            CL <br /> {data.clCount}
          </div>

        </div>
      )}
    </div>
  );
}

export default Dashboard;