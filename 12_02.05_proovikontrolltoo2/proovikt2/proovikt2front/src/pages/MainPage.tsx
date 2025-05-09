import { useCallback, useEffect, useState } from "react";
import { Words } from "../models/Words";

function MainPage() {
    const [words, setWords] = useState<Words[]>([]);
    const [totalWords, setTotalWords] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [page, setPage] = useState(0);
    const [activeType, setActiveType] = useState("");
    const [sort, setSort] = useState("type,asc");


    useEffect(() => {
        fetch("http://localhost:8080/words")
            .then(res=>res.json())
            .then(json=>setWords(json))
    }, []);

    const showByType = useCallback((wordType: string, currentPage: number) => {
        setActiveType(wordType);
        setPage(currentPage);
        fetch("http://localhost:8080/words?type=" + wordType + 
            "&page=" + currentPage +
            "&sort=" + sort
        )
            .then(res=>res.json())
            .then(json=> {
                setWords(json.content);
                setTotalWords(json.totalElements);
                setTotalPages(json.totalPages);
            })
    }, [sort])


    return (
        <div>
            
        </div>
    )
}

export default MainPage;