/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testitout.helpers;

//import org.lwjgl.system.*;
/**
 *
 * @author sperr
 */
public class GameClock {
    private static boolean paused = false;
    public static long lastFrame, totalTime;
    public static float d=0, multiplier = 1;
    private static int MAX_MULTIPLIER = 3;
    
    public static long getTime() {
        return System.nanoTime()/1000000;
    }
    
    public static float getDelta() {
        long currentTime = getTime();
        int delta = (int)(currentTime - lastFrame);
        lastFrame = getTime();
        return delta * 0.01f;
    }
    
    public static float Delta() {
        if (paused)
            return 0;
        else 
            return d * multiplier;
    }
    
    public static float TotalTime() {
        return totalTime;
    }
    
    public static float Multiplier () {
        return multiplier;
    }
    
    public static void update() {
        d = getDelta();
        totalTime += d;
    }
    
    public static void ChangeMultiplier(int change) {
        if (multiplier + change < -1 && multiplier + change > MAX_MULTIPLIER) {
        
        } else {
            multiplier += change;
        }
    }
    
    public static void Pause() {
        if (paused)
            paused = false;
        else 
            paused = true;
    }
}
