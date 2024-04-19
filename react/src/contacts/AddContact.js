import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function AddContact() {
  let navigate = useNavigate();

  const [contact, setContact] = useState({
    name: "",
    number: "",
    email: "",
    note: "",
    image: null,
  });

  const { name, number, email, note } = contact;

  const onInputChange = (e) => {
    setContact({ ...contact, [e.target.name]: e.target.value });
  };

  const onImageChange = (event) => {
    setContact({ ...contact, image: event.target.files[0] });
  };

  const onSubmit = async (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append("name", name);
    formData.append("number", number);
    formData.append("email", email);
    formData.append("note", note);
    if (contact.image) {
      formData.append("image", contact.image);
    }

    try {
      await axios.post("http://localhost:8080/contact", formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
      navigate("/");
    } catch (error) {
      console.error("Error uploading data:", error);
    }
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">New Contact</h2>
          <form onSubmit={onSubmit}>
            <div className="mb-3">
              <label htmlFor="Name" className="form-label">Name</label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter name"
                name="name"
                value={name}
                onChange={onInputChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Number" className="form-label">Number</label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter Number"
                name="number"
                value={number}
                onChange={onInputChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Email" className="form-label">E-mail</label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter e-mail address"
                name="email"
                value={email}
                onChange={onInputChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Note" className="form-label">Note</label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter Note"
                name="note"
                value={note}
                onChange={onInputChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Image" className="form-label">Image</label>
              <input
                type="file"
                className="form-control"
                name="image"
                onChange={onImageChange}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">Submit</button>
            <Link className="btn btn-outline-danger mx-2" to="/">Cancel</Link>
          </form>
        </div>
      </div>
    </div>
  );
}
