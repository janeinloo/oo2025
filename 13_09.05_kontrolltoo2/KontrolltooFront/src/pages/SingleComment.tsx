import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { Comment } from "../models/Comment";

function SingleComment() {

  const { commentId } = useParams();
  const navigate = useNavigate();
  const [comment, setComment] = useState<Comment>();

  useEffect(() => {
    fetch(`http://localhost:8080/comments/${commentId}`)
      .then(res => res.json())
      .then(json => setComment(json));
  }, [commentId]);

  if (!comment) return <div>Laen kommentaari...</div>

  return (
    <div className="container mt-4">
      <button className="btn btn-secondary mb-3" onClick={() => navigate(-1)}>← Tagasi</button>

      <h2>{comment.name}</h2>
      <p><strong>E-mail:</strong> {comment.email}</p>
      <p><strong>Post ID:</strong> {comment.postId}</p>
      <p>{comment.body}</p>
    </div>
  );
}

export default SingleComment