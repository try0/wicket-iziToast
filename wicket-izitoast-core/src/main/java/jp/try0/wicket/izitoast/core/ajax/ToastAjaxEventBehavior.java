package jp.try0.wicket.izitoast.core.ajax;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;

import jp.try0.wicket.izitoast.core.IToast;
import jp.try0.wicket.izitoast.core.Toast;

/**
 * Toast ajax callback event.<br>
 * First, create instance and add to page.<br>
 * Next, call {@link ToastAjaxEventBehavior#setOnClosed(Toast, ToastAjaxEventBehavior)}...<br>
 *
 * @author Ryo Tsunoda
 *
 */
public abstract class ToastAjaxEventBehavior extends AbstractDefaultAjaxBehavior {

	/**
	 * Sets {@link ToastAjaxEventBehavior} to OnClosed event.
	 *
	 * @param toast The target toast
	 * @param ajaxBehavior OnClosed event callback
	 */
	public static void setOnClosed(Toast toast, ToastAjaxEventBehavior ajaxBehavior) {
		toast.getToastOption().setOnClosed("function() { console.log('OnClosed');" + ajaxBehavior.getCallbackScript() + " }");
		ajaxBehavior.bindToast(toast);
	}

	/**
	 * Sets {@link ToastAjaxEventBehavior} to OnClosing event.
	 *
	 * @param toast The target toast
	 * @param ajaxBehavior OnClosing event callback
	 */
	public static void setOnClosing(Toast toast, ToastAjaxEventBehavior ajaxBehavior) {
		toast.getToastOption().setOnClosing("function() { console.log('OnClosing');" + ajaxBehavior.getCallbackScript() + " }");
		ajaxBehavior.bindToast(toast);
	}

	/**
	 * Sets {@link ToastAjaxEventBehavior} to OnOpened event.
	 *
	 * @param toast The target toast
	 * @param ajaxBehavior OnOpened event callback
	 */
	public static void setOnOpened(Toast toast, ToastAjaxEventBehavior ajaxBehavior) {
		toast.getToastOption().setOnOpened("function() { console.log('OnOpened');" + ajaxBehavior.getCallbackScript() + " }");
		ajaxBehavior.bindToast(toast);
	}

	/**
	 * Sets {@link ToastAjaxEventBehavior} to OnOpening event.
	 *
	 * @param toast The target toast
	 * @param ajaxBehavior OnOpening event callback
	 */
	public static void setOnOpening(Toast toast, ToastAjaxEventBehavior ajaxBehavior) {
		toast.getToastOption().setOnOpening("function() { console.log('OnOpening');" + ajaxBehavior.getCallbackScript() + " }");
		ajaxBehavior.bindToast(toast);
	}

	/**
	 * Target toast
	 */
	private IToast toast;

	/**
	 * Called when a toast event raised.
	 *
	 * @param target The AJAX target
	 * @param toast The binds toast
	 */
	abstract protected void respond(AjaxRequestTarget target, IToast bindToast);

	/**
	 * Binds event target toast.
	 *
	 * @param toast
	 */
	public void bindToast(IToast toast) {
		this.toast = toast;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void respond(AjaxRequestTarget target) {
		respond(target, toast);
	}

}
