package jp.try0.wicket.izitoast.core.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jp.try0.wicket.izitoast.core.IToast;
import jp.try0.wicket.izitoast.core.IToastFilter;
import jp.try0.wicket.izitoast.core.IToastOption;
import jp.try0.wicket.izitoast.core.Toast;
import jp.try0.wicket.izitoast.core.Toast.ToastType;
import jp.try0.wicket.izitoast.core.ToastOption;

/**
 * Combiner that combines messages for each toast level.<br>
 * This class works when there are multiple messages for the level.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastMessageCombiner implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Void action.
	 */
	public static final ToastMessageCombiner VOID_COMBINER = new ToastMessageCombiner() {
		private static final long serialVersionUID = 1L;

		@Override
		public List<IToast> combine(List<IToast> toastStream) {
			return toastStream;
		}

		@Override
		public String getPrefix() {
			return "";
		}

		@Override
		public String getSuffix() {
			return "";
		}
	};

	/**
	 * Default delimiter string.
	 */
	public static final String DEFAULT_SUFFIX = "<br>";

	// dummy to append suffix. this toast's level has no meaning.
	private static final Toast IDENTITY = Toast.success("");

	/**
	 * Prefix of each message
	 */
	private String prefix = "";

	/**
	 * Suffix of each message
	 */
	private String suffix = DEFAULT_SUFFIX;

	/**
	 *
	 */
	private IToastFilter ignoreToastFilter = t -> false;

	/**
	 * Constractor
	 */
	public ToastMessageCombiner() {
	}

	/**
	 * Gets prefix of each message.
	 *
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * Sets prefix of each message.
	 *
	 * @param prefix the prefix
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * Gets suffix of each message.
	 *
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * Sets suffix of each message.
	 *
	 * @param suffix the suffix
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public void setIgnoreToastFilter(IToastFilter ignoreToastFilter) {
		this.ignoreToastFilter = ignoreToastFilter;
	}

	/**
	 * Combines messages for each toast level.
	 *
	 * @param toastStream the target
	 * @return the stream that combined messages for each toast level
	 */
	public List<IToast> combine(List<IToast> toasts) {

		// not combine toasts
		List<IToast> ignores = toasts.stream().filter(ignoreToastFilter).collect(Collectors.toList());

		// combine toasts
		Map<ToastType, List<IToast>> groupByLevel = toasts.stream()
				.filter(ignoreToastFilter.negate())
				.collect(Collectors.groupingBy(IToast::getToastType));
		List<IToast> combied = groupByLevel.entrySet().stream()
				.filter(es -> !es.getValue().isEmpty())
				.map(es -> es.getValue())
				.map(ts -> {
					return ts.stream()
							.reduce(IDENTITY, (joined, t) -> {
								return combine(joined, t);
							});
				})
				.collect(Collectors.toList());

		List<IToast> results = new ArrayList<>();
		results.addAll(ignores);
		results.addAll(combied);

		return results;
	}

	/**
	 * Combines toasts.
	 *
	 * @param combined the combined toast
	 * @param target the uncombined toast
	 * @return the toast that combine combined and target
	 */
	public IToast combine(IToast combined, IToast target) {

		// select toast title
		String title = decideTitle(combined, target);

		// combine messages
		String decoratedMessage = decorateMessage(target.getMessage(), getPrefix(), getSuffix());
		String concatenatedMessage = combined.getMessage() + decoratedMessage;

		// combine toast options
		IToastOption option = decideToastOption(combined, target);

		ToastOption tmpOption = new ToastOption();
		tmpOption.setTitle(title);
		tmpOption.setMessage(concatenatedMessage);

		final Toast newToast = Toast.create(target.getToastType(), ToastOption.createMerged(option, tmpOption));

		return newToast;
	}

	/**
	 * Decides toast option.
	 *
	 * @param combined the combined toast
	 * @param target the uncombined toast
	 * @return the option to apply
	 */
	protected IToastOption decideToastOption(IToast combined, IToast target) {

		IToastOption combinedOption = combined.getToastOption();
		IToastOption targetOption = target.getToastOption();

		return ToastOption.createMerged(combinedOption, targetOption);
	}

	/**
	 * Decides toast title.
	 *
	 * @param combined the combined toast
	 * @param target the uncombined toast
	 * @return the title to display
	 */
	protected String decideTitle(IToast combined, IToast target) {
		return target.getTitle();
	}

	/**
	 * Decorates message.
	 *
	 * @param message the toast message
	 * @param prefix the message prefix
	 * @param suffix the message suffix
	 * @return decorated message
	 */
	protected String decorateMessage(String message, String prefix, String suffix) {
		return prefix + message + suffix;
	}

}