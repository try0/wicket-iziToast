package jp.try0.wicket.iziToast.samples;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.RequestCycleSettings.RenderStrategy;

import jp.try0.wicket.iziToast.core.ToastOption;
import jp.try0.wicket.iziToast.core.behavior.IziToastBehavior.ToastMessageCombiner;
import jp.try0.wicket.iziToast.core.config.IziToastSetting;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 *
 * @see jp.try0.wicket.iziToast.samples.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		getMarkupSettings().setStripWicketTags(true);
		getRequestCycleSettings().setRenderStrategy(RenderStrategy.ONE_PASS_RENDER);

		ToastOption option = new ToastOption();
		option.setStyleClass("izi-toast-custom");
		option.setPosition("topRight");
		option.setClose(true);
		option.setCloseOnClick(true);
		option.setTimeout(4000);
		option.setProgressBar(true);
		option.setLayout(2);
		option.setTransitionIn("fadeIn");

		ToastMessageCombiner combiner = new ToastMessageCombiner();
		combiner.setPrefix("・");

		IziToastSetting.createInitializer(this)
		.setAutoAppendBehavior(true)
		.setGlobalOption(option)
		.setToastMessageCombiner(combiner)
		.initialize();
	}

	@Override
	public RuntimeConfigurationType getConfigurationType() {
		return RuntimeConfigurationType.DEPLOYMENT;
	}


}
