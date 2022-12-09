package geometry;

public class EquationLine{
    private double a;
    private double b;
    private double c;

    public EquationLine(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public EquationLine(Segment s) {
        fromSegmentToEquationLine(s);
    }
    public EquationLine(Point p1, Point p2) {
        fromPointsToEquationLine(p1, p2);
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    private void fromPointsToEquationLine(Point p1, Point p2){
        this.a = p2.getY() - p1.getY();
        this.b = -(p2.getX() - p1.getX());
        this.c = -p2.getX()*a +(-p1.getY()*b);
    }

    private void fromSegmentToEquationLine(Segment s){
        fromPointsToEquationLine(s.getP1(), s.getP2());
    }

    @Override
    public String toString() {
        return "(" + a + ")*x + ("+b+")*y + ("+c+") = 0"  ;
    }
}
