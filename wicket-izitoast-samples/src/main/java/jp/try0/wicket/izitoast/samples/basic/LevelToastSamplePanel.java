package jp.try0.wicket.izitoast.samples.basic;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.Model;

import jp.try0.wicket.izitoast.core.Toast;
import jp.try0.wicket.izitoast.core.Toast.ToastType;
import jp.try0.wicket.izitoast.core.ToastOption;
import jp.try0.wicket.izitoast.samples.AbstractSamplePanel;

public class LevelToastSamplePanel extends AbstractSamplePanel {

	public LevelToastSamplePanel(String id) {
		super(id, Model.of("Levels"));
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new AjaxLink<Void>("btnInformation") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				info("Information feedback message");

				showLevelToastCode("info(\"Information feedback message\");", target);
			}
		});

		add(new AjaxLink<Void>("btnSuccess") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				success("Success feedback message");

				showLevelToastCode("success(\"Success feedback message\");", target);
			}
		});

		add(new AjaxLink<Void>("btnWarning") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				warn("Warning feedback message");

				showLevelToastCode("warn(\"Warning feedback message\");", target);
			}
		});

		add(new AjaxLink<Void>("btnError") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				error("Error feedback message");

				showLevelToastCode("error(\"Error feedback message\");", target);
			}
		});

	}

	private void showLevelToastCode(String sourceCode, AjaxRequestTarget target) {
		ToastOption option = newSourceToastOption();
		option.setTarget("#level-toasts");
		option.setMessage(StringEscapeUtils.escapeJava(sourceCode));
		Toast.create(ToastType.PLAIN, option).show(target);
	}

}
