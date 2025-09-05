import { useNavigate } from "react-router-dom";

export default function Login() {
  const nav = useNavigate()
  
  function login(event) {
    event.preventDefault();

    const formData = new FormData(event.target)
    let all = Object.fromEntries(formData.entries())

    fetch("http://localhost/api/member/login", {
      method : "POST",
      body : formData
    })
    .then((data) => {
      sessionStorage.setItem("accessToken", data.headers.get("accessToken"))
      localStorage.setItem("refreshToken", data.headers.get("refreshToken"))

      nav("/")
    })
    .catch((e) => console.log(e))
  }

  return (
    <>
      <div>
        <form onSubmit={login}>
          <input type="text" name="memberId" />
          <input type="password" name="memberPw" />
          <button>Login</button>
        </form>
      </div>
    </>
  )
}