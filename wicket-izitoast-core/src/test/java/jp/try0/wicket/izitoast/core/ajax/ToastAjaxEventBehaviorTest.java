package jp.try0.wicket.izitoast.core.ajax;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jp.try0.wicket.izitoast.core.IToast;
import jp.try0.wicket.izitoast.core.Toast;
import jp.try0.wicket.izitoast.core.Toast.ToastType;
import jp.try0.wicket.izitoast.core.behavior.IziToastBehavior;
import jp.try0.wicket.izitoast.core.test.AbstractIziToastTest;
import jp.try0.wicket.izitoast.core.test.IziToastTestPage;

/**
 * {@link ToastAjaxEventBehavior} tests.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastAjaxEventBehaviorTest extends AbstractIziToastTest {

	/**
	 * {@link ToastAjaxEventBehavior#setOnClosed(Toast, ToastAjaxEventBehavior)}<br>
	 * {@link ToastAjaxEventBehavior#setOnClosing(Toast, ToastAjaxEventBehavior)}<br>
	 * {@link ToastAjaxEventBehavior#setOnOpened(Toast, ToastAjaxEventBehavior)}<br>
	 * {@link ToastAjaxEventBehavior#setOnOpening(Toast, ToastAjaxEventBehavior)}<br>
	 */
	@Test
	public void renderCallbackFunctions() {

		IziToastTestPage page = new IziToastTestPage();
		page.add(new IziToastBehavior());

		ToastAjaxEventBehavior ajaxBehavior = new ToastAjaxEventBehavior() {

			@Override
			protected void respond(AjaxRequestTarget target, IToast bindToast) {

			}
		};
		page.add(ajaxBehavior);

		Toast toast = Toast.create(ToastType.INFO, "Test render callback functions.");
		ToastAjaxEventBehavior.setOnClosed(toast, ajaxBehavior);
		ToastAjaxEventBehavior.setOnClosing(toast, ajaxBehavior);
		ToastAjaxEventBehavior.setOnOpened(toast, ajaxBehavior);
		ToastAjaxEventBehavior.setOnOpening(toast, ajaxBehavior);

		String callbackScript = ajaxBehavior.getCallbackScript().toString();
		page.info(toast);

		final WicketTester tester = getWicketTester();
		tester.startPage(page);

		final String lastResponseString = tester.getLastResponseAsString();

		Assertions.assertTrue(lastResponseString
				.contains("\"onClosed\":function() { " + callbackScript + " }"));
		Assertions.assertTrue(lastResponseString
				.contains("\"onClosing\":function() { " + callbackScript + " }"));
		Assertions.assertTrue(lastResponseString
				.contains("\"onOpened\":function() { " + callbackScript + " }"));
		Assertions.assertTrue(lastResponseString
				.contains("\"onOpening\":function() { " + callbackScript + " }"));

	}

}
