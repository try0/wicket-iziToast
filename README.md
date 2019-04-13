# wicket-iziToast
Apache Wicket utilities for using iziToast.  



# Version
0.2.0  
wicket:8.x iziToast:1.4.0



# Demo
Deployed [wicket-iziToast-samples](https://try0.jp/app/wicket-iziToast-samples) module.



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


### Display toast with Behavior

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

