#!/bin/bash
find src -name "*.java" | xargs javac -d out -sourcepath src && java -cp out Main
