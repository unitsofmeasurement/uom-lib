package tech.uom.lib.assertj.assertions;

import javax.measure.quantity.Temperature;
import org.junit.jupiter.api.Test;
import tech.units.indriya.ComparableQuantity;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;
import static tech.uom.lib.assertj.assertions.QuantityAssert.*;

class QuantityAssertTest {

  @Test
  void testHasUnit() {
    ComparableQuantity<Temperature> temperatureKelvin = Quantities.getQuantity(309.65, Units.KELVIN);
    ComparableQuantity<Temperature> temperatureCelsius = temperatureKelvin.to(Units.CELSIUS);
    assertThat(temperatureKelvin).hasUnit(Units.KELVIN);
    assertThat(temperatureCelsius).hasUnit(Units.CELSIUS);
  }

  @Test
  void testHasValue() {
    ComparableQuantity<Temperature> temperatureKelvin = Quantities.getQuantity(309.65, Units.KELVIN);
    ComparableQuantity<Temperature> temperatureCelsius = temperatureKelvin.to(Units.CELSIUS);
    assertThat(temperatureKelvin).hasValue(309.65);
    assertThat(temperatureCelsius).hasValue(36.5);
  }

}
