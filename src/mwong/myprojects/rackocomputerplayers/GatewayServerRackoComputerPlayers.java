package mwong.myprojects.rackocomputerplayers;

import py4j.GatewayServer;

import java.util.Random;


/**
 * GatewayServerRankoComputerPlayers for pyqt5 GUI front end to connect to
 * Racko game to provide computer player in 3 difficulty level.
 *
 * @author Meisze Wong
 *         www.linkedin.com/pub/macy-wong/46/550/37b/
 */
public class GatewayServerRackoComputerPlayers {
    private final int minPlayers = 1;
    private final int maxPlayers = 4;
    int numberOfPlayers;
    int rackoSize;
    int replacementID;
    Player[] players;

    /**
     * Initialize GatewayServerBoggle with default dictionary.
     */
    public GatewayServerRackoComputerPlayers() {
        numberOfPlayers = minPlayers;
        players = new Player[4];
    }

    /**
     * Set the number of players and card size for the Racko game.
     *
     * @param total the intrger of total number of players
     * @param cardSize the integer of the card size
     */
    public void setNumberOfPlayers(int total, int cardSize) {
        if (total < minPlayers || total > maxPlayers) {
            throw new IllegalArgumentException();
        }
        rackoSize = cardSize;
        numberOfPlayers = total;
        replacementID = total;
    }

    /**
     * Generate and return the computer player object at difficulty level Easy.
     *
     * @param id the player ID of the of the game
     * @return the computer player object
     */
    public Player getPlayerEasy(int id) {
        if (id < minPlayers || id > numberOfPlayers) {
            throw new IllegalArgumentException();
        }
        players[id - 1] = new Player1(rackoSize);
        return players[id - 1];
    }

    /**
     * Generate and return the computer player object at difficulty level Moderate.
     *
     * @param id the player ID of the of the game
     * @return the computer player object
     */
    public Player getPlayerModerate(int id) {
        if (id < minPlayers || id > numberOfPlayers) {
            throw new IllegalArgumentException();
        }
        int rand = (new Random()).nextInt(2);
        if (rand == 0) {
            players[id - 1] = new Player2(rackoSize);
        } else {
            players[id - 1] = new Player2v2(rackoSize);
        }
        return players[id - 1];
    }

    /**
     * Generate and return the computer player object at difficulty level Hard.
     *
     * @param id the player ID of the of the game
     * @return the computer player object
     */
    public Player getPlayerHard(int id) {
        if (id < minPlayers || id > numberOfPlayers) {
            throw new IllegalArgumentException();
        }
        players[id - 1] = new Player3(rackoSize);
        return players[id - 1];
    }

    /**
     * Generate and return the computer player object to replace the user.
     *
     * @return the computer player object
     */
    public Player getPlayerReplacement() {
        if (replacementID >= maxPlayers) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int id = replacementID++;
        int rand = (new Random()).nextInt(2);
        if (rand == 0) {
            players[id - 1] = new Player2(rackoSize);
        } else {
            players[id - 1] = new Player2v2(rackoSize);
        }
        return players[id - 1];
    }

    /**
     * Main application to start the gateway server with random port.
     *
     * @param args standard argument main function
     */
    public static void main(String[] args) {
        int port = Integer.parseUnsignedInt(args[0]);
        if (port < 25335 || port > 65535) {
            throw new IllegalArgumentException("invalid port : " + port);
        }
        GatewayServer gatewayServer
            = new GatewayServer(new GatewayServerRackoComputerPlayers(), port);
        gatewayServer.start();
        System.out.println("Gateway server for Racko Computer Players started using port " + port);
    }
}