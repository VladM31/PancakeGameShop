import React from 'react';
import { useParams } from 'react-router-dom';

function Level() {
  const { gameId, levelId } = useParams();

  return (
    <div>
      <h1>Game {gameId}, Level {levelId}</h1>
      {/* Add your content here */}
    </div>
  );
}

export default Level;