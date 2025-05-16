import { useEffect, useRef, useState } from "react";
import { Comment } from "../models/Comment";
import { Link } from "react-router-dom";

function ManageComments() {
    
    const [comments, setComments] = useState<Comment[]>([]);
    const nameRef = useRef<HTMLInputElement>(null);
    const emailRef = useRef<HTMLInputElement>(null);
    const bodyRef = useRef<HTMLInputElement>(null);
    const postIdRef = useRef<HTMLInputElement>(null);

    useEffect(() => {
        fetch("http://localhost:8080/comments")
            .then(res=> res.json())
            .then(json=> setComments(json));
    }, []);

    const addComment = () => {
        const newComment = {
            postId: Number(postIdRef.current?.value),
            name: nameRef.current?.value,
            email: emailRef.current?.value,
            body: bodyRef.current?.value
        };

        fetch("http://localhost:8080/comments", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newComment)
        })
        .then(res => res.json())
        .then(json => setComments(json));
    };

  return (
    <div>
        <h2>Kommentaaride haldus</h2>
        <label>PostId</label>
        <input ref={postIdRef} type="text" placeholder="Post ID" />
        <label>Nimi</label>
        <input ref={nameRef} type="text" placeholder="Nimi" />
        <label>E-mail</label>
        <input ref={emailRef} type="text" placeholder="E-mail" />
        <label>Sisu</label>
        <input ref={bodyRef} type="text" placeholder="Sisu" />
        <br />
        <button onClick={addComment}>Lisa kommentaar</button>

        <div className="comment-container">
        {comments.map(comment => (
            <div key={comment.id} className="comment-card">
            <h5>{comment.name} – <span className="text-muted">{comment.email}</span></h5>
            <p><strong>Post ID:</strong> {comment.postId}</p>
            <p>{comment.body}</p>

            <Link to={`/comment/${comment.id}`}>
                <button className="btn btn-primary btn-sm mt-2">Vaata</button>
            </Link>
            </div>
        ))}
        </div>
    </div>
  );
}

export default ManageComments