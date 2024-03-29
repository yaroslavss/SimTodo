## Тестовое задание для зачисления на Mobile-практикум SimbirSoft

### Описание

Мобильное приложение представляет из себя ежедневник или ToDo List.

Выполнены требования Уровня 1 и Уровня 2 (кроме пункта "Создание компонентов экрана кодом с помощью кастомных вью на 
Kotlin или верстка с помощью Jetpack Compose") согласно полученному ТЗ.

Исходные данные для приложения находятся в файле https://github.com/yaroslavss/SimTodo/blob/master/app/src/main/assets/events.json

При старте приложения данные из json-файла загружаются в Room Database. Далее происходит формирование RecyclerView со списком часов за выбранную дату. Запрашивается список задач за эту дату из Room Database и происходит разбивка списка по часам.
Выборка данных осуществляется во ViewModel, затем данные с помощью LiveData передаются во Fragment для отображения. При смене даты в календаре происходит новая выборка.

Внизу экрана с календарем и списком часов/задач находится FAB, при нажатии на которую происходит переход на Fragment создания новой задачи. Если все поля новой задачи заполнены, созданная задача сохраняется в Room Database. Далее происходит возврат на исходный экран (HomeFragment).

### Sreenshots


<img src="https://github.com/yaroslavss/SimTodo/assets/18322997/92aeb868-8007-4e8f-ab67-0f63900a5128" width="200">
&nbsp;&nbsp;
<img src="https://github.com/yaroslavss/SimTodo/assets/18322997/2bd4721d-7cfa-4829-afb8-164549a78786" width="200">
&nbsp;&nbsp;
<img src="https://github.com/yaroslavss/SimTodo/assets/18322997/22611f21-5edf-46dc-b74c-1c2aad38dc3f" width="200">
&nbsp;&nbsp;
<img src="https://github.com/yaroslavss/SimTodo/assets/18322997/4d6c8ccd-dc01-44df-9ac9-1f8d1af39e94" width="200">

