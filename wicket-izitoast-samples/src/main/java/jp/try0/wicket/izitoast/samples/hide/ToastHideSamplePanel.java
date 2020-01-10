package jp.try0.wicket.izitoast.samples.hide;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;

import jp.try0.wicket.izitoast.core.IToast;
import jp.try0.wicket.izitoast.core.Toast;
import jp.try0.wicket.izitoast.core.ToastOption;
import jp.try0.wicket.izitoast.core.ajax.ToastAjaxEventBehavior;
import jp.try0.wicket.izitoast.samples.AbstractSamplePanel;
import jp.try0.wicket.izitoast.samples.HomePage;

public class ToastHideSamplePanel extends AbstractSamplePanel {

	public ToastHideSamplePanel(String id) {
		super(id, Model.of("Hide toast with ajax"));
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		HomePage page = (HomePage) getPage();

		WebMarkupContainer hideButtonsContainer = new WebMarkupContainer("hideButtonsContainer");
		hideButtonsContainer.setOutputMarkupId(true);
		add(hideButtonsContainer);

		List<Toast> showToastList = new ArrayList<>();
		add(new AjaxLink<Void>("btnShowHideTarget") {

			@Override
			public void onClick(AjaxRequestTarget target) {

				ToastAjaxEventBehavior ajaxBehavior = new ToastAjaxEventBehavior() {

					@Override
					protected void respond(AjaxRequestTarget target, IToast bindToast) {

						showToastList.remove(bindToast);
						target.add(hideButtonsContainer);
					}

				};

				getPage().add(ajaxBehavior);

				String id = String.valueOf(page.toastId.incrementAndGet());
				Toast toast = Toast.info("ID:" + id);
				ToastOption option = toast.getToastOption();
				option.setId(id);
				option.setTimeout(false);
				option.setProgressBar(false);

				ToastAjaxEventBehavior.setOnClosing(toast, ajaxBehavior);

				toast.show(target);

				showToastList.add(toast);

				target.add(hideButtonsContainer);
			}

		});

		hideButtonsContainer.add(new ListView<Toast>("hideButtons", showToastList) {

			@Override
			protected void populateItem(ListItem<Toast> item) {

				Toast toast = item.getModelObject();
				String toastId = toast.getToastOption().getId();

				item.add(new AjaxLink<Void>("btnHide") {
					{
						setOutputMarkupId(true);

						add(new Label("lblToastId", "Hide toast: " + toastId));

					}

					@Override
					public void renderHead(IHeaderResponse response) {
						super.renderHead(response);

						response.render(OnDomReadyHeaderItem
								.forScript("$('#" + getMarkupId() + "').on('mouseover', function(e) {"

										+ "setTimeout(function() {"
										+ "    if (!$(e.target).is(':hover')) return;"
										+ "    var toast = $('#" + toastId + "');"
						// remove conflict class. iziToast, Semantic UI"
										+ "    toast.removeClass('fadeIn');"
										+ "    toast.transition('jiggle');"
										+ "}, 500);"

										+ "});"));
					}

					@Override
					public void onClick(AjaxRequestTarget target) {
						Toast toast = item.getModelObject();
						toast.hide(target);
					}
				});

			}

		});

	}

}
