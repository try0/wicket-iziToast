# wicket-iziToast
[Apache Wicket](https://wicket.apache.org/) utilities for using [iziToast](http://izitoast.marcelodolza.com/).
wicket-iziToast converts the feedback message to JavaScript to display the toast.



[![Build Status](https://github.com/try0/wicket-iziToast/workflows/Java%20CI/badge.svg)](https://github.com/try0/wicket-iziToast/actions?query=workflow%3A%22Java+CI%22)
[![Maven Central](https://img.shields.io/maven-central/v/jp.try0.wicket/wicket-izitoast-core.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22jp.try0.wicket%22%20AND%20a:%22wicket-izitoast-core%22)



# Version


| Version | Wicket | iziToast |
| ---- | ---- | ---- |
| [1.1.0](https://search.maven.org/artifact/jp.try0.wicket/wicket-izitoast-core/1.1.0/jar)  | 8.x | 1.4.0 |
| [2.1.0](https://search.maven.org/artifact/jp.try0.wicket/wicket-izitoast-core/2.1.0/jar) | 9.x | 1.4.0 |



# Demo
Deployed [wicket-izitoast-samples](https://try0.jp/app/wicket-izitoast-samples/) module.



# Usage

[Examples](https://github.com/try0/wicket-iziToast/wiki)

Add wicket-izitoast-core dependency.  
Wicket 8.x
```xml
<dependency>
    <groupId>jp.try0.wicket</groupId>
    <artifactId>wicket-izitoast-core</artifactId>
    <version>1.1.0</version>
</dependency>
```
Wicket 9.x
```xml
<dependency>
    <groupId>jp.try0.wicket</groupId>
    <artifactId>wicket-izitoast-core</artifactId>
    <version>2.1.0</version>
</dependency>
```

### Initialize Settings
You can set default values, in the application initialize process(Application#init).
```java
IziToastSetting
.createInitializer(this)
.setAutoAppendBehavior(true)
.setGlobalOption(option) // default iziToast option. apply in client-side (execute iziToast.settings(option))
.setGlobalEachLevelOptions(perLevelOptions) // default option per levels. apply in server-side.
.setToastMessageCombiner(combiner) // combiner that combine same level feedback messages.
.initialize();
```
#### Properties
##### AutoAppendBehavior
if true add IziToastBehavior to page automatically.

##### GlobalOption
Default toast option. Execute iziToast.settings(option) in client-side and apply the default option.

##### GlobalEachLevelOptions
Default toast option per levels.

```java
ToastOption defaultInfoOption = new ToastOption();
// TODO set option values

EachLevelToastOptions options = EachLevelToastOptions.builder()
        .setInfoOption(defaultInfoOption)
        .get();

// TODO setGlobalEachLevelOptions(options)
```


Option priority level
Default option per levels (GlobalEachLevelOptions) > Default option (GlobalOption)

##### ToastMessageCombiner
Combiner that combines messages for each toast level.

```java
ToastMessageCombiner combiner = new ToastMessageCombiner();
combiner.setPrefix("・");

// TODO setToastMessageCombiner(combiner)
```

If execute this Java code.
```java
error("message1");
error("message2");
```

Messages are combined and displayed in one toast.

![errors](https://user-images.githubusercontent.com/17096601/71779629-dd56bc80-2ffa-11ea-9f45-b75b1f86d5da.PNG)


### Display toast

add IziToastBehavior to page.
(If you set setAutoAppendBehavior to true when initializing settings, no need this code.)
```java
add(new IziToastBehavior());
```

IziToastBehavior converts feedback messages to JavaScript for displaying toast.


Success toast
* org.apache.wicket.Component#success(Serializable)
* org.apache.wicket.Session#success(Serializable)

![success](https://user-images.githubusercontent.com/17096601/71773891-9d66e980-2fa8-11ea-8a3f-83e804549c58.PNG)


Information toast
* org.apache.wicket.Component#info(Serializable)
* org.apache.wicket.Session#info(Serializable)

![info](https://user-images.githubusercontent.com/17096601/71773896-aa83d880-2fa8-11ea-9149-e0c99aec01b9.PNG)


Warning toast
* org.apache.wicket.Component#warn(Serializable)
* org.apache.wicket.Session#warn(Serializable)

![warn](https://user-images.githubusercontent.com/17096601/71773898-b40d4080-2fa8-11ea-81aa-0776713cdb2e.PNG)


Error toast
* org.apache.wicket.Component#error(Serializable)
* org.apache.wicket.Session#error(Serializable)
* org.apache.wicket.Component#fatal(Serializable)
* org.apache.wicket.Session#fatal(Serializable)

![error](https://user-images.githubusercontent.com/17096601/71773873-50831300-2fa8-11ea-9df5-0474e7a1b7c1.PNG)



### Display toast manually
In this case, need instance of class that implemented IHeaderResponse or AjaxRequestTarget.

```java
Toast.create(toastLevel, message)
.show(target);
```

