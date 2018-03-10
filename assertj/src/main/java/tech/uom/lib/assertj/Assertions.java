package tech.uom.lib.assertj;

import tech.uom.lib.assertj.assertions.DimensionAssert;
import tech.uom.lib.assertj.assertions.QuantityAssert;
import tech.uom.lib.assertj.assertions.UnitAssert;

/**
 * Entry point for assertions of different data types. Each method in this class is a static factory for the
 * type-specific assertion objects.
 */
public class Assertions {

  /**
   * Creates a new instance of <code>{@link javax.measure.DimensionAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  public static DimensionAssert assertThat(javax.measure.Dimension actual) {
    return new DimensionAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link QuantityAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  public static QuantityAssert assertThat(javax.measure.Quantity actual) {
    return new QuantityAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link UnitAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  public static UnitAssert assertThat(javax.measure.Unit actual) {
    return new UnitAssert(actual);
  }

  /**
   * Creates a new <code>{@link Assertions}</code>.
   */
  protected Assertions() {
    // empty
  }
}
