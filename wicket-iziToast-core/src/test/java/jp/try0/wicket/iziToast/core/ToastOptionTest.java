package jp.try0.wicket.iziToast.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jp.try0.wicket.iziToast.core.IToastOption.OptionKeys;

/**
 * {@link ToastOption} tests.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastOptionTest {

	@Test
	public void squeezeWithDoubleQuotes() {
		ToastOption options = ToastOption.create();
		options.setBackgroundColor("red");
		options.setColor("blue");

		String optionObject = options.toJsonString().replaceAll(" ", "");

		assertTrue(optionObject.contains("\"" + OptionKeys.BACKGROUND_COLOR + "\":\"red\","));
		assertTrue(optionObject
				.contains("\"" + OptionKeys.COLOR + "\":\"blue\","));

	}

	@Test
	public void unsqueezeWithDoubleQuotes() {
		ToastOption options = ToastOption.create();
		final String onClosedFunction = "function(){alert('test');}";
		options.setOnClosed(onClosedFunction);

		String optionObject = options.toJsonString().replaceAll(" ", "");

		assertTrue(optionObject.contains("\"" + OptionKeys.ON_CLOSED + "\":" + onClosedFunction + ","));
	}

	/**
	 * {@link ToastOption#overwrite(IToastOption)}
	 */
	@Test
	public void createOverwriteOptions() {

		{
			ToastOption base = ToastOption.create();

			ToastOption overwrite = ToastOption.create();
			overwrite.setAnimateInside(false);
			overwrite.setImageWidth(1000);
			overwrite.setBackgroundColor("red");

			ToastOption overwritten = base.overwrite(overwrite);

			assertEquals(overwritten.getAnimateInside(), false);
			assertTrue(overwritten.getImageWidth() == 1000);
			assertEquals(overwritten.getBackgroundColor(), "red");
		}

		{
			ToastOption base = ToastOption.create();
			base.setAnimateInside(true);
			base.setImageWidth(100);

			ToastOption overwrite = ToastOption.create();
			overwrite.setAnimateInside(false);
			overwrite.setImageWidth(1000);
			overwrite.setBackgroundColor("red");

			ToastOption overwritten = base.overwrite(overwrite);

			assertEquals(overwritten.getAnimateInside(), false);
			assertTrue(overwritten.getImageWidth() == 1000);
			assertEquals(overwritten.getBackgroundColor(), "red");
		}

	}

}
