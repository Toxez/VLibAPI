# VLibAPI

## Подключение
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