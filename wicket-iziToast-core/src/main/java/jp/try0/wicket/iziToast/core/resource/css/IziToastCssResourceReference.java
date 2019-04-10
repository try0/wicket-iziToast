package jp.try0.wicket.iziToast.core.resource.css;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * Reference of iziToast css
 *
 * @author Ryo Tsunoda
 *
 */
public class IziToastCssResourceReference extends CssResourceReference {
	private static final long serialVersionUID = 1L;

	private static class IziToastCssResourceReferenceHolder {
		/**
		 * Reference of iziToast css
		 */
		private static final IziToastCssResourceReference INSTANCE = new IziToastCssResourceReference();
	}

	/**
	 * Gets IziToastCssResourceReference instance.
	 *
	 * @return constant
	 */
	public static IziToastCssResourceReference getInstance() {
		return IziToastCssResourceReferenceHolder.INSTANCE;
	}

	/**
	 * Constractor
	 */
	public IziToastCssResourceReference() {
		super(IziToastCssResourceReference.class, "iziToast.min.css");
	}

}
