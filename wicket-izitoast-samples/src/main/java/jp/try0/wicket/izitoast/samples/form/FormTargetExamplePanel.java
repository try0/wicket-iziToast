package jp.try0.wicket.izitoast.samples.form;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.StringValidator;

import jp.try0.wicket.izitoast.core.IToast;
import jp.try0.wicket.izitoast.core.Toast;
import jp.try0.wicket.izitoast.core.behavior.IziToastBehavior;
import jp.try0.wicket.izitoast.core.config.DefaultToastTargetLinker;
import jp.try0.wicket.izitoast.core.config.IToastTargetLinker;
import jp.try0.wicket.izitoast.samples.AbstractSamplePanel;

public class FormTargetExamplePanel extends AbstractSamplePanel {

	private IModel<String> firstName = new Model<>();

	private IModel<String> lastName = new Model<>();

	private IModel<Integer> age = new Model<>();

	private IModel<Boolean> acceptLicense = new Model<>(false);

	public FormTargetExamplePanel(String id) {
		super(id, Model.of("Target components"));
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new Form<Void>("formTargets") {

			{
				setOutputMarkupId(true);
				add(new AjaxCheckBox("chkGroupingError", new Model<>(false)) {

					@Override
					protected void onUpdate(AjaxRequestTarget target) {
						List<IziToastBehavior> behaviors = getPage().getBehaviors(IziToastBehavior.class);

						if (behaviors.isEmpty()) {
							return;
						}

						IziToastBehavior behavior = behaviors.get(0);

						if (getModelObject()) {
							behavior.setToastTargetLinker(new IToastTargetLinker() {

								@Override
								public void setTarget(IToast toast, Component component) {

									if (!(component instanceof FormComponent)) {
										return;
									}

									FormComponent<?> formComponent = (FormComponent<?>) component;
									toast.getToastOption().setTarget("#" + formComponent.getForm().getMarkupId());
									toast.getToastOption().setTimeout(0);
								}
							});
						} else {
							behavior.setToastTargetLinker(new DefaultToastTargetLinker());
						}
					}
				});

				add(new RequiredTextField<String>("txtFirstName", firstName) {
					{
						setOutputMarkupId(true);
						add(StringValidator.minimumLength(2));
					}
				});

				add(new RequiredTextField<String>("txtLastName", lastName) {
					{
						setOutputMarkupId(true);
						add(StringValidator.minimumLength(2));
					}
				});

				add(new NumberTextField<Integer>("txtAge", age, Integer.class) {
					{
						setOutputMarkupId(true);
						setRequired(true);
						setMinimum(0);
					}
				});

				add(new CheckBox("chkAcceptLicense", acceptLicense) {
					{
						setOutputMarkupId(true);
						add(new IValidator<Boolean>() {

							@Override
							public void validate(IValidatable<Boolean> validatable) {

								if (!Boolean.TRUE.equals(validatable.getValue())) {
									error(new ValidationError().addKey("Required"));
								}

							}
						});
					}

				});

				add(new AjaxSubmitLink("btnSubmit") {

					@Override
					protected void onSubmit(AjaxRequestTarget target) {
						target.focusComponent(getForm());

						Toast toast = Toast.success("Submit form <br><br>First Name: " + firstName.getObject()
								+ "<br>Last Name: " + lastName.getObject());
						toast.getToastOption().setTarget("#" + getForm().getMarkupId());
						toast.show(target);
					}

				});
			}

		});

	}

}
