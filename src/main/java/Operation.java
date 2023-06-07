public class Operation {
    double x;
    String operation;
    double y;

    public Operation(double x, String operation, double y) {
        this.x = x;
        this.operation = operation;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public String getOperation() {
        return operation;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + " " + operation + " " + y;
    }
}