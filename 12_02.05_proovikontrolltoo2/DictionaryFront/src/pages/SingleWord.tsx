import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { Word } from "../models/Word";


function SingleWord() {

    const { wordId } = useParams();
    const [word, setWord] = useState<Word>();

    useEffect(() => {
        fetch(`http://localhost:8080/words/${wordId}`)
            .then(res => res.json())
            .then(json => setWord(json));
    }, [wordId]);

    if (!word) {
        return <div>Word not found</div>;
    }

  return (
    <div>
        <div>Word: {word?.type}</div>
        <div>Description: {word?.description}</div>
        <Link to={`/edit-word/${word.id}`}>
            <button>Edit</button>
        </Link>
        <Link to="/">Back</Link>
    </div>
  );
}

export default SingleWord