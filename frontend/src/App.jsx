import { useEffect, useState } from "react";
import "./App.css";

function App() {
  const [assets, setAssets] = useState([]);
  const [count, setCount] = useState(0);
  const [types, setTypes] = useState({});
  const [search, setSearch] = useState("");

  useEffect(() => {
    fetch("/api/assets")
      .then(res => res.json())
      .then(data => setAssets(data));

    fetch("/api/assets/count")
      .then(res => res.json())
      .then(data => setCount(data));

    fetch("/api/assets/types")
      .then(res => res.json())
      .then(data => setTypes(data));
  }, []);

  const filteredAssets = assets.filter(asset =>
    (asset.displayName || "")
      .toLowerCase()
      .includes(search.toLowerCase())
  );

  return (
    <div className="app">

      <header>
        <h1>☁️ Cloud Asset Inventory</h1>
        <p>Google Cloud Asset Discovery Dashboard</p>
      </header>

      <div className="cards">

        <div className="card">
          <h3>Total Assets</h3>
          <h2>{count}</h2>
        </div>

        <div className="card">
          <h3>Services</h3>
          <h2>{types["serviceusage.googleapis.com/Service"] || 0}</h2>
        </div>

        <div className="card">
          <h3>Projects</h3>
          <h2>{types["cloudresourcemanager.googleapis.com/Project"] || 0}</h2>
        </div>

      </div>

      <div className="content">

        <h2>Asset Inventory</h2>

        <input
          type="text"
          placeholder="Search assets..."
          value={search}
          onChange={e => setSearch(e.target.value)}
        />

        <table>
          <thead>
            <tr>
              <th>Asset Type</th>
              <th>Name</th>
              <th>Location</th>
              <th>State</th>
            </tr>
          </thead>

          <tbody>
            {filteredAssets.map((asset, index) => (
              <tr key={index}>
                <td>{asset.assetType}</td>
                <td>{asset.displayName}</td>
                <td>{asset.location}</td>
                <td>{asset.state}</td>
              </tr>
            ))}
          </tbody>
        </table>

      </div>

    </div>
  );
}

export default App;
