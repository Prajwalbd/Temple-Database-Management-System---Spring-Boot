import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { api, endpoints } from "../services/api";

const Login = () => {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    phone: "",
    password: "",
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleChange = (event) => {
    const { name, value } = event.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setError("");
    setLoading(true);
    try {
      const { data } = await api.post(endpoints.auth.login, form);
      if (data.success) {
        localStorage.setItem("templeUser", JSON.stringify(data));
        navigate("/dashboard");
      } else {
        setError(data.message || "Login failed");
      }
    } catch (err) {
      const message =
        err.response?.data?.message ||
        err.response?.data?.error ||
        "Unable to login. Please try again.";
      setError(message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-page">
      <div className="auth-card">
        <h1>Temple Database</h1>
        <p className="subtitle">Sign in to manage temple operations</p>
        <form onSubmit={handleSubmit} className="auth-form">
          <label>
            Phone Number
            <input
              type="tel"
              name="phone"
              pattern="[0-9]{10}"
              value={form.phone}
              onChange={handleChange}
              placeholder="Enter 10-digit phone"
              required
            />
          </label>
          <label>
            Password
            <input
              type="password"
              name="password"
              value={form.password}
              onChange={handleChange}
              placeholder="Enter password"
              required
            />
          </label>
          {error && <p className="error-text">{error}</p>}
          <button type="submit" className="primary-btn" disabled={loading}>
            {loading ? "Signing in..." : "Login"}
          </button>
        </form>
        <p className="auth-toggle">
          New here? <Link to="/register">Create an account</Link>
        </p>
      </div>
    </div>
  );
};

export default Login;





