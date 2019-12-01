# wicket-iziToast
[Apache Wicket](https://wicket.apache.org/) utilities for using [iziToast](http://izitoast.marcelodolza.com/).  


[![Build Status](https://travis-ci.org/try0/wicket-iziToast.svg?branch=master)](https://travis-ci.org/try0/wicket-iziToast)
[![codecov](https://codecov.io/gh/try0/wicket-iziToast/branch/master/graph/badge.svg)](https://codecov.io/gh/try0/wicket-iziToast)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jp.try0.wicket%3Awicket-iziToast-parent&metric=alert_status)](https://sonarcloud.io/dashboard?id=jp.try0.wicket%3Awicket-iziToast-parent)


# Version
0.3.0  
wicket:8.x iziToast:1.4.0



# Demo
Deployed [wicket-izitoast-samples](https://try0.jp/app/wicket-izitoast-samples/) module.



# Usage

```
<dependency>
    <groupId>jp.try0.wicket</groupId>
    <artifactId>wicket-izitoast-core</artifactId>
    <version>0.3.0</version>
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

org.apache.wicket.Component#success(Serializable)   org.apache.wicket.Session#success(Serializable)  
⇒ success toast  

org.apache.wicket.Component#info(Serializable)  
org.apache.wicket.Session#info(Serializable)  
⇒ info toast  

org.apache.wicket.Component#warn(Serializable)  
org.apache.wicket.Session#warn(Serializable)  
⇒ warn toast  

org.apache.wicket.Component#error(Serializable)  
org.apache.wicket.Session#error(SerializableSerializable)    
org.apache.wicket.Component#fatal(Serializable)  
org.apache.wicket.Session#fatal(Serializable)  
⇒ error toast  


### Display toast manually
In this case, need instance of class that implemented IHeaderResponse or AjaxRequestTarget.

```java
Toast.create(toastLevel, message)
.show(target);
```

