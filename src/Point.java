public class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void takerandomStep(){
        int rand = (int)(Math.random()*4);
        if(rand == 0) x+=3;
        if(rand == 1) x-=3;
        if(rand == 2) y+=3;
        if(rand == 3) y-=3;
    }
}
