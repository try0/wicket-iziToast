package jp.try0.wicket.izitoast.core;

import java.io.Serializable;

import jp.try0.wicket.izitoast.core.Toast.ToastType;

/**
 * Toast interface.
 *
 * @author Ryo Tsunoda
 *
 */
public interface IToast extends Serializable {

	/**
	 * Gets toast type.
	 *
	 * @return the toast type
	 */
	public ToastType getToastType();

	/**
	 * Gets toast title.
	 *
	 * @return title
	 */
	public default String getTitle() {
		return getToastOption().getTitle();
	}

	/**
	 * Gets toast message.
	 *
	 * @return message
	 */
	public default String getMessage() {
		return getToastOption().getMessage();
	}

	/**
	 * Gets toast options that override global options.
	 *
	 * @return toast option
	 */
	public IToastOption getToastOption();

	/**
	 * Gets script for display toast.
	 *
	 * @return the script for display toast
	 */
	public CharSequence getScriptForDisplay();

}
