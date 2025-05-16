import { NavDropdown } from "react-bootstrap"
import { Link } from "react-router-dom"

function Menu() {
  return (
    <div>
      <NavDropdown.Item as={Link} to="/manage/comments">
        Halda Kommentaare
      </NavDropdown.Item>
    </div>
  )
}

export default Menu