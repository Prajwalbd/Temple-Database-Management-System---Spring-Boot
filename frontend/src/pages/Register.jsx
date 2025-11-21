import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { api, endpoints } from "../services/api";

const Register = () => {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    name: "",
    phone: "",
    password: "",
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleChange = (event) => {
    const { name, value } = event.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setError("");
    setSuccess("");
    setLoading(true);
    try {
      const { data } = await api.post(endpoints.auth.register, form);
      if (data.success) {
        setSuccess("Registration successful! Redirecting to login...");
        setTimeout(() => navigate("/login"), 1200);
      } else {
        setError(data.message || "Registration failed");
      }
    } catch (err) {
      const message =
        err.response?.data?.message ||
        err.response?.data?.error ||
        "Unable to register. Please try again.";
      setError(message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-page">
      <div className="auth-card">
        <h1>Create Account</h1>
        <p className="subtitle">Register to manage temple operations</p>
        <form onSubmit={handleSubmit} className="auth-form">
          <label>
            Full Name
            <input
              type="text"
              name="name"
              value={form.name}
              onChange={handleChange}
              placeholder="Enter full name"
              required
            />
          </label>
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
              placeholder="Choose a secure password"
              required
            />
          </label>
          {error && <p className="error-text">{error}</p>}
          {success && <p className="success-text">{success}</p>}
          <button type="submit" className="primary-btn" disabled={loading}>
            {loading ? "Creating account..." : "Register"}
          </button>
        </form>
        <p className="auth-toggle">
          Already registered? <Link to="/login">Back to login</Link>
        </p>
      </div>
    </div>
  );
};

export default Register;




