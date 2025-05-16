import { useCallback, useEffect, useRef, useState } from "react";
import { Word } from "../models/Word";
import { Link } from "react-router-dom";

function MainPage() {
  const [words, setWords] = useState<Word[]>([]);
  const [totalPages, setTotalPages] = useState(0);
  const [page, setPage] = useState(0);
  const [wordsPerPage, setWordsPerPage] = useState(3);
  const [sort, setSort] = useState("type,asc");

  const sizeRef = useRef<HTMLSelectElement>(null);

//   useEffect(() => {
//     fetch("http://localhost:8080/words")
//       .then(res => res.json())
//         .then(json => setWords(json));
//   }, []);

  // andmete laadimise funktsioon
  const loadWords = useCallback((currentPage: number) => {
    setPage(currentPage);
    fetch(`http://localhost:8080/paginated-words?page=${currentPage}&size=${wordsPerPage}&sort=${sort}`)
      .then(res => res.json())
      .then(json => {
        setWords(json.content);
        setTotalPages(json.totalPages);
      });
  }, [wordsPerPage, sort]);

  // lae kohe lehe avamisel
  useEffect(() => {
    loadWords(0);
  }, [loadWords]);

  // kui muuta sõnade arvu per leht, uueneb
  const handleSizeChange = () => {
    const newSize = Number(sizeRef.current?.value || 3);
    setWordsPerPage(newSize);
  };

  return (
    <div>
      <h2>Sõnad</h2>

      <button onClick={() => { setSort("type,asc"); setPage(0); }}>Sorteeri A-Z</button>
      <button onClick={() => { setSort("type,desc"); setPage(0); }}>Sorteeri Z-A</button>

      <select ref={sizeRef} onChange={handleSizeChange}>
        <option value="3">3 tk</option>
        <option value="5">5 tk</option>
        <option value="10">10 tk</option>
      </select>

      {words.map(word => (
        <div key={word.id}>
          <Link to={`/word/${word.id}`}>{word.type}</Link>
        </div>
      ))}

      <div style={{ marginTop: "1rem" }}>
        <button disabled={page === 0} onClick={() => loadWords(page - 1)}>Eelmine</button>
        <span style={{ margin: "0 10px" }}>{page + 1} / {totalPages}</span>
        <button disabled={page >= totalPages - 1} onClick={() => loadWords(page + 1)}>Järgmine</button>
      </div>
    </div>
  );
}

export default MainPage;
