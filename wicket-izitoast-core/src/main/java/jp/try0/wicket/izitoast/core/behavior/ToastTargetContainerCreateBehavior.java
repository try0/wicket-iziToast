package jp.try0.wicket.izitoast.core.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;

import jp.try0.wicket.izitoast.core.config.DefaultToastTargetSetter;

/**
 * Behavior that append javascript for create target container.
 * @author Ryo Tsundoa
 *
 */
public class ToastTargetContainerCreateBehavior extends Behavior {
	private static final long serialVersionUID = 1L;

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
		String idPrefix = DefaultToastTargetSetter.TARGET_COMPONENT_ID_PREFIX;
		String componentId = component.getMarkupId();

		StringBuilder scriptBuilder = new StringBuilder();
		scriptBuilder.append("var fc = document.getElementById('").append(componentId).append("');")
				.append("var target = document.createElement('div');")
				.append("target.id = '").append(idPrefix).append(componentId).append("';")
				.append("fc.parentNode.insertBefore(target, fc.nextSibling);")
				.append("fc.addEventListener('change', function() {")
				.append("var toast = document.querySelector('#").append(idPrefix).append(componentId)
				.append(" .iziToast');")
				.append("iziToast.hide({}, toast);});");

		return scriptBuilder.toString();
	}
}
