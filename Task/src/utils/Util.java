package utils;

import geometry.*;

import java.util.List;

public class Util {
    private static double E = 0.001;
    public static Point intersection(Segment s1, Segment s2){
        EquationLine l1 = new EquationLine(s1);
        EquationLine l2 = new EquationLine(s2);

        System.out.println("s1 = ");
        System.out.println(l1);

        System.out.println("s2 = ");
        System.out.println(l2);
        double y = (l2.getA()*l1.getC()/ l1.getA() - l2.getC())/(l2.getB()-l1.getB()* l2.getA()/ l1.getA());
        double x = -l1.getC()/ l1.getA() - l1.getB()*y/ l1.getA();
        Point p = new Point(x, y);
        if(isLiesOnLine(p, s1) && isLiesOnLine(p, s2))
            return p;
        return null;
    }

    public static boolean isLiesOnLine(Point p, Segment s){

        return isLiesOnLine(p, new EquationLine(s)) &&

                0 <= (p.getX() - s.getP1().getX())*(s.getP2().getX() - p.getX()) &&
                (p.getX() - s.getP1().getX())*(s.getP2().getX() - p.getX()) <= (s.getP1().getX() - s.getP2().getX())*(s.getP1().getX() - s.getP2().getX()) &&

                0 <= (p.getY() - s.getP1().getY())*(s.getP2().getY() - p.getY()) &&
                (p.getY() - s.getP1().getY())*(s.getP2().getY() - p.getY())  <= (s.getP1().getY()-s.getP2().getY())*(s.getP1().getY()-s.getP2().getY());

    }
    public static boolean isLiesOnLine(Point p, EquationLine e){
        return p.getX() * e.getA() + p.getY() * e.getB() + e.getC() <= E;
    }

    public static boolean isInsideFigure(Point p, Polygon polygon){
        List<Point> points = polygon.getPoints();
        boolean result = false;
        int j = polygon.getPoints().size() - 1;
        for (int i = 0; i < polygon.getPoints().size(); i++) {
            if ((points.get(i).getY() > p.getY()) != (points.get(j).getY() > p.getY()) &&
                    (p.getX() < points.get(i).getX() + (points.get(j).getX() - points.get(i).getX()) *
                            (p.getY() - points.get(i).getY()) / (points.get(j).getY() - points.get(i).getY()))) {
                result = !result;
            }
            j = i;
        }
        return result;
    }

    public static boolean isInsideFigure(Segment g, Polygon polygon){
        for (Point p: g.getPoints()) {
            if(!isInsideFigure(p, polygon))
                return false;
        }
        return true;
    }

    public static boolean isInsideFigure(Polygon inside, Polygon outside){//ѕроверка лежит ли лева€ фигура(inside) внутри правой(outside)
        for (Point p: inside.getPoints()) {
            if(!isInsideFigure(p, outside))
                return false;
        }
        return true;
    }



    public static boolean isItPoint(Geometry g){
        return Point.class.equals(g.getClass());
    }
    public static boolean isItPolygon(Geometry g){
        return Polygon.class.equals(g.getClass());
    }
    public static boolean isItSegment(Geometry g){
        return Segment.class.equals(g.getClass());
    }
}
