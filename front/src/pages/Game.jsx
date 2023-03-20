// Game.jsx
import React from 'react';
import { useParams } from 'react-router-dom';

function Game() {
  const { id } = useParams();

  return (
    <div>
      <h1>Game {id}</h1>
      {/* Add your content here */}
    </div>
  );
}

export default Game;