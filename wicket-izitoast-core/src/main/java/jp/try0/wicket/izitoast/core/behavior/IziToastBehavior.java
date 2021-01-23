package jp.try0.wicket.izitoast.core.behavior;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FeedbackMessagesModel;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

import jp.try0.wicket.izitoast.core.EachLevelToastOptions;
import jp.try0.wicket.izitoast.core.IToast;
import jp.try0.wicket.izitoast.core.IToastOption;
import jp.try0.wicket.izitoast.core.Toast;
import jp.try0.wicket.izitoast.core.Toast.ToastType;
import jp.try0.wicket.izitoast.core.ToastOption;
import jp.try0.wicket.izitoast.core.config.IToastTargetLinker;
import jp.try0.wicket.izitoast.core.config.IziToastSetting;
import jp.try0.wicket.izitoast.core.config.ToastMessageCombiner;

/**
 * iziToast behavior.<br>
 * This Behavior provides the function to create Toasts from FeedbackMessages.
 *
 * @author Ryo Tsunoda
 *
 */
public class IziToastBehavior extends IziToastResourcesBehavior {
	private static final long serialVersionUID = 1L;

	/**
	 * Model that returns empty list of {@link FeedbackMessage}
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	private static final class EmptyFeedbackMessagesModel implements IModel<List<FeedbackMessage>> {
		private static final long serialVersionUID = 1L;

		/**
		 * Instance of EmptyFeedbackMessagesModel
		 */
		private static final EmptyFeedbackMessagesModel INSTANCE = new EmptyFeedbackMessagesModel();

		/**
		 * noop
		 */
		@Override
		public final void detach() {
			// noop
		}

		/**
		 * Always returns result of {@link Collections#emptyList()}
		 */
		@Override
		public final List<FeedbackMessage> getObject() {
			return Collections.emptyList();
		}

		/**
		 * Always throws {@link UnsupportedOperationException}
		 */
		@Override
		public final void setObject(List<FeedbackMessage> object) {
			throw new UnsupportedOperationException(EmptyFeedbackMessagesModel.class.getName() + " is readonly.");
		}

	}

	private class Tuple<X, Y> {
		public final X x;
		public final Y y;

		public Tuple(X x, Y y) {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * Model of {@link FeedbackMessage}s
	 */
	private IModel<List<FeedbackMessage>> feedbackMessagesModel = EmptyFeedbackMessagesModel.INSTANCE;

	/**
	 * Message filter
	 */
	private IFeedbackMessageFilter messageFilter;

	/**
	 * Message combiner
	 */
	private ToastMessageCombiner messageCombiner;

	/**
	 * Toast target setter
	 */
	private IToastTargetLinker toastTargetLinker;

	/**
	 * Constractor
	 */
	public IziToastBehavior() {
		super();
		IziToastSetting setting = IziToastSetting.get();
		this.messageCombiner = setting.getToastMessageCombiner();
		this.toastTargetLinker = setting.getToastTargetLinker();
		setting.getMessageFilter().ifPresent(filter -> this.messageFilter = filter);
	}

	/**
	 * Constractor
	 *
	 * @param messageFilter The filter to apply
	 */
	public IziToastBehavior(IFeedbackMessageFilter messageFilter) {
		super();
		IziToastSetting setting = IziToastSetting.get();
		this.messageCombiner = setting.getToastMessageCombiner();
		this.toastTargetLinker = setting.getToastTargetLinker();
		this.messageFilter = Args.notNull(messageFilter, "messageFilter");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bind(Component component) {
		super.bind(component);

		feedbackMessagesModel = newFeedbackMessagesModel(component, messageFilter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unbind(Component component) {
		super.unbind(component);

		feedbackMessagesModel = EmptyFeedbackMessagesModel.INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void detach(Component component) {
		super.detach(component);

		// clear cache messages
		feedbackMessagesModel.detach();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onEvent(Component component, IEvent<?> event) {
		super.onEvent(component, event);

		Object payload = event.getPayload();
		if (payload instanceof AjaxRequestTarget) {
			// even when recieve an ajax request, show toast

			if (!feedbackMessagesModel.getObject().isEmpty()) {
				String script = getScriptForDisplay();
				if (!script.isEmpty()) {
					AjaxRequestTarget target = (AjaxRequestTarget) payload;

					target.appendJavaScript(script);
				}

				// clear cache messages
				feedbackMessagesModel.detach();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		super.renderHead(component, response);

		IziToastSetting.get().getGlobalOption().ifPresent(option -> {
			// iziToast global option setting
			response.render(JavaScriptHeaderItem.forScript(getScriptForSettingOption(option), null));
		});

		if (!feedbackMessagesModel.getObject().isEmpty()) {
			// notification script
			response.render(OnDomReadyHeaderItem.forScript(getScriptForDisplay()));
		}

		// clear cache messages
		feedbackMessagesModel.detach();
	}

	/**
	 * Sets filter to apply.
	 *
	 * @param messageFilter the message filter
	 */
	public void setMessageFilter(IFeedbackMessageFilter messageFilter) {
		this.messageFilter = Args.notNull(messageFilter, "messageFilter");
	}

	/**
	 * Sets combiner that combines messages for each toast level.
	 *
	 * @param messageCombiner the message combiner
	 */
	public void setMessageCombiner(ToastMessageCombiner messageCombiner) {
		this.messageCombiner = Args.notNull(messageCombiner, "messageCombiner");
	}

	/**
	 * Sets target setter that set toast target container.
	 *
	 * @param toastTargetSetter the toast target setter
	 */
	public void setToastTargetLinker(IToastTargetLinker toastTargetLinker) {
		this.toastTargetLinker = Args.notNull(toastTargetLinker, "toastTargetLinker");
	}

	/**
	 * Creates a {@link FeedbackMessagesModel}.
	 *
	 * @param pageResolvingComponent The component for retrieving page instance
	 * @param messageFilter The filter to apply
	 * @return The model of {@link FeedbackMessage}s that applied filter
	 */
	protected FeedbackMessagesModel newFeedbackMessagesModel(final Component pageResolvingComponent,
			final IFeedbackMessageFilter messageFilter) {
		return new FeedbackMessagesModel(pageResolvingComponent.getPage(), messageFilter);
	}

	/**
	 * Gets script for displaying toasts with consider level of feedback messages.
	 *
	 * @return script for displaying toasts
	 */
	protected String getScriptForDisplay() {

		List<FeedbackMessage> feedbackMessages = feedbackMessagesModel.getObject();
		if (feedbackMessages.isEmpty()) {
			return "";
		}

		List<IToast> toasts = messageCombiner.combine(toToastStream(feedbackMessages).collect(Collectors.toList()));

		final StringBuilder scripts = new StringBuilder();
		toasts.forEach(toast -> {
			// create script
			scripts.append(getScriptForDisplay(toast));
		});
		return scripts.toString();
	}

	/**
	 * Converts to stream.
	 *
	 * @param feedbackMessages the feedback messages
	 * @return the stream of toast that to display
	 */
	private Stream<IToast> toToastStream(List<FeedbackMessage> feedbackMessages) {
		final IziToastSetting settings = IziToastSetting.get();
		return feedbackMessages.stream()
				.filter(fm -> ToastType.fromFeedbackMessageLevel(fm.getLevel()).isSupported())
				.map(fm -> new Tuple<>(fm, getToast(fm)))
				.map(ft -> setup(ft, settings).y);
	}

	/**
	 * Setup feedback message and toast.
	 *
	 * @param tuple
	 * @param settings
	 * @return
	 */
	private Tuple<FeedbackMessage, IToast> setup(Tuple<FeedbackMessage, IToast> tuple, IziToastSetting settings) {

		final FeedbackMessage feedbackMessage = tuple.x;
		final IToast toast = tuple.y;

		markRendered(feedbackMessage);

		toastTargetLinker.setTarget(toast, feedbackMessage.getReporter());

		applyDefaultOption(toast, settings);

		return tuple;
	}

	/**
	 * Gets a toast that created from {@link FeedbackMessage}.
	 *
	 * @param feedbackMessage The material for creates a toast
	 * @return The toast object
	 */
	protected IToast getToast(final FeedbackMessage feedbackMessage) {
		if (feedbackMessage.getMessage() instanceof IToast) {
			// use feedback message
			return (IToast) feedbackMessage.getMessage();
		} else {
			// create new one
			Toast toast = Toast.create(feedbackMessage);
			return toast;
		}
	}

	/**
	 * Applies default option to toast.
	 *
	 * @param toast the toast
	 * @return toast
	 */
	private void applyDefaultOption(IToast toast, IziToastSetting settings) {
		EachLevelToastOptions defaultOptions = settings.getGlobalEachLevelOptions();
		defaultOptions.get(toast.getToastType()).ifPresent(option -> {
			ToastOption mearged = ToastOption.createMerged(option, toast.getToastOption());
			toast.getToastOption().merge(mearged);
		});
	}

	/**
	 * Gets script for setting iziToast option.
	 *
	 * @param option the option to specify toast styles, behaviors...
	 * @return script for setting iziToast option
	 */
	protected String getScriptForSettingOption(final IToastOption option) {
		return "iziToast.settings(" + option.toJsonString() + ");";
	}

	/**
	 * Gets script for displaying toast.
	 *
	 * @param toast the target to display
	 * @return script for displaying toast
	 */
	protected String getScriptForDisplay(final IToast toast) {
		return toast.getScriptForDisplay().toString();
	}

	/**
	 * Marks argument's message as rendered.
	 *
	 * @param feedbackMessage the target to mark as already rendered
	 */
	protected void markRendered(final FeedbackMessage feedbackMessage) {
		feedbackMessage.markRendered();
	}

}
