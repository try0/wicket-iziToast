package jp.try0.wicket.iziToast.core.behavior;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

import jp.try0.wicket.iziToast.core.test.AbstractIziToastTest;
import jp.try0.wicket.iziToast.core.test.IziToastTestPage;
import jp.try0.wicket.izitoast.core.EachLevelToastOptions;
import jp.try0.wicket.izitoast.core.Toast;
import jp.try0.wicket.izitoast.core.ToastOption;
import jp.try0.wicket.izitoast.core.Toast.ToastLevel;
import jp.try0.wicket.izitoast.core.behavior.IziToastBehavior;
import jp.try0.wicket.izitoast.core.behavior.IziToastBehavior.ToastMessageCombiner;
import jp.try0.wicket.izitoast.core.config.IziToastSetting;

/**
 * {@link IziToastBehavior} tests.
 *
 * @author Ryo Tsunoda
 *
 */
public class IziToastBehaviorTest extends AbstractIziToastTest {

	@Test
	public void appendScripts() {
		final WicketTester tester = getWicketTester();

		Page page = new IziToastTestPage() {
			{
				add(new IziToastBehavior());
			}
		};
		page.error("error");

		tester.startPage(page);

		final String lastResponseString = tester.getLastResponseAsString();

		assertTrue(lastResponseString.contains("iziToast.error("));

	}

	@Test
	public void appendScriptsOnAjaxRequest() {
		final WicketTester tester = getWicketTester();
		IziToastTestPage page = new IziToastTestPage() {
			{
				add(new IziToastBehavior());
			}

			@Override
			protected void onClickAjaxLink(AjaxRequestTarget target) {
				info("info");
			}
		};

		tester.startPage(page);
		{
			String lastResponseString = tester.getLastResponseAsString();
			assertTrue(!lastResponseString.contains("iziToast.info("));
		}

		tester.clickLink(page.getAjaxLink());
		{
			String lastResponseString = tester.getLastResponseAsString();
			assertTrue(lastResponseString.contains("iziToast.info("));
		}

	}

	@Test
	public void appendScriptsUseSession() {
		final WicketTester tester = getWicketTester();

		Page page = new IziToastTestPage() {
			{
				add(new IziToastBehavior());
			}
		};
		Session.get().error("error");

		tester.startPage(page);

		final String lastResponseString = tester.getLastResponseAsString();
		assertTrue(lastResponseString.contains("iziToast.error("));

	}

	@Test
	public void appendScriptsUseSessionOnAjaxRequest() {
		final WicketTester tester = getWicketTester();
		IziToastTestPage page = new IziToastTestPage() {
			{
				add(new IziToastBehavior());
			}

			@Override
			protected void onClickAjaxLink(AjaxRequestTarget target) {
				Session.get().info("info");
			}
		};

		tester.startPage(page);
		{
			String lastResponseString = tester.getLastResponseAsString();
			assertTrue(!lastResponseString.contains("iziToast.info("));
		}

		tester.clickLink(page.getAjaxLink());
		{
			String lastResponseString = tester.getLastResponseAsString();
			assertTrue(lastResponseString.contains("iziToast.info("));
		}

	}

	@ParameterizedTest
	@EnumSource(value = ToastLevel.class, mode = Mode.EXCLUDE, names = { "UNDEFINED" })
	public void useFilter(ToastLevel accept) {
		final WicketTester tester = getWicketTester();

		Page page = new IziToastTestPage() {
			{
				add(new IziToastBehavior(msg -> ToastLevel.fromFeedbackMessageLevel(msg.getLevel()) == accept));
			}
		};
		page.info(ToastLevel.INFO.getLevelString());
		page.success(ToastLevel.SUCCESS.getLevelString());
		page.warn(ToastLevel.WARNING.getLevelString());
		page.error(ToastLevel.ERROR.getLevelString());

		tester.startPage(page);

		final String lastResponseString = tester.getLastResponseAsString();

		for (ToastLevel lv : Arrays.asList(ToastLevel.INFO, ToastLevel.SUCCESS, ToastLevel.WARNING, ToastLevel.ERROR)) {
			if (lv == accept) {
				assertTrue(lastResponseString
						.contains("iziToast." + lv.getLevelString() + "("));
			} else {
				assertFalse(lastResponseString
						.contains("iziToast." + lv.getLevelString() + "("));
			}
		}

	}

	@Test
	public void combineToasts() {
		final WicketTester tester = getWicketTester();

		final String prefix = "TEST_P";
		final String suffix = "TEST_S";
		Page page = new IziToastTestPage() {
			{
				IziToastBehavior behavior = new IziToastBehavior();

				ToastMessageCombiner combiner = new ToastMessageCombiner();
				combiner.setPrefix(prefix);
				combiner.setSuffix(suffix);

				behavior.setMessageCombiner(combiner);
				add(behavior);
			}
		};

		page.fatal("msg1");
		page.fatal("msg2");
		page.fatal("msg3");

		tester.startPage(page);

		final String lastResponseString = tester.getLastResponseAsString();

		final String message = prefix + "msg1" + suffix
				+ prefix + "msg2" + suffix
				+ prefix + "msg3" + suffix;
		assertTrue(lastResponseString.contains("iziToast.error("));
		assertTrue(lastResponseString.contains(message));
	}

	@Test
	public void combineToastsThatHasOptions() {
		final WicketTester tester = getWicketTester();

		Page page = new IziToastTestPage() {
			{
				IziToastBehavior behavior = new IziToastBehavior();

				ToastMessageCombiner combiner = new ToastMessageCombiner();
				behavior.setMessageCombiner(combiner);
				add(behavior);
			}
		};

		final ToastOption opt1 = ToastOption.create().setMessage("msg1").setColor("red");
		Toast.create(ToastLevel.SUCCESS, opt1)
				.show(page);
		final ToastOption opt2 = ToastOption.create().setMessage("msg2").setImageWidth(1000);
		Toast.create(ToastLevel.SUCCESS, opt2)
				.show(page);

		tester.startPage(page);

		final String lastResponseString = tester.getLastResponseAsString().replaceAll(" ", "");

		final String message = "msg1" + ToastMessageCombiner.DEFAULT_SUFFIX
				+ "msg2" + ToastMessageCombiner.DEFAULT_SUFFIX;

		assertTrue(lastResponseString.contains("iziToast.success("));
		assertTrue(lastResponseString.contains(message));
	}

	@Test
	public void outputToastOptions() {

		IziToastSetting.createInitializer(getWebApplication())
				.setAutoAppendBehavior(true)
				.initialize();

		final WicketTester tester = getWicketTester();
		IziToastTestPage page = new IziToastTestPage() {
			{
				add(new IziToastBehavior());
			}
		};
		ToastOption options = ToastOption.create()
				.setAnimateInside(true)
				.setBackgroundColor("red");

		Toast.create(ToastLevel.SUCCESS, options)
				.show(page);

		tester.startPage(page);

		final String lastResponseString = tester.getLastResponseAsString();
		assertTrue(lastResponseString.contains("iziToast.success(" + options.toJsonString()));
	}

	@Test
	public void outputDefaultOptionForEachLevels() {
		ToastOption options = ToastOption.create()
				.setAnimateInside(true)
				.setBackgroundColor("red");

		IziToastSetting.createInitializer(getWebApplication())
				.setAutoAppendBehavior(true)
				.setGlobalEachLevelOptions(EachLevelToastOptions.builder().setSuccessOption(options).get())
				.initialize();

		final WicketTester tester = getWicketTester();
		IziToastTestPage page = new IziToastTestPage() {
			{
				add(new IziToastBehavior());
			}
		};
		page.success("");
		page.info("");

		tester.startPage(page);

		final String lastResponseString = tester.getLastResponseAsString();
		assertTrue(lastResponseString.contains("iziToast.success(" + options.toJsonString()));
		assertTrue(lastResponseString.contains("iziToast.info({\"message\":\"\",})"));
	}
}
