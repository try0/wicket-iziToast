package jp.try0.wicket.izitoast.core.config;

import java.util.Optional;
import java.util.function.Supplier;

import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.util.lang.Args;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.try0.wicket.izitoast.core.EachLevelToastOptions;
import jp.try0.wicket.izitoast.core.IToastOption;
import jp.try0.wicket.izitoast.core.ToastOption;
import jp.try0.wicket.izitoast.core.behavior.IziToastBehavior;

/**
 * iziToast settings. This class has configs for using iziToast.
 *
 * @author Ryo Tsunoda
 *
 */
public class IziToastSetting {

	/**
	 * Settings builder.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	public static class IziToastSettingInitializer {

		private static Logger logger = LoggerFactory.getLogger(IziToastSettingInitializer.class);

		/**
		 * Create builder.
		 *
		 * @param application the current application
		 * @return initializer
		 */
		public static IziToastSettingInitializer create(final Application application) {
			return new IziToastSettingInitializer(application);
		}

		/**
		 * Application
		 */
		private final Application application;

		/**
		 * Global option
		 */
		private ToastOption globalOption = null;

		/**
		 * Global options for each toast levels
		 */
		private EachLevelToastOptions globalEachLevelOptions = new EachLevelToastOptions();

		/**
		 * Message Filter
		 */
		private IFeedbackMessageFilter filter = IFeedbackMessageFilter.ALL;

		/**
		 * Need append behavior to new pages
		 */
		private boolean needAutoAppendToastBehavior = false;

		/**
		 * {@link IziToastBehavior} factory
		 */
		private Supplier<IziToastBehavior> iziToastBehaviorFactory = DEFAULT_IZITOAST_BEHAVIOR_FACTORY;

		/**
		 * Message combiner
		 */
		private ToastMessageCombiner toastMessageCombiner = ToastMessageCombiner.VOID_COMBINER;

		/**
		 * Target container setter
		 */
		private IToastTargetLinker toastTargetLinker = DefaultToastTargetLinker.getNullInstance();

		/**
		 * Need append {@link ToastTargetContainerAppender} to {@link FormComponent}
		 */
		private boolean needAutoAppendContainerCreateBehavior = false;

		/**
		 * Constractor
		 *
		 * @param application the current application
		 */
		public IziToastSettingInitializer(Application application) {
			this.application = Args.notNull(application, "application");
		}

		/**
		 * Sets toast global option.
		 *
		 * @param globalOption global option
		 * @return this
		 */
		public IziToastSettingInitializer setGlobalOption(ToastOption globalOption) {
			this.globalOption = globalOption;
			return this;
		}

		/**
		 * Sets toast global options for each levels.
		 *
		 * @param globalEachLevelOptions global options
		 * @return this
		 */
		public IziToastSettingInitializer setGlobalEachLevelOptions(EachLevelToastOptions globalEachLevelOptions) {
			this.globalEachLevelOptions = globalEachLevelOptions;
			return this;
		}

		/**
		 * Sets filter.
		 *
		 * @param filter the filter to apply
		 * @return this
		 */
		public IziToastSettingInitializer setMessageFilter(IFeedbackMessageFilter filter) {
			this.filter = filter;
			return this;
		}

		/**
		 * Sets target linker.
		 *
		 * @param toastTargetLinker
		 * @return
		 */
		public IziToastSettingInitializer setToastTargetLinker(IToastTargetLinker toastTargetLinker) {
			this.toastTargetLinker = toastTargetLinker;
			return this;
		}

		/**
		 * Sets need auto append {@link ToastTargetContainerAppender} to new {@link FormComponent}.
		 *
		 * @param needAutoAppendContainerCreateBehavior whether or not to append {@link ToastTargetContainerAppender} to {@link FormComponent}
		 * @return this
		 */
		public IziToastSettingInitializer setAutoAppendContainerCreateBehavior(
				boolean needAutoAppendContainerCreateBehavior) {
			this.needAutoAppendContainerCreateBehavior = needAutoAppendContainerCreateBehavior;
			return this;
		}

		/**
		 * Sets need auto append {@link IziToastBehavior} to new {@link Page}.
		 *
		 * @param needAutoAppendToastBehavior whether or not to append {@link IziToastBehavior} to new page
		 * @return this
		 */
		public IziToastSettingInitializer setAutoAppendBehavior(boolean needAutoAppendToastBehavior) {
			this.needAutoAppendToastBehavior = needAutoAppendToastBehavior;
			return this;
		}

		/**
		 * Sets {@link IziToastBehavior} factory.
		 *
		 * @param iziToastBehaviorFactory factory of {@link IziToastBehavior}
		 * @return this
		 */
		public IziToastSettingInitializer setIziToastBehaviorFactory(
				Supplier<IziToastBehavior> iziToastBehaviorFactory) {
			this.iziToastBehaviorFactory = iziToastBehaviorFactory;
			return this;
		}

		/**
		 * Sets {@link ToastMessageCombiner}.
		 *
		 * @param toastMessageCombiner combiner that combines messages for each toast level
		 * @return this
		 */
		public IziToastSettingInitializer setToastMessageCombiner(ToastMessageCombiner toastMessageCombiner) {
			this.toastMessageCombiner = toastMessageCombiner;
			return this;
		}

		/**
		 * Initialize iziToast settings.
		 *
		 * @return setting
		 */
		public IziToastSetting initialize() {

			if (application.getMetaData(META_DATA_KEY) != null) {
				throw new UnsupportedOperationException(
						"The setting has already been initialized. IziToastSettings#initialize can only be called once.");
			}

			if (needAutoAppendToastBehavior) {
				application.getComponentInstantiationListeners().add(new IziToastBehaviorAutoAppender());
			}

			if (needAutoAppendContainerCreateBehavior) {
				application.getComponentInstantiationListeners().add(new ToastTargetContainerAppender());
			}

			IziToastSetting settings = new IziToastSetting(this);

			application.setMetaData(META_DATA_KEY, settings);

			logger.info("Initialized IziToastSetting");
			return settings;
		}

	}

	/**
	 * Key of {@link IziToastSetting} instance.
	 */
	private static final MetaDataKey<IziToastSetting> META_DATA_KEY = new MetaDataKey<IziToastSetting>() {
	};

	/**
	 * Default {@link IziToastBehavior} factory.
	 */
	private static final Supplier<IziToastBehavior> DEFAULT_IZITOAST_BEHAVIOR_FACTORY = () -> new IziToastBehavior();

	/**
	 * Creates settings builder.
	 *
	 * @param application the current application
	 * @return initializer
	 */
	public static IziToastSettingInitializer createInitializer(final Application application) {
		return IziToastSettingInitializer.create(application);
	}

	/**
	 * Sets up default values.
	 *
	 * @return iziToast settings
	 */
	private static IziToastSetting initialize() {

		if (!Application.exists()) {
			throw new UnsupportedOperationException("Application is not exisits.");
		}

		final Application application = Application.get();
		boolean needAutoAppendToastBehavior = false;

		return IziToastSetting.createInitializer(application)
				.setAutoAppendBehavior(needAutoAppendToastBehavior)
				.initialize();
	}

	/**
	 * Gets instance of iziToast settings.<br>
	 * If settings has already been initialized, returns it, otherwise first initialize setting and returns it.
	 *
	 * @return iziToast settings
	 */
	public static IziToastSetting get() {
		if (!Application.exists()) {
			throw new IllegalStateException("There is no active application.");
		}

		Application application = Application.get();
		IziToastSetting settings = application.getMetaData(META_DATA_KEY);

		if (settings != null) {
			return settings;
		}

		return IziToastSetting.initialize();
	}

	/**
	 * Global option
	 */
	private final Optional<IToastOption> globalOption;

	/**
	 * Global options for each toast levels
	 */
	private final EachLevelToastOptions globalEachLevelOptions;

	/**
	 * Message Filter
	 */
	private final Optional<IFeedbackMessageFilter> filter;

	/**
	 * {@link IziToastBehavior} factory
	 */
	private final Supplier<IziToastBehavior> iziToastBehaviorFactory;

	/**
	 * Message combiner
	 */
	private final ToastMessageCombiner toastMessageCombiner;

	/**
	 * Toast target linker
	 */
	private final IToastTargetLinker toastTargetLinker;

	/**
	 * Constractor
	 */
	private IziToastSetting() {
		this.globalOption = Optional.empty();
		this.globalEachLevelOptions = new EachLevelToastOptions();
		this.filter = Optional.empty();
		this.iziToastBehaviorFactory = DEFAULT_IZITOAST_BEHAVIOR_FACTORY;
		this.toastMessageCombiner = ToastMessageCombiner.VOID_COMBINER;
		this.toastTargetLinker = DefaultToastTargetLinker.getNullInstance();
	}

	/**
	 * Constractor
	 * @param initializer the initializer for set default values
	 */
	private IziToastSetting(IziToastSettingInitializer initializer) {
		this.globalOption = Optional.ofNullable(initializer.globalOption);
		this.globalEachLevelOptions = Args.notNull(initializer.globalEachLevelOptions, "globalEachLevelOptions");
		this.filter = Optional.ofNullable(initializer.filter);
		this.iziToastBehaviorFactory = Args.notNull(initializer.iziToastBehaviorFactory, "iziToastBehaviorFactory");
		this.toastMessageCombiner = Args.notNull(initializer.toastMessageCombiner, "toastMessageCombiner");
		this.toastTargetLinker = Args.notNull(initializer.toastTargetLinker, "toastTargetLinker");
	}

	/**
	 * Gets default toast option.
	 *
	 * @return toast option
	 */
	public Optional<IToastOption> getGlobalOption() {
		return globalOption;
	}

	/**
	 * Gets default toast options for each levels.
	 *
	 * @return toast options
	 */
	public EachLevelToastOptions getGlobalEachLevelOptions() {
		return globalEachLevelOptions;
	}

	/**
	 * Gets whether has global options.
	 *
	 * @return true if settings has global options, otherwise false
	 */
	public boolean hasGlobalOptions() {
		return globalOption.isPresent();
	}

	/**
	 * Gets filter.
	 *
	 * @return the filter to apply
	 */
	public Optional<IFeedbackMessageFilter> getMessageFilter() {
		return filter;
	}

	/**
	 * Gets whether has feedback message filter.
	 *
	 * @return true if settings has filter, otherwise false
	 */
	public boolean hasMessageFilter() {
		return filter.isPresent();
	}

	/**
	 * Gets {@link IziToastBehavior} factory.
	 *
	 * @return the {@link IziToastBehavior} factory
	 */
	public Supplier<IziToastBehavior> getIziToastBehaviorFactory() {
		return () -> {
			IziToastBehavior behavior = iziToastBehaviorFactory.get();
			behavior.setMessageCombiner(toastMessageCombiner);
			getMessageFilter().ifPresent(filter -> behavior.setMessageFilter(filter));
			return behavior;
		};
	}

	/**
	 * Gets message cobiner.
	 *
	 * @return the combiner that combines messages for each toast level
	 */
	public ToastMessageCombiner getToastMessageCombiner() {
		return toastMessageCombiner;
	}

	/**
	 * Gets target container linker
	 *
	 * @return the setter
	 */
	public IToastTargetLinker getToastTargetLinker() {
		return toastTargetLinker;
	}

}
