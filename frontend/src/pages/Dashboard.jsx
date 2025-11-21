import { useEffect, useMemo, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { api, endpoints } from "../services/api";

const SectionCard = ({ title, subtitle, children }) => (
  <section className="section-card">
    <header>
      <h3>{title}</h3>
      {subtitle && <p className="section-subtitle">{subtitle}</p>}
    </header>
    {children}
  </section>
);

const useCrudSection = (fetchUrl) => {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const loadItems = async () => {
    setLoading(true);
    setError("");
    try {
      const { data } = await api.get(fetchUrl);
      setItems(data);
    } catch (err) {
      setError(
        err.response?.data?.message ||
          err.response?.data?.error ||
          "Unable to fetch data"
      );
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadItems();
  }, [fetchUrl]);

  return { items, loading, error, reload: loadItems, setItems, setError };
};

const formatDateInput = (value) => {
  if (!value) return "";
  return value.split("T")[0];
};

const formatDateDisplay = (value) => {
  if (!value) return "-";
  try {
    return new Date(value).toLocaleDateString();
  } catch {
    return value;
  }
};

const PoojaSection = () => {
  const initialForm = useMemo(
    () => ({
      poojaName: "",
      poojaDetails: "",
      poojaPrice: "",
    }),
    []
  );
  const { items, loading, error, reload, setError } = useCrudSection(
    endpoints.poojas
  );
  const [form, setForm] = useState(initialForm);
  const [editingId, setEditingId] = useState(null);
  const [actionLoading, setActionLoading] = useState(false);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const resetForm = () => {
    setForm(initialForm);
    setEditingId(null);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setActionLoading(true);
    try {
      if (editingId) {
        await api.put(`${endpoints.poojas}/${editingId}`, form);
      } else {
        await api.post(endpoints.poojas, form);
      }
      resetForm();
      await reload();
    } catch (err) {
      setError(
        err.response?.data?.error ||
          err.response?.data?.message ||
          "Action failed"
      );
    } finally {
      setActionLoading(false);
    }
  };

  const handleEdit = (pooja) => {
    setEditingId(pooja.id);
    setForm({
      poojaName: pooja.poojaName,
      poojaDetails: pooja.poojaDetails || "",
      poojaPrice: pooja.poojaPrice || "",
    });
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Delete this pooja?")) return;
    setActionLoading(true);
    try {
      await api.delete(`${endpoints.poojas}/${id}`);
      await reload();
      if (editingId === id) resetForm();
    } catch (err) {
      setError(err.response?.data?.error || "Unable to delete");
    } finally {
      setActionLoading(false);
    }
  };

  return (
    <SectionCard
      title="Pooja Management"
      subtitle="Create, edit or delete pooja offerings."
    >
      <form className="grid-form" onSubmit={handleSubmit}>
        <label>
          Pooja Name
          <input
            name="poojaName"
            value={form.poojaName}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Details
          <textarea
            name="poojaDetails"
            rows={2}
            value={form.poojaDetails}
            onChange={handleChange}
          />
        </label>
        <label>
          Price (₹)
          <input
            type="number"
            min="0"
            step="0.01"
            name="poojaPrice"
            value={form.poojaPrice}
            onChange={handleChange}
            required
          />
        </label>
        <div className="form-actions">
          <button type="submit" className="primary-btn" disabled={actionLoading}>
            {editingId ? "Update Pooja" : "Add Pooja"}
          </button>
          {editingId && (
            <button type="button" className="ghost-btn" onClick={resetForm}>
              Cancel
            </button>
          )}
        </div>
      </form>
      {error && <p className="error-text">{error}</p>}
      <div className="table-wrapper">
        {loading ? (
          <p>Loading poojas...</p>
        ) : (
          <table>
            <thead>
              <tr>
                <th>Name</th>
                <th>Details</th>
                <th>Price</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {items.length === 0 && (
                <tr>
                  <td colSpan={4}>No poojas available.</td>
                </tr>
              )}
              {items.map((pooja) => (
                <tr key={pooja.id}>
                  <td>{pooja.poojaName}</td>
                  <td>{pooja.poojaDetails || "-"}</td>
                  <td>₹ {pooja.poojaPrice}</td>
                  <td className="table-actions">
                    <button onClick={() => handleEdit(pooja)}>Edit</button>
                    <button onClick={() => handleDelete(pooja.id)}>
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </SectionCard>
  );
};

const MembersSection = () => {
  const initialForm = useMemo(
    () => ({
      memberName: "",
      memberPhoneNo: "",
      memberDetails: "",
      date: "",
      poojaId: "",
    }),
    []
  );
  const { items: members, loading, error, reload, setError } = useCrudSection(
    endpoints.members
  );
  const { items: poojas } = useCrudSection(endpoints.poojas);
  const [form, setForm] = useState(initialForm);
  const [editingId, setEditingId] = useState(null);
  const [actionLoading, setActionLoading] = useState(false);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const resetForm = () => {
    setForm(initialForm);
    setEditingId(null);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setActionLoading(true);
    try {
      if (editingId) {
        await api.put(`${endpoints.members}/${editingId}`, form);
      } else {
        await api.post(endpoints.members, form);
      }
      resetForm();
      await reload();
    } catch (err) {
      setError(
        err.response?.data?.error ||
          err.response?.data?.message ||
          "Action failed"
      );
    } finally {
      setActionLoading(false);
    }
  };

  const handleEdit = (member) => {
    setEditingId(member.id);
    setForm({
      memberName: member.memberName,
      memberPhoneNo: member.memberPhoneNo,
      memberDetails: member.memberDetails || "",
      date: formatDateInput(member.date),
      poojaId: member.pooja?.id || "",
    });
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Delete this member?")) return;
    setActionLoading(true);
    try {
      await api.delete(`${endpoints.members}/${id}`);
      await reload();
      if (editingId === id) resetForm();
    } catch (err) {
      setError(err.response?.data?.error || "Unable to delete");
    } finally {
      setActionLoading(false);
    }
  };

  return (
    <SectionCard
      title="Members"
      subtitle="Track members and the pooja they are associated with."
    >
      <form className="grid-form" onSubmit={handleSubmit}>
        <label>
          Member Name
          <input
            name="memberName"
            value={form.memberName}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Phone Number
          <input
            name="memberPhoneNo"
            pattern="[0-9]{10}"
            value={form.memberPhoneNo}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Details
          <textarea
            name="memberDetails"
            rows={2}
            value={form.memberDetails}
            onChange={handleChange}
          />
        </label>
        <label>
          Date
          <input
            type="date"
            name="date"
            value={form.date}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Select Pooja (price auto-applies)
          <select
            name="poojaId"
            value={form.poojaId}
            onChange={handleChange}
            required
          >
            <option value="">Choose pooja</option>
            {poojas.map((pooja) => (
              <option key={pooja.id} value={pooja.id}>
                {pooja.poojaName} — ₹{pooja.poojaPrice}
              </option>
            ))}
          </select>
        </label>
        <div className="form-actions">
          <button type="submit" className="primary-btn" disabled={actionLoading}>
            {editingId ? "Update Member" : "Add Member"}
          </button>
          {editingId && (
            <button type="button" className="ghost-btn" onClick={resetForm}>
              Cancel
            </button>
          )}
        </div>
      </form>
      {error && <p className="error-text">{error}</p>}
      <div className="table-wrapper">
        {loading ? (
          <p>Loading members...</p>
        ) : (
          <table>
            <thead>
              <tr>
                <th>Name</th>
                <th>Phone</th>
                <th>Pooja</th>
                <th>Date</th>
                <th>Price</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {members.length === 0 && (
                <tr>
                  <td colSpan={6}>No members found.</td>
                </tr>
              )}
              {members.map((member) => (
                <tr key={member.id}>
                  <td>{member.memberName}</td>
                  <td>{member.memberPhoneNo}</td>
                  <td>{member.pooja?.poojaName || "-"}</td>
                  <td>{formatDateDisplay(member.date)}</td>
                  <td>₹ {member.price}</td>
                  <td className="table-actions">
                    <button onClick={() => handleEdit(member)}>Edit</button>
                    <button onClick={() => handleDelete(member.id)}>
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </SectionCard>
  );
};

const StaffSection = () => {
  const initialForm = useMemo(
    () => ({
      staffName: "",
      staffPhone: "",
      staffDetails: "",
      staffRole: "",
      staffSalary: "",
    }),
    []
  );
  const { items, loading, error, reload, setError } = useCrudSection(
    endpoints.staff
  );
  const [form, setForm] = useState(initialForm);
  const [editingId, setEditingId] = useState(null);
  const [actionLoading, setActionLoading] = useState(false);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const resetForm = () => {
    setForm(initialForm);
    setEditingId(null);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setActionLoading(true);
    try {
      if (editingId) {
        await api.put(`${endpoints.staff}/${editingId}`, form);
      } else {
        await api.post(endpoints.staff, form);
      }
      resetForm();
      await reload();
    } catch (err) {
      setError(
        err.response?.data?.error ||
          err.response?.data?.message ||
          "Action failed"
      );
    } finally {
      setActionLoading(false);
    }
  };

  const handleEdit = (staff) => {
    setEditingId(staff.id);
    setForm({
      staffName: staff.staffName,
      staffPhone: staff.staffPhone,
      staffDetails: staff.staffDetails || "",
      staffRole: staff.staffRole,
      staffSalary: staff.staffSalary || "",
    });
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Delete this staff member?")) return;
    setActionLoading(true);
    try {
      await api.delete(`${endpoints.staff}/${id}`);
      await reload();
      if (editingId === id) resetForm();
    } catch (err) {
      setError(err.response?.data?.error || "Unable to delete");
    } finally {
      setActionLoading(false);
    }
  };

  return (
    <SectionCard title="Staff" subtitle="Manage temple staff and roles.">
      <form className="grid-form" onSubmit={handleSubmit}>
        <label>
          Staff Name
          <input
            name="staffName"
            value={form.staffName}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Phone Number
          <input
            name="staffPhone"
            pattern="[0-9]{10}"
            value={form.staffPhone}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Role
          <input
            name="staffRole"
            value={form.staffRole}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Salary (₹)
          <input
            type="number"
            min="0"
            step="0.01"
            name="staffSalary"
            value={form.staffSalary}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Details
          <textarea
            name="staffDetails"
            rows={2}
            value={form.staffDetails}
            onChange={handleChange}
          />
        </label>
        <div className="form-actions">
          <button type="submit" className="primary-btn" disabled={actionLoading}>
            {editingId ? "Update Staff" : "Add Staff"}
          </button>
          {editingId && (
            <button type="button" className="ghost-btn" onClick={resetForm}>
              Cancel
            </button>
          )}
        </div>
      </form>
      {error && <p className="error-text">{error}</p>}
      <div className="table-wrapper">
        {loading ? (
          <p>Loading staff...</p>
        ) : (
          <table>
            <thead>
              <tr>
                <th>Name</th>
                <th>Phone</th>
                <th>Role</th>
                <th>Salary</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {items.length === 0 && (
                <tr>
                  <td colSpan={5}>No staff added yet.</td>
                </tr>
              )}
              {items.map((staff) => (
                <tr key={staff.id}>
                  <td>{staff.staffName}</td>
                  <td>{staff.staffPhone}</td>
                  <td>{staff.staffRole}</td>
                  <td>₹ {staff.staffSalary}</td>
                  <td className="table-actions">
                    <button onClick={() => handleEdit(staff)}>Edit</button>
                    <button onClick={() => handleDelete(staff.id)}>
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </SectionCard>
  );
};

const HallsSection = () => {
  const initialForm = useMemo(
    () => ({
      hallName: "",
      hallDetails: "",
    }),
    []
  );
  const { items, loading, error, reload, setError } = useCrudSection(
    endpoints.halls
  );
  const [form, setForm] = useState(initialForm);
  const [editingId, setEditingId] = useState(null);
  const [actionLoading, setActionLoading] = useState(false);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const resetForm = () => {
    setForm(initialForm);
    setEditingId(null);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setActionLoading(true);
    try {
      if (editingId) {
        await api.put(`${endpoints.halls}/${editingId}`, form);
      } else {
        await api.post(endpoints.halls, form);
      }
      resetForm();
      await reload();
    } catch (err) {
      setError(
        err.response?.data?.error ||
          err.response?.data?.message ||
          "Action failed"
      );
    } finally {
      setActionLoading(false);
    }
  };

  const handleEdit = (hall) => {
    setEditingId(hall.id);
    setForm({
      hallName: hall.hallName,
      hallDetails: hall.hallDetails || "",
    });
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Delete this hall?")) return;
    setActionLoading(true);
    try {
      await api.delete(`${endpoints.halls}/${id}`);
      await reload();
      if (editingId === id) resetForm();
    } catch (err) {
      setError(err.response?.data?.error || "Unable to delete");
    } finally {
      setActionLoading(false);
    }
  };

  return (
    <SectionCard title="Hall Details" subtitle="Add halls and their details.">
      <form className="grid-form" onSubmit={handleSubmit}>
        <label>
          Hall Name
          <input
            name="hallName"
            value={form.hallName}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Details
          <textarea
            name="hallDetails"
            rows={2}
            value={form.hallDetails}
            onChange={handleChange}
          />
        </label>
        <div className="form-actions">
          <button type="submit" className="primary-btn" disabled={actionLoading}>
            {editingId ? "Update Hall" : "Add Hall"}
          </button>
          {editingId && (
            <button type="button" className="ghost-btn" onClick={resetForm}>
              Cancel
            </button>
          )}
        </div>
      </form>
      {error && <p className="error-text">{error}</p>}
      <div className="table-wrapper">
        {loading ? (
          <p>Loading halls...</p>
        ) : (
          <table>
            <thead>
              <tr>
                <th>Name</th>
                <th>Details</th>
                <th>Bookings</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {items.length === 0 && (
                <tr>
                  <td colSpan={4}>No halls created yet.</td>
                </tr>
              )}
              {items.map((hall) => (
                <tr key={hall.id}>
                  <td>{hall.hallName}</td>
                  <td>{hall.hallDetails || "-"}</td>
                  <td>{hall.bookings?.length || 0}</td>
                  <td className="table-actions">
                    <button onClick={() => handleEdit(hall)}>Edit</button>
                    <button onClick={() => handleDelete(hall.id)}>
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </SectionCard>
  );
};

const HallBookingSection = () => {
  const initialForm = useMemo(
    () => ({
      name: "",
      phoneNo: "",
      details: "",
      price: "",
      date: "",
      hallId: "",
    }),
    []
  );
  const {
    items: bookings,
    loading,
    error,
    reload,
    setError,
  } = useCrudSection(endpoints.hallBookings);
  const { items: halls } = useCrudSection(endpoints.halls);
  const [form, setForm] = useState(initialForm);
  const [editingId, setEditingId] = useState(null);
  const [actionLoading, setActionLoading] = useState(false);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const resetForm = () => {
    setForm(initialForm);
    setEditingId(null);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setActionLoading(true);
    try {
      if (editingId) {
        await api.put(`${endpoints.hallBookings}/${editingId}`, form);
      } else {
        await api.post(endpoints.hallBookings, form);
      }
      resetForm();
      await reload();
    } catch (err) {
      setError(
        err.response?.data?.error ||
          err.response?.data?.message ||
          "Action failed"
      );
    } finally {
      setActionLoading(false);
    }
  };

  const handleEdit = (booking) => {
    setEditingId(booking.id);
    setForm({
      name: booking.name,
      phoneNo: booking.phoneNo,
      details: booking.details || "",
      price: booking.price || "",
      date: formatDateInput(booking.date),
      hallId: booking.hall?.id || "",
    });
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Delete this booking?")) return;
    setActionLoading(true);
    try {
      await api.delete(`${endpoints.hallBookings}/${id}`);
      await reload();
      if (editingId === id) resetForm();
    } catch (err) {
      setError(err.response?.data?.error || "Unable to delete");
    } finally {
      setActionLoading(false);
    }
  };

  return (
    <SectionCard
      title="Hall Bookings"
      subtitle="Assign functions to available halls."
    >
      <form className="grid-form" onSubmit={handleSubmit}>
        <label>
          Person Name
          <input
            name="name"
            value={form.name}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Phone Number
          <input
            name="phoneNo"
            pattern="[0-9]{10}"
            value={form.phoneNo}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Details
          <textarea
            name="details"
            rows={2}
            value={form.details}
            onChange={handleChange}
          />
        </label>
        <label>
          Price (₹)
          <input
            type="number"
            min="0"
            step="0.01"
            name="price"
            value={form.price}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Function Date
          <input
            type="date"
            name="date"
            value={form.date}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Select Hall
          <select
            name="hallId"
            value={form.hallId}
            onChange={handleChange}
            required
          >
            <option value="">Choose hall</option>
            {halls.map((hall) => (
              <option key={hall.id} value={hall.id}>
                {hall.hallName}
              </option>
            ))}
          </select>
        </label>
        <div className="form-actions">
          <button type="submit" className="primary-btn" disabled={actionLoading}>
            {editingId ? "Update Booking" : "Add Booking"}
          </button>
          {editingId && (
            <button type="button" className="ghost-btn" onClick={resetForm}>
              Cancel
            </button>
          )}
        </div>
      </form>
      {error && <p className="error-text">{error}</p>}
      <div className="table-wrapper">
        {loading ? (
          <p>Loading bookings...</p>
        ) : (
          <table>
            <thead>
              <tr>
                <th>Name</th>
                <th>Phone</th>
                <th>Hall</th>
                <th>Date</th>
                <th>Price</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {bookings.length === 0 && (
                <tr>
                  <td colSpan={6}>No bookings yet.</td>
                </tr>
              )}
              {bookings.map((booking) => (
                <tr key={booking.id}>
                  <td>{booking.name}</td>
                  <td>{booking.phoneNo}</td>
                  <td>{booking.hall?.hallName || "-"}</td>
                  <td>{formatDateDisplay(booking.date)}</td>
                  <td>₹ {booking.price}</td>
                  <td className="table-actions">
                    <button onClick={() => handleEdit(booking)}>Edit</button>
                    <button onClick={() => handleDelete(booking.id)}>
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </SectionCard>
  );
};

const VolunteerSection = () => {
  const initialForm = useMemo(
    () => ({
      volunteerName: "",
      volunteerPhone: "",
      volunteerDetail: "",
      date: "",
    }),
    []
  );
  const { items, loading, error, reload, setError } = useCrudSection(
    endpoints.volunteers
  );
  const [form, setForm] = useState(initialForm);
  const [editingId, setEditingId] = useState(null);
  const [actionLoading, setActionLoading] = useState(false);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const resetForm = () => {
    setForm(initialForm);
    setEditingId(null);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setActionLoading(true);
    try {
      if (editingId) {
        await api.put(`${endpoints.volunteers}/${editingId}`, form);
      } else {
        await api.post(endpoints.volunteers, form);
      }
      resetForm();
      await reload();
    } catch (err) {
      setError(
        err.response?.data?.error ||
          err.response?.data?.message ||
          "Action failed"
      );
    } finally {
      setActionLoading(false);
    }
  };

  const handleEdit = (volunteer) => {
    setEditingId(volunteer.id);
    setForm({
      volunteerName: volunteer.volunteerName,
      volunteerPhone: volunteer.volunteerPhone,
      volunteerDetail: volunteer.volunteerDetail || "",
      date: formatDateInput(volunteer.date),
    });
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Delete this volunteer?")) return;
    setActionLoading(true);
    try {
      await api.delete(`${endpoints.volunteers}/${id}`);
      await reload();
      if (editingId === id) resetForm();
    } catch (err) {
      setError(err.response?.data?.error || "Unable to delete");
    } finally {
      setActionLoading(false);
    }
  };

  return (
    <SectionCard
      title="Volunteers"
      subtitle="Keep track of temple volunteers."
    >
      <form className="grid-form" onSubmit={handleSubmit}>
        <label>
          Volunteer Name
          <input
            name="volunteerName"
            value={form.volunteerName}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Phone Number
          <input
            name="volunteerPhone"
            pattern="[0-9]{10}"
            value={form.volunteerPhone}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Details
          <textarea
            name="volunteerDetail"
            rows={2}
            value={form.volunteerDetail}
            onChange={handleChange}
          />
        </label>
        <label>
          Date
          <input
            type="date"
            name="date"
            value={form.date}
            onChange={handleChange}
            required
          />
        </label>
        <div className="form-actions">
          <button type="submit" className="primary-btn" disabled={actionLoading}>
            {editingId ? "Update Volunteer" : "Add Volunteer"}
          </button>
          {editingId && (
            <button type="button" className="ghost-btn" onClick={resetForm}>
              Cancel
            </button>
          )}
        </div>
      </form>
      {error && <p className="error-text">{error}</p>}
      <div className="table-wrapper">
        {loading ? (
          <p>Loading volunteers...</p>
        ) : (
          <table>
            <thead>
              <tr>
                <th>Name</th>
                <th>Phone</th>
                <th>Date</th>
                <th>Details</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {items.length === 0 && (
                <tr>
                  <td colSpan={5}>No volunteers recorded.</td>
                </tr>
              )}
              {items.map((volunteer) => (
                <tr key={volunteer.id}>
                  <td>{volunteer.volunteerName}</td>
                  <td>{volunteer.volunteerPhone}</td>
                  <td>{formatDateDisplay(volunteer.date)}</td>
                  <td>{volunteer.volunteerDetail || "-"}</td>
                  <td className="table-actions">
                    <button onClick={() => handleEdit(volunteer)}>Edit</button>
                    <button onClick={() => handleDelete(volunteer.id)}>
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </SectionCard>
  );
};

const sectionCards = [
  {
    key: "poojas",
    label: "Poojas",
    description: "Manage pooja names, details and pricing.",
  },
  {
    key: "members",
    label: "Members",
    description: "Track devotees participating in poojas.",
  },
  {
    key: "staff",
    label: "Staff",
    description: "Maintain staff roles, salaries and contact info.",
  },
  {
    key: "halls",
    label: "Halls",
    description: "Add halls and their details for events.",
  },
  {
    key: "bookings",
    label: "Hall Bookings",
    description: "Assign halls to functions with pricing.",
  },
  {
    key: "volunteers",
    label: "Volunteers",
    description: "Store volunteer availability and contacts.",
  },
];

const Dashboard = () => {
  const navigate = useNavigate();
  const { section } = useParams();
  const user = JSON.parse(localStorage.getItem("templeUser") || "{}");
  const today = new Date().toLocaleDateString(undefined, {
    weekday: "long",
    year: "numeric",
    month: "long",
    day: "numeric",
  });

  const handleLogout = () => {
    localStorage.removeItem("templeUser");
    navigate("/login");
  };

  const goToSection = (key) => {
    navigate(`/dashboard/${key}`);
  };

  const renderSection = (sectionKey) => {
    switch (sectionKey) {
      case "members":
        return <MembersSection />;
      case "staff":
        return <StaffSection />;
      case "halls":
        return <HallsSection />;
      case "bookings":
        return <HallBookingSection />;
      case "volunteers":
        return <VolunteerSection />;
      case "poojas":
        return <PoojaSection />;
      default:
        return (
          <p className="error-text">
            Unknown section. Please go back and choose a table.
          </p>
        );
    }
  };

  const selected = sectionCards.find((card) => card.key === section);

  return (
    <div className="dashboard">
      <header className="dashboard-hero">
        <div>
          <p className="hero-eyebrow">Temple Database</p>
          <h1>Welcome back {user.name || "Administrator"}</h1>
          <p className="hero-subtitle">
            Central hub to manage poojas, members, halls, staff and daily temple
            operations.
          </p>
        </div>
        <div className="hero-meta">
          <div>
            <p className="hero-label">Today</p>
            <strong>{today}</strong>
          </div>
          <div>
            <p className="hero-label">Signed in as</p>
            <strong>{user.role || "Admin"}</strong>
          </div>
          <button className="ghost-btn logout-btn" onClick={handleLogout}>
            Logout
          </button>
        </div>
      </header>

      {!section && (
        <div className="tables-board">
          <p className="section-subtitle">
            Choose a table to manage its records.
          </p>
          <div className="tables-card">
            {sectionCards.map((card) => (
              <button
                key={card.key}
                className="table-row"
                onClick={() => goToSection(card.key)}
              >
                <span>{card.label}</span>
                <span aria-hidden="true">→</span>
              </button>
            ))}
          </div>
        </div>
      )}

      {section && (
        <div className="section-detail">
          <button
            className="ghost-btn back-btn"
            onClick={() => navigate("/dashboard")}
          >
            ← All tables
          </button>
          <h2>{selected?.label || "Manage Data"}</h2>
          <p className="section-subtitle">{selected?.description}</p>
          <div className="tab-content">{renderSection(section)}</div>
        </div>
      )}
    </div>
  );
};

export default Dashboard;

