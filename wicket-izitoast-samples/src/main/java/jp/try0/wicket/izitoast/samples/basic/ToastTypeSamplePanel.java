package jp.try0.wicket.izitoast.samples.basic;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.Model;

import jp.try0.wicket.izitoast.core.Toast;
import jp.try0.wicket.izitoast.core.Toast.ToastType;
import jp.try0.wicket.izitoast.core.ToastOption;
import jp.try0.wicket.izitoast.core.config.IziToastSetting;
import jp.try0.wicket.izitoast.samples.AbstractSamplePanel;

public class ToastTypeSamplePanel extends AbstractSamplePanel {

	public ToastTypeSamplePanel(String id) {
		super(id, Model.of("Types"));
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new AjaxLink<Void>("btnInformation") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				getPage().info("Information feedback message");

				showLevelToastCode("info(\"Information feedback message\");", target);
			}
		});

		add(new AjaxLink<Void>("btnSuccess") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				getPage().success("Success feedback message");

				showLevelToastCode("success(\"Success feedback message\");", target);
			}
		});

		add(new AjaxLink<Void>("btnWarning") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				getPage().warn("Warning feedback message");

				showLevelToastCode("warn(\"Warning feedback message\");", target);
			}
		});

		add(new AjaxLink<Void>("btnError") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				getPage().error("Error feedback message");

				showLevelToastCode("error(\"Error feedback message\");", target);
			}
		});

		add(new AjaxLink<Void>("btnPlain") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				ToastOption option = new ToastOption();
				option.setMessage("Plain type");
				if (IziToastSetting.get().hasGlobalOptions()) {
					option.merge(IziToastSetting.get().getGlobalOption().get());
				}
				Toast.create(ToastType.PLAIN, option).show(target);
			}

		});

		add(new AjaxLink<Void>("btnQuestion") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				ToastOption option = new ToastOption();
				option.setMessage("Question type");
				if (IziToastSetting.get().hasGlobalOptions()) {
					option.merge(IziToastSetting.get().getGlobalOption().get());
				}
				Toast.create(ToastType.QUESTION, option).show(target);
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
