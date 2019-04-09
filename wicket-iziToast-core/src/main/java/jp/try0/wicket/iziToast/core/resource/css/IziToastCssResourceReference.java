package jp.try0.wicket.iziToast.core.resource.css;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * Reference of toastr css
 *
 * @author Ryo Tsunoda
 *
 */
public class IziToastCssResourceReference extends CssResourceReference {
	private static final long serialVersionUID = 1L;

	private static class ToastrCssResourceReferenceHolder {
		/**
		 * Reference of toastr css
		 */
		private static final IziToastCssResourceReference INSTANCE = new IziToastCssResourceReference();
	}

	/**
	 * Gets ToastrCssResourceReference instance.
	 *
	 * @return constant
	 */
	public static IziToastCssResourceReference getInstance() {
		return ToastrCssResourceReferenceHolder.INSTANCE;
	}

	/**
	 * Constractor
	 */
	public IziToastCssResourceReference() {
		super(IziToastCssResourceReference.class, "iziToast.min.css");
	}

}
