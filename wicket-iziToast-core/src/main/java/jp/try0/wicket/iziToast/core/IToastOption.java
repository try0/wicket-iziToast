package jp.try0.wicket.iziToast.core;

import java.io.Serializable;

/**
 * Toast options interface.
 *
 *
 * @author Ryo Tsunoda
 *
 */
public interface IToastOption extends Serializable {

	/**
	 * Gets options as json string.
	 *
	 * @return json string
	 */
	public String toJsonString();

	/**
	 * Gets new overwritten options.<br>
	 * If the value of the argument's option is exists, overwrite the option value.
	 *
	 * @param option overwrite options
	 * @return overwritten option
	 */
	public IToastOption overwrite(IToastOption option);

}
