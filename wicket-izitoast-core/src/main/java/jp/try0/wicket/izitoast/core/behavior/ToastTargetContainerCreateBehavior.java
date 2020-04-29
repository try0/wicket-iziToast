package jp.try0.wicket.izitoast.core.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;

/**
 * Behavior that append javascript for create target container.
 * @author Ryo Tsunoda
 *
 */
public class ToastTargetContainerCreateBehavior extends Behavior {
	private static final long serialVersionUID = 1L;

	/**
	 * Container markup id prefix.
	 */
	public static final String TARGET_COMPONENT_ID_PREFIX = "wicket-iziToast-target-";

	/**
	 * Constructor.
	 */
	public ToastTargetContainerCreateBehavior() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		super.renderHead(component, response);

		String script = getTargetContainerAppendScript(component);
		response.render(OnDomReadyHeaderItem.forScript(script));
	}

	/**
	 * Creates javascript that appends toast container after repeater component.
	 *
	 * @param component the repeater component
	 * @return script
	 */
	protected String getTargetContainerAppendScript(Component component) {
		String containerId = getContainerId(component);
		String componentId = component.getMarkupId();

		StringBuilder scriptBuilder = new StringBuilder();
		scriptBuilder.append("var fc = document.getElementById('").append(componentId).append("');")
				.append("var target = document.createElement('div');")
				.append("target.id = '").append(containerId).append("';")
				.append("fc.parentNode.insertBefore(target, fc.nextSibling);")
				.append("fc.addEventListener('change', function() {")
				.append("var toast = document.querySelector('#").append(containerId)
				.append(" .iziToast');")
				.append("iziToast.hide({}, toast);});");

		return scriptBuilder.toString();
	}

	/**
	 * Gets target container id.
	 *
	 * @param component
	 * @return
	 */
	public String getContainerId(Component component) {
		String idPrefix = TARGET_COMPONENT_ID_PREFIX;
		String componentId = component.getMarkupId();

		return idPrefix + componentId;
	}

}
