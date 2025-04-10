import { useEffect, useState } from "react";
import { Category } from "../models/Category";
function ManageCategories() {

const [kategooriad, setKategooriad] = useState<Category[]>([]);

useEffect(() => {
  fetch("http://localhost:8080/categories")
      .then(res=>res.json())
      .then(json=> setKategooriad(json))
}, []);

const deleteCategory = (id: number) => {
  fetch(`http://localhost:8080/categories/${id}`, {
    method: "DELETE",
  }).then(() => 
    setKategooriad(kategooriad.filter(category => category.id !== id)));
  ;
};

return (

  <div>
    <h2>Manage Categories</h2>
    <table border="1">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Active</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {kategooriad.map((kategooria) => (
          <tr key={kategooria.id}>
            <td>{kategooria.id}</td>
            <td>{kategooria.name}</td>
            <td>{kategooria.active ? "Yes" : "No"}</td>
            <td>
              <button onClick={() => deleteCategory(kategooria.id)}>Delete</button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
);
}

export default ManageCategories