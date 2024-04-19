import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditContact() {
  let navigate = useNavigate();

  const { id } = useParams();

  const [contact, setContact] = useState({
    name: "",
    number: "",
    email: "",
    note: "",
    image: "",
  });

  const { name, number,email,note } = contact;

  const onInputChange = (e) => {
    setContact({ ...contact, [e.target.name]: e.target.value });
  };

  useEffect(() => {
    loadContact();
  }, []);

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.put(`http://localhost:8080/contact/${id}`, contact);
    navigate("/");
  };

  const loadContact = async () => {
    const result = await axios.get(`http://localhost:8080/contact/${id}`);
    setContact(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Edit Contact</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="Name" className="form-label">
                Name
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter name"
                name="name"
                value={name}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Number" className="form-label">
                Number
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter number"
                name="number"
                value={number}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Email" className="form-label">
                E-mail
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter e-mail address"
                name="email"
                value={email}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Note" className="form-label">
                Note
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter note"
                name="note"
                value={note}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">
              Submit
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/">
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}
