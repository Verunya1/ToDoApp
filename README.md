# ToDoApp
Менеджер задач - это приложения для планирования. Здесь можно добавлять, удалять задачи и контролировать их выполняемость.
## Зависимости проекта
* `implementation platform('com.google.firebase:firebase-bom:31.2.3')` - подключение к облачному сервису Firebase
* `implementation 'com.google.firebase:firebase-auth:20.1.0'` - доступ к Firebase Authentication
* `implementation 'com.google.firebase:firebase-database:20.1.0'` - доступ к Firebase Realtime Database
* `implementation 'com.google.firebase:firebase-storage:20.1.0'` - доступ к Firebase Storage
* `implementation 'com.squareup.picasso:picasso:2.8'` - библиотека для работы с изображениями
## Команда для запуска
```
adb shell
am start -n com.package.name/com.package.name.ActivityName
```
