function Navbar() {

  const logout = () => {
    localStorage.clear();
    window.location.href = "/";
  };

  return (
    <div style={{
      padding: "10px",
      background: "#ecf0f1",
      display: "flex",
      justifyContent: "space-between"
    }}>
      <h3>Dashboard</h3>

      <button onClick={logout} style={{
        background: "red",
        color: "#fff",
        padding: "5px 10px"
      }}>
        Logout
      </button>
    </div>
  );
}

export default Navbar;