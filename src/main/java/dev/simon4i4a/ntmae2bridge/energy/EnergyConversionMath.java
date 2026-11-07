package dev.simon4i4a.ntmae2bridge.energy;

/**
 * Pure conversion calculations shared by the block entity and unit tests.
 */
public final class EnergyConversionMath {
    private EnergyConversionMath() {
    }

    /**
     * Returns the effective AE-per-HE rate after losses have been applied.
     * Invalid or non-positive ratios produce zero output.
     */
    public static double effectiveRate(double aePerHe, double energyLoss) {
        if (!Double.isFinite(aePerHe) || aePerHe <= 0.0 || !Double.isFinite(energyLoss)) {
            return 0.0;
        }
        double loss = Math.max(0.0, Math.min(1.0, energyLoss));
        return aePerHe * (1.0 - loss);
    }

    public static double outputForHe(double he, double effectiveRate) {
        if (!Double.isFinite(he) || !Double.isFinite(effectiveRate)
                || he <= 0.0 || effectiveRate <= 0.0) {
            return 0.0;
        }
        return he * effectiveRate;
    }

    /**
     * Calculates how much HE to debit for the AE energy actually accepted.
     */
    public static double heForAcceptedAe(double acceptedAe, double effectiveRate, double availableHe) {
        if (!Double.isFinite(acceptedAe) || !Double.isFinite(effectiveRate)
                || !Double.isFinite(availableHe)
                || acceptedAe <= 0.0 || effectiveRate <= 0.0 || availableHe <= 0.0) {
            return 0.0;
        }
        return Math.min(availableHe, acceptedAe / effectiveRate);
    }
}
