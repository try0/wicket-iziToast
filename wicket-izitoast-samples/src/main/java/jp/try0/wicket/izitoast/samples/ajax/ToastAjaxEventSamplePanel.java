package jp.try0.wicket.izitoast.samples.ajax;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.Model;

import jp.try0.wicket.izitoast.core.IToast;
import jp.try0.wicket.izitoast.core.Toast;
import jp.try0.wicket.izitoast.core.Toast.ToastType;
import jp.try0.wicket.izitoast.core.ajax.ToastAjaxEventBehavior;
import jp.try0.wicket.izitoast.samples.AbstractSamplePanel;
import jp.try0.wicket.izitoast.samples.HomePage;

public class ToastAjaxEventSamplePanel extends AbstractSamplePanel {

	public ToastAjaxEventSamplePanel(String id) {
		super(id, Model.of("Toast event with ajax"));
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		HomePage page = (HomePage) getPage();

		add(new AjaxLink<Void>("btnAjaxOnClosing") {

			@Override
			public void onClick(AjaxRequestTarget target) {

				ToastAjaxEventBehavior ajaxBehavior = new ToastAjaxEventBehavior() {

					@Override
					protected void respond(AjaxRequestTarget target, IToast eventTargetToast) {
						Toast toast = Toast.create(ToastType.WARNING,
								"Raised OnClosing event. Toast ID: "
										+ eventTargetToast.getToastOption().getId());

						toast.show(target);

						getPage().remove(this);
					}
				};
				getPage().add(ajaxBehavior);

				String id = String.valueOf(page.toastId.incrementAndGet());
				Toast toast = Toast.create(ToastType.ERROR, "ID:" + id);
				toast.getToastOption().setId(id);
				ToastAjaxEventBehavior.setOnClosing(toast, ajaxBehavior);

				toast.show(target);
			}

		});

		add(new AjaxLink<Void>("btnAjaxOnClosed") {

			@Override
			public void onClick(AjaxRequestTarget target) {

				ToastAjaxEventBehavior ajaxBehavior = new ToastAjaxEventBehavior() {

					@Override
					protected void respond(AjaxRequestTarget target, IToast eventTargetToast) {
						Toast toast = Toast.create(ToastType.INFO,
								"Raised OnClosed event. Toast ID: "
										+ eventTargetToast.getToastOption().getId());

						toast.show(target);

						getPage().remove(this);
					}
				};
				getPage().add(ajaxBehavior);

				String id = String.valueOf(page.toastId.incrementAndGet());
				Toast toast = Toast.create(ToastType.WARNING, "ID:" + id);
				toast.getToastOption().setId(id);
				ToastAjaxEventBehavior.setOnClosed(toast, ajaxBehavior);

				toast.show(target);
			}

		});

		add(new AjaxLink<Void>("btnAjaxOnOpening") {

			@Override
			public void onClick(AjaxRequestTarget target) {

				ToastAjaxEventBehavior ajaxBehavior = new ToastAjaxEventBehavior() {

					@Override
					protected void respond(AjaxRequestTarget target, IToast eventTargetToast) {
						Toast toast = Toast.create(ToastType.SUCCESS,
								"Raised OnOpening event. Toast ID: "
										+ eventTargetToast.getToastOption().getId());

						toast.show(target);

						getPage().remove(this);
					}
				};
				getPage().add(ajaxBehavior);

				String id = String.valueOf(page.toastId.incrementAndGet());
				Toast toast = Toast.create(ToastType.INFO, "ID:" + id);
				toast.getToastOption().setId(id);
				ToastAjaxEventBehavior.setOnOpening(toast, ajaxBehavior);

				toast.show(target);
			}

		});

		add(new AjaxLink<Void>("btnAjaxOnOpened") {

			@Override
			public void onClick(AjaxRequestTarget target) {

				ToastAjaxEventBehavior ajaxBehavior = new ToastAjaxEventBehavior() {

					@Override
					protected void respond(AjaxRequestTarget target, IToast eventTargetToast) {
						Toast toast = Toast.create(ToastType.ERROR,
								"Raised OnOpend event. Toast ID: "
										+ eventTargetToast.getToastOption().getId());

						toast.show(target);

						getPage().remove(this);
					}
				};
				getPage().add(ajaxBehavior);

				String id = String.valueOf(page.toastId.incrementAndGet());
				Toast toast = Toast.create(ToastType.SUCCESS, "ID:" + id);
				toast.getToastOption().setId(id);
				ToastAjaxEventBehavior.setOnOpened(toast, ajaxBehavior);

				toast.show(target);
			}

		});
	}

}
