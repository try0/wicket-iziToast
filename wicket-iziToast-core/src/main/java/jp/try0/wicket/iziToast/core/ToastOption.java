package jp.try0.wicket.iziToast.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * Toast options.<br>
 *
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastOption implements IToastOption {
	private static final long serialVersionUID = 1L;

	/**
	 * Option name and config.
	 *
	 * @author Ryo Tsunoda
	 *
	 */
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	private static @interface ToastOptionValue {

		/**
		 * Option key name.
		 *
		 * @return option key
		 */
		String value();

		/**
		 * Whether or not to squeeze with double quotes. Default is true.
		 *
		 * @return true if an option value needs squeeze with double quotes, otherwise
		 *         false
		 */
		boolean squeezeWithDoubleQuotes() default true;

	}

	/**
	 * Create new one.
	 *
	 * @return new {@link ToastOption}
	 */
	public static ToastOption create() {
		return new ToastOption();
	}

	/**
	 * Constractor
	 */
	public ToastOption() {

	}

	@ToastOptionValue(value = OptionKeys.ID)
	String id;
	@ToastOptionValue(value = OptionKeys.CLASS)
	String styleClass;
	@ToastOptionValue(value = OptionKeys.TITLE)
	String title;
	@ToastOptionValue(value = OptionKeys.TITLE_COLOR)
	String titleColor;
	@ToastOptionValue(value = OptionKeys.TITLE_SIZE)
	String titleSize;
	@ToastOptionValue(value = OptionKeys.TITLE_LINE_HEIGHT)
	String titleLineHeight;
	@ToastOptionValue(value = OptionKeys.MESSAGE)
	String message;
	@ToastOptionValue(value = OptionKeys.MESSAGE_COLOR)
	String messageColor;
	@ToastOptionValue(value = OptionKeys.MESSAGE_SIZE)
	String messageSize;
	@ToastOptionValue(value = OptionKeys.MESSAGE_LINE_HEIGHT)
	String messageLineHeight;
	@ToastOptionValue(value = OptionKeys.BACKGROUND_COLOR)
	String backgroundColor;
	@ToastOptionValue(value = OptionKeys.THEME)
	String theme;
	@ToastOptionValue(value = OptionKeys.COLOR)
	String color;
	@ToastOptionValue(value = OptionKeys.ICON)
	String icon;
	@ToastOptionValue(value = OptionKeys.ICON_TEXT)
	String iconText;
	@ToastOptionValue(value = OptionKeys.ICON_COLOR)
	String iconColor;
	@ToastOptionValue(value = OptionKeys.ICON_URL)
	String iconUrl;
	@ToastOptionValue(value = OptionKeys.IMAGE)
	String image;
	@ToastOptionValue(value = OptionKeys.IMAGE_WIDTH, squeezeWithDoubleQuotes = false)
	Integer imageWidth;
	@ToastOptionValue(value = OptionKeys.MAX_WIDTH)
	String maxWidth;
	@ToastOptionValue(value = OptionKeys.ZINDEX)
	String zindex;
	@ToastOptionValue(value = OptionKeys.LAYOUT, squeezeWithDoubleQuotes = false)
	Integer layout;
	@ToastOptionValue(value = OptionKeys.BALLOON, squeezeWithDoubleQuotes = false)
	Boolean balloon;
	@ToastOptionValue(value = OptionKeys.CLOSE, squeezeWithDoubleQuotes = false)
	Boolean close;
	@ToastOptionValue(value = OptionKeys.CLOSE_ON_ESCAPE, squeezeWithDoubleQuotes = false)
	Boolean closeOnEscape;
	@ToastOptionValue(value = OptionKeys.CLOSE_ON_CLICK, squeezeWithDoubleQuotes = false)
	Boolean closeOnClick;
	@ToastOptionValue(value = OptionKeys.DISPLAY_MODE, squeezeWithDoubleQuotes = false)
	Integer displayMode;
	@ToastOptionValue(value = OptionKeys.POSITION)
	String position;
	@ToastOptionValue(value = OptionKeys.TARGET)
	String target;
	@ToastOptionValue(value = OptionKeys.TARGET_FIRST, squeezeWithDoubleQuotes = false)
	Boolean targetFirst;
	@ToastOptionValue(value = OptionKeys.TIMEOUT, squeezeWithDoubleQuotes = false)
	String timeout;
	@ToastOptionValue(value = OptionKeys.RTL, squeezeWithDoubleQuotes = false)
	Boolean rtl;
	@ToastOptionValue(value = OptionKeys.ANIMATE_INSIDE, squeezeWithDoubleQuotes = false)
	Boolean animateInside;
	@ToastOptionValue(value = OptionKeys.DRAG, squeezeWithDoubleQuotes = false)
	Boolean drag;
	@ToastOptionValue(value = OptionKeys.PAUSE_ON_HOVER, squeezeWithDoubleQuotes = false)
	Boolean pauseOnHover;
	@ToastOptionValue(value = OptionKeys.RESET_ON_HOVER, squeezeWithDoubleQuotes = false)
	Boolean resetOnHover;
	@ToastOptionValue(value = OptionKeys.PROGRESS_BAR, squeezeWithDoubleQuotes = false)
	Boolean progressBar;
	@ToastOptionValue(value = OptionKeys.PROGRESS_BAR_COLOR)
	String progressBarColor;
	@ToastOptionValue(value = OptionKeys.PROGRESS_BAR_EASING)
	String progressBarEasing;
	@ToastOptionValue(value = OptionKeys.OVERLAY, squeezeWithDoubleQuotes = false)
	Boolean overlay;
	@ToastOptionValue(value = OptionKeys.OVERLAY_CLOSE, squeezeWithDoubleQuotes = false)
	Boolean overlayClose;
	@ToastOptionValue(value = OptionKeys.OVERLAY_COLOR)
	String overlayColor;
	@ToastOptionValue(value = OptionKeys.TRANSITION_IN)
	String transitionIn;
	@ToastOptionValue(value = OptionKeys.TRANSITION_OUT)
	String transitionOut;
	@ToastOptionValue(value = OptionKeys.TRANSITION_IN_MOBILE)
	String transitionInMobile;
	@ToastOptionValue(value = OptionKeys.TRANSITION_OUT_MOBILE)
	String transitionOutMobile;
	@ToastOptionValue(value = OptionKeys.BUTTONS, squeezeWithDoubleQuotes = false)
	String buttons;
	@ToastOptionValue(value = OptionKeys.INPUTS, squeezeWithDoubleQuotes = false)
	String inputs;
	@ToastOptionValue(value = OptionKeys.ON_OPENING, squeezeWithDoubleQuotes = false)
	String onOpening;
	@ToastOptionValue(value = OptionKeys.ON_OPENED, squeezeWithDoubleQuotes = false)
	String onOpened;
	@ToastOptionValue(value = OptionKeys.ON_CLOSING, squeezeWithDoubleQuotes = false)
	String onClosing;
	@ToastOptionValue(value = OptionKeys.ON_CLOSED, squeezeWithDoubleQuotes = false)
	String onClosed;

	@Override
	public String getId() {
		return id;
	}

	public ToastOption setId(String id) {
		this.id = id;
		return this;
	}

	@Override
	public String getStyleClass() {
		return styleClass;
	}

	public ToastOption setStyleClass(String styleClass) {
		this.styleClass = styleClass;
		return this;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public ToastOption setTitle(String title) {
		this.title = title;
		return this;
	}

	@Override
	public String getTitleColor() {
		return titleColor;
	}

	public ToastOption setTitleColor(String titleColor) {
		this.titleColor = titleColor;
		return this;
	}

	@Override
	public String getTitleSize() {
		return titleSize;
	}

	public ToastOption setTitleSize(String titleSize) {
		this.titleSize = titleSize;
		return this;
	}

	@Override
	public String getTitleLineHeight() {
		return titleLineHeight;
	}

	public ToastOption setTitleLineHeight(String titleLineHeight) {
		this.titleLineHeight = titleLineHeight;
		return this;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public ToastOption setMessage(String message) {
		this.message = message;
		return this;
	}

	@Override
	public String getMessageColor() {
		return messageColor;
	}

	public ToastOption setMessageColor(String messageColor) {
		this.messageColor = messageColor;
		return this;
	}

	@Override
	public String getMessageSize() {
		return messageSize;
	}

	public ToastOption setMessageSize(String messageSize) {
		this.messageSize = messageSize;
		return this;
	}

	@Override
	public String getMessageLineHeight() {
		return messageLineHeight;
	}

	public ToastOption setMessageLineHeight(String messageLineHeight) {
		this.messageLineHeight = messageLineHeight;
		return this;
	}

	@Override
	public String getBackgroundColor() {
		return backgroundColor;
	}

	public ToastOption setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
		return this;
	}

	@Override
	public String getTheme() {
		return theme;
	}

	public ToastOption setTheme(String theme) {
		this.theme = theme;
		return this;
	}

	@Override
	public String getColor() {
		return color;
	}

	public ToastOption setColor(String color) {
		this.color = color;
		return this;
	}

	@Override
	public String getIcon() {
		return icon;
	}

	public ToastOption setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	@Override
	public String getIconText() {
		return iconText;
	}

	public ToastOption setIconText(String iconText) {
		this.iconText = iconText;
		return this;
	}

	@Override
	public String getIconColor() {
		return iconColor;
	}

	public ToastOption setIconColor(String iconColor) {
		this.iconColor = iconColor;
		return this;
	}

	@Override
	public String getIconUrl() {
		return iconUrl;
	}

	public ToastOption setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
		return this;
	}

	@Override
	public String getImage() {
		return image;
	}

	public ToastOption setImage(String image) {
		this.image = image;
		return this;
	}

	@Override
	public Integer getImageWidth() {
		return imageWidth;
	}

	public ToastOption setImageWidth(Integer imageWidth) {
		this.imageWidth = imageWidth;
		return this;
	}

	@Override
	public String getMaxWidth() {
		return maxWidth;
	}

	public ToastOption setMaxWidth(String maxWidth) {
		this.maxWidth = maxWidth;
		return this;
	}

	@Override
	public String getZindex() {
		return zindex;
	}

	public ToastOption setZindex(String zindex) {
		this.zindex = zindex;
		return this;
	}

	@Override
	public Integer getLayout() {
		return layout;
	}

	public ToastOption setLayout(Integer layout) {
		this.layout = layout;
		return this;
	}

	@Override
	public Boolean getBalloon() {
		return balloon;
	}

	public ToastOption setBalloon(Boolean balloon) {
		this.balloon = balloon;
		return this;
	}

	@Override
	public Boolean getClose() {
		return close;
	}

	public ToastOption setClose(Boolean close) {
		this.close = close;
		return this;
	}

	@Override
	public Boolean getCloseOnEscape() {
		return closeOnEscape;
	}

	public ToastOption setCloseOnEscape(Boolean closeOnEscape) {
		this.closeOnEscape = closeOnEscape;
		return this;
	}

	@Override
	public Boolean getCloseOnClick() {
		return closeOnClick;
	}

	public ToastOption setCloseOnClick(Boolean closeOnClick) {
		this.closeOnClick = closeOnClick;
		return this;
	}

	@Override
	public Integer getDisplayMode() {
		return displayMode;
	}

	public ToastOption setDisplayMode(Integer displayMode) {
		this.displayMode = displayMode;
		return this;
	}

	@Override
	public String getPosition() {
		return position;
	}

	public ToastOption setPosition(String position) {
		this.position = position;
		return this;
	}

	@Override
	public String getTarget() {
		return target;
	}

	public ToastOption setTarget(String target) {
		this.target = target;
		return this;
	}

	@Override
	public Boolean getTargetFirst() {
		return targetFirst;
	}

	public ToastOption setTargetFirst(Boolean targetFirst) {
		this.targetFirst = targetFirst;
		return this;
	}

	@Override
	public String getTimeout() {
		return timeout;
	}

	public ToastOption setTimeout(String timeout) {
		this.timeout = timeout;
		return this;
	}

	public ToastOption setTimeout(Integer timeout) {
		if (timeout == null) {
			this.timeout = null;
			return this;
		}
		this.timeout = String.valueOf(timeout);
		return this;
	}

	public ToastOption setTimeout(Boolean timeout) {
		if (timeout == null) {
			this.timeout = null;
			return this;
		}
		this.timeout = String.valueOf(timeout);
		return this;
	}

	@Override
	public Boolean getRtl() {
		return rtl;
	}

	public ToastOption setRtl(Boolean rtl) {
		this.rtl = rtl;
		return this;
	}

	@Override
	public Boolean getAnimateInside() {
		return animateInside;
	}

	public ToastOption setAnimateInside(Boolean animateInside) {
		this.animateInside = animateInside;
		return this;
	}

	@Override
	public Boolean getDrag() {
		return drag;
	}

	public ToastOption setDrag(Boolean drag) {
		this.drag = drag;
		return this;
	}

	@Override
	public Boolean getPauseOnHover() {
		return pauseOnHover;
	}

	public ToastOption setPauseOnHover(Boolean pauseOnHover) {
		this.pauseOnHover = pauseOnHover;
		return this;
	}

	@Override
	public Boolean getResetOnHover() {
		return resetOnHover;
	}

	public ToastOption setResetOnHover(Boolean resetOnHover) {
		this.resetOnHover = resetOnHover;
		return this;
	}

	@Override
	public Boolean getProgressBar() {
		return progressBar;
	}

	public ToastOption setProgressBar(Boolean progressBar) {
		this.progressBar = progressBar;
		return this;
	}

	@Override
	public String getProgressBarColor() {
		return progressBarColor;
	}

	public ToastOption setProgressBarColor(String progressBarColor) {
		this.progressBarColor = progressBarColor;
		return this;
	}

	@Override
	public String getProgressBarEasing() {
		return progressBarEasing;
	}

	public ToastOption setProgressBarEasing(String progressBarEasing) {
		this.progressBarEasing = progressBarEasing;
		return this;
	}

	@Override
	public Boolean getOverlay() {
		return overlay;
	}

	public ToastOption setOverlay(Boolean overlay) {
		this.overlay = overlay;
		return this;
	}

	@Override
	public Boolean getOverlayClose() {
		return overlayClose;
	}

	public ToastOption setOverlayClose(Boolean overlayClose) {
		this.overlayClose = overlayClose;
		return this;
	}

	@Override
	public String getOverlayColor() {
		return overlayColor;
	}

	public ToastOption setOverlayColor(String overlayColor) {
		this.overlayColor = overlayColor;
		return this;
	}

	@Override
	public String getTransitionIn() {
		return transitionIn;
	}

	public ToastOption setTransitionIn(String transitionIn) {
		this.transitionIn = transitionIn;
		return this;
	}

	@Override
	public String getTransitionOut() {
		return transitionOut;
	}

	public ToastOption setTransitionOut(String transitionOut) {
		this.transitionOut = transitionOut;
		return this;
	}

	@Override
	public String getTransitionInMobile() {
		return transitionInMobile;
	}

	public ToastOption setTransitionInMobile(String transitionInMobile) {
		this.transitionInMobile = transitionInMobile;
		return this;
	}

	@Override
	public String getTransitionOutMobile() {
		return transitionOutMobile;
	}

	public ToastOption setTransitionOutMobile(String transitionOutMobile) {
		this.transitionOutMobile = transitionOutMobile;
		return this;
	}

	@Override
	public String getButtons() {
		return buttons;
	}

	public ToastOption setButtons(String buttons) {
		this.buttons = buttons;
		return this;
	}

	@Override
	public String getInputs() {
		return inputs;
	}

	public ToastOption setInputs(String inputs) {
		this.inputs = inputs;
		return this;
	}

	@Override
	public String getOnOpening() {
		return onOpening;
	}

	public ToastOption setOnOpening(String onOpening) {
		this.onOpening = onOpening;
		return this;
	}

	@Override
	public String getOnOpened() {
		return onOpened;
	}

	public ToastOption setOnOpened(String onOpened) {
		this.onOpened = onOpened;
		return this;
	}

	@Override
	public String getOnClosing() {
		return onClosing;
	}

	public ToastOption setOnClosing(String onClosing) {
		this.onClosing = onClosing;
		return this;
	}

	@Override
	public String getOnClosed() {
		return onClosed;
	}

	public ToastOption setOnClosed(String onClosed) {
		this.onClosed = onClosed;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toJsonString() {
		StringBuilder sb = new StringBuilder("{");

		for (Field field : this.getClass().getDeclaredFields()) {

			ToastOptionValue option = field.getAnnotation(ToastOptionValue.class);
			if (option == null) {
				continue;
			}

			field.setAccessible(true);

			Object optionValue;
			try {
				optionValue = field.get(this);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}

			if (optionValue != null) {

				sb.append("\"").append(option.value()).append("\":");
				if (option.squeezeWithDoubleQuotes()) {
					sb.append("\"").append(optionValue.toString()).append("\",");
				} else {
					sb.append(optionValue.toString()).append(",");
				}
			}

		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ToastOption overwrite(IToastOption option) {
		ToastOption newOption = new ToastOption();

		newOption.setId(option.getId() == null ? this.id : option.getId());

		newOption.setStyleClass(option.getStyleClass() == null ? this.styleClass : option.getStyleClass());

		newOption.setTitle(option.getTitle() == null ? this.title : option.getTitle());

		newOption.setTitleColor(option.getTitleColor() == null ? this.titleColor : option.getTitleColor());

		newOption.setTitleSize(option.getTitleSize() == null ? this.titleSize : option.getTitleSize());

		newOption.setTitleLineHeight(
				option.getTitleLineHeight() == null ? this.titleLineHeight : option.getTitleLineHeight());

		newOption.setMessage(option.getMessage() == null ? this.message : option.getMessage());

		newOption.setMessageColor(option.getMessageColor() == null ? this.messageColor : option.getMessageColor());

		newOption.setMessageSize(option.getMessageSize() == null ? this.messageSize : option.getMessageSize());

		newOption.setMessageLineHeight(
				option.getMessageLineHeight() == null ? this.messageLineHeight : option.getMessageLineHeight());

		newOption.setBackgroundColor(
				option.getBackgroundColor() == null ? this.backgroundColor : option.getBackgroundColor());

		newOption.setTheme(option.getTheme() == null ? this.theme : option.getTheme());

		newOption.setColor(option.getColor() == null ? this.color : option.getColor());

		newOption.setIcon(option.getIcon() == null ? this.icon : option.getIcon());

		newOption.setIconText(option.getIconText() == null ? this.iconText : option.getIconText());

		newOption.setIconColor(option.getIconColor() == null ? this.iconColor : option.getIconColor());

		newOption.setIconUrl(option.getIconUrl() == null ? this.iconUrl : option.getIconUrl());

		newOption.setImage(option.getImage() == null ? this.image : option.getImage());

		newOption.setImageWidth(option.getImageWidth() == null ? this.imageWidth : option.getImageWidth());

		newOption.setMaxWidth(option.getMaxWidth() == null ? this.maxWidth : option.getMaxWidth());

		newOption.setZindex(option.getZindex() == null ? this.zindex : option.getZindex());

		newOption.setLayout(option.getLayout() == null ? this.layout : option.getLayout());

		newOption.setBalloon(option.getBalloon() == null ? this.balloon : option.getBalloon());

		newOption.setClose(option.getClose() == null ? this.close : option.getClose());

		newOption.setCloseOnEscape(option.getCloseOnEscape() == null ? this.closeOnEscape : option.getCloseOnEscape());

		newOption.setCloseOnClick(option.getCloseOnClick() == null ? this.closeOnClick : option.getCloseOnClick());

		newOption.setDisplayMode(option.getDisplayMode() == null ? this.displayMode : option.getDisplayMode());

		newOption.setPosition(option.getPosition() == null ? this.position : option.getPosition());

		newOption.setTarget(option.getTarget() == null ? this.target : option.getTarget());

		newOption.setTargetFirst(option.getTargetFirst() == null ? this.targetFirst : option.getTargetFirst());

		newOption.setTimeout(option.getTimeout() == null ? this.timeout : option.getTimeout());

		newOption.setRtl(option.getRtl() == null ? this.rtl : option.getRtl());

		newOption.setAnimateInside(option.getAnimateInside() == null ? this.animateInside : option.getAnimateInside());

		newOption.setDrag(option.getDrag() == null ? this.drag : option.getDrag());

		newOption.setPauseOnHover(option.getPauseOnHover() == null ? this.pauseOnHover : option.getPauseOnHover());

		newOption.setResetOnHover(option.getResetOnHover() == null ? this.resetOnHover : option.getResetOnHover());

		newOption.setProgressBar(option.getProgressBar() == null ? this.progressBar : option.getProgressBar());

		newOption.setProgressBarColor(
				option.getProgressBarColor() == null ? this.progressBarColor : option.getProgressBarColor());

		newOption.setProgressBarEasing(
				option.getProgressBarEasing() == null ? this.progressBarEasing : option.getProgressBarEasing());

		newOption.setOverlay(option.getOverlay() == null ? this.overlay : option.getOverlay());

		newOption.setOverlayClose(option.getOverlayClose() == null ? this.overlayClose : option.getOverlayClose());

		newOption.setOverlayColor(option.getOverlayColor() == null ? this.overlayColor : option.getOverlayColor());

		newOption.setTransitionIn(option.getTransitionIn() == null ? this.transitionIn : option.getTransitionIn());

		newOption.setTransitionOut(option.getTransitionOut() == null ? this.transitionOut : option.getTransitionOut());

		newOption.setTransitionInMobile(
				option.getTransitionInMobile() == null ? this.transitionInMobile : option.getTransitionInMobile());

		newOption.setTransitionOutMobile(
				option.getTransitionOutMobile() == null ? this.transitionOutMobile : option.getTransitionOutMobile());

		newOption.setButtons(option.getButtons() == null ? this.buttons : option.getButtons());

		newOption.setInputs(option.getInputs() == null ? this.inputs : option.getInputs());

		newOption.setOnOpening(option.getOnOpening() == null ? this.onOpening : option.getOnOpening());

		newOption.setOnOpened(option.getOnOpened() == null ? this.onOpened : option.getOnOpened());

		newOption.setOnClosing(option.getOnClosing() == null ? this.onClosing : option.getOnClosing());

		newOption.setOnClosed(option.getOnClosed() == null ? this.onClosed : option.getOnClosed());

		return newOption;
	}

}
