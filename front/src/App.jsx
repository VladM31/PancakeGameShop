import GameRoutes from "./routes";
import { useSelector } from "react-redux";
import { useEffect } from "react";
import Header from "./components/UI/Header";

function App() {
  const user = useSelector(state => state);

  useEffect(() => {
    console.log(user);
  }, [user]);

  return (
    <>
      <Header>
        <GameRoutes />
      </Header>
    </>
  );
}

export default App;
