import { useEffect, useState } from "react";
import { Product } from "../models/Products";

function ManageProducts() {

  const [products, setProducts] = useState<Product[]>([]);

  useEffect(() => {
    fetch("http://localhost:8080/products")
        .then(res=>res.json())
        .then(json=> setProducts(json))
  }, []);

  const deleteProduct = (id: number) => {
    fetch(`http://localhost:8080/products/${id}`, {
      method: "DELETE",
    }).then(() => 
      setProducts(products.filter(product => product.id !== id)));
    ;
  };

  return (
    <div>
      <h2>Manage Products</h2>
      <table border="1">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Image</th>
            <th>Category</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product) => (
            <tr key={product.id}>
              <td>{product.id}</td>
              <td>{product.name}</td>
              <td>{product.price}</td>
              <td>
                <img src={product.image} alt={product.name} width="50" height="50" />
              </td>
              <td>{product.category?.name}</td>
              <td>
              <button onClick={() => deleteProduct(product.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ManageProducts;