package Battleship;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static int StringNumberToInt(String s) {
        int n = s.length();
        return Integer.parseInt(s.substring(0, n - 1));

    }

    private static int StringLetterToInt(String s) {
        int n = s.length();
        char c = s.charAt(n - 1);
        return c - 64;
    }

    private static List<Point> getShotsFromString(String T) {
        String[] allShotsString = T.split(" ");
        List<Point> shotList = new ArrayList<>(allShotsString.length);
        for (String i : allShotsString) {
            int shotPositionStartY = StringNumberToInt(i);
            int shotPositionStartX = StringLetterToInt(i);
            Point shot = new Point(shotPositionStartX, shotPositionStartY);
            shotList.add(shot);
        }
        return shotList;
    }

    private static List<Ship> getShipsFromString(String S) {
        String[] allShipsString = S.split(",");
        List<Ship> shipList = new ArrayList<>(allShipsString.length);
        for (String i : allShipsString) {
            String[] shipString = i.split(" ");
            String shipPositionStart = shipString[0];
            String shipPositionEnd = shipString[1];

            // first point
            int shipPositionStartY = StringNumberToInt(shipPositionStart);
            int shipPositionStartX = StringLetterToInt(shipPositionStart);
            Point topLeft = new Point(shipPositionStartX, shipPositionStartY);
            // second point
            shipPositionStartY = StringNumberToInt(shipPositionEnd);
            shipPositionStartX = StringLetterToInt(shipPositionEnd);
            Point bottomRight = new Point(shipPositionStartX, shipPositionStartY);

            Ship ship = new Ship(topLeft, bottomRight);
            shipList.add(ship);
        }
        return shipList;
    }

    public static void main(String[] args) {

        int N = 10;
        String S = "2A 3E,2G 6G,2I 2I,7I 8J,6C 8C";
        String T = "2E 3D 2G 3G 4G 5G 6G 2I 5E 8H 7I 7J 8I 6C 7C 8C 9C";
        System.out.println(solution(N, S, T));

    }

    public static String solution(int N, String S, String T) {
        List<Ship> allShips = getShipsFromString(S);
        List<Point> allHits = getShotsFromString(T);
        for (Ship ship : allShips) {
            for (Point hit : allHits) {

                ship.isTheShipHit(hit);
            }
            Ship.addHitsToTotal(ship.getHits());
            Ship.addSunkenToTotal(ship.isTheShipSunken());
        }
        return "Sunken: " + Ship.getTotalSunken() + "| Hits: " + Ship.getTotalHits();
    }

}
