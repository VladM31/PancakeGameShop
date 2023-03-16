import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

const GameRoutes = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={ <Main /> }/>
                <Route path="/game/:id" component={<Game />} />
                <Route path="/payment" component={<Payment />} />
                <Route path="/game/:gameId/level/:levelId" component={<Level />} />
                <Route path="/auth/login" component={<AuthLogin />} />
                <Route path="/auth/signUp" component={<AuthSignUp />} />
                <Route path="/library" component={<Library />} />
            </Routes>
        </Router>
    );
};

export default GameRoutes;