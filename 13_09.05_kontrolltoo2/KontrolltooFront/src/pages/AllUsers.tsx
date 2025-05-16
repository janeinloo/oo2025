import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { Users } from '../models/Users';


function AllUsers() {

    const [users, setUsers] = useState<Users[]>([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [sortDirection, setSortDirection] = useState("asc");

    useEffect(() => {
    fetch(`http://localhost:8080/users?page=${page}&size=3&sort=name,${sortDirection}`)
      .then(res => res.json())
      .then(json => {
        setUsers(json.content);
        setTotalPages(json.totalPages);
      });
  }, [page, sortDirection]);

  return (
    <div className="container mt-4">
    <h3>Kasutajad</h3>

    <div className="d-flex justify-content-center gap-2 mb-3">
      <button
        className="btn btn-outline-primary"
        onClick={() => {
          setSortDirection("asc");
          setPage(0);
        }}
      >
        Sorteeri A–Z
      </button>
      <button
        className="btn btn-outline-primary"
        onClick={() => {
          setSortDirection("desc");
          setPage(0);
        }}
      >
        Sorteeri Z–A
      </button>
    </div>

    <ul className="list-group mb-3">
      {users.map(user => (
        <li key={user.id} className="list-group-item d-flex justify-content-between">
          <div><strong>{user.name}</strong> – {user.email}</div>
          <Link to={`/user/${user.id}`}>
            <button className="btn btn-sm btn-primary">Vaata kommentaare</button>
          </Link>
        </li>
      ))}
    </ul>

    <div className="d-flex justify-content-center gap-2">
      <button disabled={page === 0} onClick={() => setPage(page - 1)} className="btn btn-secondary">← Eelmine</button>
      <span>Leht {page + 1}</span>
      <button disabled={page + 1 >= totalPages} onClick={() => setPage(page + 1)} className="btn btn-secondary">Järgmine →</button>
    </div>
  </div>
);
}

export default AllUsers