/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testitout.model;

/**
 *
 * @author sperr
 */
public class Factory {
    private int availBays,totalBays;
    private int maxBuildLevel;
    private int inStockSteel, inStockAlum, inStockFuel;
    private int shipCount;
    private int droneCount;
    private MainCharacter mc;

    
    public Factory(MainCharacter mc) {
        this.totalBays = 2; //Only 2 bays to start the game
        this.availBays = 2;
        this.maxBuildLevel = 1; //start at level 1
        this.inStockSteel = 100;
        this.inStockAlum = 100;
        this.inStockFuel = 100;
        this.mc = mc;
    }
    
    public boolean produceShip (String name, String type) {
        if ( (getShipCount() > 0) && (getAvailBays() > 0) ) {
            setShipCount(getShipCount() - 1);
            setAvailBays(getAvailBays() - 1);
            Ship ship = new Ship(name, type);
            mc.addToShipList(ship);
            return true;
        }
        return false;
    }
    
    public boolean produceDrone (String name, String type) {
        if (getAvailBays() > 0) {
            if ((this.inStockAlum >= 25) && (this.inStockFuel >= 25) && (this.inStockSteel >= 25) ) {   
                setAvailBays(getAvailBays() - 1);
                this.inStockAlum -= 25;
                this.inStockSteel -= 25;
                this.inStockFuel -= 25;
                Drone drone = new Drone(name,type);
                mc.addToDroneList(drone);
                return true;
            } 
        }
        return false;
    }

    public int getMaxBuildLevel() {
        return maxBuildLevel;
    }

    public void setMaxBuildLevel(int maxBuildLevel) {
        this.maxBuildLevel = maxBuildLevel;
    }

    public int getShipCount() {
        return shipCount;
    }

    public void setShipCount(int shipCount) {
        this.shipCount = shipCount;
    }

    public int getDroneCount() {
        return droneCount;
    }

    public void setDroneCount(int droneCount) {
        this.droneCount = droneCount;
    }

    public int getAvailBays() {
        return availBays;
    }

    public void setAvailBays(int availBays) {
        this.availBays = availBays;
    }

    public int getTotalBays() {
        return totalBays;
    }

    public void setTotalBays(int totalBays) {
        this.totalBays = totalBays;
    }

    public int getInStockSteel() {
        return inStockSteel;
    }

    public void setInStockSteel(int inStockSteel) {
        this.inStockSteel = inStockSteel;
    }

    public int getInStockAlum() {
        return inStockAlum;
    }

    public void setInStockAlum(int inStockAlum) {
        this.inStockAlum = inStockAlum;
    }

    public int getInStockFuel() {
        return inStockFuel;
    }

    public void setInStockFuel(int inStockFuel) {
        this.inStockFuel = inStockFuel;
    }
}
