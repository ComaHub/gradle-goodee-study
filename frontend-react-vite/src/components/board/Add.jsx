import { useRef, useState } from "react"
import { useNavigate } from "react-router-dom"

export default function Add() {
  const [name, setName] = useState("test")
  let [inputFile, setInputFile] = useState([])
  const fileIdx = useRef(0)
  const nav = useNavigate()
  const title = useRef("")
  const writer = useRef("")
  const content = useRef("")
  const attach = useRef([])

  function get() {
    title.current.focus;
    setName(title.current.value)
  }

  function addBoard() {
    const formData = new FormData()
    formData.append("boardTitle", title.current.value)
    formData.append("boardWriter", writer.current.value)
    formData.append("boardContent", content.current.value)

    attach.current.forEach((attachFile) => {
      formData.append("attaches", attachFile.files[0])
    })

    fetch("http://localhost/api/notice", {
      method : "POST",
      body : formData
    })
    .then((data) => data.text())
    .then((data) => {
      if (data == "true") {
        console.log("등록 완료!")
        nav("/notice")
      }
    })
    .catch((e) => console.log(e))
  }

  function removeFileSlot(event) {
    setInputFile((prev) => {
      const thisIdx = event.target.getAttribute("data-file-idx")
      let newFiles = []

      prev.forEach((file) => {
        if (file.key != thisIdx) {
          newFiles.push(file)
        }
      })
  
      return newFiles
    })
  }

  function addFile() {
    if (inputFile.length > 4) {
      alert("최대 5개!!")
      return
    }
    
    const input = (
      <div key={fileIdx.current} >
        <input type="file" name="attaches" ref={(refs) => attach.current[fileIdx.current] = refs} />
        <button type="button" data-file-idx={fileIdx.current} onClick={removeFileSlot}>X</button>
      </div>
    )

    fileIdx.current++
    const newFiles = [...inputFile, input]
    inputFile = [...newFiles]
    console.log("input: " + inputFile.length)
    setInputFile(inputFile)
  }

  

  return (
    <>
      <h1>Add Page</h1>
      
      <input type="text" ref={title} />
      <input type="text" ref={writer}/>
      <textarea ref={content}></textarea>

      <div>
        {inputFile}
      </div>
      <div>
        <button type="button" onClick={addFile}>Add File</button>
      </div>

      <button onClick={get}>CLICK</button>
      <button onClick={addBoard}>ADD</button>
    </>
  )
}