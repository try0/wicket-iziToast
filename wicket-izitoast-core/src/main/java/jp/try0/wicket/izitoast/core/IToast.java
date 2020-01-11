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
	 * Gets toast title. (option.title)
	 *
	 * @return title
	 * @see #getToastOption()
	 */
	public default String getTitle() {
		return getToastOption().getTitle();
	}

	/**
	 * Gets toast message. (option.message)
	 *
	 * @return message
	 * @see #getToastOption()
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
