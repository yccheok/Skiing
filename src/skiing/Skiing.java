/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiing;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        if (map == null) {
            System.out.println("null map");
            return;
        }
        
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
        if (bestPath == null) {
            System.out.println("null");
        } else {
            System.out.println(bestPath.size()+ "" + bestPath.steep()+ "@redmart.com");
        }
        //bestPath.print();
    }
    
    public static Map getMap() {
        /*
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
        */
        URL url;
        try {
            url = new URL("http://s3-ap-southeast-1.amazonaws.com/geeks.redmart.com/coding-problems/map.txt");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Skiing.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        Scanner s;
        try {
            s = new Scanner(url.openStream());
        } catch (IOException ex) {
            Logger.getLogger(Skiing.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        int row = s.nextInt();
        int col = s.nextInt();
        
        List<Integer> elevations = new ArrayList<>();
        while (s.hasNext()) {
            elevations.add(s.nextInt());
        }
        
        return new Map(row, col, elevations);
    }
    
}
