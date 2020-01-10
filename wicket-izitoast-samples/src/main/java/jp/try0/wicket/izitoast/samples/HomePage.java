package jp.try0.wicket.izitoast.samples;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import jp.try0.wicket.izitoast.core.Toast;
import jp.try0.wicket.izitoast.core.Toast.ToastType;
import jp.try0.wicket.izitoast.samples.ajax.ToastAjaxEventSamplePanel;
import jp.try0.wicket.izitoast.samples.basic.ToastTypeSamplePanel;
import jp.try0.wicket.izitoast.samples.hide.ToastHideSamplePanel;
import jp.try0.wicket.izitoast.samples.random.RandomToastSamplePanel;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public static String getGitHubUrl(Class<?> clazz) {
		String base = "https://github.com/try0/wicket-iziToast/tree/develop/wicket-izitoast-samples/src/main/java/";
		return base + clazz.getName().replaceAll(Pattern.quote("."), "/") + ".java";
	}


	public AtomicInteger toastId = new AtomicInteger(0);

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

		info(Toast.create(ToastType.INFO, "infomation message", "Information"));
		success(Toast.create(ToastType.SUCCESS, "success message", "Success"));
		warn(Toast.create(ToastType.WARNING, "warning message", "Warning"));
		error(Toast.create(ToastType.ERROR, "error message", "Error"));
	}


	@Override
	public void onEvent(IEvent<?> event) {
		super.onEvent(event);

		if (event.getPayload() instanceof AjaxRequestTarget) {
			AjaxRequestTarget t = ((AjaxRequestTarget) event.getPayload());

			// Stop event bubbling.
			// When both option.close and option.closeOnClick are true and clicked close button, OnClosing, OnClosed raised twice.
			t.appendJavaScript("document.querySelectorAll('.iziToast-close').forEach(function(btnClose) { "
					+ "    btnClose.addEventListener('click', function(e) {"
					+ "        e.stopPropagation();"
					+ "    });"
					+ "})");

		}
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new ExternalLink("linkToSource", HomePage.getGitHubUrl(getClass())));

		add(new ToastTypeSamplePanel("LevelToastSamplePanel"));

		add(new RandomToastSamplePanel("RandomToastSamplePanel"));

		add(new ToastAjaxEventSamplePanel("ToastAjaxEventSamplePanel"));

		add(new ToastHideSamplePanel("ToastHideSamplePanel"));




		add(new AjaxLink<Void>("btnHide") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				Toast.hideAll(target);
			}
		});

		add(new AjaxLink<Void>("btnDestroy") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				Toast.destroy(target);
			}
		});

	}


}
