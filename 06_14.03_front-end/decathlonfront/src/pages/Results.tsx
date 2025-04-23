import { useEffect, useRef, useState } from "react";
import { Result } from "../models/Results";
import { Athlete } from "../models/Athletes";
import { ToastContainer, toast } from 'react-toastify';

function Results() {

    const [results, setResults] = useState<Result[]>([]);
    const [athletes, setAthletes] = useState<Athlete[]>([]);

    useEffect(() => {
        fetch("http://localhost:8080/results")
            .then(res=>res.json())
            .then(json=> setResults(json))
    }, []);

    useEffect(() => {
        fetch("http://localhost:8080/athletes")
            .then(res=>res.json())
            .then(json=> setAthletes(json))
    }, []);

    const deleteResult = (id: number) => {
        fetch(`http://localhost:8080/results/${id}`, { method: "DELETE" })
            .then(() => setResults(results.filter(result => result.id !== id)));
    };

    const eventRef = useRef<HTMLInputElement>(null);
    const scoreRef = useRef<HTMLInputElement>(null);
    const pointsRef = useRef<HTMLInputElement>(null);
    const athleteRef = useRef<HTMLSelectElement>(null);

    const addResult = () => {
        const newResult = {
            event: eventRef.current?.value,
            score: scoreRef.current?.value,
            points: Number(pointsRef.current?.value),
            athlete: {"id": Number(athleteRef.current?.value)}
        };
        fetch(`http://localhost:8080/results`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(newResult)
        })
        .then(res => res.json())
        .then(json => {
            if (json.message === undefined && json.timestamp === undefined && json. status === undefined) {
            setResults(json)
            toast.success("Result added successfully!");
            } else {
                toast.error(json.message);
            }
        });
    };


    return (
        <div>
            <h2>Results</h2>
            <label>Event</label>
            <input ref={eventRef} type="text"/> <br />
            <label>Score</label>
            <input ref={scoreRef} type="text"/> <br />
            <label>Points</label>
            <input ref={pointsRef} type="number"/> <br />
            <label>Athlete</label>
            <select ref={athleteRef}>
                {athletes.map(athlete => <option key={athlete.id} value={athlete.id}>{athlete.name}</option>)}
            </select>
            <button onClick={() => addResult()}>Add result</button>
            <br />
            <table>
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
            <ToastContainer />
        </div>
    );
}

export default Results;