package jp.try0.wicket.izitoast.core;

import org.apache.wicket.Component;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.util.lang.Args;

import jp.try0.wicket.izitoast.core.behavior.IziToastBehavior;

/**
 * Toast
 *
 * @author Ryo Tsunoda
 *
 */
public class Toast implements IToast {
	private static final long serialVersionUID = 1L;

	/**
	 * Script that destroy all toasts.
	 */
	public static final String SCRIPT_DESTROY_TOAST = "iziToast.destroy();";

	/**
	 * Script that hide all toasts.
	 */
	public static final String SCRIPT_HIDE_ALL_TOAST = "var toasts = document.querySelectorAll('.iziToast');"
			+ "toasts.forEach(function(toast) { iziToast.hide({}, toast); });";

	/**
	 * Toast Types
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static enum ToastType {
				/**
				 * Undefined
				 */
				UNDEFINED("undefined", FeedbackMessage.UNDEFINED, false),

				/**
				 * Success
				 */
				SUCCESS("success", FeedbackMessage.SUCCESS, true),

				/**
				 * Information
				 */
				INFO("info", FeedbackMessage.INFO, true),

				/**
				 * Warning
				 */
				WARNING("warning", FeedbackMessage.WARNING, true),

				/**
				 * Error
				 */
				ERROR("error", FeedbackMessage.ERROR, true),

				/**
				 * Plain
				 */
				PLAIN("show", FeedbackMessage.UNDEFINED, true),

				/**
				 * Question
				 */
				QUESTION("question", FeedbackMessage.UNDEFINED, true),
				;

		/**
		 * Feedback message level
		 */
		int feedbackMessageLevel;

		/**
		 * Toast type. Except for {@link ToastType#UNDEFINED}, it's same as iziToast's method name that display messages.
		 */
		String toastType;

		/**
		 * Whether toast can be displayed.
		 */
		boolean isSupported;

		/**
		 * Constractor
		 *
		 * @param level level string
		 * @param isSupported Whether it is iziToast's method name to display the message
		 */
		ToastType(String level, int feedbackMessageLevel, boolean isSupported) {
			this.feedbackMessageLevel = feedbackMessageLevel;
			this.toastType = level;
			this.isSupported = isSupported;
		}

		/**
		 * Gets type as string. Except for {@link ToastType#UNDEFINED}, it's same as iziToast's method name that display messages.
		 *
		 * @return a type as string
		 */
		public String getTypeAsString() {
			return this.toastType;
		}

		/**
		 * Gets whether toast can be displayed.
		 *
		 * @return true if toast can be displayed, otherwise false
		 */
		public boolean isSupported() {
			return this.isSupported;
		}

		/**
		 * Whether is less than argument's level.
		 *
		 * @param level the level to be compared to this level
		 * @return true if this level is less than argument's level, otherwise false
		 */
		public boolean lessThan(ToastType level) {
			return this.feedbackMessageLevel < level.feedbackMessageLevel;
		}

		/**
		 * Whether is greater than argument's level.
		 *
		 * @param level the level to be compared to this level
		 * @return true if this level is greater than argument's level, otherwise false
		 */
		public boolean greaterThan(ToastType level) {
			return this.feedbackMessageLevel > level.feedbackMessageLevel;
		}

		/**
		 * Convert feedback message level to toast level.
		 *
		 * @param levelOfFeedbackMessage
		 *            the feedback message's level
		 * @return the toast level
		 */
		public static ToastType fromFeedbackMessageLevel(int levelOfFeedbackMessage) {

			for (FeedbackMessageLevel messageLevel : FeedbackMessageLevel.values()) {
				if (messageLevel.toInteger() == levelOfFeedbackMessage) {
					return fromFeedbackMessageLevel(messageLevel);
				}
			}

			return ToastType.UNDEFINED;
		}

		/**
		 * Converts feedback message level to toast level.
		 *
		 * @param level
		 *            the feedback message's level
		 * @return the toast level
		 */
		public static ToastType fromFeedbackMessageLevel(FeedbackMessageLevel level) {

			switch (level) {
			case DEBUG:
			case ERROR:
			case FATAL:
				return ToastType.ERROR;
			case INFO:
				return ToastType.INFO;
			case SUCCESS:
				return ToastType.SUCCESS;
			case WARNING:
				return ToastType.WARNING;
			default:
				return ToastType.UNDEFINED;
			}

		}
	}

	/**
	 * Enum of FeedbackMessage's level constants.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public enum FeedbackMessageLevel {
		/**
		 * Undefined level
		 */
		UNDEFINED(FeedbackMessage.UNDEFINED),

		/**
		 * Debug level
		 */
		DEBUG(FeedbackMessage.DEBUG),

		/**
		 * Info level
		 */
		INFO(FeedbackMessage.INFO),

		/**
		 * Success level
		 */
		SUCCESS(FeedbackMessage.SUCCESS),

		/**
		 * Warning level
		 */
		WARNING(FeedbackMessage.WARNING),

		/**
		 * Error level
		 */
		ERROR(FeedbackMessage.ERROR),

		/**
		 * Fatal level
		 */
		FATAL(FeedbackMessage.FATAL),;

		/**
		 * {@link FeedbackMessage}'s constant of message level.
		 */
		int level;

		/**
		 * Constractor
		 *
		 * @param level
		 */
		private FeedbackMessageLevel(int level) {
			this.level = level;
		}

		/**
		 * Gets message level as int.
		 *
		 * @return the {@link FeedbackMessage}'s level int value
		 */
		public int toInteger() {
			return level;
		}

		/**
		 * Converts toast level to feedback message level.
		 *
		 * @param toastLevel the toast level
		 * @return the feedback message's level
		 */
		public static FeedbackMessageLevel fromToastLevel(ToastType toastLevel) {

			switch (toastLevel) {
			case ERROR:
				return FeedbackMessageLevel.ERROR;
			case INFO:
				return FeedbackMessageLevel.INFO;
			case SUCCESS:
				return FeedbackMessageLevel.SUCCESS;
			case WARNING:
				return FeedbackMessageLevel.WARNING;
			default:
				return FeedbackMessageLevel.UNDEFINED;
			}

		}
	}

	/**
	 * Creates a toast.
	 *
	 * @param level the toast level
	 * @param message the string displayed on toast
	 * @return a toast
	 */
	public static Toast create(ToastType level, String message) {
		return new Toast(level, message);
	}

	/**
	 * Creates a toast.
	 *
	 * @param level the toast level
	 * @param option the toast option
	 * @return a toast
	 */
	public static Toast create(ToastType level, ToastOption option) {
		return new Toast(level, option);
	}

	/**
	 * Creates a toast.
	 *
	 * @param level the toast level
	 * @param message the toast message
	 * @param title the toast title
	 * @return a toast
	 */
	public static Toast create(ToastType level, String message, String title) {
		return new Toast(level, message, title);
	}

	/**
	 * Creates a success toast.
	 *
	 * @param message the string displayed on toast
	 * @return a success level toast
	 */
	public static Toast success(String message) {
		return new Toast(ToastType.SUCCESS, message);
	}

	/**
	 * Creates an information toast.
	 *
	 * @param message the string displayed on toast
	 * @return an information level toast
	 */
	public static Toast info(String message) {
		return new Toast(ToastType.INFO, message);
	}

	/**
	 * Creates a warning toast.
	 *
	 * @param message the string displayed on toast
	 * @return a warning level toast
	 */
	public static Toast warn(String message) {
		return new Toast(ToastType.WARNING, message);
	}

	/**
	 * Creates a error toast.
	 *
	 * @param message the string displayed on toast
	 * @return a error level toast
	 */
	public static Toast error(String message) {
		return new Toast(ToastType.ERROR, message);
	}

	/**
	 * Creates a plain toast.
	 *
	 * @param message the string displayed on toast
	 * @return a plain type toast
	 */
	public static Toast plain(String message) {
		return new Toast(ToastType.PLAIN, message);
	}

	/**
	 * Remove current toasts without using animation.
	 *
	 * @param response the response object
	 */
	public static void destroy(final IHeaderResponse response) {
		response.render(JavaScriptHeaderItem.forScript(SCRIPT_DESTROY_TOAST, null));
	}

	/**
	 * Remove current toasts without using animation.
	 *
	 * @param target the request target
	 */
	public static void destroy(final IPartialPageRequestHandler target) {
		target.appendJavaScript(SCRIPT_DESTROY_TOAST);
	}

	/**
	 * Remove current toasts using animation.
	 *
	 * @param response the response object
	 */
	public static void hideAll(final IHeaderResponse response) {
		response.render(JavaScriptHeaderItem.forScript(SCRIPT_HIDE_ALL_TOAST, null));
	}

	/**
	 * Remove current toasts using animation.
	 *
	 * @param target the request target
	 */
	public static void hideAll(final IPartialPageRequestHandler target) {
		target.appendJavaScript(SCRIPT_HIDE_ALL_TOAST);
	}

	/**
	 * Gets hide toast script.
	 *
	 * @param toastId {@link ToastOption#id}
	 * @return
	 */
	public static String getHideScript(String toastId) {
		return "var hideToast = document.getElementById('" + toastId + "');iziToast.hide({}, hideToast);";
	}

	/**
	 * Toast level
	 */
	private ToastType level;

	/**
	 * Option for override global option
	 */
	private ToastOption option;

	private Toast(ToastType level) {
		this.level = Args.notNull(level, "level");
	}

	/**
	 * Constractor
	 *
	 * @param level the toast level
	 * @param message the string displayed on toast
	 */
	public Toast(ToastType level, String message) {
		this(level);

		ToastOption option = new ToastOption();
		option.setMessage(message);
		setToastOption(option);
	}

	/**
	 * Constractor
	 *
	 * @param level the toast level
	 * @param message the string displayed on toast
	 * @param title the string displayed on toast
	 */
	public Toast(ToastType level, String message, String title) {
		this(level);

		ToastOption option = new ToastOption();
		option.setTitle(title);
		option.setMessage(message);
		setToastOption(option);
	}

	/**
	 * Constractor
	 *
	 * @param level the toast level
	 * @param option option for override global option
	 */
	public Toast(ToastType level, ToastOption option) {
		if (!level.isSupported()) {
			throw new IllegalArgumentException("This level is unsupported.");
		}

		this.level = Args.notNull(level, "level");
		this.option = Args.notNull(option, "option");
	}

	/**
	 * Gets option.
	 *
	 * @return an {@code Optional} with a toast option
	 */
	@Override
	public ToastOption getToastOption() {
		return option;
	}

	/**
	 * Sets option.<br>
	 * Caution : option includes title and message.
	 *
	 * @param option
	 * @return
	 */
	public Toast setToastOption(ToastOption option) {
		this.option = Args.notNull(option, "option");
		return this;
	}

	/**
	 * Gets toast level.
	 *
	 * @return the toast level
	 */
	@Override
	public ToastType getToastLevel() {
		return level;
	}

	/**
	 * Replaces \r\n and \r to &lt;br&gt; tag.
	 *
	 * @param value the target that to replace newline code with a br tag
	 * @return a string that replaced a newline code with a br tag
	 */
	private static String replaceNewlineCodeToTag(String value) {
		return value.replaceAll("\r\n", "\n").replaceAll("\r", "\n").replaceAll("\n", "<br/>");
	}

	/**
	 * Creates script for displaying toast with consider level.
	 *
	 * @return the script for displaying toast
	 */
	@Override
	public String getScriptForDisplay() {
		final StringBuilder script = new StringBuilder();

		script.append("iziToast.").append(level.getTypeAsString()).append("(");

		// sets override options
		script.append(option.toJsonString());

		script.append(");");

		return script.toString();
	}

	/**
	 * Shows toast.
	 *
	 * @param target the request target
	 */
	public void show(final IPartialPageRequestHandler target) {
		target.appendJavaScript(getScriptForDisplay());
	}

	/**
	 * Shows toast.
	 *
	 * @param responce responce object
	 */
	public void show(final IHeaderResponse responce) {
		responce.render(JavaScriptHeaderItem.forScript(getScriptForDisplay(), null));
	}

	/**
	 * Shows toast.<br>
	 * This method need to add {@link IziToastBehavior} to any of component in page.
	 *
	 * @param component the reporting component
	 */
	public void show(final Component component) {
		switch (level) {
		case ERROR:
			component.error(this);
			break;
		case INFO:
			component.info(this);
			break;
		case SUCCESS:
			component.success(this);
			break;
		case WARNING:
			component.warn(this);
			break;
		default:
			throw new IllegalArgumentException("This level is unsupported.");

		}
	}

	/**
	 * Hides this toast.
	 *
	 * @param target
	 */
	public void hide(final IPartialPageRequestHandler target) {
		String id = getToastOption().getId();
		if (id == null || id.isEmpty()) {
			throw new IllegalStateException("Toast has no id (option.id).");
		}

		target.appendJavaScript(getHideScript(id));
	}

}
