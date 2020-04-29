package jp.try0.wicket.izitoast.core.config;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.markup.html.form.FormComponent;

import jp.try0.wicket.izitoast.core.behavior.ToastTargetContainerCreateBehavior;

/**
 * Toast target cotainer appender.<br>
 * Appends toast container to FormComponent.
 *
 * @see DefaultToastTargetSetter
 * @author Ryo Tsunoda
 *
 */
public class ToastTargetContainerAppender implements IComponentInstantiationListener, Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onInstantiation(Component component) {

		if (!needAppend(component)) {
			return;
		}

		component.add(newContainerCreateBehavior());
	}

	/**
	 * Whether append target container or not.<br>
	 *
	 * @param component the repeater component
	 * @return if component type is {@link FormComponent} returns true otherwise false.
	 */
	public boolean needAppend(Component component) {
		return component instanceof FormComponent;
	}

	/**
	 * Creates {@link ToastTargetContainerCreateBehavior}.
	 *
	 * @return behavior
	 */
	protected ToastTargetContainerCreateBehavior newContainerCreateBehavior() {
		return new ToastTargetContainerCreateBehavior();
	}

}
