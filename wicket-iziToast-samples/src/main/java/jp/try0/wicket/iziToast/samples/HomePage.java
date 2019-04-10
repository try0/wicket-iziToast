package jp.try0.wicket.iziToast.samples;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import jp.try0.wicket.iziToast.core.Toast;
import jp.try0.wicket.iziToast.core.Toast.ToastLevel;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		info("info");
		success("success");
		warn("warn");
		error("error");

		info("info2");
		success("success2");
		warn("warn2");
		error("error2");

		info(Toast.create(ToastLevel.INFO, "infomation message", "Information"));
		success(Toast.create(ToastLevel.SUCCESS, "success message", "Success"));
		warn(Toast.create(ToastLevel.WARNING, "warning message", "Warning"));
		error(Toast.create(ToastLevel.ERROR, "error message", "Error"));

	}
}
