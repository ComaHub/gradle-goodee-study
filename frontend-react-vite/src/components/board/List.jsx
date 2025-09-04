import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function List() {
  const [boards, setBoards] = useState([])
  const [page, setPage] = useState(0)

  const products = [
    { title : '정유니', id : 1 },
    { title : '정리제', id : 2 },
    { title : '정나나', id : 3 }
  ]

  const result = products.map((prod) => 
    <li key={prod.id}>
      {prod.title}
    </li>
  )

  function nextPage() {
    setPage(page + 1);
  }

  useEffect(() => {
    fetch(`http://localhost/api/notice?page=${page}`, {
      method : "GET"
    })
    .then((data) => data.json())
    .then((data) => {
      const fetchData = data.content.map((value) =>
        <li key={value.boardNum}>
          {value.boardTitle}
        </li>
      )
  
      setBoards(fetchData)
    }
    )
  }, [page])

  
  return (
    <>
      <h1>List Page</h1>
      <ul>{result}</ul>
      <ul>{boards}</ul>
      <div>
        <h3>Page: {page}</h3>
        <button onClick={nextPage}>NEXT</button>
      </div>
      <div>
        <Link to="/notice/add">Add</Link>
      </div>
    </>
  )
}