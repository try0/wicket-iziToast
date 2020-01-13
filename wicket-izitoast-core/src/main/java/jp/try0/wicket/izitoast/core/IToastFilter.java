package jp.try0.wicket.izitoast.core;

import org.danekja.java.util.function.serializable.SerializablePredicate;

/**
 * Toast filter.
 *
 * @author Ryo Tsunoda
 *
 */
public interface IToastFilter extends SerializablePredicate<IToast> {

	@Override
	default IToastFilter negate() {
		return (t) -> !test(t);
	}
}
