/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testitout.model;

import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author sperr
 */
public class MainCharacter {
    private String name;
    private ArrayList<Ship> shipList;
    private ArrayList<Drone> droneList;
    private ObservableList<String> droneInfoList;
    private Factory factory;
    private int actionsTaken;
    private int maxDailyActions;

    
    public MainCharacter () {
        shipList = new ArrayList<Ship>();
        droneList = new ArrayList<Drone>();
        this.name = "The Bob";
        factory = new Factory(this);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the shipList
     */
    public ArrayList<Ship> getShipList() {
        return shipList;
    }

    /**
     * @return the droneList
     */
    public ArrayList<Drone> getDroneList() {
        return droneList;
    }

    /**
     * @return the factory
     */
    public Factory getFactory() {
        return factory;
    }
    
    public void addToShipList (Ship ship) {
        this.shipList.add(ship);
    }
    
    public void addToDroneList(Drone drone) {
        this.droneList.add(drone);
    }

}
