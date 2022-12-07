# RLE and Caesar Encryption

Main:
[![Tests](https://github.com/prdumbledore/FirstLabPI/actions/workflows/gradle-tests.yml/badge.svg?branch=main)](https://github.com/prdumbledore/FirstLabPI/actions/workflows/gradle-tests.yml)
Develop:
[![Tests](https://github.com/prdumbledore/FirstLabPI/actions/workflows/gradle-tests.yml/badge.svg?branch=develop)](https://github.com/prdumbledore/FirstLabPI/actions/workflows/gradle-tests.yml)


App for packing and unpacking by RLE or Caesar

## How to use

Available routes:
```
// packing by RLE
http://127.0.0.1:8080/rle?operation=p&text=pppppp

// unpacking by RLE         
http://127.0.0.1:8080/rle?operation=u&text=6p

// packing by Caesar
http://127.0.0.1:8080/caesar?operation=p&step=3&text=ppp

// unpacking by Caesar
http://127.0.0.1:8080/caesar?operation=U&step=3&text=sss
```
## How to run with docker
Clone repository:
``` console
$ git clone https://github.com/prdumbledore/FirstLabPI.git
```
CD into app folder:
``` console
$ cd FirstLabPI
```
Build docker image:
``` console
$ docker build -t FirstLabPI
```
Run docker image:
``` console
$ docker run -p 8080:8080 FirstLabPI
```

## License

[MIT License](./LICENSE)

=======================
RLE and Caesar Encryption
=======================