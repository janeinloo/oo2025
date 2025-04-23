import { useEffect, useState } from "react";
import { Athlete } from "../models/Athletes";
import { Result } from "../models/Results";


function MainPage() {

    const [athletes, setAthletes] = useState<Athlete[]>([]);
    const [results, setResults] = useState<Result[]>([]);
    const [totalAthletes, setTotalAthletes] = useState(0);
    const countriesByPage = 1;
    const [page, setPage] = useState(0);
    const [activeCountry, setActiveCountry] = useState("");
    const [countries, setCountries] = useState<string[]>([]);



    useEffect(() => {
        fetch("http://localhost:8080/athletes")
            .then(res=>res.json())
            .then((json: Athlete[])=> setCountries([...new Set(json.map((a: Athlete) => a.country))]))
    }, []);
    
    useEffect(() => {
        showByCountry("", 0);
    }, []);

  function showByCountry(athleteCountry: string, currentPage: number) {
    setActiveCountry(athleteCountry);
    setPage(currentPage);
    fetch("http://localhost:8080/athletes-country?country=" + athleteCountry + "&size=" + countriesByPage + "&page=" + currentPage)
            .then(res=>res.json())
            .then(json=> {
              setAthletes(json.content)
              setTotalAthletes(json.totalElements);
            }) //Kui on riik stringiga sportlase all, mis siis siia panna, et muutuks? Kui ei hakka tööle ilusti ss küsin homme, aga teen nii palju ära kui oskan/saan.
  }

  function updatePage(newPage: number) {
    showByCountry(activeCountry, newPage);
  }

  // kas tundub yldsegi oige? showByCountry("")}
  return (
    <div>
        <div>Sportlasi kokku: {totalAthletes}</div>
        <button onClick={() => showByCountry("", 0)}>All athletes</button>
        {countries.map(country => 
          <button key={country} onClick={() => showByCountry(country, 0)}>
            {country}
  </button>
)}
      <br />
      <br />

      {athletes.map(athlete => (
      <div key={athlete.id} className="athlete-card">
        <div className="athlete-name">{athlete.name}</div>
        <div>{athlete.country}</div>
        <div>{athlete.age}</div>
        <div>{athlete.totalPoints}</div>
      </div>
    ))}

      {results.map(result =>
        <div key={result.id}>
          <div>{result.athlete.name}</div>
          <div>{result.event}</div>
          <div>{result.score}</div>
          <div>{result.points}</div>
        </div> )}
        <button disabled={page === 0} onClick={() => updatePage(page - 1)}>Eelmine</button>
        <span>{page + 1}</span>
        <button disabled={page === Math.ceil(totalAthletes/countriesByPage-1)} onClick={() => updatePage(page + 1)}>Järgmine</button>
    </div>
  )
}

export default MainPage