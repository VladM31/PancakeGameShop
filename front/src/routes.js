import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Main from './pages/Main';
import Game from './pages/Game';
import Payment from './pages/Payment';
import Level from './pages/Level';
import Login from './pages/Login';
import SignUp from './pages/SignUp';
import Library from './pages/Library';
import Header from './components/UI/Header';
import BasketModal from "./components/UI/Modals/BasketModal";
import {useState} from "react";
import AccessDenied from "./pages/AccessDenied";
import VideoModal from "./components/UI/Modals/VideoModal";

function GameRoutes() {

    const [isRouteAllowed, setIsRouteAllowed] = useState(false);

    function handleRouteClick() {
        setIsRouteAllowed(true);
    }

    return(
        <Router>
            <Routes>
                <Route element={<><Header /> <BasketModal func={handleRouteClick} /> <VideoModal /> </>}>
                    <Route path="/" name="Main" element={<Main />}/>
                    <Route path="/game/:id" name="Game"  element={<Game />}/>
                    <Route path="/payment" name="Payment"  element={isRouteAllowed ? <Payment /> : <AccessDenied />}/>
                    <Route path="/game/:gameId/level/:levelId" name="Level"  element={<Level />}/>
                    <Route path="/auth/login" name="Login"  element={<Login />}/>
                    <Route path="/auth/signUp" name="SignUp"  element={<SignUp />}/>
                    <Route path="/library" name="Library"  element={<Library />}/>
                    <Route path="/accessDenied" name="AccessDenied"  element={<AccessDenied />}/>
                </Route>
            </Routes>
        </Router>
    )
}

export default GameRoutes;