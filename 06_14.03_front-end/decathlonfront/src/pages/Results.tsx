import { useEffect, useState } from "react";
import { Result } from "../models/Results";

function Results() {

    const [results, setResults] = useState<Result[]>([]);

    useEffect(() => {
        fetch("http://localhost:8080/results")
            .then(res=>res.json())
            .then(json=> setResults(json))
    }, []);

    const deleteResult = (id: number) => {
        fetch(`http://localhost:8080/results/${id}`, { method: "DELETE" })
            .then(() => setResults(results.filter(result => result.id !== id)));
    };

    return (
        <div>
            <h2>Results</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>Athlete</th>
                        <th>Event</th>
                        <th>Score</th>
                        <th>Points</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {results.map(result => (
                        <tr key={result.id}>
                            <td>{result.athlete.name}</td>
                            <td>{result.event}</td>
                            <td>{result.score}</td>
                            <td>{result.points}</td>
                            <td>
                                <button onClick={() => deleteResult(result.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default Results;