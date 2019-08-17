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
	 * Option names.
	 */
	public static class OptionKeys {
		public static final String ID = "id";
		public static final String CLASS = "class";
		public static final String TITLE = "title";
		public static final String TITLE_COLOR = "titleColor";
		public static final String TITLE_SIZE = "titleSize";
		public static final String TITLE_LINE_HEIGHT = "titleLineHeight";
		public static final String MESSAGE = "message";
		public static final String MESSAGE_COLOR = "messageColor";
		public static final String MESSAGE_SIZE = "messageSize";
		public static final String MESSAGE_LINE_HEIGHT = "messageLineHeight";
		public static final String BACKGROUND_COLOR = "backgroundColor";
		public static final String THEME = "theme";
		public static final String COLOR = "color";
		public static final String ICON = "icon";
		public static final String ICON_TEXT = "iconText";
		public static final String ICON_COLOR = "iconColor";
		public static final String ICON_URL = "iconUrl";
		public static final String IMAGE = "image";
		public static final String IMAGE_WIDTH = "imageWidth";
		public static final String MAX_WIDTH = "maxWidth";
		public static final String ZINDEX = "zindex";
		public static final String LAYOUT = "layout";
		public static final String BALLOON = "balloon";
		public static final String CLOSE = "close";
		public static final String CLOSE_ON_ESCAPE = "closeOnEscape";
		public static final String CLOSE_ON_CLICK = "closeOnClick";
		public static final String DISPLAY_MODE = "displayMode";
		public static final String POSITION = "position";
		public static final String TARGET = "target";
		public static final String TARGET_FIRST = "targetFirst";
		public static final String TIMEOUT = "timeout";
		public static final String RTL = "rtl";
		public static final String ANIMATE_INSIDE = "animateInside";
		public static final String DRAG = "drag";
		public static final String PAUSE_ON_HOVER = "pauseOnHover";
		public static final String RESET_ON_HOVER = "resetOnHover";
		public static final String PROGRESS_BAR = "progressBar";
		public static final String PROGRESS_BAR_COLOR = "progressBarColor";
		public static final String PROGRESS_BAR_EASING = "progressBarEasing";
		public static final String OVERLAY = "overlay";
		public static final String OVERLAY_CLOSE = "overlayClose";
		public static final String OVERLAY_COLOR = "overlayColor";
		public static final String TRANSITION_IN = "transitionIn";
		public static final String TRANSITION_OUT = "transitionOut";
		public static final String TRANSITION_IN_MOBILE = "transitionInMobile";
		public static final String TRANSITION_OUT_MOBILE = "transitionOutMobile";
		public static final String BUTTONS = "buttons";
		public static final String INPUTS = "inputs";
		public static final String ON_OPENING = "onOpening";
		public static final String ON_OPENED = "onOpened";
		public static final String ON_CLOSING = "onClosing";
		public static final String ON_CLOSED = "onClosed";
	}

	public String getId();

	public String getStyleClass();

	public String getTitle();

	public String getTitleColor();

	public String getTitleSize();

	public String getTitleLineHeight();

	public String getMessage();

	public String getMessageColor();

	public String getMessageSize();

	public String getMessageLineHeight();

	public String getBackgroundColor();

	public String getTheme();

	public String getColor();

	public String getIcon();

	public String getIconText();

	public String getIconColor();

	public String getIconUrl();

	public String getImage();

	public Integer getImageWidth();

	public String getMaxWidth();

	public String getZindex();

	public Integer getLayout();

	public Boolean getBalloon();

	public Boolean getClose();

	public Boolean getCloseOnEscape();

	public Boolean getCloseOnClick();

	public Integer getDisplayMode();

	public String getPosition();

	public String getTarget();

	public Boolean getTargetFirst();

	public String getTimeout();

	public Boolean getRtl();

	public Boolean getAnimateInside();

	public Boolean getDrag();

	public Boolean getPauseOnHover();

	public Boolean getResetOnHover();

	public Boolean getProgressBar();

	public String getProgressBarColor();

	public String getProgressBarEasing();

	public Boolean getOverlay();

	public Boolean getOverlayClose();

	public String getOverlayColor();

	public String getTransitionIn();

	public String getTransitionOut();

	public String getTransitionInMobile();

	public String getTransitionOutMobile();

	public String getButtons();

	public String getInputs();

	public String getOnOpening();

	public String getOnOpened();

	public String getOnClosing();

	public String getOnClosed();

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
