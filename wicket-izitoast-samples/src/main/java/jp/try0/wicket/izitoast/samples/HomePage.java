package jp.try0.wicket.izitoast.samples;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import jp.try0.wicket.izitoast.core.IToastOption;
import jp.try0.wicket.izitoast.core.Toast;
import jp.try0.wicket.izitoast.core.ToastOption;
import jp.try0.wicket.izitoast.core.Toast.ToastLevel;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

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

	private void showLevelToastCode(String sourceCode, AjaxRequestTarget target) {
		ToastOption option = newSourceToastOption();
		option.setTarget("#level-toasts");
		option.setMessage(StringEscapeUtils.escapeJava(sourceCode) );
		Toast.create(ToastLevel.PLAIN, option).show(target);
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

		add(new AjaxLink<Void>("btnRandomeOption") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				showRandomToast(target);
			}
		});

		AtomicInteger counter = new AtomicInteger(0);

		add( new AjaxLink<Void>("btnRandomeOptionX5") {

			private final AjaxLink<Void> self = this;
			{
				setOutputMarkupId(true);

				add(new AttributeAppender("class", () ->  {
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

				dummyContainer.add(new AjaxSelfUpdatingTimerBehavior(Duration.milliseconds(800)) {

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
		add(dummyContainer =new WebMarkupContainer("dummy") {
			{
				setOutputMarkupId(true);
			}
		});

		add(new AjaxLink<Void>("btnHide") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				Toast.hide(target);
			}
		});

		add(new AjaxLink<Void>("btnDestroy") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				Toast.destroy(target);
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
			toast = Toast.create(ToastLevel.INFO, option);

			break;
		}
		case 1: {
			option.setTitle("Success");
			toast = Toast.create(ToastLevel.SUCCESS, option);

			break;
		}
		case 2: {
			option.setTitle("Warning");
			toast = Toast.create(ToastLevel.WARNING, option);

			break;
		}
		case 3: {
			option.setTitle("Error");
			toast = Toast.create(ToastLevel.ERROR, option);

			break;
		}

		default:
			option.setTitle("Simple");
			toast = Toast.create(ToastLevel.PLAIN, option);
			break;
		}

		toast.show(target);

		String forDispPrettyScript = getScriptForDisplay(toast);
		option2.setMessage("<pre id='dispScript'>" + forDispPrettyScript + "</pre>");
		Toast.create(ToastLevel.PLAIN, option2).show(target);

	}

	private ToastOption newSourceToastOption() {
		ToastOption option2 = new ToastOption();
		option2.setTimeout(false);
		option2.setDrag(false);
		option2.setClose(true);
		option2.setCloseOnClick(false);
		return option2;
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
