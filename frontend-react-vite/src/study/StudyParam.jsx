import { useLocation, useParams, useSearchParams } from 'react-router-dom'

export default function StudyParam() {
  // useSearchParams = Query String을 조작할 때
  const [param1, setParam1] = useSearchParams();
  const name = param1.get("name")
  console.log(name)

  // useParams = 경로 변수를 가져올 때 (AppRoutes에도 설정해줘야 함)
  const param2 = useParams();
  console.log(param2.name)

  // useLocation = URL의 전체 정보를 가져올 때
  const loc = useLocation()
  const param3 = new URLSearchParams(loc.search)
  console.log(param3.get("name"))

  // state 옵션 = URL에 노출시키지 않고 데이터를 전달할 때
  const states = loc.state || {}
  console.log(states.name)
  
  return (
    <>
      <h1>Study Param</h1>
    </>
  )
}