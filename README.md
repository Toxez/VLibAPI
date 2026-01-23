# VLibAPI

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
    <version>1.0.0</version>
    <scope>provided</scope>
</dependency>
```

```yaml
depend: [VLibAPI]
```

# Как использовать?

Цвета:
```java
// отправляет сообщение с цветом игроку
player.sendMessage(TextColor.parse("<red>текст"));
```
Логирование:
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