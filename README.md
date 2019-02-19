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
