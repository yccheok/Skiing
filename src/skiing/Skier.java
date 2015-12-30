/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiing;

/**
 *
 * @author yccheok
 */
public class Skier {
    private static class Direction {
        public Direction(int dr, int dc) {
            this.dr = dr;
            this.dc = dc;
        }
        
        public final int dr;
        public final int dc;
    }
    
    public Skier(Map map) {
        this.map = map;
    }
    
    public Path visit(Path path, int r, int c) {
        if (r < 0 || c < 0 || r >= map.getRow() || c >= map.getCol()) {
            return path;
        }
        
        final int elevation = map.getElevation(r, c);
        if (false == path.add(r, c, elevation)) {
            return path;
        }
        
        // Search for bestPath
        Direction[] directions = new Direction[] {
            new Direction(-1, 0),   // Up
            new Direction(1, 0),    // Down
            new Direction(0, -1),   // Left
            new Direction(0, 1),    // Right
        };
        
        Path bestPath = null;
        
        for (Direction direction : directions) {
            Path tmp = visit(path, r+direction.dr, c+direction.dc);
            if (bestPath == null) {
                // Create a new instance for best path is important, as we are
                // going to perform undo.
                bestPath = new Path(path);
            } else {
                if (bestPath.isBetterThanMe(tmp)) {
                    bestPath = new Path(tmp);
                }
            }
        }
        
        path.undo();
        
        return bestPath;       
    }
    
    private final Map map;
}
