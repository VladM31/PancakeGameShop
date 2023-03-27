import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Main from './pages/Main';
import Game from './pages/Game';
import Payment from './pages/Payment';
import Level from './pages/Level';
import Login from './pages/Login';
import SignUp from './pages/SignUp';
import Library from './pages/Library';

function GameRoutes() {
    return(
        <Router>
            <Routes>
                <Route path="/" name="Main" element={<Main />}/>
                <Route path="/game/:id" name="Game"  element={<Game />}/>
                <Route path="/payment" name="Payment"  element={<Payment />}/>
                <Route path="/game/:gameId/level/:levelId" name="Level"  element={<Level />}/>
                <Route path="/auth/login" name="Login"  element={<Login />}/>
                <Route path="/auth/signUp" name="SignUp"  element={<SignUp />}/>
                <Route path="/library" name="Library"  element={<Library />}/>
            </Routes>
        </Router>
    )
}

export default GameRoutes;