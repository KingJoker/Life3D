package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World {
    public static final double STEP_SIZE = 1;
    public static final double SIZE = 360;
    Map<Location,Node> world;

    public World(){
        world = new ConcurrentHashMap<Location, Node>();
        for(double x = 0; x < 360; x+= STEP_SIZE){
            for(double y = 0; y < 360; y+= STEP_SIZE){
                world.put(new Location(x,y),new Node());
            }
        }
    }

    public void step(){
        world.forEach(((location, node) -> stepNode(location)));
        world.forEach(((location, node) -> node.activateNextStep()));
    }

    public void stepNode(Location loc){
        Node node = world.get(loc);
        int aliveNeighbors = aliveCount(getNeighbors(loc));
        if(node.isAlive()){
            if(aliveNeighbors < 2 || aliveNeighbors > 3){
                node.setNextState(false);
            }
            else{
                node.setNextState(true);
            }
        }
        else{
            if(aliveNeighbors == 3){
                node.setNextState(true);
            }
            else{
                node.setNextState(false);
            }
        }
    }

    public int aliveCount(List<Node> nodes){
        return (int) nodes.parallelStream().filter(node -> node.isAlive()).count();
    }

    public List<Node> getNeighbors(Location loc){
        List<Node> neighbors = new ArrayList<>();

        for(double x = 0 - STEP_SIZE; x <= STEP_SIZE; x+=STEP_SIZE){
            for(double y = 0 - STEP_SIZE; y <= STEP_SIZE; y+=STEP_SIZE){
                if(x == 0 && y == 0)
                    continue;
                double neighborX = loc.getX() + x;
                double neighborY = loc.getY() + y;
                if(neighborX < 0){
                    neighborX += SIZE;
                }
                if(neighborY < 0){
                    neighborY += SIZE;
                }
                if(neighborX > SIZE){
                    neighborX -= SIZE;
                }

                if(neighborY > SIZE){
                    neighborY -= SIZE;
                }
                Location neighborLoc = new Location(neighborX,neighborY);
                neighbors.add(world.get(neighborLoc));
            }
        }
        return neighbors;
    }

}
