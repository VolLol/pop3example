# pop3example
[![Build Status](https://travis-ci.com/VolLol/pop3example.svg?branch=master)](https://travis-ci.com/VolLol/pop3example)

Пример реализации POP3 сервера для демонстрации моих навыклв программирования на Java. Он не имеет базы данных и все данные храняться в памяти. Реализованны следующие команды протокола:
1. APOP
1. DELE
1. LIST
1. NOOP
1. PASS
1. QUIT
1. RETR
1. RSET
1. STAT
1. TOP
1. USER

Для того, чтобы запустить сервер достаточно выполнить следующую команду в консоли:
```bash
gradlew run
```

Так же можно посмотреть запись демонстрации работы.
![Server works](src/test/resources/gif/server.gif)
![Client works](src/test/resources/gif/client.gif)


