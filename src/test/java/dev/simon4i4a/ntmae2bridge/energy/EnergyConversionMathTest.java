package dev.simon4i4a.ntmae2bridge.energy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnergyConversionMathTest {
    private static final double EPSILON = 1.0E-9;

    @Test
    void appliesConfiguredLossToConversionRate() {
        assertEquals(0.8, EnergyConversionMath.effectiveRate(1.0, 0.2), EPSILON);
        assertEquals(0.0, EnergyConversionMath.effectiveRate(1.0, 1.0), EPSILON);
    }

    @Test
    void convertsHeUsingEffectiveRate() {
        assertEquals(160.0, EnergyConversionMath.outputForHe(100.0, 1.6), EPSILON);
    }

    @Test
    void debitsOnlyHeCorrespondingToAcceptedAe() {
        assertEquals(25.0, EnergyConversionMath.heForAcceptedAe(50.0, 2.0, 100.0), EPSILON);
    }

    @Test
    void neverDebitsMoreThanAvailableBuffer() {
        assertEquals(10.0, EnergyConversionMath.heForAcceptedAe(500.0, 2.0, 10.0), EPSILON);
    }

    @Test
    void invalidInputsProduceNoEnergy() {
        assertEquals(0.0, EnergyConversionMath.effectiveRate(Double.NaN, 0.0), EPSILON);
        assertEquals(0.0, EnergyConversionMath.outputForHe(10.0, -1.0), EPSILON);
        assertEquals(0.0, EnergyConversionMath.heForAcceptedAe(10.0, 0.0, 10.0), EPSILON);
    }
}
