/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author yccheok
 */
public class Skiing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Map map = getMap();
        
        Skier skier = new Skier(map);
     
        Path bestPath = null;
        
        for (int r=0; r<map.getRow(); r++) {
            for (int c=0; c<map.getCol(); c++) {
                Path path = skier.visit(new Path(), r, c);
                if (bestPath == null) {
                    bestPath = path;
                } else {
                    if (bestPath.isBetterThanMe(path)) {
                        bestPath = path;
                    }
                }
            }            
        }
        
        bestPath.print();
    }
    
    public static Map getMap() {
        int row = 4;
        int col = 4;
        int[] _elevations = new int[] {
            4,8,7,3,
            2,5,9,3,
            6,3,2,5,
            4,4,1,6,
        };

        List<Integer> elevations = new ArrayList<>();
        for (int e : _elevations) {
            elevations.add(e);
        }
        
        return new Map(row, col, elevations);
    }
    
}
