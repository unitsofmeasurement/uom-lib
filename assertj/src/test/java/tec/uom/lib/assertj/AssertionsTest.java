package tec.uom.lib.assertj;

import static org.junit.Assert.*;
import static tech.uom.lib.assertj.Assertions.*;

import javax.measure.Unit;
import javax.measure.quantity.Length;

import org.junit.Test;

import tec.units.ri.quantity.QuantityDimension;
import tec.units.ri.unit.BaseUnit;
import tec.units.ri.unit.Units;

public class AssertionsTest {

    @Test
    public void testUnit() {
	Unit<Length> m = new BaseUnit<Length>("m", QuantityDimension.LENGTH);
	assertThat(m).isEqualTo(Units.METRE);
    }

}
