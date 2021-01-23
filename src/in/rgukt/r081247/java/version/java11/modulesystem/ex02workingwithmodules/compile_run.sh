rm -rf ./out
javac --module-source-path src -d out $(find . -name '*.java')
java --module-path out -m easytext.cli/javamodularity.easytext.cli.Main
java --module-path out -m easytext.gui/javamodularity.easytext.gui.Main
