package jp.try0.wicket.izitoast.core.config;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.wicket.Application;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Test;

import jp.try0.wicket.izitoast.core.behavior.IziToastBehavior;
import jp.try0.wicket.izitoast.core.test.AbstractIziToastTest;

/**
 * {@link ToastrBehaviorAutoAppender} tests.
 *
 * @author Ryo Tsunoda
 *
 */
public class IziToastBehaviorAutoAppenderTest extends AbstractIziToastTest {

	/**
	 * {@link ToastrBehaviorAutoAppender} test.
	 */
	@Test
	public void appendToastrBehavior() {
		final Application app = getWebApplication();
		app.getComponentInstantiationListeners().add(new IziToastBehaviorAutoAppender());

		final WicketTester tester = getWicketTester();

		final WebMarkupContainer container = new WebMarkupContainer("dummy") {

			@Override
			public String getMarkupId() {
				return "dummy";
			}

			@Override
			public Markup getAssociatedMarkup() {
				return Markup.of("<div wicket:id=\"dummy\"></div>");
			}

			@Override
			protected void onComponentTag(ComponentTag tag) {
				tag.setName("div");

				super.onComponentTag(tag);
			}
		};

		tester.startComponentInPage(container);

		tester.assertBehavior(tester.getLastRenderedPage().getPageRelativePath(), IziToastBehavior.class);

		assertEquals(0, container.getBehaviors(IziToastBehavior.class).size(), "must be added only to page");
		assertEquals(1, tester.getLastRenderedPage().getBehaviors(IziToastBehavior.class).size(),
				"must be added only once to page");

		tester.startPage(tester.getLastRenderedPage());

		assertEquals(0, container.getBehaviors(IziToastBehavior.class).size(), "must be added only to page");
		assertEquals(1, tester.getLastRenderedPage().getBehaviors(IziToastBehavior.class).size(),
				"must be added only once to page");
	}

}
