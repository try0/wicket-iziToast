package jp.try0.wicket.izitoast.core.config;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.application.IComponentInstantiationListener;

import jp.try0.wicket.izitoast.core.IToast;
import jp.try0.wicket.izitoast.core.IToastFilter;

/**
 * The toast target setter.
 *
 * @see ToastTargetContainerAppender
 * @author Ryo Tsunoda
 *
 */
public class DefaultToastTargetLinker implements IToastTargetLinker {

	private static final IToastTargetLinker INSTANCE = new DefaultToastTargetLinker();

	private static final IToastTargetLinker NULL_INSTANCE = new DefaultToastTargetLinker() {
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

	public static IToastTargetLinker getInstance() {
		return INSTANCE;
	}

	public static IToastTargetLinker getNullInstance() {
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

		if (Application.exists()) {
			Application app = Application.get();

			for (IComponentInstantiationListener listener : app.getComponentInstantiationListeners()) {
				if (listener instanceof ToastTargetContainerAppender) {
					ToastTargetContainerAppender appender = (ToastTargetContainerAppender) listener;
					if (appender.needAppend(component)) {
						toast.getToastOption().setTarget("#" + TARGET_COMPONENT_ID_PREFIX + component.getMarkupId());
						toast.getToastOption().setTimeout(false);
						toast.getToastOption().setDisplayMode(1);
						return;
					}
				}
			}
		}

		toast.getToastOption().setTarget("#" + component.getMarkupId());
	}
}
