  import players.*;

public class PlayerPool {
    private Player[] players;


    public PlayerPool() {
        players = new Player[0];
    }

    public PlayerPool(Player...varArgs) {
        players = varArgs;
    }


    public void checkForBancrupts() {
        for(int playerCount = 0; playerCount < players.length; playerCount++) {
            if(players[playerCount].getMoney() < 10 || (players[playerCount] instanceof Dealer)) {
                String bancrupcyMessage = "Игрок " + players[playerCount].getName() + " " +
                                            players[playerCount].getSurname() + " обанкротился";
                bancrupcyMessage += " и покидает игру.";
                System.out.println(bancrupcyMessage);
                removePlayer(players[playerCount]);
            }
        }
    }
    public void removePlayer(Player removedPlayer) {
        removedPlayer = null;
        Player[] swapPlayerPool = new Player[players.length - 1];
        int nextPlayer = 0;
        for(Player swapPlayer : players) {
            if(swapPlayer == null) {
                continue;
            }
            else {
                swapPlayerPool[nextPlayer++] = swapPlayer;
            }
        }
        players = swapPlayerPool;
    }
    public void addPlayer(Player addedPlayer) {
        Player[] swapPlayerPool = new Player[players.length + 1];
        int nextPlayer;
        for(nextPlayer = 0; nextPlayer < players.length; nextPlayer++) {
            swapPlayerPool[nextPlayer] = players[nextPlayer];
        }
        swapPlayerPool[nextPlayer] = addedPlayer;
        players = swapPlayerPool;
    }
    public Player getPlayer(int playerPositionInPool) {
        return players[playerPositionInPool];
    }
    public int getPoolSize() {
        return players.length;
    }

    // Список персон присутсвующих в игре
    public String personList() {
        StringBuffer playerList = new StringBuffer();

        for (Player nextPerson : players) {
            if (nextPerson instanceof Dealer) {
                playerList.append("\t[Крупье]\n");
                continue;
            }
            else if (nextPerson instanceof AIPlayer) {
                playerList.append("\t[Бот] ");
            }
            else if (nextPerson instanceof HumanPlayer) {
                playerList.append("\t[Человек] ");
            }
            playerList.append(nextPerson.getInfo());
            playerList.append("\n");
        }

        return playerList.toString();
    }
}
