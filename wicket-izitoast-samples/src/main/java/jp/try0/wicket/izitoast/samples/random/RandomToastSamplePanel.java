package jp.try0.wicket.izitoast.samples.random;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import jp.try0.wicket.izitoast.core.IToastOption;
import jp.try0.wicket.izitoast.core.Toast;
import jp.try0.wicket.izitoast.core.Toast.ToastType;
import jp.try0.wicket.izitoast.core.ToastOption;
import jp.try0.wicket.izitoast.samples.AbstractSamplePanel;

public class RandomToastSamplePanel extends AbstractSamplePanel {

	private static class RandomString {
		final String[] items;

		RandomString(String[] items) {
			this.items = items;
		}

		String next() {
			return items[new Random().nextInt(items.length)];
		}
	}

	private static final String[] POSITIONS = new String[] { "topLeft", "topRight", "bottomLeft", "bottomRight",
			"topCenter",
			"bottomCenter", "center" };
	private static final String[] TRANSITION_IN_ANIMATIONS = new String[] { "bounceInLeft", "bounceInRight",
			"bounceInUp",
			"bounceInDown", "fadeIn", "fadeInDown", "fadeInUp", "fadeInLeft", "fadeInRight", "flipInX" };
	private static final String[] TRANSITION_OUT_ANIMATIONS = new String[] { "fadeOut", "fadeOutUp", "fadeOutDown",
			"fadeOutLeft",
			"fadeOutRight", "flipOutX" };

	private static final RandomString positions = new RandomString(POSITIONS);
	private static final RandomString transitionIns = new RandomString(TRANSITION_IN_ANIMATIONS);
	private static final RandomString transitionOuts = new RandomString(TRANSITION_OUT_ANIMATIONS);

	private WebMarkupContainer dummyContainer;

	public RandomToastSamplePanel(String id) {
		super(id, Model.of("Random"));
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new AjaxLink<Void>("btnRandomeOption") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				showRandomToast(target);
			}
		});

		AtomicInteger counter = new AtomicInteger(0);

		add(new AjaxLink<Void>("btnRandomeOptionX5") {

			private final AjaxLink<Void> self = this;
			{
				setOutputMarkupId(true);

				add(new AttributeAppender("class", () -> {
					if (counter.intValue() != 0) {
						return " disabled loading ";
					} else {
						return "";
					}
				}));
			}

			@Override
			public void onClick(AjaxRequestTarget target) {
				counter.incrementAndGet();
				showRandomToast(target);

				dummyContainer.add(new AjaxSelfUpdatingTimerBehavior(Duration.ofMillis(800)) {

					@Override
					protected void onPostProcessTarget(AjaxRequestTarget target) {
						super.onPostProcessTarget(target);
						showRandomToast(target);

						if (counter.incrementAndGet() == 5) {
							stop(target);

							target.add(dummyContainer);
							target.add(self);
							counter.set(0);
						}
					}

				});

				target.add(dummyContainer);
				target.add(self);
			}
		});
		add(dummyContainer = new WebMarkupContainer("dummy") {
			{
				setOutputMarkupId(true);
			}
		});
	}

	private void showRandomToast(AjaxRequestTarget target) {

		ToastOption option = new ToastOption();
		option.setPosition(positions.next());
		option.setTimeout(1500 + new Random().nextInt(5000));
		option.setTransitionIn(transitionIns.next());
		option.setTransitionOut(transitionOuts.next());
		option.setMessage("Hello!!");
		option.setMaxWidth("800");
		option.setDisplayMode(new Random().nextInt(3));
		option.setAnimateInside(new Random().nextBoolean());
		option.setBalloon(new Random().nextBoolean());
		option.setClose(new Random().nextBoolean());
		option.setCloseOnClick(new Random().nextBoolean());
		option.setCloseOnEscape(new Random().nextBoolean());
		option.setDrag(new Random().nextBoolean());
		option.setOverlay(new Random().nextBoolean());
		option.setOverlayClose(new Random().nextBoolean());
		option.setPauseOnHover(new Random().nextBoolean());
		option.setProgressBar(new Random().nextBoolean());

		ToastOption option2 = newSourceToastOption();
		option2.setTarget("#text");
		option2.setButtons(
				"[['<button><i class=\\'play icon\\'></i>Run</button>', function (instance, toast) { eval(toast.querySelector('#dispScript').textContent); }]]");

		Toast toast;

		int number = new Random().nextInt(5);
		switch (number) {
		case 0: {
			option.setTitle("Information");
			toast = Toast.create(ToastType.INFO, option);

			break;
		}
		case 1: {
			option.setTitle("Success");
			toast = Toast.create(ToastType.SUCCESS, option);

			break;
		}
		case 2: {
			option.setTitle("Warning");
			toast = Toast.create(ToastType.WARNING, option);

			break;
		}
		case 3: {
			option.setTitle("Error");
			toast = Toast.create(ToastType.ERROR, option);

			break;
		}

		default:
			option.setTitle("Simple");
			toast = Toast.create(ToastType.PLAIN, option);
			break;
		}

		toast.show(target);

		String forDispPrettyScript = getScriptForDisplay(toast);
		option2.setMessage("<pre id='dispScript'>" + forDispPrettyScript + "</pre>");
		Toast.create(ToastType.PLAIN, option2).show(target);

	}

	private String getScriptForDisplay(Toast toast) {
		return toast.getScriptForDisplay().replace(toast.getToastOption().toJsonString(),
				StringEscapeUtils.escapeJson(getOptionPrettyString(toast.getToastOption())));
	}

	private String getOptionPrettyString(IToastOption option) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			String prettyFormatted = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(option);
			return prettyFormatted.replaceAll("styleClass", "class");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
