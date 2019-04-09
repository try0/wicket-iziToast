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

	@ToastOptionValue(value = "id")
	String id;
	@ToastOptionValue(value = "class")
	String styleClass;
	@ToastOptionValue(value = "title")
	String title;
	@ToastOptionValue(value = "titleColor")
	String titleColor;
	@ToastOptionValue(value = "titleSize")
	String titleSize;
	@ToastOptionValue(value = "titleLineHeight")
	String titleLineHeight;
	@ToastOptionValue(value = "message")
	String message;
	@ToastOptionValue(value = "messageColor")
	String messageColor;
	@ToastOptionValue(value = "messageSize")
	String messageSize;
	@ToastOptionValue(value = "messageLineHeight")
	String messageLineHeight;
	@ToastOptionValue(value = "backgroundColor")
	String backgroundColor;
	@ToastOptionValue(value = "theme")
	String theme;
	@ToastOptionValue(value = "color")
	String color;
	@ToastOptionValue(value = "icon")
	String icon;
	@ToastOptionValue(value = "iconText")
	String iconText;
	@ToastOptionValue(value = "iconColor")
	String iconColor;
	@ToastOptionValue(value = "iconUrl")
	String iconUrl;
	@ToastOptionValue(value = "image")
	String image;
	@ToastOptionValue(value = "imageWidth")
	String imageWidth;
	@ToastOptionValue(value = "maxWidth")
	String maxWidth;
	@ToastOptionValue(value = "zindex")
	String zindex;
	@ToastOptionValue(value = "layout")
	String layout;
	@ToastOptionValue(value = "balloon")
	String balloon;
	@ToastOptionValue(value = "close", squeezeWithDoubleQuotes = false)
	String close;
	@ToastOptionValue(value = "closeOnEscape")
	String closeOnEscape;
	@ToastOptionValue(value = "closeOnClick", squeezeWithDoubleQuotes = false)
	String closeOnClick;
	@ToastOptionValue(value = "displayMode")
	String displayMode;
	@ToastOptionValue(value = "position")
	String position;
	@ToastOptionValue(value = "target")
	String target;
	@ToastOptionValue(value = "targetFirst")
	String targetFirst;
	@ToastOptionValue(value = "timeout", squeezeWithDoubleQuotes = false)
	String timeout;
	@ToastOptionValue(value = "rtl")
	String rtl;
	@ToastOptionValue(value = "animateInside")
	String animateInside;
	@ToastOptionValue(value = "drag")
	String drag;
	@ToastOptionValue(value = "pauseOnHover")
	String pauseOnHover;
	@ToastOptionValue(value = "resetOnHover")
	String resetOnHover;
	@ToastOptionValue(value = "progressBar")
	String progressBar;
	@ToastOptionValue(value = "progressBarColor")
	String progressBarColor;
	@ToastOptionValue(value = "progressBarEasing")
	String progressBarEasing;
	@ToastOptionValue(value = "overlay")
	String overlay;
	@ToastOptionValue(value = "overlayClose")
	String overlayClose;
	@ToastOptionValue(value = "overlayColor")
	String overlayColor;
	@ToastOptionValue(value = "transitionIn")
	String transitionIn;
	@ToastOptionValue(value = "transitionOut")
	String transitionOut;
	@ToastOptionValue(value = "transitionInMobile")
	String transitionInMobile;
	@ToastOptionValue(value = "transitionOutMobile")
	String transitionOutMobile;
	@ToastOptionValue(value = "buttons")
	String buttons;
	@ToastOptionValue(value = "inputs")
	String inputs;
	@ToastOptionValue(value = "onOpening", squeezeWithDoubleQuotes = false)
	String onOpening;
	@ToastOptionValue(value = "onOpened", squeezeWithDoubleQuotes = false)
	String onOpened;
	@ToastOptionValue(value = "onClosing", squeezeWithDoubleQuotes = false)
	String onClosing;
	@ToastOptionValue(value = "onClosed", squeezeWithDoubleQuotes = false)
	String onClosed;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	public String getTitleSize() {
		return titleSize;
	}

	public void setTitleSize(String titleSize) {
		this.titleSize = titleSize;
	}

	public String getTitleLineHeight() {
		return titleLineHeight;
	}

	public void setTitleLineHeight(String titleLineHeight) {
		this.titleLineHeight = titleLineHeight;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageColor() {
		return messageColor;
	}

	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}

	public String getMessageSize() {
		return messageSize;
	}

	public void setMessageSize(String messageSize) {
		this.messageSize = messageSize;
	}

	public String getMessageLineHeight() {
		return messageLineHeight;
	}

	public void setMessageLineHeight(String messageLineHeight) {
		this.messageLineHeight = messageLineHeight;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconText() {
		return iconText;
	}

	public void setIconText(String iconText) {
		this.iconText = iconText;
	}

	public String getIconColor() {
		return iconColor;
	}

	public void setIconColor(String iconColor) {
		this.iconColor = iconColor;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(String imageWidth) {
		this.imageWidth = imageWidth;
	}

	public String getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(String maxWidth) {
		this.maxWidth = maxWidth;
	}

	public String getZindex() {
		return zindex;
	}

	public void setZindex(String zindex) {
		this.zindex = zindex;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getBalloon() {
		return balloon;
	}

	public void setBalloon(String balloon) {
		this.balloon = balloon;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public String getCloseOnEscape() {
		return closeOnEscape;
	}

	public void setCloseOnEscape(String closeOnEscape) {
		this.closeOnEscape = closeOnEscape;
	}

	public String getCloseOnClick() {
		return closeOnClick;
	}

	public void setCloseOnClick(String closeOnClick) {
		this.closeOnClick = closeOnClick;
	}

	public String getDisplayMode() {
		return displayMode;
	}

	public void setDisplayMode(String displayMode) {
		this.displayMode = displayMode;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTargetFirst() {
		return targetFirst;
	}

	public void setTargetFirst(String targetFirst) {
		this.targetFirst = targetFirst;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getRtl() {
		return rtl;
	}

	public void setRtl(String rtl) {
		this.rtl = rtl;
	}

	public String getAnimateInside() {
		return animateInside;
	}

	public void setAnimateInside(String animateInside) {
		this.animateInside = animateInside;
	}

	public String getDrag() {
		return drag;
	}

	public void setDrag(String drag) {
		this.drag = drag;
	}

	public String getPauseOnHover() {
		return pauseOnHover;
	}

	public void setPauseOnHover(String pauseOnHover) {
		this.pauseOnHover = pauseOnHover;
	}

	public String getResetOnHover() {
		return resetOnHover;
	}

	public void setResetOnHover(String resetOnHover) {
		this.resetOnHover = resetOnHover;
	}

	public String getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(String progressBar) {
		this.progressBar = progressBar;
	}

	public String getProgressBarColor() {
		return progressBarColor;
	}

	public void setProgressBarColor(String progressBarColor) {
		this.progressBarColor = progressBarColor;
	}

	public String getProgressBarEasing() {
		return progressBarEasing;
	}

	public void setProgressBarEasing(String progressBarEasing) {
		this.progressBarEasing = progressBarEasing;
	}

	public String getOverlay() {
		return overlay;
	}

	public void setOverlay(String overlay) {
		this.overlay = overlay;
	}

	public String getOverlayClose() {
		return overlayClose;
	}

	public void setOverlayClose(String overlayClose) {
		this.overlayClose = overlayClose;
	}

	public String getOverlayColor() {
		return overlayColor;
	}

	public void setOverlayColor(String overlayColor) {
		this.overlayColor = overlayColor;
	}

	public String getTransitionIn() {
		return transitionIn;
	}

	public void setTransitionIn(String transitionIn) {
		this.transitionIn = transitionIn;
	}

	public String getTransitionOut() {
		return transitionOut;
	}

	public void setTransitionOut(String transitionOut) {
		this.transitionOut = transitionOut;
	}

	public String getTransitionInMobile() {
		return transitionInMobile;
	}

	public void setTransitionInMobile(String transitionInMobile) {
		this.transitionInMobile = transitionInMobile;
	}

	public String getTransitionOutMobile() {
		return transitionOutMobile;
	}

	public void setTransitionOutMobile(String transitionOutMobile) {
		this.transitionOutMobile = transitionOutMobile;
	}

	public String getButtons() {
		return buttons;
	}

	public void setButtons(String buttons) {
		this.buttons = buttons;
	}

	public String getInputs() {
		return inputs;
	}

	public void setInputs(String inputs) {
		this.inputs = inputs;
	}

	public String getOnOpening() {
		return onOpening;
	}

	public void setOnOpening(String onOpening) {
		this.onOpening = onOpening;
	}

	public String getOnOpened() {
		return onOpened;
	}

	public void setOnOpened(String onOpened) {
		this.onOpened = onOpened;
	}

	public String getOnClosing() {
		return onClosing;
	}

	public void setOnClosing(String onClosing) {
		this.onClosing = onClosing;
	}

	public String getOnClosed() {
		return onClosed;
	}

	public void setOnClosed(String onClosed) {
		this.onClosed = onClosed;
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
		return null;
	}

}
