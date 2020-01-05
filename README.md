# wicket-iziToast
[Apache Wicket](https://wicket.apache.org/) utilities for using [iziToast](http://izitoast.marcelodolza.com/).  
wicket-iziToast converts feedback messages to JavaScript for displaying toast.


[![Build Status](https://travis-ci.org/try0/wicket-iziToast.svg?branch=master)](https://travis-ci.org/try0/wicket-iziToast)
[![codecov](https://codecov.io/gh/try0/wicket-iziToast/branch/master/graph/badge.svg)](https://codecov.io/gh/try0/wicket-iziToast)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jp.try0.wicket%3Awicket-iziToast-parent&metric=alert_status)](https://sonarcloud.io/dashboard?id=jp.try0.wicket%3Awicket-izitoast-parent)
[![Maven Central](https://img.shields.io/maven-central/v/jp.try0.wicket/wicket-izitoast-core.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22jp.try0.wicket%22%20AND%20a:%22wicket-izitoast-core%22)



# Version
0.3.1  
wicket:8.x iziToast:1.4.0



# Demo
Deployed [wicket-izitoast-samples](https://try0.jp/app/wicket-izitoast-samples/) module.



# Usage

Add wicket-izitoast-core dependency.
```xml
<dependency>
    <groupId>jp.try0.wicket</groupId>
    <artifactId>wicket-izitoast-core</artifactId>
    <version>0.3.1</version>
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


### Display toast using Behavior

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

