rm -rf ./out
javac --module-source-path src -d out $(find . -name '*.java')
java --module-path out -m easytext/javamodularity.easytext.Main
