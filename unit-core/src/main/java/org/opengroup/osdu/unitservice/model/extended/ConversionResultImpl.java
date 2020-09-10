package org.opengroup.osdu.unitservice.model.extended;

import org.opengroup.osdu.unitservice.interfaces.ConversionResult;
import org.opengroup.osdu.unitservice.interfaces.ScaleOffset;
import org.opengroup.osdu.unitservice.interfaces.Unit;
import org.opengroup.osdu.unitservice.interfaces.ABCD;

/**
 * A class implements {@link ConversionResult}.
 */
public class ConversionResultImpl implements ConversionResult {
    private ABCD abcd;
    private ScaleOffset scaleOffset;
    private Unit fromUnit;
    private Unit toUnit;

    /**
     * Constructor
     *
     * @param abcd     abcd object
     * @param fromUnit from unit object
     * @param toUnit   to unit object
     */
    public ConversionResultImpl(ABCD abcd, Unit fromUnit, Unit toUnit) {
        this.abcd = abcd;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
    }

    /**
     * Constructor
     *
     * @param scaleOffset scale offset object
     * @param fromUnit    from unit object
     * @param toUnit      to unit object
     */
    public ConversionResultImpl(ScaleOffset scaleOffset, Unit fromUnit, Unit toUnit) {
        this.scaleOffset = scaleOffset;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
    }

    /**
     * Gets the conversion result in type {@link ABCD}
     *
     * @return ABCD result
     */
    @Override
    public ABCD getABCD() {
        return abcd;
    }

    /**
     * Gets the conversion result in type {@link ScaleOffset}
     *
     * @return Scale offset result
     */
    @Override
    public ScaleOffset getScaleOffset() {
        return scaleOffset;
    }

    /**
     * Gets the fromUnit {@link Unit}
     *
     * @return from unit
     */
    @Override
    public Unit getFromUnit() {
        return fromUnit;
    }

    /**
     * Gets the toUnit {@link Unit}
     *
     * @return to unit
     */
    @Override
    public Unit getToUnit() {
        return toUnit;
    }
}
