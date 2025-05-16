import { useEffect, useRef, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Word } from "../models/Word";
import { ToastContainer, toast } from "react-toastify";

function EditWord() {

    const { wordId } = useParams();
    const [word, setWord] = useState<Word>();
    const navigate = useNavigate();
    const typeRef = useRef<HTMLInputElement>(null);
    const descriptionRef = useRef<HTMLInputElement>(null);

    useEffect(() => {
        fetch("http://localhost:8080/words/" + wordId)
            .then(res=> res.json())
            .then(json=> setWord(json));
    }, [wordId]);

    const updateWord = () => {
        const updatedWord = {
            id: word?.id,
            type: typeRef.current?.value || "",
            description: descriptionRef.current?.value || ""
        };

        fetch("http://localhost:8080/words", {
            method: "PUT",
            body: JSON.stringify(updatedWord),
            headers: {
                "Content-Type": "application/json"
            }
        })
        .then(res => res.json())
        .then(json => {
            if (Array.isArray(json)) {
                toast.success("Word updated successfully");
                navigate("/");
            } else {
                toast.error("Failed to update word");
            }
        });
    };

    if (!word) {
        return <div>Word not found</div>;
    };

  return (
    <div>
        <h2>Edit word</h2>
        <label>Type</label> <br />
        <input type="text" defaultValue={word?.type} ref={typeRef} /> <br />
        <label>Description</label> <br />
        <input type="text" defaultValue={word?.description} ref={descriptionRef} /> <br />
        <button onClick={updateWord}>Update Word</button>
        <ToastContainer />
    </div>
  );
}

export default EditWord