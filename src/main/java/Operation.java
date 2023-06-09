public class Operation {
    private final double x;
    private final String operation;
    private final double y;
    private final double result;

    public Operation(double x, String operation, double y) {
        this.x = x;
        this.operation = operation;
        this.y = y;
        result = calculateResult(operation);
    }

    private double calculateResult(String operation) {
        return switch (operation) {
            case "+" -> x + y;
            case "-" -> x - y;
            case "*" -> x * y;
            case "/" -> x / y;
            default -> throw new IllegalStateException("Nieznana wartość " + operation);
        };
    }

    @Override
    public String toString() {
        return x + " " + operation + " " + y + " = " + result;
    }
}