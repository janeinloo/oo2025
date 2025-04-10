import { useEffect, useState } from "react";
import { Athlete } from "../models/Athletes";

function Athletes() {

    const [athletes, setAthletes] = useState<Athlete[]>([]);

    useEffect(() => {
        fetch("http://localhost:8080/athletes")
            .then(res=>res.json())
            .then(json=> setAthletes(json))
    }, []);

    const deleteAthlete = (id: number) => {
        fetch(`http://localhost:8080/athletes/${id}`, { method: "DELETE" })
            .then(() => setAthletes(athletes.filter(athlete => athlete.id !== id)));
    };

    return (
        <div>
            <h2>Athletes</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Country</th>
                        <th>Age</th>
                        <th>Total Points</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {athletes.map(athlete => (
                        <tr key={athlete.id}>
                            <td>{athlete.name}</td>
                            <td>{athlete.country}</td>
                            <td>{athlete.age}</td>
                            <td>{athlete.totalPoints}</td>
                            <td>
                                <button onClick={() => deleteAthlete(athlete.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default Athletes;