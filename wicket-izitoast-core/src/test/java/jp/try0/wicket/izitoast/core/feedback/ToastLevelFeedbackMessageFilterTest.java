package jp.try0.wicket.izitoast.core.feedback;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.stream.Stream;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;
import org.junit.jupiter.params.provider.MethodSource;

import com.google.common.collect.Sets;

import jp.try0.wicket.izitoast.core.Toast;
import jp.try0.wicket.izitoast.core.Toast.FeedbackMessageLevel;
import jp.try0.wicket.izitoast.core.Toast.ToastType;
import jp.try0.wicket.izitoast.core.feedback.ToastLevelFeedbackMessageFilter;
import jp.try0.wicket.izitoast.core.test.AbstractIziToastTest;

/**
 * {@link ToastLevelFeedbackMessageFilter} Tests.
 *
 * @author Ryo Tsunoda
 *
 */
public class ToastLevelFeedbackMessageFilterTest extends AbstractIziToastTest {

	/**
	 * {@link ToastLevelFeedbackMessageFilter#lessThan(ToastType)} test.
	 *
	 * @param level
	 */
	@ParameterizedTest
	@EnumSource(value = ToastType.class, mode = Mode.EXCLUDE, names = { "UNDEFINED" })
	public void lessThan(ToastType level) {
		ToastLevelFeedbackMessageFilter filter = ToastLevelFeedbackMessageFilter.lessThan(level);

		Component dummy = new WebMarkupContainer("dummy");

		Integer feedbackMessageLevel = FeedbackMessageLevel.fromToastLevel(level).toInteger();
		FeedbackMessage argLevelMessage = new FeedbackMessage(dummy, Toast.create(level, "dummy"),
				feedbackMessageLevel);
		assertFalse(filter.accept(argLevelMessage), "Argument's level is must not contains for accepts level");

		Toast successToast = Toast.success("dummy");
		FeedbackMessage successMessage = new FeedbackMessage(dummy, successToast, FeedbackMessage.SUCCESS);
		assertEquals(filter.accept(successMessage), successToast.getToastType().lessThan(level));

		Toast infoToast = Toast.info("dummy");
		FeedbackMessage infoMessage = new FeedbackMessage(dummy, infoToast, FeedbackMessage.INFO);
		assertEquals(filter.accept(infoMessage), infoToast.getToastType().lessThan(level));

		Toast warnToast = Toast.warn("dummy");
		FeedbackMessage warnMessage = new FeedbackMessage(dummy, warnToast, FeedbackMessage.WARNING);
		assertEquals(filter.accept(warnMessage), warnToast.getToastType().lessThan(level));

		Toast errorToast = Toast.error("dummy");
		FeedbackMessage errorMessage = new FeedbackMessage(dummy, errorToast, FeedbackMessage.ERROR);
		assertEquals(filter.accept(errorMessage), errorToast.getToastType().lessThan(level));

	}

	/**
	 * {@link ToastLevelFeedbackMessageFilter#lessThanOrEqual(ToastType)} test.
	 *
	 * @param level
	 */
	@ParameterizedTest
	@EnumSource(value = ToastType.class, mode = Mode.EXCLUDE, names = { "UNDEFINED" })
	public void lessThanOrEqual(ToastType level) {
		ToastLevelFeedbackMessageFilter filter = ToastLevelFeedbackMessageFilter.lessThanOrEqual(level);

		Component dummy = new WebMarkupContainer("dummy");

		Integer feedbackMessageLevel = FeedbackMessageLevel.fromToastLevel(level).toInteger();
		FeedbackMessage argLevelMessage = new FeedbackMessage(dummy, Toast.create(level, "dummy"),
				feedbackMessageLevel);
		assertTrue(filter.accept(argLevelMessage), "Argument's level is must contains for accepts level");

		Toast successToast = Toast.success("dummy");
		FeedbackMessage successMessage = new FeedbackMessage(dummy, successToast, FeedbackMessage.SUCCESS);
		assertEquals(filter.accept(successMessage),
				successToast.getToastType().lessThan(level) || successToast.getToastType().equals(level));

		Toast infoToast = Toast.info("dummy");
		FeedbackMessage infoMessage = new FeedbackMessage(dummy, infoToast, FeedbackMessage.INFO);
		assertEquals(filter.accept(infoMessage),
				infoToast.getToastType().lessThan(level) || infoToast.getToastType().equals(level));

		Toast warnToast = Toast.warn("dummy");
		FeedbackMessage warnMessage = new FeedbackMessage(dummy, warnToast, FeedbackMessage.WARNING);
		assertEquals(filter.accept(warnMessage),
				warnToast.getToastType().lessThan(level) || warnToast.getToastType().equals(level));

		Toast errorToast = Toast.error("dummy");
		FeedbackMessage errorMessage = new FeedbackMessage(dummy, errorToast, FeedbackMessage.ERROR);
		assertEquals(filter.accept(errorMessage),
				errorToast.getToastType().lessThan(level) || errorToast.getToastType().equals(level));

	}

	/**
	 * {@link ToastLevelFeedbackMessageFilter#greaterThan(ToastType)} test.
	 *
	 * @param level
	 */
	@ParameterizedTest
	@EnumSource(value = ToastType.class, mode = Mode.EXCLUDE, names = { "UNDEFINED" })
	public void greaterThan(ToastType level) {
		ToastLevelFeedbackMessageFilter filter = ToastLevelFeedbackMessageFilter.greaterThan(level);

		Component dummy = new WebMarkupContainer("dummy");

		Integer feedbackMessageLevel = FeedbackMessageLevel.fromToastLevel(level).toInteger();
		FeedbackMessage argLevelMessage = new FeedbackMessage(dummy, Toast.create(level, "dummy"),
				feedbackMessageLevel);
		assertFalse(filter.accept(argLevelMessage), "Argument's level is not must contains for accepts level");

		Toast successToast = Toast.success("dummy");
		FeedbackMessage successMessage = new FeedbackMessage(dummy, successToast, FeedbackMessage.SUCCESS);
		assertEquals(filter.accept(successMessage), successToast.getToastType().greaterThan(level));

		Toast infoToast = Toast.info("dummy");
		FeedbackMessage infoMessage = new FeedbackMessage(dummy, infoToast, FeedbackMessage.INFO);
		assertEquals(filter.accept(infoMessage), infoToast.getToastType().greaterThan(level));

		Toast warnToast = Toast.warn("dummy");
		FeedbackMessage warnMessage = new FeedbackMessage(dummy, warnToast, FeedbackMessage.WARNING);
		assertEquals(filter.accept(warnMessage), warnToast.getToastType().greaterThan(level));

		Toast errorToast = Toast.error("dummy");
		FeedbackMessage errorMessage = new FeedbackMessage(dummy, errorToast, FeedbackMessage.ERROR);
		assertEquals(filter.accept(errorMessage), errorToast.getToastType().greaterThan(level));

	}

	/**
	 * {@link ToastLevelFeedbackMessageFilter#greaterThanOrEqual(ToastType)} test.
	 *
	 * @param level
	 */
	@ParameterizedTest
	@EnumSource(value = ToastType.class, mode = Mode.EXCLUDE, names = { "UNDEFINED" })
	public void greaterThanOrEqual(ToastType level) {
		ToastLevelFeedbackMessageFilter filter = ToastLevelFeedbackMessageFilter.greaterThanOrEqual(level);

		Component dummy = new WebMarkupContainer("dummy");

		Integer feedbackMessageLevel = FeedbackMessageLevel.fromToastLevel(level).toInteger();
		FeedbackMessage argLevelMessage = new FeedbackMessage(dummy, Toast.create(level, "dummy"),
				feedbackMessageLevel);
		assertTrue(filter.accept(argLevelMessage), "Argument's level is must contains for accepts level");

		Toast successToast = Toast.success("dummy");
		FeedbackMessage successMessage = new FeedbackMessage(dummy, successToast, FeedbackMessage.SUCCESS);
		assertEquals(filter.accept(successMessage),
				successToast.getToastType().greaterThan(level) || successToast.getToastType().equals(level));

		Toast infoToast = Toast.info("dummy");
		FeedbackMessage infoMessage = new FeedbackMessage(dummy, infoToast, FeedbackMessage.INFO);
		assertEquals(filter.accept(infoMessage),
				infoToast.getToastType().greaterThan(level) || infoToast.getToastType().equals(level));

		Toast warnToast = Toast.warn("dummy");
		FeedbackMessage warnMessage = new FeedbackMessage(dummy, warnToast, FeedbackMessage.WARNING);
		assertEquals(filter.accept(warnMessage),
				warnToast.getToastType().greaterThan(level) || warnToast.getToastType().equals(level));

		Toast errorToast = Toast.error("dummy");
		FeedbackMessage errorMessage = new FeedbackMessage(dummy, errorToast, FeedbackMessage.ERROR);
		assertEquals(filter.accept(errorMessage),
				errorToast.getToastType().greaterThan(level) || errorToast.getToastType().equals(level));

	}

	private static final String GET_LEVEL_SET_SOURCE = "getLevelSetSource";
	public static Stream<Set<ToastType>> getLevelSetSource() {
		return Stream.of(
				Sets.newHashSet(ToastType.SUCCESS),
				Sets.newHashSet(ToastType.SUCCESS, ToastType.ERROR),
				Sets.newHashSet(ToastType.INFO, ToastType.ERROR),
				Sets.newHashSet(ToastType.WARNING, ToastType.ERROR),
				Sets.newHashSet(ToastType.SUCCESS, ToastType.WARNING),
				Sets.newHashSet(ToastType.SUCCESS, ToastType.INFO, ToastType.ERROR)
				);
	}

	/**
	 * {@link ToastLevelFeedbackMessageFilter#accepts(ToastType...)} test.
	 *
	 * @param toastType
	 */
	@ParameterizedTest
	@MethodSource(GET_LEVEL_SET_SOURCE)
	public void accepts(Set<ToastType> levels) {

		ToastLevelFeedbackMessageFilter filter =
				ToastLevelFeedbackMessageFilter.accepts(levels.toArray(new ToastType[0]));

		Component dummy = new WebMarkupContainer("dummy");

		Toast successToast = Toast.success("dummy");
		FeedbackMessage successMessage = new FeedbackMessage(dummy, successToast, FeedbackMessage.SUCCESS);
		assertEquals(filter.accept(successMessage), levels.contains(successToast.getToastType()));

		Toast infoToast = Toast.info("dummy");
		FeedbackMessage infoMessage = new FeedbackMessage(dummy, infoToast, FeedbackMessage.INFO);
		assertEquals(filter.accept(infoMessage), levels.contains(infoToast.getToastType()));

		Toast warnToast = Toast.warn("dummy");
		FeedbackMessage warnMessage = new FeedbackMessage(dummy, warnToast, FeedbackMessage.WARNING);
		assertEquals(filter.accept(warnMessage), levels.contains(warnToast.getToastType()));

		Toast errorToast = Toast.error("dummy");
		FeedbackMessage errorMessage = new FeedbackMessage(dummy, errorToast, FeedbackMessage.ERROR);
		assertEquals(filter.accept(errorMessage), levels.contains(errorToast.getToastType()));

	}

	/**
	 * {@link ToastLevelFeedbackMessageFilter#ignores(ToastType...)} test.
	 *
	 * @param toastType
	 */
	@ParameterizedTest
	@MethodSource(GET_LEVEL_SET_SOURCE)
	public void ignores(Set<ToastType> levels) {

		ToastLevelFeedbackMessageFilter filter =
				ToastLevelFeedbackMessageFilter.ignores(levels.toArray(new ToastType[0]));

		Component dummy = new WebMarkupContainer("dummy");

		Toast successToast = Toast.success("dummy");
		FeedbackMessage successMessage = new FeedbackMessage(dummy, successToast, FeedbackMessage.SUCCESS);
		assertEquals(filter.accept(successMessage), !levels.contains(successToast.getToastType()));

		Toast infoToast = Toast.info("dummy");
		FeedbackMessage infoMessage = new FeedbackMessage(dummy, infoToast, FeedbackMessage.INFO);
		assertEquals(filter.accept(infoMessage), !levels.contains(infoToast.getToastType()));

		Toast warnToast = Toast.warn("dummy");
		FeedbackMessage warnMessage = new FeedbackMessage(dummy, warnToast, FeedbackMessage.WARNING);
		assertEquals(filter.accept(warnMessage), !levels.contains(warnToast.getToastType()));

		Toast errorToast = Toast.error("dummy");
		FeedbackMessage errorMessage = new FeedbackMessage(dummy, errorToast, FeedbackMessage.ERROR);
		assertEquals(filter.accept(errorMessage), !levels.contains(errorToast.getToastType()));

	}


}
