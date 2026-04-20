import { useState } from "react";
import API from "../api/axios";

function Attendance() {
  const [employeeId, setEmployeeId] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [data, setData] = useState([]);

  // Fetch attendance
  const fetchAttendance = async () => {
    const res = await API.get("/attendance", {
      params: { employeeId, startDate, endDate }
    });
    setData(res.data);
  };

  // Punch In
  const punchIn = async () => {
    await API.post(`/attendance/punch-in?employeeId=${employeeId}`);
    alert("Punched In");
    fetchAttendance();
  };

  // Punch Out
  const punchOut = async () => {
    await API.post(`/attendance/punch-out?employeeId=${employeeId}`);
    alert("Punched Out");
    fetchAttendance();
  };

  return (
    <div>
      <h2>Attendance</h2>

      {/* Filters */}
      <div className="card">
        <input
          placeholder="Employee ID"
          value={employeeId}
          onChange={(e) => setEmployeeId(e.target.value)}
        />

        <input type="date" onChange={(e) => setStartDate(e.target.value)} />
        <input type="date" onChange={(e) => setEndDate(e.target.value)} />

        <button onClick={fetchAttendance}>Search</button>
        <button onClick={punchIn}>Punch In</button>
        <button onClick={punchOut}>Punch Out</button>
      </div>

      {/* Data */}
      {data.map((item) => (
        <div className="card" key={item.id}>
          <p><b>Date:</b> {item.date}</p>
          <p><b>Punch In:</b> {item.punchIn}</p>
          <p><b>Punch Out:</b> {item.punchOut}</p>
        </div>
      ))}
    </div>
  );
}

export default Attendance;