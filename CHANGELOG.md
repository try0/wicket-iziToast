# Change Log

# Wicket9

## 2.1.0

* Add jp.try0.wicket.izitoast.core.Toast#settings(IHeaderResponse, IToastOption)
* Add jp.try0.wicket.izitoast.core.Toast#settings(IPartialPageRequestHandler, IToastOption)
* Add jp.try0.wicket.izitoast.core.Toast#create(FeedbackMessage)
* add jp.try0.wicket.izitoast.core.Toast#creates(Iterable<FeedbackMessage>)

## 2.0.0

* For Wicket 9


# Wicket8

## 1.1.0

* Add jp.try0.wicket.izitoast.core.Toast#settings(IHeaderResponse, IToastOption)
* Add jp.try0.wicket.izitoast.core.Toast#settings(IPartialPageRequestHandler, IToastOption)
* Add jp.try0.wicket.izitoast.core.Toast#create(FeedbackMessage)
* add jp.try0.wicket.izitoast.core.Toast#creates(Iterable<FeedbackMessage>)

## 1.0.0

* For Wicket 8

## 0.5.0

* Supports work with the feedback message reporter component
* Move jp.try0.wicket.izitoast.core.behavior.IziToastBehavior$ToastMessageCombiner to jp.try0.wicket.izitoast.core.config.ToastMessageCombiner
* jp.try0.wicket.izitoast.core.config.ToastMessageCombiner#combine, Changes the argument and return type from the Stream to the List
* Rename jp.try0.wicket.izitoast.core.ToastOption#overwrite(ToastOption) => #merge(ToastOption), and create new mearged option => update members

## 0.4.0

* Add jp.try0.wicket.izitoast.core.ajax.ToastAjaxEventBehavior
* Add question type to jp.try0.wicket.izitoast.core.Toast.ToastType
* Add hide(final IPartialPageRequestHandler target) to jp.try0.wicket.izitoast.core.Toast (instance method)
* Rename jp.try0.wicket.izitoast.core.Toast.ToastLevel to ToastType
* Rename jp.try0.wicket.izitoast.core.IToast#getToastLevel() to getToastType()
* Rename jp.try0.wicket.izitoast.core.Toast.SCRIPT_HIDE_TOAST to SCRIPT_HIDE_ALL_TOAST
* Rename jp.try0.wicket.izitoast.core.Toast.hide(IHeaderResponse) to hideAll(IHeaderResponse) (static method)
* Update wicket version
* Update junit version


## 0.3.1

* Fix [Issue1]("https://github.com/try0/wicket-iziToast/issues/1").

## 0.3.0

* Rename maven artifactId, package

