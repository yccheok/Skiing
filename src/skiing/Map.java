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
public class Map {
    public Map(int row, int col, List<Integer> elevations) {
        if (elevations.size() != (row * col)) {
            throw new java.lang.IllegalArgumentException("elevations.size() != (row * col)");
        }
        this.row = row;
        this.col = col;
        this.elevations.addAll(elevations);
    }
    
    public int getElevation(int r, int c) {
        int index = r*col + c;
        return elevations.get(index);
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    private final int row;
    private final int col;
    private final List<Integer> elevations = new ArrayList<>();
}
