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
                <Route path="/" element={<Main />}/>
                <Route path="/game/:id" element={<Game />}/>
                <Route path="/payment" element={<Payment />}/>
                <Route path="/game/:gameId/level/:levelId" element={<Level />}/>
                <Route path="/auth/login" element={<Login />}/>
                <Route path="/auth/signUp" element={<SignUp />}/>
                <Route path="/library " element={<Library />}/>
            </Routes>
        </Router>
    )
}

export default GameRoutes;