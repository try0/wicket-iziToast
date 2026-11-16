# wicket-iziToast

[English](README.md) | 日本語

[Apache Wicket](https://wicket.apache.org/) で [iziToast](http://izitoast.marcelodolza.com/) を使うためのユーティリティです。  
Wicket のフィードバックメッセージを JavaScript に変換し、トーストとして表示します。



[![Build Status](https://github.com/try0/wicket-iziToast/workflows/Java%20CI/badge.svg)](https://github.com/try0/wicket-iziToast/actions?query=workflow%3A%22Java+CI%22)
[![Maven Central](https://img.shields.io/maven-central/v/jp.try0.wicket/wicket-izitoast-core.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/jp.try0.wicket/wicket-izitoast-core)



# バージョン


| バージョン | Wicket | iziToast |
| ---- | ---- | ---- |
| [4.0.0](https://central.sonatype.com/artifact/jp.try0.wicket/wicket-izitoast-core/4.0.0) | 11.x | 1.4.0 |
| [3.0.0](https://central.sonatype.com/artifact/jp.try0.wicket/wicket-izitoast-core/3.0.0) | 10.x | 1.4.0 |




# デモ
[wicket-izitoast-samples](https://try0.jp/app/wicket-izitoast-samples/) モジュールを公開しています。



# 使い方

[サンプル（Wiki）](https://github.com/try0/wicket-iziToast/wiki)

wicket-izitoast-core を依存関係に追加します。  
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


### 初期設定
アプリケーションの初期化処理（Application#init）で、デフォルト値を設定できます。
```java
IziToastSetting
.createInitializer(this)
.setAutoAppendBehavior(true)
.setGlobalOption(option) // iziToast のデフォルトオプション。クライアント側で適用（iziToast.settings(option) を実行）
.setGlobalEachLevelOptions(perLevelOptions) // レベルごとのデフォルトオプション。サーバー側で適用
.setToastMessageCombiner(combiner) // 同じレベルのフィードバックメッセージを結合するコンバイナー
.initialize();
```
#### プロパティ
##### AutoAppendBehavior
true の場合、IziToastBehavior をページに自動で追加します。

##### GlobalOption
トーストのデフォルトオプションです。クライアント側で iziToast.settings(option) を実行して適用します。

##### GlobalEachLevelOptions
レベルごとのトーストのデフォルトオプションです。

```java
ToastOption defaultInfoOption = new ToastOption();
// TODO オプション値を設定

EachLevelToastOptions options = EachLevelToastOptions.builder()
        .setInfoOption(defaultInfoOption)
        .get();

// TODO setGlobalEachLevelOptions(options) で設定
```


オプションの優先順位  
レベルごとのデフォルトオプション（GlobalEachLevelOptions） > デフォルトオプション（GlobalOption）

##### ToastMessageCombiner
同じレベルのメッセージを結合するコンバイナーです。

```java
ToastMessageCombiner combiner = new ToastMessageCombiner();
combiner.setPrefix("・");

// TODO setToastMessageCombiner(combiner) で設定
```

次のコードを実行すると、
```java
error("message1");
error("message2");
```

メッセージが結合され、1つのトーストで表示されます。

![errors](https://user-images.githubusercontent.com/17096601/71779629-dd56bc80-2ffa-11ea-9f45-b75b1f86d5da.PNG)


### トーストの表示

ページに IziToastBehavior を追加します。  
（初期設定で setAutoAppendBehavior を true にしている場合、このコードは不要です。）
```java
add(new IziToastBehavior());
```

IziToastBehavior がフィードバックメッセージを JavaScript に変換し、トーストを表示します。


成功トースト
* org.apache.wicket.Component#success(Serializable)
* org.apache.wicket.Session#success(Serializable)

![success](https://user-images.githubusercontent.com/17096601/71773891-9d66e980-2fa8-11ea-8a3f-83e804549c58.PNG)


情報トースト
* org.apache.wicket.Component#info(Serializable)
* org.apache.wicket.Session#info(Serializable)

![info](https://user-images.githubusercontent.com/17096601/71773896-aa83d880-2fa8-11ea-9149-e0c99aec01b9.PNG)


警告トースト
* org.apache.wicket.Component#warn(Serializable)
* org.apache.wicket.Session#warn(Serializable)

![warn](https://user-images.githubusercontent.com/17096601/71773898-b40d4080-2fa8-11ea-81aa-0776713cdb2e.PNG)


エラートースト
* org.apache.wicket.Component#error(Serializable)
* org.apache.wicket.Session#error(Serializable)
* org.apache.wicket.Component#fatal(Serializable)
* org.apache.wicket.Session#fatal(Serializable)

![error](https://user-images.githubusercontent.com/17096601/71773873-50831300-2fa8-11ea-9df5-0474e7a1b7c1.PNG)



### トーストを手動で表示
IHeaderResponse または AjaxRequestTarget のインスタンスを使って表示します。

```java
Toast.create(toastLevel, message)
.show(target);
```

