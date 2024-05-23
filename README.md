# Модули информационной системы реабилитации пациентов с неврологическими заболеваниями

## Модуль [arm](arm)
Backend-часть автоматизированного рабочего места врача. [Frontend часть](https://github.com/giezz/rc_doc_arm_front).
## Модуль [med-cart](med-cart)
Электронная медицинская карта (ЭМК), SOAP-сервис. Данный модуль предоставляет API для получения информации о пациентах в 
ЕМИАС: информация о пациенте, эпикризис, история госпитализаций.
## Модуль [icf](icf)
Справочник МКФ (Международная классификация функционирования, ограничений жизнедеятельности и здоровья).
### Cборка сервисов ARM ([arm](arm), [med-cart](med-cart), [icf](icf))
#### Сборка с помощью Spring Boot Maven Plugin и Cloud Native Buildpacks
```
mvn -pl arm,icf,med-cart spring-boot:build-image
docker compose -f docker-compose-arm.yml up
```
#### Сборка с помощью Spring Boot Maven Plugin и Dockerfile
```
docker compose -f docker-compose-arm-dockerfile.yml up
```
## Модуль questionnaire
Конструктор анкет. [Frontend-часть](https://github.com/Soundflog/rc-constructor-forms).
[Пользовательский интерфейс для пациента](https://github.com/Soundflog/rc-questionnaire-desktop-frontend).
