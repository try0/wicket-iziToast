package jp.try0.wicket.izitoast.core.feedback;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.junit.jupiter.api.Test;

import jp.try0.wicket.izitoast.core.IToast;
import jp.try0.wicket.izitoast.core.Toast;
import jp.try0.wicket.izitoast.core.Toast.ToastType;
import jp.try0.wicket.izitoast.core.ToastOption;
import jp.try0.wicket.izitoast.core.test.AbstractIziToastTest;

/**
 * {@link ToastIgnoreFilter} Tests.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastIgnoreFilterTest extends AbstractIziToastTest {

	@Test
	public void feedbackMessageIsNotToast() {

		Component dummy = new WebMarkupContainer("dummy");

		ToastIgnoreFilter filter = new ToastIgnoreFilter();
		FeedbackMessage message = new FeedbackMessage(dummy, "string", FeedbackMessage.DEBUG);

		assertTrue(filter.accept(message));
	}

	@Test
	public void feedbackMessageIsToast() {
		Component dummy = new WebMarkupContainer("dummy");
		ToastIgnoreFilter filter = new ToastIgnoreFilter();
		{
			IToast toast = Toast.info("info-toast");
			FeedbackMessage message = new FeedbackMessage(dummy, toast, FeedbackMessage.DEBUG);

			assertFalse(filter.accept(message));
		}

		{
			IToast toast = new IToast() {

				@Override
				public ToastType getToastType() {
					return null;
				}

				@Override
				public ToastOption getToastOption() {
					return null;
				}

				@Override
				public CharSequence getScriptForDisplay() {
					return null;
				}

			};
			FeedbackMessage message = new FeedbackMessage(dummy, toast, FeedbackMessage.DEBUG);

			assertFalse(filter.accept(message));
		}

		{
			IToast toast = new Toast(ToastType.INFO, "message") {

			};
			FeedbackMessage message = new FeedbackMessage(dummy, toast, FeedbackMessage.DEBUG);

			assertFalse(filter.accept(message));
		}

	}

}
