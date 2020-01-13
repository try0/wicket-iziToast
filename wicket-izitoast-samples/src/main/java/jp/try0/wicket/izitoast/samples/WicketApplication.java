package jp.try0.wicket.izitoast.samples;

import java.io.Serializable;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.RequestCycleSettings.RenderStrategy;

import jp.try0.wicket.izitoast.core.ToastOption;
import jp.try0.wicket.izitoast.core.config.DefaultToastTargetSetter;
import jp.try0.wicket.izitoast.core.config.IziToastSetting;
import jp.try0.wicket.izitoast.core.config.ToastMessageCombiner;
import jp.try0.wicket.izitoast.core.config.ToastTargetContainerAppender;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 *
 * @see jp.try0.wicket.izitoast.samples.Start#main(String[])
 */
public class WicketApplication extends WebApplication implements Serializable {
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
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
		combiner.setIgnoreToastFilter(DefaultToastTargetSetter.NO_TARGET_FILTER.negate());
		combiner.setPrefix("・");

		IziToastSetting.createInitializer(this)
				.setAutoAppendBehavior(true)
				.setGlobalOption(option)
				.setToastMessageCombiner(combiner)
				.setToastTargetSetter(DefaultToastTargetSetter.getInstance())
				.initialize();

		getComponentInstantiationListeners().add(new ToastTargetContainerAppender());
	}

	@Override
	public RuntimeConfigurationType getConfigurationType() {
		return RuntimeConfigurationType.DEPLOYMENT;
	}

}
