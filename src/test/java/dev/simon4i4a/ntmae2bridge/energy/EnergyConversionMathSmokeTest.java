package dev.simon4i4a.ntmae2bridge.energy;

/**
 * Dependency-free smoke tests for the conversion math.
 * Run with: javac ... && java ...EnergyConversionMathSmokeTest
 */
public final class EnergyConversionMathSmokeTest {
    private static final double EPSILON = 1.0E-8;

    private EnergyConversionMathSmokeTest() {
    }

    public static void main(String[] args) {
        close(0.8, EnergyConversionMath.effectiveRate(1.0, 0.2), "loss rate");
        close(160.0, EnergyConversionMath.outputForHe(100.0, 1.6), "AE output");
        close(25.0, EnergyConversionMath.heForAcceptedAe(50.0, 2.0, 100.0), "partial acceptance");
        close(10.0, EnergyConversionMath.heForAcceptedAe(500.0, 2.0, 10.0), "buffer cap");
        close(0.0, EnergyConversionMath.effectiveRate(Double.NaN, 0.0), "NaN input");
        close(0.0, EnergyConversionMath.outputForHe(10.0, -1.0), "negative rate");
        System.out.println("EnergyConversionMath smoke tests passed.");
    }

    private static void close(double expected, double actual, String name) {
        if (Math.abs(expected - actual) > EPSILON) {
            throw new AssertionError(name + ": expected " + expected + ", got " + actual);
        }
    }
}
