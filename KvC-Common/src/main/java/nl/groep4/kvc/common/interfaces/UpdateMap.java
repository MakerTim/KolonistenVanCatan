package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;
import java.util.Collection;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.SelectState;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;

/**
 * Opens panes and performs other actions
 * 
 * @author Tim
 * @version 1.0
 */
public interface UpdateMap extends Updatable<Map>, UpdateDice, UpdateStock, UpdateCosts, UpdateTrade, UpdateRound,
	UpdateScore, UpdatePlayerOrder {

    /**
     * Closes overlay
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void closeOverlay() throws RemoteException;

    /**
     * Opens build pane
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void openBuildPane() throws RemoteException;

    /**
     * Opens option pane
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void openOptionPane() throws RemoteException;

    /**
     * Opens rule pane
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void openRulesPane() throws RemoteException;

    /**
     * Opens dice pane
     * 
     * @param isOwnTurn
     *            true when it's your turn and false when it's not your turn
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void openDicePane(boolean isOwnTurn) throws RemoteException;

    /**
     * Opens buy pane
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void openBuyPane() throws RemoteException;

    /**
     * Opens trade pane
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void openTradePane() throws RemoteException;

    /**
     * Opens monopoly pane
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void openMonopolyPane() throws RemoteException;

    /**
     * Opens invention pane
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void openInventionPane() throws RemoteException;

    /**
     * Opens credits pane
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void openCreditsPane() throws RemoteException;

    /**
     * Opens pause pane
     * 
     * @param isOwner
     *            true when you set the game on pause and false when you do not
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void openPausePane(boolean isOwner) throws RemoteException;

    /**
     * Opens save pane
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void openSavePane() throws RemoteException;

    /**
     * Highlights streets
     * 
     * @param streets
     *            streets that will be highlighted
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void highlightStreets(Collection<Street> streets) throws RemoteException;

    /**
     * Highlights buildings
     * 
     * @param buildings
     *            buildings that will be highlighted
     * @param type
     *            kind of building
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void highlightBuildings(Collection<Building> buildings, BuildingType type) throws RemoteException;

    /**
     * Blocks all action
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void blockActions() throws RemoteException;

    /**
     * Unblocks actions
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void unblockActions() throws RemoteException;

    /**
     * Sets selectable
     * 
     * @param selectables
     *            selects building, street
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void setSelectable(SelectState selectables) throws RemoteException;
}
