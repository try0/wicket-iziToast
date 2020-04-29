package jp.try0.wicket.izitoast.core.config;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.Page;

import jp.try0.wicket.izitoast.core.IToast;
import jp.try0.wicket.izitoast.core.IToastFilter;
import jp.try0.wicket.izitoast.core.behavior.ToastTargetContainerCreateBehavior;

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

		List<ToastTargetContainerCreateBehavior> behaviors = component
				.getBehaviors(ToastTargetContainerCreateBehavior.class);

		if (behaviors.isEmpty()) {
			toast.getToastOption().setTarget("#" + component.getMarkupId());
		} else {
			ToastTargetContainerCreateBehavior behavior = behaviors.get(0);
			toast.getToastOption().setTarget("#" + behavior.getContainerId(component));
			toast.getToastOption().setTimeout(false);
			toast.getToastOption().setDisplayMode(1);
		}
	}
}
