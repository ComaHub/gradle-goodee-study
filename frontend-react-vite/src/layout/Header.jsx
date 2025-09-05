import { Link } from "react-router-dom"

export default function Header() {

  return (
    <>
      <Link to="/">Home</Link>
      <Link to="/notice">Notice</Link>

      <Link to="/study/state">StudyState</Link>
      <Link to="/study/ref">StudyRef</Link>
      <Link to="/study/effect">StudyEffect</Link>
      <Link to="/study/param">StudyParam</Link>
      <Link to="/study/param" state={{ name : "Coma" }}>StudyParamWithState</Link>

      <div>
        <Link to="/member/login">Login</Link>
        <Link to="/member/join">Join</Link>
      </div>

      <div>
        <Link to="/member/logout">Logout</Link>
        <Link to="/member/detail">MyPage</Link>
      </div>
    </>
  )
}