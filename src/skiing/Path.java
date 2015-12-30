/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiing;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yccheok
 */
public class Path {
    private static class Step {
        public Step(int r, int c, int elevation) {
            this.r = r;
            this.c = c;
            this.elevation = elevation;
        }
        
        public final int r;
        public final int c;
        public final int elevation;
    }
    
    public Path() {        
    }
    
    public Path(Path path) {
        steps.addAll(path.steps);
    }
    
    public boolean add(int r, int c, int elevation ) {
        if (steps.isEmpty()) {
            return steps.add(new Step(r, c, elevation));
        }
        
        // but only if the elevation of the area you are going into is less than 
        // the one you are in
        if (steps.get(steps.size()-1).elevation > elevation) {
            return steps.add(new Step(r, c, elevation));
        }
        
        return false;
    }
    
    public void undo() {
        steps.remove(steps.size() - 1);
    }
    
    public int size() {
        return steps.size();
    }
    
    public int steep() {
        if (steps.size() < 2) {
            return 0;
        }
        
        return steps.get(0).elevation - steps.get(steps.size()-1).elevation;
    }
    
    public boolean isBetterThanMe(Path path) {
        // And if there are several paths down of the same length, you want to 
        // take the one with the steepest vertical drop
        final int size = path.size();
        final int meSize = this.size();
        if (size > meSize) {
            return true;
        } else if (size == meSize) {
            if (path.steep() > this.steep()) {
                return true;
            }
        }    
        return false;
    }
    
    public void print() {
        for (Step step : steps) {
            System.out.print("[" + step.r + "," + step.c + "]" + step.elevation + " ->");
        }
        System.out.println("");
    }
    private final List<Step> steps = new ArrayList<>();
}
