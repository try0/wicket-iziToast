package jp.try0.wicket.izitoast.core.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

import jp.try0.wicket.izitoast.core.resource.css.IziToastCssResourceReference;
import jp.try0.wicket.izitoast.core.resource.js.IziToastJavaScriptResourceReference;

/**
 * iziToast resource behavior.<br>
 * This behavior provides the function to append iziToast's resources.
 *
 * @author Ryo Tsunoda
 *
 */
public class IziToastResourcesBehavior extends Behavior {
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		super.renderHead(component, response);

		// iziToast css
		response.render(CssHeaderItem.forReference(IziToastCssResourceReference.getInstance()));
		// iziToast js
		response.render(JavaScriptHeaderItem.forReference(IziToastJavaScriptResourceReference.getInstance()));

	}

}
