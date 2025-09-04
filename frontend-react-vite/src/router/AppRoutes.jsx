import { Route, Routes } from "react-router-dom";
import Index from "../components/Index";
import List from "../components/board/List";
import Add from "../components/board/Add";
import StudyState from "../study/StudyState";
import StudyRef from "../study/StudyRef";
import StudyEffect from "../study/StudyEffect";
import StudyParam from "../study/StudyParam";
import Login from "../components/member/Login";

export default function AppRoutes() {

  return (
    <>
      <Routes>
        <Route path="/" element={<Index />} />
        <Route path="/notice">
          <Route index element={<List />} />
          <Route path="add" element={<Add />} />
        </Route>
        <Route path="/member">
          <Route path="login" element={<Login />} />
        </Route>
        <Route path="/study">
          <Route path="state" element={<StudyState />} />
          <Route path="ref" element={<StudyRef />} />
          <Route path="effect" element={<StudyEffect />} />
          <Route path="param" element={<StudyParam />} />
          <Route path="param/:name" element={<StudyParam />} />
        </Route>
      </Routes>
    </>
  )
}