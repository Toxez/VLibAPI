<p align="center">
  <img src="./assets/vlib.png" alt="VLibAPI" width="800"/>
  <br><br>
  <img src="https://jitpack.io/v/Toxez/VLibAPI.svg" alt="JitPack version"/>
  <img src="https://img.shields.io/badge/Minecraft-1.20–1.21+-orange" alt="Supported versions"/>
  <img src="https://img.shields.io/badge/Platform-Paper%20%7C%20Spigot-blue" alt="Platform"/>
  <img src="https://img.shields.io/badge/Java-17%2B-red" alt="Java"/>
</p>

### Что может эта библиотека:

 - Поддержка цветов через TextColor (минимеседж)
 - Удобная система логов через SLF4J
 - Работа с игроками (отправка сообщения, работа с инв, получения ника и UUID игрока и тд...)

# Подключение
### Maven
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

```
```xml
<dependency>
    <groupId>com.github.Toxez</groupId>
    <artifactId>VLibAPI</artifactId>
    <version>1.0.0-RELEASE</version> <!-- укажите актуальную версию -->
    <scope>provided</scope>
</dependency>
```

### Gradle
```groovy
dependencyResolutionManagement { 
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

```groovy
dependencies {
    implementation 'com.github.Toxez:VLibAPI:1.0.0-RELEASE' // укажите актуальную версию
}
```

### plugin.yml
```yaml
depend: [VLibAPI]
```

# Как использовать?

### Цвета:
```java
// отправляет сообщение с цветом игроку
player.sendMessage(TextColor.parse("<red>текст"));
```
или же сообщение от либы где сразу есть поддержка цветов 
```java
PlayerMsg.send(player, "<green>текст");
```
### Логирование:
```java
public class Plugin extends JavaPlugin {

    private LogUtil log; // добавьте это поле

    @Override
    public void onEnable() {
        log = LogUtil.of(this); // добавьте это

        // логи
        log.info("инфо");
        log.warn("варн {}", message);
        log.error("ошибка {}", error);
    }
}
```

В других классах:
```java
public class Test {
    
    private final LogUtil log; // добавьте это

    // и это
    public Test(LogUtil log) { 
        this.log = log;
    }

    public void test() {
        // лог
        log.info("текст");
    }
}
```

### Работа с игроками

PlayerFind — Поиск и состояние
```java
// код внутри {} выполнится ТОЛЬКО если игрок онлайн
PlayerFind.name("Tox_8729").ifPresent(player -> {
    player.sendMessage("да ты крут");
});

// проверка онлайн ли игрок
if (PlayerFind.isOnline("Tox_8729")) { ... }

// получить всех игроков
PlayerFind.all().forEach(p -> ...);
```

Жизнь и еда
```java
// полностью восстановить (хп + еда + убрать эффектыи огонь)
PlayerLife.restore(player);

// Просто похилить
PlayerLife.heal(player);

// Покормить
PlayerLife.feed(player);

// Сменить режим игры
PlayerLife.gm(player, GameMode.CREATIVE);
```

Инвентарь
```java
// очистить всё
PlayerInv.clear(player);

// выдать предмет (если нет места выкинет рядом)
PlayerInv.give(player, itemStack);

// проверка есть ли место
if (PlayerInv.hasSpace(player)) { ... }
```

Сообщения
```java
// Ссообщение в чат (сразу с цветами)
PlayerMsg.send(player, "<green>текст");

// экшнбар
PlayerMsg.action(player, "<green>текст");

// тайтл
PlayerMsg.title(player, "<green>текст", "<green>текст");
```

Благодаря PlayerFind можно например
```java
PlayerFind.name(args[0]).ifPresent(p -> {
    PlayerInv.clear(p);
    PlayerInv.give(p, kitItem);
    PlayerMsg.action(p, "<green>вы получили предметы");
});
```

Пример экшен бара всем игрокам
```java
PlayerFind.all().forEach(p -> PlayerMsg.action(p, "текст"));
```
