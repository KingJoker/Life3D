package sample;

public class Location {
    double x;
    double y;

    public Location(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return (x+" "+y).hashCode();
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
}
