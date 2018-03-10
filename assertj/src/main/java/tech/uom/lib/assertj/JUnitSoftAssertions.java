package tech.uom.lib.assertj;

import org.assertj.core.internal.cglib.proxy.Enhancer;

import org.assertj.core.api.ErrorCollector;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;

import tech.uom.lib.assertj.assertions.DimensionAssert;
import tech.uom.lib.assertj.assertions.QuantityAssert;
import tech.uom.lib.assertj.assertions.UnitAssert;

/**
 * Entry point for assertions of different data types. Each method in this class is a static factory for the
 * type-specific assertion objects.
 */
public class JUnitSoftAssertions implements TestRule {

  /** Collects error messages of all AssertionErrors thrown by the proxied method. */
  protected final ErrorCollector collector = new ErrorCollector();

  /** Creates a new </code>{@link JUnitSoftAssertions}</code>. */
  public JUnitSoftAssertions() {
    super();
  }

  /**
   * TestRule implementation that verifies that no proxied assertion methods have failed.
   */
  public Statement apply(final Statement base, Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        base.evaluate();
        MultipleFailureException.assertEmpty(collector.errors());
      }
    };
  }
  
  @SuppressWarnings("unchecked")
  protected <T, V> V proxy(Class<V> assertClass, Class<T> actualClass, T actual) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(assertClass);
    enhancer.setCallback(collector);
    return (V) enhancer.create(new Class[] { actualClass }, new Object[] { actual });
  }

  /**
   * Creates a new "soft" instance of <code>{@link DimensionAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  public DimensionAssert assertThat(javax.measure.Dimension actual) {
    return proxy(DimensionAssert.class, javax.measure.Dimension.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link QuantityAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  public QuantityAssert assertThat(javax.measure.Quantity actual) {
    return proxy(QuantityAssert.class, javax.measure.Quantity.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link UnitAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  public UnitAssert assertThat(javax.measure.Unit actual) {
    return proxy(UnitAssert.class, javax.measure.Unit.class, actual);
  }

}
