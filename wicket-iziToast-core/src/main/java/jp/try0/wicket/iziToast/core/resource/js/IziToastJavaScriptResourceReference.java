package jp.try0.wicket.iziToast.core.resource.js;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * Reference of toastr javascript
 *
 * @author Ryo Tsunoda
 *
 */
public class IziToastJavaScriptResourceReference extends JavaScriptResourceReference {
	private static final long serialVersionUID = 1L;

	/**
	 * Reference of toastr javascript
	 */
	private static final IziToastJavaScriptResourceReference INSTANCE = new IziToastJavaScriptResourceReference();

	/**
	 * Gets ToastrJavaScriptResourceReference instance.
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
