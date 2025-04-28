import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Athlete } from "../models/Athletes";

function SingleAthlete() {
    const { athleteId } = useParams();
    const [athlete, setAthlete] = useState<Athlete>();

    useEffect(() => {
        fetch("http://localhost:8080/athletes/" + athleteId)
            .then(res => res.json())
            .then(json => setAthlete(json));
    }, [athleteId]);

  return (
    <div>
        <div>Name: {athlete?.name}</div>
        <div>Country: {athlete?.country}</div>
        <div>Age: {athlete?.age}</div>
        <div>TotalPoints: {athlete?.totalPoints}</div>
    </div>
  )
}

export default SingleAthlete