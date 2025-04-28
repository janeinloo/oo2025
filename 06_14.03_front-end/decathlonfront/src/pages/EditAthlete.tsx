import { useEffect, useRef, useState } from "react";
import { Athlete } from "../models/Athletes";
import { useNavigate, useParams } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";

function EditAthlete() {

    const { athleteId } = useParams();
    const nameRef = useRef<HTMLInputElement>(null);
    const countryRef = useRef<HTMLInputElement>(null);
    const ageRef = useRef<HTMLInputElement>(null);
    const totalPointsRef = useRef<HTMLInputElement>(null);
    const [athlete, setAthlete] = useState<Athlete>();
    const navigate = useNavigate();

    useEffect(() => {
        fetch("http://localhost:8080/athletes/" + athleteId)
            .then(res=>res.json())
            .then(json=> setAthlete(json))
    }, [athleteId]);
    
    const editAthlete = () => {
        const modifiedAthlete = {
            id: athleteId,
            name: nameRef.current?.value,
            country: countryRef.current?.value,
            age: Number(ageRef.current?.value),
            totalPoints: Number(totalPointsRef.current?.value)
        }

        fetch("http://localhost:8080/athletes", {
            method: "PUT",
            body: JSON.stringify(modifiedAthlete),
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then(res=>res.json())
            .then(json=> {
                if(json.message && json.timestamp && json.status) {
                    //alert(json.message);
                    toast.error(json.message);
                } else {
                    navigate("/athletes");
                }
            });
    }

    if (athlete === undefined) {
        return <div>Athlete not found</div>
    }

  return (
    <div>
        <label>Name</label> <br />
        <input ref={nameRef} defaultValue={athlete?.name} type="text"/> <br />
        <label>Country</label> <br />
        <input ref={countryRef} defaultValue={athlete?.country} type="text"/> <br />
        <label>Age</label> <br />
        <input ref={ageRef} defaultValue={athlete?.age} type="text"/> <br />
        <label>Total points</label> <br />
        <input ref={totalPointsRef} defaultValue={athlete?.totalPoints} type="number"/> <br />
        <button onClick={() => editAthlete()}>Edit athlete</button>
        <ToastContainer/>
    </div>
  )
}

export default EditAthlete