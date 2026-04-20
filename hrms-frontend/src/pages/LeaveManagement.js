import { useState } from "react";
import API from "../api/axios";

function LeaveManagement() {
  const [employeeId, setEmployeeId] = useState("");
  const [leaveType, setLeaveType] = useState("PL");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [leaves, setLeaves] = useState([]);

  // Fetch leaves
  const fetchLeaves = async () => {
    const res = await API.get("/leaves", {
      params: { employeeId }
    });
    setLeaves(res.data);
  };

  // Apply leave
  const applyLeave = async () => {
    await API.post("/leaves", {
      employeeId,
      leaveType,
      startDate,
      endDate
    });

    alert("Leave Applied");
    fetchLeaves();
  };

  return (
    <div>
      <h2>Leave Management</h2>

      {/* Apply Leave */}
      <div className="card">
        <h3>Apply Leave</h3>

        <input
          placeholder="Employee ID"
          onChange={(e) => setEmployeeId(e.target.value)}
        />

        <select onChange={(e) => setLeaveType(e.target.value)}>
          <option value="PL">PL</option>
          <option value="CL">CL</option>
          <option value="LWP">LWP</option>
        </select>

        <input type="date" onChange={(e) => setStartDate(e.target.value)} />
        <input type="date" onChange={(e) => setEndDate(e.target.value)} />

        <button onClick={fetchLeaves}>Search</button>
        <button onClick={applyLeave}>Apply Leave</button>
      </div>

      {/* Leave List */}
      {leaves.map((leave) => (
        <div className="card" key={leave.id}>
          <p><b>Type:</b> {leave.leaveType}</p>
          <p><b>Start:</b> {leave.startDate}</p>
          <p><b>End:</b> {leave.endDate}</p>
          <p><b>Total Days:</b> {leave.totalDays}</p>
        </div>
      ))}
    </div>
  );
}

export default LeaveManagement;