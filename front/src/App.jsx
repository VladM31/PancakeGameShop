import GameRoutes from "./routes";
import { useSelector } from "react-redux";
import { useEffect } from "react";
function App() {
  const user = useSelector(state => state);

  useEffect(() => {
    console.log(user);
  }, [user]);

  return (
    <>
      <GameRoutes />
    </>
  );
}

export default App;
