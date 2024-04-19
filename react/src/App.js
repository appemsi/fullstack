import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./layout/Navbar";
import Home from "./pages/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddContact from "./contacts/AddContact";
import EditContact from "./contacts/EditContact";

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />

        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route exact path="/addcontact" element={<AddContact />} />
          <Route exact path="/editcontact/:id" element={<EditContact />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
