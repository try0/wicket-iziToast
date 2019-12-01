package jp.try0.wicket.izitoast.core.test;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;



/**
 * Test base class.
 *
 * @author Ryo Tsunoda
 *
 */
public abstract class AbstractIziToastTest {


	private WebApplication application;
	private WicketTester tester;

	@BeforeEach
	public void setUp() {
		application = newWebApplication();
		tester = new WicketTester(application);
	}


	@AfterEach
	public final void after() throws Exception {
		if (tester != null) {
			tester.destroy();
		}
	}

	protected WebApplication getWebApplication() {
		return application;
	}

	protected WicketTester getWicketTester() {
		return tester;
	}

	/**
	 * Create mock application.
	 *
	 * @return
	 */
	protected WebApplication newWebApplication() {
		return new WebApplication() {

			@Override
			protected void init() {
				super.init();
				onInitializeApplication(this);
			}

			@Override
			public Class<? extends Page> getHomePage() {
				return AbstractIziToastTest.this.getHomePage();
			}

		};
	}

	protected void onInitializeApplication(final WebApplication application) {
		// noop
	}

	/**
	 * Get mock home page.
	 *
	 * @return
	 */
	protected Class<? extends Page> getHomePage() {
		return IziToastTestPage.class;
	}

}
