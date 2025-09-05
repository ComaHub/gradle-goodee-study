import { useNavigate } from "react-router-dom"

export default function Logout() {
  sessionStorage.removeItem("accessToken")
  localStorage.removeItem("refreshToken")

  const nav = useNavigate()
  nav("/")
}