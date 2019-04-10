package jp.try0.wicket.iziToast.core.config;

import java.util.function.Supplier;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.application.IComponentInstantiationListener;

import jp.try0.wicket.iziToast.core.behavior.IziToastBehavior;

/**
 * Appends a {@link IziToastBehavior} to new {@link Page}.
 *
 * @author Ryo Tsunoda
 *
 */
public class IziToastBehaviorAutoAppender implements IComponentInstantiationListener {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onInstantiation(Component component) {
		if (component instanceof Page) {
			Supplier<IziToastBehavior> factory = IziToastSetting.get().getIziToastBehaviorFactory();
			IziToastBehavior behavior = factory.get();
			component.add(behavior);
		}
	}

}
