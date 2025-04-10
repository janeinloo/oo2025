import { useEffect, useState } from "react";
import { Athlete } from "../models/Athletes";
import { Result } from "../models/Results";


function MainPage() {

    const [athletes, setAthletes] = useState<Athlete[]>([]);
    const [results, setResults] = useState<Result[]>([]);
    
    useEffect(() => {
        fetch("http://localhost:8080/athletes")
            .then(res=>res.json())
            .then(json=> setAthletes(json))
    }, []);
    
    useEffect(() => {
        fetch("http://localhost:8080/results")
            .then(res=>res.json())
            .then(json=> setResults(json))
    }, []);

  return (
    <div>
        {athletes.map(athlete =>
        <div key={athlete.id}>
          <div>{athlete.name}</div>
          <div>{athlete.country}</div>
          <div>{athlete.age}</div>
          <div>{athlete.totalPoints}</div>
        </div>
      )}
      <br />
      <br />
      {results.map(result =>
        <div key={result.id}>
          <div>{result.athlete.name}</div>
          <div>{result.event}</div>
          <div>{result.score}</div>
          <div>{result.points}</div>
        </div> )}
    </div>
  )
}

export default MainPage