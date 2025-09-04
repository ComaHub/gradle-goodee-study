import { useEffect, useState } from "react"

export default function StudyEffect() {
  const [count, setCount] = useState(0)

  function increase() {
    setCount(count + 1)
  }

  // 렌더링 할 때마다 실행 (Mount, Status 변경 시)
  useEffect(() => {
    console.log("EFFECT 1")
  })

  // 빈 배열 추가 시 Mount할 때만 실행 ([] <= 의존성 배열)
  useEffect(() => {
    console.log("EFFECT 2")
  }, [])
  
  // 배열에 변수를 추가하면 해당 변수의 값이 바뀔 때도 실행
  useEffect(() => {
    console.log("EFFECT 3")
  }, [count])

  useEffect(() => {

    // Clean-up Code (해당 component가 unmount될 때 실행하고 싶은 code)
    return () => {

    }
  })
  
  return (
    <>
      <h1>Use Effect</h1>
      <h1>{count}</h1>
      <button onClick={increase}>NEXT</button>
    </>
  )
}