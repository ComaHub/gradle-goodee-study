import { useRef, useState } from "react"

function Add() {
  const [name, setName] = useState("test")
  const title = useRef("")
  const writer = useRef("")
  const content = useRef("")

  function get() {
    title.current.focus;
    setName(title.current.value)
  }

  function addBoard() {
    const params = new URLSearchParams();
    params.set("boardTitle", title.current.value)
    params.set("boardWriter", writer.current.value)
    params.set("boardContent", content.current.value)

    fetch("http://localhost/notice", {
      method : "POST",
      body : params
    })
    .then((data) => data.text())
    .then((data) => {
      if (data == "true") console.log("등록 완료!")
    })
    .catch((e) => console.log(e))
  }

  return (
    <>
      <h1>Add Page</h1>
      
      <input type="text" ref={title} />
      <input type="text" ref={writer}/>
      <textarea ref={content}></textarea>

      <button onClick={get}>CLICK</button>
      <button onClick={addBoard}>ADD</button>
    </>
  )
}

export default Add