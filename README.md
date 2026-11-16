# wicket-iziToast

English | [日本語](README.ja.md)

[Apache Wicket](https://wicket.apache.org/) utilities for using [iziToast](http://izitoast.marcelodolza.com/).  
wicket-iziToast converts Wicket feedback messages into JavaScript that displays them as toasts.



[![Build Status](https://github.com/try0/wicket-iziToast/workflows/Java%20CI/badge.svg)](https://github.com/try0/wicket-iziToast/actions?query=workflow%3A%22Java+CI%22)
[![Maven Central](https://img.shields.io/maven-central/v/jp.try0.wicket/wicket-izitoast-core.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22jp.try0.wicket%22%20AND%20a:%22wicket-izitoast-core%22)



# Version


| Version | Wicket | iziToast |
| ---- | ---- | ---- |
| [4.0.0](https://search.maven.org/artifact/jp.try0.wicket/wicket-izitoast-core/4.0.0/jar) | 11.x | 1.4.0 |
| [3.0.0](https://search.maven.org/artifact/jp.try0.wicket/wicket-izitoast-core/3.0.0/jar) | 10.x | 1.4.0 |




# Demo
A live demo of the [wicket-izitoast-samples](https://try0.jp/app/wicket-izitoast-samples/) module is available.



# Usage

[Examples](https://github.com/try0/wicket-iziToast/wiki)

Add the wicket-izitoast-core dependency.  
Wicket 11.x
```xml
<dependency>
    <groupId>jp.try0.wicket</groupId>
    <artifactId>wicket-izitoast-core</artifactId>
    <version>4.0.0</version>
</dependency>
```

Wicket 10.x
```xml
<dependency>
    <groupId>jp.try0.wicket</groupId>
    <artifactId>wicket-izitoast-core</artifactId>
    <version>3.0.0</version>
</dependency>
```


### Initialize Settings
You can configure defaults during application initialization (Application#init).
```java
IziToastSetting
.createInitializer(this)
.setAutoAppendBehavior(true)
.setGlobalOption(option) // default iziToast options, applied on the client side via iziToast.settings(option)
.setGlobalEachLevelOptions(perLevelOptions) // default options for each level, applied on the server side
.setToastMessageCombiner(combiner) // combines feedback messages of the same level
.initialize();
```
#### Properties
##### AutoAppendBehavior
If true, IziToastBehavior is automatically added to every page.

##### GlobalOption
Default toast options. They are applied on the client side by calling iziToast.settings(option).

##### GlobalEachLevelOptions
Default toast options for each level.

```java
ToastOption defaultInfoOption = new ToastOption();
// TODO set option values

EachLevelToastOptions options = EachLevelToastOptions.builder()
        .setInfoOption(defaultInfoOption)
        .get();

// TODO setGlobalEachLevelOptions(options)
```


Option priority:  
Per-level default options (GlobalEachLevelOptions) > Default options (GlobalOption)

##### ToastMessageCombiner
Combines messages of the same toast level into a single toast.

```java
ToastMessageCombiner combiner = new ToastMessageCombiner();
combiner.setPrefix("・");

// TODO setToastMessageCombiner(combiner)
```

For example, with the following code:
```java
error("message1");
error("message2");
```

the messages are combined and displayed in a single toast.

![errors](https://user-images.githubusercontent.com/17096601/71779629-dd56bc80-2ffa-11ea-9f45-b75b1f86d5da.PNG)


### Display toast

Add IziToastBehavior to the page.  
(Not needed if you call setAutoAppendBehavior(true) during initialization.)
```java
add(new IziToastBehavior());
```

IziToastBehavior converts feedback messages into JavaScript that displays toasts.


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
To show a toast manually, you need an IHeaderResponse or AjaxRequestTarget instance.

```java
Toast.create(toastLevel, message)
.show(target);
```

