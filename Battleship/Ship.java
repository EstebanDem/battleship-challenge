package Battleship;

public class Ship {
    private Point topLeft;
    private Point bottomRight;
    private int hits = 0;
    private boolean sunken = false;
    private static int totalHits = 0;
    private static int totalSunken = 0;

    public static int getTotalHits() {
        return totalHits;
    }

    public int getHits() {
        return hits;
    }

    public static int getTotalSunken() {
        return totalSunken;
    }

    public Ship(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public int getSize() {
        return (bottomRight.getX() - topLeft.getX() + 1) * (bottomRight.getY() - topLeft.getY() + 1);
    }

    public boolean isInsideLeft(Point p) {
        return (p.getX() >= topLeft.getX() && p.getY() >= topLeft.getY());
    }

    public boolean isInsideRight(Point p) {
        return (p.getX() <= bottomRight.getX() && p.getY() <= bottomRight.getY());
    }

    public boolean isPointInside(Point p) {
        return (isInsideLeft(p) && isInsideRight(p));
    }

    public void isTheShipHit(Point p) {
        if (isPointInside(p)) {
            this.hits++;
        }
    }

    public boolean isTheShipSunken() {

        if (hits == getSize()) {
            sunken = true;
        } else {
            sunken = false;
        }
        return sunken;
    }

    public static void addHitsToTotal(int h) {
        Ship.totalHits += h;
    }

    public static void addSunkenToTotal(boolean b) {
        if (b) {
            totalSunken++;
        }
    }

    @Override
    public String toString() {
        return "|BARCO| <topLeft>: " + topLeft + " <bottomRight>: " + bottomRight + " MAX HITS " + getSize()
                + " | HITS RECIBIDOS: " + hits;
    }
}
