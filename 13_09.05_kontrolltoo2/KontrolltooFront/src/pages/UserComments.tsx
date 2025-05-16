import { useParams, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { Comment } from '../models/Comment';

function UserComments() {

    const { userId } = useParams();
    const navigate = useNavigate();
    const [comments, setComments] = useState<Comment[]>([]);

    useEffect(() => {
        fetch(`http://localhost:8080/comments-user?userId=${userId}`)
            .then(res=> res.json())
            .then(json=> setComments(json));
    }, [userId]);

  return (
    <div className="container mt-4">
      <button className="btn btn-secondary mb-3" onClick={() => navigate(-1)}>← Tagasi</button>
      <h3>Kasutaja kommentaarid</h3>
      {comments.map(comment => (
        <div key={comment.id} className="comment-card">
          <p><strong>{comment.name}</strong> – {comment.email}</p>
          <p>{comment.body}</p>
        </div>
      ))}
    </div>
  );
}

export default UserComments