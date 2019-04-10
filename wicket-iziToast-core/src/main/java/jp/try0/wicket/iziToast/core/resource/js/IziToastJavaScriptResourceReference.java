package jp.try0.wicket.iziToast.core.resource.js;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * Reference of iziToast javascript
 *
 * @author Ryo Tsunoda
 *
 */
public class IziToastJavaScriptResourceReference extends JavaScriptResourceReference {
	private static final long serialVersionUID = 1L;

	/**
	 * Reference of iziToast javascript
	 */
	private static final IziToastJavaScriptResourceReference INSTANCE = new IziToastJavaScriptResourceReference();

	/**
	 * Gets IziToastJavaScriptResourceReference instance.
	 *
	 * @return constant
	 */
	public static IziToastJavaScriptResourceReference getInstance() {
		return INSTANCE;
	}

	/**
	 * Constractor
	 */
	public IziToastJavaScriptResourceReference() {
		super(IziToastJavaScriptResourceReference.class, "iziToast.min.js");
	}

}
