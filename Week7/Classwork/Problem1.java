public abstract class Shape {
    private static int counter = 0;
    private final String shapeId;

    public Shape() {
        counter++;
        this.shapeId = "SHAPE-" + counter;
    }

    public abstract double calculateArea();

    public void scale(double factor) {
        scale(factor, factor);
    }

    public abstract void scale(double xFactor, double yFactor);

    public String getShapeId() {
        return shapeId;
    }

    public static void printArea(Shape s) {
        if (s != null) {
            System.out.println(s.calculateArea());
        }
    }
}

class CircleShape extends Shape {
    private double radius;

    public CircleShape(double radius) {
        super();
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        this.radius *= xFactor;
    }
}

class SquareShape extends Shape {
    private double side;

    public SquareShape(double side) {
        super();
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        this.side *= xFactor;
    }
}
