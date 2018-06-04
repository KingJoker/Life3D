package sample;

public class Node {
    boolean alive;
    boolean nextAlive;

    public Node(){
        alive = false;
        nextAlive = false;
    }

    public Node(boolean alive){
        this.alive = alive;
        nextAlive = false;
    }

    public void setNextState(boolean nextState){
        nextAlive = nextState;
    }

    public void activateNextStep(){
        alive = nextAlive;
    }

    public boolean isAlive(){
        return alive;
    }
}
