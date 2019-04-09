package jp.try0.wicket.iziToast.samples;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		info("info");
		success("success");
		warn("warn");
		error("error");

	}
}
