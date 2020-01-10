package jp.try0.wicket.izitoast.core;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import jp.try0.wicket.izitoast.core.EachLevelToastOptions;
import jp.try0.wicket.izitoast.core.ToastOption;
import jp.try0.wicket.izitoast.core.Toast.ToastType;

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

		assertTrue(options.get(ToastType.INFO).isPresent());
		assertTrue(options.get(ToastType.INFO).get() == info);

		assertTrue(options.get(ToastType.SUCCESS).isPresent());
		assertTrue(options.get(ToastType.SUCCESS).get() == success);

		assertTrue(options.get(ToastType.WARNING).isPresent());
		assertTrue(options.get(ToastType.WARNING).get() == warn);

		assertTrue(options.get(ToastType.ERROR).isPresent());
		assertTrue(options.get(ToastType.ERROR).get() == error);
	}

}
