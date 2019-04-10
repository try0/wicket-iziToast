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
