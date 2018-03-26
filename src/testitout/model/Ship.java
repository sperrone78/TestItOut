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
public class Ship implements Buildable {
    private String name;
    private String type;
    
    public Ship(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
    public boolean build () {
        return false;
    }
}
