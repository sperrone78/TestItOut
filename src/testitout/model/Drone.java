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
public class Drone implements Buildable {
    private String name;
    private String type;
    
    public Drone(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean build () {
        return false;
    }
    
    public String toString(){
        return getName() + " : " + getType();
    }
}
