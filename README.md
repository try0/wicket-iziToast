# wicket-iziToast
[Apache Wicket](https://wicket.apache.org/) utilities for using [iziToast](http://izitoast.marcelodolza.com/).  


[![Build Status](https://travis-ci.org/try0/wicket-iziToast.svg?branch=master)](https://travis-ci.org/try0/wicket-iziToast)
[![codecov](https://codecov.io/gh/try0/wicket-iziToast/branch/master/graph/badge.svg)](https://codecov.io/gh/try0/wicket-iziToast)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jp.try0.wicket%3Awicket-iziToast-parent&metric=alert_status)](https://sonarcloud.io/dashboard?id=jp.try0.wicket%3Awicket-iziToast-parent)


# Version
0.2.0  
wicket:8.x iziToast:1.4.0



# Demo
Deployed [wicket-iziToast-samples](https://try0.jp/app/wicket-iziToast-samples/) module.



# Usage
wicket-iziToast is under development.
version 0.x.x is test release.

```
<dependency>
    <groupId>jp.try0.wicket</groupId>
    <artifactId>wicket-iziToast-core</artifactId>
    <version>0.2.0</version>
</dependency>
```


### Initialize Settings
You can set default values, in the application initialize process(Application#init).
```java
IziToastSetting
.createInitializer(this)
.setAutoAppendBehavior(true)
.setGlobalOption(option) // default iziToast option
.setToastMessageCombiner(combiner) // combiner that combine same level feedback messages.
.initialize();
```


### Display toast using Behavior

add IziToastBehavior to any of components in page.  
(If you set setAutoAppendBehavior to true when initializing settings, no need this code.)
```java
add(new IziToastBehavior());
```

IziToastBehavior converts feedback messages to JavaScript for displaying toast.

Component#success(Serializable), Session#success(Serializable)  
⇒ success toast  

Component#info(Serializable), Session#info(Serializable)  
⇒ info toast  

Component#warn(Serializable), Session#warn(Serializable)  
⇒ warn toast  

Component#error(Serializable), Session#error(SerializableSerializable)  
Component#fatal(Serializable), Session#fatal(Serializable)  
⇒ error toast  


### Display toast manually
In this case, need instance of class that implemented IHeaderResponse or AjaxRequestTarget.

```java
Toast.create(toastLevel, message)
.show(target);
```

