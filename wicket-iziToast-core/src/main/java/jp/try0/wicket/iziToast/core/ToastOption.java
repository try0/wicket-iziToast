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
	@ToastOptionValue(value = "imageWidth", squeezeWithDoubleQuotes = false)
	Integer imageWidth;
	@ToastOptionValue(value = "maxWidth")
	String maxWidth;
	@ToastOptionValue(value = "zindex")
	String zindex;
	@ToastOptionValue(value = "layout", squeezeWithDoubleQuotes = false)
	Integer layout;
	@ToastOptionValue(value = "balloon", squeezeWithDoubleQuotes = false)
	Boolean balloon;
	@ToastOptionValue(value = "close", squeezeWithDoubleQuotes = false)
	Boolean close;
	@ToastOptionValue(value = "closeOnEscape", squeezeWithDoubleQuotes = false)
	Boolean closeOnEscape;
	@ToastOptionValue(value = "closeOnClick", squeezeWithDoubleQuotes = false)
	Boolean closeOnClick;
	@ToastOptionValue(value = "displayMode", squeezeWithDoubleQuotes = false)
	Integer displayMode;
	@ToastOptionValue(value = "position")
	String position;
	@ToastOptionValue(value = "target")
	String target;
	@ToastOptionValue(value = "targetFirst", squeezeWithDoubleQuotes = false)
	Boolean targetFirst;
	@ToastOptionValue(value = "timeout", squeezeWithDoubleQuotes = false)
	String timeout;
	@ToastOptionValue(value = "rtl", squeezeWithDoubleQuotes = false)
	Boolean rtl;
	@ToastOptionValue(value = "animateInside", squeezeWithDoubleQuotes = false)
	Boolean animateInside;
	@ToastOptionValue(value = "drag", squeezeWithDoubleQuotes = false)
	Boolean drag;
	@ToastOptionValue(value = "pauseOnHover", squeezeWithDoubleQuotes = false)
	Boolean pauseOnHover;
	@ToastOptionValue(value = "resetOnHover", squeezeWithDoubleQuotes = false)
	Boolean resetOnHover;
	@ToastOptionValue(value = "progressBar", squeezeWithDoubleQuotes = false)
	Boolean progressBar;
	@ToastOptionValue(value = "progressBarColor")
	String progressBarColor;
	@ToastOptionValue(value = "progressBarEasing")
	String progressBarEasing;
	@ToastOptionValue(value = "overlay", squeezeWithDoubleQuotes = false)
	Boolean overlay;
	@ToastOptionValue(value = "overlayClose", squeezeWithDoubleQuotes = false)
	Boolean overlayClose;
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
	@ToastOptionValue(value = "buttons", squeezeWithDoubleQuotes = false)
	String buttons;
	@ToastOptionValue(value = "inputs", squeezeWithDoubleQuotes = false)
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

	public Integer getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(Integer imageWidth) {
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

	public Integer getLayout() {
		return layout;
	}

	public void setLayout(Integer layout) {
		this.layout = layout;
	}

	public Boolean getBalloon() {
		return balloon;
	}

	public void setBalloon(Boolean balloon) {
		this.balloon = balloon;
	}

	public Boolean getClose() {
		return close;
	}

	public void setClose(Boolean close) {
		this.close = close;
	}

	public Boolean getCloseOnEscape() {
		return closeOnEscape;
	}

	public void setCloseOnEscape(Boolean closeOnEscape) {
		this.closeOnEscape = closeOnEscape;
	}

	public Boolean getCloseOnClick() {
		return closeOnClick;
	}

	public void setCloseOnClick(Boolean closeOnClick) {
		this.closeOnClick = closeOnClick;
	}

	public Integer getDisplayMode() {
		return displayMode;
	}

	public void setDisplayMode(Integer displayMode) {
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

	public Boolean getTargetFirst() {
		return targetFirst;
	}

	public void setTargetFirst(Boolean targetFirst) {
		this.targetFirst = targetFirst;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public void setTimeout(Integer timeout) {
		if (timeout == null) {
			return;
		}
		this.timeout = String.valueOf(timeout);
	}

	public void setTimeout(Boolean timeout) {
		if (timeout == null) {
			return;
		}
		this.timeout = String.valueOf(timeout);
	}

	public Boolean getRtl() {
		return rtl;
	}

	public void setRtl(Boolean rtl) {
		this.rtl = rtl;
	}

	public Boolean getAnimateInside() {
		return animateInside;
	}

	public void setAnimateInside(Boolean animateInside) {
		this.animateInside = animateInside;
	}

	public Boolean getDrag() {
		return drag;
	}

	public void setDrag(Boolean drag) {
		this.drag = drag;
	}

	public Boolean getPauseOnHover() {
		return pauseOnHover;
	}

	public void setPauseOnHover(Boolean pauseOnHover) {
		this.pauseOnHover = pauseOnHover;
	}

	public Boolean getResetOnHover() {
		return resetOnHover;
	}

	public void setResetOnHover(Boolean resetOnHover) {
		this.resetOnHover = resetOnHover;
	}

	public Boolean getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(Boolean progressBar) {
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

	public Boolean getOverlay() {
		return overlay;
	}

	public void setOverlay(Boolean overlay) {
		this.overlay = overlay;
	}

	public Boolean getOverlayClose() {
		return overlayClose;
	}

	public void setOverlayClose(Boolean overlayClose) {
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

		newOption.setBackgroundColor(option.getBackgroundColor() == null ? this.backgroundColor : option.getBackgroundColor());

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

		newOption.setProgressBarColor(option.getProgressBarColor() == null ? this.progressBarColor : option.getProgressBarColor());

		newOption.setProgressBarEasing(option.getProgressBarEasing() == null ? this.progressBarEasing : option.getProgressBarEasing());

		newOption.setOverlay(option.getOverlay() == null ? this.overlay : option.getOverlay());

		newOption.setOverlayClose(option.getOverlayClose() == null ? this.overlayClose : option.getOverlayClose());

		newOption.setOverlayColor(option.getOverlayColor() == null ? this.overlayColor : option.getOverlayColor());

		newOption.setTransitionIn(option.getTransitionIn() == null ? this.transitionIn : option.getTransitionIn());

		newOption.setTransitionOut(option.getTransitionOut() == null ? this.transitionOut : option.getTransitionOut());

		newOption.setTransitionInMobile(option.getTransitionInMobile() == null ? this.transitionInMobile : option.getTransitionInMobile());

		newOption.setTransitionOutMobile(option.getTransitionOutMobile() == null ? this.transitionOutMobile : option.getTransitionOutMobile());

		newOption.setButtons(option.getButtons() == null ? this.buttons : option.getButtons());

		newOption.setInputs(option.getInputs() == null ? this.inputs : option.getInputs());

		newOption.setOnOpening(option.getOnOpening() == null ? this.onOpening : option.getOnOpening());

		newOption.setOnOpened(option.getOnOpened() == null ? this.onOpened : option.getOnOpened());

		newOption.setOnClosing(option.getOnClosing() == null ? this.onClosing : option.getOnClosing());

		newOption.setOnClosed(option.getOnClosed() == null ? this.onClosed : option.getOnClosed());

		return newOption;
	}

}
