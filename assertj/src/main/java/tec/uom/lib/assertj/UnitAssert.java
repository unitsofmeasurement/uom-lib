package tec.uom.lib.assertj;

import java.util.Objects;

import javax.measure.Unit;

import org.assertj.core.api.AbstractAssert;

//javadoc omitted for brevity
//1 - inherits from AbstractAssert !
public class UnitAssert extends AbstractAssert<UnitAssert, Unit> {

    // 2 - Write a constructor to build your assertion class with the object you
    // want make assertions on.
    public UnitAssert(Unit actual) {
	super(actual, UnitAssert.class);
    }

    // 3 - A fluent entry point to your specific assertion class, use it with
    // static import.
    public static UnitAssert assertThat(Unit actual) {
	return new UnitAssert(actual);
    }

    // 4 - a specific assertion !
    public UnitAssert hasName(String name) {
	// check that actual Unit we want to make assertions on is not null.
	isNotNull();

	// check condition
	if (!Objects.equals(actual.getName(), name)) {
	    failWithMessage("Expected unit's name to be <%s> but was <%s>", name, actual.getName());
	}

	// return the current assertion for method chaining
	return this;
    }

    // 4 - another specific assertion !
    public UnitAssert hasSymbol(String symbol) {
	// check that actual Unit we want to make assertions on is not null.
	isNotNull();

	// check condition
	if (!Objects.equals(actual.getSymbol(), symbol)) {
	    failWithMessage("Expected unit's symbol to be <%s> but was <%s>", symbol, actual.getSymbol());
	}

	// return the current assertion for method chaining
	return this;
    }
}