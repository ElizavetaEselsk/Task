package geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Polygon implements Geometry {
    private List<Point> points = null;

    public Polygon(Point... points) {
        if(points.length < 3)
            return;


        setPoints(points);
    }

    public double area()
    {
        double s = 0;
        for (int i = 0; i < points.size() ; i++)
            if (i == 0)
                s += points.get(i).getX() * (points.get(i+1).getY() - points.get(points.size() - 1).getY());
            else if (i == points.size() - 1)
                s += points.get(i).getX() * (points.get(0).getY() - points.get(i-1).getY());
            else
                s += points.get(i).getX() * (points.get(i+1).getY() - points.get(i-1).getY());

        double area = 0.5 * Math.abs(s);
        return area;

    }

    public double perimeter(){
        double p = 0.;

        for (int i = 0; i < points.size()-1; i++) {
            p += new Segment(points.get(i), points.get(i+1)).getLength();
        }

        return p + new Segment(points.get(0), points.get(points.size()-1)).getLength();

    }

    public List<Segment> getSegments(){
        List<Segment> l = new ArrayList<>();

        for (int i = 0; i < this.points.size() - 1; i++) {
            l.add(new Segment(this.points.get(i), this.points.get(i+1)));
        }

        l.add(new Segment(this.points.get(this.points.size()-1), this.points.get(0)));

        return l;
    }


    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(Point... points) {
        if(points.length < 3)
            return;

        this.points = new ArrayList<>();
        for (Point e:points) {
            this.points.add(e);
        }
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "points=" + points +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polygon polygon = (Polygon) o;
        return Objects.equals(points, polygon.points);
    }

}
