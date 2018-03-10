package tech.uom.lib.assertj;

import tech.uom.lib.assertj.assertions.DimensionAssert;
import tech.uom.lib.assertj.assertions.QuantityAssert;
import tech.uom.lib.assertj.assertions.UnitAssert;

/**
 * Entry point for BDD assertions of different data types. Each method in this class is a static factory for the
 * type-specific assertion objects.
 */
public class BddAssertions {

  /**
   * Creates a new instance of <code>{@link DimensionAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  public static DimensionAssert then(javax.measure.Dimension actual) {
    return new DimensionAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link QuantityAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  public static QuantityAssert then(javax.measure.Quantity actual) {
    return new QuantityAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link UnitAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  public static UnitAssert then(javax.measure.Unit actual) {
    return new UnitAssert(actual);
  }

  /**
   * Creates a new <code>{@link BddAssertions}</code>.
   */
  protected BddAssertions() {
    // empty
  }
}
