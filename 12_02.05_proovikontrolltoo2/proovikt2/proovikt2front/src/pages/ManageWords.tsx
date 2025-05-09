import { useEffect, useRef, useState } from "react";
import { Words } from "../models/Words";



function ManageWords() {
    const [words, setWords] = useState<Words[]>([]);

    useEffect(() => {
        fetch("http://localhost:8080/words")
            .then(res => res.json())
            .then(json => setWords(json))
    }, []);

    const deleteWord = (typeID: number) => {
        fetch(`http://localhost:8080/words/${typeID}`, {
            method: "DELETE"
        }).then(() =>
        setWords(words.filter(word => word.typeID !== typeID)));
    };

    const typeRef = useRef<HTMLInputElement>(null);
    const descriptionRef = useRef<HTMLInputElement>(null);

    const addWord = () => {
        const newWord = {
            type: typeRef.current?.value,
            description: descriptionRef.current?.value
        }

        fetch(`http://localhost:8080/words`, {
            method: "POST",
            body: JSON.stringify(newWord),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res=> res.json())
        .then(json=> {
            if (json.message === undefined && json.timestamp === undefined 
                && json.status === undefined) {
                setWords(json)
            } else {
                alert("Error: " + json.message)
            }
        })
    }

    return(
        <div>
            <h2>Manage words</h2>

            <label>Type</label> <br />
            <input ref={typeRef} type="text" /> <br />
            <label>Description</label> <br />
            <input ref={descriptionRef} type="text" /> <br />
            <button onClick={() => addWord()}>Add word</button>

            <table>
                <thead>
                        <tr>
                        <th>ID</th>
                        <th>Type</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    {words.map((word) => (
                        <tr key={word.typeID}>
                            <td>{word.typeID}</td>
                            <td>{word.type}</td>
                            <td>{word.description}</td>
                            <td>
                                <button onClick={() => deleteWord(word.typeID)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default ManageWords;