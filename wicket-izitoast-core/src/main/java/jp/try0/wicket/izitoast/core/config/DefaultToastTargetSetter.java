package jp.try0.wicket.izitoast.core.config;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.form.FormComponent;

import jp.try0.wicket.izitoast.core.IToast;
import jp.try0.wicket.izitoast.core.IToastFilter;

/**
 * The toast target setter.
 *
 * @see ToastTargetContainerAppender
 * @author Ryo Tsunoda
 *
 */
public class DefaultToastTargetSetter implements IToastTargetSetter {

	private static final IToastTargetSetter INSTANCE = new DefaultToastTargetSetter();

	private static final IToastTargetSetter NULL_INSTANCE = new DefaultToastTargetSetter() {
		@Override
		public void setTarget(IToast toast, Component component) {
			// no-op
		}
	};

	public static final String TARGET_COMPONENT_ID_PREFIX = "wicket-iziToast-target-";

	public static final IToastFilter NO_TARGET_FILTER = new IToastFilter() {

		@Override
		public boolean test(IToast t) {
			return t.getToastOption().getTarget() == null
					|| t.getToastOption().getTarget().isEmpty();
		}
	};

	public static IToastTargetSetter getInstance() {
		return INSTANCE;
	}

	public static IToastTargetSetter getNullInstance() {
		return NULL_INSTANCE;
	}

	@Override
	public void setTarget(IToast toast, Component component) {

		if (component == null || component instanceof Page) {
			return;
		}

		if (!component.isVisibilityAllowed()) {
			return;
		}

		if (!component.getOutputMarkupId()) {
			return;
		}

		if (component instanceof FormComponent) {
			toast.getToastOption().setTarget("#" + TARGET_COMPONENT_ID_PREFIX + component.getMarkupId());
			toast.getToastOption().setTimeout(false);
			toast.getToastOption().setDisplayMode(1);
			return;
		}

		toast.getToastOption().setTarget("#" + component.getMarkupId());
	}
}
