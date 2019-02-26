# softwerke-practice
Here are the materials on the Developing OSGi Application Project, Feb-Mar 2019, SoftWerke

--

Author: Maksim Kryuchkov


## Project

Состоит из 5-ти этапов. Первый этап ознакомительный

### Stage 2

Лежит в папке stage2. Собранные бандлы лежат в папке bundles.

Проект состоит из трех модулей: org.tilacyn.hello, org.tilacyn.hello.client и org.tilacyn.hello.main

Первые два модуля - это OSGi модули, hello - это сервис, client - клиент. Модуль Main не является OSGi модулем, он просто запускает приложение используя Apache Felix. Через Gogo тоже пробовал собирать, все работает.

Корень проекта: stage2/service

Команда для сборки:

```bash
mvn install
```

Команда для тестирования приложения через Main:

```bash
cd org.tilacyn.hello.main
mvn exec:java
```

### Stage 3

Возникли некоторое вопросы.

Во-первых, в папке stage3 лежит текущая версия проекта. Команда mvn install работает, но main не запускается, выдавая ошибку:

```
Error starting program: org.osgi.framework.BundleException: Unable to resolve tilacyn.org.tilacyn.hello [1](R 1.0): missing requirement [tilacyn.org.tilacyn.hello [1](R 1.0)] osgi.wiring.package; (&(osgi.wiring.package=org.osgi.service.component)(version>=1.3.0)) Unresolved requirements: [[tilacyn.org.tilacyn.hello [1](R 1.0)] osgi.wiring.package; (&(osgi.wiring.package=org.osgi.service.component)(version>=1.3.0))]
org.osgi.framework.BundleException: Unable to resolve tilacyn.org.tilacyn.hello [1](R 1.0): missing requirement [tilacyn.org.tilacyn.hello [1](R 1.0)] osgi.wiring.package; (&(osgi.wiring.package=org.osgi.service.component)(version>=1.3.0)) Unresolved requirements: [[tilacyn.org.tilacyn.hello [1](R 1.0)] osgi.wiring.package; (&(osgi.wiring.package=org.osgi.service.component)(version>=1.3.0))]
	at org.apache.felix.framework.Felix.resolveBundleRevision(Felix.java:4368)
	at org.apache.felix.framework.Felix.startBundle(Felix.java:2281)
	at org.apache.felix.framework.BundleImpl.start(BundleImpl.java:998)
	at org.apache.felix.framework.BundleImpl.start(BundleImpl.java:984)
	at org.tilacyn.hello.main.Main.main(Main.java:32)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.codehaus.mojo.exec.ExecJavaMojo$1.run(ExecJavaMojo.java:297)
	at java.lang.Thread.run(Thread.java:748)
```
