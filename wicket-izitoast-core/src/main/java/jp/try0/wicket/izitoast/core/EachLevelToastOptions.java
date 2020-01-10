package jp.try0.wicket.izitoast.core;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import jp.try0.wicket.izitoast.core.Toast.ToastType;

/**
 * Toast options for each {@link ToastType}.
 *
 * @author Ryo Tsunoda
 *
 */
public class EachLevelToastOptions implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Options builder.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static class EachLevelToastOptionsBuilder {

		/**
		 * options for each levels
		 */
		Map<ToastType, ToastOption> options = new EnumMap<>(ToastType.class);

		/**
		 * Sets option of infomation toast.
		 *
		 * @param option option of information toast
		 * @return builder
		 */
		public EachLevelToastOptionsBuilder setInfoOption(ToastOption option) {
			options.put(ToastType.INFO, option);
			return this;
		}

		/**
		 * Sets option of success toast.
		 *
		 * @param option option of success toast
		 * @return builder
		 */
		public EachLevelToastOptionsBuilder setSuccessOption(ToastOption option) {
			options.put(ToastType.SUCCESS, option);
			return this;
		}

		/**
		 * Sets option of warning toast.
		 *
		 * @param option option of warn toast
		 * @return builder
		 */
		public EachLevelToastOptionsBuilder setWarnOption(ToastOption option) {
			options.put(ToastType.WARNING, option);
			return this;
		}

		/**
		 * Sets option of error toast.
		 *
		 * @param option option of error toast
		 * @return builder
		 */
		public EachLevelToastOptionsBuilder setErrorOption(ToastOption option) {
			options.put(ToastType.ERROR, option);
			return this;
		}

		/**
		 * Creates ToastOptions.
		 *
		 * @return the options for each levels
		 */
		public EachLevelToastOptions get() {
			return new EachLevelToastOptions(options);
		}
	}

	/**
	 * Creates options builder.
	 *
	 * @return builder
	 */
	public static EachLevelToastOptionsBuilder builder() {
		return new EachLevelToastOptionsBuilder();
	}

	/**
	 * options for each level
	 */
	private final Map<ToastType, ToastOption> options = new EnumMap<>(ToastType.class);

	/**
	 * Constructor
	 */
	public EachLevelToastOptions() {
	}

	/**
	 * Constrauctor
	 *
	 * @param options the options for each levels
	 */
	public EachLevelToastOptions(Map<ToastType, ToastOption> options) {
		options.forEach((key, val) -> {
			if (val == null) {
				return;
			}

			this.options.put(key, val);
		});
	}

	/**
	 * Gets toast option.
	 *
	 * @param level the toast level
	 * @return the toast option
	 */
	public Optional<ToastOption> get(ToastType level) {
		ToastOption option = options.get(level);
		return Optional.ofNullable(option);
	}

}
