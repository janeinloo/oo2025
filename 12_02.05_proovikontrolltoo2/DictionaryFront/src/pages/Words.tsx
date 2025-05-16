import { useEffect, useRef, useState } from "react";
import { Word } from "../models/Word";
import { ToastContainer, toast } from "react-toastify";

function Words() {

    const [words, setWords] = useState<Word[]>([]);
    const typeRef = useRef<HTMLInputElement>(null);
    const descriptionRef = useRef<HTMLInputElement>(null);

    useEffect(() => {
        fetch("http://localhost:8080/words")
          .then(res => res.json())
          .then(json => setWords(json));
      }, []);

    const addWord = () => {
        const newWord = {
            type: typeRef.current?.value,
            description: descriptionRef.current?.value
        };

        fetch("http://localhost:8080/words", {
            method: "POST",
            body: JSON.stringify(newWord),
            headers: {
                "Content-Type": "application/json"
            }
        })
        .then(res => res.json())
        .then(json => {
            if (Array.isArray(json)) {
                setWords(json);
                toast.success("Word added successfully");
                if (typeRef.current && descriptionRef.current) {
                    typeRef.current.value = "";
                    descriptionRef.current.value = "";
                }
            } else {
                toast.error("Failed to add word");
            }
        });
    };


  return (
    <div>
        <h2>Words</h2>
        <label>Type</label> <br />
        <input ref={typeRef} type="text" /> <br />
        <label>Description</label> <br />
        <input ref={descriptionRef} type="text" /> <br />
        <button onClick={addWord}>Add Word</button>

        <hr />
        {words.map(word => (
            <div key={word.id}>
                <div><strong>{word.type}</strong></div>
            </div>
        ))}
        <ToastContainer />
    </div>
  );
}

export default Words