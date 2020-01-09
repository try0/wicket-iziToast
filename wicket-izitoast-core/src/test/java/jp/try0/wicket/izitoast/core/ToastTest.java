package jp.try0.wicket.izitoast.core;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

import jp.try0.wicket.izitoast.core.Toast.ToastLevel;
import jp.try0.wicket.izitoast.core.behavior.IziToastBehavior;
import jp.try0.wicket.izitoast.core.test.AbstractIziToastTest;
import jp.try0.wicket.izitoast.core.test.IziToastTestPage;

/**
 * {@link Toast} tests.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastTest extends AbstractIziToastTest {

	/**
	 * {@link Toast#create(ToastLevel, String)}<br>
	 * {@link Toast#info(String)}<br>
	 * {@link Toast#success(String)}<br>
	 * {@link Toast#warn(String)}<br>
	 * {@link Toast#error(String)}<br>
	 */
	@Test
	public void createToast() {

		{
			Toast toast = Toast.info("info");
			assertEquals(toast.getToastLevel(), ToastLevel.INFO);
			assertEquals(toast.getMessage(), "info");
		}
		{
			Toast toast = Toast.success("success");
			assertEquals(toast.getToastLevel(), ToastLevel.SUCCESS);
			assertEquals(toast.getMessage(), "success");
		}
		{
			Toast toast = Toast.warn("warn");
			assertEquals(toast.getToastLevel(), ToastLevel.WARNING);
			assertEquals(toast.getMessage(), "warn");
		}
		{
			Toast toast = Toast.error("error");
			assertEquals(toast.getToastLevel(), ToastLevel.ERROR);
			assertEquals(toast.getMessage(), "error");
		}

	}

	@ParameterizedTest
	@EnumSource(value = ToastLevel.class, mode = Mode.EXCLUDE, names = { "UNDEFINED" })
	public void showToastWithAjaxRequest(ToastLevel level) {

		AjaxLink<Void> link = new AjaxLink<Void>("showToast") {

			@Override
			public Markup getAssociatedMarkup() {
				return Markup.of("<a wicket:id=\"showToast\">click</a>");
			}

			@Override
			public void onClick(AjaxRequestTarget target) {
				Toast.create(level, level.getLevelString()).show(target);
			}

		};

		final WicketTester tester = getWicketTester();
		tester.startComponentInPage(link);
		tester.clickLink(link);

		final String lastResponseString = tester.getLastResponseAsString();
		final String toastLevelString = level.getLevelString();
		assertTrue(lastResponseString.contains("iziToast." + toastLevelString));

	}

	@ParameterizedTest
	@EnumSource(value = ToastLevel.class, mode = Mode.EXCLUDE, names = { "UNDEFINED" })
	public void showToastWithHeaderResponse(ToastLevel level) {

		Page page = new IziToastTestPage() {

			@Override
			public void renderHead(IHeaderResponse response) {
				super.renderHead(response);
				Toast.create(level, level.getLevelString()).show(response);
			}

		};

		final WicketTester tester = getWicketTester();
		tester.startPage(page);

		final String lastResponseString = tester.getLastResponseAsString();
		final String toastLevelString = level.getLevelString();
		assertTrue(lastResponseString.contains("iziToast." + toastLevelString));

	}

	@ParameterizedTest
	@EnumSource(value = ToastLevel.class, mode = Mode.EXCLUDE, names = { "UNDEFINED", "PLAIN" })
	public void showToastWithFeedbackMessage(ToastLevel level) {

		Link<Void> link = new Link<Void>("showToast") {

			@Override
			public Markup getAssociatedMarkup() {
				return Markup.of("<a wicket:id=\"showToast\">click</a>");
			}

			@Override
			public void onClick() {
				Toast.create(level, level.getLevelString()).show(this);
			}

		};

		final WicketTester tester = getWicketTester();
		tester.startComponentInPage(link);
		tester.clickLink(link);

		assertEquals(link.getFeedbackMessages().size(), 1);

		for (FeedbackMessage fm : link.getFeedbackMessages()) {
			if (fm.getMessage() instanceof IToast) {
				IToast toast = (IToast) fm.getMessage();
				assertEquals(toast.getToastLevel(), level);
			} else {
				fail();
			}
		}

	}

	/**
	 * {@link Toast#hide(org.apache.wicket.core.request.handler.IPartialPageRequestHandler)}
	 */
	@Test
	public void hideToast_NoId() {
		IziToastTestPage page = new IziToastTestPage() {
			@Override
			protected void onClickAjaxLink(AjaxRequestTarget target) {
				super.onClickAjaxLink(target);

				assertThrows(IllegalStateException.class, () ->  {
					Toast.info("test").hide(target);
				});


			}
		};
		page.add(new IziToastBehavior());

		final WicketTester tester = getWicketTester();
		tester.startPage(page);

		AjaxLink<?> link = page.getAjaxLink();
		tester.clickLink(link);

		assertTrue(tester.isExposeExceptions());

	}

	/**
	 * {@link Toast#hide(org.apache.wicket.core.request.handler.IPartialPageRequestHandler)}
	 */
	@Test
	public void hideToast() {
		IziToastTestPage page = new IziToastTestPage() {
			@Override
			protected void onClickAjaxLink(AjaxRequestTarget target) {
				super.onClickAjaxLink(target);

				Toast toast = Toast.info("test");
				toast.getToastOption().setId("hide-target-toast");
				toast.hide(target);
			}
		};
		page.add(new IziToastBehavior());

		final WicketTester tester = getWicketTester();
		tester.startPage(page);

		AjaxLink<?> link = page.getAjaxLink();
		tester.clickLink(link);


		final String lastResponseString = tester.getLastResponseAsString();
		assertTrue(lastResponseString.contains(Toast.getHideScript("hide-target-toast")));

	}

	/**
	 * {@link Toast#hideAll(IHeaderResponse)}<br>
	 * {@link Toast#hideAll(AjaxRequestTarget)}
	 */
	@Test
	public void hideAllToast() {
		// IHeaderResponse
		{
			IziToastTestPage page = new IziToastTestPage() {

				@Override
				public void renderHead(IHeaderResponse response) {
					super.renderHead(response);
					Toast.hideAll(response);
				}
			};

			final WicketTester tester = getWicketTester();
			tester.startPage(page);

			final String lastResponseString = tester.getLastResponseAsString();
			assertTrue(lastResponseString.contains(Toast.SCRIPT_HIDE_ALL_TOAST));
		}

		// AjaxRequestTarget
		{
			IziToastTestPage page = new IziToastTestPage() {
				@Override
				protected void onClickAjaxLink(AjaxRequestTarget target) {
					super.onClickAjaxLink(target);
					Toast.hideAll(target);
				}
			};

			final WicketTester tester = getWicketTester();
			tester.startPage(page);

			AjaxLink<?> link = page.getAjaxLink();
			tester.clickLink(link);

			final String lastResponseString = tester.getLastResponseAsString();
			assertTrue(lastResponseString.contains(Toast.SCRIPT_HIDE_ALL_TOAST));
		}

	}

	/**
	 * {@link Toast#destroy(IHeaderResponse)}<br>
	 * {@link Toast#destroy(AjaxRequestTarget)}
	 */
	@Test
	public void destoryToast() {
		// IHeaderResponse
		{
			IziToastTestPage page = new IziToastTestPage() {

				@Override
				public void renderHead(IHeaderResponse response) {
					super.renderHead(response);
					Toast.destroy(response);
				}
			};

			final WicketTester tester = getWicketTester();
			tester.startPage(page);

			final String lastResponseString = tester.getLastResponseAsString();
			assertTrue(lastResponseString.contains(Toast.SCRIPT_DESTROY_TOAST));
		}

		// AjaxRequestTarget
		{
			IziToastTestPage page = new IziToastTestPage() {
				@Override
				protected void onClickAjaxLink(AjaxRequestTarget target) {
					super.onClickAjaxLink(target);
					Toast.destroy(target);
				}
			};

			final WicketTester tester = getWicketTester();
			tester.startPage(page);

			AjaxLink<?> link = page.getAjaxLink();
			tester.clickLink(link);

			final String lastResponseString = tester.getLastResponseAsString();
			assertTrue(lastResponseString.contains(Toast.SCRIPT_DESTROY_TOAST));
		}

	}
}
