package jp.try0.wicket.iziToast.core.config;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Test;

import jp.try0.wicket.iziToast.core.test.AbstractIziToastTest;
import jp.try0.wicket.iziToast.core.test.IziToastTestPage;
import jp.try0.wicket.izitoast.core.EachLevelToastOptions;
import jp.try0.wicket.izitoast.core.ToastOption;
import jp.try0.wicket.izitoast.core.Toast.ToastLevel;
import jp.try0.wicket.izitoast.core.behavior.IziToastBehavior;
import jp.try0.wicket.izitoast.core.behavior.IziToastBehavior.ToastMessageCombiner;
import jp.try0.wicket.izitoast.core.config.IziToastSetting;

/**
 * {@link ToastrSetting} tests.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastrSettingTest extends AbstractIziToastTest {

	@Test
	public void initializeSettings() {

		boolean autoAppend = true;
		ToastOption options = ToastOption.create();
		IFeedbackMessageFilter filter = msg -> true;
		Supplier<IziToastBehavior> factory = () -> new IziToastBehavior();
		ToastMessageCombiner combier = new ToastMessageCombiner();

		IziToastSetting.createInitializer(getWebApplication())
				.setAutoAppendBehavior(autoAppend)
				.setGlobalOption(options)
				.setMessageFilter(filter)
				.setIziToastBehaviorFactory(factory)
				.setToastMessageCombiner(combier)
				.initialize();

		IziToastSetting settings = IziToastSetting.get();

		assertEquals(settings.hasGlobalOptions(), true);
		assertTrue(settings.getGlobalOption().isPresent());
		assertTrue(settings.hasMessageFilter());
		assertTrue(settings.getGlobalOption().get() == options);
		assertTrue(settings.getMessageFilter().get() == filter);
		assertTrue(settings.getToastMessageCombiner() == combier);
	}

	@Test
	public void initializeSimpleSettings() {

		IziToastSetting.createInitializer(getWebApplication()).initialize();

		IziToastSetting settings = IziToastSetting.get();

		assertEquals(settings.hasGlobalOptions(), false);
		assertFalse(settings.getGlobalOption().isPresent());
		assertTrue(settings.getMessageFilter().isPresent());
		assertEquals(settings.getMessageFilter().get(), IFeedbackMessageFilter.ALL);

	}

	@Test
	public void initializeSettingsTwice() {
		assertThrows(UnsupportedOperationException.class, () -> {
			IziToastSetting.createInitializer(getWebApplication()).initialize();
			IziToastSetting.createInitializer(getWebApplication()).initialize();
		});
	}

	@Test
	public void autoAppendBehavior() {

		IziToastSetting.createInitializer(getWebApplication())
				.setAutoAppendBehavior(true)
				.initialize();

		final WicketTester tester = getWicketTester();
		tester.startPage(IziToastTestPage.class);
		List<IziToastBehavior> behaviors = tester.getLastRenderedPage().getBehaviors(IziToastBehavior.class);
		assertTrue(behaviors.size() == 1);

	}

	@Test
	public void customBehaviorFactory() {

		final IModel<IziToastBehavior> behavior = Model.of();
		IziToastSetting.createInitializer(getWebApplication())
				.setAutoAppendBehavior(true)
				.setIziToastBehaviorFactory(() -> {
					behavior.setObject(new IziToastBehavior());
					return behavior.getObject();
				})
				.initialize();

		final WicketTester tester = getWicketTester();

		tester.startPage(IziToastTestPage.class);
		List<IziToastBehavior> behaviors = tester.getLastRenderedPage().getBehaviors(IziToastBehavior.class);
		assertTrue(behaviors.size() == 1);
		assertTrue(behaviors.contains(behavior.getObject()));
	}



	@Test
	public void setGlobalOptions() {
		ToastOption globalOptions = ToastOption.create()
				.setAnimateInside(true)
				.setBackgroundColor("blue");
		IziToastSetting.createInitializer(getWebApplication())
				.setAutoAppendBehavior(true)
				.setGlobalOption(globalOptions)
				.initialize();

		final WicketTester tester = getWicketTester();
		tester.startPage(IziToastTestPage.class);

		final String lastResponseString = tester.getLastResponseAsString();
		assertTrue(lastResponseString.contains(globalOptions.toJsonString()));

	}

	@Test
	public void setGlobalEachLevelOptions() {

		ToastOption info = new ToastOption();
		ToastOption success = new ToastOption();
		ToastOption warn = new ToastOption();
		ToastOption error = new ToastOption();

		EachLevelToastOptions options = new EachLevelToastOptions(new HashMap<ToastLevel, ToastOption>() {
			{
				put(ToastLevel.INFO, info);
				put(ToastLevel.SUCCESS, success);
				put(ToastLevel.WARNING, warn);
				put(ToastLevel.ERROR, error);
			}
		});

		IziToastSetting setting = IziToastSetting.createInitializer(getWebApplication())
				.setAutoAppendBehavior(true)
				.setGlobalEachLevelOptions(options)
				.initialize();

		assertTrue(setting.getGlobalEachLevelOptions().get(ToastLevel.INFO).isPresent());
		assertTrue(setting.getGlobalEachLevelOptions().get(ToastLevel.INFO).get() == info);
		assertTrue(setting.getGlobalEachLevelOptions().get(ToastLevel.SUCCESS).isPresent());
		assertTrue(setting.getGlobalEachLevelOptions().get(ToastLevel.SUCCESS).get() == success);
		assertTrue(setting.getGlobalEachLevelOptions().get(ToastLevel.WARNING).isPresent());
		assertTrue(setting.getGlobalEachLevelOptions().get(ToastLevel.WARNING).get() == warn);
		assertTrue(setting.getGlobalEachLevelOptions().get(ToastLevel.ERROR).isPresent());
		assertTrue(setting.getGlobalEachLevelOptions().get(ToastLevel.ERROR).get() == error);

	}
}
