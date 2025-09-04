export default function Login() {
  
  function login(event) {
    event.preventDefault();

    const formData = new FormData(event.target)
    let all = Object.fromEntries(form.entries())

    fetch("http://localhost/api/member/login", {
      method : "POST",
      body : formData
    })
    .then((data) => data.json())
    .then((data) => console.log(data))
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