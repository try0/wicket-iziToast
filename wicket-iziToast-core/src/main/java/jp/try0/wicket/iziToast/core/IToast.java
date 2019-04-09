package jp.try0.wicket.iziToast.core;

import java.io.Serializable;

import jp.try0.wicket.iziToast.core.Toast.ToastLevel;

/**
 * Toast interface.
 *
 * @author Ryo Tsunoda
 *
 */
public interface IToast extends Serializable {

	/**
	 * Gets toast level.
	 *
	 * @return the toast level
	 */
	public ToastLevel getToastLevel();

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
