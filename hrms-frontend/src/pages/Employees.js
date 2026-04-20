import React, { useEffect, useState } from "react";
import API from "../api/axios";

function Employees() {

  const [employees, setEmployees] = useState([]);
  const [form, setForm] = useState({
    firstName: "",
    lastName: "",
    email: "",
    department: "",
    salary: ""
  });
  const [editingId, setEditingId] = useState(null);

  useEffect(() => {
    fetchEmployees();
  }, []);

  // ✅ FETCH EMPLOYEES (FIXED)
  const fetchEmployees = async () => {
    try {
      const res = await API.get("/employees");

      console.log("API RESPONSE:", res.data);

      // 🔥 HANDLE ALL CASES
      const data =
        res.data?.data?.content ||   // pageable
        res.data?.data ||            // normal response
        [];

      setEmployees(data);

    } catch (err) {
      console.error("Fetch error:", err);
      setEmployees([]);
    }
  };

  // ✅ ADD / UPDATE
  const handleAdd = async () => {
    try {
      if (editingId) {
        await API.put(`/employees/${editingId}`, form);
        alert("Updated successfully ✅");
        setEditingId(null);
      } else {
        await API.post("/employees", form);
        alert("Added successfully ✅");
      }

      // reset form
      setForm({
        firstName: "",
        lastName: "",
        email: "",
        department: "",
        salary: ""
      });

      fetchEmployees();

    } catch (err) {
      console.error("Save error:", err);
      alert("Operation failed ❌");
    }
  };

  // ✅ EDIT
  const handleEdit = (emp) => {
    setForm({
      firstName: emp.firstName,
      lastName: emp.lastName,
      email: emp.email,
      department: emp.department,
      salary: emp.salary
    });
    setEditingId(emp.id);
  };

  // ✅ DELETE (FIXED WITH CHECK)
  const handleDelete = async (id) => {
    if (!id) {
      alert("Invalid ID ❌");
      return;
    }

    try {
      console.log("Deleting ID:", id);

      await API.delete(`/employees/${id}`);

      alert("Deleted successfully ✅");
      fetchEmployees();

    } catch (err) {
      console.error("Delete error:", err);
      alert("Delete failed ❌");
    }
  };

  return (
    <div>
      <h2>Employees</h2>

      {/* FORM */}
      <h3>{editingId ? "Update Employee" : "Add Employee"}</h3>

      <input
        placeholder="First Name"
        value={form.firstName}
        onChange={(e) => setForm({ ...form, firstName: e.target.value })}
      /><br /><br />

      <input
        placeholder="Last Name"
        value={form.lastName}
        onChange={(e) => setForm({ ...form, lastName: e.target.value })}
      /><br /><br />

      <input
        placeholder="Email"
        value={form.email}
        onChange={(e) => setForm({ ...form, email: e.target.value })}
      /><br /><br />

      <input
        placeholder="Department"
        value={form.department}
        onChange={(e) => setForm({ ...form, department: e.target.value })}
      /><br /><br />

      <input
        placeholder="Salary"
        value={form.salary}
        onChange={(e) => setForm({ ...form, salary: e.target.value })}
      /><br /><br />

      <button onClick={handleAdd}>
        {editingId ? "Update" : "Add"}
      </button>

      <hr />

      {/* LIST */}
      {employees.length === 0 ? (
        <p>No employees found</p>
      ) : (
        employees.map((emp) => (
          <div
            key={emp.id}
            style={{
              border: "1px solid #ccc",
              margin: "10px",
              padding: "10px",
              borderRadius: "8px"
            }}
          >
            <b>{emp.firstName} {emp.lastName}</b><br />
            {emp.email}<br />
            {emp.department}<br />
            ₹ {emp.salary}<br /><br />

            <button onClick={() => handleEdit(emp)}>
              Edit
            </button>

            <button
              onClick={() => handleDelete(emp.id)}
              style={{ marginLeft: "10px", color: "red" }}
            >
              Delete
            </button>
          </div>
        ))
      )}
    </div>
  );
}

export default Employees;