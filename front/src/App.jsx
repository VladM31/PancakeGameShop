import GameRoutes from "./routes";
import BasketModal from "./components/UI/Modals/BasketModal";
// import { useSelector } from "react-redux";
// import { useEffect } from "react";
function App() {
  // const user = useSelector(state => state);

  // useEffect(() => {
  //   console.log(user);
  // }, [user]);

  return (
    <>
      <BasketModal />
      <GameRoutes />
    </>
  );
}

export default App;
