package jp.try0.wicket.izitoast.core;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import jp.try0.wicket.izitoast.core.EachLevelToastOptions;
import jp.try0.wicket.izitoast.core.ToastOption;
import jp.try0.wicket.izitoast.core.Toast.ToastLevel;

/**
 * {@link EachLevelToastOptions} tests.
 *
 * @author Ryo Tsunoda
 *
 */
public class EachLevelToastOptionsTest {

	@Test
	public void build() {
		ToastOption info = new ToastOption();
		ToastOption success = new ToastOption();
		ToastOption warn = new ToastOption();
		ToastOption error = new ToastOption();

		EachLevelToastOptions options = EachLevelToastOptions.builder()
				.setInfoOption(info)
				.setSuccessOption(success)
				.setWarnOption(warn)
				.setErrorOption(error)
				.get();

		assertTrue(options.get(ToastLevel.INFO).isPresent());
		assertTrue(options.get(ToastLevel.INFO).get() == info);

		assertTrue(options.get(ToastLevel.SUCCESS).isPresent());
		assertTrue(options.get(ToastLevel.SUCCESS).get() == success);

		assertTrue(options.get(ToastLevel.WARNING).isPresent());
		assertTrue(options.get(ToastLevel.WARNING).get() == warn);

		assertTrue(options.get(ToastLevel.ERROR).isPresent());
		assertTrue(options.get(ToastLevel.ERROR).get() == error);
	}

}
