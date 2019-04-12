package jp.try0.wicket.iziToast.core;

import org.apache.wicket.Component;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.util.lang.Args;

import jp.try0.wicket.iziToast.core.behavior.IziToastBehavior;

/**
 * Toast
 *
 * @author Ryo Tsunoda
 *
 */
public class Toast implements IToast {
	private static final long serialVersionUID = 1L;

	/**
	 * Toast levels
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static enum ToastLevel {
		/**
		 * Undefined level
		 */
		UNDEFINED("undefined", FeedbackMessage.UNDEFINED, false),

		/**
		 * Success level
		 */
		SUCCESS("success", FeedbackMessage.SUCCESS, true),

		/**
		 * Information level
		 */
		INFO("info", FeedbackMessage.INFO, true),

		/**
		 * Warning level
		 */
		WARNING("warning", FeedbackMessage.WARNING, true),

		/**
		 * Error level
		 */
		ERROR("error", FeedbackMessage.ERROR, true),;

		/**
		 * Feedback message level
		 */
		int feedbackMessageLevel;

		/**
		 * Level string. Except for {@link ToastLevel#UNDEFINED}, it's same as iziToast's method name that display messages.
		 */
		String level;

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
		ToastLevel(String level, int feedbackMessageLevel, boolean isSupported) {
			this.feedbackMessageLevel = feedbackMessageLevel;
			this.level = level;
			this.isSupported = isSupported;
		}

		/**
		 * Gets level as string. Except for {@link ToastLevel#UNDEFINED}, it's same as iziToast's method name that display messages.
		 *
		 * @return a level string
		 */
		public String getLevelString() {
			return this.level;
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
		public boolean lessThan(ToastLevel level) {
			return this.feedbackMessageLevel < level.feedbackMessageLevel;
		}

		/**
		 * Whether is greater than argument's level.
		 *
		 * @param level the level to be compared to this level
		 * @return true if this level is greater than argument's level, otherwise false
		 */
		public boolean greaterThan(ToastLevel level) {
			return this.feedbackMessageLevel > level.feedbackMessageLevel;
		}

		/**
		 * Convert feedback message level to toast level.
		 *
		 * @param levelOfFeedbackMessage
		 *            the feedback message's level
		 * @return the toast level
		 */
		public static ToastLevel fromFeedbackMessageLevel(int levelOfFeedbackMessage) {

			for (FeedbackMessageLevel messageLevel : FeedbackMessageLevel.values()) {
				if (messageLevel.toInteger() == levelOfFeedbackMessage) {
					return fromFeedbackMessageLevel(messageLevel);
				}
			}

			return ToastLevel.UNDEFINED;
		}

		/**
		 * Converts feedback message level to toast level.
		 *
		 * @param level
		 *            the feedback message's level
		 * @return the toast level
		 */
		public static ToastLevel fromFeedbackMessageLevel(FeedbackMessageLevel level) {

			switch (level) {
			case DEBUG:
			case ERROR:
			case FATAL:
				return ToastLevel.ERROR;
			case INFO:
				return ToastLevel.INFO;
			case SUCCESS:
				return ToastLevel.SUCCESS;
			case WARNING:
				return ToastLevel.WARNING;
			default:
				return ToastLevel.UNDEFINED;
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
		public static FeedbackMessageLevel fromToastLevel(ToastLevel toastLevel) {

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
	public static Toast create(ToastLevel level, String message) {
		return new Toast(level, message);
	}

	/**
	 * Creates a toast.
	 *
	 * @param level
	 * @param option
	 * @return
	 */
	public static Toast create(ToastLevel level, IToastOption option) {
		return new Toast(level, option);
	}

	/**
	 * Creates a toast.
	 *
	 * @param level
	 * @param message
	 * @param title
	 * @return
	 */
	public static Toast create(ToastLevel level, String message, String title) {
		return new Toast(level, message, title);
	}

	/**
	 * Creates a success toast.
	 *
	 * @param message the string displayed on toast
	 * @return a success level toast
	 */
	public static Toast success(String message) {
		return new Toast(ToastLevel.SUCCESS, message);
	}

	/**
	 * Creates an information toast.
	 *
	 * @param message the string displayed on toast
	 * @return an information level toast
	 */
	public static Toast info(String message) {
		return new Toast(ToastLevel.INFO, message);
	}

	/**
	 * Creates a warning toast.
	 *
	 * @param message the string displayed on toast
	 * @return a warning level toast
	 */
	public static Toast warn(String message) {
		return new Toast(ToastLevel.WARNING, message);
	}

	/**
	 * Creates a error toast.
	 *
	 * @param message the string displayed on toast
	 * @return a error level toast
	 */
	public static Toast error(String message) {
		return new Toast(ToastLevel.ERROR, message);
	}

	/**
	 * Remove current toasts without using animation.
	 *
	 * @param response the response object
	 */
	public static void destroy(final IHeaderResponse response) {
		response.render(JavaScriptHeaderItem.forScript("iziToast.destroy();", null));
	}

	/**
	 * Remove current toasts without using animation.
	 *
	 * @param target the request target
	 */
	public static void destroy(final IPartialPageRequestHandler target) {
		target.appendJavaScript("iziToast.destroy();");
	}

	/**
	 * Remove current toasts using animation.
	 *
	 * @param response the response object
	 */
	public static void hide(final IHeaderResponse response) {
		String hideScript = "var toasts = document.querySelectorAll('.iziToast');"
				+"toasts.forEach(toast => iziToast.hide({}, toast));";
		response.render(JavaScriptHeaderItem.forScript(hideScript, null));
	}

	/**
	 * Remove current toasts using animation.
	 *
	 * @param target the request target
	 */
	public static void hide(final IPartialPageRequestHandler target) {
		String hideScript = "var toasts = document.querySelectorAll('.iziToast');"
				+"toasts.forEach(toast => iziToast.hide({}, toast));";
		target.appendJavaScript(hideScript);
	}

	/**
	 * Toast level
	 */
	private ToastLevel level;

	/**
	 * Option for override global option
	 */
	private IToastOption option;

	private Toast(ToastLevel level) {
		this.level = Args.notNull(level, "level");
	}

	/**
	 * Constractor
	 *
	 * @param level the toast level
	 * @param message the string displayed on toast
	 */
	public Toast(ToastLevel level, String message) {
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
	public Toast(ToastLevel level, String message, String title) {
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
	 * @param message the string displayed on toast
	 * @param title the string displayed on toast
	 * @param option option for override global option
	 */
	public Toast(ToastLevel level, IToastOption option) {
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
	public IToastOption getToastOption() {
		return option;
	}

	public void setToastOption(IToastOption option) {
		this.option = Args.notNull(option, "option");
	}

	/**
	 * Gets toast level.
	 *
	 * @return the toast level
	 */
	@Override
	public ToastLevel getToastLevel() {
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

		script.append("iziToast.").append(level.getLevelString()).append("(");

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

}
