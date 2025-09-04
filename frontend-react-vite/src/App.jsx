import Header from "./layout/Header.jsx";
import AppRoutes from "./router/AppRoutes.jsx";

export default function App() {
  let age = 10
  let obj = {
    age : 700,
    name : "정리제"
  }

  return (
    <>
      <Header />
      <AppRoutes />
    </>
  )
}