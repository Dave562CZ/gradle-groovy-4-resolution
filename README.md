# Groovy compile classpath resolution issue

During groovy test compile classpath resolution both versions of groovy 3 and 4 are added to the classpath. Even when groovy 4 has set capabilities of groovy 3.

To replicate the issue run the following command:
```./gradlew testClasses -q```
