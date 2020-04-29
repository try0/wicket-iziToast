package jp.try0.wicket.izitoast.core.config;

import org.apache.wicket.Component;
import org.danekja.java.util.function.serializable.SerializableBiConsumer;

import jp.try0.wicket.izitoast.core.IToast;

/**
 * Setter that set target container to toast.
 *
 * @author Ryo Tsunoda
 *
 */
public interface IToastTargetLinker extends SerializableBiConsumer<IToast, Component> {

	/**
	 * Sets target container to toast.
	 *
	 * @param toast
	 * @param component
	 */
	void setTarget(IToast toast, Component component);

	@Override
	default void accept(IToast t, Component u) {
		setTarget(t, u);
	}

}
