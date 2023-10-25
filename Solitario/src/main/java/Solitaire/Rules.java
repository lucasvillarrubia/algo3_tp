package Solitaire;

import Klondike.AddingChecker;
import Elements.Game;
import Klondike.RemovingChecker;
import Klondike.SequenceChecker;

public abstract class Rules {
        private AddingChecker addingChecker;
        private RemovingChecker removingChecker;
        private SequenceChecker sequenceChecker;

        public boolean checkGameStatus(Game game) {
                return game.gameStatus();
        }
        protected abstract void gameInit(Game game);
}
