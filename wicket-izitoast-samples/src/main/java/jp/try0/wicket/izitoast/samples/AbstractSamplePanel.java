package jp.try0.wicket.izitoast.samples;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import jp.try0.wicket.izitoast.core.ToastOption;

/**
 * Sample base class.
 *
 * @author Ryo Tsunoda
 *
 */
public abstract class AbstractSamplePanel extends Panel {

	private final IModel<String> titleModel;

	/**
	 * Constructor.
	 *
	 * @param id
	 */
	public AbstractSamplePanel(String id, IModel<String> titleModel) {
		super(id);
		this.titleModel = titleModel;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new Label("lblSampleTitle", titleModel) {
			{
				setMarkupId(AbstractSamplePanel.this.getClass().getSimpleName());
			}
		});

		add(new ExternalLink("linkToSource", HomePage.getGitHubUrl(getClass())));
	}

	protected ToastOption newSourceToastOption() {
		ToastOption option2 = new ToastOption();
		option2.setTimeout(false);
		option2.setDrag(false);
		option2.setClose(true);
		option2.setCloseOnClick(false);
		return option2;
	}

}
