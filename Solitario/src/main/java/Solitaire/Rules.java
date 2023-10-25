package Solitaire;

import Klondike.AddingChecker;
import Elements.Game;
import Klondike.SequenceChecker;

public abstract class Rules {
        private AddingChecker addingChecker;

        private SequenceChecker sequenceChecker;

        public boolean checkGameStatus(Game game) {
                return game.gameStatus();
        }
        protected abstract void gameInit(Game game);
}
