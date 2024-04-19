import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";

export default function Home() {
  const [contacts, setContacts] = useState([]);

  const { id } = useParams();

  useEffect(() => {
    loadContacts();
  }, []);

  const loadContacts = async () => {
    const result = await axios.get("http://localhost:8080/contacts");
    setContacts(result.data);
  };

  const deleteContact = async (id) => {
    await axios.delete(`http://localhost:8080/contact/${id}`);
    loadContacts();
  };

  return (
    <div className="container">
      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">S.N</th>
              <th scope="col">Photo</th>
              <th scope="col">Name</th>
              <th scope="col">Number</th>
              <th scope="col">Email</th>
              <th scope="col">Note</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {contacts.map((contact, index) => (
              <tr>
                <th scope="row" key={index}>
                  {index + 1}
                </th>
                <td>
                
                {contact.image ? <img src={contact.image} style={{ width: '30px', height: 'auto' }} alt="Contact" /> : null}
                    
                </td>
                <td>{contact.name}</td>
                <td>{contact.number}</td>
                <td>{contact.email}</td>
                <td>{contact.note}</td>
                <td>
                  
                  <Link
                    className="btn btn-outline-primary mx-2"
                    to={`/editcontact/${contact.id}`}
                  >
                    Edit
                  </Link>
                  <button
                    className="btn btn-danger mx-2"
                    onClick={() => deleteContact(contact.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
