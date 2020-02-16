package jp.try0.wicket.izitoast.core.config;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.FormComponent;

/**
 * Toast target cotainer appender.<br>
 * Appends toast container to FormComponent.
 *
 * @see DefaultToastTargetSetter
 * @author Ryo Tsunoda
 *
 */
public class ToastTargetContainerAppender implements IComponentInstantiationListener, Serializable {

	@Override
	public void onInstantiation(Component component) {

		if (needAppend(component)) {

			component.add(new Behavior() {

				@Override
				public void renderHead(Component component, IHeaderResponse response) {
					super.renderHead(component, response);

					String script = getTargetAppendScript(component);
					response.render(OnDomReadyHeaderItem.forScript(script));
				}
			});
		}

	}

	public boolean needAppend(Component component) {
		return component instanceof FormComponent;
	}

	protected String getTargetAppendScript(Component component) {
		String idPrefix = DefaultToastTargetSetter.TARGET_COMPONENT_ID_PREFIX;
		String componentId = component.getMarkupId();

		String script = "var fc = document.getElementById('" + componentId + "');";
		script += "var target = document.createElement('div'); "
				+ "target.id = '" + idPrefix + componentId + "';";
		script += "fc.parentNode.insertBefore(target, fc.nextSibling);";

		script += "fc.addEventListener('change', function() {"
				+ "var toast = document.querySelector('#" + idPrefix + componentId + " .iziToast');"
				+ "iziToast.hide({}, toast);"
				+ "});";

		return script;
	}

}
